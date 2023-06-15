package com.example.monkeytype;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

public class TypingTestView {
    private Stage stage;
    private TextFlow textToType;
    private Scene userInput;

    public TypingTestView(Stage stage) {
        this.stage = stage;
        initUI();
    }

    private void initUI() {
        BorderPane root = new BorderPane();

        VBox centerPane = new VBox(10);
        textToType = new TextFlow();
        centerPane.getChildren().addAll(textToType);
        centerPane.setAlignment(Pos.CENTER);

        root.setCenter(centerPane);

        Scene scene = new Scene(root, 800, 600);
        userInput = scene;
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

    public Scene getUserInput() {
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

    public void spinWrongLetter(int index ) {
        Text letter = (Text) textToType.getChildren().get(index);
        RotateTransition transition = new RotateTransition(Duration.seconds(1), letter);
        transition.setByAngle(360);
        transition.setAxis(Rotate.Y_AXIS);
        transition.play();
    }
    public void moveCorrectLetter(int index ) {
        Text letter = (Text) textToType.getChildren().get(index);
        TranslateTransition transition = new TranslateTransition(Duration.seconds(0.4), letter);
        transition.setByY(-5);
        transition.setCycleCount(2);
        transition.setAutoReverse(true);
        transition.play();

    }    public void spinRepeatedLetter(int index ) {
        Text letter = (Text) textToType.getChildren().get(index);
        RotateTransition transition = new RotateTransition(Duration.seconds(1), letter);
        transition.setByAngle(360);
        transition.setAxis(Rotate.X_AXIS);
        transition.play();
    }
}