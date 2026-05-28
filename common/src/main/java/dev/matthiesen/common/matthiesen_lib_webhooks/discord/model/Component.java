package dev.matthiesen.common.matthiesen_lib_webhooks.discord.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Component {
    public static final int ACTION_ROW = 1;
    public static final int BUTTON = 2;

    public static final int PRIMARY = 1;
    public static final int SECONDARY = 2;
    public static final int SUCCESS = 3;
    public static final int DANGER = 4;
    public static final int LINK = 5;

    private int type;
    private Integer style;
    private String label;
    private Emoji emoji;
    @SerializedName("custom_id")
    private String customId;
    private String url;
    private Boolean disabled;
    private List<Component> components;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Integer getStyle() {
        return style;
    }

    public void setStyle(Integer style) {
        this.style = style;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Emoji getEmoji() {
        return emoji;
    }

    public void setEmoji(Emoji emoji) {
        this.emoji = emoji;
    }

    public String getCustomId() {
        return customId;
    }

    public void setCustomId(String customId) {
        this.customId = customId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    public List<Component> getComponents() {
        return components;
    }

    public void setComponents(List<Component> components) {
        this.components = components;
    }

    public static class Emoji {
        private String id;
        private String name;
        private boolean animated;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isAnimated() {
            return animated;
        }

        public void setAnimated(boolean animated) {
            this.animated = animated;
        }
    }
}

