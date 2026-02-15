package practica7.practica7.repository;

import practica7.practica7.model.Obra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ObraRepository extends JpaRepository<Obra, Long> {
    List<Obra> findByAutor(String autor);
    List<Obra> findByGenero(String genero);
}
