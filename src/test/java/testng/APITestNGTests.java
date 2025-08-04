package testng;

import apiexecution.RunAPIs;
import data.ResourceData;
import data.UserInfoData;
import data.UserLoginData;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import static utilities.ReadExpectedResponses.*;

public class APITestNGTests extends TestNGBaseTest {
//    APIs from: https://reqres.in/

    @Test(description = "Login as a valid user", groups = {"smoke"})
    void userLogin() {
        UserLoginData testData1 = new UserLoginData();
        testData1.setExpectedResponse(getUserLoginResponse());

        RunAPIs api = new RunAPIs();
        api.postUserLogin(testData1);
    }

    @Test(description = "Invalid user login")
    void userLoginUnsuccessful() {
        UserLoginData testData1 = new UserLoginData();
        testData1.setPassword(false);
        testData1.setExpectedResponse(getFailedUserLoginResponse());
        testData1.setExpectedStatusCode(400);

        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        map.put("error", "Missing password");
        testData1.setMap(map);

        RunAPIs api = new RunAPIs();
        api.postUserLogin(testData1);
    }


    @Test(description = "Get Single User API should return 200 OK for valid user IDs")
    void runGetSingleUserReturns200() {
        UserInfoData testData1 = new UserInfoData();
        testData1.setUserId(2);
        testData1.setExpectedResponse(getSingleUserExpectedResponse());

        UserInfoData testData2 = new UserInfoData();
        testData2.setUserId(25);
        testData2.setExpectedStatusCode(404);

        RunAPIs api = new RunAPIs();
        api.getSingleUser(List.of(testData1, testData2));
    }


    @Test(description = "Get Users List API should return 200 OK for valid API request", groups = {"smoke"})
    void runGetListReturns200() {
        UserInfoData testData1 = new UserInfoData();
        testData1.setExpectedResponse(getUserListExpectedResponse());

        RunAPIs api = new RunAPIs();
        api.getUsersList(List.of(testData1));
    }

    @Test(description = "Get Single Resource API should return 200 OK for valid user IDs", groups = {"smoke"})
    void runGetSingleResourceReturns200() {
        ResourceData testData1 = new ResourceData();
        testData1.setResourceId(2);
        testData1.setExpectedResponse(getSingleResourceResponse());

        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        map.put("data.name", "fuchsia rose");
        testData1.setMap(map);

        RunAPIs api = new RunAPIs();
        api.getSingleResource(List.of(testData1));
    }
}
