package com.geditor.util;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class ColorConverterTest {
    @Test
    public void rgbBlackToCmyk() throws Exception {
        float[] cmyk = ColorConverter.rgbToCmyk(0, 0, 0);
        assertArrayEquals(cmyk, new float[] {0,0,0,1}, 0.001f);
    }

    @Test
    public void rgbRed1ToCmyk() throws Exception {
        float[] cmyk = ColorConverter.rgbToCmyk(1, 0, 0);
        assertArrayEquals(cmyk, new float[] {0,1,1,0.996f}, 0.001f);
    }

    @Test
    public void rgbRed2ToCmyk() throws Exception {
        float[] cmyk = ColorConverter.rgbToCmyk(255, 0, 0);
        assertArrayEquals(cmyk, new float[] {0,1,1,0}, 0.001f);
    }

    @Test
    public void rgbGreen1ToCmyk() throws Exception {
        float[] cmyk = ColorConverter.rgbToCmyk(0, 7, 0);
        assertArrayEquals(cmyk, new float[] {1,0,1,0.973f}, 0.001f);
    }

    @Test
    public void rgbGreen2ToCmyk() throws Exception {
        float[] cmyk = ColorConverter.rgbToCmyk(0, 255, 0);
        assertArrayEquals(cmyk, new float[] {1,0,1,0}, 0.001f);
    }

    @Test
    public void rgbBlue1ToCmyk() throws Exception {
        float[] cmyk = ColorConverter.rgbToCmyk(0, 0, 255);
        assertArrayEquals(cmyk, new float[] {1,1,0,0}, 0.001f);
    }

    @Test
    public void rgbBlue2ToCmyk() throws Exception {
        float[] cmyk = ColorConverter.rgbToCmyk(0, 0, 9);
        assertArrayEquals(cmyk, new float[] {1,1,0,0.965f}, 0.001f);
    }
    @Test
    public void rgbWhiteToCmyk() throws Exception {
        float[] cmyk = ColorConverter.rgbToCmyk(255, 255, 255);
        assertArrayEquals(cmyk, new float[] {0,0,0,0}, 0.001f);
    }


    @Test
    public void cmykToRgb() throws Exception {

    }

}