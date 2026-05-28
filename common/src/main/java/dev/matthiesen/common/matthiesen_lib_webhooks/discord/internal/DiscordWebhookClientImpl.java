package dev.matthiesen.common.matthiesen_lib_webhooks.discord.internal;

import dev.matthiesen.common.matthiesen_lib_webhooks.discord.api.DiscordWebhookClient;
import dev.matthiesen.common.matthiesen_lib_webhooks.discord.exception.DiscordWebhookException;
import dev.matthiesen.common.matthiesen_lib_webhooks.discord.model.WebhookMessage;
import dev.matthiesen.common.matthiesen_lib_webhooks.discord.service.WebhookService;

public class DiscordWebhookClientImpl implements DiscordWebhookClient {
    private final WebhookService webhookService;

    public DiscordWebhookClientImpl(WebhookService webhookService) {
        this.webhookService = webhookService;
    }

    @Override
    public void sendMessage(String webhookUrl, WebhookMessage message) throws DiscordWebhookException {
        webhookService.send(webhookUrl, message);
    }
}

