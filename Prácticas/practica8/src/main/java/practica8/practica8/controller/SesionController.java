package practica8.practica8.service;

import practica8.practica8.model.Sesion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.List;

@Service
public class SesionService {

    @Autowired
    private EntityManager entityManager;

    // 1. INSERTAR - devuelve el id del objeto insertado
    @Transactional
    public Long insertarSesion(Sesion sesion) {
        try {
            entityManager.persist(sesion);
            entityManager.flush();
            System.out.println("Sesión insertada: " + sesion.getDia() + " a las " + sesion.getHora() + " con ID: " + sesion.getId());
            return sesion.getId();
        } catch (Exception e) {
            System.out.println("Error al insertar sesión: " + e.getMessage());
            e.printStackTrace();
            return null;
        } finally {
            if (entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }

    // 2. BORRAR - dado un objeto
    @Transactional
    public void borrarSesion(Sesion sesion) {
        try {
            Sesion sesionEncontrada = entityManager.find(Sesion.class, sesion.getId());
            if (sesionEncontrada != null) {
                entityManager.remove(sesionEncontrada);
                System.out.println("Sesión eliminada: " + sesion.getDia() + " a las " + sesion.getHora());
            }
        } catch (Exception e) {
            System.out.println("Error al borrar sesión: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }

    // 3. ACTUALIZAR - dado un id y un atributo (actualizar hora)
    @Transactional
    public void actualizarHoraSesion(Long id, String nuevaHora) {
        try {
            Sesion sesion = entityManager.find(Sesion.class, id);
            if (sesion != null) {
                sesion.setHora(nuevaHora);
                entityManager.merge(sesion);
                System.out.println("Sesión actualizada: Nueva hora: " + nuevaHora);
            } else {
                System.out.println("Sesión no encontrada con ID: " + id);
            }
        } catch (Exception e) {
            System.out.println("Error al actualizar sesión: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }

    // 4. OBTENER - dado un id
    public Sesion obtenerSesionPorId(Long id) {
        try {
            Sesion sesion = entityManager.find(Sesion.class, id);
            if (sesion != null) {
                System.out.println("Sesión encontrada: " + sesion.getDia() + " a las " + sesion.getHora());
                return sesion;
            } else {
                System.out.println("Sesión no encontrada con ID: " + id);
                return null;
            }
        } catch (Exception e) {
            System.out.println("Error al obtener sesión: " + e.getMessage());
            e.printStackTrace();
            return null;
        } finally {
            if (entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }

    // 5. OBTENER - varios objetos dado un atributo diferente del id (por hora)
    public List<Sesion> obtenerSesionesPorHora(String hora) {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Sesion> criteriaQuery = criteriaBuilder.createQuery(Sesion.class);
            Root<Sesion> root = criteriaQuery.from(Sesion.class);
            
            criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("hora"), hora));
            
            List<Sesion> sesiones = entityManager.createQuery(criteriaQuery).getResultList();
            System.out.println("Se encontraron " + sesiones.size() + " sesiones a las: " + hora);
            return sesiones;
        } catch (Exception e) {
            System.out.println("Error al buscar sesiones por hora: " + e.getMessage());
            e.printStackTrace();
            return List.of();
        } finally {
            if (entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }

    // 6. OBTENER - por día
    public List<Sesion> obtenerSesionesPorDia(String dia) {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Sesion> criteriaQuery = criteriaBuilder.createQuery(Sesion.class);
            Root<Sesion> root = criteriaQuery.from(Sesion.class);
            
            criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("dia"), dia));
            
            List<Sesion> sesiones = entityManager.createQuery(criteriaQuery).getResultList();
            System.out.println("Se encontraron " + sesiones.size() + " sesiones el: " + dia);
            return sesiones;
        } catch (Exception e) {
            System.out.println("Error al buscar sesiones por día: " + e.getMessage());
            e.printStackTrace();
            return List.of();
        } finally {
            if (entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }

    // 7. OBTENER todas las sesiones
    public List<Sesion> obtenerTodasLasSesiones() {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Sesion> criteriaQuery = criteriaBuilder.createQuery(Sesion.class);
            Root<Sesion> root = criteriaQuery.from(Sesion.class);
            
            criteriaQuery.select(root);
            
            return entityManager.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            System.out.println("Error al obtener todas las sesiones: " + e.getMessage());
            e.printStackTrace();
            return List.of();
        } finally {
            if (entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }

    // 8. ACTUALIZAR sesión completa (para cambiar clave externa)
    @Transactional
    public void actualizarSesion(Long id, Sesion sesionActualizada) {
        try {
            Sesion sesion = entityManager.find(Sesion.class, id);
            if (sesion != null) {
                sesion.setHora(sesionActualizada.getHora());
                sesion.setDia(sesionActualizada.getDia());
                sesion.setSala(sesionActualizada.getSala());
                sesion.setObra(sesionActualizada.getObra());
                entityManager.merge(sesion);
                System.out.println("Sesión actualizada: ID " + id);
            } else {
                System.out.println("Sesión no encontrada con ID: " + id);
            }
        } catch (Exception e) {
            System.out.println("Error al actualizar sesión: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }
}