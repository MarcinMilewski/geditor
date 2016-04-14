package com.geditor.util;

public class ColorConverter {
    private static final int CMYK_MAX_VALUE = 1;
    private static final int RGB_MAX_VALUE = 255;

    public static float[] rgbToCmyk(float red, float green, float blue) {
        red /= RGB_MAX_VALUE;
        green /= RGB_MAX_VALUE;
        blue /= RGB_MAX_VALUE;
        float cyan, magenta, yellow;
        float black = Math.min(Math.min(CMYK_MAX_VALUE - red, CMYK_MAX_VALUE - green), CMYK_MAX_VALUE - blue);
        if (black == CMYK_MAX_VALUE) {
            cyan = 0;
            magenta = 0;
            yellow = 0;
        } else {
            cyan = (CMYK_MAX_VALUE - red - black) / (CMYK_MAX_VALUE - black);
            magenta = (CMYK_MAX_VALUE - green - black) / (CMYK_MAX_VALUE - black);
            yellow = (CMYK_MAX_VALUE - blue - black) / (CMYK_MAX_VALUE - black);
        }
        return new float[]{cyan, magenta, yellow, black};
    }

    public static int[] cmykToRgb(int cyan, int magenta, int yellow, int black) {
        int red = CMYK_MAX_VALUE - Math.min(CMYK_MAX_VALUE, cyan *(1-black) + black);
        int green = CMYK_MAX_VALUE - Math.min(CMYK_MAX_VALUE, magenta *(1-black) + black);
        int blue = CMYK_MAX_VALUE - Math.min(CMYK_MAX_VALUE, yellow *(1-black) + black);

        return new int[] {red, green, blue};
    }
}
