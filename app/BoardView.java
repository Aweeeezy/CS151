import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class BoardView extends JFrame {
    JFrame frame;
    private ArrayList<Icon> pitCounter;

    public void update(){
    }

    public BoardView() {
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
          }
        });

        stripedButton.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            StyleStrategy s = new StripedStrategy();
            BoardPanel board = new BoardPanel(s);
            solidButton.setVisible(false);
            stripedButton.setVisible(false);
          }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(solidButton);
        buttonPanel.add(stripedButton);
        frame.add(buttonPanel);
        frame.setVisible(true);
    }
}
