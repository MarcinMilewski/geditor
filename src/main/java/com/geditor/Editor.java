package com.geditor;

/**
 * Created by marcin on 23.02.16.
 */

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
    private Graphics2D graphics;
    private Mode mode;
    private int currentX, currentY, oldX, oldY;

    public Editor() {
        setDoubleBuffered(false);
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                logger.trace("Mouse pressed.");
                oldX = e.getX();
                oldY = e.getY();
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                logger.trace("Mouse dragged.");
                currentX = e.getX();
                currentY = e.getY();

                if (graphics != null) {
                    graphics.drawLine(oldX, oldY, currentX, currentY);
                    repaint();
                    oldX = currentX;
                    oldY = currentY;
                }
            }
        });

    }

    protected void paintComponent(Graphics g) {
        if (image == null) {
            image = createImage(getSize().width, getSize().height);
            graphics = (Graphics2D) image.getGraphics();
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

    public void black() {
        graphics.setPaint(Color.black);
    }

    public void drawLine() {
        mode = Mode.LINE;
    }

    public void drawRectangle() {
        mode = Mode.RECTANGLE;
    }

    public void drawOval() {
        mode = Mode.OVAL;
    }
}