package dev.matthiesen.common.matthiesen_lib_webhooks.discord.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WebhookMessage {
    private String content;
    private String username;
    @SerializedName("avatar_url")
    private String avatarUrl;
    private boolean tts;
    private List<Embed> embeds;
    private List<Component> components;
    private transient List<WebhookFile> files;

    public WebhookMessage() {
    }

    public WebhookMessage(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public boolean isTts() {
        return tts;
    }

    public void setTts(boolean tts) {
        this.tts = tts;
    }

    public List<Embed> getEmbeds() {
        return embeds;
    }

    public void setEmbeds(List<Embed> embeds) {
        this.embeds = embeds;
    }

    public List<Component> getComponents() {
        return components;
    }

    public void setComponents(List<Component> components) {
        this.components = components;
    }

    public List<WebhookFile> getFiles() {
        return files;
    }

    public void setFiles(List<WebhookFile> files) {
        this.files = files;
    }
}

