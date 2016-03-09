package com.geditor.mode.draw.mouse;

import com.geditor.Editor;
import com.geditor.mode.CustomMouseAdapter;
import com.google.common.collect.Lists;

import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Created by marcin on 09.03.16.
 */
public class PolygonDrawMouseAdapter extends CustomMouseAdapter {
    private int clickCount = 0;
    private ArrayList<Integer> xPoints = Lists.newArrayList();
    private ArrayList<Integer> yPoints = Lists.newArrayList();

    public PolygonDrawMouseAdapter(Editor editor) {
        super(editor);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        clickCount++;
        xPoints.add(e.getX());
        yPoints.add(e.getX());
    }


}
