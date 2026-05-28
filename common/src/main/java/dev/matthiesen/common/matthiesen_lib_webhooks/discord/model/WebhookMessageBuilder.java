package dev.matthiesen.common.matthiesen_lib_webhooks.discord.model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class WebhookMessageBuilder {
    private String content;
    private String username;
    private String avatarUrl;
    private boolean tts;
    private List<Embed> embeds;
    private List<Component> components;
    private List<WebhookFile> files;

    public WebhookMessageBuilder withContent(String content) {
        this.content = content;
        return this;
    }

    public WebhookMessageBuilder withUsername(String username) {
        this.username = username;
        return this;
    }

    public WebhookMessageBuilder withAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
        return this;
    }

    public WebhookMessageBuilder withTts(boolean tts) {
        this.tts = tts;
        return this;
    }

    public WebhookMessageBuilder withEmbeds(List<Embed> embeds) {
        this.embeds = embeds;
        return this;
    }

    public WebhookMessageBuilder addEmbed(Embed embed) {
        if (this.embeds == null) {
            this.embeds = new ArrayList<>();
        }
        this.embeds.add(embed);
        return this;
    }

    public WebhookMessageBuilder addEmbed(Consumer<EmbedBuilder> embedBuilderCustomizer) {
        EmbedBuilder builder = new EmbedBuilder();
        embedBuilderCustomizer.accept(builder);
        return addEmbed(builder.build());
    }

    public WebhookMessageBuilder withComponents(List<Component> components) {
        this.components = components;
        return this;
    }

    public WebhookMessageBuilder addComponent(Component component) {
        if (this.components == null) {
            this.components = new ArrayList<>();
        }
        this.components.add(component);
        return this;
    }

    public WebhookMessageBuilder withFiles(List<WebhookFile> files) {
        this.files = files;
        return this;
    }

    public WebhookMessageBuilder addFile(WebhookFile file) {
        if (this.files == null) {
            this.files = new ArrayList<>();
        }
        this.files.add(file);
        return this;
    }

    public WebhookMessage build() {
        WebhookMessage message = new WebhookMessage();
        message.setContent(content);
        message.setUsername(username);
        message.setAvatarUrl(avatarUrl);
        message.setTts(tts);
        message.setEmbeds(embeds);
        message.setComponents(components);
        message.setFiles(files);
        return message;
    }
}

