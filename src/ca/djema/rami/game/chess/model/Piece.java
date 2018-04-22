/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.27.0.3728.d139ed893 modeling language!*/

package ca.djema.rami.game.chess.model;

// line 10 "../../../../../../ChessGame.ump"
public class Piece
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Piece Attributes
  private int xPosition;
  private int yPosition;

  //Piece Associations
  private Player player;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Piece(int aXPosition, int aYPosition, Player aPlayer)
  {
    xPosition = aXPosition;
    yPosition = aYPosition;
    boolean didAddPlayer = setPlayer(aPlayer);
    if (!didAddPlayer)
    {
      throw new RuntimeException("Unable to create piece due to player");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setXPosition(int aXPosition)
  {
    boolean wasSet = false;
    xPosition = aXPosition;
    wasSet = true;
    return wasSet;
  }

  public boolean setYPosition(int aYPosition)
  {
    boolean wasSet = false;
    yPosition = aYPosition;
    wasSet = true;
    return wasSet;
  }

  public int getXPosition()
  {
    return xPosition;
  }

  public int getYPosition()
  {
    return yPosition;
  }

  public Player getPlayer()
  {
    return player;
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
    Player placeholderPlayer = player;
    this.player = null;
    if(placeholderPlayer != null)
    {
      placeholderPlayer.removePiece(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "xPosition" + ":" + getXPosition()+ "," +
            "yPosition" + ":" + getYPosition()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "player = "+(getPlayer()!=null?Integer.toHexString(System.identityHashCode(getPlayer())):"null");
  }
}