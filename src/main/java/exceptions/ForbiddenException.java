package exceptions;

public class ForbiddenException extends APIException {
    public ForbiddenException(String message) {
        super("403 Forbidden: " + message);
    }
}
