package com.geditor.transformation.filtration;

import com.geditor.commons.MaskMatrix;
import lombok.extern.log4j.Log4j;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Log4j
public class FilterUtils {

    public static BufferedImage filter(BufferedImage image, float[][] mask) {
        checkMaskLengthShouldBeOdd(mask);
        BufferedImage result = createImageBackup(image);

        List<Float> maskMatrix = new MaskMatrix(mask).getMaskList();
        final int range = mask.length / 2;
        final int maskSize = mask.length * mask.length;
        final int maskSum = getMaskSum(mask);

//        log.debug("Filter with mask: " + float2dArrayToString(mask));
        for (int i = range; i < image.getHeight() - range; ++i) {
            for (int j = range; j < image.getWidth() - range; ++j) {
                List<Color> colors = getColorsFromImage(image, j, i, maskSize, range);
                Iterator<Float> maskIterator = maskMatrix.iterator();
                final int[] redSum = {0};
                final int[] greenSum = {0};
                final int[] blueSum = {0};
                colors.forEach(color -> {
                    Float scale = maskIterator.next();
                    redSum[0] += (color.getRed() * scale);
                    greenSum[0] += (color.getGreen() * scale);
                    blueSum[0] += (color.getBlue() * scale);
                });
                if (redSum[0] < 0) redSum[0] = 0;
                if (redSum[0] > 255) redSum[0] = 255;
                if (greenSum[0] < 0) greenSum[0] = 0;
                if (greenSum[0] > 255) greenSum[0] = 255;
                if (blueSum[0] < 0) blueSum[0] = 0;
                if (blueSum[0] > 255) blueSum[0] = 255;

                if (maskSum > 0) {
                    result.setRGB(j, i, new Color(redSum[0] / maskSum, greenSum[0] / maskSum, blueSum[0] / maskSum).getRGB());
                } else {
                    result.setRGB(j, i, new Color(redSum[0], greenSum[0], blueSum[0]).getRGB());
                }

            }
        }
        return result;
    }

    private static int getMaskSum(float[][] mask) {
        float sum = 0;
        for (float[] floats : mask) {
            for (float aFloat : floats) {
                sum += aFloat;
            }
        }
        return Math.round(sum);
    }

    private static List<Color> getColorsFromImage(BufferedImage image, final int row, final int col, final int maskSize, final int range) {
        List<Color> colors = new ArrayList<>();
        for (int i = row - range; i <= row + range; ++i) {
            for (int j = col - range; j <= col + range; ++j) {
                colors.add(new Color(image.getRGB(i, j)));
            }
        }
        return colors;
    }

    private static void checkMaskLengthShouldBeOdd(float[][] mask) {
        final int length = mask.length;
        if (length % 2 != 1 && length >= 3) {
            throw new IllegalArgumentException("Invalid mask");
        }
        for (int i = 0; i < length; i++) {
            if (mask[i].length != length) throw new IllegalArgumentException("Invalid mask - should be NxN");
        }
    }

    private static BufferedImage createImageBackup(BufferedImage image) {
        ColorModel cm = image.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = image.copyData(null);
        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
    }

    public static float[][] getSmoothMask() {
        return new float[][]{
                {0.111f, 0.111f, 0.111f},
                {0.111f, 0.111f, 0.111f},
                {0.111f, 0.111f, 0.111f}
        };
    }

    public static float[][] getSobelHorizontalMask() {
        return new float[][]{
                {1f, 2f, 1f},
                {0f, 0f, 0f},
                {-1f, -2f, -1f}
        };
    }

    public static float[][] getSobelVerticalMask() {
        return new float[][]{
                {1f, 0f, -1f},
                {2f, 0f, -2f},
                {1f, 0f, -1f}
        };
    }

    public static float[][] getHighPass1FilterMask() {
        return new float[][] {
                {0, -0.25f, 0},
                {-0.25f, 2, -0.25f},
                {0, -0.25f, 0}
        };
    }

    public static float[][] getHighPass2FilterMask() {
        return new float[][] {
                {-1, -1, -1},
                {-1, 9, -1},
                {-1, -1, -1}
        };
    }

    private static String float2dArrayToString(float[][] tab) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < tab.length; i++) {
            for (int i1 = 0; i1 < tab[i].length; i1++) {
                stringBuilder.append(tab[i][i1] + ", ");
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    public static BufferedImage medianFilter(BufferedImage image, int matrixSize) {
        if (matrixSize % 2 != 1) throw new IllegalArgumentException("Matrix size should be odd");
        BufferedImage result = createImageBackup(image);

        final int range = matrixSize / 2;
        final int maskSize = matrixSize * matrixSize;

//        log.debug("Median filtering, matrix size: " + matrixSize);
        for (int i = range; i < image.getHeight() - range; ++i) {
            for (int j = range; j < image.getWidth() - range; ++j) {
                List<Color> colors = getColorsFromImage(image, j, i, maskSize, range);
                List<Integer> redColors = colors.stream().map(e -> e.getRed()).sorted().collect(Collectors.toList());
                List<Integer> greenColors = colors.stream().map(e -> e.getGreen()).sorted().collect(Collectors.toList());
                List<Integer> blueColors = colors.stream().map(e -> e.getBlue()).sorted().collect(Collectors.toList());

                final int redMedian = getMedian(redColors);
                final int greenMedian = getMedian(greenColors);
                final int blueMedian = getMedian(blueColors);
                result.setRGB(j, i, new Color(redMedian, greenMedian, blueMedian).getRGB());
            }
        }
        return result;
    }

    private static int getMedian(List<Integer> list) {
        final int size = list.size();
        final int middle = size / 2;
        if (size % 2 == 1) {
            return list.get(middle);
        } else {
            return Math.round((list.get(middle) + list.get(middle - 1)) / 2);
        }
    }
}
