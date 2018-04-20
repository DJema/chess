/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.27.0.3728.d139ed893 modeling language!*/

package ca.djema.rami.game.chess.model;

// line 9 "../../../../../../ChessGame.ump"
public class Square
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Square Associations
  private Piece piece;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Square()
  {}

  //------------------------
  // INTERFACE
  //------------------------

  public Piece getPiece()
  {
    return piece;
  }

  public boolean hasPiece()
  {
    boolean has = piece != null;
    return has;
  }

  public boolean setPiece(Piece aNewPiece)
  {
    boolean wasSet = false;
    if (piece != null && !piece.equals(aNewPiece) && equals(piece.getSquare()))
    {
      //Unable to setPiece, as existing piece would become an orphan
      return wasSet;
    }

    piece = aNewPiece;
    Square anOldSquare = aNewPiece != null ? aNewPiece.getSquare() : null;

    if (!this.equals(anOldSquare))
    {
      if (anOldSquare != null)
      {
        anOldSquare.piece = null;
      }
      if (piece != null)
      {
        piece.setSquare(this);
      }
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Piece existingPiece = piece;
    piece = null;
    if (existingPiece != null)
    {
      existingPiece.delete();
    }
  }

}