package dev.oscar.personalback.personalbackend.articulos.services;

import dev.oscar.personalback.articulos.exceptions.ArticulosNotFoundException;
import dev.oscar.personalback.articulos.model.Articulos;
import dev.oscar.personalback.articulos.repository.ArticulosRepository;
import dev.oscar.personalback.articulos.service.ArticulosService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ArticulosServiceTest {

    @Mock
    private ArticulosRepository repository;

    @InjectMocks
    private ArticulosService service;

    private Articulos articulo1;
    private Articulos articulo2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

    
        articulo1 = new Articulos(1L, "Artículo 1", "Autor 1", "Tecnología", LocalDate.now(), "Texto 1", null, null, null, null, null);
        articulo2 = new Articulos(2L, "Artículo 2", "Autor 2", "Psicología", LocalDate.now(), "Texto 2", null, null, null, null, null);
    }

    @Test
    void testGetAllArticulos() {
   
        when(repository.findAll()).thenReturn(Arrays.asList(articulo1, articulo2));


        List<Articulos> articulos = service.getAllArticulos();


        assertEquals(2, articulos.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    void testGetArticulosByIdFound() {
   
        when(repository.findById(1L)).thenReturn(Optional.of(articulo1));

 
        Articulos articulo = service.getArticulosById(1L);

     
        assertEquals(articulo1, articulo);
        verify(repository, times(1)).findById(1L);
    }

    @Test
    void testGetArticulosByIdNotFound() {
       
        when(repository.findById(3L)).thenReturn(Optional.empty());


        assertThrows(ArticulosNotFoundException.class, () -> service.getArticulosById(3L));
        verify(repository, times(1)).findById(3L);
    }

    @Test
    void testCreateArticulo() {
      
        when(repository.save(articulo1)).thenReturn(articulo1);

       
        Articulos articuloCreado = service.createArticulo(articulo1);

  
        assertEquals(articulo1, articuloCreado);
        verify(repository, times(1)).save(articulo1);
    }

    @Test
    void testUpdateArticuloFound() {
    
        when(repository.existsById(articulo1.getId())).thenReturn(true);
        when(repository.save(articulo1)).thenReturn(articulo1);

   
        Articulos articuloActualizado = service.updateArticulo(articulo1);


        assertEquals(articulo1, articuloActualizado);
        verify(repository, times(1)).existsById(articulo1.getId());
        verify(repository, times(1)).save(articulo1);
    }

    @Test
    void testUpdateArticuloNotFound() {
    
        when(repository.existsById(articulo1.getId())).thenReturn(false);

     
        assertThrows(ArticulosNotFoundException.class, () -> service.updateArticulo(articulo1));
        verify(repository, times(1)).existsById(articulo1.getId());
    }

    @Test
    void testDeleteArticuloByIdFound() {
     
        when(repository.existsById(1L)).thenReturn(true);

 
        service.deleteArticuloById(1L);


        verify(repository, times(1)).existsById(1L);
        verify(repository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteArticuloByIdNotFound() {
 
        when(repository.existsById(3L)).thenReturn(false);


        assertThrows(ArticulosNotFoundException.class, () -> service.deleteArticuloById(3L));
        verify(repository, times(1)).existsById(3L);
    }

    @Test
    void testGetArticulosByTipo() {

        when(repository.findByTipo("Tecnología")).thenReturn(Arrays.asList(articulo1));

    
        List<Articulos> articulosTecnologia = service.getArticulosByTipo("Tecnología");

      
        assertEquals(1, articulosTecnologia.size());
        assertEquals(articulo1, articulosTecnologia.get(0));
        verify(repository, times(1)).findByTipo("Tecnología");
    }
}