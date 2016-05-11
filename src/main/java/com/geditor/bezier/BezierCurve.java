package com.geditor.bezier;

import com.geditor.util.MathUtils;
import com.google.common.collect.Lists;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.List;

public class BezierCurve {
    private final List<Point> controlPoints;
    private final int density;
    private final List<Point2D.Float> curve;
    final float[] domain;
    final int n;


    public BezierCurve(List<Point> controlPoints, int density) {
        this.controlPoints = controlPoints;
        this.density = density;
        domain = calculateDomain(density);
        createCurve();
        curve = createCurve();
        n = controlPoints.size();
    }

    public List<Point2D.Float> getCurve() {
        return curve;
    }

    private List<Point2D.Float> createCurve() {
        List<Point2D.Float> curvePoints = Lists.newArrayList();

        for (int t = 0; t < domain.length; ++t) {
            curvePoints.add(calculateValue(t));
        }

        return null;
    }

    private Point2D.Float calculateValue(int t) {
        float xValue = 0;
        float yValue = 0;
        for (int i = 0; i < n; i++) {
            float bernstainPolynomialValue = MathUtils.bernsteinPolynomial(n, i,t);
            xValue += controlPoints.get(i).x * bernstainPolynomialValue;
            yValue += controlPoints.get(i).y * bernstainPolynomialValue;
        }
        return new Point2D.Float(xValue, yValue);
    }


    private float[] calculateDomain(int density) {
        float[] domain = new float[density];
        final float delta = 1 / density;
        for (int i = 1; i < density; ++i) {
            domain[i] = delta + domain[i-1];
        }
        return domain;
    }
}
