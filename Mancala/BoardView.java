/**
 * Creates the mancala game's main window and buttons for determining the
 * stylization of the game board.
 */
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

/**
 * Creates the mancala game's main window and buttons for determining the
 * stylization of the game board.
 */
public class BoardView extends JFrame implements ChangeListener {
  private BoardModel model;
  private BoardPanel board;

  /**
   * Constructs the JFrame enclosing the mancala game board and creates
   * JButtons for the different game parameters.
   * @param BoardModel m
   */
  public BoardView(BoardModel m) {
    super("Mancala Game");
    model = m;
    board = new BoardPanel(model);
    this.setSize(1000, 500);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(new BorderLayout());
    this.setVisible(true);

    JPanel initialSelections = new JPanel();
    JButton red3 = new JButton("Red Styling with 3 Stones");
    JButton red4 = new JButton("Red Styling with 4 Stones");
    JButton green3 = new JButton("Green Styling with 3 Stones");
    JButton green4 = new JButton("Green Styling with 4 Stones");

    red3.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        board.initializeBoard(new RedStrategy());
        model.initializePits(3);
        initialSelections.setVisible(false);
        add(board, BorderLayout.CENTER);
      }
    });
    red4.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        board.initializeBoard(new RedStrategy());
        model.initializePits(4);
        initialSelections.setVisible(false);
        add(board, BorderLayout.CENTER);
      }
    });
    green3.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        board.initializeBoard(new GreenStrategy());
        model.initializePits(3);
        initialSelections.setVisible(false);
        add(board, BorderLayout.CENTER);
      }
    });
    green4.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        board.initializeBoard(new GreenStrategy());
        model.initializePits(4);
        initialSelections.setVisible(false);
        add(board, BorderLayout.CENTER);
      }
    });

    initialSelections.add(red3);
    initialSelections.add(red4);
    initialSelections.add(green3);
    initialSelections.add(green4);

    this.add(initialSelections);
  }

  /**
   * Listens for stateChanged notifications from the BoardModel.
   * @param ChangeEvent e
   */
  public void stateChanged(ChangeEvent e) {
    ArrayList<Pit> pits = board.getPits();
    int id;
    int counter = 0;
    for (int i=0; i < pits.size(); i++) {
      if (i > 6) {
        id = i + 5 - counter;
        counter = counter + 2;
      }
      else
        id = i;
      if (i == 6)
        id = 13;
      pits.get(id).setStoneCount(model.getPits()[i]);
      pits.get(i).repaint();
    }
  }
}
