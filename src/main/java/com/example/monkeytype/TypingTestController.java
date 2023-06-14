package com.example.monkeytype;
import javafx.application.Platform;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.concurrent.atomic.AtomicBoolean;

public class TypingTestController {
    private static int i = 0;
    private TypingTestModel model;
    private TypingTestView view;

    private Thread timerThread;
    private Stage stage;
    private AtomicBoolean isPaused;

    public TypingTestController(Stage stage, String languageFileName, Duration duration) {
        this.stage = stage;
        this.model = new TypingTestModel(languageFileName);
        this.view = new TypingTestView(stage);
        this.isPaused = new AtomicBoolean(false);

        view.getUserInput().addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                closeApplication();
            }
            if (event.isControlDown() && event.isShiftDown() && event.getCode() == KeyCode.P) {
                pauseTest();
            }
            if (event.getCode() == KeyCode.TAB) {
                // Store the current event for later comparison
                event.consume(); // Consume the event to prevent it from triggering other handlers
                view.getUserInput().addEventHandler(KeyEvent.KEY_PRESSED, enterEvent -> {
                    if (enterEvent.getCode() == KeyCode.ENTER) {
                        restartTest();
                        enterEvent.consume(); // Consume the event to prevent it from triggering other handlers
                    }
                });
            }
        });
        view.getUserInput().addEventHandler(KeyEvent.KEY_TYPED, event -> {
            String expectedText = view.getTextToType();
            String enteredText = event.getCharacter();
            checkEnteredText(enteredText, expectedText);
        });
        view.setTextToType(model.getRandomWords(30));

        // Start the timer thread
        long durationMillis = Math.round(duration.toMillis());
        timerThread = new Thread(() -> timerLoop(durationMillis));
        timerThread.setDaemon(true); // Set the thread as daemon
        timerThread.start();
    }

    private void checkEnteredText(String enteredText, String textToType) {
        String letter = String.valueOf(textToType.charAt(i));

        if (enteredText.equals(letter)) {
            System.out.println("Correct!");
            view.modifyLetterColor(i, Color.GREEN);
        } else if(i>0 && enteredText.equals(String.valueOf(textToType.charAt(i-1))) && !enteredText.equals(" ")){
            System.out.println("Powtórzenie!");
                view.addLetterToTextToType( enteredText, i);
                view.modifyLetterColor(i, Color.ORANGE);

            //i--
        }else {
            System.out.println("Incorrect!");
            view.modifyLetterColor(i, Color.RED);
        }
        if(view.getTextToType().length()==i+1) {
            view.setTextToType(model.getRandomWords(30));
            i=0;
        }
        else
            i++;
    }

    private void pauseTest(){
        pauseTimer();
    }
    private void restartTest(){
        resetTimer();
        view.setTextToType(model.getRandomWords(30));
        i=0;
    }

    private void closeApplication() {
        // Close the application on the JavaFX application thread
        Platform.runLater(() -> {
            stage.close();
        });

        // Interrupt the timer thread if it's still running
        if (timerThread != null && timerThread.isAlive()) {
            timerThread.interrupt();
        }
    }

    private void calculateWPM() {
    }
    private void timerLoop(long durationMillis) {
        try {
            while (!isPaused.get() && durationMillis > 0) {
                Thread.sleep(1000);
                durationMillis -= 1000;
            }
            if (durationMillis <= 0) {
                closeApplication();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void resetTimer() {
        if (timerThread != null && timerThread.isAlive()) {
            timerThread.interrupt();
        }
        timerThread = new Thread(() -> timerLoop(Math.round(Duration.ZERO.toMillis())));
        timerThread.setDaemon(true); // Set the thread as daemon
        timerThread.start();
    }

    public void pauseTimer() {
        isPaused.set(true);
    }

    public void resumeTimer() {
        isPaused.set(false);
    }
}