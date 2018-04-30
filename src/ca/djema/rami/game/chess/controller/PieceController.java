package ca.djema.rami.game.chess.controller;

import java.io.IOException;
import java.util.ArrayList;

import ca.djema.rami.game.chess.application.ChessGameApplication;
import ca.djema.rami.game.chess.model.ChessGame;
import ca.djema.rami.game.chess.model.Piece;
import ca.djema.rami.game.chess.model.PieceBishop;
import ca.djema.rami.game.chess.model.PieceKnight;
import ca.djema.rami.game.chess.model.PiecePawn;
import ca.djema.rami.game.chess.model.PieceRook;
import ca.djema.rami.game.chess.model.Player;
import ca.djema.rami.game.chess.model.PlayerBlack;
import ca.djema.rami.game.chess.model.PlayerWhite;
import ca.djema.rami.game.chess.view.ChessGameScreen;

public class PieceController {

	public static void click(int x, int y) throws IOException {

		ChessGame chessGame = ChessGameApplication.getChessGame();
		Piece[][] board = chessGame.getBoard().getBoard();

		// No piece is selected, then select one of your pieces
		if (ChessGameApplication.isPieceSelected() == false) {

			if (board[x][y] != null
					&& board[x][y].getPlayer().equals(
							ChessGameApplication.getMovingPlayer())) {
				ChessGameApplication.setSelectedXPosition(x);
				ChessGameApplication.setSelectedYPosition(y);
				ChessGameApplication.setPieceSelected(true);
			}

		}

		// Select another one of your own pieces
		else if (ChessGameApplication.isPieceSelected() == true
				&& board[x][y] != null
				&& board[x][y].getPlayer().equals(
						ChessGameApplication.getMovingPlayer())) {

			ChessGameApplication.setSelectedXPosition(x);
			ChessGameApplication.setSelectedYPosition(y);

		}

		// If a piece is selected and you are not selecting another one of your
		// pieces, try to move to the clicked position
		else if (ChessGameApplication.isPieceSelected() == true) {
			move(x, y);
		}
	}

	private static void move(int x, int y) throws IOException {

		ChessGame chessGame = ChessGameApplication.getChessGame();
		Piece movingPiece = chessGame.getBoard().getBoard()[ChessGameApplication
				.getSelectedXPosition()][ChessGameApplication
				.getSelectedYPosition()];
		Player movingPlayer = chessGame.getBoard().getBoard()[ChessGameApplication
				.getSelectedXPosition()][ChessGameApplication
				.getSelectedYPosition()].getPlayer();

		if (movingPiece instanceof PiecePawn
				&& movingPlayer instanceof PlayerWhite) {
			moveWhitePawn(x, y);
		}
		if (movingPiece instanceof PiecePawn
				&& movingPlayer instanceof PlayerBlack) {
			moveBlackPawn(x, y);
		}
		if (movingPiece instanceof PieceRook) {
			moveRook(x, y);
		}
		if (movingPiece instanceof PieceBishop) {
			moveBishop(x, y);
		}
		if (movingPiece instanceof PieceKnight) {
			moveKnight(x, y);
		}

	}

	// Logic for moving a white pawn
	private static void moveWhitePawn(int destinationX, int destinationY)
			throws IOException {

		ChessGame chessGame = ChessGameApplication.getChessGame();
		Piece[][] board = chessGame.getBoard().getBoard();
		int currentX = ChessGameApplication.getSelectedXPosition();
		int currentY = ChessGameApplication.getSelectedYPosition();

		Moves validMoves = getValidMoves(board[currentX][currentY],
				board[currentX][currentY].getPlayer());

		for (int i = 0; i < validMoves.getListOfValidMovesX().size(); i++) {

			if (validMoves.getListOfValidMovesX().get(i) == destinationX
					&& validMoves.getListOfValidMovesY().get(i) == destinationY) {

				board[destinationX][destinationY] = board[currentX][currentY];
				board[currentX][currentY] = null;

				ChessGameApplication.getChessGameScreen().movePawn(currentX,
						currentY, destinationX, destinationY, "white");
				ChessGameApplication.setMovingPlayer(chessGame.getPlayer(1));
				ChessGameApplication.setPieceSelected(false);
				ChessGameApplication.setSelectedXPosition(-1);
				ChessGameApplication.setSelectedYPosition(-1);

				break;
			}
		}
	}

	// Logic for moving a black pawn
	private static void moveBlackPawn(int destinationX, int destinationY)
			throws IOException {

		ChessGame chessGame = ChessGameApplication.getChessGame();
		Piece[][] board = chessGame.getBoard().getBoard();
		int currentX = ChessGameApplication.getSelectedXPosition();
		int currentY = ChessGameApplication.getSelectedYPosition();

		Moves validMoves = getValidMoves(board[currentX][currentY],
				board[currentX][currentY].getPlayer());

		for (int i = 0; i < validMoves.getListOfValidMovesX().size(); i++) {

			if (validMoves.getListOfValidMovesX().get(i) == destinationX
					&& validMoves.getListOfValidMovesY().get(i) == destinationY) {

				board[destinationX][destinationY] = board[currentX][currentY];
				board[currentX][currentY] = null;

				ChessGameApplication.getChessGameScreen().movePawn(currentX,
						currentY, destinationX, destinationY, "black");
				ChessGameApplication.setMovingPlayer(chessGame.getPlayer(0));
				ChessGameApplication.setPieceSelected(false);
				ChessGameApplication.setSelectedXPosition(-1);
				ChessGameApplication.setSelectedYPosition(-1);

				break;
			}
		}
	}

	// Logic for moving a Rook
	private static void moveRook(int destinationX, int destinationY)
			throws IOException {

		ChessGame chessGame = ChessGameApplication.getChessGame();
		Piece[][] board = chessGame.getBoard().getBoard();
		int currentX = ChessGameApplication.getSelectedXPosition();
		int currentY = ChessGameApplication.getSelectedYPosition();
		Player player = board[currentX][currentY].getPlayer();

		Moves validMoves = getValidMoves(board[currentX][currentY],
				board[currentX][currentY].getPlayer());

		for (int i = 0; i < validMoves.getListOfValidMovesX().size(); i++) {

			if (validMoves.getListOfValidMovesX().get(i) == destinationX
					&& validMoves.getListOfValidMovesY().get(i) == destinationY) {

				board[destinationX][destinationY] = board[currentX][currentY];
				board[currentX][currentY] = null;

				if (player instanceof PlayerWhite) {
					ChessGameApplication.getChessGameScreen().moveRook(
							currentX, currentY, destinationX, destinationY,
							"white");
					ChessGameApplication
							.setMovingPlayer(chessGame.getPlayer(1));
				} else {
					ChessGameApplication.getChessGameScreen().moveRook(
							currentX, currentY, destinationX, destinationY,
							"black");
					ChessGameApplication
							.setMovingPlayer(chessGame.getPlayer(0));
				}

				ChessGameApplication.setPieceSelected(false);
				ChessGameApplication.setSelectedXPosition(-1);
				ChessGameApplication.setSelectedYPosition(-1);

				break;
			}
		}
	}

	// Logic for moving a Bishop
	private static void moveBishop(int destinationX, int destinationY)
			throws IOException {

		ChessGame chessGame = ChessGameApplication.getChessGame();
		Piece[][] board = chessGame.getBoard().getBoard();
		int currentX = ChessGameApplication.getSelectedXPosition();
		int currentY = ChessGameApplication.getSelectedYPosition();
		Player player = board[currentX][currentY].getPlayer();

		Moves validMoves = getValidMoves(board[currentX][currentY],
				board[currentX][currentY].getPlayer());

		for (int i = 0; i < validMoves.getListOfValidMovesX().size(); i++) {

			if (validMoves.getListOfValidMovesX().get(i) == destinationX
					&& validMoves.getListOfValidMovesY().get(i) == destinationY) {

				board[destinationX][destinationY] = board[currentX][currentY];
				board[currentX][currentY] = null;

				if (player instanceof PlayerWhite) {
					ChessGameApplication.getChessGameScreen().moveBishop(
							currentX, currentY, destinationX, destinationY,
							"white");
					ChessGameApplication
							.setMovingPlayer(chessGame.getPlayer(1));
				} else {
					ChessGameApplication.getChessGameScreen().moveBishop(
							currentX, currentY, destinationX, destinationY,
							"black");
					ChessGameApplication
							.setMovingPlayer(chessGame.getPlayer(0));
				}

				ChessGameApplication.setPieceSelected(false);
				ChessGameApplication.setSelectedXPosition(-1);
				ChessGameApplication.setSelectedYPosition(-1);

				break;
			}
		}
	}

	// Logic for moving a Knight
	private static void moveKnight(int destinationX, int destinationY)
			throws IOException {

		ChessGame chessGame = ChessGameApplication.getChessGame();
		Piece[][] board = chessGame.getBoard().getBoard();
		int currentX = ChessGameApplication.getSelectedXPosition();
		int currentY = ChessGameApplication.getSelectedYPosition();
		Player player = board[currentX][currentY].getPlayer();

		Moves validMoves = getValidMoves(board[currentX][currentY],
				board[currentX][currentY].getPlayer());

		for (int i = 0; i < validMoves.getListOfValidMovesX().size(); i++) {

			if (validMoves.getListOfValidMovesX().get(i) == destinationX
					&& validMoves.getListOfValidMovesY().get(i) == destinationY) {

				board[destinationX][destinationY] = board[currentX][currentY];
				board[currentX][currentY] = null;

				if (player instanceof PlayerWhite) {
					ChessGameApplication.getChessGameScreen().moveKnight(
							currentX, currentY, destinationX, destinationY,
							"white");
					ChessGameApplication
							.setMovingPlayer(chessGame.getPlayer(1));
				} else {
					ChessGameApplication.getChessGameScreen().moveKnight(
							currentX, currentY, destinationX, destinationY,
							"black");
					ChessGameApplication
							.setMovingPlayer(chessGame.getPlayer(0));
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
			if (currentX + 1 < 8
					&& currentY + 1 < 8
					&& board[currentX + 1][currentY + 1] != null
					&& board[currentX + 1][currentY + 1].getPlayer() instanceof PlayerBlack) {
				moves.getListOfValidMovesX().add(currentX + 1);
				moves.getListOfValidMovesY().add(currentY + 1);
			}
			if (currentX + 1 < 8
					&& currentY - 1 < 8
					&& currentY - 1 >= 0
					&& board[currentX + 1][currentY - 1] != null
					&& board[currentX + 1][currentY - 1].getPlayer() instanceof PlayerBlack) {
				moves.getListOfValidMovesX().add(currentX + 1);
				moves.getListOfValidMovesY().add(currentY - 1);
			}
			if(currentX == 1 && board[currentX + 2][currentY] == null){
				moves.getListOfValidMovesX().add(currentX + 2);
				moves.getListOfValidMovesY().add(currentY);
			}
			return moves;
		}

		if (piece instanceof PiecePawn && player instanceof PlayerBlack) {
			if (currentX - 1 > -1 && board[currentX - 1][currentY] == null) {
				moves.getListOfValidMovesX().add(currentX - 1);
				moves.getListOfValidMovesY().add(currentY);
			}
			if (currentX - 1 > -1
					&& currentY + 1 < 8
					&& board[currentX - 1][currentY + 1] != null
					&& board[currentX - 1][currentY + 1].getPlayer() instanceof PlayerWhite) {
				moves.getListOfValidMovesX().add(currentX - 1);
				moves.getListOfValidMovesY().add(currentY + 1);
			}
			if (currentX - 1 > -1
					&& currentY - 1 > -1
					&& currentY - 1 >= 0
					&& board[currentX - 1][currentY - 1] != null
					&& board[currentX - 1][currentY - 1].getPlayer() instanceof PlayerWhite) {
				moves.getListOfValidMovesX().add(currentX - 1);
				moves.getListOfValidMovesY().add(currentY - 1);
			}
			if(currentX == 6 && board[currentX - 2][currentY] == null){
				moves.getListOfValidMovesX().add(currentX - 2);
				moves.getListOfValidMovesY().add(currentY);
			}
			return moves;

		}

		if (piece instanceof PieceRook) {

			for (int i = currentX + 1; i < 8; i++) {
				if (board[i][currentY] == null) {
					moves.getListOfValidMovesX().add(i);
					moves.getListOfValidMovesY().add(currentY);
				} else if (board[i][currentY] != null
						&& board[i][currentY].getPlayer() != player) {
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
				} else if (board[i][currentY] != null
						&& board[i][currentY].getPlayer() != player) {
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
				} else if (board[currentX][i] != null
						&& board[currentX][i].getPlayer() != player) {
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
				} else if (board[currentX][i] != null
						&& board[currentX][i].getPlayer() != player) {
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
				} else if (board[i][j] != null
						&& board[i][j].getPlayer() != player) {
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
				} else if (board[i][j] != null
						&& board[i][j].getPlayer() != player) {
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
				} else if (board[i][j] != null
						&& board[i][j].getPlayer() != player) {
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
				} else if (board[i][j] != null
						&& board[i][j].getPlayer() != player) {
					moves.getListOfValidMovesX().add(i);
					moves.getListOfValidMovesY().add(j);
					break;
				} else {
					break;
				}
			}

			return moves;
		}

		if (piece instanceof PieceKnight) {

			// Try all 8 possible moves for the knight
			if (currentX + 2 < 8 && currentY + 1 < 8
					&& ((board[currentX + 2][currentY + 1] == null)
					|| board[currentX + 2][currentY + 1].getPlayer() != player)) {
				moves.getListOfValidMovesX().add(currentX + 2);
				moves.getListOfValidMovesY().add(currentY + 1);
			}
			if (currentX + 2 < 8 && currentY - 1 >= 0
					&& ((board[currentX + 2][currentY - 1] == null)
					|| board[currentX + 2][currentY - 1].getPlayer() != player)) {
				moves.getListOfValidMovesX().add(currentX + 2);
				moves.getListOfValidMovesY().add(currentY - 1);
			}
			if (currentX - 2 >= 0 && currentY + 1 < 8
					&& ((board[currentX - 2][currentY + 1] == null)
					|| board[currentX - 2][currentY + 1].getPlayer() != player)) {
				moves.getListOfValidMovesX().add(currentX - 2);
				moves.getListOfValidMovesY().add(currentY + 1);
			}
			if (currentX - 2 >= 0 && currentY - 1 >= 0
					&& ((board[currentX - 2][currentY - 1] == null)
					|| board[currentX - 2][currentY - 1].getPlayer() != player)) {
				moves.getListOfValidMovesX().add(currentX - 2);
				moves.getListOfValidMovesY().add(currentY - 1);
			}
			if (currentX + 1 < 8 && currentY + 2 < 8
					&& ((board[currentX + 1][currentY + 2] == null)
					|| board[currentX + 1][currentY + 2].getPlayer() != player)) {
				moves.getListOfValidMovesX().add(currentX + 1);
				moves.getListOfValidMovesY().add(currentY + 2);
			}
			if (currentX - 1 >= 0 && currentY + 2 < 8
					&& ((board[currentX - 1][currentY + 2] == null)
					|| board[currentX - 1][currentY + 2].getPlayer() != player)) {
				moves.getListOfValidMovesX().add(currentX - 1);
				moves.getListOfValidMovesY().add(currentY + 2);
			}
			if (currentX + 1 < 8 && currentY - 2 >= 0
					&& ((board[currentX + 1][currentY - 2] == null)
					|| board[currentX + 1][currentY - 2].getPlayer() != player)) {
				moves.getListOfValidMovesX().add(currentX + 1);
				moves.getListOfValidMovesY().add(currentY - 2);
			}
			if (currentX - 1 >= 0 && currentY - 2 >= 0
					&& ((board[currentX - 1][currentY - 2] == null)
					|| board[currentX- 1][currentY - 2].getPlayer() != player)) {
				moves.getListOfValidMovesX().add(currentX - 1);
				moves.getListOfValidMovesY().add(currentY - 2);
			}

			return moves;
		}

		return null;
	}

}
