package dev.matthiesen.common.matthiesen_lib_webhooks.discord;

import dev.matthiesen.common.matthiesen_lib_webhooks.MatthiesenLibWebhooksConstants;
import dev.matthiesen.common.matthiesen_lib_webhooks.discord.api.DiscordWebhookClient;
import dev.matthiesen.common.matthiesen_lib_webhooks.discord.exception.DiscordWebhookException;
import dev.matthiesen.common.matthiesen_lib_webhooks.discord.model.WebhookMessage;

/**
 * A class responsible for sending messages to Discord webhooks using a provided DiscordWebhookClient. It checks if the
 * webhook URL is configured before attempting to send messages and handles any exceptions that may occur during the sending
 * process by logging an error message.
 */
public class WebhookNotifier {
    private final DiscordWebhookClient client;
    private final String webhookUrl;

    /**
     * Constructs a new WebhookNotifier with the specified DiscordWebhookClient and webhook URL.
     * @param client The DiscordWebhookClient instance that will be used to send messages to the Discord webhook. This client
     *               is responsible for handling the communication with the Discord API, allowing the WebhookNotifier to focus
     *               on the higher-level logic of sending messages without worrying about the underlying network communication details.
     * @param webhookUrl The URL of the Discord webhook to which messages will be sent. This URL should be configured properly
     *                   for the WebhookNotifier to function correctly, as it is used to identify the specific Discord channel
     *                   and webhook that will receive the messages sent by this notifier. If the webhook URL is not configured
     *                   (i.e., null or blank), the WebhookNotifier will not attempt to send any messages and will simply return
     *                   without performing any actions.
     */
    public WebhookNotifier(DiscordWebhookClient client, String webhookUrl) {
        this.client = client;
        this.webhookUrl = webhookUrl;
    }

    /**
     * Checks if the webhook URL is configured (i.e., not null and not blank) before attempting to send messages. This method
     * is used to ensure that the WebhookNotifier only tries to send messages when a valid webhook URL is provided, preventing
     * unnecessary errors and exceptions that may occur when trying to send messages to an invalid or missing webhook URL. If
     * the webhook URL is not configured, the WebhookNotifier will simply return without performing any actions when the
     * sendDiscordMessage method is called.
     * @return true if the webhook URL is configured (not null and not blank), false otherwise. This indicates whether the
     * WebhookNotifier is ready to send messages to the specified Discord webhook or if it should skip sending messages due
     * to a missing or invalid webhook URL.
     */
    public boolean isConfigured() {
        return webhookUrl != null && !webhookUrl.isBlank();
    }

    /**
     * Sends a message to the configured Discord webhook using the provided DiscordWebhookClient. This method first checks if
     * the webhook URL is configured before attempting to send the message. If the webhook URL is not configured, the method will
     * simply return without performing any actions. If the webhook URL is configured, the method will try to send the message
     * using the DiscordWebhookClient and will catch any DiscordWebhookException that may occur during the sending process. If
     * an exception is caught, an error message will be logged using the MatthiesenLibWebhooksConstants.createErrorLog method,
     * providing details about the failure to send the Discord webhook message, including the exception message for better
     * debugging and error handling purposes.
     * @param message The WebhookMessage object that contains the content and details of the message to be sent to the Discord webhook.
     *                This message should be properly constructed according to the requirements of the Discord API, including any
     *                necessary fields such as content, embeds, username, avatar URL, etc., to ensure that it can be successfully
     *                sent and displayed in the target Discord channel associated with the webhook URL.
     */
    public void sendDiscordMessage(WebhookMessage message) {
        if (!isConfigured()) {
            return;
        }

        try {
            client.sendMessage(webhookUrl, message);
        } catch (DiscordWebhookException e) {
            MatthiesenLibWebhooksConstants.createErrorLog("Failed to send Discord webhook: " + e.getMessage());
        }
    }
}

