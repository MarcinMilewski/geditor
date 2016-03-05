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
    Editor editor;
    ActionListener actionListener = new ActionListener() {

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == clearButton) {
                editor.clear();
            } else if (e.getSource() == lineButton) {

            } else if (e.getSource() == rectangleButton) {
            } else if (e.getSource() == ovalButton) {
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
        editor = new Editor();

        content.add(editor, BorderLayout.CENTER);

        JPanel controls = new JPanel();

        clearButton = new JButton("Clear");
        clearButton.addActionListener(actionListener);
        lineButton = new JButton("Line");
        lineButton.addActionListener(actionListener);
        rectangleButton = new JButton("Rectangle");
        rectangleButton.addActionListener(actionListener);
        ovalButton = new JButton("Oval");

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
