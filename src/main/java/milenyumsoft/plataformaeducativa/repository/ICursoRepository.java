package milenyumsoft.plataformaeducativa.repository;

import milenyumsoft.plataformaeducativa.modelo.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICursoRepository extends JpaRepository<Long, Curso> {
}
