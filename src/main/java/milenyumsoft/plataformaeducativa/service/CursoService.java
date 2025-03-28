package milenyumsoft.plataformaeducativa.service;

import milenyumsoft.plataformaeducativa.modelo.Curso;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CursoService implements ICursoService {


    @Override
    public List<Curso> findAllCurso() {
        return List.of();
    }

    @Override
    public Optional<Curso> findByIdCusro(Long id) {
        return Optional.empty();
    }

    @Override
    public Curso updateCurso(Long id, Curso curso) {
        return null;
    }

    @Override
    public Curso createCurso(Curso curso) {
        return null;
    }

    @Override
    public String deleteCurso(Long id) {
        return "";
    }
}
