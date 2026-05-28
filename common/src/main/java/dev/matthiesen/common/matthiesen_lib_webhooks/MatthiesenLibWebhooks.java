package dev.matthiesen.common.matthiesen_lib_webhooks;

import dev.matthiesen.common.matthiesen_lib_webhooks.discord.WebhookNotifier;
import dev.matthiesen.common.matthiesen_lib_webhooks.discord.internal.DiscordWebhookClientImpl;
import dev.matthiesen.common.matthiesen_lib_webhooks.discord.model.WebhookMessage;
import dev.matthiesen.common.matthiesen_lib_webhooks.discord.model.WebhookMessageBuilder;
import dev.matthiesen.common.matthiesen_lib_webhooks.discord.service.WebhookService;

import java.util.Objects;
import java.util.function.Consumer;

/**
 * MatthiesenLibWebhooks is a utility mod for sending webhook messages, particularly to Discord. It provides methods to
 * create webhook notifiers and send messages using a builder pattern for easy message construction.
 */
public class MatthiesenLibWebhooks {
    /**
     * Private constructor to prevent instantiation of the MatthiesenLibWebhooks class, as it is intended to be a utility class with static methods.
     * This design choice ensures that the class cannot be instantiated and is only used for its static functionality, which includes initializing the
     * library and creating webhook notifiers for sending messages to Discord webhooks.
     */
    private MatthiesenLibWebhooks() {}

    /**
     * Initializes the MatthiesenLibWebhooks library. This method can be called to perform any necessary setup or configuration before using the
     * library's functionality. In this implementation, it simply logs an informational message indicating that the library has been initialized,
     * but it can be extended in the future to include more complex initialization logic if needed.
     */
    public static void initialize() {
        MatthiesenLibWebhooksConstants.createInfoLog("Initialized");
    }

    /**
     * Creates a new WebhookNotifier instance using the provided webhook URL. This method initializes a DiscordWebhookClientImpl with a
     * WebhookService and then constructs a WebhookNotifier that can be used to send messages to the specified Discord webhook. The webhook
     * URL should be properly configured for the WebhookNotifier to function correctly, as it is used to identify the specific Discord channel
     * and webhook that will receive the messages sent by this notifier.
     * @param webhookUrl The URL of the Discord webhook to which messages will be sent. This URL should be configured properly for the
     *                   WebhookNotifier to function correctly, as it is used to identify the specific Discord channel and webhook that
     *                   will receive the messages sent by this notifier. If the webhook URL is not configured (i.e., null or blank), the
     *                   WebhookNotifier created by this method will not attempt to send any messages and will simply return without performing
     *                   any actions when its sendDiscordMessage method is called.
     * @return A new WebhookNotifier instance that can be used to send messages to the specified Discord webhook. This notifier is initialized
     * with a DiscordWebhookClientImpl that uses a WebhookService to handle the actual communication with the Discord API, allowing the
     * WebhookNotifier to focus on the higher-level logic of sending messages without worrying about the underlying network communication details.
     */
    public static WebhookNotifier createWebhookNotifier(String webhookUrl) {
        return new WebhookNotifier(new DiscordWebhookClientImpl(new WebhookService()), webhookUrl);
    }

    /**
     * A nested static class that provides a convenient interface for sending webhook messages, particularly to Discord. It encapsulates
     * a WebhookNotifier and offers methods to send messages directly or through a builder pattern for easy message construction. This
     * class can be used to simplify the process of sending messages to Discord webhooks by providing a more user-friendly API for
     * constructing and sending messages.
     */
    public static class Webhooks {
        private final WebhookNotifier notifier;

        /**
         * Constructs a new Webhooks instance with the specified WebhookNotifier. This constructor allows for dependency injection of a
         * WebhookNotifier, enabling greater flexibility and testability when using the Webhooks class to send messages to Discord webhooks.
         * The provided WebhookNotifier is used internally to handle the actual sending of messages to the configured Discord webhook, allowing
         * the Webhooks class to focus on providing a convenient interface for message construction and sending without worrying about the
         * underlying communication details.
         * @param notifier The WebhookNotifier instance that will be used to send messages to the Discord webhook. This notifier should be
         *                 properly configured with a valid webhook URL for it to function correctly, as it is responsible for handling the
         *                 communication with the Discord API and sending messages to the specified webhook. If the notifier is not properly
         *                 configured (e.g., if it has a null or blank webhook URL), the Webhooks class will not be able to send messages
         *                 successfully, and any attempts to send messages using this notifier may result in errors or exceptions being logged.
         */
        public Webhooks(WebhookNotifier notifier) {
            this.notifier = Objects.requireNonNull(notifier, "notifier must not be null");
        }

        /**
         * Constructs a new Webhooks instance with the specified webhook URL. This constructor creates a WebhookNotifier using the provided
         * webhook URL and then initializes the Webhooks instance with that notifier. The webhook URL should be properly configured for the
         * WebhookNotifier to function correctly, as it is used to identify the specific Discord channel and webhook that will receive the
         * messages sent by this notifier. If the webhook URL is not configured (i.e., null or blank), the WebhookNotifier created by this
         * constructor will not attempt to send any messages and will simply return without performing any actions when its sendDiscordMessage
         * method is called.
         * @param webhookUrl The URL of the Discord webhook to which messages will be sent. This URL should be configured properly for the
         *                   WebhookNotifier to function correctly, as it is used to identify the specific Discord channel and webhook that
         *                   will receive the messages sent by this notifier. If the webhook URL is not configured (i.e., null or blank),
         *                   the WebhookNotifier created by this constructor will not attempt to send any messages and will simply return
         *                   without performing any actions when its sendDiscordMessage method is called.
         */
        public Webhooks(String webhookUrl) {
            this(createWebhookNotifier(webhookUrl));
        }

        /**
         * Sends a message to the configured Discord webhook using the provided WebhookMessage object. This method uses the internal WebhookNotifier
         * to send the message, which will handle the communication with the Discord API and ensure that the message is sent to the specified webhook
         * URL. If the WebhookNotifier is not properly configured (e.g., if it has a null or blank webhook URL), this method will not attempt to send
         * the message and will simply return without performing any actions. If an error occurs during the sending process, an error message will be
         * logged using the MatthiesenLibWebhooksConstants.createErrorLog method, providing details about the failure to send the Discord webhook message,
         * including any exception messages for better debugging and error handling purposes.
         * @param message The WebhookMessage object that contains the content and details of the message to be sent to the Discord webhook. This message
         *                should be properly constructed according to the requirements of the Discord API, including any necessary fields such as content,
         *                embeds, username, avatar URL, etc., to ensure that it can be successfully sent and displayed in the target Discord channel
         *                associated with the webhook URL. If the message is not properly constructed or if there are issues with the webhook configuration,
         *                the sending process may fail, and appropriate error handling will be performed by logging error messages for debugging purposes.
         */
        public void sendMessage(WebhookMessage message) {
            notifier.sendDiscordMessage(message);
        }

        /**
         * Sends a message to the configured Discord webhook using a WebhookMessageBuilder. This method accepts a Consumer that allows the caller
         * to customize the WebhookMessageBuilder, which is then used to build a WebhookMessage object that is sent to the Discord webhook. This
         * approach provides a convenient way to construct messages using a builder pattern, allowing for more flexible and readable message construction
         * while still leveraging the underlying WebhookNotifier to handle the actual sending of messages to the specified webhook URL. If the WebhookNotifier
         * is not properly configured (e.g., if it has a null or blank webhook URL), this method will not attempt to send the message and will simply return
         * without performing any actions. If an error occurs during the sending process, an error message will be logged using the
         * MatthiesenLibWebhooksConstants.createErrorLog method, providing details about the failure to send the Discord webhook message,
         * including any exception messages for better debugging and error handling purposes.
         * @param messageBuilderCustomizer A Consumer that accepts a WebhookMessageBuilder, allowing the caller to customize the builder to construct
         *                                 a WebhookMessage object. This customizer should configure the builder with the necessary fields and content
         *                                 according to the requirements of the Discord API, such as setting the message content, embeds, username,
         *                                 avatar URL, etc., to ensure that the resulting WebhookMessage can be successfully sent and displayed in
         *                                 the target Discord channel associated with the webhook URL. If the customizer does not properly configure
         *                                 the builder or if there are issues with the webhook configuration, the sending process may fail, and appropriate
         *                                 error handling will be performed by logging error messages for debugging purposes.
         */
        public void sendMessage(Consumer<WebhookMessageBuilder> messageBuilderCustomizer) {
            sendMessage(message(messageBuilderCustomizer));
        }

        /**
         * Constructs a WebhookMessage using a WebhookMessageBuilder that is customized by the provided Consumer. This method creates a new
         * WebhookMessageBuilder, applies the customizer to it, and then builds a WebhookMessage object that can be sent to the Discord webhook.
         * This approach allows for a more flexible and readable way to construct messages using a builder pattern, enabling callers to easily
         * configure the message content and details while still ensuring that the resulting WebhookMessage is properly constructed according to
         * the requirements of the Discord API.
         * @param messageBuilderCustomizer A Consumer that accepts a WebhookMessageBuilder, allowing the caller to customize the builder to construct
         *                                 a WebhookMessage object. This customizer should configure the builder with the necessary fields and content
         *                                 according to the requirements of the Discord API, such as setting the message content, embeds, username,
         *                                 avatar URL, etc., to ensure that the resulting WebhookMessage can be successfully sent and displayed in the
         *                                 target Discord channel associated with the webhook URL. If the customizer does not properly configure the
         *                                 builder or if there are issues with the webhook configuration, the sending process may fail, and appropriate
         *                                 error handling will be performed by logging error messages for debugging purposes.
         * @return A WebhookMessage object that has been constructed using the provided WebhookMessageBuilder customizer. This message is built according
         * to the configurations specified in the customizer and is ready to be sent to the Discord webhook using the sendMessage method. If the customizer
         * does not properly configure the builder or if there are issues with the webhook configuration, the sending process may fail, and appropriate error
         * handling will be performed by logging error messages for debugging purposes.
         */
        public WebhookMessage message(Consumer<WebhookMessageBuilder> messageBuilderCustomizer) {
            Objects.requireNonNull(messageBuilderCustomizer, "messageBuilderCustomizer must not be null");
            WebhookMessageBuilder builder = new WebhookMessageBuilder();
            messageBuilderCustomizer.accept(builder);
            return builder.build();
        }
    }
}
