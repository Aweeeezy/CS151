import java.util.*;

public class Pits extends UndoItem {
  private LinkedList<Pit> list;

  public Pits() {
    list = new LinkedList<Pit>();
  }

  public Pit get(int pit) {
    return list.get(pit);
  }

  public Iterator<Pit> getIterator(int start) {
    return list.listIterator(start);
  }

  public UndoItem getOld() {
    return super.clone();
  }
}
