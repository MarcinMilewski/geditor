package com.geditor.io.importer.ppm.p3;

import com.geditor.io.importer.exception.ImportFileException;
import com.geditor.io.importer.exception.InvalidExtensionException;
import com.geditor.io.importer.parser.FileParser;
import com.geditor.io.importer.parser.ppm.p3.PpmP3Parser;
import com.geditor.io.importer.ppm.AbstractPpmImporter;

import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;

/**
 * Created by marcin on 13.03.16.
 */
public class Ppm3Importer extends AbstractPpmImporter {

    public Ppm3Importer(String filePath) throws FileNotFoundException, InvalidExtensionException {
        super(filePath);
    }

    @Override
    public BufferedImage importFile() throws ImportFileException {
        return super.importFile();
    }

    @Override
    protected FileParser getParser() {
        return new PpmP3Parser();
    }


    @Override
    protected void validateExtension(String extension) throws InvalidExtensionException {
        if (!extension.equalsIgnoreCase("ppmp3")) throw new InvalidExtensionException("invalid extension: " + extension);
    }

}
