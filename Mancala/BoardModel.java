/**
 * Model component of the Mancala game architecture.
 */
import java.util.*;
import javax.swing.event.*;

/**
 * The BoardModel class contains all the data used by the Mancala game.
 */
public class BoardModel {
  private int[] pits;
  private int[] backupPits;
  private ArrayList<ChangeListener> listeners;
  private int currentPlayer;
  private int lastPlayer;
  private HashMap<Integer, Integer> opposites;
  private int[] undos;
  boolean turnOver;
  boolean alreadyUndid;

  /**
   * Constructs BoardModel by initializing arrays for storing pit data and view
   * ChangeListeners, a HashMap for storing opposite pit paris, an array of
   * undos remaing for each player, an int storing the current player, and
   * flags used to determine if an undo is appropriate.
   */
  public BoardModel() {
    pits = new int[14];
    listeners = new ArrayList<ChangeListener>();
    currentPlayer = 0;
    turnOver = true;
    undos = new int[] {3, 3};
    alreadyUndid = false;
    opposites = new HashMap<Integer, Integer>();
    initOpposites();
  }

  /**
   * Initialized the HashMap used to find opposite pit pairs.
   */
  public void initOpposites() {
    opposites.put(0, 12);
    opposites.put(1, 11);
    opposites.put(2, 10);
    opposites.put(3, 9);
    opposites.put(4, 8);
    opposites.put(5, 7);
    opposites.put(7, 5);
    opposites.put(8, 4);
    opposites.put(9, 3);
    opposites.put(10, 2);
    opposites.put(11, 1);
    opposites.put(12, 0);
  }

  /**
   * Determines if it is appropraite for an undo action to take place.
   */
  public void undo() {
    if (turnOver && undos[(currentPlayer + 1) % 2] > 0 && !alreadyUndid) {
      pits = backupPits;
      currentPlayer = (currentPlayer + 1) % 2;
      undos[currentPlayer]--;
      alreadyUndid = true;
      lastPlayer = currentPlayer;
      notifyViews();
    }
  }

  /**
   * Attaches a view's ChangeListener to the model.
   * @param ChangeListener l
   */
  public void registerView(ChangeListener l) {
    listeners.add(l);
  }

  /**
   * Initialized the array of ints that represent the stone count in each pit.
   * @param int numStones -- the initial value for each pit.
   */
  public void initializePits(int numStones) {
    for (int p=0; p < pits.length; p++) {
      if (p == 6 || p == 13)
        pits[p] = 0;
      else
        pits[p] = numStones;
    }
    notifyViews();
  }

  /**
   * Triggers the stateChanged action for the views' ChangeListener.
   */
  public void notifyViews() {
    ChangeEvent e = new ChangeEvent(this);
    for (ChangeListener l : listeners)
      l.stateChanged(e);
  }

  /**
   * Yields an array of ints representing the stone count in each pit.
   * @return int[] pits
   */
  public int[] getPits() {
    return pits;
  }

  /**
   * Yields the current player.
   * @return int currentPlayer
   */
  public int getCurrentPlayer() {
    return currentPlayer;
  }

  /**
   * Performs Mancala game's logic to complete a turn.
   * @param int id -- signifies the pit that the player has clicked.
   */
  public void takeTurn(int id) {
    if (currentPlayer != lastPlayer)
      alreadyUndid = false;
    turnOver = true;
    backupPits = pits.clone();

    int numStones = pits[id];
    pits[id] = 0;
    int index = id;

    while (numStones > 1) {
      index--;
      if (index < 0)
        index = 13;
      if ((currentPlayer == 0 && index == 6) || (currentPlayer == 1 && index == 13))
        continue;
      pits[index]++;
      numStones--;
    }

    index--;
    if (index < 0)
      index = 13;
    if ((currentPlayer == 0 && index == 6) || (currentPlayer == 1 && index == 13))
      index--;
      if (index < 0)
        index = 13;
    if ((currentPlayer == 0 && index == 13) || (currentPlayer == 1 && index == 6))
      turnOver = false;
    if (pits[index] == 0 &&
        ((currentPlayer == 0 && index > 0 && index < 6) ||
         (currentPlayer == 1 && index > 6 && index < 13))) {
      int stolenAmount = pits[opposites.get(index)] + 1;
      pits[opposites.get(index)] = 0;
      pits[index]--;
      if (currentPlayer == 0)
        pits[13] += stolenAmount;
      else
        pits[6] += stolenAmount;
    }
    pits[index]++;

    checkIfGameOver();

    notifyViews();

    if (turnOver) {
      currentPlayer = (currentPlayer + 1) % 2;
      turnOver = true;
    }
  }

  /**
   * Sums up both players' pit counts independently to determine if the game is
   * finished -- moves the remaining stones on the other player's side to their
   * mancala if so.
   */
  public void checkIfGameOver() {
    int sum1 = 0;
    for (int i=0; i < 6; i++)
      sum1 += pits[i];
    if (sum1 == 0) {
      for (int i=7; i < 13; i++) {
        sum1 += pits[i];
        pits[i] = 0;
        pits[6] += sum1;
      }
    }
    int sum2 = 0;
    for (int i=7; i < 13; i++)
      sum2 += pits[i];
    if (sum2 == 0) {
      for (int i=0; i < 6; i++) {
        sum2 += pits[i];
        pits[i] = 0;
        pits[13] += sum2;
      }
    }
  }
}
