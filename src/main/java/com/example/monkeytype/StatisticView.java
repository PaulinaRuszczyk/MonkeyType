package com.example.monkeytype;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.BorderPane;

public class StatisticView extends BorderPane {

    private final LineChart<Number, Number> lineChart;

    public StatisticView() {
        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("Time");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Value");

        lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Words Per Minute and Accuracy Over Time");
        lineChart.setCreateSymbols(false);

        setCenter(lineChart);
    }

    public void addSeries(XYChart.Series<Number, Number> series) {
        lineChart.getData().add(series);
    }
}