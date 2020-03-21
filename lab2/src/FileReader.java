import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileReader {
    public static String ReadFile(String fileName) {
        StringBuilder result = new StringBuilder();
        BufferedReader reader = null;
        try {
            if (fileName.isEmpty()) {
                reader = new BufferedReader(new InputStreamReader(System.in));
            }
            else {
                reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
            }
            while (reader.ready()) {
                result.append(reader.readLine());
                result.append("\n");
            }
            result.deleteCharAt(result.length() - 1);
        } catch (IOException e) {
            System.err.println("Error while reading file: " + e.getLocalizedMessage());
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace(System.err);
                }
            }
        }
        return result.toString();
    }
}
