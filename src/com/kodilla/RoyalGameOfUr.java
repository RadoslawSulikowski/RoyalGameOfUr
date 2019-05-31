package com.kodilla;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;



public class RoyalGameOfUr extends Application{

    private Image imageback = new Image("file:C:/Users/Radek/Documents/Projects/RoyalGameOfUr/src/com/resources/background.png", true);
    private Image imageBlueField = new Image("file:C:/Users/Radek/Documents/Projects/RoyalGameOfUr/src/com/resources/blueField.png", true);
    private Image imageGreenField = new Image("file:C:/Users/Radek/Documents/Projects/RoyalGameOfUr/src/com/resources/greenField.png", true);
    private Image imageHighlightedField = new Image("file:C:/Users/Radek/Documents/Projects/RoyalGameOfUr/src/com/resources/highlightedField.png", true);
    private Image imageYellowField = new Image("file:C:/Users/Radek/Documents/Projects/RoyalGameOfUr/src/com/resources/yellowField.png", true);

    public void setRowsAndColumns(GridPane grid){
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

    public void drawBoard(GridPane grid){
        double width = 92;
        for(int i = 4; i < 8;i ++) {
            ImageView imageViewGreenField = new ImageView(imageGreenField);
            imageViewGreenField.setFitWidth(width);
            imageViewGreenField.setPreserveRatio(true);
            imageViewGreenField.setSmooth(true);
            grid.add(imageViewGreenField, i, 3);
        }
        for(int i = 4; i < 8;i ++) {
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

        ImageView imageViewHighlightedField = new ImageView(imageHighlightedField);
        imageViewHighlightedField.setFitWidth(width);
        imageViewHighlightedField.setPreserveRatio(true);
        imageViewHighlightedField.setSmooth(true);

        ImageView imageViewHighlightedField1 = new ImageView(imageHighlightedField);
        imageViewHighlightedField1.setFitWidth(width);
        imageViewHighlightedField1.setPreserveRatio(true);
        imageViewHighlightedField1.setSmooth(true);

        ImageView imageViewHighlightedField2 = new ImageView(imageHighlightedField);
        imageViewHighlightedField2.setFitWidth(width);
        imageViewHighlightedField2.setPreserveRatio(true);
        imageViewHighlightedField2.setSmooth(true);

        grid.add(imageViewHighlightedField, 7,4);
        grid.add(imageViewHighlightedField1, 10,3);
        grid.add(imageViewHighlightedField2, 10,5);


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

        Scene scene = new Scene(grid, 1500, 850, Color.BLACK);
        primaryStage.setTitle("RoyalGameOfUr");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.sizeToScene();
        primaryStage.setResizable(false);
    }
}


