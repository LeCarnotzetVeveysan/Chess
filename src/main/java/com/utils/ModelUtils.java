package com.utils;

import com.chess.Board;
import com.chess.Piece;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class ModelUtils {

    public static int[] getIndicesFromCoordinates(String inCoordinates){
        char[] stringCoord = inCoordinates.toCharArray();
        int[] intCoord = new int[2];
        intCoord[0] = 8 - Integer.parseInt(String.valueOf(stringCoord[1]));
        intCoord[1] = stringCoord[0] - 97;
        return intCoord;
    }

    public static String getCoordinatesFromIndices(int inRow, int inCol){
        String xChar = String.valueOf((char) (inCol + 97));
        return xChar + (8 - inRow);
    }

    public static void setImage(ImageView iv, String fileName) throws FileNotFoundException {
        String path = "src/main/resources/images/" + fileName + ".png";
        Image image = new Image(new FileInputStream(path));
        iv.setImage(image);
    }

    public static ArrayList<String> removeIllegalCells(Board mainBoard, ArrayList<String> startList, Piece inPiece) {
        ArrayList<String> validMoves = new ArrayList<>();
        for(String testMove : startList){
            Board tempBoard = new Board(mainBoard.getBoardPosition());
            tempBoard.movePiece(inPiece.getCurrentCell(), testMove);
            tempBoard.calculateValidMoves();
            if(!tempBoard.getIsCheck(inPiece.getColor())){
                validMoves.add(testMove);
            }
        }
        return validMoves;
    }
}


