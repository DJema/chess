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

        // Check if the move is a castle
        if (isCastle(destinationX, destinationY, currentX, currentY, board, movingPlayer)) {
            if (movingPlayer instanceof PlayerWhite) {
                ChessGameApplication.setMovingPlayer(chessGame.getPlayer(1));
            } else {
                ChessGameApplication.setMovingPlayer(chessGame.getPlayer(0));
            }
            ChessGameApplication.setPieceSelected(false);
            ChessGameApplication.setSelectedXPosition(-1);
            ChessGameApplication.setSelectedYPosition(-1);
            return;
        }

        Moves validMoves = getValidMoves(movingPiece, movingPlayer, ChessGameApplication.getSelectedXPosition(),
                ChessGameApplication.getSelectedYPosition(), chessGame.getBoard().getBoard());

        for (int i = 0; i < validMoves.getListOfValidMovesX().size(); i++) {

            if (validMoves.getListOfValidMovesX().get(i) == destinationX
                    && validMoves.getListOfValidMovesY().get(i) == destinationY) {

                if (!isInCheckAfterMove(destinationX, destinationY, movingPlayer)) {

                    board[destinationX][destinationY] = board[currentX][currentY];
                    board[currentX][currentY] = null;

                    if (movingPlayer instanceof PlayerWhite) {

                        if (movingPiece instanceof PiecePawn) {
                            ChessGameApplication.getChessGameScreen().movePawn(currentX, currentY, destinationX,
                                    destinationY, "white");
                        } else if (movingPiece instanceof PieceRook) {
                            ((PieceRook) movingPiece).setHasMoved(true);
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
                            ((PieceKing) movingPiece).setHasMoved(true);
                            ChessGameApplication.getChessGameScreen().moveKing(currentX, currentY, destinationX,
                                    destinationY, "white");
                        }

                        ChessGameApplication.setMovingPlayer(chessGame.getPlayer(1));

                    } else {

                        if (movingPiece instanceof PiecePawn) {
                            ChessGameApplication.getChessGameScreen().movePawn(currentX, currentY, destinationX,
                                    destinationY, "black");
                        } else if (movingPiece instanceof PieceRook) {
                            ((PieceRook) movingPiece).setHasMoved(true);
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
                            ((PieceKing) movingPiece).setHasMoved(true);
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

    }

    private static boolean isCastle(int destinationX, int destinationY, int currentX, int currentY, Piece[][] board,
            Player movingPlayer) throws IOException {
        if ((board[0][4] instanceof PieceKing) && (board[0][0] instanceof PieceRook)
                && !((PieceKing) board[0][4]).isHasMoved() && !((PieceRook) board[0][0]).isHasMoved()
                && (destinationX == 0) && (destinationY == 2) && (currentX == 0)
                && (currentY == 4) & !isInCheck(board, movingPlayer)) {

            if (board[0][1] == null && board[0][2] == null && board[0][3] == null) {
                Piece king = board[0][4];
                Piece rook = board[0][0];
                board[0][4] = null;
                board[0][0] = null;
                board[0][2] = king;
                board[0][3] = rook;
                ChessGameApplication.getChessGameScreen().castle1();

                return true;
            }

        }
        
        if ((board[0][4] instanceof PieceKing) && (board[0][7] instanceof PieceRook)
                && !((PieceKing) board[0][4]).isHasMoved() && !((PieceRook) board[0][7]).isHasMoved()
                && (destinationX == 0) && (destinationY == 6) && (currentX == 0)
                && (currentY == 4) & !isInCheck(board, movingPlayer)) {

            if (board[0][5] == null && board[0][6] == null) {
                Piece king = board[0][4];
                Piece rook = board[0][7];
                board[0][4] = null;
                board[0][7] = null;
                board[0][6] = king;
                board[0][5] = rook;
                ChessGameApplication.getChessGameScreen().castle2();

                return true;
            }

        }
        
        if ((board[7][4] instanceof PieceKing) && (board[7][0] instanceof PieceRook)
                && !((PieceKing) board[7][4]).isHasMoved() && !((PieceRook) board[7][0]).isHasMoved()
                && (destinationX == 7) && (destinationY == 2) && (currentX == 7)
                && (currentY == 4) & !isInCheck(board, movingPlayer)) {

            if (board[7][1] == null && board[7][2] == null && board[7][3] == null) {
                Piece king = board[7][4];
                Piece rook = board[7][0];
                board[7][4] = null;
                board[7][0] = null;
                board[7][2] = king;
                board[7][3] = rook;
                ChessGameApplication.getChessGameScreen().castle3();

                return true;
            }

        }
        
        if ((board[7][4] instanceof PieceKing) && (board[7][7] instanceof PieceRook)
                && !((PieceKing) board[7][4]).isHasMoved() && !((PieceRook) board[7][7]).isHasMoved()
                && (destinationX == 7) && (destinationY == 6) && (currentX == 7)
                && (currentY == 4) & !isInCheck(board, movingPlayer)) {

            if (board[7][5] == null && board[7][6] == null) {
                Piece king = board[7][4];
                Piece rook = board[7][7];
                board[7][4] = null;
                board[7][7] = null;
                board[7][6] = king;
                board[7][5] = rook;
                ChessGameApplication.getChessGameScreen().castle4();

                return true;
            }

        }

        return false;
    }

    private static boolean isInCheckAfterMove(int destinationX, int destinationY, Player movingPlayer) {

        ChessGame chessGame = ChessGameApplication.getChessGame();
        Piece[][] board = chessGame.getBoard().getBoard();
        int currentX = ChessGameApplication.getSelectedXPosition();
        int currentY = ChessGameApplication.getSelectedYPosition();
        Piece[][] boardCopy = new Piece[8][8];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                boardCopy[i][j] = board[i][j];
            }
        }

        boardCopy[destinationX][destinationY] = boardCopy[currentX][currentY];
        boardCopy[currentX][currentY] = null;

        return isInCheck(boardCopy, movingPlayer);

    }

    private static boolean isInCheck(Piece[][] boardCopy, Player movingPlayer) {

        ChessGame chessGame = ChessGameApplication.getChessGame();
        int kingXPosition = -1;
        int kingYPosition = -1;

        Player nonMovingPlayer;
        if (movingPlayer instanceof PlayerBlack) {
            nonMovingPlayer = chessGame.getPlayer(0);
        } else {
            nonMovingPlayer = chessGame.getPlayer(1);
        }

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (boardCopy[i][j] != null && boardCopy[i][j].getPlayer().equals(movingPlayer)
                        && boardCopy[i][j] instanceof PieceKing) {
                    kingXPosition = i;
                    kingYPosition = j;
                }
            }
        }

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {

                if (boardCopy[i][j] != null && !boardCopy[i][j].getPlayer().equals(movingPlayer)) {
                    Moves moves = getValidMoves(boardCopy[i][j], nonMovingPlayer, i, j, boardCopy);
                    for (int k = 0; k < moves.getListOfValidMovesX().size(); k++) {
                        if (moves.getListOfValidMovesX().get(k) == kingXPosition
                                && moves.getListOfValidMovesY().get(k) == kingYPosition) {

                            return true;
                        }
                    }
                }
            }
        }

        return false;

    }

    private static Moves getValidMoves(Piece piece, Player player, int currentX, int currentY, Piece[][] board) {
        Moves moves = new Moves();

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
            // dummy bishop by recursively building it with the validMoves function.
            moves.getListOfValidMovesX().addAll(
                    getValidMoves(new PieceRook(-1, -1, new Player(new ChessGame())), player, currentX, currentY, board)
                            .getListOfValidMovesX());
            moves.getListOfValidMovesY().addAll(
                    getValidMoves(new PieceRook(-1, -1, new Player(new ChessGame())), player, currentX, currentY, board)
                            .getListOfValidMovesY());

            moves.getListOfValidMovesX().addAll(getValidMoves(new PieceBishop(-1, -1, new Player(new ChessGame())),
                    player, currentX, currentY, board).getListOfValidMovesX());
            moves.getListOfValidMovesY().addAll(getValidMoves(new PieceBishop(-1, -1, new Player(new ChessGame())),
                    player, currentX, currentY, board).getListOfValidMovesY());

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
