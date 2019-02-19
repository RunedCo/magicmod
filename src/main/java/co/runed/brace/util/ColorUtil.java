package co.runed.brace.util;

import java.awt.Color;

public class ColorUtil {
    public static int convertColorToInt(Color color) {
        return color.getAlpha() << 24 | color.getRed() << 16 | color.getGreen() << 8 | color.getBlue();
    }
}
