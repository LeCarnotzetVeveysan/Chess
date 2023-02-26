package com.chess;

public class Cell {

    private String coordinates;
    private Piece currentPiece;

    public Cell(String inCoord){
        coordinates = inCoord;
        currentPiece = null;
    }
}
