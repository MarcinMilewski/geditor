package com.geditor.ui.controller;

import lombok.Getter;

public class EditorController {
    @Getter
    private static EditorController instance = new EditorController();
    private EditorController() {};

}
