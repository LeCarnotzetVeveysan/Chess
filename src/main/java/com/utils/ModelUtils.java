package com.utils;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ModelUtils {

    public static int[] getIndicesFromCoordinates(String inCoordinates){
        char[] stringCoord = inCoordinates.toCharArray();
        int[] intCoord = new int[2];
        intCoord[0] = 8 - (stringCoord[1] - 48);
        intCoord[1] = stringCoord[0] - 97;
        return intCoord;
    }

    public static String getCoordinatesFromIndices(int inRow, int inCol){
        String xChar = String.valueOf((char) (inRow + 97));
        return xChar + (8 - inCol);
    }

    public static void setImage(ImageView iv, String fileName) throws FileNotFoundException {
        String path = "src/main/resources/images/" + fileName + ".png";
        Image image = new Image(new FileInputStream(path));
        iv.setImage(image);
        System.out.println("Done !");
    }
}


