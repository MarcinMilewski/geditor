package com.geditor.mode.draw.mouse;

import com.geditor.bezier.BezierCurve;
import com.geditor.commons.Polyline2D;
import com.geditor.mode.CustomMouseAdapter;
import com.geditor.ui.editor.Editor;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import lombok.extern.log4j.Log4j;

import java.util.Arrays;
import java.util.List;
import java.awt.*;
import java.awt.event.MouseEvent;

@Log4j
public class BezierCurveDrawMouseAdapter  extends CustomMouseAdapter {
    private List<Point> controlPoints;
    private Polyline2D polyLineCurve;

    public BezierCurveDrawMouseAdapter(Editor editor) {
        super(editor);
        controlPoints = Lists.newArrayList();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (polyLineCurve == null) {
            polyLineCurve = new Polyline2D();
            controlPoints.add(new Point(e.getX(), e.getY()));
            log.debug("Add point: x= " + e.getX() + ",y= " + e.getY());
            log.debug("C points=" + Joiner.on("\t").join(controlPoints));
        }
        else {
            if (e.getButton() == MouseEvent.BUTTON3) {
                log.debug("Bezier curve saved, c points=" + Joiner.on("\t").join(controlPoints));
                editor.setShape(polyLineCurve);
                drawer.addEditable(polyLineCurve);
                drawer.draw(polyLineCurve);
                editor.repaint();
                editor.setShape(null);
            } else {
                controlPoints.add(new Point(e.getX(), e.getY()));
                log.debug("Add point: x= " + e.getX() + ",y= " + e.getY());
                log.debug("Bezier curve painting, c points=" + Joiner.on("\t").join(controlPoints));
                BezierCurve bezierCurve = new BezierCurve(controlPoints, 100);
                log.debug("Bezier points: " + Joiner.on("\t").join(bezierCurve.getCurve()));
                polyLineCurve = new Polyline2D(bezierCurve.getCurve());
                editor.setShape(polyLineCurve);
                editor.repaint();
            }
        }

    }

}
