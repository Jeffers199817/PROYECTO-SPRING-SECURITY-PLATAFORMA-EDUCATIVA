package milenyumsoft.plataformaeducativa.service;

import milenyumsoft.plataformaeducativa.modelo.Curso;
import milenyumsoft.plataformaeducativa.repository.ICursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CursoService implements ICursoService {

    @Autowired
    private ICursoRepository cursoRepository;

    @Override
    public List<Curso> findAllCurso() {
        return cursoRepository.findAll() ;
    }

    @Override
    public Optional<Curso> findByIdCusro(Long id) {

      Optional<Curso> curso=  cursoRepository.findById(id);

      if(!curso.isEmpty()) {


          return curso;
      } else {

          throw  new RuntimeException("No existe el curso.");
      }

    }

    @Override
    public Curso updateCurso(Long id, Curso curso) {
        return null;
    }

    @Override
    public Curso createCurso(Curso curso) {

        return ;
    }

    @Override
    public String deleteCurso(Long id) {
        return "";
    }
}
