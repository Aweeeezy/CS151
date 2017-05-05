import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.util.*;


public class BoardController {
  ArrayList<ActionListener> pitListeners;
  ActionListener endTurn;
  ActionListener undo;
  BoardModel gameState;

  public BoardController() {
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

    gameState = new BoardModel();

    pitListeners = new ArrayList<ActionListener>();
    for (int i=0; i<12; i++) {
      int id;
      if (i > 5)
        id = i+1;
      else
        id = i;
      pitListeners.add(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          gameState.takeTurn(id);
        }
      });
    }

  }
}
