package com.gameplatform.RoyalGameOfUr;

import com.gameplatform.HighScore;
import com.gameplatform.MainMenu;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;

import java.util.ArrayList;
import java.util.List;

import static com.gameplatform.Configurator.*;
import static com.gameplatform.GamePlatform.*;
import static javafx.scene.paint.Color.*;

public class RGOUHighScores {
    private GridPane grid;
    private double numberOfRows = 23;
    private double numberOfColumns = 7;

    private Label highScoresLabel = createLabel("HIGH SCORES", FONT30);
    private Label placeLabel = createLabel("PLACE", FONT30, ORANGE);
    private Label playerLabel = createLabel("PLAYER", FONT30, ORANGE);
    private Label scoresLabel = createLabel("SCORES", FONT30, ORANGE);
    private Button oneRoundButton = createButton("1 ROUND GAME", "ONE", FONT20, 150, e -> gameModeButtonAction((ActionEvent) e));
    private Button threeRoundsButton = createButton("3 ROUND GAME", "THREE", FONT20, 150, e -> gameModeButtonAction((ActionEvent) e));
    private Button fiveRoundsButton = createButton("5 ROUND GAME", "FIVE", FONT20, 150, e -> gameModeButtonAction((ActionEvent) e));
    private Button mainMenuButton = createButton("Main Menu", "MainMenuButton", FONT30, e -> mainMenuButtonAction());

    public RGOUHighScores(GridPane grid) {
        this.grid = grid;
    }

    public GridPane newHighScores() {
        setRowsAndColumns(grid, numberOfRows, numberOfColumns);
        addLabels();
        addButtons();
        return grid;
    }

    private Label createCellLabel(Paint color) {
        Label cellLabel = new Label();
        cellLabel.setFont(FONT20);
        cellLabel.setTextFill(color);
        GridPane.setHalignment(cellLabel, HPos.CENTER);

        return cellLabel;
    }

    private void addLabels() {
        grid.add(highScoresLabel, 3, 1);
        grid.add(placeLabel, 1, 5);
        grid.add(playerLabel, 3, 5);
        grid.add(scoresLabel, 5, 5);
        GridPane.setHalignment(highScoresLabel, HPos.CENTER);
        GridPane.setHalignment(placeLabel, HPos.CENTER);
        GridPane.setHalignment(playerLabel, HPos.CENTER);
        GridPane.setHalignment(scoresLabel, HPos.CENTER);
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

        GridPane.setHalignment(oneRoundButton, HPos.LEFT);
        GridPane.setHalignment(threeRoundsButton, HPos.CENTER);
        GridPane.setHalignment(fiveRoundsButton, HPos.RIGHT);
        GridPane.setHalignment(mainMenuButton, HPos.CENTER);
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
        clearGrid(grid, numberOfRows, numberOfColumns);
        MainMenu menu = new MainMenu(grid);
        grid = menu.newMainMenu();
    }
}


