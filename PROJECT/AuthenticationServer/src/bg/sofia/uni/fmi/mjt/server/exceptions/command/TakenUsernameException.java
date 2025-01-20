package bg.sofia.uni.fmi.mjt.server.exceptions.command;

public class TakenUsernameException extends IncorrectCommandException {
    public TakenUsernameException(String message) {
        super(message);
    }
}
