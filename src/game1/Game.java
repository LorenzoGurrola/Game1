package game1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Game {
    private Boolean debug;
    private int number;
    private int chances;
    private Boolean result = false; //true is a win, false is a loss
    
    private Random rand = new Random();
    private Input input;

    public Boolean run(int max, int chances, String method, Boolean debug) {
        this.debug = debug;
        number = rand.nextInt(max) + 1;

        debugPrint("\n\nNumber set to: " + number);
        
        this.chances = chances;
        if(method == "r") {
            input = new RandomInput(max, debug);
        }
        else if(method == "s") {
            input = new StrategicInput(max, debug);
        }
        else {
            System.out.println("Invalid method supplied.");
        }
        
        while(result == false && this.chances > 0) {
            loop();
        }
        debugPrint("Finished with a result of " + result);
        return result;
    }

    private void loop() {
        int guess = input.getGuess();
        String feedback = check(guess);
        input.setFeedback(feedback);
        if(feedback == "equals") {
            result = true;
        }
        chances--;
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

    private void debugPrint(String message) {
        if(debug) {
            System.err.println(message);
        }
    }
}