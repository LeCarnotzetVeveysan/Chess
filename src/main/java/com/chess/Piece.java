package com.chess;

import java.util.ArrayList;

public abstract class Piece {

    private String imgResource;
    private char color;
    private ArrayList<String> accessibleCells;
    private ArrayList<String> cellHistory;
    private boolean hasMoved;

    public abstract ArrayList<String> getAccessibleCells(Board board);
}
