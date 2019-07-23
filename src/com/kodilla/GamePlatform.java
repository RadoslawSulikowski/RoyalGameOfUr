package com.kodilla;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.*;
import java.util.*;


public class GamePlatform extends Application {

    private static Map<String, String> playersMap = new HashMap<>();
    private static List<HighScore> oneGameHighScoresArrayList = new ArrayList<>();
    private static List<HighScore> threeGameHighScoresArrayList = new ArrayList<>();
    private static List<HighScore> fiveGameHighScoresArrayList = new ArrayList<>();
    private static File playersMapFile = new File("players");
    private static File oneGameHighScoresListFile = new File("oneGameHighScoresListFile");
    private static File threeGameHighScoresListFle = new File("threeGameHighScoresListFle");
    private static File fiveGameHighScoresListFile = new File("fiveGameHighScoresListFile");

    private Image imageBack = new Image("file:src/com/resources/background.png", true);
    private GridPane grid = new GridPane();
    private static boolean loggedIn = false;
    private static String playerName = "";

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

    public static String getPlayerName() {
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

    static Map<String, String> getPlayersMap() {
        return playersMap;
    }

    public static List<HighScore> getOneGameHighScoresArrayList() {
        return oneGameHighScoresArrayList;
    }

    public static List<HighScore> getThreeGameHighScoresArrayList() {
        return threeGameHighScoresArrayList;
    }

    public static List<HighScore> getFiveGameHighScoresArrayList() {
        return fiveGameHighScoresArrayList;
    }

    public static File getOneGameHighScoresListFile() {
        return oneGameHighScoresListFile;
    }

    public static File getThreeGameHighScoresListFle() {
        return threeGameHighScoresListFle;
    }

    public static File getFiveGameHighScoresListFile() {
        return fiveGameHighScoresListFile;
    }

    static File getPlayersMapFile() {
        return playersMapFile;
    }

    public static void saveList(List list, File file) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(list);
            oos.close();
        } catch(Exception e) {
            System.out.println("Exception" + e);
        }
    }

    static void saveMap(Map map, File file) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(map);
            oos.close();
        } catch(Exception e) {
            System.out.println("Exception" + e);
        }
    }

    private static void loadList(List list, File file) {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            Object readList = ois.readObject();
            if (readList instanceof List) {
                list.addAll((List) readList);
            }
            ois.close();
        } catch(Exception e) {
            System.out.println("Exception" + e);
        }
    }

    private static void loadMap(Map map, File file) {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            Object readMap = ois.readObject();
            if (readMap instanceof Map) {
                map.putAll((Map) readMap);
            }
            ois.close();
        } catch(Exception e) {
            System.out.println("Exception" + e);
        }
    }


    @Override
    public void start(Stage primaryStage) {

        loadList(oneGameHighScoresArrayList, oneGameHighScoresListFile);
        loadList(threeGameHighScoresArrayList, threeGameHighScoresListFle);
        loadList(fiveGameHighScoresArrayList, fiveGameHighScoresListFile);


        loadMap(playersMap, playersMapFile);


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