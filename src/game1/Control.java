package game1;

import java.util.List;

public class Control {
    //r = random
    //s = strategic
    public static void main(String[] args) { 
        DataManager.run(1, 10, 2, 3, List.of("s","r"), false);
    }
}
