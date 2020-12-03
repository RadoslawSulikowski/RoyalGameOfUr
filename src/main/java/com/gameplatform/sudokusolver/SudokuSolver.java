package com.gameplatform.sudokusolver;

import com.gameplatform.MainMenu;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import static com.gameplatform.Configurator.clearGrid;
import static java.lang.Integer.parseInt;
import static javafx.scene.paint.Color.BLACK;
import static javafx.scene.paint.Color.DARKGREEN;

public class SudokuSolver {

    private SudokuBoard inputBoard = new SudokuBoard();
    private GridPane grid;
    private SudokuSolverConfigurator configurator;

    public SudokuSolver(GridPane grid) {
        this.grid = grid;
        configurator = new SudokuSolverConfigurator(grid);
    }

    public GridPane newSolver() {
        configurator.configure();
        grid.lookup("#SolveButton").setOnMouseClicked(e -> resolveSudoku());
        grid.lookup("#MainMenuButton").setOnMouseClicked(e -> mainMenuButtonAction());
        grid.lookup("#ResetBoardButton").setOnMouseClicked(e -> resetAllFields());
        return grid;
    }

    private void getBoardFromUI() {
        inputBoard.createBoard();
        for (int i = 0; i < 81; i++) {
            SudokuFieldButton fieldFromUI = (SudokuFieldButton) grid.lookup("#field" + i);
            SudokuField field = inputBoard.getFieldByFieldNumber(fieldFromUI.getFieldNumber());
            if (!fieldFromUI.getText().equals("")) {
                field.setValue(parseInt(fieldFromUI.getText()));
                field.setColumn(fieldFromUI.getColumnNumber());
                field.setRow(fieldFromUI.getRowNumber());
                field.setSection(fieldFromUI.getSectionNumber());
                field.setUserValue(true);
            }
        }
    }

    private void resolveSudoku() {
        getBoardFromUI();
        if (inputBoard.hasDuplicateValuesInRowColumnOrSection()) {
            ((Label) grid.lookup("#DialogLabel")).
                    setText("Your sudoku has duplicated values in rows, columns or sections. Pleas check your input board");
        } else {
            SudokuBoard board = inputBoard.copyBoard();
            board.prepareBoard();
            if (board.solve()) {
                grid.lookup("#SolveButton").setOnMouseClicked(null);
                drawBoard(board);
            } else {
                ((Label) grid.lookup("#DialogLabel")).
                        setText("Your sudoku has no solution. Pleas check your input board");
            }
        }
    }

    private void drawBoard(SudokuBoard board) {
        for (int i = 0; i < 81; i++) {
            SudokuFieldButton fieldButton = (SudokuFieldButton) grid.lookup("#field" + i);
            SudokuField field = board.getFieldByFieldNumber(i);
            fieldButton.setOnMouseClicked(null);
            if (field.isUserValue()) {
                fieldButton.setTextFill(DARKGREEN);
                fieldButton.getStyleClass().removeAll("blue-button", "grey-button", "red-button");
                fieldButton.getStyleClass().add("green-button");
            } else {
                fieldButton.setText("" + field.getValue());
            }
        }
    }

    private void mainMenuButtonAction() {
        clearGrid(grid, configurator.getNumberOfRows(), configurator.getNumberOfColumns());
        MainMenu menu = new MainMenu(grid);
        grid = menu.newMainMenu();
    }

    private void resetAllFields() {
        grid.lookup("#SolveButton").setOnMouseClicked(e -> resolveSudoku());
        for (int i = 0; i < 81; i++) {
            SudokuFieldButton fieldButton = (SudokuFieldButton) grid.lookup("#field" + i);
            fieldButton.setText("");
            fieldButton.setTextFill(BLACK);
            fieldButton.getStyleClass().removeAll("green-button", "red-button");
            fieldButton.setOnMouseClicked(e -> configurator.fieldAction(fieldButton));
            configurator.setBoardButtonsDefaultStyleClass(fieldButton);
        }
    }
}

