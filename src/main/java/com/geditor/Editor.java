package com.geditor;

/**
 * Created by marcin on 23.02.16.
 */

import com.geditor.graphics.LogGraphicsWrapper;
import com.geditor.mode.Mode;
import com.geditor.mode.draw.PointDrawMode;
import lombok.Getter;
import lombok.Setter;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class Editor extends JComponent {
    private static final Logger logger = Logger.getLogger(Editor.class.getName());

    private Image image;
    @Getter @Setter private LogGraphicsWrapper logGraphicsWrapper;
    private Mode mode;
    @Getter @Setter private Point old;
    @Getter @Setter private Point current;
    public Editor() {
        setDoubleBuffered(false);
    }

    protected void paintComponent(Graphics g) {
        if (image == null) {
            image = createImage(getSize().width, getSize().height);
            logGraphicsWrapper = new LogGraphicsWrapper((Graphics2D) image.getGraphics());
            logGraphicsWrapper.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            clear();
        }
        g.drawImage(image, 0, 0, null);
    }

    public void clear() {
        logGraphicsWrapper.setPaint(Color.white);
        logGraphicsWrapper.fillRect(0, 0, getSize().width, getSize().height);
        logGraphicsWrapper.setPaint(Color.black);
        repaint();
    }

    public void setColor(Color color) {
        logGraphicsWrapper.setPaint(color);
    }

    public void setPointMode() {
        mode = new PointDrawMode(this);
        mode.activate();
        logger.info("mode: Point");
    }

    public void setRectangleMode() {
        logger.info("mode: Rectangle");


        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                logger.trace("Mouse pressed: " + "x: " +  e.getX() + " y " + e.getY());
                old = new Point(e.getX(), e.getY());

            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                logger.trace("Mouse dragged: " + "x: " +  e.getX() + " y " + e.getY());
                current = new Point(e.getX(), e.getY());

//                logGraphicsWrapper.drawLineWithoutLogging(old.x, old.y,  current.x, current.y) ;
                repaint();
                old = new Point(current);

            }
        });


    }
}