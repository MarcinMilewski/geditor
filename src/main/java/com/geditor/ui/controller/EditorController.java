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
        int currentBrightness = Editor.getInstance().getBrightness();
        editor.setImage(PointTransformations.changeBrighteness(editor.getImage(), value - currentBrightness));
        editor.setBrightness(value);
        editor.repaint();
    }

    public void addRed(int value) {
        int currentRed = Editor.getInstance().getRedShift();
        editor.setImage(PointTransformations.addRed(editor.getImage(), value - currentRed));
        editor.setRedShift(value);
        editor.repaint();
    }

    public void addGreen(int value) {
        int currentGreen = Editor.getInstance().getGreenShift();
        editor.setImage(PointTransformations.addGreen(editor.getImage(), value - currentGreen));
        editor.setGreenShift(value);
        editor.repaint();
    }

    public void addBlue(int value) {
        int currentBlue = Editor.getInstance().getBlueShift();
        editor.setImage(PointTransformations.addBlue(editor.getImage(), value - currentBlue));
        editor.setBlueShift(value);
        editor.repaint();
    }

    public void multiply(float value) {
        editor.setImage(PointTransformations.multiply(editor.getImage(), value));
        editor.repaint();
    }

}
