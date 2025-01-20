package bg.sofia.uni.fmi.mjt.cookingcompass.exception;

public class IncorrectHealthException extends Exception {

    public IncorrectHealthException(String message) {
        super(message);
    }

    public IncorrectHealthException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
