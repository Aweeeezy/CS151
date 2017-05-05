import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.util.*;


public class BoardController {
  ArrayList<ActionListener> pitListeners;
  ActionListener endTurn;
  ActionListener undo;
  BoardModel gameState;

  public BoardController(BoardModel m) {
    gameState = m;

    endTurn = new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        gameState.changePlayer();
      }
    };

    undo = new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        gameState.undo();
      }
    };

    pitListeners = new ArrayList<ActionListener>();
    for (int i=0; i<12; i++) {
      int id;
      if (i > 5)
        id = i+1;
      else
        id = i;
      pitListeners.add(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          System.out.println("Clicking pit " + id);
          gameState.takeTurn(id);
        }
      });
    }
  }

  public ActionListener getListener(int i) {
    return pitListeners.get(i);
  }
}
