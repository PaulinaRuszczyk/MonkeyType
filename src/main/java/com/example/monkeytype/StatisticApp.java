package com.example.monkeytype;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StatisticApp extends Application {


    @Override
    public void start(Stage primaryStage) {
        StatisticModel model = new StatisticModel();
        StatisticView view = new StatisticView();
        StatisticsController controller = new StatisticsController(view);

        controller.addDataPoint(5,100, 85);
        controller.addDataPoint(6,120, 90);
        controller.addDataPoint(7,130, 95);
        controller.addDataPoint(8,140, 100);
        controller.addDataPoint(9,150, 100);
        controller.addDataPoint(10,160, 100);

        Scene scene = new Scene(view, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Words Per Minute and Accuracy");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}