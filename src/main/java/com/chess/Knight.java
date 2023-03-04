package com.chess;

public class Knight extends Piece {
    public Knight(char inColor, String inCurrentCell) {
        super("knight", inColor, inCurrentCell);
    }

    @Override
    public void calculateAccessibleCells(Board board) {
    }
}
