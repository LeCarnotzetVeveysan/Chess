package com.chess;

import java.util.*;

import static com.chess.GameState.*;
import static com.utils.ModelUtils.*;

public class Board {

    private GameState gameState = ONGOING;
    private Cell[][] cells;
    private Piece whiteKing, blackKing;
    private ArrayList<Piece> whiteCapturedPieces, blackCapturedPieces;
    private Piece activePiece;
    private char currentPlayer;
    private boolean promotionRequired = false, canClaimFiftyMoveDraw = false, canClaimThreefoldDraw = false;
    private ArrayList<String> moves, positions;

    public Board(){
        initializeBoard();
        initializePieces();
        calculateValidMoves();
        registerPosition();
        calculateGameState();
    }

    public Board(String gameState, String lastMove){

        initializeBoard();

        for(int i = 0; i < 64; i++){
            Piece piece = null;
            switch (gameState.charAt(i)){
                case 'K' -> {
                    piece = new King('w', indicesToCoordinates(i/8, i%8));
                    if(i/8 != 7 && i%8 != 4){
                        piece.setCurrentCell(indicesToCoordinates(i/8, i % 8));
                    }
                }
                case 'k' -> {
                    piece = new King('b', indicesToCoordinates(i/8, i%8));
                    if(i/8 != 0 && i%8 != 4){
                        piece.setCurrentCell(indicesToCoordinates(i/8, i % 8));
                    }
                }
                case 'Q' -> piece = new Queen('w', indicesToCoordinates(i/8, i%8));
                case 'q' -> piece = new Queen('b', indicesToCoordinates(i/8, i%8));
                case 'B' -> piece = new Bishop('w', indicesToCoordinates(i/8, i%8));
                case 'b' -> piece = new Bishop('b', indicesToCoordinates(i/8, i%8));
                case 'N' -> piece = new Knight('w', indicesToCoordinates(i/8, i%8));
                case 'n' -> piece = new Knight('b', indicesToCoordinates(i/8, i%8));
                case 'R' -> piece = new Rook('w', indicesToCoordinates(i/8, i%8));
                case 'r' -> piece = new Rook('b', indicesToCoordinates(i/8, i%8));
                case 'P' -> {
                    piece = new Pawn('w', indicesToCoordinates(i / 8, i % 8));
                    if(i/8 != 6){
                        piece.setCurrentCell(indicesToCoordinates(i/8, i % 8));
                    }
                }
                case 'p' -> {
                    piece = new Pawn('b', indicesToCoordinates(i/8, i%8));
                    if(i/8 != 1){
                        piece.setCurrentCell(indicesToCoordinates(i/8, i % 8));
                    }
                }

            }
            cells[i/8][i%8].setPiece(piece);
        }

        int whiteKingIndex = gameState.indexOf('K');
        whiteKing = cells[whiteKingIndex/8][whiteKingIndex%8].getPiece();
        int blackKingIndex = gameState.indexOf('k');
        blackKing = cells[blackKingIndex/8][blackKingIndex%8].getPiece();

        moves.add(lastMove);
        registerPosition();
        calculateLineOfSight();
        calculateGameState();
    }

    public boolean isGameFinished(){
        return !gameState.equals(ONGOING);
    }
    public GameState getGameState(){
        return gameState;
    }
    public boolean getCanClaimFiftyMoveDraw(){
        return canClaimFiftyMoveDraw;
    }

    public void claimFiftyMoveDrawOffer(){
        gameState = FIFTY_MOVE_DRAW_CLAIMED;
    }

    public boolean getCanClaimThreefoldDraw(){
        return canClaimFiftyMoveDraw;
    }

    public void claimThreefoldDrawOffer(){
        gameState = THREEFOLD_REPETITION_DRAW_CLAIMED;
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

    public void registerPosition(){
        positions.add(getBoardPosition());
    }

    public void initializeBoard(){
        cells = new Cell[8][8];
        for(int i = 0; i <= 7; i++){
            for(int j = 0; j <= 7; j++){
                cells[i][j] = new Cell(indicesToCoordinates(i,j));
            }
        }
        whiteCapturedPieces = new ArrayList<>();
        blackCapturedPieces = new ArrayList<>();
        currentPlayer = 'w';
        moves = new ArrayList<>(Arrays.asList("Initialization"));
        positions = new ArrayList<>();
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
            cells[6][i].setPiece(new Pawn('w', indicesToCoordinates(6,i)));
            cells[1][i].setPiece(new Pawn('b', indicesToCoordinates(1,i)));
        }

        calculateValidMoves();
    }

    public ArrayList<Piece> remainingPieces(char color){
        ArrayList<Piece> pieces = new ArrayList<>();
        for(int i = 0; i <= 7; i++){
            for(int j = 0; j <= 7; j++){
                if(cells[i][j].getOccupied()){
                    if(cells[i][j].getPiece().getColor() == color){
                        pieces.add(cells[i][j].getPiece());
                    }
                }
            }
        }
        return pieces;
    }

    public String remainingPieceTypes(char color){
        String types = "";
        for(Piece p : remainingPieces(color)){
            types += p.getType(false);
        }
        return types;
    }

    public int numberOfPieces(char color){
        return remainingPieces(color).size();
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
        int[] coordinates = coordinatesToIndices(inCoordinates);
        return cells[coordinates[0]][coordinates[1]];
    }

    public Piece getActivePiece(){
        return activePiece;
    }

    public void setActivePiece(Piece inPiece){
        activePiece = inPiece;
        setValidMoves();
    }
    public void movePiece(boolean realMove, boolean register, boolean enPassant, boolean changePlayer, String inStartCell, String inEndCell){
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
            registerMove(register, endCell.getPiece(), moveType, enPassant);
            updateMoves();
            calculateGameState();
            if(changePlayer){
                nextPlayer();
            }
        } else {
            calculateLineOfSight();
        }
    }

    private void registerMove(boolean register, Piece inPiece, char inMoveType, boolean enPassant) {
        if (register){
            String move = "";
            move += inPiece.getType(false);
            move += inPiece.getPreviousCell();
            move += inMoveType;
            move += inPiece.getCurrentCell();

            if(enPassant){
                move += "e.p";
            }

            if(isCheckmate(getOtherPlayer())){
                move += "#";
            } else if (getIsCheck(getOtherPlayer())) {
                move += "+";
            }

            moves.add(move);
            registerPosition();
        }
    }

    public String getLastMove(){
        return moves.get(moves.size() - 1);
    }

    public void registerCastle(boolean kingside){
        String move = "";
        if(kingside){
            move += "0-0";
        } else {
            move += "0-0-0";
        }

        if(isCheckmate(getOtherPlayer())){
            move += "#";
        } else if (getIsCheck(getOtherPlayer())) {
            move += "+";
        }

        moves.add(move);
        registerPosition();
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
            int[] intCoord = coordinatesToIndices(coord);
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
                Cell currentCell = getSpecificCell(indicesToCoordinates(i, j));
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
        calculateGameState();
    }

    public void calculatePromotionRequired(){
        for(int i = 0; i <= 7; i++){
            Cell currentCell = getSpecificCell(indicesToCoordinates(0,i));
            if(currentCell.getOccupied()){
                if(currentCell.getPiece().getType(true) == 'P'){
                    setPromotionRequired(true);
                }
            }
            currentCell = getSpecificCell(indicesToCoordinates(7,i));
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
        if(!(gameState == ONGOING)){
            System.out.println("Game ended");
        }
    }

    private int numberOfAvailableMoves(char color){
        int count = 0;
        for (int i = 0; i <= 7; i++) {
            for (int j = 0; j <= 7; j++) {
                Cell currentCell = getSpecificCell(indicesToCoordinates(i, j));
                if(currentCell.getOccupied()) {
                    if(currentCell.getPiece().getColor() == color){
                        count += currentCell.getPiece().getAccessibleCells().size();
                    }
                }
            }
        }
        return count;
    }

    private void calculateDraw() {
        if((insufficientMaterial('w') && insufficientMaterial('b'))){
            gameState = INSUFFICIENT_MATERIAL;
        } else if (deadPosition()) {
            gameState = DEAD_POSITION;
        } else if(nMovesWithoutPawnMoveOrCapture(75)){
            gameState = SEVENTY_FIVE_MOVE_RULE_DRAW;
        } else if (nFoldPosition(5)) {
            gameState = FIVEFOLD_REPETITION_RULE_DRAW;
        } else if (nMovesWithoutPawnMoveOrCapture(50)) {
            canClaimFiftyMoveDraw = true;
        } else if (nFoldPosition(3)) {
            canClaimThreefoldDraw = true;
        }
    }

    private boolean nFoldPosition(int limit) {
        ArrayList<String> whiteToPlayPositions = new ArrayList<>();
        ArrayList<String> blackToPlayPositions = new ArrayList<>();
        for(int i = 1; i < positions.size(); i += 2){
            whiteToPlayPositions.add(positions.get(i));
        }
        for(int i = 2; i < positions.size(); i += 2){
            blackToPlayPositions.add(positions.get(i));
        }
        Map<String, Integer> whitePositions = new HashMap<>();
        Map<String, Integer> blackPositions = new HashMap<>();
        for (String str : whiteToPlayPositions) {
            if (whitePositions.containsKey(str)) {
                whitePositions.put(str, whitePositions.get(str) + 1);
            } else {
                whitePositions.put(str, 1);
            }
        }
        for (String str : blackToPlayPositions) {
            if (blackPositions.containsKey(str)) {
                blackPositions.put(str, blackPositions.get(str) + 1);
            } else {
                blackPositions.put(str, 1);
            }
        }
        return whitePositions.containsValue(limit) || blackPositions.containsValue(limit);
    }

    private boolean nMovesWithoutPawnMoveOrCapture(int limit){
        if(moves.size() >= 2*limit){
            boolean hasPawnMoveOrCapture = false;
            for(int i = moves.size() - 1; i > moves.size() - (2*limit); i--){
                if (moves.get(i).contains("P") || moves.get(i).contains("x")) {
                    hasPawnMoveOrCapture = true;
                    break;
                }
            }
            return !hasPawnMoveOrCapture;
        }
        return false;
    }
    private boolean deadPosition() {
        //Really complicated but maybe avoid doing it
        return false;
    }

    public boolean insufficientMaterial(char color){
        ArrayList<String> simpleTypes = new ArrayList<>(Arrays.asList("K","BK", "KB", "KN", "NK"));
        ArrayList<String> twoBishops = new ArrayList<>(Arrays.asList("KBB", "BKB", "BBK"));
        String remainingPieceTypes = remainingPieceTypes(color);
        if(simpleTypes.contains(remainingPieceTypes)){
            return true;
        } else if (twoBishops.contains(remainingPieceTypes)) {
            char colors = 'x';
            for(Piece p : remainingPieces(color)){
                char cellColor = getCellColor(p.getCurrentCell());
                if(colors == 'x' && p.getType(false) == 'B'){
                    colors = cellColor;
                } else if(p.getType(false) == 'B' && cellColor == colors) {
                    return true;
                }
            }
        }
        return false;
    }

    private void calculateStalemate() {
        if(isStalemate('w')){
            gameState = STALEMATE_WHITE;
        } else if(isStalemate('b')){
            gameState = STALEMATE_BLACK;
        }
    }

    private void calculateCheckmate() {
        if(isCheckmate('w')){
            gameState = CHECKMATE_WHITE;
        } else if(isCheckmate('b')){
            gameState = CHECKMATE_BLACK;
        }
    }

    public boolean isStalemate(char color){
        return numberOfAvailableMoves(color) == 0 && !getIsCheck(color);
    }

    public boolean isCheckmate(char color){
        return numberOfAvailableMoves(color) == 0 && getIsCheck(color);
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
                Cell currentCell = getSpecificCell(indicesToCoordinates(i, j));
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
            movePiece(true, false, false, false,"e1","g1");
            movePiece(true, false, false, false, "h1", "f1");
        } else {
            movePiece(true, false, false, false, "e8","g8");
            movePiece(true, false, false, false, "h8", "f8");
        }
        registerCastle(true);
        nextPlayer();
    }

    public void castleQueenside(char color){
        if(color == 'w'){
            movePiece(true, false, false, false, "e1","c1");
            movePiece(true, false, false, false, "a1", "d1");
        } else {
            movePiece(true, false, false, false, "e8","c8");
            movePiece(true, false, false, false, "a8", "d8");
        }
        registerCastle(false);
        nextPlayer();
    }

    public void enPassant(String startCell, String endcell){
        movePiece(true, false, true, true, startCell, endcell);
        Cell captureCell = cells[coordinatesToIndices(startCell)[0]][coordinatesToIndices(endcell)[1]];
        Piece capturedPiece = captureCell.getPiece();
        if(capturedPiece.getColor() == 'w'){
            whiteCapturedPieces.add(capturedPiece);
        } else {
            blackCapturedPieces.add(capturedPiece);
        }
        captureCell.setPiece(null);
    }

    public ArrayList<String> getMoves(){
        return moves;
    }
}
