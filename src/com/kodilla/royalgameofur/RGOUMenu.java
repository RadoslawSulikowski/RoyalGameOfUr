package com.kodilla.royalgameofur;

import com.kodilla.MainMenu;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import static com.kodilla.Configurator.*;

public class RGOUMenu {

    private Label rGOUMenuLabel1 = createLabel("CHOOSE GAME MODE:", FONT25);
    private Button onePlayerButton = createButton("ONE PLAYER", FONT30, 250, (e) -> onePlayerButtonAction());
    private Button twoPlayersButton = createButton("TWO PLAYERS", FONT30, 250, (e) -> twoPlayersButtonAction());
    private Button mainMenuButton = createButton("MAIN MENU", FONT30, 250, (e) -> mainMenuButtonAction());
    private Button oneRoundButton = createButton("1 ROUND", FONT30, 250, e -> gameModeButtonAction((ActionEvent) e));
    private Button threeRoundsButton = createButton("3 ROUNDS", FONT30, 250, e -> gameModeButtonAction((ActionEvent) e));
    private Button fiveRoundsButton = createButton("5 ROUNDS", FONT30, 250, e -> gameModeButtonAction((ActionEvent) e));
    private Button normalGameButton = createButton("NORMAL", FONT30, 150, e -> normalGameButtonAction());
    private Button hardGameButton = createButton("HARD", FONT30, 150, e -> hardGameButtonAction());

    private GridPane grid;
    private double numberOfRows = 20;
    private double numberOfColumns = 7;
    private int roundsToPlay = 0;

    public RGOUMenu(GridPane grid) {
        this.grid = grid;
    }

    public GridPane newMenu() {
        setRowsAndColumns(grid, numberOfRows, numberOfColumns);
        addLabels();
        addButtons();

        return grid;
    }

    private void addLabels() {
        grid.add(rGOUMenuLabel1, 3, 1);
    }

    private void addButtons() {
        grid.add(oneRoundButton, 1, 3);
        grid.add(threeRoundsButton, 3, 3);
        grid.add(fiveRoundsButton, 5, 3);
        grid.add(mainMenuButton, 3, 18);
    }

    private void gameModeButtonAction(ActionEvent event) {
        Object button = event.getSource();
        if (button.equals(oneRoundButton)) {
            grid.getChildren().remove(threeRoundsButton);
            grid.getChildren().remove(fiveRoundsButton);
            roundsToPlay = 1;
            GridPane.setConstraints(oneRoundButton, 3, 3);
            GridPane.setHalignment(oneRoundButton, HPos.CENTER);
        }

        if (button.equals(threeRoundsButton)) {
            grid.getChildren().remove(oneRoundButton);
            grid.getChildren().remove(fiveRoundsButton);
            roundsToPlay = 3;
            GridPane.setConstraints(threeRoundsButton, 3, 3);
            GridPane.setHalignment(threeRoundsButton, HPos.CENTER);
        }

        if (button.equals(fiveRoundsButton)) {
            grid.getChildren().remove(oneRoundButton);
            grid.getChildren().remove(threeRoundsButton);
            roundsToPlay = 5;
            GridPane.setConstraints(fiveRoundsButton, 3, 3);
            GridPane.setHalignment(fiveRoundsButton, HPos.CENTER);
        }

        grid.getChildren().remove(normalGameButton);
        grid.getChildren().remove(hardGameButton);

        if (!grid.getChildren().contains(onePlayerButton)) {
            grid.add(onePlayerButton, 2, 6);
            GridPane.setHalignment(onePlayerButton, HPos.LEFT);
        } else {
            GridPane.setConstraints(onePlayerButton, 2, 6);
            GridPane.setHalignment(onePlayerButton, HPos.LEFT);
        }
        if (!grid.getChildren().contains(twoPlayersButton)) {
            grid.add(twoPlayersButton, 4, 6);
        }
    }

    private void normalGameButtonAction() {
        clearGrid(grid, numberOfRows, numberOfColumns);
        RoyalGameOfUr royalGameOfUr = new RoyalGameOfUr(grid, roundsToPlay, true, false);
        grid = royalGameOfUr.newGame();
    }

    private void hardGameButtonAction() {
        clearGrid(grid, numberOfRows, numberOfColumns);
        RoyalGameOfUr royalGameOfUr = new RoyalGameOfUr(grid, roundsToPlay, true, true);
        grid = royalGameOfUr.newGame();
    }

    private void onePlayerButtonAction() {
        grid.getChildren().remove(twoPlayersButton);
        GridPane.setConstraints(onePlayerButton, 3, 6);

        if (!grid.getChildren().contains(normalGameButton) && !grid.getChildren().contains(normalGameButton)) {
            grid.add(normalGameButton, 2, 9);
            grid.add(hardGameButton, 4, 9);
        }
        GridPane.setHalignment(normalGameButton, HPos.CENTER);
        GridPane.setHalignment(hardGameButton, HPos.CENTER);
    }

    private void twoPlayersButtonAction() {
        clearGrid(grid, numberOfRows, numberOfColumns);
        RoyalGameOfUr royalGameOfUr = new RoyalGameOfUr(grid, roundsToPlay, false, false);
        grid = royalGameOfUr.newGame();
    }

    private void mainMenuButtonAction() {
        clearGrid(grid, numberOfRows, numberOfColumns);
        MainMenu menu = new MainMenu(grid);
        grid = menu.newMainMenu();
    }
}
