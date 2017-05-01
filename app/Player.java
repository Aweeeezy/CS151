public class Player {

    public Player(){
        this.score = 0;
        this.undosRemaining = 3;
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean hasUndo(){
        return this.undosRemaining > 0;
    }

    public void useUndo(){
        try {
            this.undosRemaining -= 1;
        } catch (Exception e){
            System.out.println("No more available undos");
        }
    }
    private int score;
    private int undosRemaining;

}
