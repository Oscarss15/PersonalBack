 package dev.oscar.personalback.salas.exceptions;


public class SalasException extends RuntimeException {

    public SalasException(String message) {
        super(message);
    }

    public SalasException(String message, Throwable cause) {
        super(message, cause);
    }
}
 