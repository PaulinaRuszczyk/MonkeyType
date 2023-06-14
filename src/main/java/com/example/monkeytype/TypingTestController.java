package com.example.monkeytype;
import javafx.scene.paint.Color;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
public class TypingTestController {
    private static int i = 0;
    private TypingTestModel model;
    private TypingTestView view;

    public TypingTestController(Stage stage, String languageFileName) {
        this.model = new TypingTestModel(languageFileName);
        this.view = new TypingTestView(stage);

        view.getUserInput().addEventHandler(KeyEvent.KEY_TYPED, event -> {
            String expectedText = view.getTextToType();
            String enteredText = event.getCharacter();
            checkEnteredText(enteredText, expectedText);
        });
        view.setTextToType(model.getRandomWords(30));
    }

    private void checkEnteredText(String enteredText, String textToType) {
        String letter = String.valueOf(textToType.charAt(i));

        if (enteredText.equals(letter)) {
            // Text entered matches the expected text
            System.out.println("Correct!");
            view.modifyLetterColor(i, Color.GREEN);
        } else {
            // Text entered does not match the expected text
            System.out.println("Incorrect!");
            view.modifyLetterColor(i, Color.RED);
        }
        i++;
    }
}