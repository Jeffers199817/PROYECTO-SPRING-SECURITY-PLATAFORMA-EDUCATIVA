package milenyumsoft.plataformaeducativa.service;

import milenyumsoft.plataformaeducativa.dto.CursoDTO;
import milenyumsoft.plataformaeducativa.dto.CursoResponseDTO;
import milenyumsoft.plataformaeducativa.dto.EstudianteResponseDTO;
import milenyumsoft.plataformaeducativa.dto.ProfesorResponseDTO;
import milenyumsoft.plataformaeducativa.modelo.Curso;
import milenyumsoft.plataformaeducativa.modelo.Estudiante;
import milenyumsoft.plataformaeducativa.modelo.Profesor;
import milenyumsoft.plataformaeducativa.repository.ICursoRepository;
import milenyumsoft.plataformaeducativa.repository.IEstudianteRepository;
import milenyumsoft.plataformaeducativa.repository.IProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CursoService implements ICursoService {

    @Autowired
    private ICursoRepository cursoRepository;
    @Autowired
    private IProfersorService profesorService;
    @Autowired
    private IEstudianteService estudianteService;

    @Override
    public List<Curso> findAllCurso() {
        return cursoRepository.findAll();
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
    public CursoResponseDTO createCurso(CursoDTO cursoDTO) {

        Curso curso;
        Curso newCurso= new Curso();


        try{

            curso =   cursoRepository.findCursoByNombre(cursoDTO.getNombre());
            if(curso==null){

                newCurso.setNombre(cursoDTO.getNombre());
                newCurso.setDescripcion(cursoDTO.getDescripcion());

                //2. Asignar el id del profesor.
                 Optional<Profesor> profesor = profesorService.findByIdProfesor(cursoDTO.getProfesorId());
                 Profesor profeExistente= profesor.get();

                 newCurso.setProfesor(profeExistente);

                 //3. Asignar la list e estudiante


                cursoRepository.save(newCurso);


                CursoResponseDTO cursoResponseDTO = new CursoResponseDTO();

                cursoResponseDTO.setId(newCurso.getId());
                cursoResponseDTO.setNombre(newCurso.getNombre());
                cursoResponseDTO.setDescripcion(newCurso.getDescripcion());
                cursoResponseDTO.setNombreProfesor(profeExistente.getNombre());

                return cursoResponseDTO;

            }else{

                    throw new RuntimeException("No se puede crear el curso, ya existe.");



            }



        }catch (Exception e ){

            throw new RuntimeException("No se puede crear el curso, ya existe.");
        }


    }

    @Override
    public String deleteCurso(Long id) {
        return "";
    }
}
