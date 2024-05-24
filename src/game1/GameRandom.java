package game1;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class GameRandom {
    private int max;
    private int chances = 3;
    private int number;
    private Set<Integer> guesses = new HashSet<>();

    private Random rand = new Random();
    private Boolean running = true;
    private Boolean result; //true is a win, false is a loss

    public Boolean run(int max, int chances) {
        this.max = max;
        this.chances = chances;
        number = rand.nextInt(max) + 1;

        //System.out.println("max: " + max + " chances: " + chances + " number is " + number);

        while(running == true) {
            loop();
        }

        //System.out.println("finished. Result is " + result);

        return result;
    }

    private void loop() {
        int guess = rand.nextInt(max) + 1;  
        while(guesses.contains(guess)) {
            //System.out.println("Wanted to guess " + guess + " but already guessed that.");
            guess = rand.nextInt(max) + 1;
        }
        
        // System.out.println("Guess is " + guess);
        // System.out.println("Already guessed:");
        // for (int i : guesses) {
        //     System.out.println(i);
        // }

        guesses.add(guess);
        if(check(guess)) {
            running = false;
            result = true;
        }
        else {
            chances--;

            //System.out.println("remaining chances = " + chances);

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
}