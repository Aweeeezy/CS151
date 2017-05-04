import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BoardView extends JFrame {

    JFrame frame;
    JPanel topPanel;
    JPanel bottomPanel;
    StyleStrategy board;
    private ArrayList<Icon> pitCounter;

    public void update(StyleStrategy s){


    }
    public BoardView() {
        pitCounter = new ArrayList<>(14);


        frame = new JFrame("Mancala Game");
        topPanel = new JPanel();
        bottomPanel = new JPanel();
        frame.setLayout(new BorderLayout());


        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(bottomPanel, BorderLayout.SOUTH);


        JButton endTurnButton = new JButton("End Turn");
        JButton undoButton = new JButton("Undo Turn");
    }



    }
}