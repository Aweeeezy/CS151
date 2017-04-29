public class Players extends UndoElement {
  private ArrayList<Player> players;

  public Players() {
    players = new ArrayList<Player>(2);
  }

  public <T> getOld() {
    return (UndoElement) players.clone();
  }
}
