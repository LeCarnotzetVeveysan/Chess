package com.chess;

import java.util.ArrayList;

public class King extends Piece {

    public King(String inType, char inColor, String inCurrentCell) {
        super(inType, inColor, inCurrentCell);
    }

    @Override
    public ArrayList<String> setAccessibleCells(Board board) {
        return null;
    }
}
