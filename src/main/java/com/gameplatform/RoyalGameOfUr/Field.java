package com.gameplatform.RoyalGameOfUr;


import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

class Field extends ImageView {

    private boolean isBusyByBlue = false;
    private boolean isBusyByGreen = false;
    private boolean isHighlighted = false;
    private int fieldNumberForGreen;
    private int fieldNumberForBlue;

    Field(Image image) {
        super(image);
    }

    boolean getIsHighlighted() {
        return isHighlighted;
    }

    int getFieldNumber(String pawnColor) {
        if (pawnColor.equals("BLUE")) {
            return fieldNumberForBlue;
        }
        return fieldNumberForGreen;
    }

    boolean getIsBusy() {
        return (isBusyByBlue || isBusyByGreen);
    }

    boolean getIsBusy(String pawnColor) {
        if (pawnColor.equals("GREEN")) {
            return isBusyByGreen;
        }
        return isBusyByBlue;
    }

    void setIsHighlighted() {
        isHighlighted = true;
    }

    void setFieldNumberForGreen(int fieldNumberForGreen) {
        this.fieldNumberForGreen = fieldNumberForGreen;
    }

    void setFieldNumberForBlue(int fieldNumberForBlue) {
        this.fieldNumberForBlue = fieldNumberForBlue;
    }

    void setIsBusyByBlue(boolean busy) {
        isBusyByBlue = busy;
    }

    void setIsBusyByGreen(boolean busy) {
        isBusyByGreen = busy;
    }


    Pawn getPawnFromField(GridPane grid, int fieldNumber, String pawnColor) {
        for (Node node : grid.getChildren()) {
            if (node instanceof Pawn && (((Pawn) node).getPawnColor().equals(pawnColor)) && (((Pawn) node).getPosition() == convertFieldNumberOtherColors(fieldNumber))) {
                return (Pawn) node;
            }
        }
        return null;
    }

    static int convertFieldNumberOtherColors(int fieldNumber) {
        if (fieldNumber == 13) {
            return 17;
        }
        if (fieldNumber == 14) {
            return 16;
        }
        if (fieldNumber == 16) {
            return 14;
        }
        if (fieldNumber == 17) {
            return 13;
        }
        return fieldNumber;
    }
}
