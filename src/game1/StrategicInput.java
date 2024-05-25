package game1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StrategicInput implements Input{
    private boolean debug = false;
    private List<Integer> guesses = new ArrayList<>();
    private List<String> feedback = new ArrayList<>();
    private List<Integer> vaildGuesses = new ArrayList<>();
    private Random rand = new Random();

    public StrategicInput(int max, Boolean debug) {
        this.debug = debug;
        for (int index = 1; index <= max; index++) {
            vaildGuesses.add(index);
        }
        debugPrint("Valid guesses are: " + vaildGuesses);
    }
    
    @Override
    public int getGuess() {     
        int guessIndex = getGuessIndex();
        int guess = vaildGuesses.get(guessIndex);
        debugPrint("Guessing " + guess + " via the StrategicInput method.");
        guesses.add(guess);
        return guess;
    }

    @Override
    public void setFeedback(String currentFeedback) {
        if(currentFeedback == "equals") {
            return;
        }
        feedback.add(currentFeedback);
        int lastGuess = guesses.get(guesses.size() - 1);
        List<Integer> invaildGuesses = new ArrayList<>();
        if(currentFeedback == "higher") {
            for (int g : vaildGuesses) {
                if(g <= lastGuess) {
                    invaildGuesses.add(g);
                }
            }
        }
        else if(currentFeedback == "lower") {
            for (int g : vaildGuesses) {
                if(g >= lastGuess) {
                    invaildGuesses.add(g);
                }
            }
        }
        for (int g : invaildGuesses) {
            vaildGuesses.removeIf(n -> n == g);
        }
        debugPrint("Due to the feedback '" + currentFeedback + "' on the last guess '" 
                         + lastGuess + "', numbers " + invaildGuesses + " \nhave been removed the valid guesses list, leaving "
                         + vaildGuesses);
    }

    private int getGuessIndex() {
        int size = vaildGuesses.size();
        if(isOdd(size)) {
            int index = size/2;
            debugPrint("The size of the validGuesses list is " + size + 
            " which is an odd number, meaning we choose the index" + size/2);
            return index;
        }
        else {
            int r = rand.nextInt(2);
            int index = size/2 - r;
            debugPrint("The size of the validGuesses list is " + size + 
            " which is an even number, meaning we choose the index " + index +
            " after randomly choosing either 0 and 1, which was " + r +
            " and then subtracting this number from half of the size, which was " + size/2);
            return index; //returns one of the two middle options, randomly
        }
    }

    private Boolean isOdd(int n) {
        return n % 2 == 1;
    }

    @Override
    public void debugPrint(String message) {
        if(debug) {
            System.err.println(message);
        }
    }
}
