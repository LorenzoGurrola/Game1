package game1;

public class DataPackage {
    private int runs, wins, max, chances, delta;
    private String method;

    public DataPackage(int runs, int wins, int max, int chances, int delta, String method) {
        this.runs = runs;
        this.wins = wins;
        this.max = max;
        this.chances = chances;
        this.delta = delta;
        this.method = method;
    }

    public int getRuns() {
        return runs;
    }

    public int getWins() {
        return wins;
    }

    public int getMax() {
        return max;
    }

    public int getChances() {
        return chances;
    }

    public int getDelta() {
        return delta;
    }

    public String getMethod() {
        return method; 
    }
}
