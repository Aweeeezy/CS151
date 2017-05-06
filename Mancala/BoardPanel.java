/**
 * Defines the layout of the mancala game board.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

/**
 * Defines the layout of the mancala game board.
 */
public class BoardPanel extends JPanel {
  private ArrayList<Pit> pits;
  private JPanel pitGrid;
  private BoardModel model;
  private JButton undo;

  /**
   * Constructs BoardPanel with a BorderLayout and fits a game board formatted
   * as a GridLayout into the center. Also initializes an undo button and an
   * ArrayList of pits that are displayed on the game board.
   * @param BoardModel m
   */
  public BoardPanel(BoardModel m) {
    super(new BorderLayout());
    model = m;
    pitGrid = new JPanel(new GridLayout(2, 6, 0, 295));
    pits = new ArrayList<Pit>();
    undo = new JButton("Undo Turn");
    undo.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        model.undo();
      }
    });
  }

  /**
   * Creates pits for both mancalas and all 12 actual pits while attaching
   * MouseListeners to those 12 pits.
   * @param StyleStrategy s -- determines the stylization of the board's
   * components.
   */
  public void initializeBoard(StyleStrategy s) {
    Pit mancalaA = new Pit(s, true);
    Pit mancalaB = new Pit(s, true);
    this.add(undo, BorderLayout.NORTH);
    this.add(mancalaA, BorderLayout.WEST);
    this.add(pitGrid, BorderLayout.CENTER);
    this.add(mancalaB, BorderLayout.EAST);
    int counter = 0;
    for (int i=0; i < 14; i++) {
      if (i == 6)
        pits.add(mancalaA);
      else if (i == 13)
        pits.add(mancalaB);
      else {
        int id;
        if (i > 6) {
          id = i + 5 - counter;
          counter = counter + 2;
        }
        else
          id = i;
        Pit pit = new Pit(s, false);
        pit.addMouseListener(new MouseAdapter() {
          public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);
            if ((id < 6 && model.getCurrentPlayer() == 0) || (id > 6 && model.getCurrentPlayer() == 1))
              model.takeTurn(id);
          }
        });
        pitGrid.add(pit);
        pits.add(pit);
      }
    }
  }

  /**
   * Yields an ArrayList of pits.
   * @return ArrayList<Pit> pits
   */
  public ArrayList<Pit> getPits() {
    return pits;
  }
}
