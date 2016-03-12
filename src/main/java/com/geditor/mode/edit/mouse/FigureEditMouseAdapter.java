package com.geditor.mode.edit.mouse;

import com.geditor.Editor;
import com.geditor.mode.CustomMouseAdapter;
import org.apache.log4j.Logger;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by marcin on 12.03.16.
 */
public class FigureEditMouseAdapter extends CustomMouseAdapter {
    private static final Logger logger = Logger.getLogger(FigureEditMouseAdapter.class.getName());

    public FigureEditMouseAdapter(Editor editor) {
        super(editor);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Point point = new Point(e.getX(), e.getY());
        Shape shape = editor.findShape(point);
        if (shape != null) {
            logger.info("Shape founded: " + shape);
        }
    }
}
