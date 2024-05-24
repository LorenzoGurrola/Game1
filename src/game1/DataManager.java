package game1;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class DataManager {
    public static void writeToFile(String content, String filePath) {
        try (FileWriter fileWriter = new FileWriter(filePath, false);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.write(content);
        } catch (IOException e) {
            System.out.println("An error occurred while writing to this file:");
            e.printStackTrace();
        }
    }
}
