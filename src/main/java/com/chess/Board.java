package com.chess;

import java.util.ArrayList;

import static com.chess.GameState.*;
import static com.utils.ModelUtils.getCoordinatesFromIndices;
import static com.utils.ModelUtils.getIndicesFromCoordinates;

public class Board {

    private GameState gameState = ONGOING;
    private Cell[][] cells;
    private Piece whiteKing, blackKing;
    private ArrayList<Piece> whiteCapturedPieces, blackCapturedPieces;
    private Piece activePiece;
    private char currentPlayer;
    private boolean promotionRequired = false;
    private ArrayList<String> moves;

    public Board(){
        initializeBoard();
        initializePieces();
        calculateValidMoves();
    }

    public Board(String gameState){

        initializeBoard();

        for(int i = 0; i < 64; i++){
            Piece piece = null;
            switch (gameState.charAt(i)){
                case 'K' -> {
                    piece = new King('w', getCoordinatesFromIndices(i/8, i%8));
                    if(i/8 != 7 && i%8 != 4){
                        piece.setCurrentCell(getCoordinatesFromIndices(i/8, i % 8));
                    }
                }
                case 'k' -> {
                    piece = new King('b', getCoordinatesFromIndices(i/8, i%8));
                    if(i/8 != 0 && i%8 != 4){
                        piece.setCurrentCell(getCoordinatesFromIndices(i/8, i % 8));
                    }
                }
                case 'Q' -> piece = new Queen('w', getCoordinatesFromIndices(i/8, i%8));
                case 'q' -> piece = new Queen('b', getCoordinatesFromIndices(i/8, i%8));
                case 'B' -> piece = new Bishop('w', getCoordinatesFromIndices(i/8, i%8));
                case 'b' -> piece = new Bishop('b', getCoordinatesFromIndices(i/8, i%8));
                case 'N' -> piece = new Knight('w', getCoordinatesFromIndices(i/8, i%8));
                case 'n' -> piece = new Knight('b', getCoordinatesFromIndices(i/8, i%8));
                case 'R' -> piece = new Rook('w', getCoordinatesFromIndices(i/8, i%8));
                case 'r' -> piece = new Rook('b', getCoordinatesFromIndices(i/8, i%8));
                case 'P' -> {
                    piece = new Pawn('w', getCoordinatesFromIndices(i / 8, i % 8));
                    if(i/8 != 6){
                        piece.setCurrentCell(getCoordinatesFromIndices(i/8, i % 8));
                    }
                }
                case 'p' -> {
                    piece = new Pawn('b', getCoordinatesFromIndices(i/8, i%8));
                    if(i/8 != 1){
                        piece.setCurrentCell(getCoordinatesFromIndices(i/8, i % 8));
                    }
                }

            }
            cells[i/8][i%8].setPiece(piece);
        }


        int whiteKingIndex = gameState.indexOf('K');
        whiteKing = cells[whiteKingIndex/8][whiteKingIndex%8].getPiece();
        int blackKingIndex = gameState.indexOf('k');
        blackKing = cells[blackKingIndex/8][blackKingIndex%8].getPiece();
        calculateLineOfSight();
    }

    public String toStringSquare(){
        String toReturn = "";
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                toReturn += cells[i][j].getCoordinates();
            }
            toReturn += "\n";
        }
        return toReturn;
    }

    public String getBoardPosition(){
        String position = "";
        for(int i = 0; i <= 7; i++){
            for(int j = 0; j <= 7; j++){
                if(cells[i][j].getOccupied()) {
                    position += cells[i][j].getPiece().getType(true);
                } else {
                    position += 'x';
                }
            }
        }
        return position;
    }

    public void initializeBoard(){
        cells = new Cell[8][8];
        for(int i = 0; i <= 7; i++){
            for(int j = 0; j <= 7; j++){
                cells[i][j] = new Cell(getCoordinatesFromIndices(i,j));
            }
        }
        whiteCapturedPieces = new ArrayList<>();
        blackCapturedPieces = new ArrayList<>();
        currentPlayer = 'w';
        moves = new ArrayList<>();
    }

    private void initializePieces() {
        //Kings
        Piece firstKing = new King('w', "e1");
        Piece secondKing = new King('b', "e8");
        cells[7][4].setPiece(firstKing);
        whiteKing = firstKing;
        cells[0][4].setPiece(secondKing);
        blackKing = secondKing;
        //Queens
        cells[7][3].setPiece(new Queen('w', "d1"));
        cells[0][3].setPiece(new Queen('b', "d8"));
        //Bishops
        cells[7][2].setPiece(new Bishop('w', "c1"));
        cells[7][5].setPiece(new Bishop('w', "f1"));
        cells[0][2].setPiece(new Bishop('b', "c8"));
        cells[0][5].setPiece(new Bishop('b', "f8"));
        //Knights
        cells[7][1].setPiece(new Knight('w', "b1"));
        cells[7][6].setPiece(new Knight('w', "g1"));
        cells[0][1].setPiece(new Knight('b', "b8"));
        cells[0][6].setPiece(new Knight('b', "g8"));
        //Rooks
        cells[7][0].setPiece(new Rook('w', "a1"));
        cells[7][7].setPiece(new Rook('w', "h1"));
        cells[0][0].setPiece(new Rook('b', "a8"));
        cells[0][7].setPiece(new Rook('b', "h8"));
        //Pawns
        for (int i = 0; i < 8; i++){
            cells[6][i].setPiece(new Pawn('w', getCoordinatesFromIndices(6,i)));
            cells[1][i].setPiece(new Pawn('b', getCoordinatesFromIndices(1,i)));
        }

        calculateValidMoves();
    }

    public void calculateValidMoves() {
        for(int i = 0; i <= 7; i++){
            for(int j = 0; j <= 7; j++){
                if(cells[i][j].getOccupied()) {
                    cells[i][j].getPiece().calculateAccessibleCells(true, this);
                }
            }
        }
    }

    public void calculateLineOfSight() {
        for(int i = 0; i <= 7; i++){
            for(int j = 0; j <= 7; j++){
                if(cells[i][j].getOccupied()) {
                    cells[i][j].getPiece().calculateAccessibleCells(false, this);
                }
            }
        }
    }

    public Cell getSpecificCell(String inCoordinates){
        int[] coordinates = getIndicesFromCoordinates(inCoordinates);
        return cells[coordinates[0]][coordinates[1]];
    }

    public Piece getActivePiece(){
        return activePiece;
    }

    public void setActivePiece(Piece inPiece){
        activePiece = inPiece;
        setValidMoves();
    }
    public void movePiece(boolean realMove, String inStartCell, String inEndCell){
        char moveType = '-';
        Cell startCell = getSpecificCell(inStartCell);
        Cell endCell = getSpecificCell(inEndCell);
        if(endCell.getOccupied()){
            if(endCell.getPiece().getColor() == 'w'){
                whiteCapturedPieces.add(endCell.getPiece());
            } else {
                blackCapturedPieces.add(endCell.getPiece());
            }
            endCell.setPiece(null);
            moveType = 'x';
        }
        endCell.setPiece(startCell.getPiece());
        endCell.getPiece().setCurrentCell(inEndCell);
        startCell.setPiece(null);

        if(realMove) {
            calculatePromotionRequired();
            updateMoves();
            registerMove(endCell.getPiece(), moveType);
            nextPlayer();
            calculateGameState();
        } else {
            calculateLineOfSight();
        }
    }

    private void registerMove(Piece inPiece, char inMoveType) {
        String move = moves.size() + 1 + ". ";
        move += inPiece.getType(false);
        move += inPiece.getPreviousCell();
        move += inMoveType;
        move += inPiece.getCurrentCell();

        if(getIsCheck(getOtherPlayer())){
            move += "+";
        }

        moves.add(move);
        System.out.println(moves.get(moves.size() - 1));
    }

    public void updateMoves(){
        calculateValidMoves();
        resetValidMoves();
    }

    public char getCurrentPlayer(){
        return currentPlayer;
    }

    public char getOtherPlayer(){
        return getOppositeColor(currentPlayer);
    }

    public char getOppositeColor(char inColor){
        if(inColor == 'w'){
            return 'b';
        } else {
            return 'w';
        }
    }

    public void nextPlayer(){
        if(currentPlayer == 'w'){
            currentPlayer = 'b';
        } else {
            currentPlayer = 'w';
        }
    }

    public void resetValidMoves(){
        for(int i = 0; i <= 7; i++){
            for(int j = 0; j <= 7; j++){
                cells[i][j].setValidMove(false);
            }
        }
    }

    public void setValidMoves(){
        for (String coord : activePiece.getAccessibleCells()){
            int[] intCoord = getIndicesFromCoordinates(coord);
            cells[intCoord[0]][intCoord[1]].setValidMove(true);
        }
    }

    public Piece getKing(char inColor){
        if(inColor == 'w'){
            return whiteKing;
        } else {
            return blackKing;
        }
    }

    public boolean getIsCheck(char inColor){
        if(inColor == 'w'){
            return isKingInLineOfSight(whiteKing);
        } else {
            return isKingInLineOfSight(blackKing);
        }
    }

    private boolean isKingInLineOfSight(Piece inKing) {
        for (int i = 0; i <= 7; i++) {
            for (int j = 0; j <= 7; j++) {
                Cell currentCell = getSpecificCell(getCoordinatesFromIndices(i, j));
                if(currentCell.getOccupied()) {
                    Piece currentPiece = currentCell.getPiece();
                    if (currentPiece.getColor() != inKing.getColor()) {
                        if (currentPiece.getAccessibleCells().contains(inKing.getCurrentCell())) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public void promotePawn(String inCell, char inTypeColor) {
        setPromotionRequired(false);
        Cell currentCell = getSpecificCell(inCell);
        switch (inTypeColor) {
            case 'Q' -> currentCell.setPiece(new Queen('w', inCell));
            case 'q' -> currentCell.setPiece(new Queen('b', inCell));
            case 'B' -> currentCell.setPiece(new Bishop('w', inCell));
            case 'b' -> currentCell.setPiece(new Bishop('b', inCell));
            case 'N' -> currentCell.setPiece(new Knight('w', inCell));
            case 'n' -> currentCell.setPiece(new Knight('b', inCell));
            case 'R' -> currentCell.setPiece(new Rook('w', inCell));
            case 'r' -> currentCell.setPiece(new Rook('r', inCell));
        }
        updateMoves();
    }

    public void calculatePromotionRequired(){
        for(int i = 0; i <= 7; i++){
            Cell currentCell = getSpecificCell(getCoordinatesFromIndices(0,i));
            if(currentCell.getOccupied()){
                if(currentCell.getPiece().getType(true) == 'P'){
                    setPromotionRequired(true);
                }
            }
            currentCell = getSpecificCell(getCoordinatesFromIndices(7,i));
            if (currentCell.getOccupied()) {
                if (currentCell.getPiece().getType(true) == 'p') {
                    setPromotionRequired(true);
                }
            }
        }
    }

    public boolean getPromotionRequired() {
        return promotionRequired;
    }

    public void setPromotionRequired(boolean inState){
        promotionRequired = inState;
    }
    
    public void calculateGameState(){
        calculateCheckmate();
        calculateStalemate();
        calculateDraw();
    }

    private int numberOfAvailableMoves(){
        int count = 0;
        for (int i = 0; i <= 7; i++) {
            for (int j = 0; j <= 7; j++) {
                Cell currentCell = getSpecificCell(getCoordinatesFromIndices(i, j));
                if(currentCell.getOccupied()) {
                    if(currentCell.getPiece().getColor() == currentPlayer){
                        count += currentCell.getPiece().getAccessibleCells().size();
                    }
                }
            }
        }
        return count;
    }

    private void calculateDraw() {

    }

    private void calculateStalemate() {
        if(currentPlayer == 'w'){
            if(numberOfAvailableMoves() == 0 && !getIsCheck('w')){
                gameState = STALEMATE_WHITE;
            }
        } else {
            if(numberOfAvailableMoves() == 0 && !getIsCheck('b')){
                gameState = STALEMATE_BLACK;
            }
        }

    }

    private void calculateCheckmate() {
        if(currentPlayer == 'w'){
            if(numberOfAvailableMoves() == 0 && getIsCheck('w')){
                gameState = CHECKMATE_WHITE;
            }
        } else {
            if(numberOfAvailableMoves() == 0 && getIsCheck('b')){
                gameState = CHECKMATE_BLACK;
            }
        }
    }

    public ArrayList<Piece> getBlackCapturedPieces() {
        return blackCapturedPieces;
    }

    public ArrayList<Piece> getWhiteCapturedPieces() {
        return whiteCapturedPieces;
    }

    public boolean isCellInEnemyLineOfSight(char enemyColor, String inCell){
        for (int i = 0; i <= 7; i++) {
            for (int j = 0; j <= 7; j++) {
                Cell currentCell = getSpecificCell(getCoordinatesFromIndices(i, j));
                if(currentCell.getOccupied()) {
                    Piece currentPiece = currentCell.getPiece();
                    if (currentPiece.getColor() == enemyColor) {
                        if (currentPiece.getAccessibleCells().contains(inCell)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean getCanCastleKingside(char color){
        boolean kingMoved = getKing(currentPlayer).getMoved();
        Cell rookCell = getSpecificCell(color == 'w' ? "h1" : "h8");
        if(rookCell.getOccupied() && rookCell.getPiece().getType(false) == 'R'){
            boolean rookMoved = rookCell.getPiece().getMoved();
            if(!kingMoved && !rookMoved){
                String[] cellsToCheck = color == 'w' ? new String[]{"f1","g1"} : new String[]{"f8","g8"};
                for(String c : cellsToCheck){
                    if(isCellInEnemyLineOfSight(getOppositeColor(color),c)){
                        return false;
                    }
                    if(getSpecificCell(c).getOccupied()){
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    public boolean getCanCastleQueenside(char color) {
        boolean kingMoved = getKing(currentPlayer).getMoved();
        Cell rookCell = getSpecificCell(color == 'w' ? "a1" : "a8");
        if(rookCell.getOccupied() && rookCell.getPiece().getType(false) == 'R'){
            boolean rookMoved = rookCell.getPiece().getMoved();
            if(!kingMoved && !rookMoved){
                String[] cellsToCheck = color == 'w' ? new String[]{"b1","c1","d1"} : new String[]{"b8","c8","d8"};
                for(String c : cellsToCheck){
                    if(isCellInEnemyLineOfSight(getOppositeColor(color),c)){
                        return false;
                    }
                    if(getSpecificCell(c).getOccupied()){
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    public void castleKingside(char color){
        if(color == 'w'){
            movePiece(true, "e1","g1");
            movePiece(true, "h1", "f1");
        } else {
            movePiece(true, "e8","g8");
            movePiece(true, "h8", "f8");
        }

    }

    public void castleQueenside(char color){
        if(color == 'w'){
            movePiece(true, "e1","c1");
            movePiece(true, "a1", "d1");
        } else {
            movePiece(true, "e8","c8");
            movePiece(true, "a8", "d8");
        }
    }
}
