package com.chess;

import java.util.ArrayList;

import static com.utils.ModelUtils.*;

public class King extends Piece {

    public King(char inColor, String inCurrentCell) {
        super("king", inColor, inCurrentCell);
    }

    @Override
    public void calculateAccessibleCells(boolean removeInvalidMoves, Board board) {

        ArrayList<String> accessibleCells = new ArrayList<>();
        int[] currentPos = getIndicesFromCoordinates(super.getCurrentCell());

        boolean isOnTopBorder = currentPos[0] == 0;
        boolean isOnBottomBorder = currentPos[0] == 7;
        boolean isOnLeftBorder = currentPos[1] == 0;
        boolean isOnRightBorder = currentPos[1] == 7;

        if(!isOnTopBorder){
            String topMiddleMove = getCoordinatesFromIndices(currentPos[0] - 1, currentPos[1]);
            Cell topMiddleCell = board.getSpecificCell(topMiddleMove);
            if(!topMiddleCell.getOccupied() || (topMiddleCell.getOccupied() && topMiddleCell.getPiece().getColor() != super.getColor())) {
                accessibleCells.add(topMiddleMove);
            }
            
            if(!isOnLeftBorder) {
                String topLeftMove = getCoordinatesFromIndices(currentPos[0] - 1, currentPos[1] - 1);
                Cell topLeftCell = board.getSpecificCell(topLeftMove);
                if (!topLeftCell.getOccupied() || (topLeftCell.getOccupied() && topLeftCell.getPiece().getColor() != super.getColor())) {
                    accessibleCells.add(topLeftMove);
                }
            }

            if(!isOnRightBorder) {
                String topRightMove = getCoordinatesFromIndices(currentPos[0] - 1, currentPos[1] + 1);
                Cell topRightCell = board.getSpecificCell(topRightMove);
                if (!topRightCell.getOccupied() || (topRightCell.getOccupied() && topRightCell.getPiece().getColor() != super.getColor())) {
                    accessibleCells.add(topRightMove);
                }
            }
        }

        if(!isOnBottomBorder){
            String bottomMiddleMove = getCoordinatesFromIndices(currentPos[0] + 1, currentPos[1]);
            Cell bottomMiddleCell = board.getSpecificCell(bottomMiddleMove);
            if(!bottomMiddleCell.getOccupied() || (bottomMiddleCell.getOccupied() && bottomMiddleCell.getPiece().getColor() != super.getColor())) {
                accessibleCells.add(bottomMiddleMove);
            }

            if(!isOnLeftBorder) {
                String bottomLeftMove = getCoordinatesFromIndices(currentPos[0] + 1, currentPos[1] - 1);
                Cell bottomLeftCell = board.getSpecificCell(bottomLeftMove);
                if (!bottomLeftCell.getOccupied() || (bottomLeftCell.getOccupied() && bottomLeftCell.getPiece().getColor() != super.getColor())) {
                    accessibleCells.add(bottomLeftMove);
                }
            }

            if(!isOnRightBorder) {
                String bottomRightMove = getCoordinatesFromIndices(currentPos[0] + 1, currentPos[1] + 1);
                Cell bottomRightCell = board.getSpecificCell(bottomRightMove);
                if (!bottomRightCell.getOccupied() || (bottomRightCell.getOccupied() && bottomRightCell.getPiece().getColor() != super.getColor())) {
                    accessibleCells.add(bottomRightMove);
                }
            }
        }

        if(!isOnLeftBorder) {
            String middleLeftMove = getCoordinatesFromIndices(currentPos[0], currentPos[1] - 1);
            Cell middleLeftCell = board.getSpecificCell(middleLeftMove);
            if (!middleLeftCell.getOccupied() || (middleLeftCell.getOccupied() && middleLeftCell.getPiece().getColor() != super.getColor())) {
                accessibleCells.add(middleLeftMove);
            }
        }

        if(!isOnRightBorder) {
            String middleRightMove = getCoordinatesFromIndices(currentPos[0], currentPos[1] + 1);
            Cell middleRightCell = board.getSpecificCell(middleRightMove);
            if (!middleRightCell.getOccupied() || (middleRightCell.getOccupied() && middleRightCell.getPiece().getColor() != super.getColor())) {
                accessibleCells.add(middleRightMove);
            }
        }

        //Missing castling

        if(removeInvalidMoves){
            ArrayList<String> cleanedAccessibleCells = removeIllegalCells(board, accessibleCells, super.getCurrentCell());
            super.setAccessibleCells(cleanedAccessibleCells);
        } else {
            super.setAccessibleCells(accessibleCells);
        }


    }

}
