package com.chess;

import java.util.ArrayList;

public class King extends Piece {

    public King(char inColor, String inCurrentCell) {
        super("king", inColor, inCurrentCell);
    }

    @Override
    public ArrayList<String> setAccessibleCells(Board board) {
        return null;
    }
}
