package com.geditor.mode.edit.mouse;

import com.geditor.ui.editor.EditorView;
import com.geditor.mode.CustomMouseAdapter;
import com.geditor.mode.edit.strategy.LineEditStrategy;
import com.geditor.mode.edit.strategy.OvalEditStrategy;
import com.geditor.mode.edit.strategy.RectangleEditStrategy;
import org.apache.log4j.Logger;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

/**
 * Created by marcin on 12.03.16.
 */
public class FigureEditMouseAdapter extends CustomMouseAdapter {
    private static final Logger logger = Logger.getLogger(FigureEditMouseAdapter.class.getName());
    private Point startPoint;
    public FigureEditMouseAdapter(EditorView editorView) {
        super(editorView);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        startPoint = new Point(e.getX(), e.getY());
        editorView.setShape(new Rectangle());
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
        Rectangle rectangle = ((Rectangle) editorView.getShape());
        Shape foundedShape = drawer.findShape(rectangle);
        if (foundedShape != null) {
            editorView.setShape(foundedShape);
            if (foundedShape instanceof Line2D) {
                logger.info("Line2D founded " + foundedShape);
                editorView.setStrategy(new LineEditStrategy(editorView));
            }
            else if (foundedShape instanceof Rectangle) {
                logger.info("Rectangle founded " + foundedShape);
                editorView.setStrategy(new RectangleEditStrategy(editorView));
            }
            else if (foundedShape instanceof Ellipse2D) {
                logger.info("Ellipse founded " + foundedShape);
                editorView.setStrategy(new OvalEditStrategy(editorView));
            }
            else {
                logger.info("Invalid shape " + foundedShape);
            }
            editorView.repaint();
        }
        else {
            editorView.setShape(null);
            editorView.repaint();
        }
    }
}
