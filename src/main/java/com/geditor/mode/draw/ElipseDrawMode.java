package com.geditor.mode.draw;

import com.geditor.Editor;
import com.geditor.mode.AbstractMode;
import com.geditor.mode.Mode;
import com.geditor.mode.draw.mouse.ElipseDrawMouseAdapter;

/**
 * Created by marcin on 06.03.16.
 */
public class ElipseDrawMode extends AbstractMode implements Mode {
    public ElipseDrawMode(Editor editor) {
        super(editor);
        mouseAdapter = new ElipseDrawMouseAdapter(editor);
    }
}
