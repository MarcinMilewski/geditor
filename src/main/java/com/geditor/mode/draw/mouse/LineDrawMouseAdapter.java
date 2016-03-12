package com.geditor.mode.draw.mouse;

import com.geditor.Editor;
import com.geditor.global.Global;
import com.geditor.mode.CustomMouseAdapter;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;

/**
 * Created by marcin on 06.03.16.
 */
public class LineDrawMouseAdapter extends CustomMouseAdapter {
    private Point startPoint;

    public LineDrawMouseAdapter(Editor editor) {
        super(editor);
    }

    @Override
    public void mousePressed(MouseEvent e) {
            startPoint = new Point(e.getX(), e.getY());
            Global.setShape(new Line2D.Double());
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        ((Line2D)Global.getShape()).setLine(startPoint, new Point(e.getX(), e.getY()));
        editor.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        drawer.add(Global.getShape());
        drawer.draw(Global.getShape());
        editor.repaint();
        Global.setShape(null);
    }
}
