package com.kodilla;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class GamePlatform extends Application {

    private Image imageBack = new Image("file:src/com/resources/background.png", true);
    private GridPane grid = new GridPane();
    private static boolean loggedIn = false;
    private static String playerName = "";
    private static HashMap<String, String> playersMap = new HashMap<>();
    private static HashMap<String, String> highScoresMap = new HashMap<>();
    private static File playersMapFile = new File("players");
    private static File highScoresMapFile = new File("highScores");

    private void addRowsAndColumns() {
        for (int i = 0; i < 150; i++) {
            ColumnConstraints column = new ColumnConstraints();
            column.setPercentWidth(0);
            grid.getColumnConstraints().add(column);
        }
        for (int i = 0; i < 80; i++) {
            RowConstraints row = new RowConstraints();
            row.setPercentHeight(0);
            grid.getRowConstraints().add(row);
        }
    }

    static String getPlayerName() {
        return playerName;
    }

    static boolean getLoggedIn() {
        return loggedIn;
    }

    static void setPlayerName(String playerName) {
        GamePlatform.playerName = playerName;
    }

    static void setLoggedIn(boolean loggedIn) {
        GamePlatform.loggedIn = loggedIn;
    }

    public static HashMap<String, String> getPlayersMap() {
        return playersMap;
    }

    public static HashMap<String, String> getHighScoresMap() {
        return highScoresMap;
    }

    public static void saveMap(Map<String, String> map, File file) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(map);
            oos.close();
        } catch(Exception e) {
            System.out.println("Exception" + e);
        }
    }

    private static void loadMap(HashMap<String, String> map, File file) {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            Object readMap = ois.readObject();
            if (readMap instanceof HashMap) {
                map.putAll((HashMap) readMap);
            }
            ois.close();
        } catch(Exception e) {
            System.out.println("Exception" + e);
        }
    }

    public static File getPlayersMapFile() {
        return playersMapFile;
    }

    public static File getHighScoresMapFile() {
        return highScoresMapFile;
    }

    @Override
    public void start(Stage primaryStage) {

        loadMap(playersMap, playersMapFile);
        loadMap(highScoresMap, highScoresMapFile);

        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, true);
        BackgroundImage backgroundImage = new BackgroundImage(imageBack, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);
        grid.setBackground(background);

        addRowsAndColumns();

        LogPanel logPanel = new LogPanel(grid);
        grid = logPanel.newLogPanel();

        Scene scene = new Scene(grid, 1500, 800, Color.BLACK);
        primaryStage.setTitle("GamePlatform");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.sizeToScene();
        primaryStage.setResizable(false);
    }

    public static void main(String[] args) {
        launch(args);
    }
}


