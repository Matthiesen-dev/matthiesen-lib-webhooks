package dev.matthiesen.matthiesen_core_webhooks.fabric;

import dev.matthiesen.matthiesen_core_webhooks.common.MatthiesenLibWebhooks;
import dev.matthiesen.matthiesen_core_webhooks.common.MatthiesenLibWebhooksConstants;
import net.fabricmc.api.ModInitializer;

/**
 * This is the main class for the Fabric implementation of the mod. It initializes the mod and sets up any necessary event listeners.
 */
public final class MatthiesenLibWebhooksFabric implements ModInitializer {
    /**
     * Constructor for the mod. This is where you can set up any necessary fields or perform any initial setup before the mod is fully initialized.
     */
    public MatthiesenLibWebhooksFabric() {}

    /**
     * This method is called when the mod is initialized. It is responsible for setting up the mod and registering any necessary event listeners or commands.
     */
    @Override
    public void onInitialize() {
        MatthiesenLibWebhooksConstants.createInfoLog("Loading for Fabric Mod Loader");
        MatthiesenLibWebhooks.initialize();
    }
}
