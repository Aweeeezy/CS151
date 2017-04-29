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

    public int getUndosRemaining(){
        return this.undosRemaining;
    }

    public void useUndo(){
        try {
            this.undosRemaining -= 1;
        } catch (Exception e){
            System.out.println("No more available undos");
        }
    }

    public boolean hasUndo(){
        return undosRemaining <=3;
    }
    private int score;
    private int undosRemaining;


}
