package com.chess;

import java.util.ArrayList;

public class Rook extends Piece {

    public Rook(char inColor, String inCurrentCell) {
        super("rook", inColor, inCurrentCell);
    }

    @Override
    public ArrayList<String> setAccessibleCells(Board board) {
        return null;
    }
}
