package com.geditor.mode.draw;

import com.geditor.Editor;
import com.geditor.mode.AbstractEditorStrategy;
import com.geditor.mode.EditorStrategy;
import com.geditor.mode.draw.mouse.OvalDrawMouseAdapter;

/**
 * Created by marcin on 06.03.16.
 */
public class OvalDrawEditorStrategy extends AbstractEditorStrategy implements EditorStrategy {
    public OvalDrawEditorStrategy(Editor editor) {
        super(editor);
        this.mouseAdapter = new OvalDrawMouseAdapter(editor);
    }
}
