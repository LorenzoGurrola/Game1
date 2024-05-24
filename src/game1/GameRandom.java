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
    

    private GameRandom(int max, int chances) {
        this.max = max;
        this.chances = chances;
        run();
    }

    private void run() {
        number = rand.nextInt(max) + 1;
        while(running == true) {
            loop();
        }
    }

    private void loop() {
        int guess = rand.nextInt(max) + 1;  
        while(guesses.contains(guess)) {
            guess = rand.nextInt(max) + 1;
        }
        guesses.add(guess);
        if(check(guess)) {
            running = false;
        }
        else {
            chances--;
            if(chances == 0) {
                running = false;
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