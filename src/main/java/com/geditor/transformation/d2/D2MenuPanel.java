package com.geditor.transformation.d2;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class D2MenuPanel extends JPanel {
    private final D2TransformationController d2TransformationController = D2TransformationController.getInstance();
    private final JButton squareButton = new JButton("Reset");
    private final JButton translationButton = new JButton("Translation");
    private final JButton rotationButton = new JButton("Rotation");
    private final JButton scaleButton = new JButton("Scale");

    private final JButton submitButton = new JButton("Submit");

    private final JLabel translationLabel = new JLabel("Translation:");
    private final JLabel rotationLabel = new JLabel("Rotation:");
    private final JLabel scaleLabel = new JLabel("Scale:");
    private final JLabel xLabel = new JLabel("x");
    private final JLabel yLabel = new JLabel("y");
    private final JLabel alphaLabel = new JLabel("alpha");
    private final JLabel kLabel = new JLabel("k");

    private final JTextField xTranslationTextField = new JTextField();
    private final JTextField yTranslationTextField = new JTextField();
    private final JTextField xRotationTextField = new JTextField();
    private final JTextField yRotationTextField = new JTextField();
    private final JTextField alphaRotationTextField = new JTextField("0");
    private final JTextField scaleTextField = new JTextField("1");
    private final Dimension textFieldDimension = new Dimension(60, 30);
    public D2MenuPanel() {
        translationButton.addActionListener(e -> {
            double dx = Double.parseDouble((xTranslationTextField.getText()));
            double dy = Double.parseDouble((yTranslationTextField.getText()));
            d2TransformationController.translate(dx, dy);
        });


        xTranslationTextField.setPreferredSize(textFieldDimension);
        yTranslationTextField.setPreferredSize(textFieldDimension);
        xRotationTextField.setPreferredSize(textFieldDimension);
        yRotationTextField.setPreferredSize(textFieldDimension);
        alphaRotationTextField.setPreferredSize(textFieldDimension);
        scaleTextField.setPreferredSize(textFieldDimension);


        setLayout(new MigLayout());
        add(squareButton, "wrap");
        add(translationLabel, "wrap");
        add(xLabel);
        add(xTranslationTextField, "wrap");
        add(yLabel);
        add(yTranslationTextField, "wrap");
        add(translationButton, "wrap");

        add(rotationLabel, "wrap");
//        add(xLabel);
        add(xRotationTextField, "wrap");
//        add(yLabel);
        add(yRotationTextField, "wrap");
        add(alphaLabel);
        add(alphaRotationTextField, "wrap");

        add(scaleLabel, "wrap");
        add(kLabel);
        add(scaleTextField, "wrap");
        add(submitButton, "wrap");
    }
}