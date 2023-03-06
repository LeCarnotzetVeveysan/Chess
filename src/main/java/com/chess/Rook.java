package com.chess;

import java.util.ArrayList;

import static com.utils.ModelUtils.getCoordinatesFromIndices;
import static com.utils.ModelUtils.getIndicesFromCoordinates;

public class Rook extends Piece {

    public Rook(char inColor, String inCurrentCell) {
        super("rook", inColor, inCurrentCell);
    }

    @Override
    public void calculateAccessibleCells(Board board) {
        ArrayList<String> accessibleCells = new ArrayList<>();
        int[] currentPos = getIndicesFromCoordinates(super.getCurrentCell());

        for (int i = currentPos[0] - 1; i >= 0; i--) {
            String nCellUpMove = getCoordinatesFromIndices(i, currentPos[1]);
            Cell nCellUp = board.getSpecificCell(nCellUpMove);
            if (!nCellUp.getOccupied()) {
                accessibleCells.add(nCellUpMove);
            } else if (nCellUp.getOccupied() && nCellUp.getPiece().getColor() != super.getColor()) {
                accessibleCells.add(nCellUpMove);
                break;
            } else {
                break;
            }
        }

        for (int i = currentPos[0] + 1; i <= 7; i++){
            String nCellDownMove = getCoordinatesFromIndices(i, currentPos[1]);
            Cell nCellDown = board.getSpecificCell(nCellDownMove);
            if (!nCellDown.getOccupied()) {
                accessibleCells.add(nCellDownMove);
            } else if (nCellDown.getOccupied() && nCellDown.getPiece().getColor() != super.getColor()) {
                accessibleCells.add(nCellDownMove);
                break;
            } else {
                break;
            }
        }

        for (int i = currentPos[1] - 1; i >= 0; i--){
            String nCellLeftMove = getCoordinatesFromIndices(currentPos[0], i);
            Cell nCellLeft = board.getSpecificCell(nCellLeftMove);
            if (!nCellLeft.getOccupied()) {
                accessibleCells.add(nCellLeftMove);
            } else if (nCellLeft.getOccupied() && nCellLeft.getPiece().getColor() != super.getColor()) {
                accessibleCells.add(nCellLeftMove);
                break;
            } else {
                break;
            }
        }

        for (int i = currentPos[1] + 1; i <= 7; i++){
            String nCellRightMove = getCoordinatesFromIndices(currentPos[0],i );
            Cell nCellRight = board.getSpecificCell(nCellRightMove);
            if (!nCellRight.getOccupied()) {
                accessibleCells.add(nCellRightMove);
            } else if (nCellRight.getOccupied() && nCellRight.getPiece().getColor() != super.getColor()) {
                accessibleCells.add(nCellRightMove);
                break;
            } else {
                break;
            }
        }

        super.setAccessibleCells(accessibleCells);

    }
}

