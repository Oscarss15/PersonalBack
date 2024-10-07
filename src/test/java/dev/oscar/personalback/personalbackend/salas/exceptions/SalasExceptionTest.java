package dev.oscar.personalback.personalbackend.salas.exceptions;

import org.junit.jupiter.api.Test;

import dev.oscar.personalback.salas.exceptions.SalasException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SalasExceptionTest {

    @Test
    public void testSalasExceptionWithMessage() {
        String message = "This is a custom exception message.";
        SalasException exception = new SalasException(message);

        assertEquals(message, exception.getMessage());
    }

    @Test
    public void testSalasExceptionWithMessageAndCause() {
        String message = "This is a custom exception message.";
        Throwable cause = new RuntimeException("This is the cause.");
        SalasException exception = new SalasException(message, cause);

        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    public void testSalasExceptionThrow() {
        assertThrows(SalasException.class, () -> {
            throw new SalasException("Exception thrown for testing.");
        });
    }
}