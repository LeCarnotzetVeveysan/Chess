package com.chess;

import com.utils.ModelUtils;

import static com.utils.ModelUtils.getCoordinatesFromIndices;

public class Board {

    private Cell[][] cells;

    public Board(){
        initializeBoard();
    }

    public String toStringSquare(){
        String toReturn = "";
        for(int i = 7; i >= 0; i--){
            for(int j = 0; j < 8; j++){
                toReturn += cells[i][j].getCoordinates();
            }
            toReturn += "\n";
        }
        return toReturn;
    }

    public void initializeBoard(){
        cells = new Cell[8][8];
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                cells[j][i] = new Cell(getCoordinatesFromIndices(i,j));
            }
        }
    }
}
