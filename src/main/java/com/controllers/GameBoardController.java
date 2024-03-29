package com.controllers;

import com.chess.Board;
import com.chess.Cell;
import com.chess.Piece;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static com.utils.LoadScene.changeLauncherScene;
import static com.utils.ModelUtils.*;

public class GameBoardController {

    @FXML
    private AnchorPane mainPane;
    @FXML
    private GridPane boardGP;
    @FXML
    public VBox promotionVB;
    @FXML
    public Label gamestateLabel, drawOfferLabel;
    @FXML
    public Button nextPlayerBT, resignBT;
    @FXML
    public Button acceptDrawBT, offerStandardDrawBT, claimFiftyMoveDrawBT, claimThreefoldDrawBT;
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
    private boolean rotateBoard;

    public void initialize() throws IOException {
        initIVs();
        promotionVB.setDisable(true);
        promotionVB.setVisible(false);

        //String testPos = "rnbqkbnRpppppNpxxxrNxrxxxNNxxxxxNxxxxxxxxxNNNNxxPPPPPPPxRNBQKBNR";
        //String testPos = "rnbqkbnRpppppNpxxxrNxrxxxNNxxxxxNxxxxxxxxxNNNNxxPPPPPPPPRxxxKxxR";
        //String testPos = "kxxxxxxxxxxxxxxxxxxxxRxxxxxxxxRxxxxxxxxxxxxxxxxxxxxxxxxxKxxxxxxx";
        //String testPos = "kxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxPxxxxxxxxxxxxxxxxxxKxxxxxxx";
        //mainBoard = new Board(testPos, "Initialization");
        mainBoard = new Board();

        refreshScene();
        nextPlayerBT.setDisable(true);
        rotateBoard = true;
    }

    public void refreshScene() throws IOException {
        stopIfFinished();
        drawOffer();
        stopIfFinished();
        gamestateLabel.setText(mainBoard.getGameState().name());
        cleanImages();
        updatePieceImages();
        updateCellImages();
        updateCapturedPieceImages();
    }

    private void drawOffer() {
        claimThreefoldDrawBT.setVisible(false);
        claimThreefoldDrawBT.setDisable(true);
        claimFiftyMoveDrawBT.setVisible(false);
        claimFiftyMoveDrawBT.setDisable(true);

        if(mainBoard.getCanClaimThreefoldDraw()){
            claimThreefoldDrawBT.setVisible(true);
            claimThreefoldDrawBT.setDisable(false);
        }
        if(mainBoard.getCanClaimFiftyMoveDraw()){
            claimFiftyMoveDrawBT.setVisible(true);
            claimFiftyMoveDrawBT.setDisable(false);
        }
    }

    public void claimFiftyMoveDraw(){
        mainBoard.claimFiftyMoveDrawOffer();
    }

    public void claimThreefoldDraw(){
        mainBoard.claimThreefoldDrawOffer();
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
                Cell cell = mainBoard.getSpecificCell(indicesToCoordinates(i,j));
                if(cell.getOccupied()){
                    setImage(pieceIVs[i][j], "sprites/" + cell.getPiece().getImageFileName());
                }
            }
        }
    }

    private void updateCellImages() throws FileNotFoundException {
        updateCheckCells();
        updateCheckMateCells();
    }

    private void updateCheckMateCells() {
    }

    private void updateCheckCells() throws FileNotFoundException {
        if(mainBoard.getIsCheck('w')){
            for (int i = 0; i <= 7; i++){
                for (int j = 0; j <= 7; j++){
                    Cell cell = mainBoard.getSpecificCell(indicesToCoordinates(i,j));
                    if(cell.getOccupied() && cell.getPiece().getAccessibleCells().contains(mainBoard.getKing('w').getCurrentCell())){
                        setImage(cellIVs[i][j], "/cellBlurs/mid_pink");
                    }
                }
            }
        }
        if(mainBoard.getIsCheck('b')){
            for (int i = 0; i <= 7; i++){
                for (int j = 0; j <= 7; j++){
                    Cell cell = mainBoard.getSpecificCell(indicesToCoordinates(i,j));
                    if(cell.getOccupied() && cell.getPiece().getAccessibleCells().contains(mainBoard.getKing('b').getCurrentCell())){
                        setImage(cellIVs[i][j], "/cellBlurs/mid_pink");
                    }
                }
            }
        }
    }

    public void rotateBoardAndPieceImages() throws InterruptedException {
        int angle = (int) boardGP.getRotate() == 0 ? 180 : 0;
        boardGP.setRotate(angle);
        for (int i = 0; i <= 7; i++){
            for (int j = 0; j <= 7; j++){
                pieceIVs[i][j].setRotate(angle);
            }
        }
    }

    public void setCellClickableState(boolean inState){
        for (int i = 0; i <= 7; i++){
            for (int j = 0; j <= 7; j++){
                pieceIVs[i][j].setDisable(!inState);
            }
        }
    }

    public void stopIfFinished() throws IOException {
        if(mainBoard.isGameFinished()){
            setCellClickableState(false);
            mainPane.setDisable(true);
            changeLauncherScene("results");
        }
    }

    @FXML
    public void onCellCkicked(MouseEvent mouseEvent) throws IOException, InterruptedException {
        String cellCoord = ((Node) mouseEvent.getSource()).getId().substring(1,3);
        Cell clickedCell = mainBoard.getSpecificCell(cellCoord);
        if(canMove()) {
            if (clickedCell.getValidMove()) {
                moveSequence(cellCoord, clickedCell);
            } else if (clickedCell.getOccupied() && pieceIsPlayersColor(clickedCell)) {
                showAvailableMovesSequence(clickedCell);
            }
        }
    }

    public boolean pieceIsPlayersColor(Cell clickedCell){
        return clickedCell.getPiece().getColor() == mainBoard.getCurrentPlayer();
    }

    private void showAvailableMovesSequence(Cell clickedCell) throws FileNotFoundException {
        cleanCellImages();
        updateCheckCells();
        mainBoard.setActivePiece(clickedCell.getPiece());

        for (String coord : mainBoard.getActivePiece().getAccessibleCells()) {
            int[] coords = coordinatesToIndices(coord);
            Cell cell = mainBoard.getSpecificCell(coord);

            if (cell.getOccupied()) {
                setImage(cellIVs[coords[0]][coords[1]], "/cellBlurs/mid_red");
            } else {
                setImage(cellIVs[coords[0]][coords[1]], "/cellBlurs/mid_blue");
            }
        }
    }

    private void moveSequence(String cellCoord, Cell clickedCell) throws IOException, InterruptedException {
        Piece piece = mainBoard.getActivePiece();
        boolean differentColumns = clickedCell.getColumnIndex() != piece.getColumnIndex();
        boolean emptyEndCell = !clickedCell.getOccupied();
        boolean castling = cellCoord.equals("g1") || cellCoord.equals("c1") || cellCoord.equals("g8") || cellCoord.equals("c8");
        boolean hasMoved = piece.getMoved();
        if(piece.getType(false) == 'K' && castling && !hasMoved){
            switch (cellCoord){
                case "g1" -> mainBoard.castleKingside('w');
                case "c1" -> mainBoard.castleQueenside('w');
                case "g8" -> mainBoard.castleKingside('b');
                case "c8" -> mainBoard.castleQueenside('b');
            }
        } else if(piece.getType(false) == 'P' && differentColumns && emptyEndCell){
            mainBoard.enPassant(piece.getCurrentCell(), cellCoord);
        } else {
            mainBoard.movePiece(true, true, false, piece.getCurrentCell(), cellCoord);
        }

        if(mainBoard.getPromotionRequired()){
            promotionVB.setDisable(false);
            promotionVB.setVisible(true);
            setCellClickableState(false);
        } else {
            switchPlayerIfAuto();
        }

        refreshScene();
    }

    private void switchPlayerIfAuto() throws InterruptedException {
        if(mainBoard.getAutoSwitchPlayer()){
            mainBoard.nextPlayer();
            rotateBoardAndPieceImages();
        } else {
            nextPlayerBT.setDisable(false);
        }
    }

    @FXML
    public void promotePawn(char desiredPiece) throws IOException, InterruptedException {
        Piece activePiece = mainBoard.getActivePiece();
        char promotedPiece = activePiece.getColor() == 'w' ? desiredPiece : Character.toLowerCase(desiredPiece);
        mainBoard.promotePawn(activePiece.getCurrentCell(), promotedPiece);
        promotionVB.setDisable(true);
        promotionVB.setVisible(false);
        setCellClickableState(true);
        switchPlayerIfAuto();
        refreshScene();
    }

    @FXML
    public void onQueenPromotionClick() throws IOException, InterruptedException {
        promotePawn('Q');
    }

    @FXML
    public void onBishopPromotionClick() throws IOException, InterruptedException {
        promotePawn('B');
    }

    @FXML
    public void onKnightPromotionClick() throws IOException, InterruptedException {
        promotePawn('N');
    }

    @FXML
    public void onRookPromotionClick() throws IOException, InterruptedException {
        promotePawn('R');
    }

    public void onNextPlayerClick() throws InterruptedException {
        mainBoard.nextPlayer();
        nextPlayerBT.setDisable(true);
        if(rotateBoard) {
            rotateBoardAndPieceImages();
        }
    }

    public boolean canMove(){
        return nextPlayerBT.isDisabled();
    }

    public void onResignButtonClick() throws IOException {
        mainBoard.resign(mainBoard.getCurrentPlayer());
        refreshScene();
    }

    public void onAcceptDrawClick(MouseEvent mouseEvent) {
    }

    public void onStandardDrawClick(MouseEvent mouseEvent) {
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

}
