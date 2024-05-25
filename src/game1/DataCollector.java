package game1;

import java.time.LocalDate;
import java.time.LocalTime;

public class DataCollector {

    private static void run(int runs, int max, int chances, String method, Boolean debug) {
        int wins = 0;
        for (int index = 0; index < runs; index++) {
            Game game = new Game();
            Boolean result = game.run(max, chances, method, debug);
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

    //r = Random, s = Strategic
    public static void main(String[] args) { 
        //run(1, 10, 3, "r");
        run(1, 10, 3, "r", true);
    }
}
