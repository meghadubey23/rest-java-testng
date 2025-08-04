package exceptions;

public class BadRequestException extends APIException {
    public BadRequestException(String message) {
        super("400 Bad Request: " + message);
    }
}
