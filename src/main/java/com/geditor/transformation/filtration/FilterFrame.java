package com.geditor.transformation.filtration;

import com.geditor.ui.controller.EditorController;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class FilterFrame extends JFrame{
    private final JButton smoothButton = new JButton("Smooth filter");
    private final JButton sobelHorizontalButton = new JButton("Sobel horizontal");
    private final JButton sobelVerticalButton = new JButton("Sobel vertical");
    private final EditorController editorController = EditorController.getInstance();

    public FilterFrame() throws HeadlessException {
        super("Filters");
        Container container = getContentPane();
        container.setLayout(new MigLayout());
        container.add(smoothButton);
        container.add(sobelHorizontalButton);
        container.add(sobelVerticalButton);
        addListeners();
        setSize(new Dimension(800, 600));
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void addListeners() {
        smoothButton.addActionListener(e -> editorController.filter(FilterUtils.getSmoothMask()));
        sobelHorizontalButton.addActionListener(e -> editorController.filter(FilterUtils.getSobelHorizontalMask()));
        sobelVerticalButton.addActionListener(e -> editorController.filter(FilterUtils.getSobelVerticalMask()));
    }
}
