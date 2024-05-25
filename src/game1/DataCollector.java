package game1;

import java.time.LocalDate;
import java.time.LocalTime;

public class DataCollector {

    private static void run(int runs, int max, int chances, String method) {
        int wins = 0;
        for (int index = 0; index < runs; index++) {
            Game game = new Game();
            Boolean result = game.run(max, chances, method);
            if(result) {
                wins++;
            }
        }
        record(runs, wins, method);
    }

    private static void record(int runs, int wins, String method) {
        double winRate = (double)wins/(double)runs;
        double e = Math.pow(10, 4);
        winRate = Math.round(winRate * e)/e;
        
        String date = LocalDate.now().toString();
        String time = LocalTime.now().toString();

        String title =  method + "," + date + ", " + time;
        String content = "Date: " + date
                     + "\nTime: " + time
                     + "\nMethod: " + method
                     + "\nRuns: " + runs
                     + "\nWins: " + wins
                     + "\nWin Rate: " + winRate;

        DataManager.writeToFile(content, "data/" + title); 
    }

    public static void main(String[] args) {
        //run(1000000, 10, 3, "Random");
        run(1, 13, 3, "Strategic");
    }
}
