package com.gameplatform.sudokuSolver;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import static com.gameplatform.sudokuSolver.SudokuField.EMPTY;


public class SudokuBoard {

    private SudokuField[] board = new SudokuField[81];


    SudokuField[] getBoard() {
        return board;
    }

    SudokuField getFieldByFieldNumber(int fieldNumber) {
        return board[fieldNumber];
    }

    void createBoard() {
        for (int i = 0; i < board.length; i++) {
            board[i] = new SudokuField();
            board[i].setValue(EMPTY);
            board[i].setRow(i / 9 + 1);
            board[i].setFieldNumber(i);
            board[i].setColumn(i % 9 + 1);
            board[i].setSection((((board[i].getRow() - 1) / 3) * 3 + (board[i].getColumn() - 1) / 3) + 1);
        }
    }

    SudokuBoard copyBoard() {
        SudokuBoard copy = new SudokuBoard();
        for (int i = 0; i < board.length; i++) {
            copy.getBoard()[i] = board[i].copyField();
        }
        return copy;
    }

    private void recoverBoard(SudokuBoard backtrackBoard) {
        for (SudokuField field : board) {
            board[field.getFieldNumber()] = backtrackBoard.getFieldByFieldNumber(field.getFieldNumber());
        }
    }

    void prepareBoard() {
        for (SudokuField field : board) {
            if (field.getValue() != EMPTY) {
                field.getCandidates().clear();
                field.getCandidates().add(field.getValue());
                removeCandidateFromRowColumnSection(field.getFieldNumber());
            }
        }
    }

    private int firstFieldWithTheLeastCandidates() {
        for (int i = 2; i < 10; i++) {
            for (SudokuField field : board) {
                if (field.getCandidates().size() == i) {
                    return field.getFieldNumber();
                }
            }
        }
        return 0;
    }

    private boolean isFilled() {
        for (SudokuField field : board) {
            if (field.getValue() == EMPTY) {
                return false;
            }
        }
        return true;
    }

    private boolean hasFieldWithNoCandidates() {
        for (SudokuField field : board) {
            if (field.getCandidates().isEmpty()) {
                return true;
            }
        }
        return false;
    }

    boolean hasNoDuplicateValuesInRowColumnOrSection() {

        List<Integer> lR = new ArrayList<>();
        List<Integer> lC = new ArrayList<>();
        List<Integer> lS = new ArrayList<>();

        for (int i = 1; i < 10; i++) {
            for (SudokuField field : board) {
                if (field.getRow() == i) {
                    lR.add(field.getValue());
                }
                if (field.getColumn() == i) {
                    lC.add(field.getValue());
                }
                if (field.getSection() == i) {
                    lS.add(field.getValue());
                }
            }
            for (int j = 1; j < 10; j++) {
                if (lR.indexOf(j) != lR.lastIndexOf(j)
                        || lC.indexOf(j) != lC.lastIndexOf(j)
                        || lS.indexOf(j) != lS.lastIndexOf(j)) {
                    return false;
                }
            }
            lR.clear();
            lC.clear();
            lS.clear();
        }
        return true;
    }

    private void removeCandidateFromRowColumnSection(int filledFieldIndex) {
        for (SudokuField field : board) {
            if (field.getValue() == EMPTY &&
                    field.getCandidates().contains(board[filledFieldIndex].getValue())) {
                if (board[filledFieldIndex].getRow() == field.getRow()
                        || board[filledFieldIndex].getColumn() == field.getColumn()
                        || board[filledFieldIndex].getSection() == field.getSection()) {
                    field.getCandidates().remove((Integer) board[filledFieldIndex].getValue());
                }
            }
        }
    }

    private boolean isSingleCandidateOnPosition(SudokuField fieldI, SudokuField fieldJ) {
        return (fieldI.getRow() == fieldJ.getRow()
                || fieldI.getColumn() == fieldJ.getColumn()
                || fieldI.getSection() == fieldJ.getSection()
                && fieldJ.getValue() != 0);
    }

    private boolean isCandidateOnSinglePosition(List<Integer> listForRows, List<Integer> listForColumns, List<Integer> listForSections,
                                                SudokuField field, int i, int j) {
        return (field.getValue() == EMPTY && field.getCandidates().contains(j)
                && (listForRows.contains(j) && listForRows.indexOf(j) == listForRows.lastIndexOf(j) && field.getRow() == i
                || listForColumns.contains(j) && listForColumns.indexOf(j) == listForColumns.lastIndexOf(j) && field.getColumn() == i
                || listForSections.contains(j) && listForSections.indexOf(j) == listForSections.lastIndexOf(j) && field.getSection() == i));
    }

    private void addBoardToBacktracksAndSetOneOfCandidatesAsValue(Deque<Backtrack> backtracks) {
        int fieldNumber = firstFieldWithTheLeastCandidates();
        SudokuField field = getFieldByFieldNumber(fieldNumber);
        int chosenCandidate = field.getCandidates().get(0);
        backtracks.push(new Backtrack(copyBoard(), fieldNumber, chosenCandidate));
        field.setValue(chosenCandidate);
        field.getCandidates().clear();
        field.getCandidates().add(chosenCandidate);
        removeCandidateFromRowColumnSection(fieldNumber);
    }

    private boolean singleCandidate() {
        boolean noAction = true;
        for (SudokuField fieldI : board) {
            if (fieldI.getValue() == EMPTY) {
                if (fieldI.getCandidates().size() == 1) {
                    fieldI.setValue(fieldI.getCandidates().get(0));
                    removeCandidateFromRowColumnSection(fieldI.getFieldNumber());
                    noAction = false;
                } else {
                    for (SudokuField fieldJ : board) {
                        if (isSingleCandidateOnPosition(fieldI, fieldJ)) {
                            if (fieldI.getCandidates().remove((Integer) fieldJ.getValue())) {
                                noAction = false;
                            }
                        }
                    }
                    if (fieldI.getCandidates().size() == 1) {
                        fieldI.setValue(fieldI.getCandidates().get(0));
                        removeCandidateFromRowColumnSection(fieldI.getFieldNumber());
                        noAction = false;
                    }
                }
            }
        }
        return noAction;
    }

    private boolean singlePosition() {
        boolean noAction = true;
        List<Integer> listForRows = new ArrayList<>();
        List<Integer> listForColumns = new ArrayList<>();
        List<Integer> listForSections = new ArrayList<>();

        for (int i = 1; i < 10; i++) {
            for (SudokuField field : board) {
                if (field.getValue() == EMPTY && field.getRow() == i) {
                    listForRows.addAll(field.getCandidates());
                }
                if (field.getValue() == EMPTY && field.getColumn() == i) {
                    listForColumns.addAll(field.getCandidates());
                }
                if (field.getValue() == EMPTY && field.getSection() == i) {
                    listForSections.addAll(field.getCandidates());
                }
            }
            for (int j = 1; j < 10; j++) {
                for (SudokuField field : board) {
                    if (isCandidateOnSinglePosition(listForRows, listForColumns, listForSections, field, i, j)) {
                        field.setValue(j);
                        field.getCandidates().clear();
                        field.getCandidates().add(j);
                        removeCandidateFromRowColumnSection(field.getFieldNumber());
                        noAction = false;
                    }
                }
            }
            listForRows.clear();
            listForColumns.clear();
            listForSections.clear();
        }
        return noAction;
    }

    boolean solve() {
        if (!hasNoDuplicateValuesInRowColumnOrSection()) {
            return false;
        }
        Deque<Backtrack> backtracks = new ArrayDeque<>();
        boolean solved = false;
        boolean solvedCorrect = false;
        while (!solved) {
            boolean end = false;
            while (!end) {
                end = singlePosition() && singleCandidate();
            }
            if (!isFilled()) {
                if (!hasFieldWithNoCandidates()) {
                    addBoardToBacktracksAndSetOneOfCandidatesAsValue(backtracks);
                    solved = false;
                } else {
                    if (backtracks.size() == 0) {
                        solved = true;
                        solvedCorrect = false;
                    } else {
                        Backtrack b = backtracks.pop();
                        recoverBoard(b.getBoard());
                        getFieldByFieldNumber(b.getFieldNumber()).getCandidates().remove(0);
                        solved = false;
                    }
                }
            } else {
                solved = true;
                solvedCorrect = true;
            }
        }
        return solvedCorrect;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < board.length; i++) {
            if (i % 27 == 0) {
                result.append("\n\t-----------------------");
            }
            if (i % 9 == 0) {
                result.append("\n");
            }
            if (i % 3 == 0) {
                result.append("\t|");
            }
            result.append(board[i].getValue()).append("|");
        }
        result.append("\n\t-----------------------");
        return result.toString();
    }
}
