package com.chess;

import java.util.ArrayList;

import static com.utils.ModelUtils.*;

public class Bishop extends Piece {
    public Bishop(char inColor, String inCurrentCell) {
        super("bishop", inColor, inCurrentCell);
    }

    @Override
    public void calculateAccessibleCells(boolean removeInvalidMoves, Board board) {

        ArrayList<String> accessibleCells = new ArrayList<>();
        int[] currentPos = coordinatesToIndices(super.getCurrentCell());

        int i,j;

        for (i = currentPos[0] + 1, j = currentPos[1] + 1; i <= 7 && j <= 7; i++, j++) {
            String nCellBottomRightMove = indicesToCoordinates(i, j);
            Cell nCellBottomRight = board.getSpecificCell(nCellBottomRightMove);
            if (!nCellBottomRight.getOccupied()) {
                accessibleCells.add(nCellBottomRightMove);
            } else if (nCellBottomRight.getOccupied() && nCellBottomRight.getPiece().getColor() != super.getColor()) {
                accessibleCells.add(nCellBottomRightMove);
                break;
            } else {
                break;
            }
        }

        for (i = currentPos[0] + 1, j = currentPos[1] - 1; i <= 7 && j >= 0; i++, j--) {
            String nCellBottomLeftMove = indicesToCoordinates(i, j);
            Cell nCellBottomLeft = board.getSpecificCell(nCellBottomLeftMove);
            if (!nCellBottomLeft.getOccupied()) {
                accessibleCells.add(nCellBottomLeftMove);
            } else if (nCellBottomLeft.getOccupied() && nCellBottomLeft.getPiece().getColor() != super.getColor()) {
                accessibleCells.add(nCellBottomLeftMove);
                break;
            } else {
                break;
            }
        }

        for (i = currentPos[0] - 1, j = currentPos[1] + 1; i >= 0 && j <= 7; i--, j++) {
            String nCellTopRightMove = indicesToCoordinates(i, j);
            Cell nCellTopRight = board.getSpecificCell(nCellTopRightMove);
            if (!nCellTopRight.getOccupied()) {
                accessibleCells.add(nCellTopRightMove);
            } else if (nCellTopRight.getOccupied() && nCellTopRight.getPiece().getColor() != super.getColor()) {
                accessibleCells.add(nCellTopRightMove);
                break;
            } else {
                break;
            }
        }

        for (i = currentPos[0] - 1, j = currentPos[1] - 1; i >= 0 && j >= 0; i--, j--) {
            String nCellTopLeftMove = indicesToCoordinates(i, j);
            Cell nCellTopLeft = board.getSpecificCell(nCellTopLeftMove);
            if (!nCellTopLeft.getOccupied()) {
                accessibleCells.add(nCellTopLeftMove);
            } else if (nCellTopLeft.getOccupied() && nCellTopLeft.getPiece().getColor() != super.getColor()) {
                accessibleCells.add(nCellTopLeftMove);
                break;
            } else {
                break;
            }
        }

        if(removeInvalidMoves){
            ArrayList<String> cleanedAccessibleCells = removeIllegalCells(board, accessibleCells, super.getCurrentCell());
            super.setAccessibleCells(cleanedAccessibleCells);
        } else {
            super.setAccessibleCells(accessibleCells);
        }

    }
}
