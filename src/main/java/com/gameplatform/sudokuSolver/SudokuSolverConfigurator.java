package com.gameplatform.sudokuSolver;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.TextAlignment;

import static com.gameplatform.Configurator.*;
import static javafx.scene.paint.Color.BLACK;

class SudokuSolverConfigurator {

    private GridPane grid;
    private double numberOfRows = 16;
    private double numberOfColumns = 30;
    private final ToggleGroup numberButtons = new ToggleGroup();
    private final ToggleGroup boardButtons = new ToggleGroup();

    SudokuSolverConfigurator(GridPane grid) {
        this.grid = grid;
    }

    void configure() {
        setRowsAndColumns(grid, numberOfRows, numberOfColumns);
        drawBoard();
        addNumbersButtons();
        addDialogLabel();
        addNavigationButtons();
    }

    double getNumberOfRows() {
        return numberOfRows;
    }

    double getNumberOfColumns() {
        return numberOfColumns;
    }

    private void drawBoard() {
        for (int i = 9; i < 18; i++) {
            for (int j = 2; j < 11; j++) {
                int column = i - 8;
                int row = j - 1;
                int section = (((row - 1) / 3) * 3 + (column - 1) / 3) + 1;
                int fieldNumber = 9 * (row - 1) + column - 1;
                SudokuFieldButton button = new SudokuFieldButton("");
                button.setColumnNumber(column);
                button.setRowNumber(row);
                button.setSectionNumber(section);
                button.setFieldNumber(fieldNumber);
                button.setId("field" + fieldNumber);
                button.setFont(FONT20);
                button.setMinWidth(46);
                button.setTextAlignment(TextAlignment.CENTER);
                button.setToggleGroup(boardButtons);
                button.setMinHeight(46);
                button.setTextAlignment(TextAlignment.CENTER);
                button.setTextFill(BLACK);
                button.setOnMouseClicked(e -> fieldAction(button));
                grid.add(button, i, j);
                setBoardButtonsDefaultStyleClass(button);
            }
        }
    }

    void setBoardButtonsDefaultStyleClass(SudokuFieldButton button) {
        if (button.getSectionNumber() % 2 != 0) {
            button.getStyleClass().add("blue-button");
        } else {
            button.getStyleClass().add("grey-button");
        }
    }

    private void addNumbersButtons() {
        for (int i = 1; i < 11; i++) {
            ToggleButton button = new ToggleButton("" + i);
            button.setId("button" + i);
            button.setFont(FONT20);
            button.setMinWidth(46);
            button.setTextAlignment(TextAlignment.CENTER);
            button.setToggleGroup(numberButtons);
            button.setMinHeight(46);
            button.setTextAlignment(TextAlignment.CENTER);
            button.setTextFill(BLACK);
            button.setUserData("" + i);
            button.setOnMouseClicked(e -> ((Label) grid.lookup("#DialogLabel")).setText(""));
            button.getStyleClass().add("grey-button");
            if (i == 10) {
                button.setText("");
                button.setGraphic(new ImageView(new Image("file:src/main/resources/eraser.jpg", true)));
            }
            grid.add(button, 8 + i, 12);
        }
    }

    private void addDialogLabel() {
        grid.add(createLabel("", "DialogLabel", FONT20, true, Pos.BASELINE_CENTER, 850),
                5, 0, 18, 2);
    }

    private void addNavigationButtons() {
        grid.add(createButton("Solve", "SolveButton", FONT25, 850),
                5, 14);
        grid.add(createButton("Reset Board", "ResetBoardButton", FONT30, 250),
                20, 5, 3, 1);
        grid.add(createButton("Main Menu", "MainMenuButton", FONT30, 250),
                20, 7, 3, 1);
    }

    void fieldAction(SudokuFieldButton field) {
        ((Label) grid.lookup("#DialogLabel")).setText("");
        String numberToSet;
        if (numberButtons.getSelectedToggle() != null) {
            numberToSet = (String) numberButtons.getSelectedToggle().getUserData();
            if (numberToSet != null) {
                if (numberToSet.equals("10")) {
                    field.setText("");
                } else {
                    field.setText(numberToSet);
                }
                checkDuplicatedValues();
            }
        }
    }

    private void checkDuplicatedValues() {
        SudokuFieldButton checkedField;
        SudokuFieldButton field;
        for (int i = 0; i < 81; i++) {
            checkedField = (SudokuFieldButton) grid.lookup("#field" + i);
            if (checkedField.getText().equals("")) {
                checkedField.getStyleClass().removeAll("grey-button", "blue-button", "red-button");
                setBoardButtonsDefaultStyleClass(checkedField);
                continue;
            }
            checkedField.setValueDuplicated(false);
            for (int j = 0; j < 81; j++) {
                if (j == i) {
                    continue;
                }
                field = (SudokuFieldButton) grid.lookup("#field" + j);
                if (checkedField.getSectionNumber() == field.getSectionNumber()
                        || checkedField.getColumnNumber() == field.getColumnNumber()
                        || checkedField.getRowNumber() == field.getRowNumber()) {
                    if (checkedField.getText().equals(field.getText())) {
                        checkedField.getStyleClass().removeAll("grey-button", "blue-button", "red-button");
                        checkedField.getStyleClass().add("red-button");
                        checkedField.setValueDuplicated(true);
                    } else if (!checkedField.isValueDuplicated()) {
                        checkedField.getStyleClass().removeAll("grey-button", "blue-button", "red-button");
                        setBoardButtonsDefaultStyleClass(checkedField);
                    }
                }
            }
        }
    }
}

