package com.kodilla;


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

    boolean getIsHighlighted() {
        return isHighlighted;
    }

    void setIsHighlighted() {
        isHighlighted = true;
    }

    int getFieldNumberForGreen() {
        return fieldNumberForGreen;
    }

    void setFieldNumberForGreen(int fieldNumberForGreen) {
        this.fieldNumberForGreen = fieldNumberForGreen;
    }

    int getFieldNumberForBlue() {
        return fieldNumberForBlue;
    }

    void setFieldNumberForBlue(int fieldNumberForBlue) {
        this.fieldNumberForBlue = fieldNumberForBlue;
    }

    boolean getIsBusyByBlue() {
        return isBusyByBlue;
    }

    void setIsBusyByBlue(boolean busy) {
        isBusyByBlue = busy;

    }

    boolean getIsBusyByGreen() {
        return isBusyByGreen;
    }

    void setIsBusyByGreen(boolean busy) {

        isBusyByGreen = busy;
    }

    Field(Image image) {
        super(image);
    }

    Pawn getOpponentPawnFromField(GridPane grid, int fieldNumber) {

        for (Node node : grid.getChildren()) {
            if (node instanceof Pawn && (((Pawn) node).getPosition() == convertFieldNumberOtherColors(fieldNumber))) {
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
