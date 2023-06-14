package com.example.monkeytype;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class TypingTestView {
    private Stage stage;
    private Label textToType;
    private TextArea userInput;

    public TypingTestView(Stage stage) {
        this.stage = stage;
        initUI();
    }

    private void initUI() {
        BorderPane root = new BorderPane();

        // Center panel with text to type and user input
        VBox centerPane = new VBox(10);
        textToType = new Label("Text to type...");
        userInput = new TextArea();
        centerPane.getChildren().addAll(textToType, userInput);
        centerPane.setAlignment(Pos.CENTER);

        root.setCenter(centerPane);

        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.setTitle("Typing Test");
    }

    public Label getTextToType() {
        return textToType;
    }

    public TextArea getUserInput() {
        return userInput;
    }
}