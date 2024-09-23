package dev.oscar.personalback.articulos.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import dev.oscar.personalback.articulos.service.ArticulosService;
import dev.oscar.personalback.articulos.model.Articulos;


@RestController
@RequestMapping(path = "api/v1/articulos")
public class ArticulosController {

     private final ArticulosService articulosService;

    @Autowired
    public ArticulosController(ArticulosService articulosService) {
        this.articulosService = articulosService;
}

@GetMapping(path = "/allarticulos")
public ResponseEntity<List<Articulos>> getAllArticulos() {
    List<Articulos> articulos = articulosService.getAllArticulos();
    return ResponseEntity.ok(articulos);
}

 @GetMapping(path = "/{id}")
    public ResponseEntity<Articulos> getArticulosById(@PathVariable Long id) {
        Articulos articulos = articulosService.getArticulosById(id);
        if (articulos != null) {
          return ResponseEntity.ok(articulos);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

  @PostMapping("/create")
    public ResponseEntity<Articulos> createArticulos(@RequestBody Articulos articulos) {
        Articulos createdArticulos = articulosService.createArticulo(articulos);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdArticulos);
    }

      @PutMapping(path = "/{id}")
    public ResponseEntity<Articulos> uptadeArticulos(@RequestBody Articulos articulos, @PathVariable Long id) {
        articulos.setId(id);
        Articulos updateArticulos = articulosService.updateArticulo(articulos);
        return ResponseEntity.ok(updateArticulos);
    }

      @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteArticulos(@PathVariable Long id) {
        articulosService.deleteArticuloById(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping(path = "/tipo")
    public ResponseEntity<List<Articulos>> getArticulosByTipo(@RequestParam String tipo) {
        List<Articulos> articulos = articulosService.getArticulosByTipo(tipo);
        return ResponseEntity.ok(articulos);
    }

    
    @GetMapping(path = "/tecnica")
    public ResponseEntity<List<Articulos>> getArticulosDeTecnica() {
        List<Articulos> articulos = articulosService.getArticulosDeTecnica();
        return ResponseEntity.ok(articulos);
    }

    
    @GetMapping(path = "/psicologia")
    public ResponseEntity<List<Articulos>> getArticulosDePsicologia() {
        List<Articulos> articulos = articulosService.getArticulosDePsicologia();
        return ResponseEntity.ok(articulos);
    }
}
