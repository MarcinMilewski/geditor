package com.geditor.mode.draw;

import com.geditor.Editor;
import com.geditor.mode.AbstractEditorStrategy;
import com.geditor.mode.EditorStrategy;
import com.geditor.mode.draw.mouse.RectangleDrawMouseAdapter;

/**
 * Created by marcin on 06.03.16.
 */
public class RectangleDrawEditorStrategy extends AbstractEditorStrategy implements EditorStrategy {
    public RectangleDrawEditorStrategy(Editor editor) {
        super(editor);
        this.mouseAdapter = new RectangleDrawMouseAdapter(editor);
    }
}
