package utilities;

import exceptions.*;
import io.restassured.response.Response;
import org.testng.Assert;

public class AssertUtils {

    public static void assertStatusCode(Response response, int expected) {
        if (expected == response.getStatusCode()) {
            ReportLog.log(String.format("%s is correct", response.getStatusCode()));
        } else {
            ReportLog.error(String.format("Actual code: %s", response.getStatusCode()));
            ReportLog.error(String.format("Expected code: %s", expected));

            throwExceptionForStatusCode(response.getStatusCode(), response.getBody().asString());
        }

    }

    public static void assertJsonEquals(String expectedResponseJson, String actualResponseJson) {
        ReportLog.log(String.format("API Response: %s", expectedResponseJson));

        if (expectedResponseJson.equals(actualResponseJson)) {
            ReportLog.log("JSON response is correct");
        } else {
            ReportLog.error(String.format("Actual Response: %s", actualResponseJson));
            ReportLog.error(String.format("Expected Response: %s", expectedResponseJson));

            throw new JsonMismatchException("JSON mismatch:\nExpected: " + expectedResponseJson + "\nActual: " + actualResponseJson);
        }
    }

    private static void throwExceptionForStatusCode(int statusCode, String body) {
        switch (statusCode) {
            case 400 -> throw new BadRequestException(body);
            case 401 -> throw new UnauthorizedException(body);
            case 403 -> throw new ForbiddenException(body);
            case 404 -> throw new NotFoundException(body);
            default -> throw new StatusCodeMismatchException(200, statusCode); // default expected 200
        }
    }

    public static <T> void assertEqualValues(T expected, T actual, String message) {
        Assert.assertEquals(expected, actual);
        ReportLog.log(message);
    }

    // Wrapper for assertTrue
    public static void assertConditionTrue(boolean condition, String message) {
        Assert.assertTrue(condition);
        ReportLog.log(message);
    }
}
