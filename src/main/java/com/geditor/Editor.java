package com.geditor;

/**
 * Created by marcin on 23.02.16.
 */

import com.geditor.container.FigureContainer;
import com.geditor.util.Mode;
import com.geditor.util.Point;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;

public class Editor extends JComponent {
    private static final Logger logger = Logger.getLogger(Editor.class.getName());

    private Image image;
    private Graphics2D graphics;
    private Mode mode;
    private Point old;
    private Point current;

    private FigureContainer figureContainer;

    public Editor() {
        setDoubleBuffered(false);

//        addMouseListener(new MouseAdapter() {
//            public void mousePressed(MouseEvent e) {
//                logger.trace("Mouse pressed.");
//                old = new Point(e.getX(), e.getY());
//            }
//        });
//
//        addMouseMotionListener(new MouseMotionAdapter() {
//            public void mouseDragged(MouseEvent e) {
//                logger.trace("Mouse dragged.");
//                current = new Point(e.getX(), e.getY());
//
//                if (graphics != null) {
//                    graphics.drawLine(old.getX(), old.getY(), current.getX(), current.getY());
//                    repaint();
//                    old = new Point(current);
//                }
//            }
//        });

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

    public void setColor(Color color) {
        graphics.setPaint(color);
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }


}