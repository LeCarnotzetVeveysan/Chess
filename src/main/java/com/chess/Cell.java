package com.chess;

public class Cell {

    private String coordinates;
    private Piece piece;
    private Boolean validMove;

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

    public Boolean getValidMove() {
        return validMove;
    }

    public void setValidMove(boolean inState){
        validMove = inState;
    }
}
