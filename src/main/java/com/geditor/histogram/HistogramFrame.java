package com.geditor.histogram;

import com.geditor.ui.controller.EditorController;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.GridPane;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

import static javafx.scene.chart.XYChart.Data;
import static javafx.scene.chart.XYChart.Series;

public class HistogramFrame extends JFrame{

    private final EditorController editorController = EditorController.getInstance();
    private final HistogramModel histogramModel = editorController.createHistogram();
    private final JButton stretchButton = new JButton("Stretch histogram");
    private final JButton equalizeButton = new JButton("Equalize histogram");
    private JFXPanel fxPanel = new JFXPanel();

    public HistogramFrame() throws HeadlessException {
        initSwingComponents();
        initFxComponents();
        setVisible(true);
    }

    private void initSwingComponents() {
        stretchButton.addActionListener(e -> editorController.stretchHistogram());
        equalizeButton.addActionListener(e -> editorController.equalizeHistogram());
        Container contentPane = getContentPane();
        contentPane.setLayout(new MigLayout("wrap 3"));
        contentPane.add(stretchButton);
        contentPane.add(equalizeButton, "wrap");
        contentPane.add(fxPanel, "span");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(800, 600);
    }

    private void initFxComponents() {
        Platform.runLater(() -> {
            GridPane gridPane = new GridPane();
            Scene scene = new Scene(gridPane);
            BarChart<String, Number> redChannelChart = createRedChannelChart();
            BarChart<String, Number> greenChannelChart = createGreenChannelChart();
            BarChart<String, Number> blueChannelChart = createBlueChannelChart();
            gridPane.add(redChannelChart, 0, 0);
            gridPane.add(greenChannelChart, 0, 1);
            gridPane.add(blueChannelChart, 0, 2);
            fxPanel.setScene(scene);
        });
    }

    private BarChart<String, Number> createRedChannelChart() {
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();

        final BarChart<String,Number> bc =
                new BarChart<String,Number>(xAxis,yAxis);

        bc.setTitle("Red");
        bc.setBarGap(0);
        bc.setCategoryGap(1);
        xAxis.setLabel("Value");
        yAxis.setLabel("Number");
        XYChart.Series redSeries = getRedSeries();
        bc.getData().add(redSeries);
        return bc;
    }

    private Series getRedSeries() {
        int[] redChannel = histogramModel.getRedChannel();
        Series redSeries = new Series();
        redSeries.setName("Red");
        for (int i = 0; i < redChannel.length; ++i) {
            redSeries.getData().add(new Data<String,Number>(String.valueOf(i), redChannel[i]));
        }
        return redSeries;
    }

    private BarChart<String, Number> createGreenChannelChart() {
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String,Number> bc =
                new BarChart<String,Number>(xAxis,yAxis);
        bc.setBarGap(0);
        bc.setCategoryGap(1);
        bc.setTitle("Green");
        xAxis.setLabel("Value");
        yAxis.setLabel("Number");
        XYChart.Series redSeries = getGreenSeries();
        bc.getData().add(redSeries);
        return bc;
    }

    private Series getGreenSeries() {
        int[] greenChannel = histogramModel.getGreenChannel();
        Series greenSeries = new Series();
        greenSeries.setName("Green");
        for (int i = 0; i < greenChannel.length; ++i) {
            greenSeries.getData().add(new Data<String,Number>(String.valueOf(i), greenChannel[i]));
        }
        return greenSeries;
    }

    private BarChart<String, Number> createBlueChannelChart() {
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();

        final BarChart<String,Number> bc =
                new BarChart<String,Number>(xAxis,yAxis);
        bc.setBarGap(0);
        bc.setCategoryGap(1);
        bc.setTitle("Blue");
        xAxis.setLabel("Value");
        yAxis.setLabel("Number");
        XYChart.Series redSeries = getBlueSeries();
        bc.getData().add(redSeries);
        return bc;
    }

    private Series getBlueSeries() {
        int[] blueChannel = histogramModel.getBlueChannel();
        Series blueSeries = new Series();
        blueSeries.setName("Blue");
        for (int i = 0; i < blueChannel.length; ++i) {
            blueSeries.getData().add(new Data<String,Number>(String.valueOf(i), blueChannel[i]));
        }
        return blueSeries;
    }
}
