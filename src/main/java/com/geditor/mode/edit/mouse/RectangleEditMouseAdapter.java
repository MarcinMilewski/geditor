package com.geditor.mode.edit.mouse;

import com.geditor.ui.editor.EditorView;
import com.geditor.mode.CustomMouseAdapter;
import com.geditor.mode.edit.strategy.FigureEditStrategy;
import org.apache.log4j.Logger;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by marcin on 13.03.16.
 */
public class RectangleEditMouseAdapter extends CustomMouseAdapter {
    private static final Logger logger = Logger.getLogger(RectangleEditMouseAdapter.class.getName());
    private Point startPoint;
    private Rectangle editShape;

    public RectangleEditMouseAdapter(EditorView editorView) {
        super(editorView);
        editShape = (Rectangle) editorView.getShape();
        startPoint = new Point((int) editShape.getX(), (int) editShape.getY());
    }

    @Override
    public void mousePressed(MouseEvent e) {
        logger.info("Edit started");
        drawer.removeEditable(editShape);
        editorView.redrawAll();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int x = Math.min(startPoint.x, e.getX());
        int y = Math.min(startPoint.y, e.getY());
        int width = Math.abs(e.getX() - startPoint.x);
        int height = Math.abs(e.getY() - startPoint.y);

        ((Rectangle) editorView.getShape()).setBounds(x, y, width, height);
        editorView.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        logger.info("Edit finished");
        drawer.addEditable(editShape);
        editorView.setShape(null);
        editorView.redrawAll();
        editorView.setStrategy(new FigureEditStrategy(editorView));
    }
}
