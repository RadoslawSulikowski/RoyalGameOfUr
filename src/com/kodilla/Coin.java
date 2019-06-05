package com.kodilla;

import javafx.scene.shape.Circle;

import static javafx.scene.paint.Color.GOLD;
import static javafx.scene.paint.Color.SILVER;

public class Coin extends Circle{

    public void changeColor(){
        if(this.getFill().equals(GOLD)){
            this.setFill(SILVER);
        }
        if(this.getFill().equals(SILVER)){
            this.setFill(GOLD);
        }
    }
}
