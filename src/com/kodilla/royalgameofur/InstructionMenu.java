package com.kodilla.royalgameofur;

import com.kodilla.MainMenu;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import static com.kodilla.Configurator.FONT30;
import static javafx.geometry.HPos.CENTER;

public class InstructionMenu {
    private Image page1 = new Image("file:src/com/resources/page1.png", true);
    private Image page2 = new Image("file:src/com/resources/page2.png", true);

    private Button nextButton = createNextButton();
    private Button previousButton = createPreviousButton();
    private Button mainMenuButton = createMainMenuButton();
    private GridPane grid;

    public InstructionMenu(GridPane grid) {
        this.grid = grid;
    }

    public GridPane newInstruction() {
        setRowsAndColumns();
        addButtons();
        addPage();
        return grid;
    }


    private void setRowsAndColumns() {

        grid.getRowConstraints().get(0).setMinHeight(50);
        grid.getRowConstraints().get(1).setMinHeight(700);
        grid.getRowConstraints().get(2).setMinHeight(50);

        grid.getColumnConstraints().get(0).setMinWidth(505);
        grid.getColumnConstraints().get(1).setMinWidth(490);
        grid.getColumnConstraints().get(2).setMinWidth(505);

    }

    private void resetRowsAndColumns() {

        double numberOfRows = 3;
        double numberOfColumns = 3;

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

    private Button createNextButton() {

        Button nextButton = new Button("Next");
        nextButton.setId("NextButton");
        nextButton.setFont(FONT30);
        GridPane.setHalignment(nextButton, CENTER);
        nextButton.setOnAction((e) -> nextButtonAction());
        return nextButton;
    }

    private Button createPreviousButton() {
        Button previousButton = new Button("Previous");
        previousButton.setId("PreviousButton");
        previousButton.setFont(FONT30);
        GridPane.setHalignment(previousButton, CENTER);
        previousButton.setOnAction((e) -> previousButtonAction());
        return previousButton;
    }

    private Button createMainMenuButton() {
        Button mainMenuButton = new Button("Main Menu");
        mainMenuButton.setId("MainMenuButton");
        mainMenuButton.setFont(FONT30);
        GridPane.setHalignment(mainMenuButton, CENTER);
        mainMenuButton.setOnAction((e) -> mainMenuButtonAction());
        return mainMenuButton;
    }


    private void addButtons() {

        grid.add(nextButton, 2, 1);
        grid.add(mainMenuButton, 1, 2);
    }

    private void addPage() {

        ImageView page = new ImageView(page1);
        page.setFitWidth(490);
        page.setPreserveRatio(true);
        page.setId("Page");
        grid.add(page, 1, 1);
    }


    private void nextButtonAction() {
        grid.getChildren().remove(nextButton);
        grid.add(previousButton, 0, 1);

        ((ImageView) grid.lookup("#Page")).setImage(page2);
    }

    private void previousButtonAction() {
        grid.getChildren().remove(previousButton);
        grid.add(nextButton, 2, 1);

        ((ImageView) grid.lookup("#Page")).setImage(page1);
    }

    private void mainMenuButtonAction() {

        clearGrid();

        MainMenu menu = new MainMenu(grid);
        grid = menu.newMainMenu();

    }
}
