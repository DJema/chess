/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.27.0.3728.d139ed893 modeling language!*/

package ca.djema.rami.game.chess.model;

// line 7 "../../../../../../ChessGame.ump"
public class Board
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Board Associations
  private ChessGame chessGame;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Board(ChessGame aChessGame)
  {
    if (aChessGame == null || aChessGame.getBoard() != null)
    {
      throw new RuntimeException("Unable to create Board due to aChessGame");
    }
    chessGame = aChessGame;
  }

  public Board()
  {
    chessGame = new ChessGame(this);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public ChessGame getChessGame()
  {
    return chessGame;
  }

  public void delete()
  {
    ChessGame existingChessGame = chessGame;
    chessGame = null;
    if (existingChessGame != null)
    {
      existingChessGame.delete();
    }
  }

}