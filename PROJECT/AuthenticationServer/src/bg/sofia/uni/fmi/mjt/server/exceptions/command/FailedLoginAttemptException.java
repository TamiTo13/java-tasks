package bg.sofia.uni.fmi.mjt.server.exceptions.command;

public class FailedLoginAttemptException extends IncorrectCommandException {
    public FailedLoginAttemptException(String message) {
        super(message);
    }
}
