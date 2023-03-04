package com.chess;

import java.util.ArrayList;

public class Bishop extends Piece {
    public Bishop(char inColor, String inCurrentCell) {
        super("bishop", inColor, inCurrentCell);
    }

    @Override
    public ArrayList<String> setAccessibleCells(Board board) {
        return null;
    }
}
