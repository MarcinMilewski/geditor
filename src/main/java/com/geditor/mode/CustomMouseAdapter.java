package com.geditor.mode;

import com.geditor.ui.editor.EditorView;
import com.geditor.graphic.Drawer;

import java.awt.event.MouseAdapter;

/**
 * Created by marcin on 06.03.16.
 */
public class CustomMouseAdapter extends MouseAdapter {
    protected final EditorView editorView;
    protected final Drawer drawer;

    public CustomMouseAdapter(EditorView editorView) {
        this.editorView = editorView;
        if (editorView != null ){
            drawer = editorView.getDrawer();
        }
        else {
            drawer = null;
        }
    }
}
