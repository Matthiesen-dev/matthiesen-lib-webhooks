package dev.matthiesen.matthiesen_core_webhooks.neoforge;

import dev.matthiesen.matthiesen_core_webhooks.common.MatthiesenCoreWebhooks;
import net.neoforged.fml.common.Mod;

@Mod(MatthiesenCoreWebhooks.MOD_ID)
public final class MatthiesenCoreWebhooksNeoForge {
    public MatthiesenCoreWebhooksNeoForge() {
        MatthiesenCoreWebhooks.createInfoLog("Loading for NeoForge Mod Loader");
        MatthiesenCoreWebhooks.initialize();
    }
}
