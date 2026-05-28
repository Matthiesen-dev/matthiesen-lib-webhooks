package dev.matthiesen.neoforge.matthiesen_lib_webhooks;

import dev.matthiesen.common.matthiesen_lib_webhooks.MatthiesenLibWebhooks;
import dev.matthiesen.common.matthiesen_lib_webhooks.MatthiesenLibWebhooksConstants;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.event.server.ServerStoppingEvent;

@Mod(MatthiesenLibWebhooksConstants.MOD_ID)
public class MatthiesenLibWebhooksNeoForge {
    public MatthiesenLibWebhooksNeoForge() {
        MatthiesenLibWebhooksConstants.createInfoLog("Loading for NeoForge Mod Loader");
        MatthiesenLibWebhooks.initialize();
    }
}
