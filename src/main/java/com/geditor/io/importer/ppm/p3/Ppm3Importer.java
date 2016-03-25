package com.geditor.io.importer.ppm.p3;

import com.geditor.io.importer.exception.ImportFileException;
import com.geditor.io.importer.parser.FileParser;
import com.geditor.io.importer.parser.ppm.p3.PpmP3Parser;
import com.geditor.io.importer.ppm.AbstractPpmImporter;

import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by marcin on 13.03.16.
 */
public class Ppm3Importer extends AbstractPpmImporter {

    public Ppm3Importer(File file)  {
        super(file);
    }

    @Override
    public BufferedImage importImage() throws ImportFileException {
        return super.importImage();
    }

    @Override
    protected FileParser getParser() {
        return new PpmP3Parser(file);
    }

}
