package game1;

public class Control {
    //r = random
    //s = strategic
    public static void main(String[] args) { 
        for (int index = 0; index < 100; index++) {
            DataManager.run(100, 10, 2, 3, "r", false);
        }
        
    }
}
