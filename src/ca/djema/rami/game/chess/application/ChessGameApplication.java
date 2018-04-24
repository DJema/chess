package ca.djema.rami.game.chess.application;

import java.io.IOException;

import ca.djema.rami.game.chess.controller.ChessGameController;
import ca.djema.rami.game.chess.model.ChessGame;
import ca.djema.rami.game.chess.model.Player;
import ca.djema.rami.game.chess.view.ChessGameScreen;

public class ChessGameApplication {
    
    private static ChessGame chessGame;
    private static int selectedXPosition = -1;
    private static int selectedYPosition = -1;
    private static Player movingPlayer;
    private static ChessGameScreen ChessGameScreen;
    
    
    private static boolean isPieceSelected = false;

    public static void main(String[] args) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                chessGame = new ChessGame();
                ChessGameController.initializeGame();
                try {
                    ChessGameScreen = new ChessGameScreen();
                    ChessGameScreen.setVisible(true);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }
    
    public static ChessGame getChessGame() {
        return chessGame;
    }
    
    public static int getSelectedXPosition() {
        return selectedXPosition;
    }

    public static void setSelectedXPosition(int selectedXPosition) {
        ChessGameApplication.selectedXPosition = selectedXPosition;
    }

    public static int getSelectedYPosition() {
        return selectedYPosition;
    }

    public static void setSelectedYPosition(int selectedYPosition) {
        ChessGameApplication.selectedYPosition = selectedYPosition;
    }

    public static boolean isPieceSelected() {
        return isPieceSelected;
    }

    public static void setPieceSelected(boolean isPieceSelected) {
        ChessGameApplication.isPieceSelected = isPieceSelected;
    }

    public static Player getMovingPlayer() {
        return movingPlayer;
    }

    public static void setMovingPlayer(Player movingPlayer) {
        ChessGameApplication.movingPlayer = movingPlayer;
    }

    public static ChessGameScreen getChessGameScreen() {
        return ChessGameScreen;
    }

    public static void setChessGameScreen(ChessGameScreen chessGameScreen) {
        ChessGameScreen = chessGameScreen;
    }
    
}
