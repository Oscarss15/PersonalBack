 package dev.oscar.personalback.salas.exceptions;

public class SalasNotFoundException extends SalasException {

    public SalasNotFoundException(String message){
        super(message);
    }

    public SalasNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

 