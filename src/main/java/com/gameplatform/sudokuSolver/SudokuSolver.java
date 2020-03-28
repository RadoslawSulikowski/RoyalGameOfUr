package com.gameplatform.sudokuSolver;

import javafx.scene.layout.GridPane;

public class SudokuSolver {
    private GridPane grid;
    private SudokuSolverConfigurator configurator;

    public SudokuSolver(GridPane grid) {
        this.grid = grid;
        configurator = new SudokuSolverConfigurator(grid);
    }

    public GridPane newSolver() {
        configurator.configure();
        return grid;
    }
}
