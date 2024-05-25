package game1;

import java.io.BufferedWriter;
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
        record(runs, wins, decimalPlacesInReport methods);
    }

    private static void record(int runs, int wins, int decimalPlaces, String methods) {
        double winRate = createWinRate(runs, wins, decimalPlaces);
        
        String date = LocalDate.now().toString();
        String time = LocalTime.now().toString();

        String title =  methods + "," + date + ", " + time;
        String content = "Date: " + date
                     + "\nTime: " + time
                     + "\nMethods: " + methods
                     + "\nRuns: " + runs
                     + "\nWins: " + wins
                     + "\nWin Rate: " + winRate;

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

    private static double createWinRate(int runs, int wins, int decimalPlaces) {
        double winRate = (double)wins/(double)runs;
        double round = Math.pow(10, decimalPlaces);
        winRate = Math.round(winRate * round)/round;
        return winRate;
    }
}
