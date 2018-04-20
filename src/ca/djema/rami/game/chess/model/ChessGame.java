/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.27.0.3728.d139ed893 modeling language!*/

package ca.djema.rami.game.chess.model;
import java.util.*;

// line 3 "../../../../../../ChessGame.ump"
public class ChessGame
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ChessGame Associations
  private Board board;
  private List<Player> player;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ChessGame(Board aBoard)
  {
    if (aBoard == null || aBoard.getChessGame() != null)
    {
      throw new RuntimeException("Unable to create ChessGame due to aBoard");
    }
    board = aBoard;
    player = new ArrayList<Player>();
  }

  public ChessGame()
  {
    board = new Board(this);
    player = new ArrayList<Player>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public Board getBoard()
  {
    return board;
  }

  public Player getPlayer(int index)
  {
    Player aPlayer = player.get(index);
    return aPlayer;
  }

  public List<Player> getPlayer()
  {
    List<Player> newPlayer = Collections.unmodifiableList(player);
    return newPlayer;
  }

  public int numberOfPlayer()
  {
    int number = player.size();
    return number;
  }

  public boolean hasPlayer()
  {
    boolean has = player.size() > 0;
    return has;
  }

  public int indexOfPlayer(Player aPlayer)
  {
    int index = player.indexOf(aPlayer);
    return index;
  }

  public boolean isNumberOfPlayerValid()
  {
    boolean isValid = numberOfPlayer() >= minimumNumberOfPlayer() && numberOfPlayer() <= maximumNumberOfPlayer();
    return isValid;
  }

  public static int requiredNumberOfPlayer()
  {
    return 2;
  }

  public static int minimumNumberOfPlayer()
  {
    return 2;
  }

  public static int maximumNumberOfPlayer()
  {
    return 2;
  }

  public Player addPlayer()
  {
    if (numberOfPlayer() >= maximumNumberOfPlayer())
    {
      return null;
    }
    else
    {
      return new Player(this);
    }
  }

  public boolean addPlayer(Player aPlayer)
  {
    boolean wasAdded = false;
    if (player.contains(aPlayer)) { return false; }
    if (numberOfPlayer() >= maximumNumberOfPlayer())
    {
      return wasAdded;
    }

    ChessGame existingChessGame = aPlayer.getChessGame();
    boolean isNewChessGame = existingChessGame != null && !this.equals(existingChessGame);

    if (isNewChessGame && existingChessGame.numberOfPlayer() <= minimumNumberOfPlayer())
    {
      return wasAdded;
    }

    if (isNewChessGame)
    {
      aPlayer.setChessGame(this);
    }
    else
    {
      player.add(aPlayer);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removePlayer(Player aPlayer)
  {
    boolean wasRemoved = false;
    //Unable to remove aPlayer, as it must always have a chessGame
    if (this.equals(aPlayer.getChessGame()))
    {
      return wasRemoved;
    }

    //chessGame already at minimum (2)
    if (numberOfPlayer() <= minimumNumberOfPlayer())
    {
      return wasRemoved;
    }
    player.remove(aPlayer);
    wasRemoved = true;
    return wasRemoved;
  }

  public void delete()
  {
    Board existingBoard = board;
    board = null;
    if (existingBoard != null)
    {
      existingBoard.delete();
    }
    while (player.size() > 0)
    {
      Player aPlayer = player.get(player.size() - 1);
      aPlayer.delete();
      player.remove(aPlayer);
    }
    
  }

}