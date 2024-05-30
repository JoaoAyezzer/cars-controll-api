package br.edu.unicesumar.carscontrollapi.exceptions;

public class ConflitException extends RuntimeException {

    public ConflitException(String message) {
        super(message);
    }

    public ConflitException(String message, Throwable cause) {
        super(message, cause);
    }
}
