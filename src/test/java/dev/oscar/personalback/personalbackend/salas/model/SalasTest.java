package dev.oscar.personalback.personalbackend.salas.model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dev.oscar.personalback.salas.model.Salas;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;

public class SalasTest {

    private Salas sala;

    @BeforeEach
    void setUp() {
       
        sala = new Salas();
    }

    @Test
    void testGetAndSetId() {
      
        sala.setId(1L);

        
        assertThat(sala.getId()).isEqualTo(1L);
    }

    @Test
    void testGetAndSetCiudad() {
    
        sala.setCiudad("Madrid");


        assertThat(sala.getCiudad()).isEqualTo("Madrid");
    }

    @Test
    void testGetAndSetLugar() {

        sala.setLugar("Teatro Real");

    
        assertThat(sala.getLugar()).isEqualTo("Teatro Real");
    }

    @Test
    void testGetAndSetFecha() {
   
        LocalDate fecha = LocalDate.of(2024, 10, 7);
        sala.setFecha(fecha);

       
        assertThat(sala.getFecha()).isEqualTo(fecha);
    }

    @Test
    void testGetAndSetHora() {
   
        LocalTime hora = LocalTime.of(12, 30);
        sala.setHora(hora);

      
        assertThat(sala.getHora()).isEqualTo(hora);
    }

    @Test
    void testConstructorConParametros() {
       
        LocalDate fecha = LocalDate.of(2024, 10, 7);
        LocalTime hora = LocalTime.of(12, 30);
        Salas salaConParametros = new Salas(1L, "Barcelona", "Sala B", fecha, hora);

      
        assertThat(salaConParametros.getId()).isEqualTo(1L);
        assertThat(salaConParametros.getCiudad()).isEqualTo("Barcelona");
        assertThat(salaConParametros.getLugar()).isEqualTo("Sala B");
        assertThat(salaConParametros.getFecha()).isEqualTo(fecha);
        assertThat(salaConParametros.getHora()).isEqualTo(hora);
    }
}