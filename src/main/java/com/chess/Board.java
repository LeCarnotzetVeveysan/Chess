package com.chess;

import com.utils.ModelUtils;

import static com.utils.ModelUtils.getCoordinatesFromIndices;
import static com.utils.ModelUtils.getIndicesFromCoordinates;

public class Board {

    private Cell[][] cells;

    public Board(){
        initializeBoard();
    }

    public String toStringSquare(){
        String toReturn = "";
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                toReturn += cells[i][j].getCoordinates();
            }
            toReturn += "\n";
        }
        return toReturn;
    }

    public void initializeBoard(){
        cells = new Cell[8][8];
        for(int i = 0; i <= 7; i++){
            for(int j = 0; j <= 7; j++){
                cells[i][j] = new Cell(getCoordinatesFromIndices(i,j));
            }
        }
        for (int i = 0; i < 8; i++){
            cells[1][i].setCurrentPiece(new Pawn('b', getCoordinatesFromIndices(1,i)));
            System.out.println(getCoordinatesFromIndices(i,1));
        }
    }

    public Cell[][] getCells(){
        return cells;
    }

    public Cell getSpecificCell(String inCoordinates){
        int[] coordinates = getIndicesFromCoordinates(inCoordinates);
        return cells[coordinates[1]][coordinates[0]];
    }
}
