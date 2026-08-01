package dev.matthiesen.matthiesen_core_webhooks.common.service;

import dev.matthiesen.matthiesen_core.common.api.discord.WebhookNotifierInstance;
import dev.matthiesen.matthiesen_core.common.api.discord.WebhookNotifierService;
import dev.matthiesen.matthiesen_core_webhooks.common.MatthiesenCoreWebhooks;

public final class DiscordNotifierService implements WebhookNotifierService {
    @Override
    public void initialize() {
        MatthiesenCoreWebhooks.createInfoLog("DiscordNotifierService initialized. Webhook notifications will attempt to be sent to Discord.");
    }

    @Override
    public TYPE type() {
        return TYPE.DISCORD;
    }

    @Override
    public WebhookNotifierInstance makeInstance(String webhookUrl) {
        return new DiscordNotifierInstance(webhookUrl);
    }
}
