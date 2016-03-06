package com.geditor.mode.draw;

import com.geditor.Editor;
import com.geditor.mode.Mode;
import com.geditor.mode.AbstractMode;
import com.geditor.mode.draw.mouse.PointDrawMouseAdapter;

/**
 * Created by marcin on 06.03.16.
 */
public class PointDrawMode extends AbstractMode implements Mode{

    public PointDrawMode(Editor editor) {
        super(editor);
        this.mouseAdapter = new PointDrawMouseAdapter(editor);
    }

}
