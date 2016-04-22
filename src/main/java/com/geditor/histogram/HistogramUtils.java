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
    public static HistogramModel createHistogram(BufferedImage bufferedImage) {
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

    public static HistogramModel stretchHistogram(BufferedImage bufferedImage) {
        return null;
    }

    public static HistogramModel equalizeHistogram(BufferedImage bufferedImage) {
        return null;
    }
}
