package game1;

public class DataCollector {
    private int runs;
    private int wins = 0;

    public DataCollector(int runs) {
        this.runs = runs;
        GameRandom gameRandom = new GameRandom();
        for (int index = 0; index < runs; index++) {
            Boolean win = gameRandom.run(10, 4);
            if(win) {
                wins++;
            }
        }
        System.out.println(runs + " runs, " + wins + " wins.");
    }

    public static void main(String[] args) {
        DataCollector dataCollector = new DataCollector(100);
    }
}
