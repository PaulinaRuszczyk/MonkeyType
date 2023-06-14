package com.example.monkeytype;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class TypingTestView {
    private Stage stage;
    private TextFlow textToType;
    private TextArea userInput;

    public TypingTestView(Stage stage) {
        this.stage = stage;
        initUI();
    }

    private void initUI() {
        BorderPane root = new BorderPane();

        // Center panel with text to type and user input
        VBox centerPane = new VBox(10);
        textToType = new TextFlow();
        userInput = new TextArea();
        centerPane.getChildren().addAll(textToType, userInput);
        centerPane.setAlignment(Pos.CENTER);

        root.setCenter(centerPane);

        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.setTitle("Typing Test");
    }

    public String getTextToType() {
        StringBuilder textBuilder = new StringBuilder();
        for (Node node : textToType.getChildren()) {
            if (node instanceof Text) {
                Text textNode = (Text) node;
                textBuilder.append(textNode.getText());
            }
        }
        return textBuilder.toString();
    }

    public void setTextToType(String text) {
        textToType.getChildren().clear();
        for (char c : text.toCharArray()) {
            Text letter = new Text(String.valueOf(c));
            letter.setFill(Color.GRAY);
            textToType.getChildren().add(letter);
        }
    }

    public TextArea getUserInput() {
        return userInput;
    }

    public void modifyLetterColor(int index, Color color) {
        if (textToType != null && index >= 0 && index < textToType.getChildren().size()) {
            Text letter = (Text) textToType.getChildren().get(index);
            letter.setFill(color);
        }
    }
    public void addLetterToTextToType(String letter, int i) {
        Text newLetter = new Text(letter);
        textToType.getChildren().add(i, newLetter);
    }
}