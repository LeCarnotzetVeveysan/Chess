package com.chess;

public class King extends Piece {

    public King(char inColor, String inCurrentCell) {
        super("king", inColor, inCurrentCell);
    }

    @Override
    public void calculateAccessibleCells(Board board) {

    }
}
