package com.geditor.ui.color;

import com.geditor.commons.Observable;
import com.geditor.commons.Observer;

import javax.swing.*;
import java.awt.*;

public class ColorConverterFrame extends JFrame implements Observer {

    private ColorConverterRGBSliderPanel colorConverterRGBSliderPanel = new ColorConverterRGBSliderPanel();
    private ColorConverterCMYKSliderPanel colorConverterCMYKSliderPanel = new ColorConverterCMYKSliderPanel();
    private ColorImagePanel colorImagePanel = new ColorImagePanel(new RGBStructure(0,0,0));
    private final ColorConverterController colorConverterController = new ColorConverterController();


    public ColorConverterFrame() throws HeadlessException {
        addAsObserver();
        Container content = getContentPane();
        content.setLayout(new BorderLayout());
        content.add(colorConverterCMYKSliderPanel, BorderLayout.EAST);
        content.add(colorConverterRGBSliderPanel, BorderLayout.WEST);
        content.add(colorImagePanel, BorderLayout.CENTER);
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
//        pack();
    }

    private void addAsObserver() {
        colorConverterCMYKSliderPanel.addObserver(this);
        colorConverterRGBSliderPanel.addObserver(this);
    }

    @Override
    public void update(Observable observable, Object object) {
        if (object instanceof RGBStructure) {
            System.out.print("RGB");
            colorImagePanel.draw((RGBStructure) object);
        }
        else if (object instanceof CMYKStructure) {
            System.out.print("CMYK");
            colorImagePanel.repaint();
            repaint();
        }
        else {
            throw new IllegalArgumentException();
        }
    }
}
