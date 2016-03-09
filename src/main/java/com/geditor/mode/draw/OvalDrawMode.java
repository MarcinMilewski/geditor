package com.geditor.mode.draw;

import com.geditor.Editor;
import com.geditor.mode.AbstractMode;
import com.geditor.mode.Mode;
import com.geditor.mode.draw.mouse.OvalDrawMouseAdapter;

/**
 * Created by marcin on 06.03.16.
 */
public class OvalDrawMode extends AbstractMode implements Mode {
    public OvalDrawMode(Editor editor) {
        super(editor);
        this.mouseAdapter = new OvalDrawMouseAdapter(editor);
    }
}
