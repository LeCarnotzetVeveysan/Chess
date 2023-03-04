package com.chess;

import java.util.ArrayList;

public class Knight extends Piece {
    public Knight(char inColor, String inCurrentCell) {
        super("knight", inColor, inCurrentCell);
    }

    @Override
    public ArrayList<String> setAccessibleCells(Board board) {
        return null;
    }
}
