package com.geditor.ui.controller;

import com.geditor.transformation.point.PointTransformations;
import com.geditor.ui.editor.Editor;

public class EditorController {
    private static final Editor editor = Editor.getInstance();
    private static EditorController instance = new EditorController();

    private EditorController() {
    };

    public static EditorController getInstance() {
        return instance;
    }

    public void changeBrighteness(int value) {
        editor.setImage(PointTransformations.ChangeBrighteness(editor.getImage(), value));
        editor.setBrightness(value);
        editor.repaint();
    }

}
