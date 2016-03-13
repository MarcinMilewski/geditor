package com.geditor.mode.edit.mouse;

import com.geditor.Editor;
import com.geditor.mode.CustomMouseAdapter;
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
    public FigureEditMouseAdapter(Editor editor) {
        super(editor);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        startPoint = new Point(e.getX(), e.getY());
        editor.setShape(new Rectangle());
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int x = Math.min(startPoint.x, e.getX());
        int y = Math.min(startPoint.y, e.getY());
        int width = Math.abs(e.getX() - startPoint.x);
        int height = Math.abs(e.getY() - startPoint.y);

        ((Rectangle)editor.getShape()).setBounds(x, y, width, height);
        editor.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Rectangle rectangle = ((Rectangle)editor.getShape());
        Shape foundedShape = drawer.findShape(rectangle);
        if (foundedShape != null) {
            if (foundedShape instanceof Line2D) {
                logger.info("Line2D founded " + foundedShape);
                editor.setShape(foundedShape);
                editor.repaint();
                editor.setLineEditMode();
            }
            else if (foundedShape instanceof Rectangle) {
                logger.info("Rectangle founded " + foundedShape);
            }
            else if (foundedShape instanceof Ellipse2D) {
                logger.info("Ellipse founded " + foundedShape);
            }
            else {
                logger.info("Invalid shape " + foundedShape);
            }
        }
        else {
            editor.setShape(null);
            editor.repaint();
        }
    }
}
