public class BoardModel {
  Pits pits;
  Players players;
  int currentPlayer;
  Pit currentPit;
  ArrayList<UndoElement> undoItems;
  ArrayList<ChangeListener> listeners;
  ArrayList<String> styleIdentifiers;

  public BoardModel() {
    currentPlayer = 0;
    pits = new Pits();
    players = new Players();
    undoItems = new ArrayList<UndoElement>(2);
    listeners = new ArrayList<ChangeListener>(12);
    styleIdentifiers = new ArrayList<String>();
  }

  public void takeTurn(int pitID) {
    undoItems[0] = pits.getOld();
    undoItems[1] = players.getOld();

    // Initializes map used for finding corresponding pit
    HashMap<int, int> pitMap = new HashMap<>();
    for(int i=0; i<13; i++)
      if (i != 6)
        pitMap.put(i:12-i);

    int enemyMancala;
    if (currentPlayer)
      enemyMancala = 13;
    else
      enemyMancala = 6;

    Pit currentPit = pits.get(pitID);
    int stones = currentPit.count;
    currentPit.count = 0;
    while (stones > 1) {
      if (currentPit.id != enemyMancala) {
        stones -= 1;
        currentPit.count += 1;
      }
      try {
        currentPit = pits.get(currentPit.id + 1);
      } catch (IndexOutOfBoundsException e) {
        currentPit = pits.get(0);
      }
    }

    boolean turnOver = true;
    if (currentPit.isMancala && currentPit.player == currentPlayer) {
      turnOver = false;
    } else if (currentPit.isMancala) {
      currentPit = currentPit.get(currentPit.id + 1);
    }

    int prizePit = currentPit.id;
    if (currentPit.player == currentPlayer) {
      if (currentPit.count == 0) {
        Iterator <Pit> iterator = pits.iterator(prizePit);
        while (currentPit.id != pitMap.get(prizePit))
          try {
            currentPit = iterator.next();
          } catch (IndexOutOfBoundsException e) {
            currentPit = pits.get(0);
          }

        stolenAmount = currentPit.count;
        currentPit.count = 0;
        while (currentPit.id != prizePit)
          try {
            currentPit = iterator.next();
          } catch (IndexOutOfBoundsException e) {
            currentPit = pits.get(0);
          }
        currentPit.count = stolenAmount;
      }
    } else {
      currentPit.count += 1;
    }

    notify(turnOver);
  }

  public void notify(boolean turnOver) {
    ChangeEvent e = new ChangeEvent(this);
    for (ChangeListener c : listeners) {
      c.stateChanged(ChangeEvent e);
    }

    if turnOver
      System.out.println("Turn is over -- display Undo / End Turn buttons");
    else
      System.out.println("You ended on your mancala -- take another turn");
  }

  public void registerView(ChangeListener c) {
    listeners.add(c);
  }

  public void undo() {
    if (players[currentPlayer].hasUndo()) {
      pits = undoItems[0];
      players = undoItems[1];
      players[currentPlayer].useUndo();
    } else {
      System.out.println("No more undos left");
    }
  }
}
