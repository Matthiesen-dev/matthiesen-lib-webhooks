# Matthiesen Core Webhooks

<div>
  <img src="https://mods.matthiesen.dev/badges/matthiesenCore.svg" alt="Matthiesen Core">
</div>

This is a server-side library mod for Matthiesen Core that provides Discord webhook functionality. It allows other mods to send messages to Discord channels via the Matthiesen Core webhook service.

```java

import dev.matthiesen.matthiesen_core.common.api.discord.WebhookNotifierInstance;
import dev.matthiesen.matthiesen_core.common.core.MatthiesenCoreCommon;

public class ExampleWebHook {
    private WebhookNotifierInstance webhookNotifierInstance;
    
    public ExampleWebHook() {
        var webhookNotifierService = MatthiesenCoreCommon.INSTANCE.getWebhookService();
        if (!webhookNotifierService.isAvailable()) {
            throw new IllegalStateException("Webhook service is not available");
        }
        webhookNotifierInstance = webhookNotifierService.makeInstance("https://discord.com/api/webhooks/your-webhook-url");
    }
    
    public void sendMessage(String message) {
        webhookNotifierInstance.sendMessage(builder -> builder.withContent(message));
    }
}

```

## Requirements
- [Matthiesen Core](https://modrinth.com/mod/matthiesen-core/)

## Version Compatibility

| Minecraft Version | Mod Version |
|-------------------|-------------|
| 1.21.1            | 1.x.x       |

## License

MIT - see `LICENSE`.
