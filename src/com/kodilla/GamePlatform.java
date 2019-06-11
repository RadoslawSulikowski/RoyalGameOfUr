package com.kodilla;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.Random;

import static javafx.scene.paint.Color.*;

public class GamePlatform extends Application{

    private Image imageBack = new Image("com/resources/background.png", true);
    private GridPane grid = new GridPane();

    private void setRowsAndColumns(){
        for(int i = 0; i < 150; i++) {
            ColumnConstraints column = new ColumnConstraints();
            column.setPercentWidth(0);
            grid.getColumnConstraints().add(column);
        }
        for(int i = 0; i < 80; i++) {
            RowConstraints row = new RowConstraints();
            row.setPercentHeight(0);
            grid.getRowConstraints().add(row);
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception{

        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, true);
        BackgroundImage backgroundImage = new BackgroundImage(imageBack, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);
        grid.setBackground(background);

        setRowsAndColumns();

        MainMenu menu = new MainMenu(grid);


        Scene scene = new Scene(menu.newMainMenu(), 1500, 800, Color.BLACK);
        primaryStage.setTitle("GamePlatform");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.sizeToScene();
        primaryStage.setResizable(false);
    }

    public static void main(String[] args){
        launch(args);
    }
}


