package game1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class DataManager {
    private static List<DataPackage> = 
    public static void run(int runs, int max, int chances, int delta, List<String> methods, Boolean debug) {
        for (String method : methods) {
            System.out.println("method is " + method);
            int wins = 0;
            for (int index = 0; index < runs; index++) {
                Game game = new Game();
                Boolean result = game.run(max, chances, method, debug);
                    if(result) {
                        wins++;
                    }
            }
            record(runs, wins, delta, method);
        }
        
    }

    private static void record(int runs, int wins, int delta, String methods) {
        double winRate = createWinRate(runs, wins, delta);
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

    private static double createWinRate(int runs, int wins, int delta) {
        double winRate = (double)wins/(double)runs;
        double round = Math.pow(10, delta);
        winRate = Math.round(winRate * round)/round;
        return winRate;
    }

    private static String createFileTitle(String methods, String date) {
        int stamp = readIntFromFile("res/stamp.txt");
        String title =  stamp + methods + ", " + date;
        writeToFile(stamp+1 + "", "res/stamp.txt", false);
        return title;
    }

    private static String createFileContent(String date, String time, String method, int runs, int wins, double winRate) {
        String content = "Date: " + date
                       + "\nTime: " + time
                       + "\nMethod: " + method
                       + "\nRuns: " + runs
                       + "\nWins: " + wins
                       + "\nWin Rate: " + winRate;
        return content;
    }
}
