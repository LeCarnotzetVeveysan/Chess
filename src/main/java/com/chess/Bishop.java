package com.chess;

public class Bishop extends Piece {
    public Bishop(char inColor, String inCurrentCell) {
        super("bishop", inColor, inCurrentCell);
    }

    @Override
    public void calculateAccessibleCells(Board board) {

    }
}
