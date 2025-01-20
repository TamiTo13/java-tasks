package bg.sofia.uni.fmi.mjt.server.exceptions.command;

public class IncorrectCommandException extends Exception {
    IncorrectCommandException(String message) {
        super(message);
    }
}
