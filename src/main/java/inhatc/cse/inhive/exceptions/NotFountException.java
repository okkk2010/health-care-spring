package inhatc.cse.inhive.exceptions;

public class NotFountException extends RuntimeException {
    public NotFountException(String message) {
        super(message);
        System.out.println("NotFountException: " + message);
    }
}
