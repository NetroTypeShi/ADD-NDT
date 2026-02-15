package practica7.practica7.service;

import practica7.practica7.model.Obra;
import practica7.practica7.repository.ObraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ObraService {

    @Autowired
    private ObraRepository obraRepository;

    // 1. INSERTAR - devuelve el id del objeto insertado
    public Long insertarObra(Obra obra) {
        try {
            Obra obraGuardada = obraRepository.save(obra);
            System.out.println("✓ Obra insertada: " + obraGuardada.getNombre() + " con ID: " + obraGuardada.getId());
            return obraGuardada.getId();
        } catch (Exception e) {
            System.out.println("✗ Error al insertar obra: " + e.getMessage());
            return null;
        }
    }

    // 2. BORRAR - dado un objeto
    public void borrarObra(Obra obra) {
        try {
            obraRepository.delete(obra);
            System.out.println("✓ Obra eliminada: " + obra.getNombre());
        } catch (Exception e) {
            System.out.println("✗ Error al borrar obra: " + e.getMessage());
        }
    }

    // 3. ACTUALIZAR - dado un id y un atributo (actualizar autor)
    public void actualizarAutorObra(Long id, String nuevoAutor) {
        try {
            Optional<Obra> obraOpt = obraRepository.findById(id);
            if (obraOpt.isPresent()) {
                Obra obra = obraOpt.get();
                obra.setAutor(nuevoAutor);
                obraRepository.save(obra);
                System.out.println("✓ Obra actualizada: " + obra.getNombre() + " - Nuevo autor: " + nuevoAutor);
            } else {
                System.out.println("✗ Obra no encontrada con ID: " + id);
            }
        } catch (Exception e) {
            System.out.println("✗ Error al actualizar obra: " + e.getMessage());
        }
    }

    // 4. OBTENER - dado un id
    public Obra obtenerObraPorId(Long id) {
        try {
            Optional<Obra> obraOpt = obraRepository.findById(id);
            if (obraOpt.isPresent()) {
                System.out.println("✓ Obra encontrada: " + obraOpt.get().getNombre());
                return obraOpt.get();
            } else {
                System.out.println("✗ Obra no encontrada con ID: " + id);
                return null;
            }
        } catch (Exception e) {
            System.out.println("✗ Error al obtener obra: " + e.getMessage());
            return null;
        }
    }

    // 5. OBTENER - varios objetos dado un atributo diferente del id (por autor)
    public List<Obra> obtenerObrasPorAutor(String autor) {
        try {
            List<Obra> obras = obraRepository.findByAutor(autor);
            System.out.println("✓ Se encontraron " + obras.size() + " obras del autor: " + autor);
            return obras;
        } catch (Exception e) {
            System.out.println("✗ Error al buscar obras por autor: " + e.getMessage());
            return List.of();
        }
    }

    // Método adicional: obtener por género
    public List<Obra> obtenerObrasPorGenero(String genero) {
        try {
            List<Obra> obras = obraRepository.findByGenero(genero);
            System.out.println("✓ Se encontraron " + obras.size() + " obras del género: " + genero);
            return obras;
        } catch (Exception e) {
            System.out.println("✗ Error al buscar obras por género: " + e.getMessage());
            return List.of();
        }
    }

    // Obtener todas las obras
    public List<Obra> obtenerTodasLasObras() {
        try {
            return obraRepository.findAll();
        } catch (Exception e) {
            System.out.println("✗ Error al obtener todas las obras: " + e.getMessage());
            return List.of();
        }
    }
}