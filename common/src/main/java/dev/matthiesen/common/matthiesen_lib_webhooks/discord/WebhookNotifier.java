package dev.matthiesen.common.matthiesen_lib_webhooks.discord;

import dev.matthiesen.common.matthiesen_lib_webhooks.MatthiesenLibWebhooksConstants;
import dev.matthiesen.common.matthiesen_lib_webhooks.discord.api.DiscordWebhookClient;
import dev.matthiesen.common.matthiesen_lib_webhooks.discord.exception.DiscordWebhookException;
import dev.matthiesen.common.matthiesen_lib_webhooks.discord.model.WebhookMessage;

public class WebhookNotifier {
    private final DiscordWebhookClient client;
    private final String webhookUrl;

    public WebhookNotifier(DiscordWebhookClient client, String webhookUrl) {
        this.client = client;
        this.webhookUrl = webhookUrl;
    }

    public boolean isConfigured() {
        return webhookUrl != null && !webhookUrl.isBlank();
    }

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

