package com.geditor.mode.draw.mouse;

import com.geditor.Editor;
import com.geditor.mode.CustomMouseAdapter;
import com.geditor.mode.draw.PolygonDrawEditorStrategy;
import com.google.common.collect.Lists;

import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Created by marcin on 09.03.16.
 */
public class PolygonDrawMouseAdapter extends CustomMouseAdapter {
    private PolygonDrawEditorStrategy context;

    private int clickCount = 0;
    private ArrayList<Integer> xPoints = Lists.newArrayList();
    private ArrayList<Integer> yPoints = Lists.newArrayList();


    public PolygonDrawMouseAdapter(Editor editor, PolygonDrawEditorStrategy polygonDrawEditorStrategy) {
        super(editor);
        context = polygonDrawEditorStrategy;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        context.incrementClickCounter();
        context.addPoint(e.getPoint());
    }


}
