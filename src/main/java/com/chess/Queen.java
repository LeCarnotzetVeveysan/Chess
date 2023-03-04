package com.chess;

import java.util.ArrayList;

public class Queen extends Piece {

    public Queen(char inColor, String inCurrentCell) {
        super("queen", inColor, inCurrentCell);
    }
    @Override
    public ArrayList<String> setAccessibleCells(Board board) {
        return null;
    }
}
