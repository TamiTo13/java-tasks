package bg.sofia.uni.fmi.mjt.server.exceptions;

public class WrongSyntaxException extends Exception {
    public WrongSyntaxException(String message) {
        super(message);
    }

    public WrongSyntaxException(String message, Throwable e) {
        super(message, e);
    }
}
