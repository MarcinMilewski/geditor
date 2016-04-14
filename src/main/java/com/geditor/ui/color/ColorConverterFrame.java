package com.geditor.ui.color;

import com.geditor.commons.Observable;
import com.geditor.commons.Observer;

import javax.swing.*;
import java.awt.*;

public class ColorConverterFrame extends JFrame implements Observer {

    private final ColorConverterController colorConverterController;

    public ColorConverterFrame() throws HeadlessException {
        colorConverterController = new ColorConverterController();
        Container content = getContentPane();
        content.setLayout(new BorderLayout());
        content.add(new ColorConverterRGBSliderPanel(), BorderLayout.EAST);
        content.add(new ColorConverterCMYKSliderPanel(), BorderLayout.WEST);
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        pack();
    }

    @Override
    public void update(Observable observable, Object object) {
        if (object instanceof RGBStructure) {

        }
        else if (object instanceof CMYKStructure) {

        }
        else {
            throw new IllegalArgumentException();
        }
    }
}
