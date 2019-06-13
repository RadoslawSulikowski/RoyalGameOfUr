package com.kodilla;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

class InstructionMenu {
    private Image page1 = new Image("file:src/com/resources/page1.png", true);
    private Image page2 = new Image("file:src/com/resources/page2.png", true);

    private GridPane grid;
    private Font buttonFont = Font.font("ALGERIAN", FontWeight.BOLD, 30);

    InstructionMenu(GridPane grid) {
        this.grid = grid;
    }

    GridPane newInstruction() {
        setRowsAndColumns();
        addButtons();
        addPage();
        return grid;
    }


    private void setRowsAndColumns() {

        grid.getRowConstraints().get(0).setMinHeight(50);
        grid.getRowConstraints().get(1).setMinHeight(700);
        grid.getRowConstraints().get(2).setMinHeight(50);

        grid.getColumnConstraints().get(0).setMinWidth(101);
        grid.getColumnConstraints().get(1).setMinWidth(101);
        grid.getColumnConstraints().get(2).setMinWidth(101);
        grid.getColumnConstraints().get(3).setMinWidth(101);
        grid.getColumnConstraints().get(4).setMinWidth(101);

        grid.getColumnConstraints().get(5).setMinWidth(490);

        grid.getColumnConstraints().get(6).setMinWidth(101);
        grid.getColumnConstraints().get(7).setMinWidth(101);
        grid.getColumnConstraints().get(8).setMinWidth(101);
        grid.getColumnConstraints().get(9).setMinWidth(101);
        grid.getColumnConstraints().get(10).setMinWidth(101);

    }

    private void resetRowsAndColumns() {

        double numberOfRows = 3;
        double numberOfColumns = 11;

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

    private void addButtons() {

        Button nextButton = new Button("Next");
        nextButton.setId("NextButton");
        nextButton.setFont(buttonFont);
        nextButton.setOnAction((e) -> nextButtonAction());
        grid.add(nextButton, 8, 1);

        Button mainMenuButton = new Button("Main Menu");
        mainMenuButton.setId("MainMenuButton");
        mainMenuButton.setFont(buttonFont);
        mainMenuButton.setAlignment(Pos.CENTER_RIGHT);
        mainMenuButton.setOnAction((e) -> mainMenuButtonAction());
        grid.add(mainMenuButton, 5, 2);
    }

    private void addPage() {

        ImageView page = new ImageView(page1);
        page.setFitWidth(490);
        page.setPreserveRatio(true);
        page.setId("Page");
        grid.add(page, 5, 1);
    }


    private void nextButtonAction() {
        grid.getChildren().remove(grid.lookup("#NextButton"));

        Button previousButton = new Button("Previous");
        previousButton.setId("PreviousButton");
        previousButton.setFont(buttonFont);
        previousButton.setOnAction((e) -> previousButtonAction());
        grid.add(previousButton, 2, 1,2,1);

        ((ImageView) grid.lookup("#Page")).setImage(page2);
    }

    private void previousButtonAction() {
        grid.getChildren().remove(grid.lookup("#PreviousButton"));

        Button nextButton = new Button("Next");
        nextButton.setId("NextButton");
        nextButton.setFont(buttonFont);
        nextButton.setOnAction((e) -> nextButtonAction());
        grid.add(nextButton, 8, 1);

        ((ImageView) grid.lookup("#Page")).setImage(page1);
    }

    private void mainMenuButtonAction() {

        clearGrid();

        MainMenu menu = new MainMenu(grid);
        grid = menu.newMainMenu();

    }
}
