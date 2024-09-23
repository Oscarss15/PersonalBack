package dev.oscar.personalback.articulos.exceptions;

public class ArticulosNotFoundException extends ArticulosException {

    public ArticulosNotFoundException(String message){
        super(message);
    }

    public ArticulosNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

