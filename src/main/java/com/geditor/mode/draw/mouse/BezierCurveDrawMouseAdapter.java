package com.geditor.mode.draw.mouse;

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
    private List<Point> controlPoints = Lists.newArrayList();
    private Polyline2D polyLineCurve;

    public BezierCurveDrawMouseAdapter(Editor editor) {
        super(editor);
        polyLineCurve = new Polyline2D();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON3) {
            log.debug("Bezier curve painting, c points=" + Joiner.on("\t").join(controlPoints));
            editor.setShape(new Polyline2D());
            Polyline2D polyline2D = new Polyline2D();
            drawer.addEditable(editor.getShape());
            drawer.draw(editor.getShape());
            editor.repaint();
            editor.setShape(null);
        } else {
            log.debug("Add point: x= " + e.getX() + ",y= " + e.getY());
            log.debug("Bezier curve painting, c points=" + Joiner.on("\t").join(controlPoints));
            editor.setShape(polyLineCurve);
            Polyline2D polyline2D = new Polyline2D();
            drawer.draw(editor.getShape());
            editor.repaint();
            controlPoints.add(new Point(e.getX(), e.getY()));
        }
    }

}
