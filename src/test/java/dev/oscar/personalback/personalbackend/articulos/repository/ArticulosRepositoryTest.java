package dev.oscar.personalback.personalbackend.articulos.repository;

import dev.oscar.personalback.articulos.model.Articulos;
import dev.oscar.personalback.articulos.repository.ArticulosRepository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ArticulosRepositoryTest {

    @Autowired
    private ArticulosRepository articulosRepository;

    @Test
    @DisplayName("Test findByTipo returns articles with the correct type")
    void testFindByTipo() {
        
        Articulos articulo1 = new Articulos(null, "Artículo 1", "Autor 1", "Tecnología", LocalDate.now(), "Texto 1", null, null, null, null, null);
        Articulos articulo2 = new Articulos(null, "Artículo 2", "Autor 2", "Salud", LocalDate.now(), "Texto 2", null, null, null, null, null);
        Articulos articulo3 = new Articulos(null, "Artículo 3", "Autor 3", "Tecnología", LocalDate.now(), "Texto 3", null, null, null, null, null);

      
        articulosRepository.save(articulo1);
        articulosRepository.save(articulo2);
        articulosRepository.save(articulo3);

        
        List<Articulos> articulosTecnologia = articulosRepository.findByTipo("Tecnología");

    
        assertEquals(2, articulosTecnologia.size());

      
        for (Articulos articulo : articulosTecnologia) {
            assertEquals("Tecnología", articulo.getTipo());
        }
    }

    @Test
    @DisplayName("Test findByTipo returns empty list for unknown type")
    void testFindByTipoEmpty() {
 
        List<Articulos> articulosDesconocidos = articulosRepository.findByTipo("Desconocido");

      
        assertTrue(articulosDesconocidos.isEmpty());
    }
}