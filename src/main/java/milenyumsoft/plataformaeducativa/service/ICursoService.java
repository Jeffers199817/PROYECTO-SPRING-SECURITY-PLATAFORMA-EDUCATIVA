package milenyumsoft.plataformaeducativa.service;

import milenyumsoft.plataformaeducativa.modelo.Curso;

import java.util.List;
import java.util.Optional;

public interface ICursoService {


    List<Curso> findAllCurso();
    Optional<Curso> findByIdCusro(Long id);
    Curso updateCurso(Long id,Curso curso);
    Curso createCurso(Curso curso);
    String deleteCurso(Long id);
}
