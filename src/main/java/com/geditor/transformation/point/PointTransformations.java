package com.geditor.transformation.point;

import org.apache.log4j.Logger;

import java.awt.image.BufferedImage;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class PointTransformations {
    private static final Logger logger = Logger.getLogger(PointTransformations.class);
    public static final int COLOR_SPAN = 256;
    public static BufferedImage brightening(BufferedImage bufferedImage, int value) {
        BufferedImage result = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(), bufferedImage.getType());
        int[] lookupTable = new int[COLOR_SPAN];

        for (int i = 0; i < COLOR_SPAN; i++) {
            lookupTable[i] = i + value > 255 ? 255 : i + value;
        }

        ExecutorService executor = Executors.newWorkStealingPool();
        for (int i = 0; i < bufferedImage.getHeight(); ++i) {
            for (int j = 0; j < bufferedImage.getWidth(); j++) {
                int finalJ = j;
                int finalI = i;
                executor.execute(() -> {
                    result.setRGB(finalJ, finalI, lookupTable[bufferedImage.getRGB(finalJ, finalI)]);
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
