package com.geditor.io.importer.parser;

import com.geditor.io.importer.parser.exception.ParserException;

import java.awt.image.BufferedImage;

/**
 * Created by marcin on 13.03.16.
 */
public interface FileParser {
    BufferedImage parse() throws ParserException;
}
