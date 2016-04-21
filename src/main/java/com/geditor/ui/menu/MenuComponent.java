package com.geditor.ui.menu;

import com.geditor.ui.color.ColorConverterFrame;
import com.geditor.ui.controller.EditorController;
import com.geditor.ui.render.RenderController;
import com.geditor.ui.render.enums.Figure;
import com.geditor.ui.transformation.point.brightness.PointTransformationsFrame;

import javax.swing.*;

public class MenuComponent extends JMenuBar {
    private final JMenuItem cubeMenuItem;
    private final JMenu renderMenu;
    private final JMenuItem coneMenuItem;
    private final JMenu fileMenu;
    private final JMenuItem exitMenuItem;
    private final JMenu convertMenu;
    private final JMenuItem rgbCmykMenuItem;
    private final JMenu transformationMenu;
    private final JMenuItem brightness;
    private RenderController renderController = new RenderController();
    private EditorController editorController = EditorController.getInstance();

    public MenuComponent() {
        fileMenu = new JMenu("File");
        exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(e -> System.exit(0));
        fileMenu.add(exitMenuItem);
        add(fileMenu);

        convertMenu = new JMenu("Convert");
        rgbCmykMenuItem = new JMenuItem("RGB-CMYK");
        rgbCmykMenuItem.addActionListener(e -> new ColorConverterFrame());
        convertMenu.add(rgbCmykMenuItem);
        add(convertMenu);

        renderMenu = new JMenu("Render");
        cubeMenuItem = new JMenuItem("Cube");
        cubeMenuItem.addActionListener(e -> renderController.render(Figure.CUBE));
        coneMenuItem = new JMenuItem("Cone");
        coneMenuItem.addActionListener(e -> renderController.render(Figure.CONE));
        renderMenu.add(cubeMenuItem);
        renderMenu.add(coneMenuItem);
        add(renderMenu);

        transformationMenu = new JMenu("Transformation");
        brightness = new JMenuItem("Point transformations");
        brightness.addActionListener(e -> {
            new PointTransformationsFrame();
        });

        transformationMenu.add(brightness);
        add(transformationMenu);
    }

}
