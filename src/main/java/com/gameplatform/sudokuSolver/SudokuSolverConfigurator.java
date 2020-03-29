package com.gameplatform.sudokuSolver;

import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.TextAlignment;

import static com.gameplatform.Configurator.*;
import static javafx.scene.paint.Color.BLACK;

public class SudokuSolverConfigurator {

    private Button clearButton = createButton("0", "clearButton", FONT15, 51);
    private Button oneButton = createButton("1", "oneButton", FONT15, 51);
    private Button twoButton = createButton("2", "twoButton", FONT15, 51);
    private Button threeButton = createButton("3", "threeButton", FONT15, 51);
    private Button fourButton = createButton("4", "fourButton", FONT15, 51);
    private Button fiveButton = createButton("5", "fiveButton", FONT15, 51);
    private Button sixButton = createButton("6", "sixButton", FONT15, 51);
    private Button sevenButton = createButton("7", "sevenButton", FONT15, 51);
    private Button eightButton = createButton("8", "eightButton", FONT15, 51);
    private Button nineButton = createButton("9", "nineButton", FONT15, 51);


    private GridPane grid;
    private double numberOfRows = 16;
    private double numberOfColumns = 30;

    public SudokuSolverConfigurator(GridPane grid) {
        this.grid = grid;
    }

    public void configure() {
        setRowsAndColumns(grid, numberOfRows, numberOfColumns);
        drawBoard();
        addButtons();
    }


    public double getNumberOfRows() {
        return numberOfRows;
    }

    public double getNumberOfColumns() {
        return numberOfColumns;
    }

    private void drawBoard() {
        final ToggleGroup boardButtons = new ToggleGroup();
        for (int i = 9; i < 18; i++) {
            for (int j = 2; j < 11; j++) {
                int column = i - 8;
                int row = j - 1;
                int section = (((row - 1) / 3) * 3 + (column - 1) / 3) + 1;
                int fieldNumber = 9 * (row - 1) + column - 1;
                ToggleButton button = createToggleButton("" + fieldNumber, "field" + fieldNumber, FONT15, 46);
                button.setToggleGroup(boardButtons);
                button.setMinHeight(46);
                button.setTextAlignment(TextAlignment.CENTER);
                button.setTextFill(BLACK);
                if (section % 2 != 0) {
                    button.setStyle("-fx-base: lightblue;");
                }
                grid.add(button, i, j);
            }
        }
    }

    private void addButtons() {
        final ToggleGroup numberButtons = new ToggleGroup();
        for (int i = 1; i < 11; i++) {
            ToggleButton button = createToggleButton("" + i, i + "button", FONT15, 46);
            button.setToggleGroup(numberButtons);
            button.setMinHeight(46);
            button.setTextAlignment(TextAlignment.CENTER);
            button.setTextFill(BLACK);
            if (i == 10) {
                button.setText("");
                button.setGraphic(new ImageView( new Image("file:src/main/resources/eraser.jpg", true)));
            }
            grid.add(button, 8 + i, 12);
        }
    }
}
