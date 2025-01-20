package bg.sofia.uni.fmi.mjt.server.exceptions.command;

public class InvalidInputException extends IncorrectCommandException {
    public InvalidInputException(String message) {
        super(message);
    }
}
