package com.geditor.mode.draw;

import com.geditor.Editor;
import com.geditor.mode.AbstractEditorStrategy;
import com.geditor.mode.EditorStrategy;
import com.geditor.mode.draw.key.PolygonDrawKeyEvents;
import com.geditor.mode.draw.mouse.PolygonDrawMouseAdapter;
import com.google.common.collect.Lists;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by marcin on 09.03.16.
 */
public class PolygonDrawEditorStrategy extends AbstractEditorStrategy implements EditorStrategy {
    private Editor editor;
    private int clickCounter = 0;
    private ArrayList<Integer> xPoints = Lists.newArrayList();
    private ArrayList<Integer> yPoints = Lists.newArrayList();

    public PolygonDrawEditorStrategy(Editor editor) {
        super(editor);
        this.editor = editor;
        this.mouseAdapter = new PolygonDrawMouseAdapter(editor, this);
        this.keyAdapter = new PolygonDrawKeyEvents(editor, this);
    }

    public void incrementClickCounter() {
        clickCounter++;
    }

    public void addPoint(Point point) {
        xPoints.add((int) point.getX());
        yPoints.add((int) point.getY());
    }

    public void draw() {
        int[] x = xPoints.stream().mapToInt(i -> i).toArray();
        int[] y = yPoints.stream().mapToInt(i -> i).toArray();
        editor.getDrawer().drawPolygon(x, y, xPoints.size());
    }
}
