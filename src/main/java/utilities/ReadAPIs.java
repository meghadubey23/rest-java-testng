package utilities;

import static utilities.ReadProperty.getProperty;

public class ReadAPIs {

    public static String getBaseUri() {
        return getProperty("baseUri");
    }

    public static String getUserLoginEndpoint() {
        return getProperty("login");
    }

    public static String getSingleUserEndpoint() {
        return getProperty("getSingleUser");
    }

    public static String getUsersList() {
        return getProperty("getUsersList");
    }

    public static String getSingleResource() {
        return getProperty("getSingleResource");
    }

    public static String getResourceList() {
        return getProperty("getResourceList");
    }

}
