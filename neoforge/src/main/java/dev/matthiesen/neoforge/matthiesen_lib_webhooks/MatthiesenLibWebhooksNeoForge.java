package dev.matthiesen.neoforge.matthiesen_lib_webhooks;

import dev.matthiesen.common.matthiesen_lib_webhooks.MatthiesenLibWebhooks;
import dev.matthiesen.common.matthiesen_lib_webhooks.MatthiesenLibWebhooksConstants;
import net.neoforged.fml.common.Mod;

/**
 * This is the main class for the NeoForge implementation of the mod. It initializes the mod and sets up any necessary event listeners.
 */
@Mod(MatthiesenLibWebhooksConstants.MOD_ID)
public class MatthiesenLibWebhooksNeoForge {
    /**
     * Constructor for the mod. This is where you can set up any necessary fields or perform any initial setup before the mod is fully initialized.
     */
    public MatthiesenLibWebhooksNeoForge() {
        MatthiesenLibWebhooksConstants.createInfoLog("Loading for NeoForge Mod Loader");
        MatthiesenLibWebhooks.initialize();
    }
}
