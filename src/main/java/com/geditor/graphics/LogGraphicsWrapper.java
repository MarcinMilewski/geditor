package com.geditor.graphics;

import com.geditor.Editor;
import com.geditor.figure.Figure;
import com.geditor.figure.Line;
import com.geditor.figure.Rectangle;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;

import java.awt.*;
import java.awt.geom.Line2D;

/**
 * Created by marcin on 05.03.16.
 */
@RequiredArgsConstructor
public class LogGraphicsWrapper {
    private static final Logger logger = Logger.getLogger(Editor.class.getName());
    private final Graphics2D graphics = null;
    ListMultimap<Point, Shape> figures = ArrayListMultimap.create();


    public void drawLine(int x1, int y1, int x2, int y2) {
        graphics.drawLine(x1, y1, x2, y2);
        addLineToContainer(x1, y1, x2, y2);
    }

    private void addLineToContainer(int x1, int y1, int x2, int y2) {
       Line2D line2D = new Line2D.Double();
        if (x1 < x2 && y1 < y2) {
            for (int i = x1, j = y1; i < x2 ; i++, j++) {
                figures.put(new Point(x1, y1), Line2D);
            }
            line2D.
        }
    }

    public void drawRect(int x, int y, int width, int height) {
        graphics.drawRect(x, y, width, height);
        addRectangleToContainer(x, y, width, height);
    }

    private void addRectangleToContainer(int x, int y, int width, int height) {
        Rectangle rectangle = new Rectangle(x, y, width, height);
        for (int i = x; i < width; i++) {
            figures.put(new Point(i, y), rectangle);
        }
        for (int i = x + height; i < width; i++) {
            figures.put(new Point(i, y), rectangle);
        }
        for (int j = y; j < height; j++) {
            figures.put(new Point(j, y), rectangle);
        }
        for (int j = y + width; j < width; j++) {
            figures.put(new Point(j, y), rectangle);
        }
        logger.info("Rectangle added " + rectangle);
    }

    public  void drawOval(int x, int y, int width, int height) {
        graphics.drawOval(x, y, width, height);
    }

    public  void drawPolygon(int xPoints[], int yPoints[],
                                     int nPoints) {
        graphics.drawPolygon(xPoints, yPoints, nPoints);
    }
}
