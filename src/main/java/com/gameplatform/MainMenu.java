package com.gameplatform;

import com.gameplatform.royalgameofur.InstructionMenu;
import com.gameplatform.royalgameofur.RGOUHighScores;
import com.gameplatform.royalgameofur.RGOUMenu;
import com.gameplatform.sudokusolver.SudokuSolver;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import static com.gameplatform.Configurator.*;

public class MainMenu {
    private GridPane grid;
    private double numberOfRows = 10;
    private double numberOfColumns = 9;

    public MainMenu(GridPane grid) {
        this.grid = grid;
    }

    public GridPane newMainMenu() {
        setRowsAndColumns(grid, numberOfRows, numberOfColumns);
        addLabels();
        addButtons();

        return grid;
    }

    private void addLabels() {
        Label mainMenuLabel = createLabel("Select Game:", "MainMenuLabel", FONT25);
        grid.add(mainMenuLabel, 4, 2);

        Label royalGameOfUrLabel = createLabel("Royal Game of Uhr:", "RGOULabel", FONT20);
        grid.add(royalGameOfUrLabel, 2, 4);

        Label sudokuSolverLabel = createLabel("Sudoku Solver:", "SudokuSolverLabel", FONT20);
        grid.add(sudokuSolverLabel, 2, 6);

        Label logInfoLabel = createLabel("You're playing as " + GamePlatform.getPlayerName(), "LogLabel", FONT15, true);
        logInfoLabel.setAlignment(Pos.CENTER_RIGHT);
        GridPane.setHalignment(logInfoLabel, HPos.RIGHT);
        grid.add(logInfoLabel, 4, 0, 4, 1);
    }

    private void addButtons() {
        Button royalGameOfUrPlayGameButton = createButton("Play Game", "RGOUPlayButton", FONT20, (e) -> royalGameOfUrPlayGameButtonAction());
        GridPane.setHalignment(royalGameOfUrPlayGameButton, HPos.LEFT);
        grid.add(royalGameOfUrPlayGameButton, 4, 4);

        Button royalGameOfUrInstructionButton =
                createButton("Instruction PL", "RGOUInstructionButton", FONT20, (e) -> royalGameOfUrInstructionButtonAction());
        GridPane.setHalignment(royalGameOfUrInstructionButton, HPos.LEFT);
        grid.add(royalGameOfUrInstructionButton, 5, 4, 2, 1);

        Button royalGameOfUrHighScoresButton =
                createButton("HighScores", "RGOUHighScoresButton", FONT20, (e) -> royalGameOfUrHighScoresButtonAction());
        GridPane.setHalignment(royalGameOfUrHighScoresButton, HPos.RIGHT);
        grid.add(royalGameOfUrHighScoresButton, 6, 4);

        Button sudokuSolverButton = createButton("SOLVER", "RunSudokuSolverButton", FONT20, (e) -> runSudokuSolverAction());
        GridPane.setHalignment(sudokuSolverButton, HPos.LEFT);
        grid.add(sudokuSolverButton, 4, 6);

        Button logButton = createButton("", "LogButton", FONT15, (e) -> logButtonAction());
        if (GamePlatform.getLoggedIn()) {
            logButton.setText("LOGOUT");
        } else {
            logButton.setText("LOGIN");
        }
        GridPane.setHalignment(logButton, HPos.CENTER);
        grid.add(logButton, 8, 0);
    }

    private void logButtonAction() {
        if (GamePlatform.getLoggedIn()) {
            GamePlatform.setPlayerName("");
            GamePlatform.setLoggedIn(false);
            clearGrid(grid, numberOfRows, numberOfColumns);
            LogPanel logPanel = new LogPanel(grid);
            grid = logPanel.newLogPanel();
        } else {
            clearGrid(grid, numberOfRows, numberOfColumns);
            LogPanel logPanel = new LogPanel(grid);
            grid = logPanel.newLogPanel();
        }
    }

    private void royalGameOfUrPlayGameButtonAction() {
        clearGrid(grid, numberOfRows, numberOfColumns);
        RGOUMenu rgouMenu = new RGOUMenu(grid);
        grid = rgouMenu.newMenu();
    }

    private void royalGameOfUrInstructionButtonAction() {
        clearGrid(grid, numberOfRows, numberOfColumns);
        InstructionMenu menu = new InstructionMenu(grid);
        grid = menu.newInstruction();
    }

    private void royalGameOfUrHighScoresButtonAction() {
        clearGrid(grid, numberOfRows, numberOfColumns);
        RGOUHighScores highScores = new RGOUHighScores(grid);
        grid = highScores.newHighScores();
    }

    private void runSudokuSolverAction() {
        clearGrid(grid, numberOfRows, numberOfColumns);
        SudokuSolver sudokuSolver = new SudokuSolver(grid);
        grid = sudokuSolver.newSolver();
    }
}
