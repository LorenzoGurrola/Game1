package game1;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Game {
    private int max;
    private int chances;
    private int number;
    private Set<Integer> guesses = new HashSet<>();

    private Random rand = new Random();
    private Boolean running = true;
    private Boolean result; //true is a win, false is a loss

    private String method;

    public Boolean run(int max, int chances, String method) {
        this.max = max;
        this.chances = chances;
        this.method = method;
        number = rand.nextInt(max) + 1;
        while(running == true) {
            loop();
        }
        return result;
    }

    private void loop() {
        int guess = -100;
        if(method == "Random") {
            guess = getRandomGuess();
        }
        else if(method == )

        guesses.add(guess);
        if(check(guess)) {
            running = false;
            result = true;
        }
        else {
            chances--;
            if(chances == 0) {
                running = false;
                result = false;
            }
        }
    }

    private Boolean check(int guess) {
        if(guess == number) {
            return true;
        }
        return false;
    }

    private int getRandomGuess() {
        int guess = rand.nextInt(max) + 1;  
        while(guesses.contains(guess)) {
            guess = rand.nextInt(max) + 1;
        }
        return guess;
    }
}