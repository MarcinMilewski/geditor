package com.geditor.mode;

import com.geditor.Editor;

import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;

/**
 * Created by marcin on 06.03.16.
 */
public abstract class AbstractMode implements Mode {
    protected final Editor editor;
    protected MouseAdapter mouseAdapter;
    protected KeyAdapter keyAdapter;

    public AbstractMode(Editor editor) {
        this.editor = editor;
    }

}
