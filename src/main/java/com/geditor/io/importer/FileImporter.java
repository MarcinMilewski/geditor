package com.geditor.io.importer;

import com.geditor.io.importer.exception.ImportFileException;

import java.awt.image.BufferedImage;

/**
 * Created by marcin on 13.03.16.
 */
public interface FileImporter {
    BufferedImage importFile() throws ImportFileException;
}
