package com.geditor.ui.menu;

import com.geditor.ui.render.RenderController;
import com.geditor.ui.render.enums.Figure;

import javax.swing.*;

public class MenuModel extends JMenuBar {
    private JMenuItem cubeMenuItem;
    private JMenu renderMenu;
    private JMenuItem coneMenuItem;
    private JMenu fileMenu;
    private JMenuItem exitMenuItem;

    private RenderController renderController = new RenderController();

    public MenuModel() {
        fileMenu = new JMenu("File");
        exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(e -> System.exit(0));
        fileMenu.add(exitMenuItem);
        add(fileMenu);

        renderMenu = new JMenu("Render");
        cubeMenuItem = new JMenuItem("Cube");
        cubeMenuItem.addActionListener(e -> renderController.render(Figure.CUBE));
        coneMenuItem = new JMenuItem("Cone");
        coneMenuItem.addActionListener(e -> renderController.render(Figure.CONE));
        renderMenu.add(cubeMenuItem);
        renderMenu.add(coneMenuItem);
        add(renderMenu);
    }

}
