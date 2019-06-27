package com.kodilla.royalgameofur;

import com.kodilla.HighScore;
import com.kodilla.MainMenu;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.TextAlignment;

import java.util.*;

import static com.kodilla.GamePlatform.*;
import static javafx.scene.paint.Color.*;

public class RGOUHighScores {
    private GridPane grid;
    private double numberOfRows = 23;
    private double numberOfColumns = 7;

    private Label highScoresLabel = createHighScoresLabel();
    private Label placeLabel = createPlaceLabel();
    private Label playerLabel = createPlayerLabel();
    private Label scoresLabel = createScoresLabel();
    private Button oneRoundButton = createOneRoundButton();
    private Button threeRoundsButton = createThreeRoundsButton();
    private Button fiveRoundsButton = createFiveRoundsButton();
    private Button mainMenuButton = createMainMenuButton();

    public RGOUHighScores(GridPane grid) {
        this.grid = grid;
    }

    public GridPane newHighScores() {
        setRowsAndColumns();
        addLabels();
        addButtons();
        return grid;
    }


    private Label createHighScoresLabel() {
        Label highScoresLabel = new Label();
        highScoresLabel.setFont(FONT30);
        highScoresLabel.setTextFill(WHITE);
        highScoresLabel.setText("HIGH SCORES");
        GridPane.setHalignment(highScoresLabel, HPos.CENTER);


        return highScoresLabel;
    }

    private Label createPlaceLabel() {
        Label placeLabel = new Label();
        placeLabel.setFont(FONT30);
        placeLabel.setTextFill(ORANGE);
        placeLabel.setText("PLACE");
        GridPane.setHalignment(placeLabel, HPos.CENTER);
        return placeLabel;
    }

    private Label createPlayerLabel() {
        Label playerLabel = new Label();
        playerLabel.setFont(FONT30);
        playerLabel.setTextFill(ORANGE);
        playerLabel.setText("PLAYER");
        GridPane.setHalignment(playerLabel, HPos.CENTER);


        return playerLabel;
    }

    private Label createScoresLabel() {
        Label scoresLabel = new Label();
        scoresLabel.setFont(FONT30);
        scoresLabel.setTextFill(ORANGE);
        scoresLabel.setText("SCORES");
        GridPane.setHalignment(scoresLabel, HPos.CENTER);

        return scoresLabel;
    }

    private Label createCellLabel(Paint color) {

        Label cellLabel = new Label();
        cellLabel.setFont(FONT20);
        cellLabel.setTextFill(color);
        GridPane.setHalignment(cellLabel, HPos.CENTER);

        return cellLabel;
    }

    private Button createOneRoundButton() {
        Button oneRoundButton = new Button("1 ROUND GAME");
        oneRoundButton.setId("ONE");
        oneRoundButton.setMinWidth(150);
        oneRoundButton.setFont(FONT20);
        oneRoundButton.setTextAlignment(TextAlignment.CENTER);
        GridPane.setHalignment(oneRoundButton, HPos.LEFT);
        oneRoundButton.setOnAction(this::gameModeButtonAction);
        return oneRoundButton;
    }

    private Button createThreeRoundsButton() {
        Button threeRoundsButton = new Button("3 ROUNDS GAME");
        threeRoundsButton.setId("THREE");
        threeRoundsButton.setMinWidth(150);
        threeRoundsButton.setFont(FONT20);
        threeRoundsButton.setTextAlignment(TextAlignment.CENTER);
        GridPane.setHalignment(threeRoundsButton, HPos.CENTER);
        threeRoundsButton.setOnAction(this::gameModeButtonAction);
        return threeRoundsButton;
    }

    private Button createFiveRoundsButton() {
        Button fiveRoundsButton = new Button("5 ROUNDS GAME");
        fiveRoundsButton.setId("FIVE");
        fiveRoundsButton.setMinWidth(150);
        fiveRoundsButton.setFont(FONT20);
        fiveRoundsButton.setTextAlignment(TextAlignment.CENTER);
        GridPane.setHalignment(fiveRoundsButton, HPos.RIGHT);
        fiveRoundsButton.setOnAction(this::gameModeButtonAction);

        return fiveRoundsButton;
    }

    private Button createMainMenuButton() {
        Button mainMenuButton = new Button("Main Menu");
        mainMenuButton.setId("MainMenuButton");
        mainMenuButton.setFont(FONT30);
        GridPane.setHalignment(mainMenuButton, HPos.CENTER);
        mainMenuButton.setOnAction((e) -> mainMenuButtonAction());
        return mainMenuButton;
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
        grid.add(highScoresLabel, 3, 1);
        grid.add(placeLabel, 1, 5);
        grid.add(playerLabel, 3, 5);
        grid.add(scoresLabel, 5, 5);
        Paint color;
        for (int i = 6; i < 21; i++) {
            for (int j = 1; j < 6; j += 2) {
                if (i % 2 == 0) {
                    color = GRAY;
                } else {
                    color = SILVER;
                }
                Label cellLabel = createCellLabel(color);
                if (j == 1) {
                    cellLabel.setText("" + (i - 5));
                }
                cellLabel.setId("CELL" + j + i);
                grid.add(cellLabel, j, i);
            }
        }

    }

    private void addButtons() {
        grid.add(oneRoundButton, 2, 3, 1, 2);
        grid.add(threeRoundsButton, 3, 3, 1, 2);
        grid.add(fiveRoundsButton, 4, 3, 1, 2);
        grid.add(mainMenuButton, 3, 21, 1, 2);
    }

    private void gameModeButtonAction(ActionEvent event) {

        String buttonId = ((Button) event.getSource()).getId();
        List<HighScore> list = new ArrayList<>();
        int highScoresRow = 6;

        if (buttonId.equals("ONE")) {
            list.addAll(getOneGameHighScoresArrayList());
        }
        if (buttonId.equals("THREE")) {
            list.addAll(getThreeGameHighScoresArrayList());
        }
        if (buttonId.equals("FIVE")) {
            list.addAll(getFiveGameHighScoresArrayList());
        }

        for (HighScore highScore : list) {

            ((Label) grid.lookup("#CELL3" + highScoresRow)).setText(highScore.getPlayerName());
            ((Label) grid.lookup("#CELL5" + highScoresRow)).setText("" + highScore.getScores());
            ++highScoresRow;
        }
        for (int i = highScoresRow; i < 21; i++) {

            ((Label) grid.lookup("#CELL3" + i)).setText("");
            ((Label) grid.lookup("#CELL5" + i)).setText("");
        }
    }

    private void mainMenuButtonAction() {
        clearGrid();
        MainMenu menu = new MainMenu(grid);
        grid = menu.newMainMenu();
    }
}


