package dev.matthiesen.common.matthiesen_lib_webhooks;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MatthiesenLibWebhooksConstants {
    public static final String MOD_ID = "matthiesen_lib_webhooks";
    public static final String MOD_NAME = "Matthiesen Lib Webhooks";

    public static final Logger LOGGER = LogManager.getLogger(MOD_NAME);

    public static void createInfoLog(String message) {
        LOGGER.info(message);
    }

    public static void createErrorLog(String message) {
        LOGGER.error(message);
    }
}
