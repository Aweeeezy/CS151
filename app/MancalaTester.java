import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.*;
import java.util.*;

public class MancalaTester {
  public static void main(String[] args) {
    JFrame frame;
    BoardModel model = new BoardModel();
    BoardController controller = new BoardController(model);
    ArrayList<JButton> buttons = new ArrayList<JButton>();

    JPanel top = new JPanel();
    JPanel bottom = new JPanel();

    model.registerView(new ChangeListener() {
      public void stateChanged(ChangeEvent e) {
        System.out.println("State changed");
      }
    });

    frame = new JFrame("Mancala Game");
    frame.setLayout(new BorderLayout());
    frame.setSize(700, 700);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JButton solidButton  = new JButton("Solid Style Layout");
    JButton stripedButton = new JButton("Striped Style Layout");

    solidButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        StyleStrategy s = new SolidStrategy();
        BoardPanel board = new BoardPanel(s);
        solidButton.setVisible(false);
        stripedButton.setVisible(false);
        for (int i=0; i < 12; i++) {
          JButton b = new JButton();
          b.addActionListener(controller.getListener(i));
          b.setPreferredSize(new Dimension(100, 100));
          buttons.add(b);
          if (i < 6)
            top.add(b);
          else
            bottom.add(b);
        }
        frame.getContentPane().add(top, BorderLayout.NORTH);
        frame.getContentPane().add(bottom, BorderLayout.SOUTH);
      }
    });

    stripedButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        StyleStrategy s = new StripedStrategy();
        BoardPanel board = new BoardPanel(s);
        solidButton.setVisible(false);
        stripedButton.setVisible(false);
        for (int i=0; i < 12; i++) {
          JButton b = new JButton("ID: " + i);
          b.addActionListener(controller.getListener(i));
          b.setPreferredSize(new Dimension(100, 100));
          b.setSize(500, 500);
          buttons.add(b);
          if (i < 6)
            top.add(b);
          else
            bottom.add(b);
        }
        frame.getContentPane().add(top, BorderLayout.NORTH);
        frame.getContentPane().add(bottom, BorderLayout.SOUTH);
      }
    });

    JPanel buttonPanel = new JPanel();
    buttonPanel.add(solidButton);
    buttonPanel.add(stripedButton);
    frame.add(buttonPanel);
    frame.setVisible(true);

  }
}
