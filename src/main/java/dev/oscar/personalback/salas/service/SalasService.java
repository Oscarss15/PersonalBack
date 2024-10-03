 package dev.oscar.personalback.salas.service;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.oscar.personalback.salas.exceptions.SalasNotFoundException;
import dev.oscar.personalback.salas.model.Salas;
import dev.oscar.personalback.salas.repository.SalasRepository;

@Service
public class SalasService {

     @Autowired
    private SalasRepository repository;

    
    public List<Salas> getAllSalas() {
        return repository.findAll();
    }

    public Salas getSalasById(Long id) {
        return repository.findById(id).orElseThrow(() -> new SalasNotFoundException("Sala no encontrada"));
    }

    public Salas createSala(Salas sala) {
        return repository.save(sala);
    }

   

    public void deleteSalaById(Long id) {
        if (!repository.existsById(id)) {
            throw new SalasNotFoundException("Sala no encontrada");
        }
        repository.deleteById(id);
    }
   

} 