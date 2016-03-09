package com.geditor.mode.draw;

import com.geditor.Editor;
import com.geditor.mode.EditorStrategy;
import com.geditor.mode.AbstractEditorStrategy;
import com.geditor.mode.draw.mouse.PointDrawMouseAdapter;

/**
 * Created by marcin on 06.03.16.
 */
public class PointDrawEditorStrategy extends AbstractEditorStrategy implements EditorStrategy {

    public PointDrawEditorStrategy(Editor editor) {
        super(editor);
        this.mouseAdapter = new PointDrawMouseAdapter(editor);
    }

}
