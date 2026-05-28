package dev.matthiesen.common.matthiesen_lib_webhooks.discord.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dev.matthiesen.common.matthiesen_lib_webhooks.discord.exception.DiscordWebhookDeliveryException;
import dev.matthiesen.common.matthiesen_lib_webhooks.discord.exception.DiscordWebhookException;
import dev.matthiesen.common.matthiesen_lib_webhooks.discord.exception.DiscordWebhookSerializationException;
import dev.matthiesen.common.matthiesen_lib_webhooks.discord.model.WebhookFile;
import dev.matthiesen.common.matthiesen_lib_webhooks.discord.model.WebhookMessage;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class WebhookService {
    private static final int CONNECT_TIMEOUT_MS = 5000;
    private static final int READ_TIMEOUT_MS = 5000;

    private final Gson gson;

    public WebhookService() {
        this(new GsonBuilder().disableHtmlEscaping().create());
    }

    public WebhookService(Gson gson) {
        this.gson = gson;
    }

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

