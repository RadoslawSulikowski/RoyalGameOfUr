package com.kodilla;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.util.Random;

import static javafx.scene.paint.Color.*;
import static javafx.scene.paint.Color.SILVER;

class RoyalGameOfUr {

    private GridPane grid;
    private Random generator = new Random();
    private boolean firstMovePlayerSelected = false;
    private int bluePlayerDrawResult = 0;
    private int greenPlayerDrawResult = 0;
    private RGOUConfigurator configurator;


    RoyalGameOfUr(GridPane grid) {
        this.grid = grid;
        this.configurator = new RGOUConfigurator(grid);
    }

    GridPane newGame() {

        configurator.configure();
        ((Button) (grid.lookup("#RollButton"))).setOnAction((e) -> rollButtonAction());
        ((Button) (grid.lookup("#MainMenuButton"))).setOnAction((e) -> mainMenuButtonAction());
        ((Button) (grid.lookup("#NewGameButton"))).setOnAction((e) -> newGameButtonAction());
        Pawn.setWhichPlayerTurnAllPawns(grid, "GREEN");

        return grid;

    }
    private void rollButtonAction() {

        if (!Pawn.getIfMoveFromPawn(grid) && firstMovePlayerSelected) {
            Pawn.setFieldsToMoveAllPawns(grid, convertCoinsIntoPoints(coinsToss()));
            Pawn.setIfMoveAllPawns(grid, true);
        }
        if (!firstMovePlayerSelected) {
            firstMovePlayerSelection();
        }
    }

    private void mainMenuButtonAction() {

        configurator.clearGrid();

        MainMenu menu = new MainMenu(grid);
        grid = menu.newMainMenu();

    }

    private void newGameButtonAction() {

        configurator.clearGrid();

        RGOUMenu rgouMenu = new RGOUMenu(grid);
        grid = rgouMenu.newMenu();

    }


    private int coinsToss() {
        int goldCoins = 0;
        for (int i = 11; i < 14; i++) {
            if (generator.nextBoolean() ) {
                (getCoinFromSecondRow(i)).setFill(GOLD);
                goldCoins += 1;
            } else {
                getCoinFromSecondRow(i).setFill(SILVER);
            }
        }
        return goldCoins;
    }

    private int convertCoinsIntoPoints(int goldCoins) {
        if (goldCoins == 0) {
            return 30;
        }
        if (goldCoins == 1) {
            return 100;
        }
        if (goldCoins == 2) {
            return 20;
        }
        if (goldCoins == 3) {
            return 40;
        }
        return 0;
    }

    private void firstMovePlayerSelection() {

        if (Pawn.getWhichPlayerTurnFromPawn(grid).equals("GREEN") && greenPlayerDrawResult == 0) {
            greenPlayerDrawResult = convertCoinsIntoPoints(coinsToss());
        }
        if (Pawn.getWhichPlayerTurnFromPawn(grid).equals("BLUE") && bluePlayerDrawResult == 0) {
            bluePlayerDrawResult = convertCoinsIntoPoints(coinsToss());
        }
        Pawn.setWhichPlayerTurnAllPawns(grid, "BLUE");
        if (greenPlayerDrawResult != 0 && bluePlayerDrawResult != 0) {
            if (bluePlayerDrawResult == greenPlayerDrawResult) {
                bluePlayerDrawResult = 0;
                greenPlayerDrawResult = 0;
                Pawn.setWhichPlayerTurnAllPawns(grid, "GREEN");
            }
            if (bluePlayerDrawResult > greenPlayerDrawResult) {
                Pawn.setWhichPlayerTurnAllPawns(grid, "BLUE");
                firstMovePlayerSelected = true;
                Pawn.setIfMoveAllPawns(grid, false);
            }
            if (bluePlayerDrawResult < greenPlayerDrawResult) {
                Pawn.setWhichPlayerTurnAllPawns(grid, "GREEN");
                firstMovePlayerSelected = true;
                Pawn.setIfMoveAllPawns(grid, false);
            }
        }
    }

    private Coin getCoinFromSecondRow(int col) {
        for (Node node : grid.getChildren()) {
            if (node instanceof Coin && GridPane.getColumnIndex(node) == col) {
                return (Coin) node;
            }
        }
        return null;
    }

}
