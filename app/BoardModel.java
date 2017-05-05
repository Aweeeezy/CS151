import javax.swing.event.*;
import java.util.*;

public class BoardModel {
  Pits pits;
  Players players;
  int currentPlayer;
  Pit currentPit;
  ArrayList<UndoItem> undoItems;
  ArrayList<ChangeListener> listeners;

  public BoardModel() {
    currentPlayer = 0;
    pits = new Pits();
    System.out.println("Should have just made pits");
    players = new Players();
    undoItems = new ArrayList<UndoItem>(2);
    listeners = new ArrayList<ChangeListener>();
  }

  public void takeTurn(int pitID) {
    /*undoItems.set(0, pits.getOld());
    undoItems.set(1, players.getOld());*/

    // Initializes map used for finding corresponding pit
    HashMap<Integer, Integer> pitMap = new HashMap<>();
    for(int i=0; i<13; i++)
      if (i != 6)
        pitMap.put(i, 12-i);

    int enemyMancala;
    if (currentPlayer == 1)
      enemyMancala = 13;
    else
      enemyMancala = 6;

    Pit currentPit = pits.get(pitID);
    int stones = currentPit.getCount();
    currentPit.setCount(0);
    while (stones > 1) {
      if (currentPit.getId() != enemyMancala) {
        stones -= 1;
        currentPit.setCount(currentPit.getCount() + 1);
      }
      try {
        currentPit = pits.get(currentPit.getId() + 1);
      } catch (IndexOutOfBoundsException e) {
        currentPit = pits.get(0);
      }
    }

    boolean turnOver = true;
    if (currentPit.isMancala() && currentPit.isPlayer(currentPlayer)) {
      turnOver = false;
    } else if (currentPit.isMancala()) {
      currentPit = pits.get(currentPit.getId() + 2);
    }

    int prizePit = currentPit.getId();
    if (currentPit.isPlayer(currentPlayer)) {
      if (currentPit.getCount() == 0) {
        Iterator <Pit> iterator = pits.getIterator(prizePit);
        while (currentPit.getId() != pitMap.get(prizePit))
          try {
            currentPit = iterator.next();
          } catch (IndexOutOfBoundsException e) {
            currentPit = pits.get(0);
          }

        int stolenAmount = currentPit.getCount();
        currentPit.setCount(0);
        while (currentPit.getId() != prizePit)
          try {
            currentPit = iterator.next();
          } catch (IndexOutOfBoundsException e) {
            currentPit = pits.get(0);
          }
        currentPit.setCount(stolenAmount);
      }
    } else {
      currentPit.setCount(currentPit.getCount() + 1);
    }

    notify(turnOver);
  }

  public void notify(boolean turnOver) {
    ChangeEvent e = new ChangeEvent(this);
    for (ChangeListener c : listeners) {
      c.stateChanged(e);
    }

    if (turnOver)
      System.out.println("Turn is over -- display Undo / End Turn buttons");
    else
      System.out.println("You ended on your mancala -- take another turn");
  }

  public void registerView(ChangeListener c) {
    listeners.add(c);
  }

  public void undo() {
    if (players.get(currentPlayer).hasUndo()) {
      pits = (Pits) undoItems.get(0);
      players = (Players) undoItems.get(1);
      players.get(currentPlayer).useUndo();
    } else {
      System.out.println("No more undos left");
    }
  }

  public void changePlayer() {
    currentPlayer = (currentPlayer + 1) % 2;
  }
}
