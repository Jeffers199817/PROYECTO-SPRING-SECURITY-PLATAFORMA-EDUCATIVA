package milenyumsoft.plataformaeducativa.service;

import milenyumsoft.plataformaeducativa.dto.CursoDTO;
import milenyumsoft.plataformaeducativa.modelo.Curso;
import milenyumsoft.plataformaeducativa.modelo.Estudiante;
import milenyumsoft.plataformaeducativa.modelo.Profesor;
import milenyumsoft.plataformaeducativa.repository.ICursoRepository;
import milenyumsoft.plataformaeducativa.repository.IEstudianteRepository;
import milenyumsoft.plataformaeducativa.repository.IProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CursoService implements ICursoService {

    @Autowired
    private ICursoRepository cursoRepository;
    @Autowired
    private IProfersorService profersorService;
    @Autowired
    private IEstudianteRepository estudianteService;

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
    public Curso createCurso(CursoDTO cursodto) {

        Curso cursoExistente = cursoRepository.findCursoByNombre(cursodto.getNombre());

        if (cursoExistente == null) {

            Curso newCurso = new Curso();

            newCurso.setNombre(cursodto.getNombre());
            newCurso.setDescripcion(cursodto.getDescripcion());
            System.out.println("llegue aqui ");
            System.out.println("profesor id: " + cursodto.getProfesorId());

            Optional<Profesor> profesor = profersorService.findByIdProfesor(cursodto.getProfesorId());
            Profesor profesorExistente = profesor.get();

            Set<Estudiante> listEstudiante = cursodto.getEstudiantesId()
                    .stream()
                    .map(estudianteId-> estudianteService.findById(estudianteId))
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .collect(Collectors.toSet());

            newCurso.setProfesor(profesorExistente);
            newCurso.setEstudianteList(listEstudiante);
            System.out.println("llegue con list: " + listEstudiante);

            cursoRepository.save(newCurso);

            return newCurso;

        } else {
            throw new RuntimeException("El curso " + cursodto.getNombre() + " ya existe.");

        }
    }

    @Override
    public String deleteCurso(Long id) {
        return "";
    }
}
