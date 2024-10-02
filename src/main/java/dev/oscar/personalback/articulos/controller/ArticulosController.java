package dev.oscar.personalback.articulos.controller;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.core.io.FileSystemResource;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import dev.oscar.personalback.articulos.service.ArticulosService;
import dev.oscar.personalback.articulos.model.Articulos;
import org.springframework.core.io.Resource;

import org.springframework.http.MediaType;

import org.springframework.web.bind.annotation.RestController; // Asegúrate de tener esto
import java.io.File; // Para File
import java.io.IOException; // Para IOException
import java.nio.file.Files; // Para Files
import java.nio.file.Path; // Para Path
import java.nio.file.Paths; // Para Paths



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
public ResponseEntity<Articulos> createArticulos(
        @RequestParam("titulo") String titulo,
        @RequestParam("autor") String autor,
        @RequestParam("tipo") String tipo,
        @RequestParam("fecha") LocalDate fecha,
        @RequestParam("texto1") String texto1,
        @RequestParam("texto2") String texto2,
        @RequestParam("texto3") String texto3,
        @RequestParam(value = "imagen1", required = false) MultipartFile imagen1,
        @RequestParam(value = "imagen2", required = false) MultipartFile imagen2,
        @RequestParam(value = "imagen3", required = false) MultipartFile imagen3) {

    // Crear un nuevo objeto Articulos y asignar los valores
    Articulos nuevoArticulo = new Articulos();
    nuevoArticulo.setTitulo(titulo);
    nuevoArticulo.setAutor(autor);
    nuevoArticulo.setTipo(tipo);
    nuevoArticulo.setFecha(fecha);
    nuevoArticulo.setTexto1(texto1);
    nuevoArticulo.setTexto2(texto2);
    nuevoArticulo.setTexto3(texto3);

    // Manejar las imágenes
    if (imagen1 != null && !imagen1.isEmpty()) {
        String imagen1Path = guardarImagen(imagen1);
        nuevoArticulo.setImagen1(imagen1Path);
    }
    if (imagen2 != null && !imagen2.isEmpty()) {
        String imagen2Path = guardarImagen(imagen2);
        nuevoArticulo.setImagen2(imagen2Path);
    }
    if (imagen3 != null && !imagen3.isEmpty()) {
        String imagen3Path = guardarImagen(imagen3);
        nuevoArticulo.setImagen3(imagen3Path);
    }

    Articulos createdArticulos = articulosService.createArticulo(nuevoArticulo);
    return ResponseEntity.status(HttpStatus.CREATED).body(createdArticulos);
}
 // Método para guardar la imagen
    private String guardarImagen(MultipartFile file) {
        try {
            // Define el directorio donde se guardarán las imágenes
            String directorio = "src/main/resources/static/upload/";
            
            // Asegúrate de que el directorio existe
            Path directorioPath = Paths.get(directorio);
            if (!Files.exists(directorioPath)) {
                Files.createDirectories(directorioPath); // Crea el directorio si no existe
            }

            // Genera un nombre único para el archivo
            String nombreArchivo = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            Path rutaCompleta = directorioPath.resolve(nombreArchivo); // Usar resolve para la construcción de la ruta

            // Guarda el archivo
            Files.copy(file.getInputStream(), rutaCompleta);
            return nombreArchivo; // O la ruta completa si prefieres
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al guardar la imagen");
        }
    }
    @GetMapping("/upload/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Path path = Paths.get("src/main/resources/static/upload").resolve(filename);
        System.out.println("Path to file: " + path.toAbsolutePath()); // Agrega esta línea
        Resource resource = new FileSystemResource(path.toFile());
        
        if (!resource.exists()) {
            return ResponseEntity.notFound().build(); // Retorna 404 si no se encuentra el archivo
        }
        
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG) // Ajusta el tipo de contenido según sea necesario
                .body(resource);
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

