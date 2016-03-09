package com.geditor.mode.draw.mouse;

import com.geditor.Editor;
import com.geditor.mode.CustomMouseAdapter;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by marcin on 06.03.16.
 */
public class OvalDrawMouseAdapter extends CustomMouseAdapter {
    private int clickCount = 0;

    public OvalDrawMouseAdapter(Editor editor) {
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
            if (current.x > old.x && current.y > old.y) {
                editor.getDrawer().drawOval(old.x, old.y, current.x - old.x, current.y - old.y);
            }
            else if (current.x < old.x && current.y < old.y) {
                editor.getDrawer().drawOval(current.x, current.y, Math.abs(current.x - old.x), Math.abs(current.y - old.y));
            }
            else if (current.x > old.x && current.y < old.y) {
                editor.getDrawer().drawOval(old.x, current.y, Math.abs(current.x - old.x), Math.abs(current.y - old.y));
            }
            else  { // (current.x < old.x && current.y > old.y)
                editor.getDrawer().drawOval(current.x, old.y, Math.abs(current.x - old.x), Math.abs(current.y - old.y));
            }
            editor.repaint();
            editor.setOld(editor.getCurrent());
            clickCount = 0;
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        super.mouseDragged(e);
    }
}
