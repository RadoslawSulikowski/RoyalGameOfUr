package com.kodilla;

import com.kodilla.royalgameofur.InstructionMenu;
import com.kodilla.royalgameofur.RGOUMenu;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import static javafx.scene.paint.Color.WHITE;

 public class MainMenu {
    private GridPane grid;
    private double numberOfRows = 10;
    private double numberOfColumns = 5;

    public MainMenu(GridPane grid) {
        this.grid = grid;
    }

    public GridPane newMainMenu() {
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
        mainMenuLabel.setText("Select Game:");
        mainMenuLabel.setId("MainMenuLabel");
        mainMenuLabel.setFont(labelsFont25);
        mainMenuLabel.setMinWidth(300);
        mainMenuLabel.setTextFill(WHITE);
        grid.add(mainMenuLabel, 2, 2);

        Label royalGameOfUrLabel = new Label();
        royalGameOfUrLabel.setText("Royal Game Of Ur:");
        royalGameOfUrLabel.setId("RoyalGameOfUr:");
        royalGameOfUrLabel.setFont(labelsFont25);
        royalGameOfUrLabel.setMinWidth(300);
        royalGameOfUrLabel.setTextFill(WHITE);
        grid.add(royalGameOfUrLabel, 1, 4);
    }

    private void addButtons() {
        Font buttonFont = Font.font("ALGERIAN", FontWeight.BOLD, 30);

        Button royalGameOfUrButton = new Button("Play Game");
        royalGameOfUrButton.setId("PlayRGOUButton");
        royalGameOfUrButton.setFont(buttonFont);
        royalGameOfUrButton.setOnAction((e) -> royalGameOfUrButtonAction());
        grid.add(royalGameOfUrButton, 2, 4);

        Button instructionButton = new Button("Instruction PL");
        instructionButton.setId("InstructionButton");
        instructionButton.setFont(buttonFont);
        instructionButton.setOnAction((e) -> instructionButtonAction());
        grid.add(instructionButton, 3, 4);
    }


    private void royalGameOfUrButtonAction() {
        clearGrid();
        RGOUMenu rgouMenu = new RGOUMenu(grid);
        grid = rgouMenu.newMenu();
    }

    private void instructionButtonAction() {
        clearGrid();
        InstructionMenu menu = new InstructionMenu(grid);
        grid = menu.newInstruction();
    }
}
