package game1;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class DataCollector {

    public DataCollector(int runs) {
        int wins = 0;
        for (int index = 0; index < runs; index++) {
            GameRandom gameRandom = new GameRandom();
            Boolean result = gameRandom.run(10, 3);
            if(result) {
                wins++;
            }
        }
        record(runs, wins);
    }

    private void record(int runs, int wins) {
        double winRate = (double)runs/(double)wins;
        double e = Math.pow(10, 4);
        winRate = Math.round(winRate * e)/e;
        
        String date = LocalDate.now().toString();
        String time = LocalTime.now().toString();

        String title =  date + ", " + time;
        String content = "Date: " + date
                     + "\nTime: " + time
                     + "\nRuns: " + runs
                     + "\nWins: " + wins
                     + "\nWin Rate: " + winRate;

        DataManager.writeToFile(content, "data/" + title);
    }

    public static void main(String[] args) {
        DataCollector dataCollector = new DataCollector(1000);
    }
}
