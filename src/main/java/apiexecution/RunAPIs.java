package apiexecution;

import data.ResourceData;
import data.UserInfoData;
import data.UserLoginData;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utilities.AssertUtils;
import utilities.ReadAPIs;
import utilities.ReadPostBody;
import utilities.ReportLog;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static io.restassured.RestAssured.given;
import static utilities.ReadProperty.getProperty;

public class RunAPIs extends BaseAPI {

    public void postUserLogin(UserLoginData tdata) {
        String endPoint = ReadAPIs.getUserLoginEndpoint();

        Map<String, String> postBodyMap = new ReadPostBody("login").getJsonBody();
        if (tdata.isUsername())
            postBodyMap.put("email", getProperty("email"));
        else
            postBodyMap.remove("email");
        if (tdata.isPassword())
            postBodyMap.put("password", getProperty("password"));
        else
            postBodyMap.remove("password");

        ReportLog.log(String.format("Starting execution for %s endpoint", endPoint));

        Response response = given()
                .spec(requestSpec)
                .contentType(ContentType.JSON)
                .body(postBodyMap)
                .when()
                .post(endPoint);

        commonVerifications(tdata.getExpectedResponse(), tdata.getExpectedStatusCode(), response, tdata.getMap());
    }

    public void getSingleUser(List<UserInfoData> userInfoData) {
        String getEndPoint = ReadAPIs.getSingleUserEndpoint();

        userInfoData.parallelStream().forEach(tdata -> {
            String endPoint = String.format(getEndPoint, tdata.getUserId());

            ReportLog.log(String.format("Starting execution for %s endpoint", endPoint));

            Response response = given()
                    .spec(requestSpec)
                    .when()
                    .get(endPoint)
                    .then()
                    .extract()
                    .response();

            commonVerifications(tdata.getExpectedResponse(), tdata.getExpectedStatusCode(), response, tdata.getMap());
        });
    }

    public void getUsersList(List<UserInfoData> userInfoData) {
        String endPoint = ReadAPIs.getUsersList();

        userInfoData.parallelStream().forEach(tdata -> {
            ReportLog.log(String.format("Starting execution for %s endpoint", endPoint));

            Response response = given()
                    .spec(requestSpec)
                    .queryParam("page", "2")
                    .when()
                    .get(endPoint)
                    .then()
                    .extract()
                    .response();

            commonVerifications(tdata.getExpectedResponse(), tdata.getExpectedStatusCode(), response, tdata.getMap());
        });
    }

    public void getSingleResource(List<ResourceData> resourceData) {
        String getEndPoint = ReadAPIs.getSingleResource();

        resourceData.parallelStream().forEach(tdata -> {
            String endPoint = String.format(getEndPoint, tdata.getResourceId());

            ReportLog.log(String.format("Starting execution for %s endpoint", endPoint));

            Response response = given()
                    .spec(requestSpec)
                    .when()
                    .get(endPoint)
                    .then()
                    .extract()
                    .response();

            commonVerifications(tdata.getExpectedResponse(), tdata.getExpectedStatusCode(), response, tdata.getMap());
        });
    }

    private void commonVerifications(String expectedResponse, int expectedStatus, Response response, ConcurrentHashMap<String, String> map) {
        int statusCode = (expectedStatus != 0) ? expectedStatus : 200;

        // Parse response body once
        String body = response.getBody().asString();
        JsonPath jsonPath = new JsonPath(body);

        // Verify status code and response body
        verifyResponse(response, expectedResponse, statusCode);

        // Only do this if map is not null
        if (map != null) {
            map.forEach((key, value) -> {
                ReportLog.log(String.format("Key %s: Value %s", key, value));
                String respValue = jsonPath.get(key); // Use the parsed object
                AssertUtils.assertEqualValues(value, respValue, String.format("Error value is correct: %s", respValue));
            });
        }
    }
}
