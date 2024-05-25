package game1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomInput implements Input{
    private int max;
    private int chances;
    private List<Integer> guesses = new ArrayList<>();
    private List<String> feedback = new ArrayList<>();
    private Random rand = new Random();

    public RandomInput(int max, int chances) {
        this.max = max;
        this.chances = chances;
    }
    
    @Override
    public int getGuess() {
        int guess = rand.nextInt(max) + 1;  
        while(guesses.contains(guess)) {
            guess = rand.nextInt(max) + 1;
        }
        return guess; 
    }

    @Override
    public void setFeedback() {
    }
}
