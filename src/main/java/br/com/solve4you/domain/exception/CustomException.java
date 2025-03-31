package br.com.solve4you.domain.exception;
/**
 * Custom exception class for the application.
 * This class extends RuntimeException and provides a constructor to set the exception message.
 */

public class CustomException extends RuntimeException {
    public CustomException(String message) {
        super(message);
    }
}
