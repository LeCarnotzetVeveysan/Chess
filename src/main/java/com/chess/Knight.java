package com.chess;

import java.util.ArrayList;

import static com.utils.ModelUtils.getCoordinatesFromIndices;
import static com.utils.ModelUtils.getIndicesFromCoordinates;

public class Knight extends Piece {
    public Knight(char inColor, String inCurrentCell) {
        super("knight", inColor, inCurrentCell);
    }

    @Override
    public void calculateAccessibleCells(Board board) {

        ArrayList<String> accessibleCells = new ArrayList<>();
        int[] currentPos = getIndicesFromCoordinates(super.getCurrentCell());

        boolean isInTopRow = currentPos[0] == 0;
        boolean isInBotRow = currentPos[0] == 7;
        boolean isInLeftCol = currentPos[1] == 0;
        boolean isOnRightCol = currentPos[1] == 7;
        boolean isInTopTwoRows = currentPos[0] <= 1;
        boolean isInBotTwoRows = currentPos[0] >= 6;
        boolean isInLeftTwoCols = currentPos[1] <= 1;
        boolean isInRightTwoCols = currentPos[1] >= 6;

        if(!isInTopTwoRows && !isInLeftCol) {
            String topLeftMove = getCoordinatesFromIndices(currentPos[0] - 2, currentPos[1] - 1);
            Cell topLeftCell = board.getSpecificCell(topLeftMove);
            if (!topLeftCell.getOccupied() || (topLeftCell.getOccupied() && topLeftCell.getPiece().getColor() != super.getColor())) {
                accessibleCells.add(topLeftMove);
            }
        }

        if(!isInTopTwoRows && !isOnRightCol) {
            String topRightMove = getCoordinatesFromIndices(currentPos[0] - 2, currentPos[1] + 1);
            Cell topRightCell = board.getSpecificCell(topRightMove);
            if (!topRightCell.getOccupied() || (topRightCell.getOccupied() && topRightCell.getPiece().getColor() != super.getColor())) {
                accessibleCells.add(topRightMove);
            }
        }

        if(!isInRightTwoCols && !isInTopRow) {
            String rightTopMove = getCoordinatesFromIndices(currentPos[0] - 1, currentPos[1] + 2);
            Cell rightTopCell = board.getSpecificCell(rightTopMove);
            if (!rightTopCell.getOccupied() || (rightTopCell.getOccupied() && rightTopCell.getPiece().getColor() != super.getColor())) {
                accessibleCells.add(rightTopMove);
            }
        }

        if(!isInRightTwoCols && !isInBotRow) {
            String rightBottomMove = getCoordinatesFromIndices(currentPos[0] + 1, currentPos[1] + 2);
            Cell rightBottomCell = board.getSpecificCell(rightBottomMove);
            if (!rightBottomCell.getOccupied() || (rightBottomCell.getOccupied() && rightBottomCell.getPiece().getColor() != super.getColor())) {
                accessibleCells.add(rightBottomMove);
            }
        }

        if(!isInBotTwoRows && !isOnRightCol) {
            String bottomRightMove = getCoordinatesFromIndices(currentPos[0] + 2, currentPos[1] + 1);
            Cell bottomRightCell = board.getSpecificCell(bottomRightMove);
            if (!bottomRightCell.getOccupied() || (bottomRightCell.getOccupied() && bottomRightCell.getPiece().getColor() != super.getColor())) {
                accessibleCells.add(bottomRightMove);
            }
        }

        if(!isInBotTwoRows && !isInLeftCol) {
            String bottomLeftMove = getCoordinatesFromIndices(currentPos[0] + 2, currentPos[1] - 1);
            Cell bottomLeftCell = board.getSpecificCell(bottomLeftMove);
            if (!bottomLeftCell.getOccupied() || (bottomLeftCell.getOccupied() && bottomLeftCell.getPiece().getColor() != super.getColor())) {
                accessibleCells.add(bottomLeftMove);
            }
        }

        if(!isInLeftTwoCols && !isInTopRow) {
            String rightTopMove = getCoordinatesFromIndices(currentPos[0] - 1, currentPos[1] - 2);
            Cell rightTopCell = board.getSpecificCell(rightTopMove);
            if (!rightTopCell.getOccupied() || (rightTopCell.getOccupied() && rightTopCell.getPiece().getColor() != super.getColor())) {
                accessibleCells.add(rightTopMove);
            }
        }

        if(!isInLeftTwoCols && !isInBotRow) {
            String rightBottomMove = getCoordinatesFromIndices(currentPos[0] + 1, currentPos[1] - 2);
            Cell rightBottomCell = board.getSpecificCell(rightBottomMove);
            if (!rightBottomCell.getOccupied() || (rightBottomCell.getOccupied() && rightBottomCell.getPiece().getColor() != super.getColor())) {
                accessibleCells.add(rightBottomMove);
            }
        }

        super.setAccessibleCells(accessibleCells);

    }
}
