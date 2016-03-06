package com.geditor.mode.draw;

import com.geditor.Editor;
import com.geditor.mode.AbstractMode;
import com.geditor.mode.Mode;
import com.geditor.mode.draw.mouse.LineDrawMouseAdapter;

/**
 * Created by marcin on 06.03.16.
 */
public class LineDrawMode extends AbstractMode implements Mode{
    public LineDrawMode(Editor editor) {
        super(editor);
        this.mouseAdapter = new LineDrawMouseAdapter(editor);
    }

    @Override
    public void activate() {
        super.activate();
    }

    @Override
    public void deactivate() {
        super.deactivate();
    }
}
