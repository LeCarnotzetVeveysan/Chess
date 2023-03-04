package com.chess;

public class Queen extends Piece {

    public Queen(char inColor, String inCurrentCell) {
        super("queen", inColor, inCurrentCell);
    }
    @Override
    public void calculateAccessibleCells(Board board) {

    }
}
