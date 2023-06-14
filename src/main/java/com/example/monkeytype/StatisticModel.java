package com.example.monkeytype;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;

public class StatisticModel {

    private final ObservableList<XYChart.Data<Float, Float>> dataPoints;

    public StatisticModel() {
        this.dataPoints = FXCollections.observableArrayList();
    }

    public ObservableList<XYChart.Data<Float, Float>> getDataPoints() {
        return dataPoints;
    }

    public void addDataPoint(float wordsPerMinute, float accuracy) {
        this.dataPoints.add(new XYChart.Data<>(wordsPerMinute, accuracy));
    }
}