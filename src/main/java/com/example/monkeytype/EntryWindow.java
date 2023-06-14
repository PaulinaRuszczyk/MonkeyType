package com.example.monkeytype;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class EntryWindow extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Language selection
        ComboBox<String> languageComboBox = new ComboBox<>();
        languageComboBox.getItems().addAll("english", "afrikaans", "albanian", "czech", "danish", "dutch", "estonian", "finnish", "italian", "japanese", "latvian", "lithuanian", "norwegian", "polish", "portugese", "slovak", "swedish", "turkish", "ukrainian");
        languageComboBox.setPromptText("Select a language");

        ComboBox<String> timeComboBox = new ComboBox<>();
        timeComboBox.getItems().addAll("15", "20", "45", "60", "90", "120", "300");
        timeComboBox.setPromptText("Select a time");
        // Start button
        Button startButton = new Button("Start");
        startButton.setOnAction(event -> {
            String selectedLanguage = languageComboBox.getValue();
            String selectedTime = timeComboBox.getValue();
            openMainWindow(selectedLanguage,selectedTime);
        });
        // Info text area
        Label infoTextArea = new Label("tab+enter -> restart test \nctrl+shift+p -> pause\nesc -> end test");

        // VBox layout
        VBox root = new VBox(10);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(languageComboBox,timeComboBox, startButton, infoTextArea);

        Scene scene = new Scene(root, 300, 200);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Entry Window");
        primaryStage.show();
    }

    private void openMainWindow(String language, String time) {
        // Create a new window for the main application
        Stage mainWindowStage = new Stage();
        // Pass the selected language to the main application window

        Duration duration = Duration.seconds(Integer.parseInt(time));
        MainWindow mainWindow = new MainWindow(language, duration);
        mainWindow.start(mainWindowStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
