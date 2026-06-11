package dev.matthiesen.common.matthiesen_lib_webhooks.discord.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dev.matthiesen.common.matthiesen_lib_api.core.discord.exception.DiscordWebhookDeliveryException;
import dev.matthiesen.common.matthiesen_lib_api.core.discord.exception.DiscordWebhookException;
import dev.matthiesen.common.matthiesen_lib_api.core.discord.exception.DiscordWebhookSerializationException;
import dev.matthiesen.common.matthiesen_lib_api.core.discord.model.WebhookFile;
import dev.matthiesen.common.matthiesen_lib_api.core.discord.model.WebhookMessage;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Service responsible for sending webhook messages to Discord.
 * This class handles the HTTP communication with the Discord API, including serialization of messages to JSON, handling
 * multipart form data for file uploads, and processing the responses from the Discord API. It provides a method to send
 * a WebhookMessage to a specified Discord webhook URL, and throws appropriate exceptions if any errors occur during the
 * process, such as serialization issues or delivery failures. The WebhookService is designed to be used by higher-level
 * clients, such as the DiscordWebhookClientImpl, to abstract away the details of the HTTP communication and error handling
 * when sending messages to Discord webhooks.
 */
public class WebhookService {
    private static final int CONNECT_TIMEOUT_MS = 5000;
    private static final int READ_TIMEOUT_MS = 5000;

    private final Gson gson;

    /**
     * Constructs a new WebhookService with a default Gson instance for JSON serialization. The Gson instance is configured
     * to disable HTML escaping, which allows for better handling of special characters in webhook messages without escaping
     * them unnecessarily. This default constructor provides a convenient way to create a WebhookService with standard JSON
     * serialization settings, while still allowing for customization by providing an alternative constructor that accepts a
     * custom Gson instance if needed.
     */
    public WebhookService() {
        this(new GsonBuilder().disableHtmlEscaping().create());
    }

    /**
     * Constructs a new WebhookService with the specified Gson instance for JSON serialization. This constructor allows for
     * customization of the JSON serialization behavior by accepting a custom Gson instance, which can be configured with
     * specific settings or type adapters as needed. By providing this constructor, users of the WebhookService can have
     * more control over how their webhook messages are serialized to JSON before being sent to the Discord API, allowing
     * for greater flexibility in handling different message formats or special cases that may arise during serialization.
     * @param gson The Gson instance to be used for JSON serialization of webhook messages. This instance should be properly
     *             configured to handle the specific requirements of the webhook messages being sent, such as disabling HTML
     *             escaping or registering custom type adapters for complex message structures. By allowing a custom Gson instance
     *             to be provided, the WebhookService can accommodate a wide range of serialization needs and ensure that webhook
     *             messages are correctly formatted for delivery to the Discord API.
     */
    public WebhookService(Gson gson) {
        this.gson = gson;
    }

    /**
     * Sends a webhook message to the specified Discord webhook URL. This method handles the entire process of preparing the
     * HTTP request, including serializing the WebhookMessage to JSON, handling multipart form data if files are included in
     * the message, and processing the response from the Discord API. If any errors occur during this process, such as issues
     * with serialization or delivery failures indicated by non-successful HTTP status codes, appropriate exceptions are thrown
     * to provide detailed information about the error. This method serves as the core functionality of the WebhookService,
     * allowing clients to send messages to Discord webhooks with robust error handling and support for complex message structures
     * that may include file attachments.
     * @param webhookUrl The URL of the Discord webhook to which the message should be sent. This URL must be a valid Discord
     *                   webhook URL, and it is used to identify the specific webhook endpoint that will receive the message.
     *                   The method will validate that the webhookUrl is not null or blank before attempting to send the message,
     *                   and will throw a DiscordWebhookException if the URL is invalid.
     * @param message The WebhookMessage object containing the content of the message to be sent to the Discord webhook.
     *                This object may include text content, embeds, and file attachments, and it will be serialized to JSON
     *                for delivery to the Discord API. The method will validate that the message is not null before attempting
     *                to send it, and will throw a DiscordWebhookException if the message is null.
     * @throws DiscordWebhookException If an error occurs during the process of sending the webhook message, such as an
     * invalid webhook URL, a null message, serialization issues, or delivery failures indicated by non-successful HTTP
     * status codes from the Discord API. This exception provides detailed information about the nature of the error, allowing
     * clients to handle it appropriately in their application logic.
     */
    public void send(String webhookUrl, WebhookMessage message) throws DiscordWebhookException {
        if (webhookUrl == null || webhookUrl.isBlank()) {
            throw new DiscordWebhookException("Webhook URL must not be blank");
        }
        if (message == null) {
            throw new DiscordWebhookException("Webhook message must not be null");
        }

        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) new URI(webhookUrl).toURL().openConnection();
            connection.setRequestMethod("POST");
            connection.setConnectTimeout(CONNECT_TIMEOUT_MS);
            connection.setReadTimeout(READ_TIMEOUT_MS);
            connection.setDoOutput(true);

            String payload;
            try {
                payload = gson.toJson(message);
            } catch (Exception e) {
                throw new DiscordWebhookSerializationException("Failed to serialize webhook message", e);
            }

            List<WebhookFile> files = message.getFiles();
            if (files != null && !files.isEmpty()) {
                writeMultipartPayload(connection, payload, files);
            } else {
                writeJsonPayload(connection, payload);
            }

            int responseCode = connection.getResponseCode();
            if (responseCode < 200 || responseCode >= 300) {
                String responseBody = readResponseBody(connection);
                throw new DiscordWebhookDeliveryException(
                        "Discord webhook responded with HTTP status " + responseCode + ": " + responseBody,
                        responseCode
                );
            }
        } catch (DiscordWebhookException e) {
            throw e;
        } catch (Exception e) {
            throw new DiscordWebhookException("Unexpected error while sending webhook", e);
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    private static void writeJsonPayload(HttpURLConnection connection, String payload) throws IOException {
        connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        try (OutputStream outputStream = connection.getOutputStream()) {
            outputStream.write(payload.getBytes(StandardCharsets.UTF_8));
            outputStream.flush();
        }
    }

    private static void writeMultipartPayload(HttpURLConnection connection, String payload, List<WebhookFile> files) throws IOException {
        String boundary = "===" + System.currentTimeMillis() + "===";
        connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);

        try (OutputStream outputStream = connection.getOutputStream();
             PrintWriter writer = new PrintWriter(outputStream, true, StandardCharsets.UTF_8)) {

            writer.append("--").append(boundary).append("\r\n");
            writer.append("Content-Disposition: form-data; name=\"payload_json\"\r\n");
            writer.append("Content-Type: application/json; charset=UTF-8\r\n\r\n");
            writer.append(payload).append("\r\n");
            writer.flush();

            for (int i = 0; i < files.size(); i++) {
                WebhookFile file = files.get(i);
                writer.append("--").append(boundary).append("\r\n");
                writer.append("Content-Disposition: form-data; name=\"files[").append(String.valueOf(i)).append("]\"; filename=\"")
                        .append(file.getFilename()).append("\"\r\n");
                writer.append("Content-Type: application/octet-stream\r\n\r\n");
                writer.flush();

                outputStream.write(file.getData());
                outputStream.flush();

                writer.append("\r\n");
                writer.flush();
            }

            writer.append("--").append(boundary).append("--\r\n");
            writer.flush();
        }
    }

    private static String readResponseBody(HttpURLConnection connection) {
        InputStream errorStream = connection.getErrorStream();
        if (errorStream == null) {
            return "<empty>";
        }

        try (InputStream stream = errorStream; ByteArrayOutputStream output = new ByteArrayOutputStream()) {
            stream.transferTo(output);
            return output.toString(StandardCharsets.UTF_8);
        } catch (IOException e) {
            return "<unreadable>";
        }
    }
}

