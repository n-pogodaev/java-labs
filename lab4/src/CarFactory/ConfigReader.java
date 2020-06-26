package CarFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    public static Properties readConfig() {
        Properties props = new Properties();
        try (InputStream is = ConfigReader.class.getResourceAsStream("factory.properties")) {
            props.load(is);
        } catch (IOException e) {
            System.err.println("can't read from properties file");
        }
        return props;
    }
}
