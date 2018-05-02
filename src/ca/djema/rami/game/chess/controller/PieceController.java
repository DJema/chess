package ca.djema.rami.game.chess.controller;

import java.io.IOException;
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

public class PieceController {

    public static void click(int x, int y) throws IOException {

        ChessGame chessGame = ChessGameApplication.getChessGame();
        Piece[][] board = chessGame.getBoard().getBoard();

        // No piece is selected, then select one of your pieces
        if (ChessGameApplication.isPieceSelected() == false) {

            if (board[x][y] != null && board[x][y].getPlayer().equals(ChessGameApplication.getMovingPlayer())) {
                ChessGameApplication.setSelectedXPosition(x);
                ChessGameApplication.setSelectedYPosition(y);
                ChessGameApplication.setPieceSelected(true);
            }

        }

        // Select another one of your own pieces
        else if (ChessGameApplication.isPieceSelected() == true && board[x][y] != null
                && board[x][y].getPlayer().equals(ChessGameApplication.getMovingPlayer())) {

            ChessGameApplication.setSelectedXPosition(x);
            ChessGameApplication.setSelectedYPosition(y);

        }

        // If a piece is selected and you are not selecting another one of your
        // pieces, try to move to the clicked position
        else if (ChessGameApplication.isPieceSelected() == true) {
            move(x, y);
        }
    }

    private static void move(int destinationX, int destinationY) throws IOException {

        ChessGame chessGame = ChessGameApplication.getChessGame();
        Piece[][] board = chessGame.getBoard().getBoard();
        int currentX = ChessGameApplication.getSelectedXPosition();
        int currentY = ChessGameApplication.getSelectedYPosition();
        Player movingPlayer = board[currentX][currentY].getPlayer();
        Piece movingPiece = board[currentX][currentY];

        Moves validMoves = getValidMoves(movingPiece, movingPlayer);

        for (int i = 0; i < validMoves.getListOfValidMovesX().size(); i++) {

            if (validMoves.getListOfValidMovesX().get(i) == destinationX
                    && validMoves.getListOfValidMovesY().get(i) == destinationY) {

                board[destinationX][destinationY] = board[currentX][currentY];
                board[currentX][currentY] = null;

                if (movingPlayer instanceof PlayerWhite) {

                    if (movingPiece instanceof PiecePawn) {
                        ChessGameApplication.getChessGameScreen().movePawn(currentX, currentY, destinationX,
                                destinationY, "white");
                    } else if (movingPiece instanceof PieceRook) {
                        ChessGameApplication.getChessGameScreen().moveRook(currentX, currentY, destinationX,
                                destinationY, "white");
                    } else if (movingPiece instanceof PieceKnight) {
                        ChessGameApplication.getChessGameScreen().moveKnight(currentX, currentY, destinationX,
                                destinationY, "white");
                    } else if (movingPiece instanceof PieceBishop) {
                        ChessGameApplication.getChessGameScreen().moveBishop(currentX, currentY, destinationX,
                                destinationY, "white");
                    } else if (movingPiece instanceof PieceQueen) {
                        ChessGameApplication.getChessGameScreen().moveQueen(currentX, currentY, destinationX,
                                destinationY, "white");
                    } else if (movingPiece instanceof PieceKing) {
                        ChessGameApplication.getChessGameScreen().moveKing(currentX, currentY, destinationX,
                                destinationY, "white");
                    }

                    ChessGameApplication.setMovingPlayer(chessGame.getPlayer(1));

                } else {

                    if (movingPiece instanceof PiecePawn) {
                        ChessGameApplication.getChessGameScreen().movePawn(currentX, currentY, destinationX,
                                destinationY, "black");
                    } else if (movingPiece instanceof PieceRook) {
                        ChessGameApplication.getChessGameScreen().moveRook(currentX, currentY, destinationX,
                                destinationY, "black");
                    } else if (movingPiece instanceof PieceKnight) {
                        ChessGameApplication.getChessGameScreen().moveKnight(currentX, currentY, destinationX,
                                destinationY, "black");
                    } else if (movingPiece instanceof PieceBishop) {
                        ChessGameApplication.getChessGameScreen().moveBishop(currentX, currentY, destinationX,
                                destinationY, "black");
                    } else if (movingPiece instanceof PieceQueen) {
                        ChessGameApplication.getChessGameScreen().moveQueen(currentX, currentY, destinationX,
                                destinationY, "black");
                    } else if (movingPiece instanceof PieceKing) {
                        ChessGameApplication.getChessGameScreen().moveKing(currentX, currentY, destinationX,
                                destinationY, "black");
                    }

                    ChessGameApplication.setMovingPlayer(chessGame.getPlayer(0));
                }

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

        if (piece instanceof PiecePawn && player instanceof PlayerWhite) {
            if (currentX + 1 < 8 && board[currentX + 1][currentY] == null) {
                moves.getListOfValidMovesX().add(currentX + 1);
                moves.getListOfValidMovesY().add(currentY);
            }
            if (currentX + 1 < 8 && currentY + 1 < 8 && board[currentX + 1][currentY + 1] != null
                    && board[currentX + 1][currentY + 1].getPlayer() instanceof PlayerBlack) {
                moves.getListOfValidMovesX().add(currentX + 1);
                moves.getListOfValidMovesY().add(currentY + 1);
            }
            if (currentX + 1 < 8 && currentY - 1 < 8 && currentY - 1 >= 0 && board[currentX + 1][currentY - 1] != null
                    && board[currentX + 1][currentY - 1].getPlayer() instanceof PlayerBlack) {
                moves.getListOfValidMovesX().add(currentX + 1);
                moves.getListOfValidMovesY().add(currentY - 1);
            }
            if (currentX == 1 && board[currentX + 2][currentY] == null) {
                moves.getListOfValidMovesX().add(currentX + 2);
                moves.getListOfValidMovesY().add(currentY);
            }
        }

        if (piece instanceof PiecePawn && player instanceof PlayerBlack) {
            if (currentX - 1 > -1 && board[currentX - 1][currentY] == null) {
                moves.getListOfValidMovesX().add(currentX - 1);
                moves.getListOfValidMovesY().add(currentY);
            }
            if (currentX - 1 > -1 && currentY + 1 < 8 && board[currentX - 1][currentY + 1] != null
                    && board[currentX - 1][currentY + 1].getPlayer() instanceof PlayerWhite) {
                moves.getListOfValidMovesX().add(currentX - 1);
                moves.getListOfValidMovesY().add(currentY + 1);
            }
            if (currentX - 1 > -1 && currentY - 1 > -1 && currentY - 1 >= 0 && board[currentX - 1][currentY - 1] != null
                    && board[currentX - 1][currentY - 1].getPlayer() instanceof PlayerWhite) {
                moves.getListOfValidMovesX().add(currentX - 1);
                moves.getListOfValidMovesY().add(currentY - 1);
            }
            if (currentX == 6 && board[currentX - 2][currentY] == null) {
                moves.getListOfValidMovesX().add(currentX - 2);
                moves.getListOfValidMovesY().add(currentY);
            }

        }

        if (piece instanceof PieceRook) {

            for (int i = currentX + 1; i < 8; i++) {
                if (board[i][currentY] == null) {
                    moves.getListOfValidMovesX().add(i);
                    moves.getListOfValidMovesY().add(currentY);
                } else if (board[i][currentY] != null && board[i][currentY].getPlayer() != player) {
                    moves.getListOfValidMovesX().add(i);
                    moves.getListOfValidMovesY().add(currentY);
                    break;
                } else {
                    break;
                }
            }

            for (int i = currentX - 1; i >= 0; i--) {
                if (board[i][currentY] == null) {
                    moves.getListOfValidMovesX().add(i);
                    moves.getListOfValidMovesY().add(currentY);
                } else if (board[i][currentY] != null && board[i][currentY].getPlayer() != player) {
                    moves.getListOfValidMovesX().add(i);
                    moves.getListOfValidMovesY().add(currentY);
                    break;
                } else {
                    break;
                }
            }

            for (int i = currentY + 1; i < 8; i++) {
                if (board[currentX][i] == null) {
                    moves.getListOfValidMovesX().add(currentX);
                    moves.getListOfValidMovesY().add(i);
                } else if (board[currentX][i] != null && board[currentX][i].getPlayer() != player) {
                    moves.getListOfValidMovesX().add(currentX);
                    moves.getListOfValidMovesY().add(i);
                    break;
                } else {
                    break;
                }
            }

            for (int i = currentY - 1; i >= 0; i--) {
                if (board[currentX][i] == null) {
                    moves.getListOfValidMovesX().add(currentX);
                    moves.getListOfValidMovesY().add(i);
                } else if (board[currentX][i] != null && board[currentX][i].getPlayer() != player) {
                    moves.getListOfValidMovesX().add(currentX);
                    moves.getListOfValidMovesY().add(i);
                    break;
                } else {
                    break;
                }
            }

            return moves;
        }

        if (piece instanceof PieceBishop) {

            for (int i = currentX + 1; i < 8; i++) {
                int j = currentY + (i - currentX);
                if (j >= 8) {
                    break;
                }
                if (board[i][j] == null) {
                    moves.getListOfValidMovesX().add(i);
                    moves.getListOfValidMovesY().add(j);
                } else if (board[i][j] != null && board[i][j].getPlayer() != player) {
                    moves.getListOfValidMovesX().add(i);
                    moves.getListOfValidMovesY().add(j);
                    break;
                } else {
                    break;
                }
            }

            for (int i = currentX + 1; i < 8; i++) {
                int j = currentY - (i - currentX);
                if (j < 0) {
                    break;
                }
                if (board[i][j] == null) {
                    moves.getListOfValidMovesX().add(i);
                    moves.getListOfValidMovesY().add(j);
                } else if (board[i][j] != null && board[i][j].getPlayer() != player) {
                    moves.getListOfValidMovesX().add(i);
                    moves.getListOfValidMovesY().add(j);
                    break;
                } else {
                    break;
                }
            }

            for (int i = currentX - 1; i >= 0; i--) {
                int j = currentY + (currentX - i);
                if (j >= 8) {
                    break;
                }
                if (board[i][j] == null) {
                    moves.getListOfValidMovesX().add(i);
                    moves.getListOfValidMovesY().add(j);
                } else if (board[i][j] != null && board[i][j].getPlayer() != player) {
                    moves.getListOfValidMovesX().add(i);
                    moves.getListOfValidMovesY().add(j);
                    break;
                } else {
                    break;
                }
            }

            for (int i = currentX - 1; i >= 0; i--) {
                int j = currentY - (currentX - i);
                if (j < 0) {
                    break;
                }
                if (board[i][j] == null) {
                    moves.getListOfValidMovesX().add(i);
                    moves.getListOfValidMovesY().add(j);
                } else if (board[i][j] != null && board[i][j].getPlayer() != player) {
                    moves.getListOfValidMovesX().add(i);
                    moves.getListOfValidMovesY().add(j);
                    break;
                } else {
                    break;
                }
            }

        }

        if (piece instanceof PieceKnight) {

            // Try all 8 possible moves for the knight
            if (currentX + 2 < 8 && currentY + 1 < 8 && ((board[currentX + 2][currentY + 1] == null)
                    || board[currentX + 2][currentY + 1].getPlayer() != player)) {
                moves.getListOfValidMovesX().add(currentX + 2);
                moves.getListOfValidMovesY().add(currentY + 1);
            }
            if (currentX + 2 < 8 && currentY - 1 >= 0 && ((board[currentX + 2][currentY - 1] == null)
                    || board[currentX + 2][currentY - 1].getPlayer() != player)) {
                moves.getListOfValidMovesX().add(currentX + 2);
                moves.getListOfValidMovesY().add(currentY - 1);
            }
            if (currentX - 2 >= 0 && currentY + 1 < 8 && ((board[currentX - 2][currentY + 1] == null)
                    || board[currentX - 2][currentY + 1].getPlayer() != player)) {
                moves.getListOfValidMovesX().add(currentX - 2);
                moves.getListOfValidMovesY().add(currentY + 1);
            }
            if (currentX - 2 >= 0 && currentY - 1 >= 0 && ((board[currentX - 2][currentY - 1] == null)
                    || board[currentX - 2][currentY - 1].getPlayer() != player)) {
                moves.getListOfValidMovesX().add(currentX - 2);
                moves.getListOfValidMovesY().add(currentY - 1);
            }
            if (currentX + 1 < 8 && currentY + 2 < 8 && ((board[currentX + 1][currentY + 2] == null)
                    || board[currentX + 1][currentY + 2].getPlayer() != player)) {
                moves.getListOfValidMovesX().add(currentX + 1);
                moves.getListOfValidMovesY().add(currentY + 2);
            }
            if (currentX - 1 >= 0 && currentY + 2 < 8 && ((board[currentX - 1][currentY + 2] == null)
                    || board[currentX - 1][currentY + 2].getPlayer() != player)) {
                moves.getListOfValidMovesX().add(currentX - 1);
                moves.getListOfValidMovesY().add(currentY + 2);
            }
            if (currentX + 1 < 8 && currentY - 2 >= 0 && ((board[currentX + 1][currentY - 2] == null)
                    || board[currentX + 1][currentY - 2].getPlayer() != player)) {
                moves.getListOfValidMovesX().add(currentX + 1);
                moves.getListOfValidMovesY().add(currentY - 2);
            }
            if (currentX - 1 >= 0 && currentY - 2 >= 0 && ((board[currentX - 1][currentY - 2] == null)
                    || board[currentX - 1][currentY - 2].getPlayer() != player)) {
                moves.getListOfValidMovesX().add(currentX - 1);
                moves.getListOfValidMovesY().add(currentY - 2);
            }

        }

        if (piece instanceof PieceQueen) {

            // Add a combination of the moves a bishop and rook have using a dummy rook and
            // bishop by recursively building it with the validMoves function.
            moves.getListOfValidMovesX().addAll(
                    getValidMoves(new PieceRook(-1, -1, new Player(new ChessGame())), player).getListOfValidMovesX());
            moves.getListOfValidMovesY().addAll(
                    getValidMoves(new PieceRook(-1, -1, new Player(new ChessGame())), player).getListOfValidMovesY());

            moves.getListOfValidMovesX().addAll(
                    getValidMoves(new PieceBishop(-1, -1, new Player(new ChessGame())), player).getListOfValidMovesX());
            moves.getListOfValidMovesY().addAll(
                    getValidMoves(new PieceBishop(-1, -1, new Player(new ChessGame())), player).getListOfValidMovesY());

        }

        if (piece instanceof PieceKing) {
            // Try all 8 possible moves for the king
            if (currentX + 1 < 8 && currentY + 1 < 8 && ((board[currentX + 1][currentY + 1] == null)
                    || board[currentX + 1][currentY + 1].getPlayer() != player)) {
                moves.getListOfValidMovesX().add(currentX + 1);
                moves.getListOfValidMovesY().add(currentY + 1);
            }
            if (currentX + 1 < 8 && ((board[currentX + 1][currentY] == null)
                    || board[currentX + 1][currentY].getPlayer() != player)) {
                moves.getListOfValidMovesX().add(currentX + 1);
                moves.getListOfValidMovesY().add(currentY);
            }
            if (currentX + 1 < 8 && currentY - 1 >= 0 && ((board[currentX + 1][currentY - 1] == null)
                    || board[currentX + 1][currentY - 1].getPlayer() != player)) {
                moves.getListOfValidMovesX().add(currentX + 1);
                moves.getListOfValidMovesY().add(currentY - 1);
            }
            if (currentX - 1 >= 0 && currentY + 1 < 8 && ((board[currentX - 1][currentY + 1] == null)
                    || board[currentX - 1][currentY + 1].getPlayer() != player)) {
                moves.getListOfValidMovesX().add(currentX - 1);
                moves.getListOfValidMovesY().add(currentY + 1);
            }
            if (currentX - 1 >= 0 && ((board[currentX - 1][currentY] == null)
                    || board[currentX - 1][currentY].getPlayer() != player)) {
                moves.getListOfValidMovesX().add(currentX - 1);
                moves.getListOfValidMovesY().add(currentY);
            }
            if (currentX - 1 >= 0 && currentY - 1 >= 0 && ((board[currentX - 1][currentY - 1] == null)
                    || board[currentX - 1][currentY - 1].getPlayer() != player)) {
                moves.getListOfValidMovesX().add(currentX - 1);
                moves.getListOfValidMovesY().add(currentY - 1);
            }
            if (currentY - 1 >= 0 && ((board[currentX][currentY - 1] == null)
                    || board[currentX][currentY - 1].getPlayer() != player)) {
                moves.getListOfValidMovesX().add(currentX);
                moves.getListOfValidMovesY().add(currentY - 1);
            }
            if (currentY + 1 < 8 && ((board[currentX][currentY + 1] == null)
                    || board[currentX][currentY + 1].getPlayer() != player)) {
                moves.getListOfValidMovesX().add(currentX);
                moves.getListOfValidMovesY().add(currentY + 1);
            }
        }

        return moves;
    }

}
