package com.geditor.mode.draw;

import com.geditor.Editor;
import com.geditor.mode.AbstractMode;
import com.geditor.mode.Mode;
import com.geditor.mode.draw.mouse.PolygonDrawMouseAdapter;

/**
 * Created by marcin on 09.03.16.
 */
public class PolygonDrawMode extends AbstractMode implements Mode {
    public PolygonDrawMode(Editor editor) {
        super(editor);
        this.mouseAdapter = new PolygonDrawMouseAdapter(editor);
    }
}
