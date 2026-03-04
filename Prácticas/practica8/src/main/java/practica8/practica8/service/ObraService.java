package practica8.practica8.service;

import practica8.practica8.model.Obra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.List;

@Service
public class ObraService {

    @Autowired
    private EntityManager entityManager;

    // 1. INSERTAR - devuelve el id del objeto insertado
    @Transactional
    public Long insertarObra(Obra obra) {
        try {
            entityManager.persist(obra);
            entityManager.flush();
            System.out.println("Obra insertada: " + obra.getNombre() + " con ID: " + obra.getId());
            return obra.getId();
        } catch (Exception e) {
            System.out.println("Error al insertar obra: " + e.getMessage());
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
    public void borrarObra(Obra obra) {
        try {
            Obra obraEncontrada = entityManager.find(Obra.class, obra.getId());
            if (obraEncontrada != null) {
                entityManager.remove(obraEncontrada);
                System.out.println("Obra eliminada: " + obra.getNombre());
            }
        } catch (Exception e) {
            System.out.println("Error al borrar obra: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }

    // 3. ACTUALIZAR - dado un id y un atributo (actualizar autor)
    @Transactional
    public void actualizarAutorObra(Long id, String nuevoAutor) {
        try {
            Obra obra = entityManager.find(Obra.class, id);
            if (obra != null) {
                obra.setAutor(nuevoAutor);
                entityManager.merge(obra);
                System.out.println("Obra actualizada: " + obra.getNombre() + " - Nuevo autor: " + nuevoAutor);
            } else {
                System.out.println("Obra no encontrada con ID: " + id);
            }
        } catch (Exception e) {
            System.out.println("Error al actualizar obra: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }

    // 4. OBTENER - dado un id
    public Obra obtenerObraPorId(Long id) {
        try {
            Obra obra = entityManager.find(Obra.class, id);
            if (obra != null) {
                System.out.println("Obra encontrada: " + obra.getNombre());
                return obra;
            } else {
                System.out.println("Obra no encontrada con ID: " + id);
                return null;
            }
        } catch (Exception e) {
            System.out.println("Error al obtener obra: " + e.getMessage());
            e.printStackTrace();
            return null;
        } finally {
            if (entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }

    // 5. OBTENER - varios objetos dado un atributo diferente del id (por autor)
    public List<Obra> obtenerObrasPorAutor(String autor) {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Obra> criteriaQuery = criteriaBuilder.createQuery(Obra.class);
            Root<Obra> root = criteriaQuery.from(Obra.class);
            
            criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("autor"), autor));
            
            List<Obra> obras = entityManager.createQuery(criteriaQuery).getResultList();
            System.out.println("Se encontraron " + obras.size() + " obras del autor: " + autor);
            return obras;
        } catch (Exception e) {
            System.out.println("Error al buscar obras por autor: " + e.getMessage());
            e.printStackTrace();
            return List.of();
        } finally {
            if (entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }

    // Método adicional: obtener por género
    public List<Obra> obtenerObrasPorGenero(String genero) {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Obra> criteriaQuery = criteriaBuilder.createQuery(Obra.class);
            Root<Obra> root = criteriaQuery.from(Obra.class);
            
            criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("genero"), genero));
            
            List<Obra> obras = entityManager.createQuery(criteriaQuery).getResultList();
            System.out.println("Se encontraron " + obras.size() + " obras del género: " + genero);
            return obras;
        } catch (Exception e) {
            System.out.println("Error al buscar obras por género: " + e.getMessage());
            e.printStackTrace();
            return List.of();
        } finally {
            if (entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }

    // Obtener todas las obras
    public List<Obra> obtenerTodasLasObras() {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Obra> criteriaQuery = criteriaBuilder.createQuery(Obra.class);
            Root<Obra> root = criteriaQuery.from(Obra.class);
            
            criteriaQuery.select(root);
            
            return entityManager.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            System.out.println("Error al obtener todas las obras: " + e.getMessage());
            e.printStackTrace();
            return List.of();
        } finally {
            if (entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }
}