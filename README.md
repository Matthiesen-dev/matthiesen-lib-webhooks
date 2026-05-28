# Matthiesen Lib Webhooks

A Server-side library mod for Matthiesen Lib projects that want to be able to send discord webhooks easily!

## Docs

Documentation for this mod can be found at [mods.matthiesen.dev](https://mods.matthiesen.dev/matthiesen-lib-webhooks/)

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
