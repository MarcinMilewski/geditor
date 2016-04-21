package com.geditor.ui.transformation.point.brightness;

import com.geditor.commons.Observable;
import com.geditor.commons.Observer;
import com.geditor.ui.controller.EditorController;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class ChangeBrightnessFrame extends JFrame implements Observer {

    private final EditorController editorController = EditorController.getInstance();
    private final JSlider slider = new JSlider();
    private final JLabel label = new JLabel("Brighteness");
    private final JTextField jTextField = new JTextField("0");

    private ChangeListener changeListener = new ChangeListener() {
        private void assistStateChanged() {
            Runnable doAssist = new Runnable() {
                @Override
                public void run() {
                    changeTextValue();
                    editorController.changeBrighteness(slider.getValue());
                }

            };
            SwingUtilities.invokeLater(doAssist);
        }

        @Override
        public void stateChanged(ChangeEvent e) {
            assistStateChanged();
        }
    };

    private DocumentListener documentListener = new DocumentListener() {
        @Override
        public void insertUpdate(DocumentEvent e) {
            assistDateText();
        }

        private void assistDateText() {
            Runnable doAssist = new Runnable() {
                @Override
                public void run() {
                    updateSliderValue();
                }


            };
            SwingUtilities.invokeLater(doAssist);
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
        }
    };

    private void updateSliderValue() {
        slider.setValue(Integer.parseInt(jTextField.getText()));
    }

    private void changeTextValue() {
        jTextField.setText(String.valueOf(slider.getValue()));
    }

    public ChangeBrightnessFrame() throws HeadlessException {
        slider.setValue(0);
        slider.setMinimum(-255);
        slider.setMaximum(255);
        slider.addChangeListener(changeListener);
        jTextField.getDocument().addDocumentListener(documentListener);
        jTextField.setMinimumSize(new Dimension(80, 40));
        jTextField.setPreferredSize(new Dimension(80, 40));
        Container contentPane = getContentPane();
        contentPane.setLayout(new MigLayout("wrap 3"));
        contentPane.add(label);
        contentPane.add(slider);
        contentPane.add(jTextField);
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        pack();
    }

    @Override
    public void update(Observable observable, Object object) {

    }
}
