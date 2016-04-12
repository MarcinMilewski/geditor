package com.geditor.mode.edit.strategy;

import com.geditor.ui.editor.EditorView;
import com.geditor.mode.AbstractEditorStrategy;
import com.geditor.mode.EditorStrategy;
import com.geditor.mode.edit.mouse.FigureEditMouseAdapter;

/**
 * Created by marcin on 12.03.16.
 */
public class FigureEditStrategy extends AbstractEditorStrategy implements EditorStrategy {
    public FigureEditStrategy(EditorView editorView) {
        super(editorView);
        this.mouseAdapter = new FigureEditMouseAdapter(editorView);
    }

    @Override
    public void activate() {
        super.activate();
        editorView.setDottedStroke();
    }

    @Override
    public void deactivate() {
        super.deactivate();
        editorView.setSolidStroke();
    }
}
