package com.controllers;

import com.chess.Board;
import com.chess.Cell;
import com.chess.Piece;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

import static com.utils.ModelUtils.*;

public class TestBoardController {

    @FXML
    private ImageView ca8IV, cb8IV, cc8IV, cd8IV, ce8IV, cf8IV, cg8IV, ch8IV;
    @FXML
    private ImageView ca7IV, cb7IV, cc7IV, cd7IV, ce7IV, cf7IV, cg7IV, ch7IV;
    @FXML
    private ImageView ca6IV, cb6IV, cc6IV, cd6IV, ce6IV, cf6IV, cg6IV, ch6IV;
    @FXML
    private ImageView ca5IV, cb5IV, cc5IV, cd5IV, ce5IV, cf5IV, cg5IV, ch5IV;
    @FXML
    private ImageView ca4IV, cb4IV, cc4IV, cd4IV, ce4IV, cf4IV, cg4IV, ch4IV;
    @FXML
    private ImageView ca3IV, cb3IV, cc3IV, cd3IV, ce3IV, cf3IV, cg3IV, ch3IV;
    @FXML
    private ImageView ca2IV, cb2IV, cc2IV, cd2IV, ce2IV, cf2IV, cg2IV, ch2IV;
    @FXML
    private ImageView ca1IV, cb1IV, cc1IV, cd1IV, ce1IV, cf1IV, cg1IV, ch1IV;
    private ImageView[][] cellIVs;
    private Board mainBoard;

    public void initialize() throws FileNotFoundException {
        initIVs();
        String testPos = "rnbqkbnRpppppNpxxxrNxrxxxNNxxxxxNxxxxxxxxxNNNNxxPPPPPPPxRNBQKBNR";
        mainBoard = new Board(testPos);
        //mainBoard = new Board();

        refreshImages();

        Cell testCell = mainBoard.getSpecificCell("d6");
        Piece testPiece = testCell.getPiece();
        System.out.println(testPiece.toString());
        System.out.println(testPiece.getAccessibleCells(mainBoard));
    }

    
    public void refreshImages() throws FileNotFoundException {
        refreshBoardImages();
    }

    private void refreshBoardImages() throws FileNotFoundException {
        for (int i = 0; i <= 7; i++){
            for (int j = 0; j <= 7; j++){
                if(mainBoard.getSpecificCell(getCoordinatesFromIndices(i,j)).getOccupied()){
                    setImage(cellIVs[i][j], mainBoard.getSpecificCell(getCoordinatesFromIndices(i,j)).getPiece().getImageFileName());
                }
            }
        }
    }

    private void initIVs() {
        ArrayList<ImageView> row8IVs = new ArrayList<>(Arrays.asList(ca8IV, cb8IV, cc8IV, cd8IV, ce8IV, cf8IV, cg8IV, ch8IV));
        ArrayList<ImageView> row7IVs = new ArrayList<>(Arrays.asList(ca7IV, cb7IV, cc7IV, cd7IV, ce7IV, cf7IV, cg7IV, ch7IV));
        ArrayList<ImageView> row6IVs = new ArrayList<>(Arrays.asList(ca6IV, cb6IV, cc6IV, cd6IV, ce6IV, cf6IV, cg6IV, ch6IV));
        ArrayList<ImageView> row5IVs = new ArrayList<>(Arrays.asList(ca5IV, cb5IV, cc5IV, cd5IV, ce5IV, cf5IV, cg5IV, ch5IV));
        ArrayList<ImageView> row4IVs = new ArrayList<>(Arrays.asList(ca4IV, cb4IV, cc4IV, cd4IV, ce4IV, cf4IV, cg4IV, ch4IV));
        ArrayList<ImageView> row3IVs = new ArrayList<>(Arrays.asList(ca3IV, cb3IV, cc3IV, cd3IV, ce3IV, cf3IV, cg3IV, ch3IV));
        ArrayList<ImageView> row2IVs = new ArrayList<>(Arrays.asList(ca2IV, cb2IV, cc2IV, cd2IV, ce2IV, cf2IV, cg2IV, ch2IV));
        ArrayList<ImageView> row1IVs = new ArrayList<>(Arrays.asList(ca1IV, cb1IV, cc1IV, cd1IV, ce1IV, cf1IV, cg1IV, ch1IV));

        ArrayList<ArrayList<ImageView>> rowIVs = new ArrayList<>(Arrays.asList(row8IVs, row7IVs, row6IVs, row5IVs, row4IVs, row3IVs, row2IVs, row1IVs));
        cellIVs = new ImageView[8][8];

        for (int i = 0; i <= 7; i++){
            for (int j = 0; j <= 7; j++){
                cellIVs[i][j] = rowIVs.get(i).get(j);
            }
        }
    }

    @FXML
    public void onCellCkicked(MouseEvent mouseEvent) {
        String cellCoord = ((Node) mouseEvent.getSource()).getId().substring(1,3);
        System.out.println(cellCoord);
    }
}
