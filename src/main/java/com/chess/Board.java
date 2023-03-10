package com.chess;

import java.util.ArrayList;
import java.util.Arrays;

import static com.utils.ModelUtils.getCoordinatesFromIndices;
import static com.utils.ModelUtils.getIndicesFromCoordinates;

public class Board {

    private Cell[][] cells;
    private ArrayList<Piece> capturedPieces;
    private Piece activePiece;
    private char currentPlayer;

    public Board(){
        initializeBoard();
        initializePieces();
    }

    public Board(String gameState){
        initializeBoard();

        for(int i = 0; i < 64; i++){
            Piece piece = null;
            switch (gameState.charAt(i)){
                case 'K' -> piece = new King('w', getCoordinatesFromIndices(i/8, i%8));
                case 'k' -> piece = new King('b', getCoordinatesFromIndices(i/8, i%8));
                case 'Q' -> piece = new Queen('w', getCoordinatesFromIndices(i/8, i%8));
                case 'q' -> piece = new Queen('b', getCoordinatesFromIndices(i/8, i%8));
                case 'B' -> piece = new Bishop('w', getCoordinatesFromIndices(i/8, i%8));
                case 'b' -> piece = new Bishop('b', getCoordinatesFromIndices(i/8, i%8));
                case 'N' -> piece = new Knight('w', getCoordinatesFromIndices(i/8, i%8));
                case 'n' -> piece = new Knight('b', getCoordinatesFromIndices(i/8, i%8));
                case 'R' -> piece = new Rook('w', getCoordinatesFromIndices(i/8, i%8));
                case 'r' -> piece = new Rook('b', getCoordinatesFromIndices(i/8, i%8));
                case 'P' -> piece = new Pawn('w', getCoordinatesFromIndices(i/8, i%8));
                case 'p' -> piece = new Pawn('b', getCoordinatesFromIndices(i/8, i%8));
            }
            cells[i/8][i%8].setPiece(piece);
        }
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
        capturedPieces = new ArrayList<>();
        currentPlayer = 'w';
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
        cells[7][0].setPiece(new Rook('w', "a1"));
        cells[7][7].setPiece(new Rook('w', "h1"));
        cells[0][0].setPiece(new Rook('b', "a8"));
        cells[0][7].setPiece(new Rook('b', "h8"));
        //Pawns
        for (int i = 0; i < 8; i++){
            cells[6][i].setPiece(new Pawn('w', getCoordinatesFromIndices(6,i)));
            cells[1][i].setPiece(new Pawn('b', getCoordinatesFromIndices(1,i)));
        }
    }

    public Cell getSpecificCell(String inCoordinates){
        int[] coordinates = getIndicesFromCoordinates(inCoordinates);
        return cells[coordinates[0]][coordinates[1]];
    }

    public Piece getActivePiece(){
        return activePiece;
    }

    public void setActivePiece(Piece inPiece){
        activePiece = inPiece;
    }
    public void movePiece(String inStartCell, String inEndCell){
        Cell startCell = getSpecificCell(inStartCell);
        Cell endCell = getSpecificCell(inEndCell);
        if(endCell.getOccupied()){
            capturedPieces.add(endCell.getPiece());
            endCell.setPiece(null);
        }
        endCell.setPiece(startCell.getPiece());
        startCell.setPiece(null);
    }


}
