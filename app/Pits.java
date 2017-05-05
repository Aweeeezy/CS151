import java.util.*;

public class Pits extends UndoItem {
  private LinkedList<Pit> list;

  public Pits() {
    System.out.println("top of Pits constructor");
    list = new LinkedList<Pit>();
    for (int i=0; i < 14; i++) {
      if (i < 6)
        list.add(i, new Pit(i, 3, 0, false));
      else if (i == 7)
        list.add(i, new Pit(i, 3, 0, true));
      else if (i < 13)
        list.add(i, new Pit(i, 3, 1, false));
      else
        list.add(i, new Pit(i, 3, 1, true));
    }
  }

  public Pit get(int pit) {
    return list.get(pit);
  }

  public Iterator<Pit> getIterator(int start) {
    return list.listIterator(start);
  }

  public UndoItem getOld() {
    return this.clone();
  }
}
