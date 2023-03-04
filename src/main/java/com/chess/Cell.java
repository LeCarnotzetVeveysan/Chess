package com.chess;

public class Cell {

    private String coordinates;
    private Piece piece;

    public Cell(String inCoord){
        coordinates = inCoord;
        piece = null;
    }

    public String getCoordinates(){
        return coordinates;
    }

    public void setPiece(Piece inPiece) {
        piece = inPiece;
    }

    public Piece getPiece(){
        return piece;
    }
}
