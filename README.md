# matthiesen-lib-webhooks

<div>
  <img src="https://mods.matthiesen.dev/badges/matthiesenLibAPI.svg" alt="Matthiesen Lib API">
</div>

Cross-loader Discord webhook helper for server lifecycle notifications.

## Requirements
- [Matthiesen Lib API](https://modrinth.com/mod/matthiesen-lib-api)

## Docs

Documentation for this mod can be found at [mods.matthiesen.dev](https://mods.matthiesen.dev/matthiesen-lib-webhooks/)

## Discord Webhook Setup

Set `MATTHIESEN_LIB_WEBHOOK_URL` in the server environment before startup.

Example:

```bash
export MATTHIESEN_LIB_WEBHOOK_URL="https://discord.com/api/webhooks/..."
```

When configured, the mod posts `Server Startup` and `Server Shutdown` embeds.

The webhook client implementation is fully Java-based and does not use Lombok.

## Library Usage

```java
MatthiesenLibWebhooks.Webhooks webhooks = new MatthiesenLibWebhooks.Webhooks("https://discord.com/api/webhooks/...");

webhooks.sendMessage(message -> message
        .withUsername("My Mod")
        .withContent("Simple webhook message"));

webhooks.sendMessage(message -> message
        .withUsername("My Mod")
        .addEmbed(embed -> embed
                .withTitle("Status Update")
                .withDescription("Everything is online")
                .addField("Players", "12", true)
                .addField("TPS", "20.0", true)
        ));
```

## Version Compatibility

| Minecraft Version | Mod Version |
|-------------------|-------------|
| 1.21.1            | 1.x.x       |

## License

MIT - see `LICENSE`.
