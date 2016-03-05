package com.geditor;

import com.geditor.util.Mode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by marcin on 23.02.16.
 */
public class Main {
    JButton clearButton, lineButton, rectangleButton, ovalButton, pointButton;
    Editor editor;

    ActionListener actionListener = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == clearButton) {
                editor.clear();
            } else if(e.getSource() == pointButton) {
                editor.setMode(Mode.POINT);
            } else if (e.getSource() == lineButton) {
                editor.setMode(Mode.LINE);
            } else if (e.getSource() == rectangleButton) {
                editor.setMode(Mode.RECTANGLE);
            } else if (e.getSource() == ovalButton) {
                editor.setMode(Mode.OVAL);
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

        createButtons(content, controls);

        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

    private void createButtons(Container content, JPanel controls) {
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
    }
}
