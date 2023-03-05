package Model;

import java.io.FileWriter;
import java.io.IOException;

public class FileWriterToFileTxt<T> {

    public void writeToFileTxt(String fileName, T data) {
        try (FileWriter fw = new FileWriter("prizes.txt", true)) {
            fw.write(data.toString() + "\n");
        } catch (IOException e) {
            e.getStackTrace();
        }
    }

}
