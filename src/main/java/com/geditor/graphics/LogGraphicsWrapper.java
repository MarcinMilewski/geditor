package com.geditor.graphics;

import com.geditor.Editor;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.List;

/**
 * Created by marcin on 05.03.16.
 */
@RequiredArgsConstructor
public class LogGraphicsWrapper {
    private static final Logger logger = Logger.getLogger(Editor.class.getName());
    private final Graphics2D graphics = null;
    private List<Shape> figures = Lists.newArrayList();

    public void drawLine(int x1, int y1, int x2, int y2) {
        graphics.drawLine(x1, y1, x2, y2);
        addLineToContainer(x1, y1, x2, y2);
    }

    private void addLineToContainer(int x1, int y1, int x2, int y2) {
        Line2D line2D = new Line2D.Double(x1,y1,x2,y2);
        figures.add(line2D);
        logger.info("Line added: " + line2D);
    }

    public void drawRect(int x, int y, int width, int height) {
        graphics.drawRect(x, y, width, height);
        addRectangleToContainer(x, y, width, height);
    }

    private void addRectangleToContainer(int x, int y, int width, int height) {
        Rectangle2D rectangle = new Rectangle2D.Double(x, y, width, height);
        figures.add(rectangle);
        logger.info("Rectangle added: " + rectangle);
    }

    public  void drawOval(int x, int y, int width, int height) {
        graphics.drawOval(x, y, width, height);
    }

    public  void drawPolygon(int xPoints[], int yPoints[],
                                     int nPoints) {
        graphics.drawPolygon(xPoints, yPoints, nPoints);
    }
}
