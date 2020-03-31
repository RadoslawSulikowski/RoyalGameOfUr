package com.gameplatform.RoyalGameOfUr;

import com.gameplatform.HighScore;
import com.gameplatform.MainMenu;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.util.Comparator;
import java.util.Objects;
import java.util.Random;

import static com.gameplatform.Configurator.clearGrid;
import static com.gameplatform.GamePlatform.*;
import static javafx.scene.layout.GridPane.setConstraints;
import static javafx.scene.paint.Color.GOLD;
import static javafx.scene.paint.Color.SILVER;

class RoyalGameOfUr {
    private GridPane grid;
    private RGOUConfigurator configurator;
    private Random generator = new Random();
    private boolean firstMovePlayerSelected = false;
    private boolean ifMove = false;
    private boolean onePlayerGame;
    private boolean hardGame;
    private int bluePlayerDrawResult = 0;
    private int greenPlayerDrawResult = 0;
    private int fieldsToMove = 0;
    private String whichPlayerTurn = "";
    private int bluePawnsOnStart = 7;
    private int greenPawnsOnStart = 7;
    private int bluePawnsAtFinish = 0;
    private int greenPawnsAtFinish = 0;
    private int roundsToPlay;
    private int roundsPlayed = 0;
    private int greenPoints = 0;
    private int bluePoints = 0;

    RoyalGameOfUr(GridPane grid, int roundsToPlay, boolean ifOnePlayerGame, boolean ifHardGame) {
        this.grid = grid;
        this.roundsToPlay = roundsToPlay;
        onePlayerGame = ifOnePlayerGame;
        hardGame = ifHardGame;
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
//        configurator.whichTurnLabel.textProperty().addListener(new ChangeListener<String>() {
//            @Override
//            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
//
//                if (onePlayerGame && whichPlayerTurn.equals("BLUE") && bluePawnsAtFinish != 7 && greenPawnsAtFinish != 7) {
//                    computerMove();
//                }
//            }
//        });
        grid.setOnMouseMoved(e -> {
            if (onePlayerGame && whichPlayerTurn.equals("BLUE") && bluePawnsAtFinish != 7 && greenPawnsAtFinish != 7) {
                computerMove();
            }
        });
        return grid;
    }

    private int getPawnsOnStart(String pawnColor) {
        return pawnColor.equals("GREEN") ? greenPawnsOnStart : bluePawnsOnStart;
    }

    private int getPawnsAtFinish(String pawnColor) {
        return pawnColor.equals("GREEN") ? greenPawnsAtFinish : bluePawnsAtFinish;
    }

    private void incrementPawnsOnStart(String pawnColor) {
        if (pawnColor.equals("GREEN")) {
            ++greenPawnsOnStart;
        } else {
            ++bluePawnsOnStart;
        }
    }

    private void decrementPawnsOnStart(String pawnColor) {
        if (pawnColor.equals("GREEN")) {
            --greenPawnsOnStart;
        } else {
            --bluePawnsOnStart;
        }
    }

    private void incrementPawnsAtFinish(String pawnColor) {
        if (pawnColor.equals("GREEN")) {
            ++greenPawnsAtFinish;
        } else {
            ++bluePawnsAtFinish;
        }
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
                            pawnMove((Pawn) node, "GREEN");
                        }
                    });
                }
                if (((Pawn) node).getPawnColor().equals("BLUE") && !onePlayerGame) {
                    ((Pawn) node).setOnAction((e) -> {
                        if (ifMove && whichPlayerTurn.equals("BLUE")) {
                            pawnMove((Pawn) node, "BLUE");
                        }
                    });
                }
            }
        }
    }

    private void rollButtonAction() {
        configurator.warningsLabel.setText("");

        if (!firstMovePlayerSelected) {
            firstMovePlayerSelection();
        }
        if (!ifMove && firstMovePlayerSelected && !(onePlayerGame && whichPlayerTurn.equals("BLUE"))) {
            fieldsToMove = convertCoinsIntoPoints(coinsToss());
            if (!ifPlayerMoveImpossible()) {
                setIfMove(true);
            } else {
                fieldsToMove = 0;
                configurator.warningsLabel.setText(whichPlayerTurn + " player has no possible moves.\n" +
                        whichPlayerTurn + " lost his turn.");
                if (whichPlayerTurn.equals("GREEN")) {
                    setWhichPlayerTurn("BLUE");
                    if (onePlayerGame && bluePawnsAtFinish != 7 && greenPawnsAtFinish != 7) {
                        fieldsToMove = convertCoinsIntoPoints(coinsToss());
                        configurator.warningsLabel.setText("Blue draws " + fieldsToMove + " points.\n" +
                                "Waiting for Blue's move.");
                    }
                } else {
                    setWhichPlayerTurn("GREEN");
                }
            }
        }
    }

    private void mainMenuButtonAction() {
        clearGrid(grid, configurator.getNumberOfRows(), configurator.getNumberOfColumns());
        resetAllFields();

        MainMenu menu = new MainMenu(grid);
        grid = menu.newMainMenu();
    }

    private void newGameButtonAction() {
        clearGrid(grid, configurator.getNumberOfRows(), configurator.getNumberOfColumns());
        resetAllFields();

        RGOUMenu rgouMenu = new RGOUMenu(grid);
        grid = rgouMenu.newMenu();
    }

    private void nextGameButtonAction() {
        clearGrid(grid, configurator.getNumberOfRows(), configurator.getNumberOfColumns());
        configurator.configure();
        configurator.rollButton.setOnAction(e -> rollButtonAction());
        configurator.mainMenuButton.setOnAction(e -> mainMenuButtonAction());
        configurator.newGameButton.setOnAction(e -> newGameButtonAction());
        configurator.whichActionLabel.setText("ROLL to draw who will start");
        whichPlayerTurn = "GREEN";
        setOnActionAllPawns();
        firstMovePlayerSelected = false;
        ifMove = false;
        resetFieldsOnEndOfRound();
    }

    private void resetFieldsOnEndOfRound() {
        bluePawnsOnStart = 7;
        greenPawnsOnStart = 7;
        bluePawnsAtFinish = 0;
        greenPawnsAtFinish = 0;
        bluePlayerDrawResult = 0;
        greenPlayerDrawResult = 0;
        fieldsToMove = 0;
    }

    private void resetAllFields() {
        resetFieldsOnEndOfRound();
        roundsToPlay = 0;
        roundsPlayed = 0;
        greenPoints = 0;
        bluePoints = 0;
        firstMovePlayerSelected = false;
        ifMove = false;
    }

    private int coinsToss() {
        int goldCoins = 0;
        for (int i = 11; i < 14; i++) {
            if (generator.nextBoolean()) {
                (Objects.requireNonNull(getCoinFromSecondRow(i))).setFill(GOLD);
                goldCoins += 1;
            } else {
                Objects.requireNonNull(getCoinFromSecondRow(i)).setFill(SILVER);
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

    private void setFieldFreeOverPawn(int fieldNumber, String pawnColor) {
        if (pawnColor.equals("GREEN")) {
            getField(fieldNumber, pawnColor).setIsBusyByGreen(false);
        } else {
            getField(fieldNumber, pawnColor).setIsBusyByBlue(false);
        }
    }

    private boolean ifFieldFree(int fieldNumber, String pawnColor) {
        return !(getField(fieldNumber, pawnColor).getIsBusy(pawnColor) ||
                (getField(fieldNumber, pawnColor).getIsHighlighted()) && (getField(fieldNumber, pawnColor).getIsBusy(invertPawnColor(pawnColor))));
    }

    private boolean ifPlayerMoveImpossible() {
        boolean result = true;
        for (Node node : grid.getChildren()) {
            if (node instanceof Pawn && ((Pawn) node).getPawnColor().equals(whichPlayerTurn) && ((Pawn) node).getPosition() != 18) {
                result = result && !ifFieldFree((((Pawn) node).getPosition() + fieldsToMove), whichPlayerTurn);
            }
        }
        return result;
    }

    private boolean ifPawnCanMove(Node node) {
        return node instanceof Pawn
                && ((Pawn) node).getPawnColor().equals("BLUE")
                && ((Pawn) node).getPosition() < 18
                && ifFieldFree((((Pawn) node).getPosition() + fieldsToMove), "BLUE");
    }

    private boolean ifFieldBusyByOpponentsPawnAndHighlighted(Node node, String pawnColor) {
        return ((((Field) node).getIsHighlighted()) && ((Field) node).getIsBusy(invertPawnColor(pawnColor)));
    }

    private Field getField(int fieldNumber, String pawnColor) {
        if (fieldNumber > 18) {
            fieldNumber = 18;
        }
        Field field = null;
        for (Node node : grid.getChildren()) {
            if (node instanceof Field && fieldNumber == ((Field) node).getFieldNumber(pawnColor)) {
                field = (Field) node;
            }
        }
        return field;
    }

    private String invertPawnColor(String pawnColor) {
        if (pawnColor.equals("GREEN")) {
            return "BLUE";
        }
        return "GREEN";
    }

    private void setPawnTextWithNumberOfPawnsOnPosition(int numberOfPawns, int pawnPosition, String pawnColor) {
        for (Node node : grid.getChildren()) {
            if (node instanceof Pawn && ((Pawn) node).getPosition() == pawnPosition && ((Pawn) node).getPawnColor().equals(pawnColor)) {
                ((Pawn) node).setText(((Integer) numberOfPawns).toString());
            }
        }
    }

    private void putPawnOnPosition(Pawn pawn, String pawnColor) {
        if (pawn.getPosition() < 18) {
            Field positionField = getField(pawn.getPosition(), pawnColor);
            setConstraints(pawn, GridPane.getColumnIndex(positionField), GridPane.getRowIndex(positionField));
            if (pawnColor.equals("BLUE")) {
                positionField.setIsBusyByBlue(true);
            } else if (pawnColor.equals("GREEN")) {
                positionField.setIsBusyByGreen(true);
            }
            if (!positionField.getIsHighlighted()) {
                setWhichPlayerTurn(pawnColor.equals("BLUE") ? "GREEN" : "BLUE");
            }
        } else {
            setConstraints(pawn, 8, pawnColor.equals("BLUE") ? 5 : 3);
            pawn.setPosition(18);
            incrementPawnsAtFinish(pawnColor);
            setPawnTextWithNumberOfPawnsOnPosition(getPawnsAtFinish(pawnColor), 18, pawnColor);
            pawn.setOnAction((e) -> {
                if (whichPlayerTurn.equals(pawnColor)) {
                    configurator.warningsLabel.setText("You can't move this pawn - " +
                            "it has finished its journey over board!");
                }
            });
            if (getPawnsAtFinish(pawnColor) == 7) {
                configurator.whichTurnLabel.setText("PLAYER " + pawnColor);

                endOfRoundAction();
            } else {
                setWhichPlayerTurn(pawnColor.equals("BLUE") ? "GREEN" : "BLUE");
            }
        }
        if (getPawnsAtFinish(pawnColor) != 7) {
            setIfMove(false);
        }
    }

    private void pawnMove(Pawn pawn, String pawnColor) {
        configurator.warningsLabel.setText("");
        if (pawn.getPosition() == 1 && ifFieldFree(pawn.getPosition() + fieldsToMove, pawnColor)) {
            decrementPawnsOnStart(pawnColor);
            setPawnTextWithNumberOfPawnsOnPosition(getPawnsOnStart(pawnColor), 1, pawnColor);
            pawn.setText("");
        }
        for (Node node : grid.getChildren()) {
            if ((pawn.getPosition() + fieldsToMove) >= 18) {
                setFieldFreeOverPawn(pawn.getPosition(), pawnColor);
                pawn.setPosition(pawn.getPosition() + fieldsToMove);
                putPawnOnPosition(pawn, pawnColor);
                break;
            }
            if (node instanceof Field && ((Field) node).getFieldNumber(pawnColor) == (pawn.getPosition() + fieldsToMove)) {
                if (!((Field) node).getIsBusy()) {
                    setFieldFreeOverPawn(pawn.getPosition(), pawnColor);
                    pawn.setPosition(pawn.getPosition() + fieldsToMove);
                    fieldsToMove = 0;
                    putPawnOnPosition(pawn, pawnColor);
                    break;
                }
                if (((Field) node).getIsBusy(pawnColor)) {
                    configurator.warningsLabel.setText("You can't move this pawn, cause on final field is your another pawn");
                }
                if (ifFieldBusyByOpponentsPawnAndHighlighted(node, pawnColor)) {
                    configurator.warningsLabel.setText("You can't knock pawn on highlighted field");
                }
                if (((Field) node).getIsBusy(invertPawnColor(pawnColor)) && !((Field) node).getIsHighlighted()) {
                    setConstraints(((Field) node).getPawnFromField(grid, pawn.getPosition() + fieldsToMove, invertPawnColor(pawnColor)), 7, (pawnColor.equals("GREEN") ? 5 : 3));
                    (((Field) node).getPawnFromField(grid, pawn.getPosition() + fieldsToMove, invertPawnColor(pawnColor))).putPawnOnStart();
                    incrementPawnsOnStart(invertPawnColor(pawnColor));
                    setPawnTextWithNumberOfPawnsOnPosition(getPawnsOnStart(invertPawnColor(pawnColor)), 1, invertPawnColor(pawnColor));
                    setFieldFreeOverPawn(pawn.getPosition(), pawnColor);
                    pawn.setPosition(pawn.getPosition() + fieldsToMove);
                    setFieldFreeOverPawn(Field.convertFieldNumberOtherColors(pawn.getPosition()), invertPawnColor(pawnColor));
                    fieldsToMove = 0;
                    putPawnOnPosition(pawn, pawnColor);
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

    private void computerMove() {
        delay();
        if (ifPlayerMoveImpossible()) {
            configurator.warningsLabel.setText(whichPlayerTurn + " player has no possible moves.\n" +
                    whichPlayerTurn + " lost his turn.");
            setWhichPlayerTurn("GREEN");
        }
        if (whichPlayerTurn.equals("BLUE")) {
            if (!hardGame) {
                for (Node node : grid.getChildren()) {
                    if (ifPawnCanMove(node)) {
                        pawnMove((Pawn) node, "BLUE");
                        break;
                    }
                }
            } else {
                hardModeComputerMove();
            }
        }
    }

    private void hardModeComputerMove() {
        boolean moveDone = false;
        for (Node node : grid.getChildren()) {
            if (ifPawnCanMove(node) && getField(((Pawn) node).getPosition() + fieldsToMove, "BLUE").getIsHighlighted()) {
                pawnMove(((Pawn) node), "BLUE");
                moveDone = true;
                break;
            }
        }
        if (!moveDone) {
            for (Node node : grid.getChildren()) {
                if (ifPawnCanMove(node) && getField(((Pawn) node).getPosition() + fieldsToMove, "BLUE").getIsBusy("GREEN")) {
                    pawnMove(((Pawn) node), "BLUE");
                    moveDone = true;
                    break;
                }
            }
        }
        if (!moveDone) {
            for (Node node : grid.getChildren()) {
                if (ifPawnCanMove(node) && !getField(((Pawn) node).getPosition(), "BLUE").getIsHighlighted()) {
                    pawnMove(((Pawn) node), "BLUE");
                    moveDone = true;
                    break;
                }
            }
        }
        if (!moveDone) {
            for (Node node : grid.getChildren()) {
                if (ifPawnCanMove(node)) {
                    pawnMove(((Pawn) node), "BLUE");
                    break;
                }
            }
        }
    }

    private void endOfRoundAction() {
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
            endOfGameAction();
        }


        configurator.gamesToPlayLabel.setText("GAMES TO PLAY:    " + (roundsToPlay - roundsPlayed));
        configurator.scoresLabel.setText("GREEN    " + greenPoints + " : " + bluePoints + "    BLUE");

    }

    private void endOfGameAction() {
        configurator.whichActionLabel.setText("WON GAME");
        for (Node node : grid.getChildren()) {
            if (node instanceof Button
                    && !(((node)).getId().equals("MainMenuButton") || (node.getId().equals("NewGameButton")))) {
                ((Button) node).setOnAction((e) ->
                        configurator.warningsLabel.setText("The game has finished!"));
            }
        }
        if (!getPlayerName().equals("GUEST") && onePlayerGame) {
            HighScore highScore = new HighScore(getPlayerName(), (greenPoints - bluePoints));
            if (roundsToPlay == 1) {
                getOneGameHighScoresArrayList().add(highScore);

                getOneGameHighScoresArrayList().sort((Comparator.comparingInt(HighScore::getScores)).reversed());
                while (getOneGameHighScoresArrayList().size() > 15) {
                    getOneGameHighScoresArrayList().remove(15);
                }
                saveList(getOneGameHighScoresArrayList(), getOneGameHighScoresListFile());
            }
            if (roundsToPlay == 3) {
                getThreeGameHighScoresArrayList().add(highScore);
                getThreeGameHighScoresArrayList().sort((Comparator.comparingInt(HighScore::getScores)).reversed());
                while (getThreeGameHighScoresArrayList().size() > 15) {
                    getThreeGameHighScoresArrayList().remove(15);
                }
                saveList(getThreeGameHighScoresArrayList(), getThreeGameHighScoresListFle());
            }

            if (roundsToPlay == 5) {
                getFiveGameHighScoresArrayList().add(highScore);
                getFiveGameHighScoresArrayList().sort((Comparator.comparingInt(HighScore::getScores)).reversed());
                while (getFiveGameHighScoresArrayList().size() > 15) {
                    getFiveGameHighScoresArrayList().remove(15);
                }
                saveList(getFiveGameHighScoresArrayList(), getFiveGameHighScoresListFile());
            }
        }

    }

    private void delay() {
        try {
            Thread.sleep(2000);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
}