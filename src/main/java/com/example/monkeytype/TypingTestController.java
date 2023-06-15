package com.example.monkeytype;
import javafx.animation.RotateTransition;
import javafx.application.Platform;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
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
    private long durationMillis;
    private String currentWord;

    public TypingTestController(Stage stage, String languageFileName, Duration duration) {
        this.stage = stage;
        this.model = new TypingTestModel(languageFileName);
        this.view = new TypingTestView(stage);
        this.isPaused = new AtomicBoolean(false);
        // resumeTimer();

        view.getUserInput().addEventHandler(KeyEvent.KEY_TYPED, event -> {
            String enteredText = event.getCharacter();
            if (!isPaused.get() && !event.isControlDown() && Character.isLetter(enteredText.charAt(0)) || enteredText.equals(" ")) {
                String expectedText = view.getTextToType();
                checkEnteredText(enteredText, expectedText);
            }
        });
        view.setTextToType(model.getRandomWords(30));

        view.getUserInput().addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                closeApplication();
            }
            if (event.isControlDown() && event.isShiftDown() && event.getCode() == KeyCode.P) {
                toogleTimer();
            }
            if (event.getCode() == KeyCode.TAB) {
                event.consume();
                view.getUserInput().addEventHandler(KeyEvent.KEY_PRESSED, enterEvent -> {
                    if (enterEvent.getCode() == KeyCode.ENTER) {
                        restartTest();
                    }
                });
            }
        });
        // Start the timer thread
         durationMillis = Math.round(duration.toMillis());
        timerThread = new Thread(() -> timerLoop(durationMillis));
        timerThread.setDaemon(true);
        timerThread.start();
    }

    private void checkEnteredText(String enteredText, String textToType) {
        String letter = String.valueOf(textToType.charAt(i));

        if (enteredText.equals(letter)) {
            System.out.println("Correct!");
            view.modifyLetterColor(i, Color.GREEN);
            view.moveCorrectLetter(i);
            if(enteredText.equals(" "))
                calculateWPM();
        } else if(i>0 && enteredText.equals(String.valueOf(textToType.charAt(i-1))) && !enteredText.equals(" ")){
            System.out.println("Powtórzenie!");
                view.addLetterToTextToType( enteredText, i);
                view.modifyLetterColor(i, Color.ORANGE);
                view.spinRepeatedLetter(i);

            //i--
        }else {
            System.out.println("Incorrect!");
            view.modifyLetterColor(i, Color.RED);
            view.spinWrongLetter(i);
        }
        if(view.getTextToType().length()==i+1) {
            view.setTextToType(model.getRandomWords(30));
            i=0;
        }
        else
            i++;
    }

    private void restartTest(){
        resetTimer();
        view.setTextToType(model.getRandomWords(30));
        i=0;
    }

    private void closeApplication() {
        Platform.runLater(() -> {
            stage.close();
        });

        if (timerThread != null && timerThread.isAlive()) {
            timerThread.interrupt();
        }
    }

    private void calculateWPM() {
    }
    private void timerLoop(long durationMillis) {
        try {
            while (durationMillis > 0) {
                if (isPaused.get())
                    continue;
                Thread.sleep(1000);
                durationMillis -= 1000;
            if (durationMillis <= 0) {
                closeApplication();
            }
                System.out.println(durationMillis);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void resetTimer() {
        if (timerThread != null && timerThread.isAlive()) {
            timerThread.interrupt();
        }
        timerThread = new Thread(() -> timerLoop(durationMillis));
        timerThread.setDaemon(true);
        timerThread.start();
    }

    public void pauseTimer() {
        isPaused.set(true);
    }

    public void resumeTimer() {
        isPaused.set(false);
    }
    public  void toogleTimer(){
        if(isPaused.get())
            resumeTimer();
        else
            pauseTimer();
    }


}