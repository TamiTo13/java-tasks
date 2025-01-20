package bg.sofia.uni.fmi.mjt.cookingcompass.exception;

public class IncorrectMealTypeException extends Exception {

    public IncorrectMealTypeException(String message) {
        super(message);
    }

    public IncorrectMealTypeException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
