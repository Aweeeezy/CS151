import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.*;

public class MancalaTester {
  public static void main(String[] args) {
    BoardModel model = new BoardModel();
    BoardView view = new BoardView();
    BoardController controller = new BoardController();
    JPanel buttonPanel = new JPanel();

    JButton stripedButton = new JButton("Striped Layout");
    JButton solidButton = new JButton("Solid Layout");
    stripedButton.setVisible(true);
    solidButton.setVisible(true);

    buttonPanel.add(stripedButton);
    buttonPanel.add(solidButton);

    view.add(buttonPanel);

    model.registerView(new ChangeListener() {
      public void stateChanged(ChangeEvent e) {
        System.out.println("State changed");
        view.update();
      }
    });

  }
}
