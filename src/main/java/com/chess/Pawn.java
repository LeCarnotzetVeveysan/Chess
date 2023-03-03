package com.chess;

import java.util.ArrayList;

public class Pawn extends Piece {
    public Pawn(char inColor, String inCurrentCell) {
        super("pawn", inColor, inCurrentCell);
    }

    @Override
    public ArrayList<String> setAccessibleCells(Board board) {
        return null;
    }
}
