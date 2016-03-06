package com.geditor.mode.draw;

import com.geditor.Editor;
import com.geditor.mode.AbstractMode;
import com.geditor.mode.Mode;
import com.geditor.mode.draw.mouse.RectangleDrawMouseAdapter;

/**
 * Created by marcin on 06.03.16.
 */
public class RectangleDrawMode extends AbstractMode implements Mode {
    public RectangleDrawMode(Editor editor) {
        super(editor);
        this.mouseAdapter = new RectangleDrawMouseAdapter(editor);
    }
}
