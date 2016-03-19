package com.geditor.io.importer.ppm;

import com.geditor.io.importer.AbstractFileImporter;
import com.geditor.io.importer.exception.InvalidExtensionException;

import java.io.FileNotFoundException;

/**
 * Created by marcin on 13.03.16.
 */
public abstract class AbstractPpmImporter extends AbstractFileImporter{

    public AbstractPpmImporter(String filePath) throws FileNotFoundException, InvalidExtensionException {
        super(filePath);
    }
}
