package com.geditor.ui.color;

import javax.swing.*;
import java.awt.*;

public class ColorConverterRGBSliderPanel extends JPanel{

    private JSlider redSlider = new JSlider();
    private JSlider greenSlider = new JSlider();
    private JSlider blueSlider = new JSlider();
    private TextField redTextField = new TextField();
    private TextField greenTextField = new TextField();
    private TextField blueTextField = new TextField();

    public ColorConverterRGBSliderPanel() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        addSlider(redSlider, new Label("red"), redTextField);
        addSlider(greenSlider, new Label("green"), greenTextField);
        addSlider(blueSlider, new Label("blue"), blueTextField);
    }

    private void addSlider(JSlider slider, Label label, TextField textField) {
        slider.setValue(0);
        slider.setMinimum(0);
        slider.setMaximum(255);

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.add(label);
        panel.add(slider);
        textField.setText("0");
        textField.setMinimumSize(new Dimension(30,30));
        textField.setPreferredSize(new Dimension(30,30));
        panel.add(textField);
        add(panel);
    }

}
