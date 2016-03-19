package com.geditor.io.importer.parser.ppm.p6;

import com.geditor.io.importer.parser.FileParser;
import com.geditor.io.importer.parser.exception.ParserException;
import com.geditor.io.importer.parser.ppm.AbstractPpmParser;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;

/**
 * Created by marcin on 13.03.16.
 */
public class PpmP6Parser extends AbstractPpmParser implements FileParser{

    @Override
    public BufferedImage parse(FileInputStream fileInputStream) throws ParserException {
        return null;
    }
}
