package com.geditor.transformation.binarization;

import com.geditor.ui.controller.EditorController;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class BinarizationFrame extends JFrame {
    private final EditorController editorController = EditorController.getInstance();
    private final JSlider manualSlider = new JSlider();
    private final JSlider percentSlider = new JSlider();

    private final JTextField manualTextField = new JTextField("0");
    private final JTextField percentTextField = new JTextField("0");

    private final JLabel manualLabel = new JLabel("Manual");
    private final JLabel percentBlackSelectionLabel = new JLabel("Percent black selection");
    public BinarizationFrame() throws HeadlessException {
        super("Binarizaiton");
        Container container = getContentPane();
        container.setLayout(new MigLayout("wrap 3"));

        manualSlider.setValue(0);
        manualSlider.setMaximum(255);
        manualSlider.setMinimum(0);
        container.add(manualLabel);
        container.add(manualSlider);
        manualTextField.setEnabled(false);
        manualTextField.setMinimumSize(new Dimension(60, 40));
        manualTextField.setPreferredSize(new Dimension(60, 40));

        container.add(manualTextField, "wrap");

        percentSlider.setValue(0);
        percentSlider.setMinimum(0);
        percentSlider.setMaximum(100);
        container.add(percentBlackSelectionLabel);
        container.add(percentSlider);
        percentTextField.setEnabled(false);
        percentTextField.setMinimumSize(new Dimension(60, 40));
        percentTextField.setPreferredSize(new Dimension(60, 40));
        container.add(percentTextField, "wrap");

        addSliderEvents();
        addTextFieldEvents();
        addButtonEvents();
        setSize(new Dimension(800, 600));
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
    }


    private void addButtonEvents() {

    }

    private void addTextFieldEvents() {

    }

    private void addSliderEvents() {
        manualSlider.addChangeListener(new ChangeListener() {
            private void assistStateChanged() {
                Runnable doAssist = new Runnable() {
                    @Override
                    public void run() {
                        manualTextField.setText(String.valueOf(manualSlider.getValue()));
                        if (!manualSlider.getValueIsAdjusting()) {
                            editorController.manualBinarization(manualSlider.getValue());
                        }
                    }

                };
                SwingUtilities.invokeLater(doAssist);
            }

            @Override
            public void stateChanged(ChangeEvent e) {
                assistStateChanged();
            }

        });

        percentSlider.addChangeListener(new ChangeListener() {
            private void assistStateChanged() {
                Runnable doAssist = new Runnable() {
                    @Override
                    public void run() {
                        percentTextField.setText(String.valueOf(percentSlider.getValue()));
                        if (!percentSlider.getValueIsAdjusting()) {
                            editorController.percentBlackSelectionBinarization(percentSlider.getValue());
                        }
                    }

                };
                SwingUtilities.invokeLater(doAssist);
            }

            @Override
            public void stateChanged(ChangeEvent e) {
                assistStateChanged();
            }

        });
    }
}
