package exceptions;

public class UnauthorizedException extends APIException {
    public UnauthorizedException(String message) {
        super("401 Unauthorized: " + message);
    }
}
