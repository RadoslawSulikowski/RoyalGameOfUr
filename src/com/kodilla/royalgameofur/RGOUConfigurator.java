package com.kodilla.royalgameofur;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;

import static com.kodilla.GamePlatform.*;
import static javafx.geometry.HPos.CENTER;
import static javafx.scene.paint.Color.WHITE;


class RGOUConfigurator {

    private Image imageBlueField = new Image("file:src/com/resources/blueField.png", true);
    private Image imageGreenField = new Image("file:src/com/resources/greenField.png", true);
    private Image imageHighlightedYellowField = new Image("file:src/com/resources/highlightedYellowField.png", true);
    private Image imageHighlightedBlueField = new Image("file:src/com/resources/highlightedBlueField.png", true);
    private Image imageHighlightedGreenField = new Image("file:src/com/resources/highlightedGreenField.png", true);
    private Image imageYellowField = new Image("file:src/com/resources/yellowField.png", true);
    private Image imageBlueHomeBaseField = new Image("file:src/com/resources/blueHomeBaseField.png", true);
    private Image imageGreenHomeBaseField = new Image("file:src/com/resources/greenHomeBaseField.png", true);
    private Image imageBlueFinalBaseField = new Image("file:src/com/resources/blueFinalBaseField.png", true);
    private Image imageGreenFinalBaseField = new Image("file:src/com/resources/greenFinalBaseField.png", true);

    Label whichTurnLabel = createWhichTurnLabel();
    Label whichActionLabel = createWhichActionLabel();
    Label warningsLabel = createWarningsLabelLabel();
    Label gamesToPlayLabel = createGamesToPlayLabel();
    Label scoresLabel = createScoresLabel();
    Button rollButton = createRollButton();
    Button mainMenuButton = createMainMenuButton();
    Button newGameButton = createNewGameButton();
    Button nextGameButton = createNextRoundButton();

    private Label createWhichTurnLabel() {
        Label whichTurnLabel = new Label();
        whichTurnLabel.setId("WhichTurnLabel");
        whichTurnLabel.setFont(FONT25);
        whichTurnLabel.setMinWidth(300);
        whichTurnLabel.setTextFill(WHITE);
        return whichTurnLabel;
    }

    private Label createWhichActionLabel() {
        Label whichActionLabel = new Label();
        whichActionLabel.setId("WhichActionLabel");
        whichActionLabel.setFont(FONT25);
        whichActionLabel.setMinWidth(300);
        whichActionLabel.setTextFill(WHITE);
        whichActionLabel.setWrapText(true);
        whichActionLabel.setText("ROLL to draw who will start");
        return whichActionLabel;
    }

    private Label createWarningsLabelLabel() {
        Label warningsLabel = new Label();
        warningsLabel.setId("WarningsLabel");
        warningsLabel.setFont(FONT15);
        warningsLabel.setMinWidth(600);
        warningsLabel.setTextFill(WHITE);
        warningsLabel.setWrapText(true);
        warningsLabel.setAlignment(Pos.BOTTOM_LEFT);
        return warningsLabel;
    }

    private Label createGamesToPlayLabel() {
        Label gamesToPlay = new Label();
        gamesToPlay.setId("GameStatusLabel");
        gamesToPlay.setFont(FONT25);
        gamesToPlay.setMinWidth(300);
        gamesToPlay.setTextFill(WHITE);
        gamesToPlay.setWrapText(true);
        gamesToPlay.setAlignment(Pos.BASELINE_LEFT);
        return gamesToPlay;
    }

    private Label createScoresLabel() {
        Label scoresLabel = new Label();
        scoresLabel.setId("ScoresLabel");
        scoresLabel.setFont(FONT25);
        scoresLabel.setMinWidth(600);
        scoresLabel.setTextFill(WHITE);
        scoresLabel.setWrapText(true);
        scoresLabel.setAlignment(Pos.BASELINE_CENTER);
        return scoresLabel;
    }

    private Button createRollButton() {
        Button roll = new Button("ROLL!!");
        roll.setId("RollButton");
        roll.setPrefWidth(300);
        roll.setFont(FONT30);
        return roll;
    }

    private Button createMainMenuButton() {
        Button mainMenuButton = new Button("Main Menu");
        mainMenuButton.setId("MainMenuButton");
        mainMenuButton.setFont(FONT30);
        mainMenuButton.setMinWidth(230);
        return mainMenuButton;
    }

    private Button createNewGameButton() {
        Button newGameButton = new Button("New game");
        newGameButton.setId("NewGameButton");
        newGameButton.setFont(FONT30);
        newGameButton.setMinWidth(230);
        return newGameButton;
    }

    private Button createNextRoundButton() {

        Button nextRoundButton = new Button("Next round");
        nextRoundButton.setId("NextRoundButton");
        nextRoundButton.setFont(FONT30);
        nextRoundButton.setMinWidth(230);
        return nextRoundButton;
    }


    private GridPane grid;
    private double numberOfRows = 8;
    private double numberOfColumns = 15;

    RGOUConfigurator(GridPane grid) {
        this.grid = grid;
    }

    void configure() {
        setRowsAndColumns();
        drawBoard();
        addPawns();
        addLabels();
        addCoins();
        addButtons();
    }

    void clearGrid() {
        while (grid.getChildren().size() != 0) {
            grid.getChildren().remove(grid.getChildren().get(0));
        }
        resetRowsAndColumns();
    }

    private void setRowsAndColumns() {
        for (int i = 0; i < numberOfColumns; i++) {
            grid.getColumnConstraints().get(i).setPercentWidth(100.0 / numberOfColumns);
        }
        for (int i = 0; i < numberOfRows; i++) {
            grid.getRowConstraints().get(i).setPercentHeight(100.0 / numberOfRows);
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

    private void drawBoard() {
        double width = 100;

        Field greenHomeBaseField = new Field(imageGreenHomeBaseField);
        greenHomeBaseField.setFitWidth(width);
        greenHomeBaseField.setPreserveRatio(true);
        greenHomeBaseField.setFieldNumberForGreen(1);
        grid.add(greenHomeBaseField, 7, 3);

        Field blueHomeBaseField = new Field(imageBlueHomeBaseField);
        blueHomeBaseField.setFitWidth(width);
        blueHomeBaseField.setPreserveRatio(true);
        blueHomeBaseField.setFieldNumberForBlue(1);
        grid.add(blueHomeBaseField, 7, 5);

        Field greenFinalBaseField = new Field(imageGreenFinalBaseField);
        greenFinalBaseField.setFitWidth(width);
        greenFinalBaseField.setPreserveRatio(true);
        greenFinalBaseField.setFieldNumberForGreen(18);
        grid.add(greenFinalBaseField, 8, 3);

        Field blueFinalBaseField = new Field(imageBlueFinalBaseField);
        blueFinalBaseField.setFitWidth(width);
        blueFinalBaseField.setPreserveRatio(true);
        blueFinalBaseField.setFieldNumberForBlue(18);
        grid.add(blueFinalBaseField, 8, 5);

        for (int i = 4; i < 7; i++) {
            Field greenField = new Field(imageGreenField);
            greenField.setFitWidth(width);
            greenField.setPreserveRatio(true);
            greenField.setFieldNumberForGreen(8 - i);
            grid.add(greenField, i, 3);
        }
        for (int i = 4; i < 7; i++) {
            Field blueField = new Field(imageBlueField);
            blueField.setFitWidth(width);
            blueField.setPreserveRatio(true);
            blueField.setFieldNumberForBlue(8 - i);
            grid.add(blueField, i, 5);
        }
        for (int i = 3; i < 11; i++) {
            if (i == 6) {
                continue;
            }
            Field yellowField = new Field(imageYellowField);
            yellowField.setFitWidth(width);
            yellowField.setPreserveRatio(true);

            if (i < 10) {
                yellowField.setFieldNumberForGreen(i + 3);
                yellowField.setFieldNumberForBlue(i + 3);
            } else {
                yellowField.setFieldNumberForGreen(15);
                yellowField.setFieldNumberForBlue(15);
            }
            grid.add(yellowField, i, 4);
        }

        Field yellowField = new Field(imageYellowField);
        yellowField.setFitWidth(width);
        yellowField.setPreserveRatio(true);
        yellowField.setFieldNumberForGreen(16);
        yellowField.setFieldNumberForBlue(14);
        grid.add(yellowField, 10, 3);

        Field yellowField1 = new Field(imageYellowField);
        yellowField1.setFitWidth(width);
        yellowField1.setPreserveRatio(true);
        yellowField1.setFieldNumberForGreen(14);
        yellowField1.setFieldNumberForBlue(16);
        grid.add(yellowField1, 10, 5);

        Field highlightedYellowField = new Field(imageHighlightedYellowField);
        highlightedYellowField.setFitWidth(width);
        highlightedYellowField.setIsHighlighted();
        highlightedYellowField.setPreserveRatio(true);
        highlightedYellowField.setFieldNumberForGreen(9);
        highlightedYellowField.setFieldNumberForBlue(9);

        Field highlightedYellowField1 = new Field(imageHighlightedYellowField);
        highlightedYellowField1.setFitWidth(width);
        highlightedYellowField1.setIsHighlighted();
        highlightedYellowField1.setPreserveRatio(true);
        highlightedYellowField1.setFieldNumberForGreen(17);
        highlightedYellowField1.setFieldNumberForBlue(13);

        Field highlightedYellowField2 = new Field(imageHighlightedYellowField);
        highlightedYellowField2.setFitWidth(width);
        highlightedYellowField2.setIsHighlighted();
        highlightedYellowField2.setPreserveRatio(true);
        highlightedYellowField2.setFieldNumberForGreen(13);
        highlightedYellowField2.setFieldNumberForBlue(17);

        Field highlightedBlueField = new Field(imageHighlightedBlueField);
        highlightedBlueField.setFitWidth(width);
        highlightedBlueField.setIsHighlighted();
        highlightedBlueField.setPreserveRatio(true);
        highlightedBlueField.setFieldNumberForBlue(5);

        Field highlightedGreenField = new Field(imageHighlightedGreenField);
        highlightedGreenField.setFitWidth(width);
        highlightedGreenField.setIsHighlighted();
        highlightedGreenField.setPreserveRatio(true);
        highlightedGreenField.setFieldNumberForGreen(5);

        grid.add(highlightedYellowField, 6, 4);
        grid.add(highlightedYellowField1, 9, 3);
        grid.add(highlightedYellowField2, 9, 5);
        grid.add(highlightedBlueField, 3, 5);
        grid.add(highlightedGreenField, 3, 3);


    }

    private void addPawns() {
        for (int i = 1; i < 8; i++) {
            Pawn greenPawn = new Pawn("GREEN");
            greenPawn.setStyle(
                    "-fx-border-color: #044505;" +
                            "-fx-background-color: #0BBF0E;" +
                            "-fx-border-width: 5;"
            );
            greenPawn.setFont(FONT30);
            greenPawn.setId("GreenPawn" + i);
            GridPane.setHalignment(greenPawn, CENTER);
            grid.add(greenPawn, 7, 3);

            Pawn bluePawn = new Pawn("BLUE");
            bluePawn.setStyle(
                    "-fx-border-color: #100D5E;" +
                            "-fx-background-color: #117EEB;" +
                            "-fx-border-width: 5;"
            );
            bluePawn.setFont(FONT30);
            bluePawn.setId("BluePawn" + i);
            GridPane.setHalignment(bluePawn, CENTER);
            grid.add(bluePawn, 7, 5);
        }
    }

    private void addCoins() {
        for (int i = 11; i < 14; i++) {
            Coin coin = new Coin();
            grid.add(coin, i, 1);
        }
    }

    private void addLabels() {
        grid.add(gamesToPlayLabel, 1, 0);
        grid.add(scoresLabel, 5, 0);
        grid.add(whichTurnLabel, 3, 1);
        grid.add(whichActionLabel, 6, 1);
        grid.add(warningsLabel, 3, 2);
    }

    private void addButtons() {

        grid.add(rollButton, 11, 2, 3, 1);
        grid.add(mainMenuButton, 12, 4, 3, 1);
        grid.add(newGameButton, 12, 5, 3, 1);
    }

}
