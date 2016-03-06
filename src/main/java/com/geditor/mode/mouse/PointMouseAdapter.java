package com.geditor.mode.mouse;

import com.geditor.Editor;

import java.awt.event.MouseEvent;

/**
 * Created by marcin on 06.03.16.
 */
public class PointMouseAdapter extends CustomMouseAdapter {

    public PointMouseAdapter(Editor editor) {
        super(editor);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
    }
}
