import javax.swing.event.*;

public class MancalaTester {
  public static void main(String[] args) {
    BoardModel model = new BoardModel();
    BoardView view = new BoardView();
    BoardController controller = new BoardController();
    StyleStrategy strategy;

    JButton stripedButton = new JButton("Striped Layout");
    JButton solidButton = new JButton("Solid Layout");

    stripedButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        strategy = new StripedStrategy();
      }
    });

    solidButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        strategy = new SolidStrategy();
      }
    });

    model.registerView(new ChangeListener() {
      public void stateChanged(ChangeEvent e) {
        System.out.println("State changed");
        view.board.update(strategy);
      }
    });
  }
}
