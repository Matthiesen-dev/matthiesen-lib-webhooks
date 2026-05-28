package dev.matthiesen.common.matthiesen_lib_webhooks;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This class contains constants and utility methods for the Matthiesen Lib Webhooks mod.
 */
public class MatthiesenLibWebhooksConstants {
    /**
     * The unique identifier for the mod, used for registration and logging purposes.
     */
    public static final String MOD_ID = "matthiesen_lib_webhooks";
    /**
     * The human-readable name of the mod, used for logging and display purposes.
     */
    public static final String MOD_NAME = "Matthiesen Lib Webhooks";

    /**
     * The logger instance for the mod, used to log informational messages, warnings, and errors related to the mod's functionality.
     * This logger is configured to use the mod's name as its identifier, allowing for easy filtering and identification of log messages in the console or log files.
     */
    public static final Logger LOGGER = LogManager.getLogger(MOD_NAME);

    /**
     * Utility method to create an informational log message using the mod's logger. This method can be used throughout the mod's codebase to log
     * important information, such as successful operations, configuration details, or any other relevant messages that can help with debugging and
     * monitoring the mod's behavior during runtime.
     * @param message The informational message to be logged. This should provide useful context about the operation or event being logged, and can
     *                include details such as the outcome of a process, the state of certain variables, or any other relevant information that can
     *                assist developers and users in understanding the mod's behavior and performance.
     */
    public static void createInfoLog(String message) {
        LOGGER.info(message);
    }

    /**
     * Utility method to create an error log message using the mod's logger. This method can be used throughout the mod's codebase to log error messages,
     * exceptions, or any other issues that arise during the mod's execution. Logging errors is crucial for debugging and troubleshooting, as it provides
     * insights into what went wrong and helps developers identify and fix issues in the code.
     * @param message The error message to be logged. This should provide detailed information about the error that occurred, including any relevant context,
     *                such as the operation being performed when the error occurred, the values of important variables, or any other information that can assist
     *                developers in understanding the nature of the error and how to address it effectively.
     */
    public static void createErrorLog(String message) {
        LOGGER.error(message);
    }
}
