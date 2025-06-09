package utils;

import java.awt.*;

public final class Colors {
    // Static color constants
    public static final Color BLUE = new Color(0x8BE9FD);
    public static final Color GREEN = new Color(0x50FA7B);
    public static final Color GREY = new Color(0x363848);
    public static final Color ORANGE = new Color(0xFFB86C);
    public static final Color PINK = new Color(0xFF79C6);
    public static final Color PURPLE = new Color(0xBD93F9);
    public static final Color RED = new Color(0xFF5555);
    public static final Color YELLOW = new Color(0xF1FA8C);
    public static final Color BACKGROUND = new Color(0x393B45);
    public static final Color PANELS_BACKGROUND = new Color(0x21222C);

    // Private constructor to prevent instantiation
    private Colors() {
        throw new AssertionError("Utility class should not be instantiated");
    }
}