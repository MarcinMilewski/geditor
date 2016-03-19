package com.geditor.io.importer.ppm;

import com.geditor.io.importer.AbstractFileImporter;

import java.io.File;

/**
 * Created by marcin on 13.03.16.
 */
public abstract class AbstractPpmImporter extends AbstractFileImporter{

    public AbstractPpmImporter(File file)  {
        super(file);
    }
}
