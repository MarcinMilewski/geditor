package com.geditor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by marcin on 23.02.16.
 */
public class Main {
    JButton clearButton, lineButton, rectangleButton, ovalButton;
    DrawArea drawArea;
    ActionListener actionListener = new ActionListener() {

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == clearButton) {
                drawArea.clear();
            } else if (e.getSource() == lineButton) {
                drawArea.black();
            } else if (e.getSource() == rectangleButton) {
                drawArea.blue();
            } else if (e.getSource() == ovalButton) {
                drawArea.green();
            }
        }
    };

    public static void main(String[] args) {
        new Main().show();
    }

    public void show() {
        JFrame frame = new JFrame("Graphic editor");
        Container content = frame.getContentPane();
        content.setLayout(new BorderLayout());
        drawArea = new DrawArea();

        content.add(drawArea, BorderLayout.CENTER);

        JPanel controls = new JPanel();

        clearButton = new JButton("Line");
        clearButton.addActionListener(actionListener);
        lineButton = new JButton("Rectangle");
        lineButton.addActionListener(actionListener);
        rectangleButton = new JButton("Oval");
        rectangleButton.addActionListener(actionListener);
        ovalButton = new JButton("Clear");

        controls.add(ovalButton);
        controls.add(rectangleButton);
        controls.add(lineButton);
        controls.add(clearButton);

        content.add(controls, BorderLayout.NORTH);

        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }
}
