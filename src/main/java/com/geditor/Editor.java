package com.geditor;

/**
 * Created by marcin on 23.02.16.
 */

import com.geditor.container.FigureContainer;
import com.geditor.graphics.LogGraphicsWrapper;
import com.geditor.util.Mode;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class Editor extends JComponent {
    private static final Logger logger = Logger.getLogger(Editor.class.getName());

    private Image image;
    private LogGraphicsWrapper graphics;
    private Mode mode;
    private Point old;
    private Point current;

    private FigureContainer figureContainer;

    public Editor() {
        setDoubleBuffered(false);
    }

    protected void paintComponent(Graphics g) {
        if (image == null) {
            image = createImage(getSize().width, getSize().height);
            graphics = new LogGraphicsWrapper((Graphics2D) image.getGraphics());
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            clear();
        }
        g.drawImage(image, 0, 0, null);
    }

    public void clear() {
        graphics.setPaint(Color.white);
        graphics.fillRect(0, 0, getSize().width, getSize().height);
        graphics.setPaint(Color.black);
        repaint();
    }

    public void setColor(Color color) {
        graphics.setPaint(color);
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public void setPointMode() {
        mode = Mode.POINT;
        logger.info("POINT mode");

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                logger.trace("Mouse pressed: " + "x: " +  e.getX() + " y " + e.getY());
                old = new Point(e.getX(), e.getY());

                graphics.drawPoint(old.x, old.y);
                repaint();

            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                logger.trace("Mouse dragged: " + "x: " +  e.getX() + " y " + e.getY());
                current = new Point(e.getX(), e.getY());

                graphics.drawLineWithoutLogging(old.x, old.y,  current.x, current.y) ;
                repaint();
                old = new Point(current);

            }
        });
    }
}