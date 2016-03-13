package com.geditor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by marcin on 23.02.16.
 */
public class Main {
    private JButton clearButton, lineButton, rectangleButton, ovalButton, pointButton, polygonButton, editButton;
    private Editor editor;
    private Container content;
    private JPanel root;
    private JPanel editPanel;
    private TextField scale;

    ActionListener actionListener = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == clearButton) {
                editor.clearBuffer();
                editor.clearAll();
            } else if(e.getSource() == pointButton) {
                editor.setPointMode();
            } else if (e.getSource() == lineButton) {
                editor.setLineMode();
            } else if (e.getSource() == rectangleButton) {
                editor.setRectangleMode();
            } else if (e.getSource() == ovalButton) {
                editor.setOvalMode();
            } else if (e.getSource() == editButton) {
                editor.setEditMode();
            }
        }
    };

    private void createEditButtons() {
        scale = new TextField("scale");
        editPanel = new JPanel();
        editPanel.setSize(200, 200);
        editPanel.add(scale);
        editPanel.setVisible(true);
        editPanel.add(new JButton("Submit"));
        content.add(editPanel, BorderLayout.EAST);
        content.revalidate();
    }


    public static void main(String[] args) {
        new Main().show();
    }

    public void show() {
        JFrame frame = new JFrame("Graphic editor");
        frame.setFocusable(true);
        content = frame.getContentPane();
        content.setLayout(new BorderLayout());
        editor = new Editor();

        content.add(editor, BorderLayout.CENTER);

        root = new JPanel();

        createButtons();

        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);


    }

    private void createButtons() {
        clearButton = new JButton("Clear");
        clearButton.addActionListener(actionListener);
        lineButton = new JButton("Line");
        lineButton.addActionListener(actionListener);
        rectangleButton = new JButton("Rectangle");
        rectangleButton.addActionListener(actionListener);
        ovalButton = new JButton("Oval");
        ovalButton.addActionListener(actionListener);
        pointButton = new JButton("Pencil");
        pointButton.addActionListener(actionListener);
        editButton = new JButton("Edit");
        editButton.addActionListener(actionListener);

        root.add(pointButton);
        root.add(ovalButton);
        root.add(rectangleButton);
        root.add(lineButton);
        root.add(clearButton);
        root.add(editButton);

        content.add(root, BorderLayout.NORTH);
    }
}
