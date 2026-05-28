package dev.matthiesen.fabric.matthiesen_lib_webhooks;

import dev.matthiesen.common.matthiesen_lib_webhooks.MatthiesenLibWebhooks;
import dev.matthiesen.common.matthiesen_lib_webhooks.MatthiesenLibWebhooksConstants;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;

public class MatthiesenLibWebhooksFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        MatthiesenLibWebhooksConstants.createInfoLog("Loading for Fabric Mod Loader");
        MatthiesenLibWebhooks.initialize();
    }
}
