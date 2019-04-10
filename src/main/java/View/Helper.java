package View;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Helper {
    public static class FileManager {

        public String readFile(String fileName) throws FileNotFoundException {
            File file = new File(fileName);

            Scanner scanner;
            scanner = new Scanner(file);

            scanner.useDelimiter("\\Z");

            return scanner.next();
        }
    }
}
