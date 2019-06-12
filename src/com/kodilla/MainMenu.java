package com.kodilla;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import static javafx.scene.paint.Color.WHITE;

class MainMenu {
    private GridPane grid;
    private double numberOfRows = 10;
    private double numberOfColumns = 3;

    MainMenu(GridPane grid) {
        this.grid = grid;
    }

    GridPane newMainMenu() {
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

        Label mainMenuLabel = new Label();
        mainMenuLabel.setId("MainMenuLabel");
        mainMenuLabel.setFont(labelsFont25);
        mainMenuLabel.setMinWidth(300);
        mainMenuLabel.setTextFill(WHITE);
        mainMenuLabel.setText("Select Game:");
        grid.add(mainMenuLabel, 1, 2);
    }

    private void addButtons() {
        Font buttonFont = Font.font("ALGERIAN", FontWeight.BOLD, 30);

        Button royalGameOfUrButton = new Button("Royal Game Of Ur");
        royalGameOfUrButton.setId("RGOUButton");
        royalGameOfUrButton.setFont(buttonFont);
        royalGameOfUrButton.setOnAction((e) -> royalGameOfUrButtonAction());

        grid.add(royalGameOfUrButton, 1, 4);
    }

    private void royalGameOfUrButtonAction() {

        clearGrid();

        RGOUMenu rgouMenu = new RGOUMenu(grid);
        grid = rgouMenu.newMenu();

    }
}
