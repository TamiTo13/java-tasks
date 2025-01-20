package bg.sofia.uni.fmi.mjt.server.exceptions.command;

public class UnauthorizedCommandException extends IncorrectCommandException {
    public UnauthorizedCommandException(String message) {
        super(message);
    }
}
