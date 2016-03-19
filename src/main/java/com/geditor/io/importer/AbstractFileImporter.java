package com.geditor.io.importer;

import com.geditor.io.importer.exception.ImportFileException;
import com.geditor.io.importer.parser.FileParser;
import com.geditor.io.importer.parser.exception.ParserException;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by marcin on 13.03.16.
 */
public abstract class AbstractFileImporter implements FileImporter{
    protected final File file;
    protected final FileParser parser;
    public AbstractFileImporter(File file) {
        this.file = file;
        parser = getParser();
    }

    @Override
    public BufferedImage importImage() throws ImportFileException {
        BufferedImage bufferedImage;
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            bufferedImage = parser.parse(fileInputStream);
        } catch (FileNotFoundException e) {
            throw new ImportFileException(e);
        } catch (ParserException e) {
            throw new ImportFileException(e);
        }
        return bufferedImage;
    }

    protected abstract FileParser getParser();

}
