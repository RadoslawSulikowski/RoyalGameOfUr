package com.kodilla;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;

import static javafx.scene.layout.GridPane.setColumnIndex;
import static javafx.scene.layout.GridPane.setRowIndex;

public class BluePawn extends Button implements Pawn{

    private Double position = 0.0;
    private int fieldsToMove = 0;

    public void setFieldsToMove(int fieldsToMove){
        this.fieldsToMove = fieldsToMove;
    }
    public BluePawn(GridPane grid){
        super();
        this.setShape(new Circle(30));
        this.setMinSize(60,60);
        this.setMaxSize(60,60);
        this.setOnAction((e) -> {

            this.position += this.fieldsToMove;
            fieldsToMove = 0;
            if(this.position < 17) {
                for(Node node : grid.getChildren()) {
                    if(node instanceof ImageView && ((ImageView) node).getY() == position) {
                        setColumnIndex(this, grid.getColumnIndex(node));
                        setRowIndex(this,grid.getRowIndex(node));
                    }
                }
            } else {
                setColumnIndex(this, 9);
                setRowIndex(this,5);
            }
        });
    }
}
