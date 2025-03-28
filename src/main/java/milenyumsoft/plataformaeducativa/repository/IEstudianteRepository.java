package milenyumsoft.plataformaeducativa.repository;

import milenyumsoft.plataformaeducativa.modelo.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEstudianteRepository extends JpaRepository<Long, Estudiante> {
}
