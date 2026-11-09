package project03cs2114;
public class ScoreManager
{
    //~ Fields ................................................................
    private int scores;

    //~ Constructors ..........................................................
    public ScoreManager() {
        scores = 0;       
    }
    
    public void addPoints(int points) {
        if(points>=0) {
        scores += points;
    }
    }
    
    public void subtractPoints(int points) {
        if(points >= 0) {
        scores -= points;
        }
    }
    public void updateScore(int points) {
        scores = points;
    }
    public int getScore() {
        return scores;
    }
    public void resetScore() {
        scores = 0;
    }
    //~Public  Methods ........................................................

}
