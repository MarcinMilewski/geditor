package com.geditor.io.importer.parser;

import java.awt.image.BufferedImage;
import java.util.List;

/**
 * Created by marcin on 13.03.16.
 */
public interface FileParser {
    BufferedImage parse(List<String> lines);
}
