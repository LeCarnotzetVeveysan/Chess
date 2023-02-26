package com.utils;

public class ModelUtils {
    public static int[] getIndicesFromCoordinates(String inCoordinates){
        char[] stringCoord = inCoordinates.toCharArray();
        int[] intCoord = new int[2];
        intCoord[0] = stringCoord[0] - 97;
        intCoord[1] = stringCoord[1] - 1;
        return intCoord;
    }

    public static String getCoordinatesFromIndices(int inXCoord, int inYCoord){
        String xChar = String.valueOf((char) inXCoord);
        return xChar + (inYCoord + 1);
    }
}


