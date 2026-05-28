package dev.matthiesen.common.matthiesen_lib_webhooks.discord.internal;

import dev.matthiesen.common.matthiesen_lib_webhooks.discord.api.DiscordWebhookClient;
import dev.matthiesen.common.matthiesen_lib_webhooks.discord.exception.DiscordWebhookException;
import dev.matthiesen.common.matthiesen_lib_webhooks.discord.model.WebhookMessage;
import dev.matthiesen.common.matthiesen_lib_webhooks.discord.service.WebhookService;

/**
 * Implementation of the DiscordWebhookClient interface that uses a WebhookService to send messages to Discord webhooks.
 */
public class DiscordWebhookClientImpl implements DiscordWebhookClient {
    private final WebhookService webhookService;

    /**
     * Constructs a new DiscordWebhookClientImpl with the specified WebhookService.
     * @param webhookService The WebhookService instance that will be used to send messages to Discord webhooks. This service is responsible for
     *                       handling the actual HTTP requests and responses when communicating with the Discord API, allowing the DiscordWebhookClientImpl
     *                       to focus on the higher-level logic of sending messages without worrying about the underlying network communication details.
     */
    public DiscordWebhookClientImpl(WebhookService webhookService) {
        this.webhookService = webhookService;
    }

    @Override
    public void sendMessage(String webhookUrl, WebhookMessage message) throws DiscordWebhookException {
        webhookService.send(webhookUrl, message);
    }
}

