package com.geditor.transformation.filtration;

import com.geditor.ui.controller.EditorController;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class FilterFrame extends JFrame{
    private final JButton smoothButton = new JButton("Smooth filter");
    private final JButton sobelHorizontalButton = new JButton("Sobel horizontal");
    private final JButton sobelVerticalButton = new JButton("Sobel vertical");
    private final JButton medianButton = new JButton("Median filter");
    private final JLabel matrixSize = new JLabel("Matrix size:");
    private final JTextField medianTextField = new JTextField("3");
    private final EditorController editorController = EditorController.getInstance();
    private final JButton highPassFilter1 = new JButton("High pass filter 1");
    private final JButton highPassFilter2 = new JButton("High pass filter 2");

    public FilterFrame() throws HeadlessException {
        super("Filters");
        Container container = getContentPane();
        container.setLayout(new MigLayout());
        container.add(smoothButton, "wrap");
        container.add(sobelHorizontalButton);
        container.add(sobelVerticalButton, "wrap");
        container.add(medianButton);
        container.add(matrixSize);
        medianTextField.setMinimumSize(new Dimension(50, 40));
        medianTextField.setPreferredSize(new Dimension(50, 40));
        container.add(medianTextField, "wrap");
        container.add(highPassFilter1);
        container.add(highPassFilter2);
        addListeners();
        setSize(new Dimension(800, 600));
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void addListeners() {
        smoothButton.addActionListener(e -> editorController.filter(FilterUtils.getSmoothMask()));
        sobelHorizontalButton.addActionListener(e -> editorController.filter(FilterUtils.getSobelHorizontalMask()));
        sobelVerticalButton.addActionListener(e -> editorController.filter(FilterUtils.getSobelVerticalMask()));
        medianButton.addActionListener(e -> editorController.medianFilter(Integer.parseInt(medianTextField.getText())));
        highPassFilter1.addActionListener(e -> editorController.filter(FilterUtils.getHighPass1FilterMask()));
        highPassFilter2.addActionListener(e -> editorController.filter(FilterUtils.getHighPass2FilterMask()));
    }
}
