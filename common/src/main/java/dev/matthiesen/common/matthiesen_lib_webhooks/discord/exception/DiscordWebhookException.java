package dev.matthiesen.common.matthiesen_lib_webhooks.discord.exception;

public class DiscordWebhookException extends Exception {
    public DiscordWebhookException(String message) {
        super(message);
    }

    public DiscordWebhookException(String message, Throwable cause) {
        super(message, cause);
    }
}

