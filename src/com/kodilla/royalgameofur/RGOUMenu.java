package com.kodilla.royalgameofur;

import com.kodilla.MainMenu;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import static javafx.scene.paint.Color.WHITE;

public class RGOUMenu {

    private GridPane grid;
    private double numberOfRows = 12;
    private double numberOfColumns = 5;

    public RGOUMenu(GridPane grid) {
        this.grid = grid;
    }

    public GridPane newMenu() {

        setRowsAndColumns();
        addLabels();
        addButtons();

        return grid;
    }


    private void setRowsAndColumns() {
        for (int i = 0; i < numberOfRows; i++) {
            grid.getRowConstraints().get(i).setPercentHeight(100.0 / numberOfRows);
        }
        for (int i = 0; i < numberOfColumns; i++) {
            grid.getColumnConstraints().get(i).setPercentWidth(100.0 / numberOfColumns);
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

    private void clearGrid() {
        while (grid.getChildren().size() != 0) {
            grid.getChildren().remove(grid.getChildren().get(0));
        }
        resetRowsAndColumns();
    }

    private void addLabels() {
        Font labelsFont25 = Font.font("ALGERIAN", FontWeight.BOLD, 25);

        Label rGOUMenuLabel1 = new Label();
        rGOUMenuLabel1.setId("RGOUMenuLabel1");
        rGOUMenuLabel1.setFont(labelsFont25);
        rGOUMenuLabel1.setMinWidth(300);
        rGOUMenuLabel1.setTextFill(WHITE);
        rGOUMenuLabel1.setText("Choose game mode:");
        grid.add(rGOUMenuLabel1, 2, 2);

        Label rGOUMenuLabel2 = new Label();
        rGOUMenuLabel2.setId("RGOUMenuLabel2");
        rGOUMenuLabel2.setFont(labelsFont25);
        rGOUMenuLabel2.setMinWidth(300);
        rGOUMenuLabel2.setTextFill(WHITE);
        rGOUMenuLabel2.setWrapText(true);
        grid.add(rGOUMenuLabel2, 2, 6);
    }

    private void addButtons() {
        Font buttonFont = Font.font("ALGERIAN", FontWeight.BOLD, 30);

        Button onePlayerButton = new Button("One Player");
        onePlayerButton.setId("OnePlayerButton");
        onePlayerButton.setFont(buttonFont);
        onePlayerButton.setOnAction((e) -> onePlayersButtonAction());
        grid.add(onePlayerButton, 1, 4);

        Button twoPlayersButton = new Button("Two Players");
        twoPlayersButton.setId("TwoPlayersButton");
        twoPlayersButton.setFont(buttonFont);
        twoPlayersButton.setOnAction((e) -> twoPlayersButtonAction());
        grid.add(twoPlayersButton, 3, 4);

        Button mainMenuButton = new Button("Main Menu");
        mainMenuButton.setId("MainMenuButton");
        mainMenuButton.setFont(buttonFont);
        mainMenuButton.setOnAction((e) -> mainMenuButtonAction());
        grid.add(mainMenuButton, 2, 7);
    }

    private void onePlayersButtonAction() {
        clearGrid();
        RoyalGameOfUr royalGameOfUr = new RoyalGameOfUr(grid, true);
        grid = royalGameOfUr.newGame();
    }

    private void twoPlayersButtonAction() {
        clearGrid();
        RoyalGameOfUr royalGameOfUr = new RoyalGameOfUr(grid, false);
        grid = royalGameOfUr.newGame();
    }

    private void mainMenuButtonAction() {
        clearGrid();
        MainMenu menu = new MainMenu(grid);
        grid = menu.newMainMenu();
    }

}
