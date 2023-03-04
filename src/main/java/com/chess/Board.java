package com.chess;

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

        initializePieces();
    }

    private void initializePieces() {
        //Kings
        cells[7][4].setPiece(new King('w', "e1"));
        cells[0][4].setPiece(new King('b', "e8"));
        //Queens
        cells[7][3].setPiece(new Queen('w', "d1"));
        cells[0][3].setPiece(new Queen('b', "d8"));
        //Bishops
        cells[7][2].setPiece(new Bishop('w', "c1"));
        cells[7][5].setPiece(new Bishop('w', "f1"));
        cells[0][2].setPiece(new Bishop('b', "c8"));
        cells[0][5].setPiece(new Bishop('b', "f8"));
        //Knights
        cells[7][1].setPiece(new Knight('w', "b1"));
        cells[7][6].setPiece(new Knight('w', "g1"));
        cells[0][1].setPiece(new Knight('b', "b8"));
        cells[0][6].setPiece(new Knight('b', "g8"));
        //Rooks
        cells[7][0].setPiece(new Rook('w', "b1"));
        cells[7][7].setPiece(new Rook('w', "g1"));
        cells[0][0].setPiece(new Rook('b', "b8"));
        cells[0][7].setPiece(new Rook('b', "g8"));
        //Pawns
        for (int i = 0; i < 8; i++){
            cells[6][i].setPiece(new Pawn('w', getCoordinatesFromIndices(6,i)));
            cells[1][i].setPiece(new Pawn('b', getCoordinatesFromIndices(1,i)));
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
