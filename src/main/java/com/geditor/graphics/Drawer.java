package com.geditor.graphics;

import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.log4j.Logger;

import java.awt.*;
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

    public void setRenderingHint(RenderingHints.Key keyAntialiasing, Object valueAntialiasOn) {
        graphics.setRenderingHint(keyAntialiasing, valueAntialiasOn);
    }

    public void setColor(Color paint) {
        graphics.setPaint(paint);
    }

    public void fillRect(int i, int i1, int width, int height) {
        graphics.fillRect(i, i1, width, height);
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

    public void draw(Shape shape) {
        if (shape != null) {
            graphics.draw(shape);
        }
    }

    public void add(Shape shape) {
        figures.add(shape);
    }

    public void remove(Shape shape) {
        figures.remove(shape);
    }

}
