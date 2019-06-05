package com.kodilla;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.Random;

import static javafx.scene.paint.Color.*;

public class RoyalGameOfUr extends Application{

    private Image imageBack = new Image("file:C:/Users/Radek/Documents/Projects/RoyalGameOfUr/src/com/resources/background.png", true);
    private Image imageBlueField = new Image("file:C:/Users/Radek/Documents/Projects/RoyalGameOfUr/src/com/resources/blueField.png", true);
    private Image imageGreenField = new Image("file:C:/Users/Radek/Documents/Projects/RoyalGameOfUr/src/com/resources/greenField.png", true);
    private Image imageHighlightedYellowField = new Image("file:C:/Users/Radek/Documents/Projects/RoyalGameOfUr/src/com/resources/highlightedYellowField.png", true);
    private Image imageHighlightedBlueField = new Image("file:C:/Users/Radek/Documents/Projects/RoyalGameOfUr/src/com/resources/highlightedBlueField.png", true);
    private Image imageHighlightedGreenField = new Image("file:C:/Users/Radek/Documents/Projects/RoyalGameOfUr/src/com/resources/highlightedGreenField.png", true);
    private Image imageYellowField = new Image("file:C:/Users/Radek/Documents/Projects/RoyalGameOfUr/src/com/resources/yellowField.png", true);
    private Image imageBlueHomeBaseField = new Image("file:C:/Users/Radek/Documents/Projects/RoyalGameOfUr/src/com/resources/blueHomeBaseField.png", true);
    private Image imageGreenHomeBaseField = new Image("file:C:/Users/Radek/Documents/Projects/RoyalGameOfUr/src/com/resources/greenHomeBaseField.png", true);
    private Image imageBlueFinalBaseField = new Image("file:C:/Users/Radek/Documents/Projects/RoyalGameOfUr/src/com/resources/blueFinalBaseField.png", true);
    private Image imageGreenFinalBaseField = new Image("file:C:/Users/Radek/Documents/Projects/RoyalGameOfUr/src/com/resources/greenFinalBaseField.png", true);
    private Random generator = new Random();


    private void setRowsAndColumns(GridPane grid){
        for(int i = 0; i < 16; i++) {
            ColumnConstraints column = new ColumnConstraints();
            column.setPercentWidth(100 / 16);
            grid.getColumnConstraints().add(column);
        }
        for(int i = 0; i < 9; i++) {
            RowConstraints row = new RowConstraints();
            row.setPercentHeight(100 / 9);
            grid.getRowConstraints().add(row);
        }
    }

    private void drawBoard(GridPane grid){
        double width = 92;

        ImageView imageViewGreenHomeBaseField = new ImageView(imageGreenHomeBaseField);
        imageViewGreenHomeBaseField.setFitWidth(width);
        imageViewGreenHomeBaseField.setPreserveRatio(true);
        imageViewGreenHomeBaseField.setX(0);
        grid.add(imageViewGreenHomeBaseField, 8, 3);

        ImageView imageViewBlueHomeBaseField = new ImageView(imageBlueHomeBaseField);
        imageViewBlueHomeBaseField.setFitWidth(width);
        imageViewBlueHomeBaseField.setPreserveRatio(true);
        imageViewBlueHomeBaseField.setY(0);
        grid.add(imageViewBlueHomeBaseField, 8, 5);

        ImageView imageViewGreenFinalBaseField = new ImageView(imageGreenFinalBaseField);
        imageViewGreenFinalBaseField.setFitWidth(width);
        imageViewGreenFinalBaseField.setPreserveRatio(true);
        imageViewGreenFinalBaseField.setX(17);
        grid.add(imageViewGreenFinalBaseField, 9, 3);

        ImageView imageViewBlueFinalBaseField = new ImageView(imageBlueFinalBaseField);
        imageViewBlueFinalBaseField.setFitWidth(width);
        imageViewBlueFinalBaseField.setPreserveRatio(true);
        imageViewBlueFinalBaseField.setY(17);
        grid.add(imageViewBlueFinalBaseField, 9, 5);

        for(int i = 5; i < 8; i++) {
            ImageView imageViewGreenField = new ImageView(imageGreenField);
            imageViewGreenField.setFitWidth(width);
            imageViewGreenField.setPreserveRatio(true);
            imageViewGreenField.setX(8 - i);
            grid.add(imageViewGreenField, i, 3);
        }
        for(int i = 5; i < 8; i++) {
            ImageView imageViewBlueField = new ImageView(imageBlueField);
            imageViewBlueField.setFitWidth(width);
            imageViewBlueField.setPreserveRatio(true);
            imageViewBlueField.setY(8 - i);
            grid.add(imageViewBlueField, i, 5);
        }
        for(int i = 4; i < 12; i++) {
            if(i == 7) {
                continue;
            }
            ImageView imageViewYellowField = new ImageView(imageYellowField);
            imageViewYellowField.setFitWidth(width);
            imageViewYellowField.setPreserveRatio(true);
            if(i < 11) {
                imageViewYellowField.setX(i + 1);
                imageViewYellowField.setY(i + 1);
            } else {
                imageViewYellowField.setX(14);
                imageViewYellowField.setY(14);
            }
            grid.add(imageViewYellowField, i, 4);
        }

        ImageView imageViewYellowField = new ImageView(imageYellowField);
        imageViewYellowField.setFitWidth(width);
        imageViewYellowField.setPreserveRatio(true);
        grid.add(imageViewYellowField, 11, 3);
        imageViewYellowField.setX(15);
        imageViewYellowField.setY(13);

        ImageView imageViewYellowField1 = new ImageView(imageYellowField);
        imageViewYellowField1.setFitWidth(width);
        imageViewYellowField1.setPreserveRatio(true);
        grid.add(imageViewYellowField1, 11, 5);
        imageViewYellowField1.setX(13);
        imageViewYellowField1.setY(15);


        ImageView imageViewHighlightedYellowField = new ImageView(imageHighlightedYellowField);
        imageViewHighlightedYellowField.setFitWidth(width);
        imageViewHighlightedYellowField.setPreserveRatio(true);
        imageViewHighlightedYellowField.setX(8);
        imageViewHighlightedYellowField.setY(8);

        ImageView imageViewHighlightedYellowField1 = new ImageView(imageHighlightedYellowField);
        imageViewHighlightedYellowField1.setFitWidth(width);
        imageViewHighlightedYellowField1.setPreserveRatio(true);
        imageViewHighlightedYellowField1.setX(16);
        imageViewHighlightedYellowField1.setY(12);

        ImageView imageViewHighlightedYellowField2 = new ImageView(imageHighlightedYellowField);
        imageViewHighlightedYellowField2.setFitWidth(width);
        imageViewHighlightedYellowField2.setPreserveRatio(true);
        imageViewHighlightedYellowField2.setX(12);
        imageViewHighlightedYellowField2.setY(16);

        ImageView imageViewHighlightedBlueField = new ImageView(imageHighlightedBlueField);
        imageViewHighlightedBlueField.setFitWidth(width);
        imageViewHighlightedBlueField.setPreserveRatio(true);
        imageViewHighlightedBlueField.setY(4);

        ImageView imageViewHighlightedGreenField = new ImageView(imageHighlightedGreenField);
        imageViewHighlightedGreenField.setFitWidth(width);
        imageViewHighlightedGreenField.setPreserveRatio(true);
        imageViewHighlightedGreenField.setX(4);


        grid.add(imageViewHighlightedYellowField, 7, 4);
        grid.add(imageViewHighlightedYellowField1, 10, 3);
        grid.add(imageViewHighlightedYellowField2, 10, 5);
        grid.add(imageViewHighlightedBlueField, 4, 5);
        grid.add(imageViewHighlightedGreenField, 4, 3);


    }

    private void addButtons(GridPane grid){
        Button roll = new Button("ROLL!!");
        Font font = Font.font("ALGERIAN", FontWeight.BOLD, 30);
        roll.setPrefWidth(300);
        roll.setFont(font);
        grid.add(roll, 10, 2, 3, 1);
        roll.setOnAction((e) -> {
            int goldCoins = 0;
            int fieldsToMove = 0;
            if(generator.nextBoolean()) {
                getCoinFromGrid(grid, 10, 1).setFill(GOLD);
                goldCoins += 1;
            } else {
                getCoinFromGrid(grid, 10, 1).setFill(SILVER);
            }
            if(generator.nextBoolean()) {
                getCoinFromGrid(grid, 11, 1).setFill(GOLD);
                goldCoins += 1;
            } else {
                getCoinFromGrid(grid, 11, 1).setFill(SILVER);
            }
            if(generator.nextBoolean()) {
                getCoinFromGrid(grid, 12, 1).setFill(GOLD);
                goldCoins += 1;
            } else {
                getCoinFromGrid(grid, 12, 1).setFill(SILVER);
            }
            if(goldCoins == 0) {
                fieldsToMove = 3;
            }

            if(goldCoins == 1) {
                fieldsToMove = 1;
            }

            if(goldCoins == 2) {
                fieldsToMove = 2;
            }

            if(goldCoins == 3) {
                fieldsToMove = 4;
            }
            for(Node node : grid.getChildren()) {
                if(node instanceof Pawn) {
                    ((Pawn) node).setFieldsToMove(fieldsToMove);
                }
            }
        });
    }

    private void addCoins(GridPane grid){
        Coin coin1 = new Coin();
        coin1.setRadius(40);
        coin1.setFill(GOLD);
        coin1.setId("COIN!");

        Coin coin2 = new Coin();
        coin2.setRadius(40);
        coin2.setFill(GOLD);
        Coin coin3 = new Coin();
        coin3.setRadius(40);
        coin3.setFill(SILVER);

        grid.add(coin1, 10, 1);
        grid.add(coin2, 11, 1);
        grid.add(coin3, 12, 1);
    }

    private void addPawns(GridPane grid){

        for(int i = 0; i < 7; i++) {
            GreenPawn pawn = new GreenPawn(grid);
            pawn.setStyle(
                    "-fx-border-color: #044505;" +
                    "-fx-background-color: #0BBF0E;" +
                    "-fx-border-width: 5;"
                    );
            grid.add(pawn, 8, 3);
        }
        for(int i = 0; i < 7; i++) {
            BluePawn pawn = new BluePawn(grid);
            pawn.setStyle(
                    "-fx-border-color: #100D5E;" +
                    "-fx-background-color: #110BBF;" +
                    "-fx-border-width: 5;"
            );
            grid.add(pawn, 8, 5);
        }

    }

    private Coin getCoinFromGrid(GridPane grid, int col, int row){
        for(Node node : grid.getChildren()) {
            if(node instanceof Coin && grid.getColumnIndex(node) == col && grid.getRowIndex(node) == row) {
                return (Coin) node;
            }
        }
        return null;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{

        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, true);
        BackgroundImage backgroundImage = new BackgroundImage(imageBack, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);

        GridPane grid = new GridPane();
        grid.setBackground(background);


        setRowsAndColumns(grid);
        drawBoard(grid);
        addButtons(grid);
        addCoins(grid);
        addPawns(grid);

        Scene scene = new Scene(grid, 1500, 850, Color.BLACK);
        primaryStage.setTitle("RoyalGameOfUr");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.sizeToScene();
        primaryStage.setResizable(false);
    }

    public static void main(String[] args){
        launch(args);
    }
}


