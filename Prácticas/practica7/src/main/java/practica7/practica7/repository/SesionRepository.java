package practica7.practica7.repository;

import practica7.practica7.model.Sesion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SesionRepository extends JpaRepository<Sesion, Long> {
    List<Sesion> findByHora(String hora);
    List<Sesion> findByDia(String dia);
}
