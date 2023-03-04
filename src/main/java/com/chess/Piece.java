package com.chess;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class Piece {

    private final String type;
    private final char color;
    private final String imageFileName;
    private ArrayList<String> accessibleCells;
    private ArrayList<String> cellHistory;

    public String getImageFileName(){
        return imageFileName;
    }

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
        imageFileName = type + "_" + color;
        accessibleCells = null;
        cellHistory = new ArrayList<>(Arrays.asList(inStartCell));
    }
}
