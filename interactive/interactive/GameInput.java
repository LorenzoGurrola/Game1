package interactive;

import java.util.Random;
import java.util.Scanner;

public class GameInput {
    private int chances = 3;
    private Random rand = new Random();
    private Scanner scanner = new Scanner(System.in);
    private int number;
    private Boolean running = true;

    public GameInput() {
        run();
    }
    public static void main(String[] args) {
        GameInput gameInput = new GameInput();
    }

    private void run() {
        System.out.println("Welcome to the game.");
        number = rand.nextInt(10) + 1;
        while(running) {
            loop();
        }
    }

    private void loop() {
        System.out.println("Guess the number (1-10): (" + chances + " chances remain)");
        int guess = scanner.nextInt();
        if(check(guess)) {
            System.out.println("Congrats! " + number + " was the number to guess!");
            running = false;
        }
        else {
            chances--;
            if(chances == 0) {
                System.err.println("You lose! The number was " + number);
                running = false;
            }
        }
    }

    private Boolean check(int guess) {
        if(guess == number) {
            return true;
        }
        else if(guess > number) {
            System.out.println("The number is lower than " + guess);
        }
        else if(guess < number) {
            System.out.println("The number is higher than " + guess);
        }
        return false;
    }
}