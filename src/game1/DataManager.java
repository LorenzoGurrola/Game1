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
    private List<DataPackage> dataPackages = new ArrayList<>();
    private List<String> methods = new ArrayList<>();

    public DataManager(int runs, int max, int chances, int delta, List<String> methods, Boolean debug) {
        this.methods = methods;
        run(runs, max, chances, delta, methods, debug);
    }

    public void run(int runs, int max, int chances, int delta, List<String> methods, Boolean debug) {
        for (String method : methods) {
            int wins = 0;
            for (int index = 0; index < runs; index++) {
                Game game = new Game();
                Boolean result = game.run(max, chances, method, debug);
                    if(result) {
                        wins++;
                    }
            }
            dataPackages.add(new DataPackage(runs, wins, max, chances, delta, method));
        }
        record();
    }

    private void record() {
        String date = LocalDate.now().toString();
        String title = createFileTitle(date);
        for (DataPackage dataPackage : dataPackages) {
            int runs = dataPackage.getRuns();
            int wins = dataPackage.getWins();
            int max = dataPackage.getMax();
            int chances = dataPackage.getChances();
            int delta = dataPackage.getDelta();
            String method = dataPackage.getMethod();
            double winRate = createWinRate(runs, wins, delta);
            String time = LocalTime.now().toString();
            String content = createFileContent(date, time, method, runs, wins, winRate, max, chances);
            writeToFile(content, "data/" + title, true); 
        }
        
    }
    
    private void writeToFile(String content, String filePath, Boolean append) {
        try (FileWriter fileWriter = new FileWriter(filePath, append);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.write(content);
        } catch (IOException e) {
            System.out.println("An error occurred while writing to this file:");
            e.printStackTrace();
        }
    }

    public int readIntFromFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = reader.readLine();
            return Integer.parseInt(line);
        } catch (IOException | NumberFormatException e) {
            System.out.println("An error occurred while loading the integer.");
            e.printStackTrace();
            return -1;
        }
    }

    private double createWinRate(int runs, int wins, int delta) {
        double winRate = (double)wins/(double)runs;
        double round = Math.pow(10, delta);
        winRate = Math.round(winRate * round)/round;
        return winRate;
    }

    private String createFileTitle(String date) {
        int stamp = readIntFromFile("res/stamp.txt");
        String methodsString = createMethodsString();
        String title =  stamp + methodsString + ", " + date + ".txt";
        writeToFile(stamp+1 + "", "res/stamp.txt", false);
        return title;
    }

    private String createFileContent(String date, String time, String method, int runs, int wins, double winRate, int max, int chances) {
        String content = "RANDOM NUMBER GAME STATS"
                       + "\nDate: " + date
                       + "\nTime: " + time
                       + "\nMethod: " + method
                       + "\nRuns: " + runs
                       + "\nWins: " + wins
                       + "\nWin Rate: " + winRate
                       + "\nMax: " + max
                       + "\nChances: " + chances
                       + "\n-----\n";
        return content;
    }

    private String createMethodsString() {
        String methodsString = "";
        for (String method : methods) {
            methodsString += method;
        }
        return methodsString;
    }
}
