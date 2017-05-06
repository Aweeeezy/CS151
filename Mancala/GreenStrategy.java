/**
 * Implements StyleStrategy interface's required methods in accordance with the
 * strategy pattern.
 */
import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;

/**
 * Implements StyleStrategy interface's required methods in accordance with the
 * strategy pattern.
 */
public class GreenStrategy implements StyleStrategy {
  /**
   * Creates a pit shape to be displayed on the mancala board.
   * @return Shape Rectangle2D.Double
   */
  public Shape createPit() {
    return new Rectangle2D.Double(0, 0, 100, 100);
  }

  /**
   * Creates a mancala shape to be displayed on the mancala board.
   * @return Shape Rectangle2D.Double
   */
  public Shape createMancala() {
    return new Rectangle2D.Double(0, 0, 100, 500);
  }

  /**
   * Returns the color that this strategy specialized in.
   * @return Color Color.GREEN
   */
  public Color getColor() {
    return Color.GREEN;
  }
}
