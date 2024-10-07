package dev.oscar.personalback.personalbackend.salas.service;

import dev.oscar.personalback.salas.exceptions.SalasNotFoundException;
import dev.oscar.personalback.salas.model.Salas;
import dev.oscar.personalback.salas.repository.SalasRepository;
import dev.oscar.personalback.salas.service.SalasService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class SalasServiceTest {

    @Mock
    private SalasRepository salasRepository;

    @InjectMocks
    private SalasService salasService;

    private Salas sala;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        sala = new Salas(1L, "Madrid", "Sala 1", LocalDate.now(), LocalTime.now());
    }

    @Test
    public void testGetAllSalas() {
        when(salasRepository.findAll()).thenReturn(Arrays.asList(sala));

        List<Salas> salas = salasService.getAllSalas();

        assertNotNull(salas);
        assertEquals(1, salas.size());
        assertEquals(sala.getCiudad(), salas.get(0).getCiudad());
    }

    @Test
    public void testGetSalasById_Success() {
        when(salasRepository.findById(1L)).thenReturn(Optional.of(sala));

        Salas foundSala = salasService.getSalasById(1L);

        assertNotNull(foundSala);
        assertEquals(sala.getId(), foundSala.getId());
    }

    @Test
    public void testGetSalasById_NotFound() {
        when(salasRepository.findById(1L)).thenReturn(Optional.empty());

        SalasNotFoundException thrown = assertThrows(SalasNotFoundException.class, () -> {
            salasService.getSalasById(1L);
        });

        assertEquals("Sala no encontrada", thrown.getMessage());
    }

    @Test
    public void testCreateSala() {
        when(salasRepository.save(any(Salas.class))).thenReturn(sala);

        Salas createdSala = salasService.createSala(sala);

        assertNotNull(createdSala);
        assertEquals(sala.getId(), createdSala.getId());
    }

    @Test
    public void testDeleteSalaById_Success() {
        when(salasRepository.existsById(1L)).thenReturn(true);

        assertDoesNotThrow(() -> {
            salasService.deleteSalaById(1L);
        });

        verify(salasRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testDeleteSalaById_NotFound() {
        when(salasRepository.existsById(1L)).thenReturn(false);

        SalasNotFoundException thrown = assertThrows(SalasNotFoundException.class, () -> {
            salasService.deleteSalaById(1L);
        });

        assertEquals("Sala no encontrada", thrown.getMessage());
        verify(salasRepository, never()).deleteById(anyLong());
    }
}
