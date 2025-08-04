package exceptions;

public class StatusCodeMismatchException extends APIException {
    public StatusCodeMismatchException(int expected, int actual) {
        super("Expected status code: " + expected + ", but got: " + actual);
    }
}
