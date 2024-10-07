package dev.oscar.personalback.personalbackend.articulos.model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dev.oscar.personalback.articulos.model.Articulos;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ArticulosTest {

    private Articulos articulo;

    @BeforeEach
    void setUp() {
        articulo = new Articulos();
    }

    @Test
    void testConstructorWithParams() {
        LocalDate fecha = LocalDate.now();
        Articulos articuloParam = new Articulos(1L, "Título de prueba", "Autor de prueba", "Tipo de prueba",
                fecha, "Texto1 de prueba", "Texto2 de prueba", "Texto3 de prueba",
                "Imagen1 de prueba", "Imagen2 de prueba", "Imagen3 de prueba");

        assertEquals(1L, articuloParam.getId());
        assertEquals("Título de prueba", articuloParam.getTitulo());
        assertEquals("Autor de prueba", articuloParam.getAutor());
        assertEquals("Tipo de prueba", articuloParam.getTipo());
        assertEquals(fecha, articuloParam.getFecha());
        assertEquals("Texto1 de prueba", articuloParam.getTexto1());
        assertEquals("Texto2 de prueba", articuloParam.getTexto2());
        assertEquals("Texto3 de prueba", articuloParam.getTexto3());
        assertEquals("Imagen1 de prueba", articuloParam.getImagen1());
        assertEquals("Imagen2 de prueba", articuloParam.getImagen2());
        assertEquals("Imagen3 de prueba", articuloParam.getImagen3());
    }

    @Test
    void testGettersAndSetters() {
        articulo.setId(1L);
        articulo.setTitulo("Título de prueba");
        articulo.setAutor("Autor de prueba");
        articulo.setTipo("Tipo de prueba");
        LocalDate fecha = LocalDate.now();
        articulo.setFecha(fecha);
        articulo.setTexto1("Texto1 de prueba");
        articulo.setTexto2("Texto2 de prueba");
        articulo.setTexto3("Texto3 de prueba");
        articulo.setImagen1("Imagen1 de prueba");
        articulo.setImagen2("Imagen2 de prueba");
        articulo.setImagen3("Imagen3 de prueba");

        assertEquals(1L, articulo.getId());
        assertEquals("Título de prueba", articulo.getTitulo());
        assertEquals("Autor de prueba", articulo.getAutor());
        assertEquals("Tipo de prueba", articulo.getTipo());
        assertEquals(fecha, articulo.getFecha());
        assertEquals("Texto1 de prueba", articulo.getTexto1());
        assertEquals("Texto2 de prueba", articulo.getTexto2());
        assertEquals("Texto3 de prueba", articulo.getTexto3());
        assertEquals("Imagen1 de prueba", articulo.getImagen1());
        assertEquals("Imagen2 de prueba", articulo.getImagen2());
        assertEquals("Imagen3 de prueba", articulo.getImagen3());
    }

    @Test
    void testNoArgsConstructor() {
        assertNull(articulo.getId());
        assertNull(articulo.getTitulo());
        assertNull(articulo.getAutor());
        assertNull(articulo.getTipo());
        assertNull(articulo.getFecha());
        assertNull(articulo.getTexto1());
        assertNull(articulo.getTexto2());
        assertNull(articulo.getTexto3());
        assertNull(articulo.getImagen1());
        assertNull(articulo.getImagen2());
        assertNull(articulo.getImagen3());
    }
}