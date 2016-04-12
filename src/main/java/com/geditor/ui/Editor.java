package com.geditor.ui;

/**
 * Created by marcin on 23.02.16.
 */

import com.geditor.graphic.Drawer;
import com.geditor.mode.EditorStrategy;
import lombok.Getter;
import lombok.Setter;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Editor extends JPanel {
    private static final Logger logger = Logger.getLogger(Editor.class.getName());
    @Getter private BufferedImage image;
    @Getter @Setter private Drawer drawer;
    private EditorStrategy strategy;
    private Shape shape;
    private Graphics2D currentGraphics;
    private Stroke stroke = new BasicStroke();
    public Editor() {
        setDoubleBuffered(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        currentGraphics = (Graphics2D) g;
        if (image == null) {
            image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
            drawer = new Drawer((Graphics2D) image.getGraphics());
            drawer.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            drawer.setBackgroundColor(Color.WHITE);
            clearDrawArea();
        }

        g.drawImage(image, 0, 0, null);
        currentGraphics.setStroke(stroke);
            if (shape != null)
            {
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

    public void setStrategy(EditorStrategy strategy) {
        deactivateStrategy();
        this.strategy = strategy;
        strategy.activate();
        logger.info("strategy: " + strategy);
    }

    private void deactivateStrategy() {
        if (strategy != null) {
            logger.info("current strategy: deactivated.");
            strategy.deactivate();
        }
        setSolidStroke();
    }

    public void setDottedStroke() {
        stroke = new BasicStroke(
                1f,
                BasicStroke.CAP_ROUND,
                BasicStroke.JOIN_ROUND,
                1f,
                new float[] {2f},
                0f);
    }

    public void setImage(BufferedImage image) {
        clearDrawArea();
        this.image = image;
        drawer = new Drawer((Graphics2D) image.getGraphics());
        drawer.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        drawer.setColor(Color.black);
        repaint();
    }

    public void setSolidStroke() {
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