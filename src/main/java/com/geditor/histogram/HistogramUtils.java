package com.geditor.histogram;

import lombok.extern.log4j.Log4j;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.lang.reflect.Field;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicIntegerArray;

@Log4j
public class HistogramUtils {
    public static HistogramModel createHistogramParalell(BufferedImage bufferedImage) {
        AtomicIntegerArray redChannel = new AtomicIntegerArray(new int[256]);
        AtomicIntegerArray greenChannel = new AtomicIntegerArray(new int[256]);
        AtomicIntegerArray blueChannel = new AtomicIntegerArray(new int[256]);

        ExecutorService executor = Executors.newWorkStealingPool();
        for (int i = 0; i < bufferedImage.getHeight(); ++i) {
            int finalI = i;
            executor.execute(() -> {
                for (int j = 0; j < bufferedImage.getWidth(); ++j) {
                    int finalJ = j;
                    Color color = new Color(bufferedImage.getRGB(finalJ, finalI));
                    redChannel.incrementAndGet(color.getRed());
                    greenChannel.incrementAndGet(color.getGreen());
                    blueChannel.incrementAndGet(color.getBlue());
                }
            });
        }
        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            log.error(e);
        }

        Field redArrayField = null;
        Field greenArrayField = null;
        Field blueArrayField = null;
        HistogramModel histogramModel = null;
        try {
            redArrayField = redChannel.getClass().getDeclaredField("array");
            greenArrayField = greenChannel.getClass().getDeclaredField("array");
            blueArrayField = blueChannel.getClass().getDeclaredField("array");

            redArrayField.setAccessible(true);
            greenArrayField.setAccessible(true);
            blueArrayField.setAccessible(true);
            int[] redArray = (int[]) redArrayField.get(redChannel);
            int[] greenArray = (int[]) greenArrayField.get(greenChannel);
            int[] blueArray = (int[]) blueArrayField.get(blueChannel);
            histogramModel = new HistogramModel(redArray, greenArray, blueArray);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return histogramModel;
    }

    public static HistogramModel createHistogram(BufferedImage bufferedImage) {
        int[] redChannel = new int[256];
        int[] greenChannel = new int[256];
        int[] blueChannel = new int[256];

        for (int i = 0; i < bufferedImage.getHeight(); ++i) {
            for (int j = 0; j < bufferedImage.getWidth(); ++j) {
                Color color = new Color(bufferedImage.getRGB(j, i));
                ++redChannel[color.getRed()];
                ++greenChannel[color.getGreen()];
                ++blueChannel[color.getBlue()];
            }
        }

        return new HistogramModel(redChannel, greenChannel, blueChannel);
    }

    public static BufferedImage stretchHistogram(HistogramModel histogramModel, BufferedImage bufferedImage) {
        int threshold = (int) ((bufferedImage.getWidth() * bufferedImage.getHeight() / 256) * 0.01);
        if (threshold == 0) {
            threshold = 1;
        }
        int minRed = findMinIndex(histogramModel.getRedChannel(), threshold);
        int maxRed = findMaxIndex(histogramModel.getRedChannel(), threshold);
        int minGreen = findMinIndex(histogramModel.getRedChannel(), threshold);
        int maxGreen = findMaxIndex(histogramModel.getGreenChannel(), threshold);
        int minBlue = findMinIndex(histogramModel.getBlueChannel(), threshold);
        int maxBlue = findMaxIndex(histogramModel.getBlueChannel(), threshold);

        int[] redLoT = createStretchingLoT(minRed, maxRed);
        int[] greenLoT = createStretchingLoT(minGreen, maxGreen);
        int[] blueLoT = createStretchingLoT(minBlue, maxBlue);

        return stretchHistogramInternal(redLoT, greenLoT, blueLoT, bufferedImage);
    }

    private static int findMinIndex(int[] tab, int threshold) {
        for (int i = 0; i < tab.length; i++) {
            if (tab[i] >= threshold) {
                return i;
            }
        }
        return 0;
    }

    private static int findMaxIndex(int[] tab, int threshold) {
        for (int i = tab.length - 1; i >= 0; i--) {
            if (tab[i] >= threshold) {
                return i;
            }
        }
        return 255;
    }

    private static BufferedImage stretchHistogramInternal(int[] redLoT, int[] greenLoT, int[] blueLoT, BufferedImage bufferedImage) {
        BufferedImage result = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(), bufferedImage.getType());

        ExecutorService executor = Executors.newWorkStealingPool();
        for (int i = 0; i < bufferedImage.getHeight(); ++i) {
            int finalI = i;
            executor.execute(() -> {
                for (int j = 0; j < bufferedImage.getWidth(); ++j) {
                    int finalJ = j;
                    Color color = new Color(bufferedImage.getRGB(finalJ, finalI));

                    result.setRGB(finalJ, finalI, new Color(
                            redLoT[color.getRed()],
                            greenLoT[color.getGreen()],
                            blueLoT[color.getBlue()]).getRGB());
                }
            });
        }
        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            log.error(e);
        }
        return result;
    }

    private static int[] createStretchingLoT(int min, int max) {
        int[] result = new int[256];
        for (int i = 0; i < result.length; i++) {
            result[i] = (int) (((double) (i - min) / (double) (max - min)) * 255);
            if (result[i] < 0) {
                result[i] = 0;
            }
            if (result[i] > 255) {
                result[i] = 255;
            }
        }
        return result;
    }

    public static BufferedImage equalizeHistogram(BufferedImage bufferedImage) {
        return null;
    }
}
