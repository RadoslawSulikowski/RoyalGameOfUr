package com.kodilla;

import com.kodilla.royalgameofur.InstructionMenu;
import com.kodilla.royalgameofur.RGOUMenu;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import static com.kodilla.GamePlatform.*;
import static javafx.scene.paint.Color.WHITE;

public class MainMenu {
    private GridPane grid;
    private double numberOfRows = 10;
    private double numberOfColumns = 9;

    public MainMenu(GridPane grid) {
        this.grid = grid;
    }

    public GridPane newMainMenu() {
        setRowsAndColumns();
        addLabels();
        addButtons();

        return grid;

    }


    private void setRowsAndColumns() {

        for (int i = 0; i < numberOfRows; i++) {
            grid.getRowConstraints().get(i).setPercentHeight(100.0 / numberOfRows);
        }
        for (int i = 0; i < numberOfColumns; i++) {
            grid.getColumnConstraints().get(i).setPercentWidth(100.0 / numberOfColumns);
        }
    }

    private void resetRowsAndColumns() {

        for (int i = 0; i < numberOfRows; i++) {
            grid.getRowConstraints().get(i).setPercentHeight(0);
        }
        for (int i = 0; i < numberOfColumns; i++) {
            grid.getColumnConstraints().get(i).setPercentWidth(0);
        }
    }

    private void clearGrid() {
        while (grid.getChildren().size() != 0) {
            grid.getChildren().remove(grid.getChildren().get(0));
        }
        resetRowsAndColumns();
    }

    private void addLabels() {

        Label mainMenuLabel = new Label();
        mainMenuLabel.setText("Select Game:");
        mainMenuLabel.setId("MainMenuLabel");
        mainMenuLabel.setFont(FONT25);
        mainMenuLabel.setMinWidth(300);
        mainMenuLabel.setTextFill(WHITE);
        grid.add(mainMenuLabel, 4, 2);

        Label royalGameOfUrLabel = new Label();
        royalGameOfUrLabel.setText("Royal Game Of Ur:");
        royalGameOfUrLabel.setId("RoyalGameOfUr");
        royalGameOfUrLabel.setFont(FONT20);
        royalGameOfUrLabel.setMinWidth(300);
        royalGameOfUrLabel.setTextFill(WHITE);
        grid.add(royalGameOfUrLabel, 2, 4);

        Label logInfoLabel = new Label();
        logInfoLabel.setText("You're playing as " + GamePlatform.getPlayerName());
        logInfoLabel.setId("LogLabel");
        logInfoLabel.setFont(FONT15);
        logInfoLabel.setMinWidth(300);
        logInfoLabel.setWrapText(true);
        logInfoLabel.setTextFill(WHITE);
        logInfoLabel.setAlignment(Pos.CENTER_RIGHT);
        GridPane.setHalignment(logInfoLabel, HPos.RIGHT);
        grid.add(logInfoLabel, 4, 0, 4, 1);
    }

    private void addButtons() {

        Button royalGameOfUrPlayGameButton = new Button("Play Game");
        royalGameOfUrPlayGameButton.setId("RGOUPlayButton");
        royalGameOfUrPlayGameButton.setFont(FONT20);
        royalGameOfUrPlayGameButton.setOnAction((e) -> royalGameOfUrPlayGameButtonAction());
        GridPane.setHalignment(royalGameOfUrPlayGameButton, HPos.LEFT);
        grid.add(royalGameOfUrPlayGameButton, 4, 4);

        Button royalGameOfUrInstructionButton = new Button("Instruction PL");
        royalGameOfUrInstructionButton.setId("RGOUInstructionButton");
        royalGameOfUrInstructionButton.setFont(FONT20);
        GridPane.setHalignment(royalGameOfUrInstructionButton, HPos.LEFT);
        royalGameOfUrInstructionButton.setOnAction((e) -> royalGameOfUrInstructionButtonAction());
        grid.add(royalGameOfUrInstructionButton, 5, 4, 2, 1);

        Button royalGameOfUrHighScoresButton = new Button("HighScores");
        royalGameOfUrHighScoresButton.setId("RGOUHighScoresButton");
        royalGameOfUrHighScoresButton.setFont(FONT20);
        GridPane.setHalignment(royalGameOfUrHighScoresButton, HPos.RIGHT);
        royalGameOfUrHighScoresButton.setOnAction((e) -> royalGameOfUrHighScoresButtonAction());
        grid.add(royalGameOfUrHighScoresButton, 6, 4);

        Button logButton = new Button();
        if (GamePlatform.getLoggedIn()) {
            logButton.setText("LOGOUT");
        } else {
            logButton.setText("LOGIN");
        }
        logButton.setId("LogButton");
        logButton.setFont(FONT15);
        logButton.setOnAction((e) -> logButtonAction());
        GridPane.setHalignment(logButton, HPos.CENTER);
        grid.add(logButton, 8, 0);
    }

    private void logButtonAction() {
        if (GamePlatform.getLoggedIn()) {
            GamePlatform.setPlayerName("");
            GamePlatform.setLoggedIn(false);
            clearGrid();
            LogPanel logPanel = new LogPanel(grid);
            grid = logPanel.newLogPanel();
        } else {
            clearGrid();
            LogPanel logPanel = new LogPanel(grid);
            grid = logPanel.newLogPanel();
        }
    }

    private void royalGameOfUrPlayGameButtonAction() {
        clearGrid();
        RGOUMenu rgouMenu = new RGOUMenu(grid);
        grid = rgouMenu.newMenu();
    }

    private void royalGameOfUrInstructionButtonAction() {
        clearGrid();
        InstructionMenu menu = new InstructionMenu(grid);
        grid = menu.newInstruction();
    }

    private void royalGameOfUrHighScoresButtonAction() {

    }

}
