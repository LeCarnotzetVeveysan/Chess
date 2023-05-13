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
        int[] currentPos = coordinatesToIndices(super.getCurrentCell());
        int upDown = super.getColor() == 'b' ? 1 : -1;

        if(currentPos[0] != 0 && currentPos[0] != 7) {
            String oneCellMove = indicesToCoordinates(currentPos[0] + upDown, currentPos[1]);
            if (!board.getSpecificCell(oneCellMove).getOccupied()) {
                accessibleCells.add(oneCellMove);
            }


            if (!super.getMoved()) {
                String twoCellMove = indicesToCoordinates(currentPos[0] + (upDown * 2), currentPos[1]);
                if (!board.getSpecificCell(oneCellMove).getOccupied() && !board.getSpecificCell(twoCellMove).getOccupied()) {
                    accessibleCells.add(twoCellMove);
                }
            }

            if (currentPos[1] != 0) {
                String takeLeftMove = indicesToCoordinates(currentPos[0] + upDown, currentPos[1] - 1);
                Cell takeLeftCell = board.getSpecificCell(takeLeftMove);
                if (takeLeftCell.getOccupied() && takeLeftCell.getPiece().getColor() != super.getColor()) {
                    accessibleCells.add(takeLeftMove);
                }
            }

            if (currentPos[1] != 7) {
                String takeRightMove = indicesToCoordinates(currentPos[0] + upDown, currentPos[1] + 1);
                Cell takeRightCell = board.getSpecificCell(takeRightMove);
                if (takeRightCell.getOccupied() && takeRightCell.getPiece().getColor() != super.getColor()) {
                    accessibleCells.add(takeRightMove);
                }
            }

            //Do en-passant movement
            if(super.getColor() == 'w' && currentPos[0] == 3){
                String lastMove = board.getLastMove();
                boolean wasPawn = lastMove.charAt(0) == 'P';
                boolean twoCells = lastMove.charAt(2) == '7' && lastMove.charAt(5) == '5';

                if(wasPawn && twoCells){
                    boolean leftColumn = columnToIndex(lastMove.charAt(1)) == currentPos[1] - 1;
                    boolean rightColumn = columnToIndex(lastMove.charAt(1)) == currentPos[1] + 1;
                    if(leftColumn){
                        accessibleCells.add(indicesToCoordinates(currentPos[0] - 1, currentPos[1] - 1));
                    }
                    if(rightColumn){
                        accessibleCells.add(indicesToCoordinates(currentPos[0] - 1, currentPos[1] + 1));
                    }
                }
            }

            if(super.getColor() == 'b' && currentPos[0] == 4){
                String lastMove = board.getLastMove();
                boolean wasPawn = lastMove.charAt(0) == 'P';
                boolean twoCells = lastMove.charAt(2) == '2' && lastMove.charAt(5) == '4';

                if(wasPawn && twoCells){
                    boolean leftColumn = columnToIndex(lastMove.charAt(1)) == currentPos[1] - 1;
                    boolean rightColumn = columnToIndex(lastMove.charAt(1)) == currentPos[1] + 1;
                    if(leftColumn){
                        accessibleCells.add(indicesToCoordinates(currentPos[0] + 1 , currentPos[1] - 1));
                    }
                    if(rightColumn){
                        accessibleCells.add(indicesToCoordinates(currentPos[0] + 1, currentPos[1] + 1));
                    }
                }
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
