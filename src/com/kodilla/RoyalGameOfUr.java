package com.kodilla;

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

    RoyalGameOfUr(GridPane grid, boolean ifOnePlayerGame) {
        this.grid = grid;
        onePlayerGame = ifOnePlayerGame;
        this.configurator = new RGOUConfigurator(grid);

    }

    GridPane newGame() {

        configurator.configure();
        ((Button) (grid.lookup("#RollButton"))).setOnAction((e) -> {
            rollButtonAction();
        });
        ((Button) (grid.lookup("#MainMenuButton"))).setOnAction((e) -> mainMenuButtonAction());
        ((Button) (grid.lookup("#NewGameButton"))).setOnAction((e) -> newGameButtonAction());
        setOnActionAllPawns();
        setWhichPlayerTurn("GREEN");
        grid.setOnMouseMoved(e -> {
            if (onePlayerGame && whichPlayerTurn.equals("BLUE")) {
                computerMove();
            }
        });

        return grid;

    }

    private void setIfMove(boolean value) {
        if (value) {
            ifMove = true;
            ((Label) grid.lookup("#WhichActionLabel")).setText("MOVE PAWN");
        } else {
            ifMove = false;
            ((Label) grid.lookup("#WhichActionLabel")).setText("ROLL");
        }
    }

    private void setWhichPlayerTurn(String player) {
        whichPlayerTurn = player;
        ((Label) grid.lookup("#WhichTurnLabel")).setText(player + " player turn:");

    }

    private void setOnActionAllPawns() {
        for (Node node : grid.getChildren()) {
            if (node instanceof Pawn) {
                if (((Pawn) node).getPawnColor().equals("GREEN")) {
                    ((Pawn) node).setOnAction((e) -> {
                        if (ifMove && whichPlayerTurn.equals("GREEN")) {
                            greenPawnMove((Pawn) node);
                        }
                        /*if (onePlayerGame && whichPlayerTurn.equals("BLUE")) {
                            computerMove();
                        }*/
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
        ((Label) grid.lookup("#WarningsLabel")).setText("");

        if (!ifMove && firstMovePlayerSelected) {
            fieldsToMove = convertCoinsIntoPoints(coinsToss());
            if (!ifPlayerMoveImpossible()) {
                setIfMove(true);
            } else {
                fieldsToMove = 0;
                ((Label) grid.lookup("#WarningsLabel")).setText(whichPlayerTurn + " player has no possible moves.\n" +
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
        resetStaticFields();

        MainMenu menu = new MainMenu(grid);
        grid = menu.newMainMenu();

    }

    private void newGameButtonAction() {

        configurator.clearGrid();
        resetStaticFields();

        RGOUMenu rgouMenu = new RGOUMenu(grid);
        grid = rgouMenu.newMenu();

    }

    private void resetStaticFields() {

        bluePawnsOnStart = 7;
        greenPawnsOnStart = 7;
        bluePawnsAtFinish = 0;
        greenPawnsAtFinish = 0;
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
        ((Label) grid.lookup("#WarningsLabel")).setText("");

        if (whichPlayerTurn.equals("GREEN") && greenPlayerDrawResult == 0) {
            greenPlayerDrawResult = convertCoinsIntoPoints(coinsToss());
            ((Label) grid.lookup("#WarningsLabel")).setText("Green result: " + greenPlayerDrawResult);
        }
        if (whichPlayerTurn.equals("BLUE") && bluePlayerDrawResult == 0) {
            bluePlayerDrawResult = convertCoinsIntoPoints(coinsToss());

            ((Label) grid.lookup("#WarningsLabel")).setText(((Label) grid.lookup("#WarningsLabel")).getText()
                    + "\nBlue result: " + bluePlayerDrawResult);
        }
        if (!onePlayerGame) {
            setWhichPlayerTurn("BLUE");
        } else {
            bluePlayerDrawResult = convertCoinsIntoPoints(coinsToss());
            ((Label) grid.lookup("#WarningsLabel")).setText(((Label) grid.lookup("#WarningsLabel")).getText()
                    + "\nBlue result: " + bluePlayerDrawResult);

        }
        if (greenPlayerDrawResult != 0 && bluePlayerDrawResult != 0) {
            if (bluePlayerDrawResult == greenPlayerDrawResult) {
                bluePlayerDrawResult = 0;
                greenPlayerDrawResult = 0;

                ((Label) grid.lookup("#WarningsLabel")).setText(((Label) grid.lookup("#WarningsLabel")).getText()
                        + "\nDraw - repeat first move player selection");

                setWhichPlayerTurn("GREEN");

            } else if (bluePlayerDrawResult > greenPlayerDrawResult) {
                setWhichPlayerTurn("BLUE");
                firstMovePlayerSelected = true;
                setIfMove(false);
                ((Label) grid.lookup("#WarningsLabel")).setText(((Label) grid.lookup("#WarningsLabel")).getText()
                        + "\nBlue player starts game");
            } else {
                setWhichPlayerTurn("GREEN");
                firstMovePlayerSelected = true;
                setIfMove(false);
                ((Label) grid.lookup("#WarningsLabel")).setText(((Label) grid.lookup("#WarningsLabel")).getText()
                        + "\nGreen player starts game");
            }
        }

        if (onePlayerGame && whichPlayerTurn.equals("BLUE")) {
            fieldsToMove = convertCoinsIntoPoints(coinsToss());
            ((Label) grid.lookup("#WarningsLabel")).setText("Blue draws " + fieldsToMove + " points.\n" +
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
                    ((Label) grid.lookup("#WarningsLabel")).setText("You can't move this pawn - " +
                            "it has finished its journey over board!");
                }
            });
            if (greenPawnsAtFinish == 7) {
                ((Label) grid.lookup("#WhichTurnLabel")).setText("PLAYER GREEN");
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
                    ((Label) grid.lookup("#WarningsLabel")).setText("You can't move this pawn - " +
                            "it has finished its journey over board!");
                }
            });
            if (bluePawnsAtFinish == 7) {
                ((Label) grid.lookup("#WhichTurnLabel")).setText("PLAYER BLUE");

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
        ((Label) grid.lookup("#WarningsLabel")).setText("");
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
                    ((Label) grid.lookup("#WarningsLabel")).setText("You can't move this pawn, cause on final field is your another pawn");
                }
                if (((Field) node).getIsBusyByBlue() && ((Field) node).getIsHighlighted()) {
                    ((Label) grid.lookup("#WarningsLabel")).setText("You can't knock pawn on highlighted field");
                }

                if (((Field) node).getIsBusyByBlue() && !((Field) node).getIsHighlighted()) {
                    setConstraints(((Field) node).getOpponentPawnFromField(grid, pawn.getPosition() + fieldsToMove), 7, 5);
                    (((Field) node).getOpponentPawnFromField(grid, pawn.getPosition() + fieldsToMove)).putPawnOnStart();
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
        if (onePlayerGame && whichPlayerTurn.equals("BLUE")) {
            fieldsToMove = convertCoinsIntoPoints(coinsToss());
            ((Label) grid.lookup("#WarningsLabel")).setText("Blue draws " + fieldsToMove + " points.\n" +
                    "Waiting for Blue's move.");
        }
    }

    private void bluePawnMove(Pawn pawn) {
        ((Label) grid.lookup("#WarningsLabel")).setText("");
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
                    ((Label) grid.lookup("#WarningsLabel")).setText("You can't move this pawn, cause on final field is your another pawn");
                }

                if (((Field) node).getIsBusyByGreen() && ((Field) node).getIsHighlighted()) {

                    ((Label) grid.lookup("#WarningsLabel")).setText("You can't knock pawn on highlighted field");
                }
                if (((Field) node).getIsBusyByGreen() && !((Field) node).getIsHighlighted()) {
                    setConstraints(((Field) node).getOpponentPawnFromField(grid, pawn.getPosition() + fieldsToMove), 7, 3);
                    (((Field) node).getOpponentPawnFromField(grid, pawn.getPosition() + fieldsToMove)).putPawnOnStart();
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
        if (onePlayerGame && whichPlayerTurn.equals("BLUE")) {
            fieldsToMove = convertCoinsIntoPoints(coinsToss());
            ((Label) grid.lookup("#WarningsLabel")).setText("Blue draws " + fieldsToMove + " points.\n" +
                    "Waiting for Blue's move.");
        }
    }

    private void endOfGameAction() {

        ((Label) grid.lookup("#WhichActionLabel")).setText("WON");
        for (Node node : grid.getChildren()) {
            if (node instanceof Button
                    && !(((node)).getId().equals("MainMenuButton") || (node.getId().equals("NewGameButton")))) {
                ((Button) node).setOnAction((e) ->
                        ((Label) grid.lookup("#WarningsLabel")).setText("The game has finished!"));
            }
        }
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

            ((Label) grid.lookup("#WarningsLabel")).setText(whichPlayerTurn + " player has no possible moves.\n" +
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
