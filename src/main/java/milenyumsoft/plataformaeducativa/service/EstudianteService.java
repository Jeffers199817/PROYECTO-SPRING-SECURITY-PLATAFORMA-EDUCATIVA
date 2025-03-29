package milenyumsoft.plataformaeducativa.service;

import milenyumsoft.plataformaeducativa.dto.EstudianteDTO;
import milenyumsoft.plataformaeducativa.dto.EstudianteResponseCreateDTO;
import milenyumsoft.plataformaeducativa.modelo.Curso;
import milenyumsoft.plataformaeducativa.modelo.Estudiante;
import milenyumsoft.plataformaeducativa.modelo.Profesor;
import milenyumsoft.plataformaeducativa.modelo.Role;
import milenyumsoft.plataformaeducativa.repository.ICursoRepository;
import milenyumsoft.plataformaeducativa.repository.IEstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EstudianteService implements IEstudianteService {

    @Autowired
    private IEstudianteRepository estudianteRepository;
    @Autowired
    private IUserSecService userSecService;
    @Autowired
    private IRoleService roleService;
    @Autowired
    private ICursoRepository cursoService;

    @Override
    public Optional<Estudiante> findByIdEstudiante(Long id) {

       Optional<Estudiante> estudianteExistente = estudianteRepository.findById(id);

       if(!estudianteExistente.isEmpty()){

           return estudianteExistente;
       }
        return null;
    }

    @Override
    public List<Estudiante> findAllEstudiante() {
        return estudianteRepository.findAll();
    }

    @Override
    public EstudianteResponseCreateDTO createEstudiante(EstudianteDTO estudianteDTO) {
        EstudianteResponseCreateDTO estudianteResponseCreateDTO = new EstudianteResponseCreateDTO();

      Estudiante estudianteExistente =   estudianteRepository.findEstudianteByNombre(estudianteDTO.getNombre());
     if( estudianteExistente !=null ) {
         return null;
     }else {

         Estudiante estudianteNuevo = new Estudiante();
         estudianteNuevo.setUsername(estudianteDTO.getUsername());
         estudianteNuevo.setPassword(userSecService.encriptPassword(estudianteDTO.getPassword()));
         estudianteNuevo.setEnabled(estudianteDTO.isEnabled());
         estudianteNuevo.setAccountNotExpired(estudianteDTO.isAccountNotExpired());
         estudianteNuevo.setAccountNotLocked(estudianteDTO.isAccountNotLocked());
         estudianteNuevo.setCredentialNotExpired(estudianteDTO.isCredentialNotExpired());
         estudianteNuevo.setMatricula(estudianteDTO.getMatricula());
         estudianteNuevo.setNombre(estudianteDTO.getNombre());
         estudianteNuevo.setApellido(estudianteDTO.getApellido());

         System.out.println("Datos básicos asignados");

         try {
             Set<Role> listRole = estudianteDTO.getRolesId()
                     .stream()
                     .map(id -> roleService.findById(id))
                     .peek(opt -> System.out.println("Role Optional: " + opt))
                     .filter(Optional::isPresent)
                     .map(Optional::get)
                     .collect(Collectors.toSet());
             System.out.println("Roles procesados: " + listRole);

             Set<Curso> listCurso = estudianteDTO.getCursosId()
                     .stream()
                     .map(cursoId -> cursoService.findById(cursoId))
                     .peek(opt -> System.out.println("Curso Optional: " + opt))
                     .filter(Optional::isPresent)
                     .map(Optional::get)
                     .collect(Collectors.toSet());
             System.out.println("Cursos procesados: " + listCurso);

             estudianteNuevo.setRoleList(listRole);
             System.out.println("Roles asignados: " + listRole);
             estudianteNuevo.setCursosList(listCurso);
             System.out.println("Cursos asignados: " + listCurso);

            // estudianteRepository.save(estudianteNuevo);
             System.out.println("Estudiante guardado: " + estudianteNuevo);


    /*

             private String username;
             private String password;
             private boolean enabled;
             private boolean accountNotExpired;
             private boolean accountNotLocked;
             private boolean credentialNotExpired;
             private Set<Profesor> listProfesor;
             private String matricula;
             private String nombre;
             private String apellido;
             private Set<Curso> listCursos;
       */
             Set<String> listProfesor = new HashSet<>();

             for(Curso curso: listCurso) {
                 Profesor profe = curso.getProfesor();
                 listProfesor.add(profe.getNombre());
             }

             Set<String> listCursos = new HashSet<>();

             for(Curso cur : listCurso){

                 listCursos.add(cur.getNombre());
             }

             estudianteResponseCreateDTO.setUsername(estudianteNuevo.getUsername());
             estudianteResponseCreateDTO.setPassword(estudianteNuevo.getPassword());
             estudianteResponseCreateDTO.setEnabled(estudianteNuevo.isEnabled());
             estudianteResponseCreateDTO.setAccountNotExpired(estudianteNuevo.isAccountNotExpired());
             estudianteResponseCreateDTO.setAccountNotLocked(estudianteNuevo.isAccountNotLocked());
             estudianteResponseCreateDTO.setCredentialNotExpired(estudianteNuevo.isCredentialNotExpired());
             estudianteResponseCreateDTO.setListProfesor(listProfesor);
             estudianteResponseCreateDTO.setMatricula(estudianteNuevo.getMatricula());
             estudianteResponseCreateDTO.setNombre(estudianteNuevo.getNombre());
             estudianteResponseCreateDTO.setApellido(estudianteNuevo.getApellido());
             estudianteResponseCreateDTO.setListCursos(listCursos);


             return estudianteResponseCreateDTO;


         } catch (Exception e) {
             System.out.println("Error en el proceso: " + e.getMessage());
             e.printStackTrace();
             throw e;
         }


     }

    }

    @Override
    public Estudiante updateEstudiante(Long id, Estudiante estudiante) {
        return null;
    }

    @Override
    public String eliminarEstudiante(Long id) {
        return "";
    }
}
