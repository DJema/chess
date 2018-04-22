package ca.djema.rami.game.chess.controller;

import ca.djema.rami.game.chess.application.ChessGameApplication;
import ca.djema.rami.game.chess.model.ChessGame;
import ca.djema.rami.game.chess.model.Piece;

public class PieceController {
    
    public static void click(int x, int y) {
        
        ChessGame chessGame = ChessGameApplication.getChessGame();
        Piece[][] board = chessGame.getBoard().getBoard();
        
        // No piece is selected, then select one of your pieces
        if(ChessGameApplication.isPieceSelected() == false) {
            
            if(board[x][y]!= null && board[x][y].getPlayer().equals(ChessGameApplication.getMovingPlayer())) {
                ChessGameApplication.setSelectedXPosition(x);
                ChessGameApplication.setSelectedYPosition(y);
                ChessGameApplication.setPieceSelected(true);
            }
            
        }
        
        // Select another one of your own pieces
        else if(ChessGameApplication.isPieceSelected() == true && board[x][y]!= null 
                && board[x][y].getPlayer().equals(ChessGameApplication.getMovingPlayer())) {
            
            ChessGameApplication.setSelectedXPosition(x);
            ChessGameApplication.setSelectedYPosition(y);
            
        }
        
        // If a piece is selected and you are not selecting another one of your pieces, try to move to the clicked position
        else if (ChessGameApplication.isPieceSelected() == true) {
            move(x, y);
        }
    }

    public static void move(int x, int y) {
        
    }
}
