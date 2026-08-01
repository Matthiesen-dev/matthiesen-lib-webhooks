package dev.matthiesen.matthiesen_core_webhooks.fabric;

import dev.matthiesen.matthiesen_core_webhooks.common.MatthiesenCoreWebhooks;
import net.fabricmc.api.ModInitializer;

public final class MatthiesenCoreWebhooksFabric implements ModInitializer {
    public MatthiesenCoreWebhooksFabric() {}

    @Override
    public void onInitialize() {
        MatthiesenCoreWebhooks.createInfoLog("Loading for Fabric Mod Loader");
        MatthiesenCoreWebhooks.initialize();
    }
}
