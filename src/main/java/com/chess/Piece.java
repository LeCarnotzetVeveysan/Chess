package com.chess;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class Piece {

    private String type;
    private char color;
    private String imgResource;
    private ArrayList<String> accessibleCells;
    private ArrayList<String> cellHistory;

    public abstract ArrayList<String> setAccessibleCells(Board board);

    public ArrayList<String> getAccessibleCells(){
        return accessibleCells;
    }

    public String getCurrentCell(){
        return cellHistory.get(cellHistory.size() - 1);
    }

    public void setCurrentCell(String inCell){ cellHistory.add(inCell); }

    public Piece(String inType, char inColor, String inStartCell){
        type = inType;
        color = inColor;
        imgResource = type + "_" + color;
        accessibleCells = null;
        cellHistory = new ArrayList<>(Arrays.asList(inStartCell));
    }
}
