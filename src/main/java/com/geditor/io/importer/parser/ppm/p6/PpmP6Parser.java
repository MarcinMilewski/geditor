package com.geditor.io.importer.parser.ppm.p6;

import com.geditor.io.importer.parser.FileParser;
import com.geditor.io.importer.parser.exception.ParserException;
import com.geditor.io.importer.parser.ppm.AbstractPpmParser;
import com.google.common.base.Charsets;
import com.google.common.io.Files;
import org.apache.commons.collections4.CollectionUtils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by marcin on 13.03.16.
 */
public class PpmP6Parser extends AbstractPpmParser implements FileParser{

    private int width = 0;
    private int height = 0;
    private int maxColorValue = 0;
    private int[] dataArray;
    private int dataIndex = 0;
    private int dataSize = 0;

    @Override
    public BufferedImage parse(File file) throws ParserException {
        BufferedImage bufferedImage = null;
        try {
            List<String> content = Files.readLines(file, Charsets.US_ASCII);
            Files.toByteArray(file);
           checkIfEmpty(content);
            Iterator<String> iterator = content.iterator();
            parseHeader(iterator);
            allocateArray();
            parseBody(content);
            bufferedImage = convertToBufferedImage();
        } catch (IOException e) {
            throw new ParserException(e);
        } catch (NoSuchElementException e) {
            throw new ParserException(e);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ParserException(e);
        } catch (NumberFormatException e) {
            throw new ParserException(e);
        }
        return bufferedImage;
    }

    private void checkIfEmpty(List<String> content) throws ParserException {
        if (CollectionUtils.isEmpty(content)) {
            throw new ParserException("Empty file");
        }
    }

    private void parseHeader(Iterator<String> iterator) throws ParserException {
        if (iterator.next().equalsIgnoreCase("P6")) {
            iterator.remove();
            String row;
            while ((row = iterator.next()).startsWith("#")) {
                iterator.remove();
            };
            getHeightAndWidth(row);
            iterator.remove();
            getMaxColorValue(iterator.next());
            iterator.remove();
        } else {
            throw new ParserException("Wrong header");
        }
    }

    private void getHeightAndWidth(String row) throws ParserException {
        String[] parts = row.split("\\s+");
        if (parts.length != 2) {
            throw new ParserException("Wrong header - width and height");
        } else {
            height = Integer.parseInt(parts[1]);
            width = Integer.parseInt(parts[0]);
        }
    }

    private void getMaxColorValue(String next) throws ParserException {
        maxColorValue = Integer.parseInt(next);
        if (maxColorValue < 0 && maxColorValue > 255) throw new ParserException("Wrong max color value");
    }

    private void allocateArray() throws ParserException {
        if (width < 0 || height < 0) throw new ParserException("Wrong size values.");
        dataSize = width * height * 3;
        dataArray = new int[dataSize];
    }

    private void parseBody(List<String> content) throws ParserException, IOException {
        for (String s : content) {
            byte[] bArray = s.getBytes(StandardCharsets.US_ASCII);
            for (byte b : bArray) {
                parseBodyRow(b);
            }
        }
//        for (String s : content) {
//            for (int i = 0; i < s.length();i++) {
//                parseBodyRow((byte) s.charAt(i));
//            }
//        }

//        ByteArrayOutputStream bos = new ByteArrayOutputStream();
//        ObjectOutputStream oos = new ObjectOutputStream(bos);
//        for (String s : content) {
//            oos.writeBytes(s);
//        }
//        byte[] bytes = bos.toByteArray();
//        for (byte aByte : bytes) {
//          parseBodyRow(aByte);
//        }
    }

    private void parseBodyRow(byte aByte) throws ParserException {
            int value =  aByte & 0x000000ff;
            if (value < 0 || value > maxColorValue) throw new ParserException("Wrong byte value");
            dataArray[dataIndex++] = value;
    }

    private BufferedImage convertToBufferedImage() {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        image.getRaster().setPixels(0,0,width,height,dataArray);
        return image;
    }

}
