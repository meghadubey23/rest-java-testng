package exceptions;

public class NotFoundException extends APIException {
    public NotFoundException(String message) {
        super("404 Not Found: " + message);
    }
}
