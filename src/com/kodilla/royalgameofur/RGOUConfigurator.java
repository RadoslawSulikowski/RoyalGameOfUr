package com.kodilla.royalgameofur;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;

import static com.kodilla.Configurator.*;
import static javafx.geometry.HPos.CENTER;

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

    Label whichTurnLabel = createLabel("", "WhichTurnLabel", FONT25);
    Label whichActionLabel = createLabel("ROLL to draw who will start", "WhichActionLabel", FONT25, true, Pos.BOTTOM_LEFT);
    Label warningsLabel = createLabel("", "WarningsLabel", FONT15, true);
    Label gamesToPlayLabel = createLabel("", "GameStatusLabel", FONT25, true, Pos.BASELINE_LEFT);
    Label scoresLabel = createLabel("", "ScoresLabel", FONT25, true, Pos.BASELINE_CENTER, 600);
    Button rollButton = createButton("ROLL!!", "RollButton", FONT30, 300);
    Button mainMenuButton = createButton("Main Menu", "MainMenuButton", FONT30, 230);
    Button newGameButton = createButton("New game", "NewGameButton", FONT30, 230);
    Button nextGameButton = createButton("Next round", "NextRoundButton", FONT30, 230);

    private GridPane grid;
    private double numberOfRows = 8;
    private double numberOfColumns = 15;

    RGOUConfigurator(GridPane grid) {
        this.grid = grid;
    }

    double getNumberOfRows() {
        return numberOfRows;
    }

    double getNumberOfColumns() {
        return numberOfColumns;
    }

    void configure() {
        setRowsAndColumns(grid, numberOfRows, numberOfColumns);
        drawBoard();
        addPawns();
        addLabels();
        addCoins();
        addButtons();
    }

    private Field createField(Image image, double width, int numberForGreen, int numberForBlue, boolean isHighlighted) {
        Field field = new Field(image);
        field.setFitWidth(width);
        field.setPreserveRatio(true);
        field.setFieldNumberForGreen(numberForGreen);
        field.setFieldNumberForBlue(numberForBlue);
        if (isHighlighted) {
            field.setIsHighlighted();
        }
        return field;
    }

    private void drawBoard() {
        double width = 100;

        grid.add(createField(imageGreenHomeBaseField, width, 1, 0, false), 7, 3);
        grid.add(createField(imageBlueHomeBaseField, width, 0, 1, false), 7, 5);
        grid.add(createField(imageGreenFinalBaseField, width, 18, 0, false), 8, 3);
        grid.add(createField(imageBlueFinalBaseField, width, 0, 18, false), 8, 5);

        for (int i = 4; i < 7; i++) {
            grid.add(createField(imageGreenField, width, 8 - i, 0, false), i, 3);
        }
        for (int i = 4; i < 7; i++) {
            grid.add(createField(imageBlueField, width, 0, 8 - i, false), i, 5);
        }
        for (int i = 3; i < 11; i++) {
            if (i == 6) {
                continue;
            }
            if (i < 10) {
                grid.add(createField(imageYellowField, width, i + 3, i + 3, false), i, 4);
            } else {
                grid.add(createField(imageYellowField, width, 15, 15, false), i, 4);
            }
        }
        grid.add(createField(imageYellowField, width, 16, 14, false), 10, 3);
        grid.add(createField(imageYellowField, width, 14, 16, false), 10, 5);
        grid.add(createField(imageHighlightedYellowField, width, 9, 9, true), 6, 4);
        grid.add(createField(imageHighlightedYellowField, width, 17, 13, true), 9, 3);
        grid.add(createField(imageHighlightedYellowField, width, 13, 17, true), 9, 5);
        grid.add(createField(imageHighlightedGreenField, width, 5, 0, true), 3, 3);
        grid.add(createField(imageHighlightedBlueField, width, 0, 5, true), 3, 5);
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