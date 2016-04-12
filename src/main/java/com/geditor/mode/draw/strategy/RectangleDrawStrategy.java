package com.geditor.mode.draw.strategy;

import com.geditor.ui.editor.EditorView;
import com.geditor.mode.AbstractEditorStrategy;
import com.geditor.mode.EditorStrategy;
import com.geditor.mode.draw.mouse.RectangleDrawMouseAdapter;

/**
 * Created by marcin on 06.03.16.
 */
public class RectangleDrawStrategy extends AbstractEditorStrategy implements EditorStrategy {
    public RectangleDrawStrategy(EditorView editorView) {
        super(editorView);
        this.mouseAdapter = new RectangleDrawMouseAdapter(editorView);
    }
}
