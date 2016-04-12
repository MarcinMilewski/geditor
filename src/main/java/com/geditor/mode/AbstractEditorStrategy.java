package com.geditor.mode;

import com.geditor.ui.editor.EditorView;

import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;

/**
 * Created by marcin on 06.03.16.
 */
public abstract class AbstractEditorStrategy implements EditorStrategy {
    protected final EditorView editorView;
    protected MouseAdapter mouseAdapter;
    protected KeyAdapter keyAdapter;

    public AbstractEditorStrategy(EditorView editorView) {
        this.editorView = editorView;
    }

    @Override
    public void activate() {
        editorView.addMouseListener(mouseAdapter);
        editorView.addMouseMotionListener(mouseAdapter);
        editorView.addMouseWheelListener(mouseAdapter);
    }

    @Override
    public void deactivate() {
        editorView.removeMouseListener(mouseAdapter);
        editorView.removeMouseMotionListener(mouseAdapter);
        editorView.removeMouseWheelListener(mouseAdapter);
    }
}
