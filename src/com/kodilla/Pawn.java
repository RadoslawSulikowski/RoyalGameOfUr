package com.kodilla;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;

import static javafx.scene.layout.GridPane.*;

class Pawn extends Button {

    private int position = 1;
    private int fieldsToMove = 0;
    private boolean ifMove = false;
    private String whichPlayerTurn = "";
    private String pawnColor;
    private static int bluePawnsOnStart = 7;
    private static int greenPawnsOnStart = 7;
    private static int bluePawnsAtFinish = 0;
    private static int greenPawnsAtFinish = 0;


    Pawn(GridPane grid, String pawnColor) {
        super();
        this.pawnColor = pawnColor;
        this.setShape(new Circle(30));
        this.setMinSize(60, 60);
        this.setMaxSize(60, 60);
        this.setText("7");
        if (pawnColor.equals("GREEN")) {
            this.setOnAction((e) -> {
                if (ifMove && whichPlayerTurn.equals("GREEN")) {
                    greenPawnMove(grid);
                }
            });
        }
        if (pawnColor.equals("BLUE")) {
            this.setOnAction((e) -> {
                if (ifMove && whichPlayerTurn.equals("BLUE")) {
                    bluePawnMove(grid);
                }
            });
        }

    }

    /*String getPawnColor() {
        return pawnColor;
    }*/

    private String getWhichPlayerTurn() {
        return whichPlayerTurn;
    }

    int getPosition() {
        return position;
    }

    private void putPawnOnStart() {
        this.position = 1;
    }

    private boolean getIfMove() {
        return ifMove;
    }

    private void setIfMove(boolean ifMove) {
        this.ifMove = ifMove;
    }

    private void setFieldsToMove(int fieldsToMove) {
        this.fieldsToMove = fieldsToMove;
    }

    private void setWhichPlayerTurn(String player) {
        whichPlayerTurn = player;
    }

    private void setFieldFreeOverGreenPawn(GridPane grid, int position) {
        for (Node node : grid.getChildren()) {
            if (node instanceof Field && ((Field) node).getFieldNumberForGreen() == position) {
                ((Field) node).setIsBusyByGreen(false);
            }
        }
    }

    private void setFieldFreeOverBluePawn(GridPane grid, int position) {
        for (Node node : grid.getChildren()) {
            if (node instanceof Field && ((Field) node).getFieldNumberForBlue() == position) {
                ((Field) node).setIsBusyByBlue(false);
            }
        }
    }

    private boolean ifFinalFieldFree(GridPane grid, int finalPosition, String pawnColor){
        for(Node node : grid.getChildren()){
            if(node instanceof Field
                    && pawnColor.equals("GREEN")
                    && ((Field) node).getFieldNumberForGreen() == finalPosition
                    && ((Field) node).getIsBusyByGreen()){
                return false;
            }
            if(node instanceof Field
                    && pawnColor.equals("BLUE")
                    && ((Field) node).getFieldNumberForBlue() == finalPosition
                    && ((Field) node).getIsBusyByBlue()){
                return false;
            }
        }
        return true;
    }

    private void putGreenPawnOnPosition(GridPane grid) {
        if (position < 18) {
            for (Node node : grid.getChildren()) {
                if (node instanceof Field && ((Field) node).getFieldNumberForGreen() == position) {
                    setConstraints(this, GridPane.getColumnIndex(node), GridPane.getRowIndex(node));
                    ((Field) node).setIsBusyByGreen(true);
                    if (!((Field) node).getIsHighlighted()) {
                        Pawn.setWhichPlayerTurnAllPawns(grid, "BLUE");
                    }
                }
            }
        } else {
            setConstraints(this, 8, 3);
            position = 18;
            setPawnTextWithNumberOfPawnsOnPosition(grid, ++greenPawnsAtFinish, 18, "GREEN");
            setOnAction((e) -> {
                if (whichPlayerTurn.equals("GREEN")) {
                    ((Label) grid.lookup("#WarningsLabel")).setText("You can't move this pawn - " +
                            "it has finished its journey over board!");
                }
            });
            if (greenPawnsAtFinish == 7) {
                ((Label) grid.lookup("#WhichTurnLabel")).setText("PLAYER GREEN");
                endOfGameAction(grid);
            } else {
                Pawn.setWhichPlayerTurnAllPawns(grid, "BLUE");
            }
        }
        if (greenPawnsAtFinish != 7) {
            Pawn.setIfMoveAllPawns(grid, false);
        }
    }

    private void putBluePawnOnPosition(GridPane grid) {
        if (position < 18) {
            for (Node node : grid.getChildren()) {
                if (node instanceof Field && ((Field) node).getFieldNumberForBlue() == position) {
                    setConstraints(this, GridPane.getColumnIndex(node), GridPane.getRowIndex(node));
                    ((Field) node).setIsBusyByBlue(true);
                    if (!((Field) node).getIsHighlighted()) {
                        Pawn.setWhichPlayerTurnAllPawns(grid, "GREEN");
                    }
                }
            }
        } else {
            setConstraints(this, 8, 5);
            position = 18;
            setPawnTextWithNumberOfPawnsOnPosition(grid, ++bluePawnsAtFinish, 18, "BLUE");
            setOnAction((e) -> {
                if (whichPlayerTurn.equals("BLUE")) {
                    ((Label) grid.lookup("#WarningsLabel")).setText("You can't move this pawn - " +
                            "it has finished its journey over board!");
                }
            });
            if (bluePawnsAtFinish == 7) {
                ((Label) grid.lookup("#WhichTurnLabel")).setText("PLAYER BLUE");
                endOfGameAction(grid);
            } else {
                Pawn.setWhichPlayerTurnAllPawns(grid, "GREEN");
            }
        }
        if (bluePawnsAtFinish != 7) {
            Pawn.setIfMoveAllPawns(grid, false);
        }
    }

    private void greenPawnMove(GridPane grid) {
        ((Label) grid.lookup("#WarningsLabel")).setText("");
        if (this.position == 1 && ifFinalFieldFree(grid, position + fieldsToMove, "GREEN")) {
            setPawnTextWithNumberOfPawnsOnPosition(grid, --greenPawnsOnStart, 1, "GREEN");
            setText("");
        }
        for (Node node : grid.getChildren()) {
            if ((position + fieldsToMove) >= 18) {
                setFieldFreeOverGreenPawn(grid, position);
                position += fieldsToMove;
                putGreenPawnOnPosition(grid);
                break;
            }
            if (node instanceof Field && ((Field) node).getFieldNumberForGreen() == (position + fieldsToMove)) {
                if (!((Field) node).getIsBusyByBlue() && !((Field) node).getIsBusyByGreen()) {
                    setFieldFreeOverGreenPawn(grid, position);
                    position += fieldsToMove;
                    setFieldsToMoveAllPawns(grid, 0);
                    putGreenPawnOnPosition(grid);
                    break;
                }
                if (((Field) node).getIsBusyByGreen()) {
                    ((Label) grid.lookup("#WarningsLabel")).setText("You can't move this pawn, cause on final field is your another pawn");
                }
                if (((Field) node).getIsBusyByBlue() && ((Field) node).getIsHighlighted()) {
                    ((Label) grid.lookup("#WarningsLabel")).setText("You can't knock pawn on highlighted field");
                }

                if (((Field) node).getIsBusyByBlue() && !((Field) node).getIsHighlighted()) {
                    setConstraints(((Field) node).getPawnFromField(grid, position + fieldsToMove), 7, 5);
                    (((Field) node).getPawnFromField(grid, position + fieldsToMove)).putPawnOnStart();
                    setPawnTextWithNumberOfPawnsOnPosition(grid, ++bluePawnsOnStart, 1, "BLUE");
                    setFieldFreeOverGreenPawn(grid, position);
                    position += fieldsToMove;
                    setFieldFreeOverBluePawn(grid, Field.convertFieldNumberOtherColors(position));
                    setFieldsToMoveAllPawns(grid, 0);
                    putGreenPawnOnPosition(grid);
                    break;
                }

            }
        }

    }

    private void bluePawnMove(GridPane grid) {
        ((Label) grid.lookup("#WarningsLabel")).setText("");
        if (this.position == 1 && ifFinalFieldFree(grid, position + fieldsToMove, "BLUE")) {
            setPawnTextWithNumberOfPawnsOnPosition(grid, --bluePawnsOnStart, 1, "BLUE");
            setText("");
        }
        for (Node node : grid.getChildren()) {
            if ((position + fieldsToMove) >= 18) {
                setFieldFreeOverBluePawn(grid, position);
                position += fieldsToMove;
                putBluePawnOnPosition(grid);
                break;
            }
            if (node instanceof Field && ((Field) node).getFieldNumberForBlue() == (position + fieldsToMove)) {
                if (!((Field) node).getIsBusyByBlue() && !((Field) node).getIsBusyByGreen()) {
                    setFieldFreeOverBluePawn(grid, position);
                    position += fieldsToMove;
                    setFieldsToMoveAllPawns(grid, 0);
                    putBluePawnOnPosition(grid);
                    break;
                }
                if (((Field) node).getIsBusyByBlue()) {
                    ((Label) grid.lookup("#WarningsLabel")).setText("You can't move this pawn, cause on final field is your another pawn");
                }

                if (((Field) node).getIsBusyByGreen() && ((Field) node).getIsHighlighted()) {

                    ((Label) grid.lookup("#WarningsLabel")).setText("You can't knock pawn on highlighted field");
                }
                if (((Field) node).getIsBusyByGreen() && !((Field) node).getIsHighlighted()) {
                    setConstraints(((Field) node).getPawnFromField(grid, position + fieldsToMove), 7, 3);
                    (((Field) node).getPawnFromField(grid, position + fieldsToMove)).putPawnOnStart();
                    setPawnTextWithNumberOfPawnsOnPosition(grid, ++greenPawnsOnStart, 1, "GREEN");
                    setFieldFreeOverBluePawn(grid, position);
                    position += fieldsToMove;
                    setFieldFreeOverGreenPawn(grid, Field.convertFieldNumberOtherColors(position));
                    setFieldsToMoveAllPawns(grid, 0);
                    putBluePawnOnPosition(grid);
                    break;
                }

            }
        }

    }

    private void endOfGameAction(GridPane grid){

        ((Label) grid.lookup("#WhichActionLabel")).setText("WON");
        for (Node node : grid.getChildren()) {
            if (node instanceof Button
                    /*&& !(((node)).getId().equals("MainMenuButton") || (node.getId().equals("NewGameButton")))*/) {
                ((Button) node).setOnAction((e) ->
                        ((Label) grid.lookup("#WarningsLabel")).setText("The game has finished!"));
            }
        }
    }

    private void setPawnTextWithNumberOfPawnsOnPosition(GridPane grid, int numberOfPawns, int pawnPosition, String pawnColor) {
        for (Node node : grid.getChildren()) {
            if (node instanceof Pawn && ((Pawn) node).getPosition() == pawnPosition && ((Pawn) node).pawnColor.equals(pawnColor)) {
                ((Pawn) node).setText(((Integer) numberOfPawns).toString());
            }
        }
    }

    static boolean getIfMoveFromPawn(GridPane grid) {
        for (Node node : grid.getChildren()) {
            if (node instanceof Pawn) {
                return ((Pawn) node).getIfMove();
            }
        }
        return false;
    }

    static String getWhichPlayerTurnFromPawn(GridPane grid) {
        for (Node node : grid.getChildren()) {
            if (node instanceof Pawn) {
                return ((Pawn) node).getWhichPlayerTurn();
            }
        }
        return "";
    }

    static void setFieldsToMoveAllPawns(GridPane grid, int value) {
        for (Node node : grid.getChildren()) {
            if (node instanceof Pawn) {
                ((Pawn) node).setFieldsToMove(value);
            }
        }
    }

    static void setIfMoveAllPawns(GridPane grid, boolean value) {
        for (Node node : grid.getChildren()) {
            if (node instanceof Pawn) {
                ((Pawn) node).setIfMove(value);
            }
            if (node instanceof Label && node.getId().equals("WhichActionLabel") && value) {
                ((Label) node).setText("MOVE PAWN");
            } else if (node instanceof Label && node.getId().equals("WhichActionLabel") && !value) {
                ((Label) node).setText("ROLL");
            }
        }
    }

    static void setWhichPlayerTurnAllPawns(GridPane grid, String player) {
        for (Node node : grid.getChildren()) {
            if (node instanceof Pawn) {
                ((Pawn) node).setWhichPlayerTurn(player);
            }
            if (node instanceof Label && node.getId().equals("WhichTurnLabel")) {
                ((Label) node).setText(player + " player turn:");
            }
        }
    }
}