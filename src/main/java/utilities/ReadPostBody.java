package utilities;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Map;

public class ReadPostBody {

    private final String endPoint;

    public ReadPostBody(String endPoint) {
        this.endPoint = endPoint;
    }

    public Map<String, String> getJsonBody() {
        try {
            InputStream input = getClass().getClassLoader().getResourceAsStream("postbody.yaml");
            if (input == null) {
                throw new RuntimeException("YAML file not found: postbody.yaml");
            }

            Yaml yaml = new Yaml();
            Map<String, Map<String, String>> postBodies = yaml.load(input);
            Map<String, String> payload = postBodies.get(endPoint);

            return payload;
        } catch (Exception e) {
            throw new RuntimeException("Error reading POST body: " + e.getMessage(), e);
        }
    }
}
