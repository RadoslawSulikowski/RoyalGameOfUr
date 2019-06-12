package com.kodilla;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import static javafx.scene.paint.Color.BLACK;
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


    private GridPane grid;
    private double numberOfRows = 8;
    private double numberOfColumns = 15;

    RGOUConfigurator(GridPane grid){
        this.grid = grid;
    }

    void configure(){
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

        Field GreenFinalBaseField = new Field(imageGreenFinalBaseField);
        GreenFinalBaseField.setFitWidth(width);
        GreenFinalBaseField.setPreserveRatio(true);
        GreenFinalBaseField.setFieldNumberForGreen(18);
        grid.add(GreenFinalBaseField, 8, 3);

        Field BlueFinalBaseField = new Field(imageBlueFinalBaseField);
        BlueFinalBaseField.setFitWidth(width);
        BlueFinalBaseField.setPreserveRatio(true);
        BlueFinalBaseField.setFieldNumberForBlue(18);
        grid.add(BlueFinalBaseField, 8, 5);

        for (int i = 4; i < 7; i++) {
            Field GreenField = new Field(imageGreenField);
            GreenField.setFitWidth(width);
            GreenField.setPreserveRatio(true);
            GreenField.setFieldNumberForGreen(8 - i);
            grid.add(GreenField, i, 3);
        }
        for (int i = 4; i < 7; i++) {
            Field BlueField = new Field(imageBlueField);
            BlueField.setFitWidth(width);
            BlueField.setPreserveRatio(true);
            BlueField.setFieldNumberForBlue(8 - i);
            grid.add(BlueField, i, 5);
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

        Field HighlightedYellowField = new Field(imageHighlightedYellowField);
        HighlightedYellowField.setFitWidth(width);
        HighlightedYellowField.setIsHighlighted();
        HighlightedYellowField.setPreserveRatio(true);
        HighlightedYellowField.setFieldNumberForGreen(9);
        HighlightedYellowField.setFieldNumberForBlue(9);

        Field HighlightedYellowField1 = new Field(imageHighlightedYellowField);
        HighlightedYellowField1.setFitWidth(width);
        HighlightedYellowField1.setIsHighlighted();
        HighlightedYellowField1.setPreserveRatio(true);
        HighlightedYellowField1.setFieldNumberForGreen(17);
        HighlightedYellowField1.setFieldNumberForBlue(13);

        Field HighlightedYellowField2 = new Field(imageHighlightedYellowField);
        HighlightedYellowField2.setFitWidth(width);
        HighlightedYellowField2.setIsHighlighted();
        HighlightedYellowField2.setPreserveRatio(true);
        HighlightedYellowField2.setFieldNumberForGreen(13);
        HighlightedYellowField2.setFieldNumberForBlue(17);

        Field HighlightedBlueField = new Field(imageHighlightedBlueField);
        HighlightedBlueField.setFitWidth(width);
        HighlightedBlueField.setIsHighlighted();
        HighlightedBlueField.setPreserveRatio(true);
        HighlightedBlueField.setFieldNumberForBlue(5);

        Field HighlightedGreenField = new Field(imageHighlightedGreenField);
        HighlightedGreenField.setFitWidth(width);
        HighlightedGreenField.setIsHighlighted();
        HighlightedGreenField.setPreserveRatio(true);
        HighlightedGreenField.setFieldNumberForGreen(5);

        grid.add(HighlightedYellowField, 6, 4);
        grid.add(HighlightedYellowField1, 9, 3);
        grid.add(HighlightedYellowField2, 9, 5);
        grid.add(HighlightedBlueField, 3, 5);
        grid.add(HighlightedGreenField, 3, 3);


    }

    private void addPawns() {

        Font pawnFont = Font.font("ALGERIAN", FontWeight.BOLD, 30);

        for (int i = 0; i < 7; i++) {
            Pawn pawn = new Pawn(grid, "GREEN");
            pawn.setStyle(
                    "-fx-border-color: #044505;" +
                            "-fx-background-color: #0BBF0E;" +
                            "-fx-border-width: 5;"
            );
            pawn.setFont(pawnFont);
            pawn.setAlignment(Pos.BASELINE_CENTER);
            grid.add(pawn, 7, 3);
        }
        for (int i = 0; i < 7; i++) {
            Pawn pawn = new Pawn(grid, "BLUE");
            pawn.setStyle(
                    "-fx-border-color: #100D5E;" +
                            "-fx-background-color: #117EEB;" +
                            "-fx-border-width: 5;"
            );
            pawn.setFont(pawnFont);
            grid.add(pawn, 7, 5);
        }
    }

    private void addLabels() {
        Font labelsFont25 = Font.font("ALGERIAN", FontWeight.BOLD, 25);
        Font labelsFont15 = Font.font("ALGERIAN", FontWeight.BOLD, 15);

        Label whichTurnLabel = new Label();
        whichTurnLabel.setId("WhichTurnLabel");
        whichTurnLabel.setFont(labelsFont25);
        whichTurnLabel.setMinWidth(300);
        whichTurnLabel.setTextFill(WHITE);
        grid.add(whichTurnLabel, 3, 1);

        Label whichActionLabel = new Label();
        whichActionLabel.setId("WhichActionLabel");
        whichActionLabel.setFont(labelsFont25);
        whichActionLabel.setMinWidth(300);
        whichActionLabel.setTextFill(WHITE);
        whichActionLabel.setWrapText(true);
        whichActionLabel.setText("ROLL to draw who will start");
        grid.add(whichActionLabel, 6, 1);

        Label warningsLabel = new Label();
        warningsLabel.setId("WarningsLabel");
        warningsLabel.setFont(labelsFont15);
        warningsLabel.setMinWidth(600);
        warningsLabel.setTextFill(WHITE);
        warningsLabel.setWrapText(true);
        grid.add(warningsLabel, 3, 2);

    }

    private void addCoins() {
        for (int i = 11; i < 14; i++) {
            Coin coin = new Coin();
            coin.setRadius(40);
            coin.setFill(BLACK);
            grid.add(coin, i, 1);
        }
    }

    private void addButtons() {
        Font buttonFont = Font.font("ALGERIAN", FontWeight.BOLD, 30);

        Button roll = new Button("ROLL!!");
        roll.setId("RollButton");
        roll.setPrefWidth(300);
        roll.setFont(buttonFont);
        grid.add(roll, 11, 2, 3, 1);

        Button mainMenuButton = new Button("Main Menu");
        mainMenuButton.setId("MainMenuButton");
        mainMenuButton.setFont(buttonFont);

        grid.add(mainMenuButton, 12, 4, 2, 1);

        Button newGameButton = new Button("New game");
        newGameButton.setId("NewGameButton");
        newGameButton.setFont(buttonFont);

        grid.add(newGameButton, 12, 5, 2, 1);
    }

}
