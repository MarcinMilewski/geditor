package com.geditor.mode.mouse;

import com.geditor.Editor;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by marcin on 06.03.16.
 */
@RequiredArgsConstructor
public class CustomMouseAdapter extends MouseAdapter {
    private final Editor editor;
    private static final Logger logger = Logger.getLogger(CustomMouseAdapter.class.getName());

    @Override
    public void mousePressed(MouseEvent e) {
        logger.trace("Mouse pressed: " + "x: " +  e.getX() + " y " + e.getY());
        editor.setOld(new Point(e.getX(), e.getY()));
        editor.getLogGraphicsWrapper().drawPoint(e.getX(), e.getY());
        editor.repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
            logger.trace("Mouse dragged: " + "x: " +  e.getX() + " y " + e.getY());
            editor.setCurrent(new Point(e.getX(), e.getY()));

            Point old = editor.getOld();
            Point current = editor.getCurrent();
            editor.getLogGraphicsWrapper().drawLineWithoutLogging(old.x, old.y,  current.x, current.y) ;
            editor.repaint();
            editor.setOld(editor.getCurrent());

    }
}
