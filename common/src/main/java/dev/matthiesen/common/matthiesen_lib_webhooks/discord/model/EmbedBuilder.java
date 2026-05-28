package dev.matthiesen.common.matthiesen_lib_webhooks.discord.model;

import dev.matthiesen.common.matthiesen_lib_webhooks.discord.DiscordColor;

import java.util.ArrayList;
import java.util.List;

public class EmbedBuilder {
    private String title;
    private String description;
    private String url;
    private Integer color;
    private Embed.Author author;
    private List<Embed.EmbedField> fields;
    private Embed.Footer footer;
    private Embed.Image image;
    private Embed.Thumbnail thumbnail;
    private String timestamp;

    public EmbedBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public EmbedBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public EmbedBuilder withUrl(String url) {
        this.url = url;
        return this;
    }

    public EmbedBuilder withColor(Integer color) {
        this.color = color;
        return this;
    }

    public EmbedBuilder withColor(DiscordColor color) {
        this.color = color.getValue();
        return this;
    }

    public EmbedBuilder withAuthor(Embed.Author author) {
        this.author = author;
        return this;
    }

    public EmbedBuilder withFields(List<Embed.EmbedField> fields) {
        this.fields = fields;
        return this;
    }

    public EmbedBuilder addField(String name, String value, boolean inline) {
        if (this.fields == null) {
            this.fields = new ArrayList<>();
        }
        Embed.EmbedField field = new Embed.EmbedField();
        field.setName(name);
        field.setValue(value);
        field.setInline(inline);
        this.fields.add(field);
        return this;
    }

    public EmbedBuilder withAuthor(String name, String url, String iconUrl) {
        Embed.Author builtAuthor = new Embed.Author();
        builtAuthor.setName(name);
        builtAuthor.setUrl(url);
        builtAuthor.setIconUrl(iconUrl);
        this.author = builtAuthor;
        return this;
    }

    public EmbedBuilder withFooter(Embed.Footer footer) {
        this.footer = footer;
        return this;
    }

    public EmbedBuilder withFooter(String text, String iconUrl) {
        Embed.Footer builtFooter = new Embed.Footer();
        builtFooter.setText(text);
        builtFooter.setIconUrl(iconUrl);
        this.footer = builtFooter;
        return this;
    }

    public EmbedBuilder withImage(Embed.Image image) {
        this.image = image;
        return this;
    }

    public EmbedBuilder withImageUrl(String url) {
        Embed.Image builtImage = new Embed.Image();
        builtImage.setUrl(url);
        this.image = builtImage;
        return this;
    }

    public EmbedBuilder withThumbnail(Embed.Thumbnail thumbnail) {
        this.thumbnail = thumbnail;
        return this;
    }

    public EmbedBuilder withThumbnailUrl(String url) {
        Embed.Thumbnail builtThumbnail = new Embed.Thumbnail();
        builtThumbnail.setUrl(url);
        this.thumbnail = builtThumbnail;
        return this;
    }

    public EmbedBuilder withTimestamp(String timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public Embed build() {
        Embed embed = new Embed();
        embed.setTitle(title);
        embed.setDescription(description);
        embed.setUrl(url);
        embed.setColor(color);
        embed.setAuthor(author);
        embed.setFields(fields);
        embed.setFooter(footer);
        embed.setImage(image);
        embed.setThumbnail(thumbnail);
        embed.setTimestamp(timestamp);
        return embed;
    }
}

