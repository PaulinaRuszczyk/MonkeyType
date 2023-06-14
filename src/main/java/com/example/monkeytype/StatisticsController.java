package com.example.monkeytype;

import javafx.scene.chart.XYChart;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class StatisticsController {

    private final StatisticView view;
    private final ObservableList<XYChart.Data<Number, Number>> wpmData;
    private final ObservableList<XYChart.Data<Number, Number>> accuracyData;

    public StatisticsController(StatisticView view) {
        this.view = view;
        this.wpmData = FXCollections.observableArrayList();
        this.accuracyData = FXCollections.observableArrayList();

        XYChart.Series<Number, Number> wpmSeries = new XYChart.Series<>();
        wpmSeries.setName("Words Per Minute");
        wpmSeries.setData(wpmData);

        XYChart.Series<Number, Number> accuracySeries = new XYChart.Series<>();
        accuracySeries.setName("Accuracy");
        accuracySeries.setData(accuracyData);

        view.addSeries(wpmSeries);
        view.addSeries(accuracySeries);
    }

    public void addDataPoint(Number time, Number wordsPerMinute, Number accuracy) {
        wpmData.add(new XYChart.Data<>(time, wordsPerMinute));
        accuracyData.add(new XYChart.Data<>(time, accuracy));
    }
}