package com.kodilla;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import static javafx.scene.paint.Color.*;

public class RoyalGameOfUr extends Application{

    private Image imageback = new Image("file:C:/Users/Radek/Documents/Projects/RoyalGameOfUr/src/com/resources/background.png", true);
    private Image imageBlueField = new Image("file:C:/Users/Radek/Documents/Projects/RoyalGameOfUr/src/com/resources/blueField.png", true);
    private Image imageGreenField = new Image("file:C:/Users/Radek/Documents/Projects/RoyalGameOfUr/src/com/resources/greenField.png", true);
    private Image imageHighlightedYellowField = new Image("file:C:/Users/Radek/Documents/Projects/RoyalGameOfUr/src/com/resources/highlightedYellowField.png", true);
    private Image imageHighlightedBlueField = new Image("file:C:/Users/Radek/Documents/Projects/RoyalGameOfUr/src/com/resources/highlightedBlueField.png", true);
    private Image imageHighlightedGreenField = new Image("file:C:/Users/Radek/Documents/Projects/RoyalGameOfUr/src/com/resources/highlightedGreenField.png", true);
    private Image imageYellowField = new Image("file:C:/Users/Radek/Documents/Projects/RoyalGameOfUr/src/com/resources/yellowField.png", true);

    private void setRowsAndColumns(GridPane grid){
        for(int i = 0; i < 16; i++){
            ColumnConstraints column = new ColumnConstraints();
            column.setPercentWidth(100/16);
            grid.getColumnConstraints().add(column);
        }
        for(int i = 0; i < 9; i++){
            RowConstraints row = new RowConstraints();
            row.setPercentHeight(100/9);
            grid.getRowConstraints().add(row);
        }
    }

    private void drawBoard(GridPane grid){
        double width = 92;
        for(int i = 5; i < 8;i ++) {
            ImageView imageViewGreenField = new ImageView(imageGreenField);
            imageViewGreenField.setFitWidth(width);
            imageViewGreenField.setPreserveRatio(true);
            imageViewGreenField.setSmooth(true);
            grid.add(imageViewGreenField, i, 3);
        }
        for(int i = 5; i < 8;i ++) {
            ImageView imageViewBlueField = new ImageView(imageBlueField);
            imageViewBlueField.setFitWidth(width);
            imageViewBlueField.setPreserveRatio(true);
            imageViewBlueField.setSmooth(true);
            grid.add(imageViewBlueField, i, 5);
        }
        for(int i = 4; i < 12;i ++) {

            ImageView imageViewYellowField = new ImageView(imageYellowField);
            imageViewYellowField.setFitWidth(width);
            imageViewYellowField.setPreserveRatio(true);
            imageViewYellowField.setSmooth(true);
            grid.add(imageViewYellowField, i, 4);
        }
        for(int j = 3; j < 6; j += 2) {
            for(int i = 10; i < 12; i++) {

                ImageView imageViewYellowField = new ImageView(imageYellowField);
                imageViewYellowField.setFitWidth(width);
                imageViewYellowField.setPreserveRatio(true);
                imageViewYellowField.setSmooth(true);
                grid.add(imageViewYellowField, i, j);
            }
        }

        ImageView imageViewHighlightedYellowField = new ImageView(imageHighlightedYellowField);
        imageViewHighlightedYellowField.setFitWidth(width);
        imageViewHighlightedYellowField.setPreserveRatio(true);
        imageViewHighlightedYellowField.setSmooth(true);

        ImageView imageViewHighlightedYellowField1 = new ImageView(imageHighlightedYellowField);
        imageViewHighlightedYellowField1.setFitWidth(width);
        imageViewHighlightedYellowField1.setPreserveRatio(true);
        imageViewHighlightedYellowField1.setSmooth(true);

        ImageView imageViewHighlightedYellowField2 = new ImageView(imageHighlightedYellowField);
        imageViewHighlightedYellowField2.setFitWidth(width);
        imageViewHighlightedYellowField2.setPreserveRatio(true);
        imageViewHighlightedYellowField2.setSmooth(true);

        ImageView imageViewHighlightedBlueField = new ImageView(imageHighlightedBlueField);
        imageViewHighlightedBlueField.setFitWidth(width);
        imageViewHighlightedBlueField.setPreserveRatio(true);
        imageViewHighlightedBlueField.setSmooth(true);

        ImageView imageViewHighlightedGreenField = new ImageView(imageHighlightedGreenField);
        imageViewHighlightedGreenField.setFitWidth(width);
        imageViewHighlightedGreenField.setPreserveRatio(true);
        imageViewHighlightedGreenField.setSmooth(true);

        grid.add(imageViewHighlightedYellowField, 7,4);
        grid.add(imageViewHighlightedYellowField1, 10,3);
        grid.add(imageViewHighlightedYellowField2, 10,5);
        grid.add(imageViewHighlightedBlueField, 4,5);
        grid.add(imageViewHighlightedGreenField, 4,3);


    }

    private void addButtons(GridPane grid){
        Button roll = new Button("ROLL!!");
        Font font = Font.font("ALGERIAN", FontWeight.BOLD, 30);
        roll.setPrefWidth(300);
        roll.setFont(font);
        grid.add(roll, 10, 2, 3, 1);
    }

    private void addCoins(GridPane grid){
        Circle coin1 = new Circle();
        coin1.setRadius(40);
        coin1.setFill(GOLD);
        Circle coin2 = new Circle();
        coin2.setRadius(40);
        coin2.setFill(GOLD);
        Circle coin3 = new Circle();
        coin3.setRadius(40);
        coin3.setFill(SILVER);

        grid.add(coin1, 10, 1);
        grid.add(coin2, 11, 1);
        grid.add(coin3, 12, 1);
    }

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{

        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, true);
        BackgroundImage backgroundImage = new BackgroundImage(imageback, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);

        GridPane grid = new GridPane();
        grid.setBackground(background);


        setRowsAndColumns(grid);
        drawBoard(grid);
        addButtons(grid);
        addCoins(grid);

        Scene scene = new Scene(grid, 1500, 850, Color.BLACK);
        primaryStage.setTitle("RoyalGameOfUr");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.sizeToScene();
        primaryStage.setResizable(false);
    }
}


