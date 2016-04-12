package com.geditor.mode.edit.mouse;

import com.geditor.ui.editor.EditorView;
import com.geditor.mode.CustomMouseAdapter;
import com.geditor.mode.edit.strategy.FigureEditStrategy;
import org.apache.log4j.Logger;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;

/**
 * Created by marcin on 13.03.16.
 */
public class LineEditMouseAdapter extends CustomMouseAdapter {
    private static final Logger logger = Logger.getLogger(LineEditMouseAdapter.class.getName());
    private Point startPoint;
    private Line2D editShape;

    public LineEditMouseAdapter(EditorView editorView) {
        super(editorView);
        editShape = (Line2D) editorView.getShape();
        startPoint = new Point((int) editShape.getX1(), (int) editShape.getY1());
    }


    @Override
    public void mousePressed(MouseEvent e) {
        logger.info("Edit started");
        drawer.removeEditable(editShape);
        editorView.redrawAll();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        ((Line2D) editorView.getShape()).setLine(startPoint, new Point(e.getX(), e.getY()));
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
