package dev.oscar.personalback.personalbackend.salas.exceptions;

import org.junit.jupiter.api.Test;

import dev.oscar.personalback.salas.exceptions.SalasNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SalasNotFoundExceptionTest {

    @Test
    public void testSalasNotFoundExceptionWithMessage() {
        String message = "Sala no encontrada.";
        SalasNotFoundException exception = new SalasNotFoundException(message);

        assertEquals(message, exception.getMessage());
    }

    @Test
    public void testSalasNotFoundExceptionWithMessageAndCause() {
        String message = "Sala no encontrada.";
        Throwable cause = new RuntimeException("Causa de la excepción.");
        SalasNotFoundException exception = new SalasNotFoundException(message, cause);

        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    public void testSalasNotFoundExceptionThrow() {
        assertThrows(SalasNotFoundException.class, () -> {
            throw new SalasNotFoundException("Excepción lanzada para prueba.");
        });
    }
}