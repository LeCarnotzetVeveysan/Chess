package com.tests;

import com.chess.Board;
import com.utils.LoadScene;
import com.utils.ModelUtils;

import java.io.IOException;
import java.util.Arrays;

public class test1 {
    public static void main(String[] args) throws IOException {
        System.out.println(Arrays.toString(ModelUtils.getIndicesFromCoordinates("b5")));
        System.out.println(ModelUtils.getCoordinatesFromIndices(1,3));
        System.out.println(Arrays.toString(ModelUtils.getIndicesFromCoordinates("e2")));
        System.out.println(ModelUtils.getCoordinatesFromIndices(4,6));
    }
}
