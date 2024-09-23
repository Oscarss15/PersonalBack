package dev.oscar.personalback.articulos.exceptions;

public class ArticulosException extends RuntimeException {

    public ArticulosException(String message) {
        super(message);
    }

    public ArticulosException(String message, Throwable cause) {
        super(message, cause);
    }
}
