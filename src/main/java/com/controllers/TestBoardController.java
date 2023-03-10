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
    @FXML
    private ImageView pa8IV, pb8IV, pc8IV, pd8IV, pe8IV, pf8IV, pg8IV, ph8IV;
    @FXML
    private ImageView pa7IV, pb7IV, pc7IV, pd7IV, pe7IV, pf7IV, pg7IV, ph7IV;
    @FXML
    private ImageView pa6IV, pb6IV, pc6IV, pd6IV, pe6IV, pf6IV, pg6IV, ph6IV;
    @FXML
    private ImageView pa5IV, pb5IV, pc5IV, pd5IV, pe5IV, pf5IV, pg5IV, ph5IV;
    @FXML
    private ImageView pa4IV, pb4IV, pc4IV, pd4IV, pe4IV, pf4IV, pg4IV, ph4IV;
    @FXML
    private ImageView pa3IV, pb3IV, pc3IV, pd3IV, pe3IV, pf3IV, pg3IV, ph3IV;
    @FXML
    private ImageView pa2IV, pb2IV, pc2IV, pd2IV, pe2IV, pf2IV, pg2IV, ph2IV;
    @FXML
    private ImageView pa1IV, pb1IV, pc1IV, pd1IV, pe1IV, pf1IV, pg1IV, ph1IV;
    private ImageView[][] cellIVs, pieceIVs;
    private Board mainBoard;

    public void initialize() throws FileNotFoundException {
        initIVs();
        String testPos = "rnbqkbnRpppppNpxxxrNxrxxxNNxxxxxNxxxxxxxxxNNNNxxPPPPPPPxRNBQKBNR";
        mainBoard = new Board(testPos);
        //mainBoard = new Board();

        refreshImages();

    }

    public void refreshImages() throws FileNotFoundException {
        cleanImages();
        refreshBoardImages();
    }

    public void cleanImages(){
        cleanCellImages();
        cleanPieceImages();
    }

    private void cleanCellImages() {
        for (int i = 0; i <= 7; i++){
            for (int j = 0; j <= 7; j++){
                cellIVs[i][j].setImage(null);
            }
        }
    }

    private void cleanPieceImages() {
        for (int i = 0; i <= 7; i++){
            for (int j = 0; j <= 7; j++){
                pieceIVs[i][j].setImage(null);
            }
        }
    }

    private void refreshBoardImages() throws FileNotFoundException {
        for (int i = 0; i <= 7; i++){
            for (int j = 0; j <= 7; j++){
                Cell cell = mainBoard.getSpecificCell(getCoordinatesFromIndices(i,j));
                if(cell.getOccupied()){
                    setImage(pieceIVs[i][j], "sprites/" + cell.getPiece().getImageFileName());
                }
            }
        }
    }

    private void initIVs() {
        ArrayList<ImageView> r8CIVs = new ArrayList<>(Arrays.asList(ca8IV, cb8IV, cc8IV, cd8IV, ce8IV, cf8IV, cg8IV, ch8IV));
        ArrayList<ImageView> r7CIVs = new ArrayList<>(Arrays.asList(ca7IV, cb7IV, cc7IV, cd7IV, ce7IV, cf7IV, cg7IV, ch7IV));
        ArrayList<ImageView> r6CIVs = new ArrayList<>(Arrays.asList(ca6IV, cb6IV, cc6IV, cd6IV, ce6IV, cf6IV, cg6IV, ch6IV));
        ArrayList<ImageView> r5CIVs = new ArrayList<>(Arrays.asList(ca5IV, cb5IV, cc5IV, cd5IV, ce5IV, cf5IV, cg5IV, ch5IV));
        ArrayList<ImageView> r4CIVs = new ArrayList<>(Arrays.asList(ca4IV, cb4IV, cc4IV, cd4IV, ce4IV, cf4IV, cg4IV, ch4IV));
        ArrayList<ImageView> r3CIVs = new ArrayList<>(Arrays.asList(ca3IV, cb3IV, cc3IV, cd3IV, ce3IV, cf3IV, cg3IV, ch3IV));
        ArrayList<ImageView> r2CIVs = new ArrayList<>(Arrays.asList(ca2IV, cb2IV, cc2IV, cd2IV, ce2IV, cf2IV, cg2IV, ch2IV));
        ArrayList<ImageView> r1CIVs = new ArrayList<>(Arrays.asList(ca1IV, cb1IV, cc1IV, cd1IV, ce1IV, cf1IV, cg1IV, ch1IV));

        ArrayList<ImageView> r8PIVs = new ArrayList<>(Arrays.asList(pa8IV, pb8IV, pc8IV, pd8IV, pe8IV, pf8IV, pg8IV, ph8IV));
        ArrayList<ImageView> r7PIVs = new ArrayList<>(Arrays.asList(pa7IV, pb7IV, pc7IV, pd7IV, pe7IV, pf7IV, pg7IV, ph7IV));
        ArrayList<ImageView> r6PIVs = new ArrayList<>(Arrays.asList(pa6IV, pb6IV, pc6IV, pd6IV, pe6IV, pf6IV, pg6IV, ph6IV));
        ArrayList<ImageView> r5PIVs = new ArrayList<>(Arrays.asList(pa5IV, pb5IV, pc5IV, pd5IV, pe5IV, pf5IV, pg5IV, ph5IV));
        ArrayList<ImageView> r4PIVs = new ArrayList<>(Arrays.asList(pa4IV, pb4IV, pc4IV, pd4IV, pe4IV, pf4IV, pg4IV, ph4IV));
        ArrayList<ImageView> r3PIVs = new ArrayList<>(Arrays.asList(pa3IV, pb3IV, pc3IV, pd3IV, pe3IV, pf3IV, pg3IV, ph3IV));
        ArrayList<ImageView> r2PIVs = new ArrayList<>(Arrays.asList(pa2IV, pb2IV, pc2IV, pd2IV, pe2IV, pf2IV, pg2IV, ph2IV));
        ArrayList<ImageView> r1PIVs = new ArrayList<>(Arrays.asList(pa1IV, pb1IV, pc1IV, pd1IV, pe1IV, pf1IV, pg1IV, ph1IV));

        ArrayList<ArrayList<ImageView>> rCIVs = new ArrayList<>(Arrays.asList(r8CIVs, r7CIVs, r6CIVs, r5CIVs, r4CIVs, r3CIVs, r2CIVs, r1CIVs));
        ArrayList<ArrayList<ImageView>> rPIVs = new ArrayList<>(Arrays.asList(r8PIVs, r7PIVs, r6PIVs, r5PIVs, r4PIVs, r3PIVs, r2PIVs, r1PIVs));
        cellIVs = new ImageView[8][8];
        pieceIVs = new ImageView[8][8];

        for (int i = 0; i <= 7; i++){
            for (int j = 0; j <= 7; j++){
                cellIVs[i][j] = rCIVs.get(i).get(j);
                pieceIVs[i][j] = rPIVs.get(i).get(j);
            }
        }
    }

    @FXML
    public void onCellCkicked(MouseEvent mouseEvent) throws FileNotFoundException {
        String cellCoord = ((Node) mouseEvent.getSource()).getId().substring(1,3);
        Cell clickedCell = mainBoard.getSpecificCell(cellCoord);

        if(clickedCell.getValidMove()){
            mainBoard.movePiece(mainBoard.getActivePiece().getCurrentCell(), cellCoord);
            refreshImages();
        } else {

            if (clickedCell.getOccupied()) {

                cleanCellImages();
                Piece clickedPiece = clickedCell.getPiece();

                for (String coord : clickedPiece.getAccessibleCells(mainBoard)) {
                    int[] coords = getIndicesFromCoordinates(coord);
                    Cell cell = mainBoard.getSpecificCell(coord);
                    cell.setValidMove(true);
                    if (cell.getOccupied()) {
                        setImage(cellIVs[coords[0]][coords[1]], "/cellBlurs/mid_red");
                    } else {
                        setImage(cellIVs[coords[0]][coords[1]], "/cellBlurs/mid_blue");
                    }
                }

            }
        }
    }
}
