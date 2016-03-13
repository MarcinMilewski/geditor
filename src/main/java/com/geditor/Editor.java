package com.geditor;

/**
 * Created by marcin on 23.02.16.
 */

import com.geditor.graphics.Drawer;
import com.geditor.mode.EditorStrategy;
import com.geditor.mode.draw.strategy.*;
import com.geditor.mode.edit.strategy.FigureEditStrategy;
import com.geditor.mode.edit.strategy.LineEditStrategy;
import com.geditor.mode.edit.strategy.RectangleEditStrategy;
import lombok.Getter;
import lombok.Setter;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Editor extends JPanel {
    private static final Logger logger = Logger.getLogger(Editor.class.getName());
    private BufferedImage image;
    @Getter @Setter private Drawer drawer;
    private EditorStrategy mode;
    private Shape shape;
    private Graphics2D currentGraphics;
    private Stroke stroke = new BasicStroke();
    public Editor() {
        setDoubleBuffered(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        currentGraphics = (Graphics2D) g;
        super.paintComponent(g);
        logger.info("paintComponent triggered.");
        if (image == null) {
            image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
            drawer = new Drawer((Graphics2D) image.getGraphics());
            drawer.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            clearDrawArea();
        }

        g.drawImage(image, 0, 0, null);
        currentGraphics.setStroke(stroke);
            if (shape != null)
            {
                logger.info("shape drawing.");
                ((Graphics2D)g).draw(shape);
            }
    }

    public void clearDrawArea() {
        drawer.setColor(Color.white);
        drawer.fillRect(0, 0, getSize().width, getSize().height);
        drawer.setColor(Color.black);
        repaint();
    }

    public void clearBuffer() {
        drawer.clear();
    }

    public void clearAll() {
        clearBuffer();
        clearDrawArea();
        shape = null;
    }

    public void setColor(Color color) {
        drawer.setColor(color);
    }

    public void setPointMode() {
        deactivateMode();
        mode = new PointDrawStrategy(this);
        mode.activate();
        logger.info("mode: Point");
    }

    public void setLineMode() {
        deactivateMode();
        mode = new LineDrawStrategy(this);
        mode.activate();
        logger.info("mode: Line");
    }

    public void setRectangleMode() {
        deactivateMode();
        mode = new RectangleDrawStrategy(this);
        mode.activate();
        logger.info("mode: Rectangle");
    }

    public void setOvalMode() {
        deactivateMode();
        mode= new OvalDrawStrategy(this);
        mode.activate();
        logger.info("mode: Oval");
    }

    public void setPolygonMode() {
        deactivateMode();
        mode= new PolygonDrawStrategy(this);
        mode.activate();
        logger.info("mode: Polygon");
    }

    public void setEditMode() {
        deactivateMode();
        setDottedStroke();
        mode = new FigureEditStrategy(this);
        mode.activate();
        logger.info("mode: Edit");
    }

    public void setLineEditMode() {
        deactivateMode();
        mode = new LineEditStrategy(this);
        mode.activate();
        logger.info("mode: Line edit");
    }

    public void setRectangleEditMode() {
        deactivateMode();
        mode = new RectangleEditStrategy(this);
        mode.activate();
        logger.info("mode: Rectangle edit");
    }

    public Shape findShape(Rectangle rectangle) {
        return drawer.findShape(rectangle);
    }

    private void deactivateMode() {
        if (mode != null) {
            logger.info("current mode: deactivated.");
            mode.deactivate();
        }
        setSolidStroke();
    }

    private void setDottedStroke() {
        stroke = new BasicStroke(
                1f,
                BasicStroke.CAP_ROUND,
                BasicStroke.JOIN_ROUND,
                1f,
                new float[] {2f},
                0f);
    }

    private void setSolidStroke() {
        stroke = new BasicStroke();
    }

    public synchronized Shape getShape() {
        return shape;
    }

    public synchronized void setShape(Shape shape) {
        this.shape = shape;
    }

    public void redrawAll() {
        clearDrawArea();
        drawer.redrawAll();
        repaint();
    }

}