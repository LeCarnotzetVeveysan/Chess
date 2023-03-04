package com.chess;

public class Rook extends Piece {

    public Rook(char inColor, String inCurrentCell) {
        super("rook", inColor, inCurrentCell);
    }

    @Override
    public void calculateAccessibleCells(Board board) {

    }
}
