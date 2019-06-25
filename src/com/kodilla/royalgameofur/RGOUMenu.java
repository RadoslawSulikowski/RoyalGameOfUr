package com.kodilla.royalgameofur;

import com.kodilla.MainMenu;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import static com.kodilla.GamePlatform.FONT25;
import static com.kodilla.GamePlatform.FONT30;
import static javafx.scene.paint.Color.WHITE;

public class RGOUMenu {

    private Label rGOUMenuLabel1 = createRGOULabel();
    private Button onePlayerButton = createOnePlayerButton();
    private Button twoPlayersButton = createTwoPlayerButton();
    private Button mainMenuButton = createMainMenuButton();
    private Button oneRoundButton = createOneRoundButton();
    private Button threeRoundsButton = createThreeRoundsButton();
    private Button fiveRoundsButton = createFiveRoundsButton();
    private Button normalGameButton = createNormalGameButton();
    private Button hardGameButton = createHardGameButton();

    private GridPane grid;
    private double numberOfRows = 20;
    private double numberOfColumns = 7;
    private int roundsToPlay = 0;

    public RGOUMenu(GridPane grid) {
        this.grid = grid;
    }

    public GridPane newMenu() {

        setRowsAndColumns();
        addLabels();
        addButtons();

        return grid;
    }


    private Label createRGOULabel() {

        Label rGOUMenuLabel1 = new Label("CHOOSE GAME MODE:");
        rGOUMenuLabel1.setFont(FONT25);
        rGOUMenuLabel1.setMinWidth(300);
        rGOUMenuLabel1.setTextFill(WHITE);
        return rGOUMenuLabel1;
    }

    private Button createOnePlayerButton() {
        Button onePlayerButton = new Button("ONE PLAYER");
        onePlayerButton.setFont(FONT30);
        onePlayerButton.setMinWidth(250);
        onePlayerButton.setOnAction((e) -> onePlayerButtonAction());
        return onePlayerButton;
    }

    private Button createTwoPlayerButton() {
        Button twoPlayersButton = new Button("TWO PLAYERS");
        twoPlayersButton.setFont(FONT30);
        twoPlayersButton.setMinWidth(250);
        twoPlayersButton.setOnAction((e) -> twoPlayersButtonAction());
        return twoPlayersButton;
    }

    private Button createMainMenuButton() {
        Button mainMenuButton = new Button("MAIN MENU");
        mainMenuButton.setFont(FONT30);
        mainMenuButton.setOnAction((e) -> mainMenuButtonAction());
        return mainMenuButton;
    }

    private Button createOneRoundButton() {
        Button oneRoundButton = new Button("1 ROUND");
        oneRoundButton.setFont(FONT30);
        oneRoundButton.setOnAction(this::gameModeButtonAction);
        return oneRoundButton;
    }

    private Button createThreeRoundsButton() {
        Button threeRoundsButton = new Button("3 ROUNDS");
        threeRoundsButton.setFont(FONT30);
        threeRoundsButton.setOnAction(this::gameModeButtonAction);
        return threeRoundsButton;
    }

    private Button createFiveRoundsButton() {
        Button fiveRoundsButton = new Button("5 ROUNDS");
        fiveRoundsButton.setFont(FONT30);
        fiveRoundsButton.setOnAction(this::gameModeButtonAction);
        return fiveRoundsButton;
    }

    private Button createNormalGameButton() {
        Button normalGameButton = new Button("NORMAL");
        normalGameButton.setFont(FONT30);
        normalGameButton.setMinWidth(150);
        normalGameButton.setOnAction((e) -> normalGameButtonAction());
        return normalGameButton;
    }

    private Button createHardGameButton() {

        Button hardGameButton = new Button("HARD");
        hardGameButton.setFont(FONT30);
        hardGameButton.setMinWidth(150);
        hardGameButton.setOnAction((e) -> hardGameButtonAction());
        return hardGameButton;
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
        grid.add(rGOUMenuLabel1, 3, 1);
    }

    private void addButtons() {

        grid.add(oneRoundButton, 1, 3);
        grid.add(threeRoundsButton, 3, 3);
        grid.add(fiveRoundsButton, 5, 3);

        grid.add(mainMenuButton, 3, 18);
    }

    private void gameModeButtonAction(ActionEvent event) {
        Object button = event.getSource();
        if (button.equals(oneRoundButton)) {
            grid.getChildren().remove(threeRoundsButton);
            grid.getChildren().remove(fiveRoundsButton);
            roundsToPlay = 1;
            GridPane.setConstraints(oneRoundButton, 3, 3);
            GridPane.setHalignment(oneRoundButton, HPos.CENTER);
        }

        if (button.equals(threeRoundsButton)) {
            grid.getChildren().remove(oneRoundButton);
            grid.getChildren().remove(fiveRoundsButton);
            roundsToPlay = 3;
            GridPane.setConstraints(threeRoundsButton, 3, 3);
            GridPane.setHalignment(threeRoundsButton, HPos.CENTER);
        }

        if (button.equals(fiveRoundsButton)) {
            grid.getChildren().remove(oneRoundButton);
            grid.getChildren().remove(threeRoundsButton);
            roundsToPlay = 5;
            GridPane.setConstraints(fiveRoundsButton, 3, 3);
            GridPane.setHalignment(fiveRoundsButton, HPos.CENTER);
        }

        grid.getChildren().remove(normalGameButton);
        grid.getChildren().remove(hardGameButton);

        if (!grid.getChildren().contains(onePlayerButton)) {
            grid.add(onePlayerButton, 2, 6);
            GridPane.setHalignment(onePlayerButton, HPos.LEFT);
        } else {
            GridPane.setConstraints(onePlayerButton, 2, 6);
            GridPane.setHalignment(onePlayerButton, HPos.LEFT);
        }
        if (!grid.getChildren().contains(twoPlayersButton)) {
            grid.add(twoPlayersButton, 4, 6);
        }


    }

    private void normalGameButtonAction() {

        clearGrid();
        RoyalGameOfUr royalGameOfUr = new RoyalGameOfUr(grid, true, roundsToPlay);
        grid = royalGameOfUr.newGame();
    }

    private void hardGameButtonAction() {

    }


    private void onePlayerButtonAction() {
        grid.getChildren().remove(twoPlayersButton);
        GridPane.setConstraints(onePlayerButton, 3, 6);

        if (!grid.getChildren().contains(normalGameButton) && !grid.getChildren().contains(normalGameButton)) {
            grid.add(normalGameButton, 2, 9);
            grid.add(hardGameButton, 4, 9);
        }
        GridPane.setHalignment(normalGameButton, HPos.CENTER);
        GridPane.setHalignment(hardGameButton, HPos.CENTER);
    }

    private void twoPlayersButtonAction() {
        clearGrid();
        RoyalGameOfUr royalGameOfUr = new RoyalGameOfUr(grid, false, roundsToPlay);
        grid = royalGameOfUr.newGame();
    }

    private void mainMenuButtonAction() {
        clearGrid();
        MainMenu menu = new MainMenu(grid);
        grid = menu.newMainMenu();
    }


}
