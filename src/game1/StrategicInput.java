package game1;

import java.util.ArrayList;
import java.util.List;

public class StrategicInput implements Input{
    private int max;
    private int chances;
    private List<Integer> guesses = new ArrayList<>();
    private List<String> feedback = new ArrayList<>();
    private List<Integer> vaildGuesses = new ArrayList<>();

    public StrategicInput(int max, int chances) {
        this.max = max;
        this.chances = chances;
    }
    
    @Override
    public int getGuess() {
        if(guesses.isEmpty()) {
            return max/2;
        }
        else {
            return max/2;
        }
    }

    @Override
    public void setFeedback() {
        
    }
}
