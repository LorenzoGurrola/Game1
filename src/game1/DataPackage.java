package game1;

public class DataPackage {
    int runs, wins, delta;
    String method;

    public DataPackage(int runs, int wins, int delta, String method) {
        this.runs = runs;
        this.wins = wins;
        this.delta = delta;
        this.method = method;
    }
}
