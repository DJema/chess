package ca.djema.rami.game.chess.controller;

import ca.djema.rami.game.chess.application.ChessGameApplication;
import ca.djema.rami.game.chess.model.ChessGame;
import ca.djema.rami.game.chess.model.Piece;
import ca.djema.rami.game.chess.model.PieceBishop;
import ca.djema.rami.game.chess.model.PieceKing;
import ca.djema.rami.game.chess.model.PieceKnight;
import ca.djema.rami.game.chess.model.PiecePawn;
import ca.djema.rami.game.chess.model.PieceQueen;
import ca.djema.rami.game.chess.model.PieceRook;
import ca.djema.rami.game.chess.model.Player;
import ca.djema.rami.game.chess.model.PlayerBlack;
import ca.djema.rami.game.chess.model.PlayerWhite;


public class ChessGameController {

    public static void initializeGame() {
        ChessGame chessGame = ChessGameApplication.getChessGame();
        chessGame.addPlayer(new PlayerWhite(chessGame));
        chessGame.addPlayer(new PlayerBlack(chessGame));
        
        // Initialize White pieces (row, column, player)
        Piece WhiteRook0 = new PieceRook(0,0,chessGame.getPlayer(0));
        Piece WhiteKnight0 = new PieceKnight(0,1,chessGame.getPlayer(0));
        Piece WhiteBishop0 = new PieceBishop(0,2,chessGame.getPlayer(0));
        Piece WhiteQueen = new PieceQueen(0,3,chessGame.getPlayer(0));
        Piece WhiteKing = new PieceKing(0,4,chessGame.getPlayer(0));
        Piece WhiteBishop1 = new PieceKnight(0,5,chessGame.getPlayer(0));
        Piece WhiteKnight1 = new PieceBishop(0,6,chessGame.getPlayer(0));
        Piece WhiteRook1 = new PieceRook(0,7,chessGame.getPlayer(0));
        Piece WhitePawn0 = new PiecePawn(1,0,chessGame.getPlayer(0));
        Piece WhitePawn1 = new PiecePawn(1,1,chessGame.getPlayer(0));
        Piece WhitePawn2 = new PiecePawn(1,2,chessGame.getPlayer(0));
        Piece WhitePawn3 = new PiecePawn(1,3,chessGame.getPlayer(0));
        Piece WhitePawn4 = new PiecePawn(1,4,chessGame.getPlayer(0));
        Piece WhitePawn5 = new PiecePawn(1,5,chessGame.getPlayer(0));
        Piece WhitePawn6 = new PiecePawn(1,6,chessGame.getPlayer(0));
        Piece WhitePawn7 = new PiecePawn(1,7,chessGame.getPlayer(0));
        
        chessGame.getPlayer(0).addPiece(WhiteRook0);
        chessGame.getPlayer(0).addPiece(WhiteKnight0);
        chessGame.getPlayer(0).addPiece(WhiteBishop0);
        chessGame.getPlayer(0).addPiece(WhiteQueen);
        chessGame.getPlayer(0).addPiece(WhiteKing);
        chessGame.getPlayer(0).addPiece(WhiteBishop1);
        chessGame.getPlayer(0).addPiece(WhiteKnight1);
        chessGame.getPlayer(0).addPiece(WhiteRook1);
        chessGame.getPlayer(0).addPiece(WhitePawn0);
        chessGame.getPlayer(0).addPiece(WhitePawn1);
        chessGame.getPlayer(0).addPiece(WhitePawn2);
        chessGame.getPlayer(0).addPiece(WhitePawn3);
        chessGame.getPlayer(0).addPiece(WhitePawn4);
        chessGame.getPlayer(0).addPiece(WhitePawn5);
        chessGame.getPlayer(0).addPiece(WhitePawn6);
        chessGame.getPlayer(0).addPiece(WhitePawn7);
        
        // Initialize Black pieces (row, column, player)
        Piece BlackRook0 = new PieceRook(7,0,chessGame.getPlayer(1));
        Piece BlackKnight0 = new PieceKnight(7,1,chessGame.getPlayer(1));
        Piece BlackBishop0 = new PieceBishop(7,2,chessGame.getPlayer(1));
        Piece BlackQueen = new PieceQueen(7,3,chessGame.getPlayer(1));
        Piece BlackKing = new PieceKing(7,4,chessGame.getPlayer(1));
        Piece BlackBishop1 = new PieceKnight(7,5,chessGame.getPlayer(1));
        Piece BlackKnight1 = new PieceBishop(7,6,chessGame.getPlayer(1));
        Piece BlackRook1 = new PieceRook(7,7,chessGame.getPlayer(1));
        Piece BlackPawn0 = new PiecePawn(6,0,chessGame.getPlayer(1));
        Piece BlackPawn1 = new PiecePawn(6,1,chessGame.getPlayer(1));
        Piece BlackPawn2 = new PiecePawn(6,2,chessGame.getPlayer(1));
        Piece BlackPawn3 = new PiecePawn(6,3,chessGame.getPlayer(1));
        Piece BlackPawn4 = new PiecePawn(6,4,chessGame.getPlayer(1));
        Piece BlackPawn5 = new PiecePawn(6,5,chessGame.getPlayer(1));
        Piece BlackPawn6 = new PiecePawn(6,6,chessGame.getPlayer(1));
        Piece BlackPawn7 = new PiecePawn(6,7,chessGame.getPlayer(1));
        
        chessGame.getPlayer(1).addPiece(BlackRook0);
        chessGame.getPlayer(1).addPiece(BlackKnight0);
        chessGame.getPlayer(1).addPiece(BlackBishop0);
        chessGame.getPlayer(1).addPiece(BlackQueen);
        chessGame.getPlayer(1).addPiece(BlackKing);
        chessGame.getPlayer(1).addPiece(BlackBishop1);
        chessGame.getPlayer(1).addPiece(BlackKnight1);
        chessGame.getPlayer(1).addPiece(BlackRook1);
        chessGame.getPlayer(1).addPiece(BlackPawn0);
        chessGame.getPlayer(1).addPiece(BlackPawn1);
        chessGame.getPlayer(1).addPiece(BlackPawn2);
        chessGame.getPlayer(1).addPiece(BlackPawn3);
        chessGame.getPlayer(1).addPiece(BlackPawn4);
        chessGame.getPlayer(1).addPiece(BlackPawn5);
        chessGame.getPlayer(1).addPiece(BlackPawn6);
        chessGame.getPlayer(1).addPiece(BlackPawn7);
        
        Piece[][] board = new Piece[8][8];
        for(int i=0; i< 16; i++) {
          int xPosition = chessGame.getPlayer(0).getPiece(i).getXPosition();
          int yPosition = chessGame.getPlayer(0).getPiece(i).getYPosition();
          board[xPosition][yPosition] = chessGame.getPlayer(0).getPiece(i);  
        }
        for(int i=0; i< 16; i++) {
            int xPosition = chessGame.getPlayer(1).getPiece(i).getXPosition();
            int yPosition = chessGame.getPlayer(1).getPiece(i).getYPosition();
            board[xPosition][yPosition] = chessGame.getPlayer(1).getPiece(i); 
        }
        ChessGameApplication.setMovingPlayer(chessGame.getPlayer(0));
        chessGame.getBoard().setBoard(board);
        
    }
    
}
