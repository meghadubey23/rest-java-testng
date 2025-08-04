package utilities;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.stream.Stream;

public class ReadExpectedResponses {

    private static final Properties expectedProps = new Properties();

    static {
        try (InputStream input = ReadExpectedResponses.class.getClassLoader()
                .getResourceAsStream("expectedresponses/expected_responses.properties")) {
            if (input == null) {
                throw new RuntimeException("expected_responses.properties file not found in classpath");
            }
            expectedProps.load(input);
        } catch (IOException ex) {
            throw new RuntimeException("Failed to load expected_responses.properties", ex);
        }
    }

    public static String getExpectedResponse(String key) {
        return expectedProps.getProperty(key);
    }

    public static String getSingleUserExpectedResponse() {
        return getExpectedResponse("resp_single_user");
    }

    public static String getUserListExpectedResponse() {
        return getExpectedResponse("resp_user_list");
    }

    public static String getUserLoginResponse() {
        return getExpectedResponse("resp_login");
    }

    public static String getFailedUserLoginResponse() {
        return getExpectedResponse("resp_login_failed");
    }

    public static String getSingleResourceResponse() {
        return getExpectedResponse("resp_single_resource");
    }

    public static Stream<String> singleUserJsonProvider() {
        return Stream.of(getSingleUserExpectedResponse());
    }

    public static Stream<String> userListJsonProvider() {
        return Stream.of(getUserListExpectedResponse());
    }

    public static Stream<String> userLoginJsonProvider() {
        return Stream.of(getUserLoginResponse());
    }

    public static Stream<String> failedUserLoginJsonProvider() {
        return Stream.of(getFailedUserLoginResponse());
    }

}
