package com.geditor.graphics;

import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.log4j.Logger;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by marcin on 05.03.16.
 */
@RequiredArgsConstructor
public class Drawer {
    private static final Logger logger = Logger.getLogger(Drawer.class.getName());
    private final Graphics2D graphics;
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
        addOvalToContainer(x, y, width, height);
    }

    private void addOvalToContainer(int x, int y, int width, int height) {
        Ellipse2D ellipse2D = new Ellipse2D.Double(x, y, width, height);
        figures.add(ellipse2D);
        logger.info("Elipse added: " + ellipse2D);
    }

    public  void drawPolygon(int xPoints[], int yPoints[],
                                     int nPoints) {
        graphics.drawPolygon(xPoints, yPoints, nPoints);
    }

    public void setRenderingHint(RenderingHints.Key keyAntialiasing, Object valueAntialiasOn) {
        graphics.setRenderingHint(keyAntialiasing, valueAntialiasOn);
    }

    public void setColor(Color paint) {
        graphics.setPaint(paint);
    }

    public void fillRect(int i, int i1, int width, int height) {
        graphics.fillRect(i, i1, width, height);
    }

    public void drawPoint(int x, int y) {
        graphics.drawLine(x, y, x, y);
    }

    public void drawLineWithoutLogging(int x1, int y1, int x2, int y2) {
        graphics.drawLine(x1, y1, x2, y2);
    }

    public Shape findShape(Point point) {
        List<Shape> shapes = figures.stream().filter(shape -> shape.intersects(point.getX(), point.getY(), 5.0d, 5.0d)).collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(shapes)) {
            return shapes.get(0);
        }
        else {
            return null;
        }
    }
}
