package com.geditor.mode.draw.mouse;

import com.geditor.Editor;
import com.geditor.mode.CustomMouseAdapter;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by marcin on 06.03.16.
 */
public class LineDrawMouseAdapter extends CustomMouseAdapter {
    private int clickCount = 0;

    public LineDrawMouseAdapter(Editor editor) {
        super(editor);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        clickCount++;
        if (clickCount == 1) {
            editor.setOld(new Point(e.getX(), e.getY()));
        }
        else {
            editor.setCurrent(new Point(e.getX(), e.getY()));
            Point old = editor.getOld();
            Point current = editor.getCurrent();
            editor.getDrawer().drawLine(old.x, old.y,  current.x, current.y); ;
            editor.repaint();
            editor.setOld(editor.getCurrent());
            clickCount = 0;
        }

    }
}
