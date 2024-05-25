package game1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomInput implements Input{
    private boolean debug;
    private int max;
    private List<Integer> guesses = new ArrayList<>();
    private Random rand = new Random();

    public RandomInput(int max, Boolean debug) {
        this.max = max;
        this.debug = debug;
    }
    
    @Override
    public int getGuess() {
        int guess = rand.nextInt(max) + 1;  
        while(guesses.contains(guess)) {
            debugPrint("Was going to guess " + guess + " but we already guessed that.");
            guess = rand.nextInt(max) + 1;
        }
        guesses.add(guess);

        debugPrint("Guessing " + guess + " via the RandomInput method.");
        return guess; 
    }

    @Override
    public void setFeedback(String feedback) {
        //RandomInput does not need feedback
    }

    @Override
    public void debugPrint(String message) {
        if(debug) {
            System.err.println(message);
        }
    }
}
