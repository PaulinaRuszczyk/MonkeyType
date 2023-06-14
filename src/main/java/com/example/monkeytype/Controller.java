package com.example.monkeytype;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class Controller {
    private Model model;
    private View view;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;

        view.getStartButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String selectedLanguage = view.getLanguageComboBox().getValue();
                int selectedDuration = view.getDurationComboBox().getValue();

                model.setSelectedLanguage(selectedLanguage);
                model.setTestDuration(selectedDuration);

                // Perform necessary actions when the start button is clicked
            }
        });
    }
}