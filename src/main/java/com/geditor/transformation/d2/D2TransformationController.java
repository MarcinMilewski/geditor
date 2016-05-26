package com.geditor.transformation.d2;

import com.geditor.commons.RectanglePolygon;
import com.geditor.math.Matrix2D;
import com.geditor.math.Vector2D;
import com.geditor.transformation.point.PointTransformations;
import com.geditor.ui.editor.Editor;
import com.google.common.collect.Lists;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class D2TransformationController {
    private static final D2TransformationController instance = new D2TransformationController();
    private static final D2EditorPanel d2Editor = D2EditorPanel.getInstance();
    private static final int SCALE = d2Editor.getDECIMAL_PART();

    private D2TransformationController() {
    };

    public static D2TransformationController getInstance() {
        return instance;
    }

    public void translate(double dx, double dy) {
        final double dxCp = dx * SCALE;
        final double dyCp = dy * SCALE;

        RectanglePolygon current = d2Editor.getShape();
        List<Point> points = current.getPoints();

        List<Point> translatedPoints = Lists.newArrayList();
        points.stream().parallel().forEachOrdered(point -> {
            Vector2D pointVector = new Vector2D(point.getX(), point.getY());
            Matrix2D translationMatrix = new Matrix2D();
            translationMatrix.translate(dxCp, dyCp);
            Vector2D translatedPointVector =  translationMatrix.vec_postmultiply(pointVector);
            synchronized (this) {
                translatedPoints.add(new Point((int)translatedPointVector.get_x(), (int)translatedPointVector.get_y()));
            }
        });

        d2Editor.setShape(new RectanglePolygon(translatedPoints));
        d2Editor.repaint();
    }

}
