package game1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

public class DataManager {
    public static void run(int runs, int max, int chances, int decimalPlacesInReport, String methods, Boolean debug) {
        int wins = 0;
        for (int index = 0; index < runs; index++) {
            Game game = new Game();
            Boolean result = game.run(max, chances, methods, debug);
            if(result) {
                wins++;
            }
        }
        record(runs, wins, decimalPlacesInReport, methods);
    }

    private static void record(int runs, int wins, int decimalPlaces, String methods) {
        double winRate = createWinRate(runs, wins, decimalPlaces);
        String date = LocalDate.now().toString();
        String time = LocalTime.now().toString();
        String title = createFileTitle(methods, date);
        String content = createFileContent(date, time, methods, runs, wins, winRate);
        DataManager.writeToFile(content, "data/" + title, true); 
    }
    
    private static void writeToFile(String content, String filePath, Boolean append) {
        try (FileWriter fileWriter = new FileWriter(filePath, append);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.write(content);
        } catch (IOException e) {
            System.out.println("An error occurred while writing to this file:");
            e.printStackTrace();
        }
    }

    public static int readIntFromFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = reader.readLine();
            return Integer.parseInt(line);
        } catch (IOException | NumberFormatException e) {
            System.out.println("An error occurred while loading the integer.");
            e.printStackTrace();
            return -1;
        }
    }

    private static double createWinRate(int runs, int wins, int decimalPlaces) {
        double winRate = (double)wins/(double)runs;
        double round = Math.pow(10, decimalPlaces);
        winRate = Math.round(winRate * round)/round;
        return winRate;
    }

    private static String createFileTitle(String methods, String date) {
        int stamp = readIntFromFile("res/stamp.txt");
        String title =  stamp + methods + ", " + date;
        writeToFile(stamp+1 + "", "res/stamp.txt", false);
        return title;
    }

    private static String createFileContent(String date, String time, String methods, int runs, int wins, double winRate) {
        String content = "Date: " + date
                       + "\nTime: " + time
                       + "\nMethods: " + methods
                       + "\nRuns: " + runs
                       + "\nWins: " + wins
                       + "\nWin Rate: " + winRate;
        return content;
    }
}
