package dev.matthiesen.common.matthiesen_lib_webhooks.discord.api;

import dev.matthiesen.common.matthiesen_lib_webhooks.discord.exception.DiscordWebhookException;
import dev.matthiesen.common.matthiesen_lib_webhooks.discord.model.WebhookMessage;

public interface DiscordWebhookClient {
    void sendMessage(String webhookUrl, WebhookMessage message) throws DiscordWebhookException;
}

