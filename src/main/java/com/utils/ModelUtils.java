package com.utils;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ModelUtils {

    public static int[] getIndicesFromCoordinates(String inCoordinates){
        char[] stringCoord = inCoordinates.toCharArray();
        int[] intCoord = new int[2];
        intCoord[0] = 8 - Integer.parseInt(String.valueOf(stringCoord[1]));
        intCoord[1] = stringCoord[0] - 97;
        System.out.println(inCoordinates + " : " + intCoord[0] + " " + intCoord[1]);
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
}


