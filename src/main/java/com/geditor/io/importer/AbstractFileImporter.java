package com.geditor.io.importer;

import com.geditor.io.importer.exception.ImportFileException;
import com.geditor.io.importer.exception.InvalidExtensionException;
import com.geditor.io.importer.parser.FileParser;
import lombok.NonNull;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by marcin on 13.03.16.
 */
public abstract class AbstractFileImporter implements FileImporter{
    private final String filePath;
    protected final FileParser parser;
    public AbstractFileImporter(@NonNull String filePath) throws FileNotFoundException, InvalidExtensionException {
        this.filePath = filePath;
        parser = getParser();
        validateExtension(getFileExtensionFromFilePath(this.filePath));
    }

    @Override
    public BufferedImage importFile() throws ImportFileException {
        BufferedImage result = null;
        File file = new File(filePath);
        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath), StandardCharsets.UTF_8);
            result = parser.parse(lines);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ImportFileException(e);
        }
        return result;
    }

    protected abstract FileParser getParser();

    abstract protected void validateExtension(String fileName) throws InvalidExtensionException;

    private String getFileExtensionFromFilePath(String filePath) {
        String result = "";
        int start = 0;
        int end = filePath.length() - 1;
        for (char c : filePath.toCharArray()) {
            if (c == '.') {
                result = filePath.substring(start, end);
            }
        }
        return result;
    }
}
