package com.gameplatform.sudokuSolver;

import javafx.scene.control.ToggleButton;

public class SudokuFieldButton extends ToggleButton {
    private int rowNumber;
    private int columnNumber;
    private int sectionNumber;
    private int fieldNumber;
    private boolean isValueDuplicated;


    SudokuFieldButton(String text) {
        super(text);
    }

    int getRowNumber() {
        return rowNumber;
    }

    void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
    }

    int getColumnNumber() {
        return columnNumber;
    }

    int getFieldNumber() {
        return fieldNumber;
    }

    boolean isValueDuplicated() {
        return isValueDuplicated;
    }

    void setColumnNumber(int columnNumber) {
        this.columnNumber = columnNumber;
    }

    int getSectionNumber() {
        return sectionNumber;
    }

    void setSectionNumber(int sectionNumber) {
        this.sectionNumber = sectionNumber;
    }

    void setFieldNumber(int fieldNumber) {
        this.fieldNumber = fieldNumber;
    }

    void setValueDuplicated(boolean valueDuplicated) {
        isValueDuplicated = valueDuplicated;
    }
}
