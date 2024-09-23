package dev.oscar.personalback.articulos.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.oscar.personalback.articulos.exceptions.ArticulosNotFoundException;
import dev.oscar.personalback.articulos.model.Articulos;
import dev.oscar.personalback.articulos.repository.ArticulosRepository;

@Service
public class ArticulosService {

     @Autowired
    private ArticulosRepository repository;

    public List<Articulos> getAllArticulos() {
        return repository.findAll();
    }

    public Articulos getArticulosById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ArticulosNotFoundException("Articulo no encontrado"));
    }

    public Articulos createArticulo(Articulos articulo) {
        return repository.save(articulo);
    }

    public Articulos updateArticulo(Articulos articulo) {
        if (!repository.existsById(articulo.getId())) {
            throw new ArticulosNotFoundException("Articulo no encontrado");
        }
        return repository.save(articulo);
    }

    public void deleteArticuloById(Long id) {
        if (!repository.existsById(id)) {
            throw new ArticulosNotFoundException("Articulo no encontrado");
        }
        repository.deleteById(id);
    }
    public List<Articulos> getArticulosByTipo(String tipo) {
        return repository.findByTipo(tipo);
    }

    
    public List<Articulos> getArticulosDeTecnica() {
        return repository.findByTipo("tecnica");
    }

    public List<Articulos> getArticulosDePsicologia() {
        return repository.findByTipo("psicologia");
    }

}
