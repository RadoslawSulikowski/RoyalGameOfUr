package com.gameplatform.sudokusolver;

public final class Backtrack {
    private final SudokuBoard board;
    private final int fieldNumber;
    private final int chosenCandidate;

    Backtrack(final SudokuBoard board, final int fieldNumber, final int chosenCandidate) {
        this.board = board;
        this.fieldNumber = fieldNumber;
        this.chosenCandidate = chosenCandidate;
    }

    SudokuBoard getBoard() {
        return board;
    }

    int getFieldNumber() {
        return fieldNumber;
    }

    @Override
    public String toString() {
        return "Backtrack{" +
                "fieldNumber=" + fieldNumber +
                ", chosenCandidate=" + chosenCandidate +
                '}';
    }
}
