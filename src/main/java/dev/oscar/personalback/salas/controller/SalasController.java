package dev.oscar.personalback.salas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import dev.oscar.personalback.salas.model.Salas;
import dev.oscar.personalback.salas.service.SalasService;

@RestController
@RequestMapping(path = "api/v1/salas")
public class SalasController {

    private final SalasService salasService;

    @Autowired
    public SalasController(SalasService salasService) {
        this.salasService = salasService;
    }

    
    @GetMapping(path = "/allsalas")
    public ResponseEntity<List<Salas>> getAllSalas() {
        List<Salas> salas = salasService.getAllSalas();
        return ResponseEntity.ok(salas);
    }

    
    @GetMapping(path = "/{id}")
    public ResponseEntity<Salas> getSalasById(@PathVariable Long id) {
        Salas salas = salasService.getSalasById(id);
        if (salas != null) {
            return ResponseEntity.ok(salas);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    
    @PostMapping(path = "/create")
    public ResponseEntity<Salas> createSala(@RequestBody Salas sala) {
        Salas nuevaSala = salasService.createSala(sala);
        return ResponseEntity.ok(nuevaSala);
    }

   
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteSalaById(@PathVariable Long id) {
        salasService.deleteSalaById(id);
        return ResponseEntity.noContent().build();
    }
}
