package practica8.practica8.controller;

import practica8.practica8.model.Obra;
import practica8.practica8.service.ObraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/obras")
public class ObraController {

    @Autowired
    private ObraService obraService;

    // PARTE 2.1: POST - Insertar nueva obra
    @PostMapping
    public ResponseEntity<Long> crearObra(@RequestBody Obra obra) {
        try {
            Long id = obraService.insertarObra(obra);
            return ResponseEntity.status(HttpStatus.CREATED).body(id);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // PARTE 3.1: PUT - Actualizar autor de una obra
    @PutMapping("/{id}")
    public ResponseEntity<Void> actualizarObra(@PathVariable Long id, @RequestParam String autor) {
        try {
            obraService.actualizarAutorObra(id, autor);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // PARTE 4.1: GET por ID
    @GetMapping("/{id}")
    public ResponseEntity<Obra> obtenerObraById(@PathVariable Long id) {
        try {
            Obra obra = obraService.obtenerObraPorId(id);
            if (obra != null) {
                return ResponseEntity.ok(obra);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // PARTE 5.1: GET todos
    @GetMapping
    public ResponseEntity<List<Obra>> obtenerTodasLasObras() {
        try {
            List<Obra> obras = obraService.obtenerTodasLasObras();
            if (obras.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
            return ResponseEntity.ok(obras);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // PARTE 6.1: GET por atributo (por autor)
    @GetMapping("/autor/{autor}")
    public ResponseEntity<List<Obra>> obtenerObrasPorAutor(@PathVariable String autor) {
        try {
            List<Obra> obras = obraService.obtenerObrasPorAutor(autor);
            if (obras.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
            return ResponseEntity.ok(obras);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // Método adicional: GET por género
    @GetMapping("/genero/{genero}")
    public ResponseEntity<List<Obra>> obtenerObrasPorGenero(@PathVariable String genero) {
        try {
            List<Obra> obras = obraService.obtenerObrasPorGenero(genero);
            if (obras.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
            return ResponseEntity.ok(obras);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}