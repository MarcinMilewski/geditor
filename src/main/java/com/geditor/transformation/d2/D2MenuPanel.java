package com.geditor.transformation.d2;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class D2MenuPanel extends JPanel {
    private final D2TransformationController d2TransformationController = D2TransformationController.getInstance();
    private final JButton resetButton = new JButton("Reset");
    private final JButton translationButton = new JButton("Translation");
    private final JButton rotationButton = new JButton("Rotation");
    private final JButton scaleButton = new JButton("Scale");

    private final JButton submitButton = new JButton("Submit");

    private final JLabel translationLabel = new JLabel("Translation:");
    private final JLabel rotationLabel = new JLabel("Rotation:");
    private final JLabel scaleLabel = new JLabel("Scale:");
    private final JLabel xTranslationLabel = new JLabel("x");
    private final JLabel yTranslationLabel = new JLabel("y");
    private final JLabel alphaLabel = new JLabel("alpha");
    private final JLabel pivotLabel = new JLabel("Pivot");
    private final JLabel xRotationLabel = new JLabel("x");
    private final JLabel yRotationLabel = new JLabel("y");
    private final JLabel kLabel = new JLabel("k");

    private final JTextField xTranslationTextField = new JTextField();
    private final JTextField yTranslationTextField = new JTextField();
    private final JTextField angleRotationTextField = new JTextField("0");
    private final JTextField xRotationTextField = new JTextField("-1");
    private final JTextField yRotationTextField = new JTextField("-1");

    private final JTextField scaleTextField = new JTextField("1");
    private final Dimension textFieldDimension = new Dimension(60, 30);
    public D2MenuPanel() {
        resetButton.addActionListener(e -> {
            d2TransformationController.reset();
        });

        translationButton.addActionListener(e -> {
            double dx = Double.parseDouble((xTranslationTextField.getText()));
            double dy = Double.parseDouble((yTranslationTextField.getText()));
            d2TransformationController.translate(dx, dy);
        });

        rotationButton.addActionListener(e -> {
            double angle = Double.parseDouble(angleRotationTextField.getText());
            double xPivot = Double.parseDouble(xRotationTextField.getText());
            double yPivot = Double.parseDouble(yRotationTextField.getText());
            d2TransformationController.rotate(-Math.toRadians(angle), xPivot, -yPivot);
        });

        xTranslationTextField.setPreferredSize(textFieldDimension);
        yTranslationTextField.setPreferredSize(textFieldDimension);
        angleRotationTextField.setPreferredSize(textFieldDimension);
        scaleTextField.setPreferredSize(textFieldDimension);
        xRotationTextField.setPreferredSize(textFieldDimension);
        yRotationTextField.setPreferredSize(textFieldDimension);


        setLayout(new MigLayout());
        add(resetButton, "wrap");
        add(translationLabel, "wrap");
        add(xTranslationLabel);
        add(xTranslationTextField, "wrap");
        add(yTranslationLabel);
        add(yTranslationTextField, "wrap");
        add(translationButton, "wrap");

        add(rotationLabel, "wrap");
        add(alphaLabel);
        add(angleRotationTextField, "wrap");
        add(pivotLabel, "wrap");
        add(xRotationLabel);
        add(xRotationTextField, "wrap");
        add(yRotationLabel);
        add(yRotationTextField, "wrap");
        add(rotationButton, "wrap");

        add(scaleLabel, "wrap");
        add(kLabel);
        add(scaleTextField, "wrap");
        add(submitButton, "wrap");
    }
}