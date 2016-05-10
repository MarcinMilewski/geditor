package com.geditor.transformation.binarization;

import com.geditor.transformation.histogram.HistogramModel;
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

    public static BufferedImage meanIterativeSelection(BufferedImage bufferedImage) {
        int[] grayChannel = HistogramUtils.createHistogram(bufferedImage).getRedChannel();
        final int histogramLength = grayChannel.length;
        int estimatedThreshold = computeHistogramMean(grayChannel);
        int leftPartMean = 0;
        int rightPartMean = 0;
        int previousChange = 0;
        int currentChange = 1;
        do {
            previousChange = currentChange;
            leftPartMean = computeHistogramMean(0, estimatedThreshold, grayChannel);
            rightPartMean = computeHistogramMean(estimatedThreshold, histogramLength, grayChannel);
            estimatedThreshold = (leftPartMean + rightPartMean) / 2;
            currentChange = rightPartMean - leftPartMean;
        } while (previousChange != currentChange);

        log.debug("Iterative selection threshold: " + estimatedThreshold);
        int lut[] = createManualBinaryThresholdLUT(estimatedThreshold);
        return createImage(bufferedImage, lut);
    }

    private static long computeHistogramSize(int from, int to, int[] histogramChannel) {
        long result = 0;
        for (int i = from; i < to; i++) {
            result += histogramChannel[i];
        }
        return result;
    }

    private static long computeWageHistogramSum(int from, int end, int[] histogramChannel) {
        if (from > end || histogramChannel.length < end) throw new IllegalArgumentException();
        long result = 0;
        for (int i = from; i < end; i++) {
            result += (i * histogramChannel[i]);
        }
        return result;
    }

    private static int computeHistogramMean(int from, int end, int[] histogramChannel) {
        long sum = computeWageHistogramSum(from, end, histogramChannel);
        long totalSize = computeHistogramSize(from, end, histogramChannel);
        return (int) (sum / (totalSize));
    }

    private static int computeHistogramMean(int[] histogramChannel) {
        long sum = computeWageHistogramSum(0, histogramChannel.length, histogramChannel);
        long totalSize = computeHistogramSize(0, histogramChannel.length, histogramChannel);
        return (int) (sum / totalSize);
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
