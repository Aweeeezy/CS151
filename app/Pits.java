public class Pits extends UndoItems {
  private LinkedList<Pit> list;

  public Pits() {
    list = new LinkedList<Pit>();
  }

  public <T> getOld() {
    return this.clone();
  }
}
