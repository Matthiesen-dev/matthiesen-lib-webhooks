package dev.matthiesen.matthiesen_core_webhooks.common;

/**
 * MatthiesenLibWebhooks is a utility mod for sending webhook messages, particularly to Discord. It provides methods to
 * create webhook notifiers and send messages using a builder pattern for easy message construction.
 */
public final class MatthiesenLibWebhooks {
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

}
