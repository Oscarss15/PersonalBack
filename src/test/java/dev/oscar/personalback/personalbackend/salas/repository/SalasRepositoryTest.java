package dev.oscar.personalback.personalbackend.salas.repository;

import dev.oscar.personalback.salas.model.Salas;
import dev.oscar.personalback.salas.repository.SalasRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest 
class SalasRepositoryTest {

    @Autowired
    private SalasRepository salasRepository;

    private Salas sala;

    @BeforeEach
    void setUp() {
      
        sala = new Salas();
        sala.setCiudad("Madrid");
        sala.setLugar("Auditorio");
        sala.setFecha(LocalDate.of(2024, 10, 10));
        sala.setHora(LocalTime.of(18, 0));
    }

    @Test
    void testSaveSala() {

        Salas savedSala = salasRepository.save(sala);

 
        assertThat(savedSala.getId()).isNotNull(); 
        assertThat(savedSala.getCiudad()).isEqualTo("Madrid");
    }

    @Test
    void testFindById() {
  
        Salas savedSala = salasRepository.save(sala);
        Optional<Salas> optionalSala = salasRepository.findById(savedSala.getId());

     
        assertThat(optionalSala).isPresent();
        assertThat(optionalSala.get().getCiudad()).isEqualTo("Madrid");
    }

    @Test
    void testFindAll() {
     
        Salas sala2 = new Salas(null, "Barcelona", "Teatro", LocalDate.of(2024, 11, 11), LocalTime.of(19, 0));
        salasRepository.save(sala);
        salasRepository.save(sala2);

     
        List<Salas> salasList = salasRepository.findAll();

        assertThat(salasList).hasSize(8);
    }

    @Test
    void testDeleteById() {
     
        Salas savedSala = salasRepository.save(sala);
        salasRepository.deleteById(savedSala.getId());

    
        Optional<Salas> optionalSala = salasRepository.findById(savedSala.getId());
        assertThat(optionalSala).isNotPresent();
    }
}