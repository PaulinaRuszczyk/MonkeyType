package com.example.monkeytype;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class View {
    private Stage primaryStage;
    private Model model;
    private BorderPane layout;
    private ComboBox<String> languageComboBox;
    private ComboBox<Integer> durationComboBox;
    private Button startButton;

    public View(Stage primaryStage, Model model) {
        this.primaryStage = primaryStage;
        this.model = model;
    }

    public BorderPane createLayout() {
        layout = new BorderPane();

        HBox topPanel = createTopPanel();
        layout.setTop(topPanel);

        VBox bottomPanel = createBottomPanel();
        layout.setBottom(bottomPanel);

        return layout;
    }

    private HBox createTopPanel() {
        HBox topPanel = new HBox();
        topPanel.setPadding(new Insets(10));
        topPanel.setSpacing(10);

        languageComboBox = new ComboBox<>();
        languageComboBox.setItems((ObservableList<String>) model.getLanguages());
        languageComboBox.setPromptText("Select language");

        durationComboBox = new ComboBox<>();
        durationComboBox.getItems().addAll(15, 20, 45, 60, 90, 120, 300);
        durationComboBox.setPromptText("Select duration");

        startButton = new Button("Start");

        topPanel.getChildren().addAll(languageComboBox, durationComboBox, startButton);

        return topPanel;
    }

    private VBox createBottomPanel() {
        VBox bottomPanel = new VBox();
        bottomPanel.setPadding(new Insets(10));
        bottomPanel.setSpacing(10);

        Label shortcutsLabel = new Label("Shortcuts:");
        Label shortcutsInfoLabel = new Label("Tab + Enter: Restart | Ctrl + Shift + P: Pause | Esc: End Test");

        bottomPanel.getChildren().addAll(shortcutsLabel, shortcutsInfoLabel);

        return bottomPanel;
    }

    // Getters for GUI elements

    public ComboBox<String> getLanguageComboBox() {
        return languageComboBox;
    }

    public ComboBox<Integer> getDurationComboBox() {
        return durationComboBox;
    }

    public Button getStartButton() {
        return startButton;
    }
}