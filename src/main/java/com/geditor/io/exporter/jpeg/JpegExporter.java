package com.geditor.io.exporter.jpeg;

import com.geditor.io.exporter.AbstractFileExporter;
import com.geditor.io.exporter.exception.FileExportException;
import com.geditor.io.exporter.writer.jpeg.JpegFileWriter;

import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

public class JpegExporter extends AbstractFileExporter {
    @Override
    public void export(RenderedImage image, File outputFile) throws FileExportException {
        JpegFileWriter jpegFileWriter = new JpegFileWriter();
        try {
            jpegFileWriter.write(image, outputFile);
        } catch (IOException e) {
            throw new FileExportException(e);
        }
    }
}
