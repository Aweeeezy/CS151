public class Pit {

    public Pit(int id, int count, boolean player, boolean isMancala) {
        this.id = id;
        this.count = count;
        this.player = player;
        this.isMancala = isMancala;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isPlayer() {
        return player;
    }

    public void setPlayer(boolean player) {
        this.player = player;
    }

    public boolean isMancala() {
        return isMancala;
    }

    private int id;
    private int count;
    private boolean player;
    private boolean isMancala;

}