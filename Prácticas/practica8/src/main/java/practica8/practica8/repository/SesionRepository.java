package practica8.practica8.repository;

import practica8.practica8.model.Sesion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SesionRepository extends JpaRepository<Sesion, Long> {
    List<Sesion> findByHora(String hora);
    List<Sesion> findByDia(String dia);
}
