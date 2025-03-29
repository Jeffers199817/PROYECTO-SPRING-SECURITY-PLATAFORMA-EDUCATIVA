package milenyumsoft.plataformaeducativa.service;

import milenyumsoft.plataformaeducativa.dto.CursoDTO;
import milenyumsoft.plataformaeducativa.dto.CursoResponseDTO;
import milenyumsoft.plataformaeducativa.dto.ProfesorResponseDTO;
import milenyumsoft.plataformaeducativa.modelo.Curso;
import milenyumsoft.plataformaeducativa.modelo.Estudiante;
import milenyumsoft.plataformaeducativa.modelo.Profesor;
import milenyumsoft.plataformaeducativa.repository.ICursoRepository;
import milenyumsoft.plataformaeducativa.repository.IEstudianteRepository;
import milenyumsoft.plataformaeducativa.repository.IProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CursoService implements ICursoService {

    @Autowired
    private ICursoRepository cursoRepository;
    @Autowired
    private IProfersorService profesorService;
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
    public CursoResponseDTO createCurso(CursoDTO cursodto) {
        // Validar entrada
        if (cursodto == null || cursodto.getNombre() == null || cursodto.getProfesorId() == null) {
            throw new IllegalArgumentException("Los datos del curso son inválidos o incompletos.");
        }

        // Verificar si el curso ya existe
        Curso cursoExistente = cursoRepository.findCursoByNombre(cursodto.getNombre());
        if (cursoExistente != null) {
            throw new IllegalStateException("El curso " + cursodto.getNombre() + " ya existe.");
        }

        // Crear nueva entidad Curso
        Curso newCurso = new Curso();
        newCurso.setNombre(cursodto.getNombre());
        newCurso.setDescripcion(cursodto.getDescripcion());

        // Obtener y validar el profesor
        Optional<Profesor> profesorOpt = profesorService.findByIdProfesor(cursodto.getProfesorId());
        Profesor profesorExistente = profesorOpt.orElseThrow(() ->
                new IllegalArgumentException("No se encontró el profesor con ID: " + cursodto.getProfesorId()));
        newCurso.setProfesor(profesorExistente);

        // Obtener lista de estudiantes con manejo de null
        Set<Estudiante> setEstudiantes = Optional.ofNullable(cursodto.getEstudiantesId())
                .orElse(Collections.emptySet())
                .stream()
                .map(estudianteId -> estudianteService.findById(estudianteId))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());
        newCurso.setEstudianteList(setEstudiantes);

        // Guardar el curso
        Curso cursoGuardado = cursoRepository.save(newCurso);

        // Mapear a DTO de respuesta
        return mapToCursoResponseDTO(cursoGuardado);
    }

    // Método auxiliar para mapear a CursoResponseDTO
    private CursoResponseDTO mapToCursoResponseDTO(Curso curso) {
        CursoResponseDTO dto = new CursoResponseDTO();
        dto.setId(curso.getId());
        dto.setNombre(curso.getNombre());
        dto.setDescripcion(curso.getDescripcion());

        ProfesorResponseDTO profesorDTO = new ProfesorResponseDTO();
        profesorDTO.setId(curso.getProfesor().getId());
        profesorDTO.setNombre(curso.getProfesor().getNombre());
        profesorDTO.setApellido(curso.getProfesor().getApellido());
        profesorDTO.setCodigoProfesor(curso.getProfesor().getCodigoProfesor());
        dto.setProfesor(profesorDTO);

        return dto;
    }

    @Override
    public String deleteCurso(Long id) {
        return "";
    }
}
