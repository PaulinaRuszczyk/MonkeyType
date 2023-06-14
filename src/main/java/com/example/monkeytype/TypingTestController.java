package com.example.monkeytype;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import javafx.scene.text.Text;

public class TypingTestController {
    private static int i=0;
    private TypingTestModel model;
    private TypingTestView view;

    public TypingTestController(Stage stage, String languageFileName) {
        this.model = new TypingTestModel(languageFileName);
        this.view = new TypingTestView(stage);

//        view.getUserInput().addEventHandler(KeyEvent.KEY_PRESSED, event -> {
//            String enteredText = view.getUserInput().getText();
//            String textToType = view.getTextToType().getText();
//            checkEnteredText(enteredText, textToType);
//        });
        view.getUserInput().addEventHandler(KeyEvent.KEY_TYPED, event -> {
            String expectedText = view.getTextToType().getText();
            String enteredText = event.getCharacter();
            checkEnteredText( enteredText, expectedText);
        });
        view.getTextToType().setText(model.getRandomWords(30));
    }

    private void checkEnteredText(String enteredText, String textToType) {
        String letter = String.valueOf(textToType.charAt(i));
        Text text = new Text(letter);

        if (enteredText.equals(letter)) {
            // Text entered matches the expected text
            System.out.println("Correct!");
            text.setFill(Color.GREEN);
        } else {
            // Text entered does not match the expected text
            System.out.println("Incorrect!");
            text.setFill(Color.RED);
        }
        i++;
    }
}