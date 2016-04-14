package com.geditor.ui.color;

import com.geditor.commons.Observable;
import com.geditor.commons.Observer;
import com.google.common.collect.Lists;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.List;

public class ColorConverterRGBSliderPanel extends JPanel implements Observable {

    private List<Observer> observers = Lists.newArrayList();
    private JSlider redSlider = new JSlider();
    private JSlider greenSlider = new JSlider();
    private JSlider blueSlider = new JSlider();
    private JTextField redJTextField = new JTextField();
    private JTextField greenJTextField = new JTextField();
    private JTextField blueJTextField = new JTextField();

    private ChangeListener changeListener = new ChangeListener() {
        @Override
        public void stateChanged(ChangeEvent e) {
            updateTextValues();
            notifyObservers();
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
                    updateSlidersValues();
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

    public ColorConverterRGBSliderPanel() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        add(new JLabel("RGB"));
        addSlider(redSlider, new JLabel("Red"), redJTextField);
        addSlider(greenSlider, new JLabel("Green"), greenJTextField);
        addSlider(blueSlider, new JLabel("Blue"), blueJTextField);
    }

    private void addSlider(JSlider slider, JLabel label, JTextField JTextField) {
        slider.setValue(0);
        slider.setMinimum(0);
        slider.setMaximum(255);
        slider.addChangeListener(changeListener);
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.add(label);
        panel.add(slider);
        JTextField.setText("0");
        JTextField.setMinimumSize(new Dimension(30, 30));
        JTextField.setPreferredSize(new Dimension(30, 30));
        JTextField.getDocument().addDocumentListener(documentListener);
        panel.add(JTextField);
        add(panel);
    }

    private void updateSlidersValues() {
        redSlider.setValue(Integer.parseInt(redJTextField.getText()));
        greenSlider.setValue(Integer.parseInt(greenJTextField.getText()));
        blueSlider.setValue(Integer.parseInt(blueJTextField.getText()));
    }

    private void updateTextValues() {
        redJTextField.setText(String.valueOf(redSlider.getValue()));
        greenJTextField.setText(String.valueOf(greenSlider.getValue()));
        blueJTextField.setText(String.valueOf(blueSlider.getValue()));
    }

    public void set(RGBStructure rgbStructure) {
        setSliderValues(rgbStructure);
        setJTextFieldValues(rgbStructure);
    }

    private void setSliderValues(RGBStructure rgbStructure) {
        redSlider.setValue(rgbStructure.r);
        greenSlider.setValue(rgbStructure.g);
        blueSlider.setValue(rgbStructure.b);
    }

    private void setJTextFieldValues(RGBStructure rgbStructure) {
        redJTextField.setText(String.valueOf(rgbStructure.r));
        greenJTextField.setText(String.valueOf(rgbStructure.g));
        blueJTextField.setText(String.valueOf(rgbStructure.b));
    }

    public RGBStructure getValues() {
        return new RGBStructure(redSlider.getValue(), greenSlider.getValue(), blueSlider.getValue());
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        observers.forEach(e-> e.update(this, getValues()));
    }
}
