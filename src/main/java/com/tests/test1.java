package com.tests;

import com.chess.Board;
import com.utils.LoadScene;
import com.utils.ModelUtils;

import java.io.IOException;
import java.util.Arrays;

public class test1 {
    public static void main(String[] args) throws IOException {
        System.out.println(Arrays.toString(ModelUtils.getIndicesFromCoordinates("a7")));
        System.out.println(ModelUtils.getCoordinatesFromIndices(0,1));
        System.out.println(Arrays.toString(ModelUtils.getIndicesFromCoordinates("h3")));
        System.out.println(ModelUtils.getCoordinatesFromIndices(7,5));
    }
}
