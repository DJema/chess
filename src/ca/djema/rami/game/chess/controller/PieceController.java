package ca.djema.rami.game.chess.controller;

import java.io.IOException;
import java.util.ArrayList;

import ca.djema.rami.game.chess.application.ChessGameApplication;
import ca.djema.rami.game.chess.model.ChessGame;
import ca.djema.rami.game.chess.model.Piece;
import ca.djema.rami.game.chess.model.PiecePawn;
import ca.djema.rami.game.chess.model.Player;
import ca.djema.rami.game.chess.model.PlayerBlack;
import ca.djema.rami.game.chess.model.PlayerWhite;
import ca.djema.rami.game.chess.view.ChessGameScreen;

public class PieceController {
    
    public static void click(int x, int y) throws IOException {
        
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

    private static void move(int x, int y) throws IOException {
        
        ChessGame chessGame = ChessGameApplication.getChessGame();
        Piece movingPiece = chessGame.getBoard().getBoard()[ChessGameApplication.getSelectedXPosition()][ChessGameApplication.getSelectedYPosition()];
        Player movingPlayer = chessGame.getBoard().getBoard()[ChessGameApplication.getSelectedXPosition()][ChessGameApplication.getSelectedYPosition()].getPlayer();

        System.out.println(movingPiece);
        System.out.println(movingPlayer);
        
        if(movingPiece instanceof PiecePawn && movingPlayer instanceof PlayerWhite) {
            moveWhitePawn(x,y);
        }
        if(movingPiece instanceof PiecePawn && movingPlayer instanceof PlayerBlack) {
            moveBlackPawn(x,y);
        }
              
    }
    
    // Logic for moving a white pawn
    private static void moveWhitePawn(int destinationX, int destinationY) throws IOException {
        
        ChessGame chessGame = ChessGameApplication.getChessGame();
        Piece[][] board = chessGame.getBoard().getBoard();
        int currentX = ChessGameApplication.getSelectedXPosition();
        int currentY = ChessGameApplication.getSelectedYPosition();
        
        Moves validMoves = getValidMoves(board[currentX][currentY], board[currentX][currentY].getPlayer());
      
        for(int i = 0; i < validMoves.getListOfValidMovesX().size(); i++) {
            
            if( validMoves.getListOfValidMovesX().get(i) == destinationX && validMoves.getListOfValidMovesY().get(i) == destinationY) {
                
                board[destinationX][destinationY] = board[currentX][currentY];
                board[currentX][currentY] = null;
                    
                ChessGameApplication.getChessGameScreen().movePawn(currentX, currentY, destinationX, destinationY, "white");
                ChessGameApplication.setMovingPlayer(chessGame.getPlayer(1));
                ChessGameApplication.setPieceSelected(false);
                ChessGameApplication.setSelectedXPosition(-1);
                ChessGameApplication.setSelectedYPosition(-1);
                    
                break;
            }
        } 
    }
    // Logic for moving a black pawn
    private static void moveBlackPawn(int destinationX, int destinationY) throws IOException {
        
        ChessGame chessGame = ChessGameApplication.getChessGame();
        Piece[][] board = chessGame.getBoard().getBoard();
        int currentX = ChessGameApplication.getSelectedXPosition();
        int currentY = ChessGameApplication.getSelectedYPosition();
        
        Moves validMoves = getValidMoves(board[currentX][currentY], board[currentX][currentY].getPlayer());
      
        for(int i = 0; i < validMoves.getListOfValidMovesX().size(); i++) {
            
            if( validMoves.getListOfValidMovesX().get(i) == destinationX && validMoves.getListOfValidMovesY().get(i) == destinationY) {
                
                board[destinationX][destinationY] = board[currentX][currentY];
                board[currentX][currentY] = null;
                    
                ChessGameApplication.getChessGameScreen().movePawn(currentX, currentY, destinationX, destinationY, "black");
                ChessGameApplication.setMovingPlayer(chessGame.getPlayer(0));
                ChessGameApplication.setPieceSelected(false);
                ChessGameApplication.setSelectedXPosition(-1);
                ChessGameApplication.setSelectedYPosition(-1);
                    
                break;
            }
        } 
    }
    
    
    private static Moves getValidMoves(Piece piece, Player player) {
        Moves moves = new Moves();
        ChessGame chessGame = ChessGameApplication.getChessGame();
        Piece[][] board = chessGame.getBoard().getBoard();
        int currentX = ChessGameApplication.getSelectedXPosition();
        int currentY = ChessGameApplication.getSelectedYPosition();
        
        if(piece instanceof PiecePawn && player instanceof PlayerWhite) {
            if(currentX + 1 < 8 && board[currentX + 1][currentY] == null) {
                moves.getListOfValidMovesX().add(currentX + 1);
                moves.getListOfValidMovesY().add(currentY);
            }
            if(currentX + 1 < 8 && currentY + 1 < 8  &&
                    board[currentX + 1][currentY + 1] != null && board[currentX + 1][currentY + 1].getPlayer() instanceof PlayerBlack) {
                moves.getListOfValidMovesX().add(currentX + 1);
                moves.getListOfValidMovesY().add(currentY + 1);
            }
            if(currentX + 1 < 8 && currentY - 1 < 8 && currentY - 1 >= 0 &&
                    board[currentX + 1][currentY - 1] != null && board[currentX + 1][currentY - 1].getPlayer() instanceof PlayerBlack) {
                moves.getListOfValidMovesX().add(currentX + 1);
                moves.getListOfValidMovesY().add(currentY - 1);
            }
            return moves; 
        }
        
        if(piece instanceof PiecePawn && player instanceof PlayerBlack) {
            System.out.println("test");
            if(currentX - 1 > -1 && board[currentX - 1][currentY] == null) {
                moves.getListOfValidMovesX().add(currentX - 1);
                moves.getListOfValidMovesY().add(currentY);
            }
//            if(currentX + 1 < 8 && currentY + 1 < 8  &&
//                    board[currentX + 1][currentY + 1] != null && board[currentX + 1][currentY + 1].getPlayer() instanceof PlayerWhite) {
//                moves.getListOfValidMovesX().add(currentX + 1);
//                moves.getListOfValidMovesY().add(currentY + 1);
//            }
//            if(currentX + 1 < 8 && currentY - 1 < 8 && currentY - 1 >= 0 &&
//                    board[currentX + 1][currentY - 1] != null && board[currentX + 1][currentY - 1].getPlayer() instanceof PlayerWhite) {
//                moves.getListOfValidMovesX().add(currentX + 1);
//                moves.getListOfValidMovesY().add(currentY - 1);
//            }
            return moves; 
        }
        
        return null;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
