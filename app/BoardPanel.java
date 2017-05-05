/**
 * Created by brandonabajelo on 5/3/17.
 */
public class BoardPanel {

  public BoardPanel(StyleStrategy s) {
    StyleStrategy strategy = (StyleStrategy) s;
    String source = strategy.defineStrategy();
    System.out.println(source);
  }

}
