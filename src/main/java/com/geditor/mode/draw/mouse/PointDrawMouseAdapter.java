package com.geditor.mode.draw.mouse;

import com.geditor.Editor;
import com.geditor.mode.CustomMouseAdapter;
import org.apache.log4j.Logger;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by marcin on 06.03.16.
 */
public class PointDrawMouseAdapter extends CustomMouseAdapter {
    private static final Logger logger = Logger.getLogger(CustomMouseAdapter.class.getName());

    public PointDrawMouseAdapter(Editor editor) {
        super(editor);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        logger.trace("Mouse pressed: " + "x: " +  e.getX() + " y " + e.getY());
        editor.setOld(new Point(e.getX(), e.getY()));
        editor.getDrawer().drawPoint(e.getX(), e.getY());
        editor.repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        logger.trace("Mouse dragged: " + "x: " +  e.getX() + " y " + e.getY());
        editor.setCurrent(new Point(e.getX(), e.getY()));

        Point old = editor.getOld();
        Point current = editor.getCurrent();
        editor.getDrawer().drawLineWithoutLogging(old.x, old.y,  current.x, current.y) ;
        editor.repaint();
        editor.setOld(editor.getCurrent());

    }
}
