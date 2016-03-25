package com.geditor.io.importer.parser.ppm;

import com.geditor.io.importer.parser.FileParser;
import lombok.RequiredArgsConstructor;

import java.io.File;

/**
 * Created by marcin on 13.03.16.
 */
@RequiredArgsConstructor
public abstract class AbstractPpmParser implements FileParser {
    final protected File file;
}
