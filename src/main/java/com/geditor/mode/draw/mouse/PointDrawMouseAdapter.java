package com.geditor.mode.draw.mouse;

import com.geditor.ui.editor.EditorView;
import com.geditor.mode.CustomMouseAdapter;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;

/**
 * Created by marcin on 06.03.16.
 */
public class PointDrawMouseAdapter extends CustomMouseAdapter {
    Point old;

    public PointDrawMouseAdapter(EditorView editorView) {
        super(editorView);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        old = new Point(e.getX(), e.getY());
        Shape shape = new Line2D.Double(old.x, old.y, old.x, old.y);
        drawer.addNonEditable(shape);
        drawer.draw(shape);
        editorView.repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Shape shape = new Line2D.Double(old.x, old.y, e.getX(), e.getY());
        drawer.addNonEditable(shape);
        drawer.draw(shape);
        editorView.repaint();
        old = new Point(e.getX(), e.getY());
    }


}
