package com.geditor.mode.draw;

import com.geditor.Editor;
import com.geditor.mode.AbstractEditorStrategy;
import com.geditor.mode.EditorStrategy;
import com.geditor.mode.draw.mouse.LineDrawMouseAdapter;

/**
 * Created by marcin on 06.03.16.
 */
public class LineDrawEditorStrategy extends AbstractEditorStrategy implements EditorStrategy {
    public LineDrawEditorStrategy(Editor editor) {
        super(editor);
        this.mouseAdapter = new LineDrawMouseAdapter(editor);
    }

}
