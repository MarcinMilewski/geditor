package com.geditor.io.importer.parser.ppm.p6;

import com.geditor.io.importer.parser.FileParser;
import com.geditor.io.importer.parser.exception.ParserException;
import com.geditor.io.importer.parser.ppm.AbstractPpmParser;

import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by marcin on 13.03.16.
 */
public class PpmP6Parser extends AbstractPpmParser implements FileParser{

    private int[] dataArray;
    private int dataIndex = 0;
    private int dataSize = 0;

    private byte[] fileContent;
    private long fileLength  = 0;
    private int fileIndex = 0;

    @Override
    public BufferedImage parse(File file) throws ParserException {
        return null;
    }
//    @Override
//    public BufferedImage parse(File file) throws ParserException {
//        BufferedImage bufferedImage = null;
//        try {
//            ByteSource byteSource = Files.asByteSource(file);
//            fileLength = byteSource.size();
//            fileContent = byteSource.read();
//            checkIfEmpty();
//            parseHeader();
//            allocateArray();
//            bufferedImage = convertToBufferedImage();
//        } catch (IOException e) {
//            throw new ParserException(e);
//        } catch (NoSuchElementException e) {
//            throw new ParserException(e);
//        } catch (ArrayIndexOutOfBoundsException e) {
//            throw new ParserException(e);
//        } catch (NumberFormatException e) {
//            throw new ParserException(e);
//        }
//        return bufferedImage;
//    }
//
//    private void checkIfEmpty() throws ParserException {
//        if (fileLength == 0)
//            throw new ParserException("Empty file");
//    }
//
//    private void parseHeader() throws ParserException {
//        if (nextChar() != 'P') {
//            throw new ParserException("Wrong header");
//        }
//        else {
//            if (nextChar() != '6') {
//                throw new ParserException("Wrong header");
//            }
//        }
//        while(nextCharAfterNewLine() == '#');
//        getHeightAndWidth();
//        getMaxColorValue();
//    }
//
//    private void getHeightAndWidth() throws ParserException {
//        width = convertByteToInt8(getCurrentByte());
//        nextAfterNonWhite();
//        height = convertByteToInt8(getCurrentByte());
//    }
//
//    private void getMaxColorValue() throws ParserException {
//        nextCharAfterNewLine();
//        maxColorValue = convertByteToInt8(getCurrentByte());
//        if (maxColorValue < 0 && maxColorValue > 255) throw new ParserException("Wrong max color value");
//    }
//
//    private void allocateArray() throws ParserException {
//        if (width < 0 || height < 0) throw new ParserException("Wrong size values.");
//        dataSize = width * height * 3;
//        dataArray = new int[dataSize];
//    }
//
//    private void parseBody(List<String> content) throws ParserException, IOException {
//    }
//
//    private void parseBodyRow(byte aByte) throws ParserException {
//            int value =  aByte & 0x000000ff;
//            if (value < 0 || value > maxColorValue) throw new ParserException("Wrong byte value");
//            dataArray[dataIndex++] = value;
//    }
//
//    private BufferedImage convertToBufferedImage() {
//        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
//        image.getRaster().setPixels(0,0,width,height,dataArray);
//        return image;
//    }


}
