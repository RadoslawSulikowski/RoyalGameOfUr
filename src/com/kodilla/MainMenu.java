package com.kodilla;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import static javafx.scene.paint.Color.WHITE;

public class MainMenu{
    private GridPane grid;

    public MainMenu(GridPane grid){
        this.grid = grid;
    }

    public GridPane newMainMenu(){
        setRowsAndColumns();
        addLabels();
        addButtons();

        return grid;

    }

    private void clearGridFromNodes(){
        for(Node node : grid.getChildren()){
            if(node instanceof Label || node instanceof Button)
            grid.getChildren().remove(node);
        }
    }

    private void setRowsAndColumns(){

        for(int i = 0; i < 10; i++) {
            grid.getRowConstraints().get(i).setPercentHeight(100 / 10);
        }
        for(int i = 0; i < 3; i++) {
            grid.getColumnConstraints().get(i).setPercentWidth(100 / 3);
        }
    }

    private void resetRowsAndColumns(){

        for(int i = 0; i < 10; i++) {
            grid.getRowConstraints().get(i).setPercentHeight(0);
        }
        for(int i = 0; i < 3; i++) {
            grid.getColumnConstraints().get(i).setPercentWidth(0);
        }
    }

    private void addLabels(){
        Font labelsFont25 = Font.font("ALGERIAN", FontWeight.BOLD, 25);

        Label mainMenuLabel = new Label();
        mainMenuLabel.setId("MainMenuLabel");
        mainMenuLabel.setFont(labelsFont25);
        mainMenuLabel.setMinWidth(300);
        mainMenuLabel.setTextFill(WHITE);
        mainMenuLabel.setText("Select Game:");
        grid.add(mainMenuLabel, 1, 2);
    }

    private void addButtons(){
        Font buttonFont = Font.font("ALGERIAN", FontWeight.BOLD, 30);

        Button royalGameOfUrButton = new Button("Royal Game Of Ur");
        royalGameOfUrButton.setFont(buttonFont);
        royalGameOfUrButton.setOnAction((e) -> {
            grid.getChildren().remove(royalGameOfUrButton);
            grid.getChildren().remove(grid.lookup("#MainMenuLabel"));
            resetRowsAndColumns();
            RoyalGameOfUr royalGameOfUr = new RoyalGameOfUr(grid);
            grid = royalGameOfUr.newGame();
        });

        grid.add(royalGameOfUrButton, 1, 4);
    }


}
