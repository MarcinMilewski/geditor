package com.geditor.mode.draw.strategy;

import com.geditor.ui.editor.EditorView;
import com.geditor.mode.AbstractEditorStrategy;
import com.geditor.mode.EditorStrategy;
import com.geditor.mode.draw.mouse.OvalDrawMouseAdapter;

/**
 * Created by marcin on 06.03.16.
 */
public class OvalDrawStrategy extends AbstractEditorStrategy implements EditorStrategy {
    public OvalDrawStrategy(EditorView editorView) {
        super(editorView);
        this.mouseAdapter = new OvalDrawMouseAdapter(editorView);
    }
}
