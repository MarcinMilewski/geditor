package com.geditor.transformation.binarization;

import com.geditor.transformation.histogram.HistogramUtils;
import lombok.extern.log4j.Log4j;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Log4j
public class BinarizationUtils {
    public static BufferedImage manual(BufferedImage bufferedImage, int threshold) {

        int lut[] = createManualBinaryThresholdLUT(threshold);
        return createImage(bufferedImage, lut);
    }

    private static BufferedImage createImage(BufferedImage bufferedImage, int[] lut) {
        BufferedImage result = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(), bufferedImage.getType());

        ExecutorService executor = Executors.newWorkStealingPool();

        for (int i = 0; i < bufferedImage.getHeight(); ++i) {
            int finalI = i;
            executor.execute(() -> {
                for (int j = 0; j < bufferedImage.getWidth(); ++j) {
                    int finalJ = j;
                    Color color = new Color(bufferedImage.getRGB(finalJ, finalI));
                    result.setRGB(finalJ, finalI, new Color(
                            lut[color.getRed()],
                            lut[color.getGreen()],
                            lut[color.getBlue()]).getRGB());
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

    private static int[] createManualBinaryThresholdLUT(int threshold) {
        int[] lut = new int[256];
        for (int i = 0; i < lut.length; i++) {
            if (i >= threshold) {
                lut[i] = 255;
            }
        }
        return lut;
    }

    public static BufferedImage percentBlackSelection(BufferedImage bufferedImage, int percent) {
        BufferedImage result = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(), bufferedImage.getType());
        int[] grayChannel = HistogramUtils.createHistogram(bufferedImage).getRedChannel(); // red is the same as blue and green in gray picture

        int lut[] = createBlackPercentLUT(percent, bufferedImage.getHeight() * bufferedImage.getWidth(), grayChannel);
        return createImage(bufferedImage, lut);
    }

    private static int[] createBlackPercentLUT(int percent, int size, int[] grayChannel) {
        if (percent < 0 || percent > 100) throw new IllegalArgumentException("Wrong percent value.");
        int[] lut = new int[256];
        int sum = 0;
        int threshold = (int) (percent * size * 0.01);
        for (int i = 0; i < lut.length; i++) {
            sum += grayChannel[i];
            if (sum >= threshold) {
                lut[i] = 255;
            }
        }
        return lut;
    }



}
