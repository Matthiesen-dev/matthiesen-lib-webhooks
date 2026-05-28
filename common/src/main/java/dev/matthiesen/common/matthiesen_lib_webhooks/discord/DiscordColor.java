package dev.matthiesen.common.matthiesen_lib_webhooks.discord;

public enum DiscordColor {
    RED(15158332),
    GREEN(3066993),
    BLUE(3447003),
    PURPLE(10181046),
    ORANGE(15105570),
    GOLD(15844367),
    GREY(9807270),
    DARK_RED(10038562),
    DARK_GREEN(5763719),
    DARK_BLUE(5793266),
    BLACK(2303786),
    WHITE(16777215);

    private final int value;

    DiscordColor(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

