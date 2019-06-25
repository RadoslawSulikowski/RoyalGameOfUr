package com.kodilla.royalgameofur;

import com.kodilla.MainMenu;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.util.Random;

import static javafx.scene.layout.GridPane.setConstraints;
import static javafx.scene.paint.Color.*;
import static javafx.scene.paint.Color.SILVER;

class RoyalGameOfUr {

    private GridPane grid;
    private RGOUConfigurator configurator;
    private Random generator = new Random();
    private boolean firstMovePlayerSelected = false;
    private boolean ifMove = false;
    private boolean onePlayerGame;
    private int bluePlayerDrawResult = 0;
    private int greenPlayerDrawResult = 0;
    private int fieldsToMove = 0;
    private String whichPlayerTurn = "";
    private static int bluePawnsOnStart = 7;
    private static int greenPawnsOnStart = 7;
    private static int bluePawnsAtFinish = 0;
    private static int greenPawnsAtFinish = 0;
    private static int roundsToPlay = 0;
    private static int roundsPlayed = 0;
    private static int greenPoints = 0;
    private static int bluePoints = 0;


    RoyalGameOfUr(GridPane grid, boolean ifOnePlayerGame, int roundsToPlay) {
        this.grid = grid;
        RoyalGameOfUr.roundsToPlay = roundsToPlay;
        onePlayerGame = ifOnePlayerGame;
        this.configurator = new RGOUConfigurator(grid);

    }

    GridPane newGame() {

        configurator.configure();
        configurator.rollButton.setOnAction(e -> rollButtonAction());
        configurator.mainMenuButton.setOnAction(e -> mainMenuButtonAction());
        configurator.newGameButton.setOnAction(e -> newGameButtonAction());
        setOnActionAllPawns();
        configurator.gamesToPlayLabel.setText("GAMES TO PLAY:    " + roundsToPlay);
        configurator.scoresLabel.setText("GREEN    " + greenPoints + " : " + bluePoints + "    BLUE");
        setWhichPlayerTurn("GREEN");
        grid.setOnMouseMoved(e -> {
            if (onePlayerGame && whichPlayerTurn.equals("BLUE") && bluePawnsAtFinish != 7 && greenPawnsAtFinish != 7) {
                computerMove();
            }
        });

        return grid;

    }

    private void setIfMove(boolean value) {
        if (value) {
            ifMove = true;
            configurator.whichActionLabel.setText("MOVE PAWN");
        } else {
            ifMove = false;
            configurator.whichActionLabel.setText("ROLL");
        }
    }

    private void setWhichPlayerTurn(String player) {
        whichPlayerTurn = player;
        configurator.whichTurnLabel.setText(player + " player turn:");

    }

    private void setOnActionAllPawns() {
        for (Node node : grid.getChildren()) {
            if (node instanceof Pawn) {
                if (((Pawn) node).getPawnColor().equals("GREEN")) {
                    ((Pawn) node).setOnAction((e) -> {
                        if (ifMove && whichPlayerTurn.equals("GREEN")) {
                            greenPawnMove((Pawn) node);
                        }
                    });
                }
                if (((Pawn) node).getPawnColor().equals("BLUE") && !onePlayerGame) {
                    ((Pawn) node).setOnAction((e) -> {
                        if (ifMove && whichPlayerTurn.equals("BLUE")) {
                            bluePawnMove((Pawn) node);
                        }
                    });
                }
            }
        }
    }

    private void rollButtonAction() {
        configurator.warningsLabel.setText("");

        if (!ifMove && firstMovePlayerSelected) {
            fieldsToMove = convertCoinsIntoPoints(coinsToss());
            if (!ifPlayerMoveImpossible()) {
                setIfMove(true);
            } else {
                fieldsToMove = 0;
                configurator.warningsLabel.setText(whichPlayerTurn + " player has no possible moves.\n" +
                        whichPlayerTurn + " lost his turn.");
                if (whichPlayerTurn.equals("GREEN")) {

                    setWhichPlayerTurn("BLUE");
                } else {
                    setWhichPlayerTurn("GREEN");
                }
            }
        }
        if (!firstMovePlayerSelected) {
            firstMovePlayerSelection();
        }
    }

    private void mainMenuButtonAction() {

        configurator.clearGrid();
        resetFields();

        MainMenu menu = new MainMenu(grid);
        grid = menu.newMainMenu();

    }

    private void newGameButtonAction() {

        configurator.clearGrid();
        resetFields();

        RGOUMenu rgouMenu = new RGOUMenu(grid);
        grid = rgouMenu.newMenu();

    }

    private void nextGameButtonAction() {
        configurator.clearGrid();
        configurator.configure();
        configurator.rollButton.setOnAction(e -> rollButtonAction());
        configurator.mainMenuButton.setOnAction(e -> mainMenuButtonAction());
        configurator.newGameButton.setOnAction(e -> newGameButtonAction());
        configurator.whichActionLabel.setText("ROLL to draw who will start");
        whichPlayerTurn = "GREEN";
        setOnActionAllPawns();
        firstMovePlayerSelected = false;
        ifMove = false;
        resetPawnsOnStartAndAtFinish();
        bluePlayerDrawResult = 0;
        greenPlayerDrawResult = 0;
        fieldsToMove = 0;
    }

    private void resetPawnsOnStartAndAtFinish() {

        bluePawnsOnStart = 7;
        greenPawnsOnStart = 7;
        bluePawnsAtFinish = 0;
        greenPawnsAtFinish = 0;
    }

    private void resetFields() {

        bluePawnsOnStart = 7;
        greenPawnsOnStart = 7;
        bluePawnsAtFinish = 0;
        greenPawnsAtFinish = 0;
        roundsToPlay = 0;
        roundsPlayed = 0;
        greenPoints = 0;
        bluePoints = 0;
        firstMovePlayerSelected = false;
        ifMove = false;
        bluePlayerDrawResult = 0;
        greenPlayerDrawResult = 0;
        fieldsToMove = 0;
    }


    private int coinsToss() {
        int goldCoins = 0;
        for (int i = 11; i < 14; i++) {
            if (generator.nextBoolean()) {
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
            return 3;
        }
        if (goldCoins == 1) {
            return 1;
        }
        if (goldCoins == 2) {
            return 2;
        }
        if (goldCoins == 3) {
            return 4;
        }
        return 0;
    }

    private void firstMovePlayerSelection() {
        configurator.warningsLabel.setText("");

        if (whichPlayerTurn.equals("GREEN") && greenPlayerDrawResult == 0) {
            greenPlayerDrawResult = convertCoinsIntoPoints(coinsToss());
            configurator.warningsLabel.setText("Green result: " + greenPlayerDrawResult);
        }
        if (whichPlayerTurn.equals("BLUE") && bluePlayerDrawResult == 0) {
            bluePlayerDrawResult = convertCoinsIntoPoints(coinsToss());

            configurator.warningsLabel.setText(((Label) grid.lookup("#WarningsLabel")).getText()
                    + "\nBlue result: " + bluePlayerDrawResult);
        }
        if (!onePlayerGame) {
            setWhichPlayerTurn("BLUE");
        } else {
            bluePlayerDrawResult = convertCoinsIntoPoints(coinsToss());
            configurator.warningsLabel.setText(((Label) grid.lookup("#WarningsLabel")).getText()
                    + "\nBlue result: " + bluePlayerDrawResult);

        }
        if (greenPlayerDrawResult != 0 && bluePlayerDrawResult != 0) {
            if (bluePlayerDrawResult == greenPlayerDrawResult) {
                bluePlayerDrawResult = 0;
                greenPlayerDrawResult = 0;

                configurator.warningsLabel.setText(configurator.warningsLabel.getText()
                        + "\nDraw - repeat first move player selection");

                setWhichPlayerTurn("GREEN");

            } else if (bluePlayerDrawResult > greenPlayerDrawResult) {
                setWhichPlayerTurn("BLUE");
                firstMovePlayerSelected = true;
                setIfMove(false);
                configurator.warningsLabel.setText(configurator.warningsLabel.getText()
                        + "\nBlue player starts game");
            } else {
                setWhichPlayerTurn("GREEN");
                firstMovePlayerSelected = true;
                setIfMove(false);
                configurator.warningsLabel.setText(configurator.warningsLabel.getText()
                        + "\nGreen player starts game");
            }
        }

        if (onePlayerGame && whichPlayerTurn.equals("BLUE")) {
            fieldsToMove = convertCoinsIntoPoints(coinsToss());
            configurator.warningsLabel.setText("Blue draws " + fieldsToMove + " points.\n" +
                    "Waiting for Blue's move.");
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

    private void setFieldFreeOverGreenPawn(int position) {
        for (Node node : grid.getChildren()) {
            if (node instanceof Field && ((Field) node).getFieldNumberForGreen() == position) {
                ((Field) node).setIsBusyByGreen(false);
            }
        }
    }

    private void setFieldFreeOverBluePawn(int position) {
        for (Node node : grid.getChildren()) {
            if (node instanceof Field && ((Field) node).getFieldNumberForBlue() == position) {
                ((Field) node).setIsBusyByBlue(false);
            }
        }
    }

    private void setPawnTextWithNumberOfPawnsOnPosition(int numberOfPawns, int pawnPosition, String pawnColor) {
        for (Node node : grid.getChildren()) {
            if (node instanceof Pawn && ((Pawn) node).getPosition() == pawnPosition && ((Pawn) node).getPawnColor().equals(pawnColor)) {
                ((Pawn) node).setText(((Integer) numberOfPawns).toString());
            }
        }
    }

    private void putGreenPawnOnPosition(Pawn pawn) {
        if (pawn.getPosition() < 18) {
            for (Node node : grid.getChildren()) {
                if (node instanceof Field && ((Field) node).getFieldNumberForGreen() == pawn.getPosition()) {
                    setConstraints(pawn, GridPane.getColumnIndex(node), GridPane.getRowIndex(node));
                    ((Field) node).setIsBusyByGreen(true);
                    if (!((Field) node).getIsHighlighted()) {

                        setWhichPlayerTurn("BLUE");
                    }
                }
            }
        } else {
            setConstraints(pawn, 8, 3);
            pawn.setPosition(18);
            setPawnTextWithNumberOfPawnsOnPosition(++greenPawnsAtFinish, 18, "GREEN");
            pawn.setOnAction((e) -> {
                if (whichPlayerTurn.equals("GREEN")) {
                    configurator.warningsLabel.setText("You can't move this pawn - " +
                            "it has finished its journey over board!");
                }
            });
            if (greenPawnsAtFinish == 7) {
                configurator.whichTurnLabel.setText("PLAYER GREEN");
                endOfGameAction();
            } else {
                setWhichPlayerTurn("BLUE");
            }
        }
        if (greenPawnsAtFinish != 7) {
            setIfMove(false);
        }
    }

    private void putBluePawnOnPosition(Pawn pawn) {
        if (pawn.getPosition() < 18) {
            for (Node node : grid.getChildren()) {
                if (node instanceof Field && ((Field) node).getFieldNumberForBlue() == pawn.getPosition()) {
                    setConstraints(pawn, GridPane.getColumnIndex(node), GridPane.getRowIndex(node));
                    ((Field) node).setIsBusyByBlue(true);
                    if (!((Field) node).getIsHighlighted()) {
                        setWhichPlayerTurn("GREEN");
                    }
                }
            }
        } else {
            setConstraints(pawn, 8, 5);
            pawn.setPosition(18);
            setPawnTextWithNumberOfPawnsOnPosition(++bluePawnsAtFinish, 18, "BLUE");
            pawn.setOnAction((e) -> {
                if (whichPlayerTurn.equals("BLUE")) {
                    configurator.warningsLabel.setText("You can't move this pawn - " +
                            "it has finished its journey over board!");
                }
            });
            if (bluePawnsAtFinish == 7) {
                configurator.whichTurnLabel.setText("PLAYER BLUE");

                endOfGameAction();
            } else {
                setWhichPlayerTurn("GREEN");
            }
        }
        if (bluePawnsAtFinish != 7) {
            setIfMove(false);
        }
    }

    private void greenPawnMove(Pawn pawn) {
        configurator.warningsLabel.setText("");
        if (pawn.getPosition() == 1 && ifFinalFieldFree(pawn.getPosition() + fieldsToMove, "GREEN")) {
            setPawnTextWithNumberOfPawnsOnPosition(--greenPawnsOnStart, 1, "GREEN");
            pawn.setText("");
        }
        for (Node node : grid.getChildren()) {
            if ((pawn.getPosition() + fieldsToMove) >= 18) {
                setFieldFreeOverGreenPawn(pawn.getPosition());
                pawn.setPosition(pawn.getPosition() + fieldsToMove);
                putGreenPawnOnPosition(pawn);
                break;
            }
            if (node instanceof Field && ((Field) node).getFieldNumberForGreen() == (pawn.getPosition() + fieldsToMove)) {
                if (!((Field) node).getIsBusyByBlue() && !((Field) node).getIsBusyByGreen()) {
                    setFieldFreeOverGreenPawn(pawn.getPosition());
                    pawn.setPosition(pawn.getPosition() + fieldsToMove);
                    fieldsToMove = 0;
                    putGreenPawnOnPosition(pawn);
                    break;
                }
                if (((Field) node).getIsBusyByGreen()) {
                    configurator.warningsLabel.setText("You can't move this pawn, cause on final field is your another pawn");
                }
                if (((Field) node).getIsBusyByBlue() && ((Field) node).getIsHighlighted()) {
                    configurator.warningsLabel.setText("You can't knock pawn on highlighted field");
                }

                if (((Field) node).getIsBusyByBlue() && !((Field) node).getIsHighlighted()) {
                    setConstraints(((Field) node).getBluePawnFromField(grid, pawn.getPosition() + fieldsToMove), 7, 5);
                    (((Field) node).getBluePawnFromField(grid, pawn.getPosition() + fieldsToMove)).putPawnOnStart();
                    setPawnTextWithNumberOfPawnsOnPosition(++bluePawnsOnStart, 1, "BLUE");
                    setFieldFreeOverGreenPawn(pawn.getPosition());
                    pawn.setPosition(pawn.getPosition() + fieldsToMove);
                    setFieldFreeOverBluePawn(Field.convertFieldNumberOtherColors(pawn.getPosition()));
                    fieldsToMove = 0;
                    putGreenPawnOnPosition(pawn);
                    break;
                }

            }
        }
        if (onePlayerGame && whichPlayerTurn.equals("BLUE") && bluePawnsAtFinish != 7 && greenPawnsAtFinish != 7) {
            fieldsToMove = convertCoinsIntoPoints(coinsToss());
            configurator.warningsLabel.setText("Blue draws " + fieldsToMove + " points.\n" +
                    "Waiting for Blue's move.");
        }
    }

    private void bluePawnMove(Pawn pawn) {
        configurator.warningsLabel.setText("");
        if (pawn.getPosition() == 1 && ifFinalFieldFree(pawn.getPosition() + fieldsToMove, "BLUE")) {
            setPawnTextWithNumberOfPawnsOnPosition(--bluePawnsOnStart, 1, "BLUE");
            pawn.setText("");
        }
        for (Node node : grid.getChildren()) {
            if ((pawn.getPosition() + fieldsToMove) >= 18) {
                setFieldFreeOverBluePawn(pawn.getPosition());
                pawn.setPosition(pawn.getPosition() + fieldsToMove);
                putBluePawnOnPosition(pawn);
                break;
            }
            if (node instanceof Field && ((Field) node).getFieldNumberForBlue() == (pawn.getPosition() + fieldsToMove)) {
                if (!((Field) node).getIsBusyByBlue() && !((Field) node).getIsBusyByGreen()) {
                    setFieldFreeOverBluePawn(pawn.getPosition());
                    pawn.setPosition(pawn.getPosition() + fieldsToMove);
                    fieldsToMove = 0;
                    putBluePawnOnPosition(pawn);
                    break;
                }
                if (((Field) node).getIsBusyByBlue()) {
                    configurator.warningsLabel.setText("You can't move this pawn, cause on final field is your another pawn");
                }

                if (((Field) node).getIsBusyByGreen() && ((Field) node).getIsHighlighted()) {

                    configurator.warningsLabel.setText("You can't knock pawn on highlighted field");
                }
                if (((Field) node).getIsBusyByGreen() && !((Field) node).getIsHighlighted()) {
                    setConstraints(((Field) node).getGreenPawnFromField(grid, pawn.getPosition() + fieldsToMove), 7, 3);
                    (((Field) node).getGreenPawnFromField(grid, pawn.getPosition() + fieldsToMove)).putPawnOnStart();
                    setPawnTextWithNumberOfPawnsOnPosition(++greenPawnsOnStart, 1, "GREEN");
                    setFieldFreeOverBluePawn(pawn.getPosition());
                    pawn.setPosition(pawn.getPosition() + fieldsToMove);
                    setFieldFreeOverGreenPawn(Field.convertFieldNumberOtherColors(pawn.getPosition()));
                    fieldsToMove = 0;
                    putBluePawnOnPosition(pawn);
                    break;
                }

            }
        }
        if (onePlayerGame && whichPlayerTurn.equals("BLUE") && bluePawnsAtFinish != 7 && greenPawnsAtFinish != 7) {
            fieldsToMove = convertCoinsIntoPoints(coinsToss());
            configurator.warningsLabel.setText("Blue draws " + fieldsToMove + " points.\n" +
                    "Waiting for Blue's move.");
        }
    }

    private void endOfGameAction() {

        ++roundsPlayed;
        if (bluePawnsAtFinish == 7) {
            bluePoints += (bluePawnsAtFinish - greenPawnsAtFinish);
        }
        if (greenPawnsAtFinish == 7) {
            greenPoints += (greenPawnsAtFinish - bluePawnsAtFinish);
        }
        if (roundsPlayed < roundsToPlay) {
            configurator.whichActionLabel.setText(" WON THIS ROUND");
            for (Node node : grid.getChildren()) {
                if (node instanceof Button
                        && !(((node)).getId().equals("MainMenuButton") || (node.getId().equals("NewGameButton")))) {
                    ((Button) node).setOnAction((e) ->
                            configurator.warningsLabel.setText("The round has finished!"));
                }
            }
            grid.add(configurator.nextGameButton, 12, 3, 3, 1);
            configurator.nextGameButton.setOnAction(e -> nextGameButtonAction());

        } else {
            configurator.whichActionLabel.setText("WON GAME");
            for (Node node : grid.getChildren()) {
                if (node instanceof Button
                        && !(((node)).getId().equals("MainMenuButton") || (node.getId().equals("NewGameButton")))) {
                    ((Button) node).setOnAction((e) ->
                            configurator.warningsLabel.setText("The game has finished!"));
                }
            }
        }


        configurator.gamesToPlayLabel.setText("GAMES TO PLAY:    " + (roundsToPlay - roundsPlayed));
        configurator.scoresLabel.setText("GREEN    " + greenPoints + " : " + bluePoints + "    BLUE");

    }

    private boolean ifFinalFieldFree(int finalPosition, String pawnColor) {
        for (Node node : grid.getChildren()) {
            if (node instanceof Field
                    && pawnColor.equals("GREEN")
                    && ((Field) node).getFieldNumberForGreen() == finalPosition
                    && ((((Field) node).getIsBusyByGreen()) || ((((Field) node).getIsHighlighted()) && (((Field) node).getIsBusyByBlue())))) {
                return false;
            }
            if (node instanceof Field
                    && pawnColor.equals("BLUE")
                    && ((Field) node).getFieldNumberForBlue() == finalPosition
                    && ((((Field) node).getIsBusyByBlue()) || ((((Field) node).getIsHighlighted()) && (((Field) node).getIsBusyByGreen())))) {
                return false;
            }
        }
        return true;
    }

    private boolean ifPlayerMoveImpossible() {
        boolean result = true;
        for (Node node : grid.getChildren()) {
            if (node instanceof Pawn && ((Pawn) node).getPawnColor().equals(whichPlayerTurn) && ((Pawn) node).getPosition() != 18) {
                result = result && !ifFinalFieldFree((((Pawn) node).getPosition() + fieldsToMove), whichPlayerTurn);
            }
        }
        return result;
    }

    private void delay(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void computerMove() {
        delay(2);
        if (ifPlayerMoveImpossible()) {

            configurator.warningsLabel.setText(whichPlayerTurn + " player has no possible moves.\n" +
                    whichPlayerTurn + " lost his turn.");
            setWhichPlayerTurn("GREEN");
        }
        if (whichPlayerTurn.equals("BLUE")) {

            for (Node node : grid.getChildren()) {
                if (node instanceof Pawn
                        && ((Pawn) node).getPawnColor().equals("BLUE")
                        && ((Pawn) node).getPosition() < 18
                        && ifFinalFieldFree((((Pawn) node).getPosition() + fieldsToMove), "BLUE")) {
                    bluePawnMove((Pawn) node);
                    break;
                }
            }
        }
    }

}
