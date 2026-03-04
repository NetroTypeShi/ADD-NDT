package practica8.practica8.repository;

import practica8.practica8.model.Obra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ObraRepository extends JpaRepository<Obra, Long> {
    List<Obra> findByAutor(String autor);
    List<Obra> findByGenero(String genero);
}