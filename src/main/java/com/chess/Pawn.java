package com.chess;

import java.util.ArrayList;

import static com.utils.ModelUtils.getCoordinatesFromIndices;
import static com.utils.ModelUtils.getIndicesFromCoordinates;

public class Pawn extends Piece {
    public Pawn(char inColor, String inCurrentCell) {
        super("pawn", inColor, inCurrentCell);
    }

    @Override
    public void calculateAccessibleCells(Board board) {
        ArrayList<String> accessibleCells = new ArrayList<>();
        int[] currentPos = getIndicesFromCoordinates(super.getCurrentCell());
        int upDown = super.getColor() == 'w' ? 1 : -1;

        String oneCellUp = getCoordinatesFromIndices(currentPos[0] + (upDown), currentPos[1]);
        if(!board.getSpecificCell(oneCellUp).getOccupied()) {
            accessibleCells.add(oneCellUp);
        }

        if(!super.getMoved()){
            String twoCellsUp = getCoordinatesFromIndices(currentPos[0] + (upDown * 2), currentPos[1]);
            if(!board.getSpecificCell(twoCellsUp).getOccupied() && !board.getSpecificCell(twoCellsUp).getOccupied()){
                accessibleCells.add(twoCellsUp);
            }
        }

        if(currentPos[1] != 0){
            String cellUpLeft = getCoordinatesFromIndices(currentPos[0] + upDown, currentPos[1] - 1);
            if(board.getSpecificCell(cellUpLeft).getOccupied()) {
                accessibleCells.add(cellUpLeft);
            }
        }

        if(currentPos[1] != 7){
            String cellUpRight = getCoordinatesFromIndices(currentPos[0] + upDown, currentPos[1] + 1);
            if(board.getSpecificCell(cellUpRight).getOccupied()) {
                accessibleCells.add(cellUpRight);
            }
        }

        super.setAccessibleCells(accessibleCells);

    }
}
