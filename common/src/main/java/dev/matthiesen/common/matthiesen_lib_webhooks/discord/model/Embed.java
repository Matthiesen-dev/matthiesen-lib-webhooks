package dev.matthiesen.common.matthiesen_lib_webhooks.discord.model;

import java.util.List;

public class Embed {
    private String title;
    private String description;
    private String url;
    private Integer color;
    private Author author;
    private List<EmbedField> fields;
    private Footer footer;
    private Image image;
    private Thumbnail thumbnail;
    private String timestamp;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getColor() {
        return color;
    }

    public void setColor(Integer color) {
        this.color = color;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public List<EmbedField> getFields() {
        return fields;
    }

    public void setFields(List<EmbedField> fields) {
        this.fields = fields;
    }

    public Footer getFooter() {
        return footer;
    }

    public void setFooter(Footer footer) {
        this.footer = footer;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Thumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public static class Author {
        private String name;
        private String url;
        private String icon_url;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getIconUrl() {
            return icon_url;
        }

        public void setIconUrl(String iconUrl) {
            this.icon_url = iconUrl;
        }
    }

    public static class Footer {
        private String text;
        private String icon_url;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getIconUrl() {
            return icon_url;
        }

        public void setIconUrl(String iconUrl) {
            this.icon_url = iconUrl;
        }
    }

    public static class EmbedField {
        private String name;
        private String value;
        private boolean inline;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public boolean isInline() {
            return inline;
        }

        public void setInline(boolean inline) {
            this.inline = inline;
        }
    }

    public static class Image {
        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public static class Thumbnail {
        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}

