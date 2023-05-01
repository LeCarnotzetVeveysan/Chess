package com.chess;

import static com.utils.ModelUtils.getCellColor;
import static com.utils.ModelUtils.getIndicesFromCoordinates;

public class Cell {

    private String coordinates;
    private Piece piece;
    private boolean validMove;

    public Cell(String inCoord){
        coordinates = inCoord;
        piece = null;
        validMove = false;
    }

    public String getCoordinates(){
        return coordinates;
    }

    public boolean getOccupied(){
        return piece != null;
    }

    public void setPiece(Piece inPiece) {
        piece = inPiece;
    }

    public Piece getPiece(){
        return piece;
    }

    public boolean getValidMove() {
        return validMove;
    }

    public void setValidMove(boolean inState){
        validMove = inState;
    }

    public int getColumnIndex(){
        return coordinates.charAt(0) - 97;
    }

    public char getColor(){
        return getCellColor(coordinates);
    }
}
