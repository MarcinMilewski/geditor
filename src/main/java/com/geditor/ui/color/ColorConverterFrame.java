package com.geditor.ui.color;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ColorConverterFrame extends JFrame implements ActionListener {

    public ColorConverterFrame() throws HeadlessException {
        Container content = getContentPane();
        content.setLayout(new BorderLayout());
        content.add(new ColorConverterRGBSliderPanel(), BorderLayout.EAST);
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
