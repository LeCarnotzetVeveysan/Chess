package com.chess;

import java.util.ArrayList;

public abstract class Piece {

    private String imgResource;
    private char color;
    private String currentCell;
    private ArrayList<String> accessibleCells;
    private ArrayList<String> cellHistory;
    private boolean hasMoved;

    public abstract ArrayList<String> setAccessibleCells(Board board);

    public ArrayList<String> getAccessibleCells(){
        return accessibleCells;
    }

    public String getCurrentCell(){
        return cellHistory.get(cellHistory.size() - 1);
    }
}
