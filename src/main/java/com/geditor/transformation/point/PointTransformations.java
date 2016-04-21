package com.geditor.transformation.point;

import org.apache.log4j.Logger;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicIntegerArray;

public class PointTransformations {
    private static final Logger logger = Logger.getLogger(PointTransformations.class);
    public static final int COLOR_SPAN = 256;
    public static final int COLOR_MIN = 0;

    public static BufferedImage ChangeBrighteness(BufferedImage bufferedImage, int value) {
        BufferedImage result = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(), bufferedImage.getType());
        AtomicIntegerArray lookupTable = new AtomicIntegerArray(new int[COLOR_SPAN]);

        if (value >= 0) {
            for (int i = 0; i < COLOR_SPAN; i++) {
                lookupTable.set(i, i + value > 255 ? 255 : i + value);
            }
        } else {
            for (int i = 0; i < COLOR_SPAN; i++) {
                lookupTable.set(i, i + value < COLOR_MIN ? 0 : i + value);
            }
        }


        ExecutorService executor = Executors.newWorkStealingPool();
        for (int i = 0; i < bufferedImage.getHeight(); ++i) {
            for (int j = 0; j < bufferedImage.getWidth(); ++j) {
                int finalJ = j;
                int finalI = i;
                executor.execute(() -> {
                    Color color = new Color(bufferedImage.getRGB(finalJ, finalI));
                    result.setRGB(finalJ, finalI, new Color(lookupTable.get(color.getRed()),
                            lookupTable.get(color.getGreen()), lookupTable.get(color.getBlue())).getRGB());
                });
            }
        }
        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            logger.error(e);
        }
        return result;
    }
}
