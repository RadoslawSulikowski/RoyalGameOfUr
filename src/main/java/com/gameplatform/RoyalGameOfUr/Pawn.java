package com.gameplatform.RoyalGameOfUr;

import javafx.scene.control.Button;
import javafx.scene.shape.Circle;


class Pawn extends Button {

    private int position = 1;
    private String pawnColor;

    Pawn(String pawnColor) {
        super();
        this.pawnColor = pawnColor;
        this.setShape(new Circle(30));
        this.setMinSize(60, 60);
        this.setMaxSize(60, 60);
        this.setText("7");
    }

    String getPawnColor() {
        return pawnColor;
    }

    int getPosition() {
        return position;
    }

    void setPosition(int position) {
        this.position = position;
    }

    void putPawnOnStart() {
        this.position = 1;
    }

}