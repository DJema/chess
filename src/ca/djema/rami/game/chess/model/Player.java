/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.27.0.3728.d139ed893 modeling language!*/

package ca.djema.rami.game.chess.model;
import java.util.*;

// line 32 "../../../../../../ChessGame.ump"
public class Player
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Player Associations
  private List<Piece> pieces;
  private ChessGame chessGame;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Player(ChessGame aChessGame)
  {
    pieces = new ArrayList<Piece>();
    boolean didAddChessGame = setChessGame(aChessGame);
    if (!didAddChessGame)
    {
      throw new RuntimeException("Unable to create player due to chessGame");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public Piece getPiece(int index)
  {
    Piece aPiece = pieces.get(index);
    return aPiece;
  }

  public List<Piece> getPieces()
  {
    List<Piece> newPieces = Collections.unmodifiableList(pieces);
    return newPieces;
  }

  public int numberOfPieces()
  {
    int number = pieces.size();
    return number;
  }

  public boolean hasPieces()
  {
    boolean has = pieces.size() > 0;
    return has;
  }

  public int indexOfPiece(Piece aPiece)
  {
    int index = pieces.indexOf(aPiece);
    return index;
  }

  public ChessGame getChessGame()
  {
    return chessGame;
  }

  public boolean isNumberOfPiecesValid()
  {
    boolean isValid = numberOfPieces() >= minimumNumberOfPieces() && numberOfPieces() <= maximumNumberOfPieces();
    return isValid;
  }

  public static int minimumNumberOfPieces()
  {
    return 1;
  }

  public static int maximumNumberOfPieces()
  {
    return 16;
  }

  public Piece addPiece(Square aSquare)
  {
    if (numberOfPieces() >= maximumNumberOfPieces())
    {
      return null;
    }
    else
    {
      return new Piece(aSquare, this);
    }
  }

  public boolean addPiece(Piece aPiece)
  {
    boolean wasAdded = false;
    if (pieces.contains(aPiece)) { return false; }
    if (numberOfPieces() >= maximumNumberOfPieces())
    {
      return wasAdded;
    }

    Player existingPlayer = aPiece.getPlayer();
    boolean isNewPlayer = existingPlayer != null && !this.equals(existingPlayer);

    if (isNewPlayer && existingPlayer.numberOfPieces() <= minimumNumberOfPieces())
    {
      return wasAdded;
    }

    if (isNewPlayer)
    {
      aPiece.setPlayer(this);
    }
    else
    {
      pieces.add(aPiece);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removePiece(Piece aPiece)
  {
    boolean wasRemoved = false;
    //Unable to remove aPiece, as it must always have a player
    if (this.equals(aPiece.getPlayer()))
    {
      return wasRemoved;
    }

    //player already at minimum (1)
    if (numberOfPieces() <= minimumNumberOfPieces())
    {
      return wasRemoved;
    }
    pieces.remove(aPiece);
    wasRemoved = true;
    return wasRemoved;
  }

  public boolean addPieceAt(Piece aPiece, int index)
  {  
    boolean wasAdded = false;
    if(addPiece(aPiece))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPieces()) { index = numberOfPieces() - 1; }
      pieces.remove(aPiece);
      pieces.add(index, aPiece);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMovePieceAt(Piece aPiece, int index)
  {
    boolean wasAdded = false;
    if(pieces.contains(aPiece))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPieces()) { index = numberOfPieces() - 1; }
      pieces.remove(aPiece);
      pieces.add(index, aPiece);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addPieceAt(aPiece, index);
    }
    return wasAdded;
  }

  public boolean setChessGame(ChessGame aChessGame)
  {
    boolean wasSet = false;
    //Must provide chessGame to player
    if (aChessGame == null)
    {
      return wasSet;
    }

    //chessGame already at maximum (2)
    if (aChessGame.numberOfPlayer() >= ChessGame.maximumNumberOfPlayer())
    {
      return wasSet;
    }
    
    ChessGame existingChessGame = chessGame;
    chessGame = aChessGame;
    if (existingChessGame != null && !existingChessGame.equals(aChessGame))
    {
      boolean didRemove = existingChessGame.removePlayer(this);
      if (!didRemove)
      {
        chessGame = existingChessGame;
        return wasSet;
      }
    }
    chessGame.addPlayer(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    for(int i=pieces.size(); i > 0; i--)
    {
      Piece aPiece = pieces.get(i - 1);
      aPiece.delete();
    }
    ChessGame placeholderChessGame = chessGame;
    this.chessGame = null;
    if(placeholderChessGame != null)
    {
      placeholderChessGame.removePlayer(this);
    }
  }

}