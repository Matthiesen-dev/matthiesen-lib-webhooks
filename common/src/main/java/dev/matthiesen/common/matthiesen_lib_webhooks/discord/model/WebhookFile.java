package dev.matthiesen.common.matthiesen_lib_webhooks.discord.model;

public class WebhookFile {
    private String filename;
    private byte[] data;

    public WebhookFile() {
    }

    public WebhookFile(String filename, byte[] data) {
        this.filename = filename;
        this.data = data;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}

