package practica7.practica7.service;

import practica7.practica7.model.Sesion;
import practica7.practica7.repository.SesionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class SesionService {

    @Autowired
    private SesionRepository sesionRepository;

    // 1. INSERTAR - devuelve el id del objeto insertado
    public Long insertarSesion(Sesion sesion) {
        try {
            Sesion sesionGuardada = sesionRepository.save(sesion);
            System.out.println("✓ Sesión insertada: " + sesionGuardada.getDia() + " a las " + sesionGuardada.getHora() + " con ID: " + sesionGuardada.getId());
            return sesionGuardada.getId();
        } catch (Exception e) {
            System.out.println("✗ Error al insertar sesión: " + e.getMessage());
            return null;
        }
    }

    // 2. BORRAR - dado un objeto
    public void borrarSesion(Sesion sesion) {
        try {
            sesionRepository.delete(sesion);
            System.out.println("✓ Sesión eliminada: " + sesion.getDia() + " a las " + sesion.getHora());
        } catch (Exception e) {
            System.out.println("✗ Error al borrar sesión: " + e.getMessage());
        }
    }

    // 3. ACTUALIZAR - dado un id y un atributo (actualizar hora)
    public void actualizarHoraSesion(Long id, String nuevaHora) {
        try {
            Optional<Sesion> sesionOpt = sesionRepository.findById(id);
            if (sesionOpt.isPresent()) {
                Sesion sesion = sesionOpt.get();
                sesion.setHora(nuevaHora);
                sesionRepository.save(sesion);
                System.out.println("✓ Sesión actualizada: Nueva hora: " + nuevaHora);
            } else {
                System.out.println("✗ Sesión no encontrada con ID: " + id);
            }
        } catch (Exception e) {
            System.out.println("✗ Error al actualizar sesión: " + e.getMessage());
        }
    }

    // 4. OBTENER - dado un id
    public Sesion obtenerSesionPorId(Long id) {
        try {
            Optional<Sesion> sesionOpt = sesionRepository.findById(id);
            if (sesionOpt.isPresent()) {
                System.out.println("✓ Sesión encontrada: " + sesionOpt.get().getDia() + " a las " + sesionOpt.get().getHora());
                return sesionOpt.get();
            } else {
                System.out.println("✗ Sesión no encontrada con ID: " + id);
                return null;
            }
        } catch (Exception e) {
            System.out.println("✗ Error al obtener sesión: " + e.getMessage());
            return null;
        }
    }

    // 5. OBTENER - varios objetos dado un atributo diferente del id (por hora)
    public List<Sesion> obtenerSesionesPorHora(String hora) {
        try {
            List<Sesion> sesiones = sesionRepository.findByHora(hora);
            System.out.println("✓ Se encontraron " + sesiones.size() + " sesiones a las: " + hora);
            return sesiones;
        } catch (Exception e) {
            System.out.println("   Error al buscar sesiones por hora: " + e.getMessage());
            return List.of();
        }
    }

    // Método adicional: obtener por día
    public List<Sesion> obtenerSesionesPorDia(String dia) {
        try {
            List<Sesion> sesiones = sesionRepository.findByDia(dia);
            System.out.println("✓ Se encontraron " + sesiones.size() + " sesiones el: " + dia);
            return sesiones;
        } catch (Exception e) {
            System.out.println("✗ Error al buscar sesiones por día: " + e.getMessage());
            return List.of();
        }
    }

    // Obtener todas las sesiones
    public List<Sesion> obtenerTodasLasSesiones() {
        try {
            return sesionRepository.findAll();
        } catch (Exception e) {
            System.out.println("✗ Error al obtener todas las sesiones: " + e.getMessage());
            return List.of();
        }
    }
}
