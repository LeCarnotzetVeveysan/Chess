package com.chess;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class Piece {

    private final String type;
    private final char color;
    private final String imageFileName;
    private ArrayList<String> accessibleCells;
    private final ArrayList<String> cellHistory;

    public char getType(boolean colorSpecific){
        if(colorSpecific) {
            if (color == 'w') {
                if (type.equals("knight")) {
                    return 'N';
                } else {
                    return Character.toUpperCase(type.charAt(0));
                }
            } else {
                if (type.equals("knight")) {
                    return 'n';
                } else {
                    return type.charAt(0);
                }
            }
        } else {
            if (type.equals("knight")) {
                return 'N';
            }

            return Character.toUpperCase(type.charAt(0));
        }
    }

    public char getColor(){
        return color;
    }

    public String getImageFileName(){
        return imageFileName;
    }

    public abstract void calculateAccessibleCells(boolean removeInvalidMoves, Board board);

    public void setAccessibleCells(ArrayList<String> inList){
        accessibleCells = inList;
    }

    public ArrayList<String> getAccessibleCells(){
        return accessibleCells;
    }

    public boolean getMoved(){
        return cellHistory.size() != 1;
    }

    public ArrayList<String> getCellHistory(){
        return cellHistory;
    }

    public String getCurrentCell(){
        return cellHistory.get(cellHistory.size() - 1);
    }

    public String getPreviousCell(){
        return cellHistory.get(cellHistory.size() - 2);
    }

    public void setCurrentCell(String inCell){ cellHistory.add(inCell); }

    public Piece(String inType, char inColor, String inStartCell){
        type = inType;
        color = inColor;
        imageFileName = type + "_" + color;
        accessibleCells = new ArrayList<>();
        cellHistory = new ArrayList<>(Arrays.asList(inStartCell));
    }

    public char getColumn(){
        return cellHistory.get(cellHistory.size() - 1).charAt(0);
    }

    public int getColumnIndex(){
        return getColumn() - 97;
    }

    public String toString(){
        return "I'm a" + (color == 'w' ? " white " : " black ") + type + " in cell " + getCurrentCell() + ".";
    }
}
