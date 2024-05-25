package game1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Game {
    private int number;
    private Boolean running = true;
    private Boolean result; //true is a win, false is a loss
    
    private Random rand = new Random();
    private Input input;

    public Boolean run(int max, int chances, String method) {
        if(method == "r") {
            input = new RandomInput(max, chances);
        }
        if(method == "s") {
            input = new StrategicInput(max, chances);
        }
        number = rand.nextInt(max) + 1;
        while(running == true) {
            loop();
        }
        return result;
    }

    private void loop() {
        if(chances == 0) {
            running = false;
            result = false;
            int poop = input.getGuess();
        }
        int guess = Integer.MIN_VALUE;
        if(method == "Random") {
            guess = getRandomGuess();
        }
        else if(method == "Strategic") {
            guess = getStrategicGuess();
        }
        else {
            System.out.println("Invalid method supplied.");
        }
        if(check(guess) == "equals") {
            running = false;
            result = true;
        }
        else {
            chances--;
            if(check(guess) == "higher") {
                feedback.add("higher");
            }
            else if(check(guess) == "lower") {
                feedback.add("lower");
            }
            guesses.add(guess);
        }
        
        System.out.println("Number: " + number);
        System.out.println("Guesses:");
        for (int g : guesses) {
            System.out.println(g);
        }
        System.out.println("Feedback");
        for (String f : feedback) {
            System.out.println(f);
        }
    }

    private String check(int guess) {
        if(guess == number) {
            return "equals";
        }
        else if (guess > number) {
            return "lower";
        }
        return "higher";
    }

    

    
}