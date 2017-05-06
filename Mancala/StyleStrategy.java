/**
 * Interface that defines methods that classes must implement in accordance
 * with the strategy pattern.
 */
import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;

/**
 * Interface that defines methods that classes must implement in accordance
 * with the strategy pattern.
 */
public interface StyleStrategy {
  public Shape createPit();
  public Shape createMancala();
  public Color getColor();
}
