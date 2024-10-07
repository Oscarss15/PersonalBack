package dev.oscar.personalback.personalbackend.articulos.exceptions;

import org.junit.jupiter.api.Test;

import dev.oscar.personalback.articulos.exceptions.ArticulosNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ArticulosNotFoundExceptionTest {

    @Test
    public void testArticulosNotFoundExceptionWithMessage() {
        String message = "Artículo no encontrado.";
        ArticulosNotFoundException exception = new ArticulosNotFoundException(message);

        assertEquals(message, exception.getMessage());
    }

    @Test
    public void testArticulosNotFoundExceptionWithMessageAndCause() {
        String message = "Artículo no encontrado.";
        Throwable cause = new RuntimeException("Causa de la excepción.");
        ArticulosNotFoundException exception = new ArticulosNotFoundException(message, cause);

        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    public void testArticulosNotFoundExceptionThrow() {
        assertThrows(ArticulosNotFoundException.class, () -> {
            throw new ArticulosNotFoundException("Excepción lanzada para prueba.");
        });
    }
}