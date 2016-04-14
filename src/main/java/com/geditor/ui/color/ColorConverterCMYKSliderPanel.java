package com.geditor.ui.color;

import com.geditor.commons.Observable;
import com.geditor.commons.Observer;
import com.google.common.collect.Lists;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.List;

public class ColorConverterCMYKSliderPanel extends JPanel implements Observable {
    private List<Observer> observers = Lists.newArrayList();
    private JSlider cyanSlider = new JSlider();
    private JSlider magentaSlider = new JSlider();
    private JSlider yellowSlider = new JSlider();
    private JSlider blackSlider = new JSlider();
    private TextField cyanTextField = new TextField();
    private TextField magentaTextField = new TextField();
    private TextField yellowTextField = new TextField();
    private TextField blackTextField = new TextField();

    private ChangeListener changeListener = new ChangeListener() {
        @Override
        public void stateChanged(ChangeEvent e) {
            updateTextValues();
        }
    };

    private void updateTextValues() {
        cyanTextField.setText(String.valueOf((float)cyanSlider.getValue() / (float)1000));
        magentaTextField.setText(String.valueOf((float)magentaSlider.getValue() / (float)1000));
        yellowTextField.setText(String.valueOf((float)yellowSlider.getValue() / (float)1000));
        blackTextField.setText(String.valueOf((float)blackSlider.getValue() / (float)1000));
    }

    public ColorConverterCMYKSliderPanel() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        add(new Label("CMYK"));
        addSlider(cyanSlider, new Label("Cyan"), cyanTextField);
        addSlider(magentaSlider, new Label("Magenta"), magentaTextField);
        addSlider(yellowSlider, new Label("Yellow"), yellowTextField);
        addSlider(blackSlider, new Label("Black"), blackTextField);

    }

    private void addSlider(JSlider slider, Label label, TextField textField) {
        slider.setValue(0);
        slider.setMinimum(0);
        slider.setMaximum(1000);
        slider.addChangeListener(changeListener);
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.add(label);
        panel.add(slider);
        textField.setText("0");
        textField.setMinimumSize(new Dimension(50,30));
        textField.setPreferredSize(new Dimension(50,30));
        panel.add(textField);
        add(panel);
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

    }

}
