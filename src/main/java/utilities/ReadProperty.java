package utilities;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadProperty {

    private static final Properties prop = new Properties();

    static {
        try (InputStream input = ReadAPIs.class.getClassLoader().getResourceAsStream("api.properties")) {
            if (input == null) {
                throw new RuntimeException("api.properties file not found in classpath");
            }
            prop.load(input);
        } catch (IOException ex) {
            throw new RuntimeException("Failed to load api.properties", ex);
        }
    }

    public static String getProperty(String key) {
        return prop.getProperty(key);
    }


}
