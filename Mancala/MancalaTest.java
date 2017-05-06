public class MancalaTest {
  public static void main(String[] args) {
    BoardModel model = new BoardModel();
    BoardView view = new BoardView(model);
    model.registerView(view);
  }
}
