package dev.oscar.personalback.personalbackend.articulos.exceptions;

import org.junit.jupiter.api.Test;

import dev.oscar.personalback.articulos.exceptions.ArticulosException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ArticulosExceptionTest {

    @Test
    public void testArticulosExceptionWithMessage() {
        String message = "Error en Articulo.";
        ArticulosException exception = new ArticulosException(message);

        assertEquals(message, exception.getMessage());
    }

    @Test
    public void testArticulosExceptionWithMessageAndCause() {
        String message = "Error en Articulo.";
        Throwable cause = new RuntimeException("Causa de la excepción.");
        ArticulosException exception = new ArticulosException(message, cause);

        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    public void testArticulosExceptionThrow() {
        assertThrows(ArticulosException.class, () -> {
            throw new ArticulosException("Excepción lanzada para prueba.");
        });
    }
}