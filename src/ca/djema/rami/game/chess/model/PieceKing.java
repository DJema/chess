/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.27.0.3728.d139ed893 modeling language!*/

package ca.djema.rami.game.chess.model;

// line 29 "../../../../../../ChessGame.ump"
public class PieceKing extends Piece
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

    private boolean hasMoved = false;
    
  //------------------------
  // CONSTRUCTOR
  //------------------------

  public PieceKing(int aXPosition, int aYPosition, Player aPlayer)
  {
    super(aXPosition, aYPosition, aPlayer);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public void delete()
  {
    super.delete();
  }

public boolean isHasMoved() {
    return hasMoved;
}

public void setHasMoved(boolean hasMoved) {
    this.hasMoved = hasMoved;
}

}