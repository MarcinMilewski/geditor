package com.geditor.transformation.point;

import org.apache.log4j.Logger;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class PointTransformations {
    private static final Logger logger = Logger.getLogger(PointTransformations.class);
    public static final int COLOR_SPAN = 256;
    public static final int COLOR_MIN = 0;

    public static BufferedImage changeBrighteness(BufferedImage bufferedImage, int value) {
        BufferedImage result = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(), bufferedImage.getType());
        int[] lookupTable = new int[COLOR_SPAN];

        prepareColorLookupTable(value, lookupTable);


        ExecutorService executor = Executors.newWorkStealingPool();
        for (int i = 0; i < bufferedImage.getHeight(); ++i) {
            int finalI = i;
            executor.execute(() -> {
                for (int j = 0; j < bufferedImage.getWidth(); ++j) {
                    int finalJ = j;
                    Color color = new Color(bufferedImage.getRGB(finalJ, finalI));
                    result.setRGB(finalJ, finalI, new Color(lookupTable[color.getRed()],
                            lookupTable[color.getGreen()], lookupTable[color.getBlue()]).getRGB());
                }
            });

        }
        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            logger.error(e);
        }
        return result;
    }

    public static BufferedImage addRed(BufferedImage bufferedImage, int value) {
        BufferedImage result = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(), bufferedImage.getType());
        int[] lookupTable = new int[COLOR_SPAN];

        prepareColorLookupTable(value, lookupTable);

        ExecutorService executor = Executors.newWorkStealingPool();
        for (int i = 0; i < bufferedImage.getHeight(); ++i) {
            for (int j = 0; j < bufferedImage.getWidth(); ++j) {
                int finalJ = j;
                int finalI = i;
                executor.execute(() -> {
                    Color color = new Color(bufferedImage.getRGB(finalJ, finalI));
                    result.setRGB(finalJ, finalI, new Color(lookupTable[color.getRed()],
                            color.getGreen(), color.getBlue()).getRGB());
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
    public static BufferedImage addGreen(BufferedImage bufferedImage, int value) {
        BufferedImage result = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(), bufferedImage.getType());
        int[] lookupTable = new int[COLOR_SPAN];

        prepareColorLookupTable(value, lookupTable);

        ExecutorService executor = Executors.newWorkStealingPool();
        for (int i = 0; i < bufferedImage.getHeight(); ++i) {
            for (int j = 0; j < bufferedImage.getWidth(); ++j) {
                int finalJ = j;
                int finalI = i;
                executor.execute(() -> {
                    Color color = new Color(bufferedImage.getRGB(finalJ, finalI));
                    result.setRGB(finalJ, finalI, new Color(color.getRed(),
                            lookupTable[color.getGreen()], color.getBlue()).getRGB());
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
    public static BufferedImage addBlue(BufferedImage bufferedImage, int value) {
        BufferedImage result = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(), bufferedImage.getType());
        int[] lookupTable = new int[COLOR_SPAN];

        prepareColorLookupTable(value, lookupTable);

        ExecutorService executor = Executors.newWorkStealingPool();
        for (int i = 0; i < bufferedImage.getHeight(); ++i) {
            for (int j = 0; j < bufferedImage.getWidth(); ++j) {
                int finalJ = j;
                int finalI = i;
                executor.execute(() -> {
                    Color color = new Color(bufferedImage.getRGB(finalJ, finalI));
                    result.setRGB(finalJ, finalI, new Color(lookupTable[color.getRed()],
                            color.getGreen(), color.getBlue()).getRGB());
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

    private static void prepareColorLookupTable(int value, int[] lookupTable) {
        if (value > 0) {
            for (int i = 0; i < COLOR_SPAN; i++) {
                lookupTable[i] = i + value > 255 ? 255 : i + value;
            }
        } else if (value < 0){
            for (int i = 0; i < COLOR_SPAN; i++) {
                lookupTable[i] = i + value < COLOR_MIN ? 0 : i + value;
            }
        } else {
            for (int i = 0; i < COLOR_SPAN; i++) {
                lookupTable[i] = i;
            }
        }
    }
}
