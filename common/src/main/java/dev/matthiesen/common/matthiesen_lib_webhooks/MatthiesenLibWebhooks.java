package dev.matthiesen.common.matthiesen_lib_webhooks;

import dev.matthiesen.common.matthiesen_lib_webhooks.discord.WebhookNotifier;
import dev.matthiesen.common.matthiesen_lib_webhooks.discord.internal.DiscordWebhookClientImpl;
import dev.matthiesen.common.matthiesen_lib_webhooks.discord.model.WebhookMessage;
import dev.matthiesen.common.matthiesen_lib_webhooks.discord.model.WebhookMessageBuilder;
import dev.matthiesen.common.matthiesen_lib_webhooks.discord.service.WebhookService;

import java.util.Objects;
import java.util.function.Consumer;

public class MatthiesenLibWebhooks {
    public static void initialize() {
        MatthiesenLibWebhooksConstants.createInfoLog("Initialized");
    }

    public static WebhookNotifier createWebhookNotifier(String webhookUrl) {
        return new WebhookNotifier(new DiscordWebhookClientImpl(new WebhookService()), webhookUrl);
    }

    public static class Webhooks {
        private final WebhookNotifier notifier;

        public Webhooks(WebhookNotifier notifier) {
            this.notifier = Objects.requireNonNull(notifier, "notifier must not be null");
        }

        public Webhooks(String webhookUrl) {
            this(createWebhookNotifier(webhookUrl));
        }

        public void sendMessage(WebhookMessage message) {
            notifier.sendDiscordMessage(message);
        }

        public void sendMessage(Consumer<WebhookMessageBuilder> messageBuilderCustomizer) {
            sendMessage(message(messageBuilderCustomizer));
        }

        public WebhookMessage message(Consumer<WebhookMessageBuilder> messageBuilderCustomizer) {
            Objects.requireNonNull(messageBuilderCustomizer, "messageBuilderCustomizer must not be null");
            WebhookMessageBuilder builder = new WebhookMessageBuilder();
            messageBuilderCustomizer.accept(builder);
            return builder.build();
        }
    }
}
