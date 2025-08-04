package data;

import java.util.concurrent.ConcurrentHashMap;

public class BaseData {

    private int expectedStatusCode = 200;
    private String expectedResponse = null;
    private String responseValue;
    private ConcurrentHashMap<String, String> map;

    public int getExpectedStatusCode() {
        return expectedStatusCode;
    }

    public void setExpectedStatusCode(int expectedStatusCode) {
        this.expectedStatusCode = expectedStatusCode;
    }

    public String getExpectedResponse() {
        return expectedResponse;
    }

    public void setExpectedResponse(String expectedResponse) {
        this.expectedResponse = expectedResponse;
    }

    public String getResponseValue() {
        return responseValue;
    }

    public void setResponseValue(String responseValue) {
        this.responseValue = responseValue;
    }

    public ConcurrentHashMap<String, String> getMap() {
        return map;
    }

    public void setMap(ConcurrentHashMap<String, String> map) {
        this.map = map;
    }
}
