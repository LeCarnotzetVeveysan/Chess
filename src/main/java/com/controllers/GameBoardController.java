package com.controllers;

import com.chess.Board;
import com.chess.Cell;
import com.chess.Piece;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;


import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

import static com.utils.ModelUtils.*;

public class GameBoardController {

    @FXML
    public VBox promotionVB;
    @FXML
    public Label gamestateLabel;
    @FXML
    private ImageView ca8IV, cb8IV, cc8IV, cd8IV, ce8IV, cf8IV, cg8IV, ch8IV,
            ca7IV, cb7IV, cc7IV, cd7IV, ce7IV, cf7IV, cg7IV, ch7IV,
            ca6IV, cb6IV, cc6IV, cd6IV, ce6IV, cf6IV, cg6IV, ch6IV,
            ca5IV, cb5IV, cc5IV, cd5IV, ce5IV, cf5IV, cg5IV, ch5IV,
            ca4IV, cb4IV, cc4IV, cd4IV, ce4IV, cf4IV, cg4IV, ch4IV,
            ca3IV, cb3IV, cc3IV, cd3IV, ce3IV, cf3IV, cg3IV, ch3IV,
            ca2IV, cb2IV, cc2IV, cd2IV, ce2IV, cf2IV, cg2IV, ch2IV,
            ca1IV, cb1IV, cc1IV, cd1IV, ce1IV, cf1IV, cg1IV, ch1IV,
            pa8IV, pb8IV, pc8IV, pd8IV, pe8IV, pf8IV, pg8IV, ph8IV,
            pa7IV, pb7IV, pc7IV, pd7IV, pe7IV, pf7IV, pg7IV, ph7IV,
            pa6IV, pb6IV, pc6IV, pd6IV, pe6IV, pf6IV, pg6IV, ph6IV,
            pa5IV, pb5IV, pc5IV, pd5IV, pe5IV, pf5IV, pg5IV, ph5IV,
            pa4IV, pb4IV, pc4IV, pd4IV, pe4IV, pf4IV, pg4IV, ph4IV,
            pa3IV, pb3IV, pc3IV, pd3IV, pe3IV, pf3IV, pg3IV, ph3IV,
            pa2IV, pb2IV, pc2IV, pd2IV, pe2IV, pf2IV, pg2IV, ph2IV,
            pa1IV, pb1IV, pc1IV, pd1IV, pe1IV, pf1IV, pg1IV, ph1IV,
            wCP1IV, wCP2IV, wCP3IV, wCP4IV, wCP5IV, wCP6IV, wCP7IV, wCP8IV,
            wCP9IV, wCP10IV, wCP11IV, wCP12IV, wCP13IV, wCP14IV, wCP15IV, wCP16IV,        
            bCP1IV, bCP2IV, bCP3IV, bCP4IV, bCP5IV, bCP6IV, bCP7IV, bCP8IV,
            bCP9IV, bCP10IV, bCP11IV, bCP12IV, bCP13IV, bCP14IV, bCP15IV, bCP16IV;
    private ImageView[][] cellIVs, pieceIVs;
    private ArrayList<ImageView> wCaptPieceIVs, bCaptPieceIVs;
    private Board mainBoard;

    public void initialize() throws FileNotFoundException {
        initIVs();
        promotionVB.setDisable(true);
        promotionVB.setVisible(false);

        //String testPos = "rnbqkbnRpppppNpxxxrNxrxxxNNxxxxxNxxxxxxxxxNNNNxxPPPPPPPxRNBQKBNR";
        //String testPos = "rnbqkbnRpppppNpxxxrNxrxxxNNxxxxxNxxxxxxxxxNNNNxxPPPPPPPPRxxxKxxR";
        //String testPos = "kxxxxxxxxxxxxxxxxxxxxRxxxxxxxxRxxxxxxxxxxxxxxxxxxxxxxxxxKxxxxxxx";
        //String testPos = "kxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxBxBxxxxxxxxxxxxxxxxKxxxxxxx";
        //mainBoard = new Board(testPos, "Initialization");
        mainBoard = new Board();

        refreshImages();
    }

    public void refreshImages() throws FileNotFoundException {
        cleanImages();
        updatePieceImages();
        updateCellImages();
        updateCapturedPieceImages();
        gamestateLabel.setText(mainBoard.getGameState().name());
        stopIfFinished();
    }

    private void updateCapturedPieceImages() throws FileNotFoundException {
        ArrayList<Piece> whiteCapt = mainBoard.getWhiteCapturedPieces();
        if(whiteCapt.size() >= 1) {
            for (int i = 0; i < whiteCapt.size(); i++) {
                setImage(wCaptPieceIVs.get(i), "sprites/" + whiteCapt.get(i).getImageFileName());
            }
        }
        ArrayList<Piece> blackCapt = mainBoard.getBlackCapturedPieces();
        if(blackCapt.size() >= 1) {
            for (int i = 0; i < blackCapt.size(); i++) {
                setImage(bCaptPieceIVs.get(i), "sprites/" + blackCapt.get(i).getImageFileName());
            }
        }
    }

    public void cleanImages(){
        cleanCellImages();
        cleanPieceImages();
        cleanCapturedPieceImages();
    }

    private void cleanCapturedPieceImages() {
        for (ImageView iv : wCaptPieceIVs){
            iv.setImage(null);
        }
        for (ImageView iv : bCaptPieceIVs){
            iv.setImage(null);
        }
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

    private void updatePieceImages() throws FileNotFoundException {
        for (int i = 0; i <= 7; i++){
            for (int j = 0; j <= 7; j++){
                Cell cell = mainBoard.getSpecificCell(getCoordinatesFromIndices(i,j));
                if(cell.getOccupied()){
                    setImage(pieceIVs[i][j], "sprites/" + cell.getPiece().getImageFileName());
                }
            }
        }
    }

    private void updateCellImages() throws FileNotFoundException {
        updateCheckCells();
    }

    private void updateCheckCells() throws FileNotFoundException {
        if(mainBoard.getIsCheck('w')){
            for (int i = 0; i <= 7; i++){
                for (int j = 0; j <= 7; j++){
                    Cell cell = mainBoard.getSpecificCell(getCoordinatesFromIndices(i,j));
                    if(cell.getOccupied() && cell.getPiece().getAccessibleCells().contains(mainBoard.getKing('w').getCurrentCell())){
                        setImage(cellIVs[i][j], "/cellBlurs/mid_pink");
                    }
                }
            }
        }
        if(mainBoard.getIsCheck('b')){
            for (int i = 0; i <= 7; i++){
                for (int j = 0; j <= 7; j++){
                    Cell cell = mainBoard.getSpecificCell(getCoordinatesFromIndices(i,j));
                    if(cell.getOccupied() && cell.getPiece().getAccessibleCells().contains(mainBoard.getKing('b').getCurrentCell())){
                        setImage(cellIVs[i][j], "/cellBlurs/mid_pink");
                    }
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

        wCaptPieceIVs = new ArrayList<>(Arrays.asList(wCP1IV, wCP2IV, wCP3IV, wCP4IV, wCP5IV, wCP6IV, wCP7IV, wCP8IV));
        wCaptPieceIVs.addAll(Arrays.asList(wCP9IV, wCP10IV, wCP11IV, wCP12IV, wCP13IV, wCP14IV, wCP15IV, wCP16IV));
        bCaptPieceIVs = new ArrayList<>(Arrays.asList(bCP1IV, bCP2IV, bCP3IV, bCP4IV, bCP5IV, bCP6IV, bCP7IV, bCP8IV));
        bCaptPieceIVs.addAll(Arrays.asList(bCP9IV, bCP10IV, bCP11IV, bCP12IV, bCP13IV, bCP14IV, bCP15IV, bCP16IV));
    }

    public void setCellClickableState(boolean inState){
        for (int i = 0; i <= 7; i++){
            for (int j = 0; j <= 7; j++){
                pieceIVs[i][j].setDisable(!inState);
            }
        }
    }

    public void stopIfFinished(){
        if(mainBoard.isGameFinished()){
            setCellClickableState(false);
        }
    }

    @FXML
    public void onCellCkicked(MouseEvent mouseEvent) throws FileNotFoundException {
        String cellCoord = ((Node) mouseEvent.getSource()).getId().substring(1,3);
        Cell clickedCell = mainBoard.getSpecificCell(cellCoord);

        if(clickedCell.getValidMove()){
            Piece piece = mainBoard.getActivePiece();
            boolean differentColumns = clickedCell.getColumnIndex() != piece.getColumnIndex();
            boolean emptyEndCell = !clickedCell.getOccupied();
            boolean castling = cellCoord.equals("g1") || cellCoord.equals("c1") || cellCoord.equals("g8") || cellCoord.equals("c8");
            if(piece.getType(false) == 'K' && castling){
                switch (cellCoord){
                    case "g1" -> mainBoard.castleKingside('w');
                    case "c1" -> mainBoard.castleQueenside('w');
                    case "g8" -> mainBoard.castleKingside('b');
                    case "c8" -> mainBoard.castleQueenside('b');
                }
            } else if(piece.getType(false) == 'P' && differentColumns && emptyEndCell){
                mainBoard.enPassant(piece.getCurrentCell(), cellCoord);
            } else {
                mainBoard.movePiece(true, true, false, true, piece.getCurrentCell(), cellCoord);
            }

            if(mainBoard.getPromotionRequired()){
                promotionVB.setDisable(false);
                promotionVB.setVisible(true);
                setCellClickableState(false);
            }
            refreshImages();
            stopIfFinished();
        } else {
            if (clickedCell.getOccupied() && clickedCell.getPiece().getColor() == mainBoard.getCurrentPlayer()) {

                cleanCellImages();
                updateCheckCells();
                mainBoard.setActivePiece(clickedCell.getPiece());

                for (String coord : mainBoard.getActivePiece().getAccessibleCells()) {
                    int[] coords = getIndicesFromCoordinates(coord);
                    Cell cell = mainBoard.getSpecificCell(coord);

                    if (cell.getOccupied()) {
                        setImage(cellIVs[coords[0]][coords[1]], "/cellBlurs/mid_red");
                    } else {
                        setImage(cellIVs[coords[0]][coords[1]], "/cellBlurs/mid_blue");
                    }
                }
            }
        }
    }

    @FXML
    public void promotePawn(char desiredPiece) throws FileNotFoundException {
        Piece activePiece = mainBoard.getActivePiece();
        char promotedPiece = activePiece.getColor() == 'w' ? desiredPiece : Character.toLowerCase(desiredPiece);
        mainBoard.promotePawn(activePiece.getCurrentCell(), promotedPiece);
        promotionVB.setDisable(true);
        promotionVB.setVisible(false);
        setCellClickableState(true);
        stopIfFinished();
        refreshImages();
    }

    @FXML
    public void onQueenPromotionClick() throws FileNotFoundException {
        promotePawn('Q');
    }

    @FXML
    public void onBishopPromotionClick() throws FileNotFoundException {
        promotePawn('B');
    }

    @FXML
    public void onKnightPromotionClick() throws FileNotFoundException {
        promotePawn('N');
    }

    @FXML
    public void onRookPromotionClick() throws FileNotFoundException {
        promotePawn('R');
    }
}
