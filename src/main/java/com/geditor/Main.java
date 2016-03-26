package com.geditor;

import com.geditor.mode.draw.strategy.LineDrawStrategy;
import com.geditor.mode.draw.strategy.OvalDrawStrategy;
import com.geditor.mode.draw.strategy.PointDrawStrategy;
import com.geditor.mode.draw.strategy.RectangleDrawStrategy;
import com.geditor.mode.edit.strategy.FigureEditStrategy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by marcin on 23.02.16.
 */
public class Main {
    private JButton clearButton, lineButton, rectangleButton,
            ovalButton, pointButton, editButton
            ,exportButton, importButton;
    private Editor editor;
    private Container content;
    private JPanel root;
    private JPanel editPanel;

    ActionListener actionListener = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == clearButton) {
                editor.clearBuffer();
                editor.clearAll();
            } else if(e.getSource() == pointButton) {
                editor.setStrategy(new PointDrawStrategy(editor));
            } else if (e.getSource() == lineButton) {
                editor.setStrategy(new LineDrawStrategy(editor));
            } else if (e.getSource() == rectangleButton) {
                editor.setStrategy(new RectangleDrawStrategy(editor));
            } else if (e.getSource() == ovalButton) {
                editor.setStrategy(new OvalDrawStrategy(editor));
            } else if (e.getSource() == editButton) {
                editor.setStrategy(new FigureEditStrategy(editor));
            } else if (e.getSource() == exportButton) {
                createSaveFileChooserFrame();
            } else if (e.getSource() == importButton) {
                createOpenFileChooserFrame();
            }
        }
    };

    private void createSaveFileChooserFrame() {
        JFrame frame = new JFrame("Save");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(new FileExportPanel(editor));

        frame.pack();
        frame.setVisible(true);
    }

    private void createOpenFileChooserFrame() {
        JFrame frame = new JFrame("Open");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(new FileImportPanel(editor));

        frame.pack();
        frame.setVisible(true);
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

        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
//        frame.pack();
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
        exportButton = new JButton("Export");
        exportButton.addActionListener(actionListener);
        importButton = new JButton("Import");
        importButton.addActionListener(actionListener);

        root.add(pointButton);
        root.add(ovalButton);
        root.add(rectangleButton);
        root.add(lineButton);
        root.add(clearButton);
        root.add(editButton);
        root.add(importButton);
        root.add(exportButton);


        content.add(root, BorderLayout.NORTH);
    }
}
