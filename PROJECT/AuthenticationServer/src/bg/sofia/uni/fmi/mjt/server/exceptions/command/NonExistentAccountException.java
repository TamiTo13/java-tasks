package bg.sofia.uni.fmi.mjt.server.exceptions.command;

public class NonExistentAccountException extends IncorrectCommandException {
    public NonExistentAccountException(String message) {
        super(message);
    }

}
