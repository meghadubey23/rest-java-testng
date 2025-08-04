package apiexecution;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utilities.AssertUtils;
import utilities.ReadAPIs;
import utilities.ReportLog;

public abstract class BaseAPI {

    // Global reusable requestSpec
    protected static RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri(ReadAPIs.getBaseUri())
            .addHeader("x-api-key", "reqres-free-v1")
            .setContentType("application/json")
            .build();

    // Verifies status and optionally JSON
    protected void verifyResponse(Response response, String expectedJson, Integer statusCode) {
        AssertUtils.assertStatusCode(response, statusCode);

        if (expectedJson != null) {
            AssertUtils.assertJsonEquals(expectedJson, response.getBody().asString());
        }
    }

    protected void verifyResponse(Response response) {
        verifyResponse(response, null, 200);
    }

    protected void verifyResponse(Response response, String expectedJson) {
        verifyResponse(response, expectedJson, 200);
    }

    protected void verifyResponse(Response response, Integer statusCode) {
        verifyResponse(response, null, statusCode);
    }

    protected String getValueFromResponse(Response response, String key) {
        JsonPath jsonPath = new JsonPath(response.getBody().asString());
        return jsonPath.getString(key);
    }

    protected void logRequestAndResponse(String endpoint, Response response) {
        ReportLog.log("Endpoint: " + endpoint);
        ReportLog.log("Status: " + response.getStatusCode());
        ReportLog.log("Body: " + response.getBody().asString());
    }
}
