package com.chess;

import java.util.ArrayList;

import static com.utils.ModelUtils.*;

public class Pawn extends Piece {
    public Pawn(char inColor, String inCurrentCell) {
        super("pawn", inColor, inCurrentCell);
    }

    @Override
    public void calculateAccessibleCells(boolean removeInvalidMoves, Board board) {
        ArrayList<String> accessibleCells = new ArrayList<>();
        int[] currentPos = getIndicesFromCoordinates(super.getCurrentCell());
        int upDown = super.getColor() == 'b' ? 1 : -1;

        if(currentPos[0] != 0 && currentPos[0] != 7) {
            String oneCellMove = getCoordinatesFromIndices(currentPos[0] + upDown, currentPos[1]);
            if (!board.getSpecificCell(oneCellMove).getOccupied()) {
                accessibleCells.add(oneCellMove);
            }


            if (!super.getMoved()) {
                String twoCellMove = getCoordinatesFromIndices(currentPos[0] + (upDown * 2), currentPos[1]);
                if (!board.getSpecificCell(oneCellMove).getOccupied() && !board.getSpecificCell(twoCellMove).getOccupied()) {
                    accessibleCells.add(twoCellMove);
                }
            }

            if (currentPos[1] != 0) {
                String takeLeftMove = getCoordinatesFromIndices(currentPos[0] + upDown, currentPos[1] - 1);
                Cell takeLeftCell = board.getSpecificCell(takeLeftMove);
                if (takeLeftCell.getOccupied() && takeLeftCell.getPiece().getColor() != super.getColor()) {
                    accessibleCells.add(takeLeftMove);
                }
            }

            if (currentPos[1] != 7) {
                String takeRightMove = getCoordinatesFromIndices(currentPos[0] + upDown, currentPos[1] + 1);
                Cell takeRightCell = board.getSpecificCell(takeRightMove);
                if (takeRightCell.getOccupied() && takeRightCell.getPiece().getColor() != super.getColor()) {
                    accessibleCells.add(takeRightMove);
                }
            }

            //Do en-passant movement

        }

        if(removeInvalidMoves){
            ArrayList<String> cleanedAccessibleCells = removeIllegalCells(board, accessibleCells, super.getCurrentCell());
            super.setAccessibleCells(cleanedAccessibleCells);
        } else {
            super.setAccessibleCells(accessibleCells);
        }

    }
}
