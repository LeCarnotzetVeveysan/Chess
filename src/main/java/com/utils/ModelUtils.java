package com.utils;

import com.chess.Board;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class ModelUtils {

    public static int[] coordinatesToIndices(String inCoordinates){
        char[] stringCoord = inCoordinates.toCharArray();
        int[] intCoord = new int[2];
        intCoord[0] = 8 - Integer.parseInt(String.valueOf(stringCoord[1]));
        intCoord[1] = stringCoord[0] - 97;
        return intCoord;
    }

    public static String indicesToCoordinates(int inRow, int inCol){
        String xChar = String.valueOf((char) (inCol + 97));
        return xChar + (8 - inRow);
    }

    public static int columnToIndex(char column){
        return column - 97;
    }

    public static void setImage(ImageView iv, String fileName) throws FileNotFoundException {
        String path = "src/main/resources/images/" + fileName + ".png";
        Image image = new Image(new FileInputStream(path));
        iv.setImage(image);
    }

    public static ArrayList<String> removeIllegalCells(Board mainBoard, ArrayList<String> startList, String inCurrentCell) {
        ArrayList<String> validMoves = new ArrayList<>();
        char color = mainBoard.getSpecificCell(inCurrentCell).getPiece().getColor();
        for(String testMove : startList){
            Board tempBoard = new Board(mainBoard.getBoardPosition(), mainBoard.getLastMove());
            tempBoard.movePiece(false, true, true, false, inCurrentCell, testMove);
            if(!tempBoard.getIsCheck(color)){
                validMoves.add(testMove);
            }
        }
        return validMoves;
    }

    public static char getCellColor(String coordinates){
        int[] indices = coordinatesToIndices(coordinates);
        if((indices[0] + indices[1])%2 == 0 ){
            return 'w';
        } else {
            return 'b';
        }
    }
}


