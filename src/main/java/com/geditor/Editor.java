package com.geditor;

/**
 * Created by marcin on 23.02.16.
 */

import com.geditor.graphics.Drawer;
import com.geditor.mode.EditorStrategy;
import com.geditor.mode.draw.*;
import com.geditor.mode.edit.FigureEditStrategy;
import lombok.Getter;
import lombok.Setter;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;

public class Editor extends JComponent {
    private static final Logger logger = Logger.getLogger(Editor.class.getName());

    private Image image;
    @Getter @Setter private Drawer drawer;
    private EditorStrategy mode;
    @Getter @Setter private Point old;
    @Getter @Setter private Point current;
    public Editor() {
        setDoubleBuffered(false);
    }

    protected void paintComponent(Graphics g) {
        if (image == null) {
            image = createImage(getSize().width, getSize().height);
            drawer = new Drawer((Graphics2D) image.getGraphics());
            drawer.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            clear();
        }
        g.drawImage(image, 0, 0, null);
    }

    public void clear() {
        drawer.setColor(Color.white);
        drawer.fillRect(0, 0, getSize().width, getSize().height);
        drawer.setColor(Color.black);
        repaint();
    }

    public void setColor(Color color) {
        drawer.setColor(color);
    }

    public void setPointMode() {
        deactivateMode();
        mode = new PointDrawEditorStrategy(this);
        mode.activate();
        logger.info("mode: Point");
    }

    public void setLineMode() {
        deactivateMode();
        mode = new LineDrawEditorStrategy(this);
        mode.activate();
        logger.info("mode: Line");
    }

    public void setRectangleMode() {
        deactivateMode();
        mode = new RectangleDrawEditorStrategy(this);
        mode.activate();
        logger.info("mode: Rectangle");
    }

    public void setOvalMode() {
        deactivateMode();
        mode= new OvalDrawEditorStrategy(this);
        mode.activate();
        logger.info("mode: Oval");
    }

    public void setPolygonMode() {
        deactivateMode();
        mode= new PolygonDrawEditorStrategy(this);
        mode.activate();
        logger.info("mode: Polygon");
    }

    public void setEditMode() {
        deactivateMode();
        mode = new FigureEditStrategy(this);
        mode.activate();
        logger.info("mode: Edit");
    }

    public Shape findShape(Point point) {
        return drawer.findShape(point);
    }

    private void deactivateMode() {
        if (mode != null) {
            logger.info("current mode: deactivated.");
            mode.deactivate();
        }
    }



}