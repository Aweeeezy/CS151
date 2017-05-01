import java.util.*;

public class Players extends UndoItem {
  private ArrayList<Player> players;

  public Players() {
    players = new ArrayList<Player>(2);
  }

  public Player get(int player) {
    return players.get(player);
  }

  public UndoItem getOld() {
    return super.clone();
  }
}
