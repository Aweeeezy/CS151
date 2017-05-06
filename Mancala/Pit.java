/**
 * The Pit class is a JComponent that is displayed on the mancala game board.
 */
import java.awt.*;
import javax.swing.*;
import java.util.Random;
import java.awt.geom.Ellipse2D;

/**
 * The Pit class is a JComponent that is displayed on the mancala game board.
 */
public class Pit extends JComponent {
  StyleStrategy style;
  int numStones = 0;
  boolean isMancala;

  /**
   * Constructs a Pit object.
   * @param StyleStrategy s -- determines the stylization of this pit.
   * @param boolean m -- determines if this pit is a mancala or not.
   */
  public Pit(StyleStrategy s, boolean m) {
    style = s;
    isMancala = m;
  }

  /**
   * Sets the number of stones to be drawn in this pit.
   * @param int c -- the number of stones to draw.
   */
  public void setStoneCount(int c) {
    numStones = c;
  }

  /**
   * Yields the preferred size of this JComponent.
   * @return Dimension
   */
  public Dimension getPreferredSize() {
    return new Dimension(100, 100);
  }

  /**
   * Paints this JComponent and draws stones inside of it.
   * @param Graphics g
   */
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;
    g2.setColor(style.getColor());
    Shape pit;
    if (isMancala)
      pit = style.createMancala();
    else
      pit = style.createPit();
    g2.fill(pit);
    g2.draw(pit);

    for (int i = 0; i < numStones; i++) {
			Ellipse2D.Double stone = new Ellipse2D.Double(
					new Random().nextDouble() * (style.createPit().getBounds().width - 2 * 10),
					new Random().nextDouble() * (style.createPit().getBounds().height - 2 * 10),
					10, 10);
			g2.setColor(Color.BLACK);
			g2.fill(stone);
			g2.draw(stone);
		}
  }
}
