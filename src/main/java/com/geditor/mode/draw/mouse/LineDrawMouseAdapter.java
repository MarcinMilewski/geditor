package com.geditor.mode.draw.mouse;

import com.geditor.ui.editor.EditorView;
import com.geditor.mode.CustomMouseAdapter;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;

/**
 * Created by marcin on 06.03.16.
 */
public class LineDrawMouseAdapter extends CustomMouseAdapter {
    private Point startPoint;

    public LineDrawMouseAdapter(EditorView editorView) {
        super(editorView);
    }

    @Override
    public void mousePressed(MouseEvent e) {
            startPoint = new Point(e.getX(), e.getY());
            editorView.setShape(new Line2D.Double());
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        ((Line2D) editorView.getShape()).setLine(startPoint, new Point(e.getX(), e.getY()));
        editorView.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        drawer.addEditable(editorView.getShape());
        drawer.draw(editorView.getShape());
        editorView.repaint();
        editorView.setShape(null);
    }
}
