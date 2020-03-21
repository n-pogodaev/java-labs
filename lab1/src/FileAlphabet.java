import java.io.*;
import java.util.Scanner;

public class FileAlphabet extends Alphabet {
    public FileAlphabet(String s) {
        super();
        try (Scanner rd = new Scanner(new BufferedInputStream(new FileInputStream(s)))) {
            String[] nextLine;
            while (rd.hasNextLine()) {
                nextLine = rd.nextLine().split(" ");
                putToAlphabet(nextLine[0], nextLine[1]);
            }
        } catch (IOException e) {
            System.err.println("Reading file error: " + e.getLocalizedMessage());
        }
    }
}
