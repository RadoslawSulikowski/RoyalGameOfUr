package com.gameplatform;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

import static javafx.scene.paint.Color.WHITE;

public class Configurator {

    public static final Font FONT15 = Font.font("ALGERIAN", FontWeight.BOLD, 15);
    public static final Font FONT20 = Font.font("ALGERIAN", FontWeight.BOLD, 20);
    public static final Font FONT25 = Font.font("ALGERIAN", FontWeight.BOLD, 25);
    public static final Font FONT30 = Font.font("ALGERIAN", FontWeight.BOLD, 30);

    public static void setRowsAndColumns(GridPane grid, double numberOfRows, double numberOfColumns) {
        for (int i = 0; i < numberOfRows; i++) {
            grid.getRowConstraints().get(i).setPercentHeight(100.0 / numberOfRows);
        }
        for (int i = 0; i < numberOfColumns; i++) {
            grid.getColumnConstraints().get(i).setPercentWidth(100.0 / numberOfColumns);
        }
    }

    private static void resetRowsAndColumns(GridPane grid, double numberOfRows, double numberOfColumns) {
        for (int i = 0; i < numberOfRows; i++) {
            grid.getRowConstraints().get(i).setPercentHeight(0);
        }
        for (int i = 0; i < numberOfColumns; i++) {
            grid.getColumnConstraints().get(i).setPercentWidth(0);
        }
    }

    public static void clearGrid(GridPane grid, double numberOfRows, double numberOfColumns) {
        while (grid.getChildren().size() != 0) {
            grid.getChildren().remove(grid.getChildren().get(0));
        }
        resetRowsAndColumns(grid, numberOfRows, numberOfColumns);
    }

    public static Label createLabel(String text, Font font) {
        Label label = new Label(text);
        label.setFont(font);
        label.setMinWidth(300);
        label.setTextFill(WHITE);
        label.setAlignment(Pos.CENTER);
        return label;
    }

    public static Label createLabel(String text, Font font, Color textFill) {
        Label label = createLabel(text, font);
        label.setTextFill(textFill);
        return label;
    }

    public static Label createLabel(String text, String id, Font font) {
        Label label = createLabel(text, font);
        label.setId(id);
        return label;
    }

    public static Label createLabel(String text, String id, Font font, boolean wrapText) {
        Label label = createLabel(text, id, font);
        label.setWrapText(wrapText);
        return label;
    }

    public static Label createLabel(String text, String id, Font font, boolean wrapText, Pos alignment) {
        Label label = createLabel(text, id, font, wrapText);
        label.setAlignment(alignment);
        return label;
    }

    public static Label createLabel(String text, String id, Font font, boolean wrapText, Pos alignment, double minWidth) {
        Label label = createLabel(text, id, font, wrapText, alignment);
        label.setMinWidth(minWidth);
        return label;
    }

    public static Button createButton(String text, String id, Font font, double minWidth) {
        Button button = new Button(text);
        button.setId(id);
        button.setFont(font);
        button.setMinWidth(minWidth);
        button.setTextAlignment(TextAlignment.CENTER);

        return button;
    }

    public static Button createButton(String text, String id, Font font, EventHandler action) {
        Button button = new Button(text);
        button.setId(id);
        button.setFont(font);
        button.setOnAction(action);

        return button;
    }

    public static Button createButton(String text, Font font, double minWidth, EventHandler action) {
        Button button = new Button(text);
        button.setFont(font);
        button.setMinWidth(minWidth);
        button.setTextAlignment(TextAlignment.CENTER);
        button.setOnAction(action);

        return button;
    }

    public static Button createButton(String text, String id, Font font, double minWidth, EventHandler action) {
        Button button = new Button(text);
        button.setId(id);
        button.setFont(font);
        button.setMinWidth(minWidth);
        button.setTextAlignment(TextAlignment.CENTER);
        button.setOnAction(action);

        return button;
    }

    public static TextField createTextField(String id) {
        TextField textField = new TextField();
        textField.setPromptText("Player Name");
        textField.setId(id);

        return textField;
    }

    public static TextField createTextField(String id, int maxWidth) {
        TextField textField = new TextField();
        textField.setText("");
        textField.setPromptText("Player Name");
        textField.setId(id);
        textField.setMaxWidth(maxWidth);

        return textField;
    }

    public static PasswordField createPasswordField(String id) {
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        passwordField.setId(id);
        return passwordField;
    }

    public static PasswordField createPasswordField(String id, int maxWidth) {
        PasswordField passwordField = new PasswordField();
        passwordField.setText("");
        passwordField.setPromptText("Password");
        passwordField.setId(id);
        passwordField.setMaxWidth(maxWidth);
        return passwordField;
    }
}