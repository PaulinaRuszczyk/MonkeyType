package com.example.monkeytype;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.util.Duration;


public class MainWindow extends Application {
    private String language;
    private Duration time;

    public MainWindow() {
        // Default constructor
    }

    public MainWindow(String languageFileName, Duration time) {
        this.language = languageFileName;
        this.time = time;
    }

    @Override
    public void start(Stage primaryStage) {
        if(language == null || time == null) {
            System.out.println("Language or time not selected");
            System.exit(0);
        }
        String str = "dictionary/" + language + ".txt";
        new TypingTestController(primaryStage, str, time);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}