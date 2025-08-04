package data;

public class UserLoginData extends BaseData {

    private boolean username = true;
    private boolean password = true;

    public boolean isUsername() {
        return username;
    }

    public void setUsername(boolean username) {
        this.username = username;
    }

    public boolean isPassword() {
        return password;
    }

    public void setPassword(boolean password) {
        this.password = password;
    }
}
