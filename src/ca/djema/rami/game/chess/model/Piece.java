/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.27.0.3728.d139ed893 modeling language!*/

package ca.djema.rami.game.chess.model;

// line 12 "../../../../../../ChessGame.ump"
public class Piece
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Piece Associations
  private Square square;
  private Player player;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Piece(Square aSquare, Player aPlayer)
  {
    boolean didAddSquare = setSquare(aSquare);
    if (!didAddSquare)
    {
      throw new RuntimeException("Unable to create piece due to square");
    }
    boolean didAddPlayer = setPlayer(aPlayer);
    if (!didAddPlayer)
    {
      throw new RuntimeException("Unable to create piece due to player");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public Square getSquare()
  {
    return square;
  }

  public Player getPlayer()
  {
    return player;
  }

  public boolean setSquare(Square aNewSquare)
  {
    boolean wasSet = false;
    if (aNewSquare == null)
    {
      //Unable to setSquare to null, as piece must always be associated to a square
      return wasSet;
    }
    
    Piece existingPiece = aNewSquare.getPiece();
    if (existingPiece != null && !equals(existingPiece))
    {
      //Unable to setSquare, the current square already has a piece, which would be orphaned if it were re-assigned
      return wasSet;
    }
    
    Square anOldSquare = square;
    square = aNewSquare;
    square.setPiece(this);

    if (anOldSquare != null)
    {
      anOldSquare.setPiece(null);
    }
    wasSet = true;
    return wasSet;
  }

  public boolean setPlayer(Player aPlayer)
  {
    boolean wasSet = false;
    //Must provide player to piece
    if (aPlayer == null)
    {
      return wasSet;
    }

    //player already at maximum (16)
    if (aPlayer.numberOfPieces() >= Player.maximumNumberOfPieces())
    {
      return wasSet;
    }
    
    Player existingPlayer = player;
    player = aPlayer;
    if (existingPlayer != null && !existingPlayer.equals(aPlayer))
    {
      boolean didRemove = existingPlayer.removePiece(this);
      if (!didRemove)
      {
        player = existingPlayer;
        return wasSet;
      }
    }
    player.addPiece(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Square existingSquare = square;
    square = null;
    if (existingSquare != null)
    {
      existingSquare.setPiece(null);
    }
    Player placeholderPlayer = player;
    this.player = null;
    if(placeholderPlayer != null)
    {
      placeholderPlayer.removePiece(this);
    }
  }

}