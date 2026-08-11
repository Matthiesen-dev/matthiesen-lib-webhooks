package dev.matthiesen.matthiesen_core_webhooks.common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class MatthiesenCoreWebhooks {
    public static final String MOD_ID = "matthiesen_core_webhooks";
    public static final String MOD_NAME = "Matthiesen Core Webhooks";
    public static final Logger LOGGER = LogManager.getLogger(MOD_NAME);

    private MatthiesenCoreWebhooks() {}

    public static void initialize() {
        createInfoLog("Matthiesen Core Webhooks service initialized.");
    }

    public static void createInfoLog(String message) {
        LOGGER.info(message);
    }

}
