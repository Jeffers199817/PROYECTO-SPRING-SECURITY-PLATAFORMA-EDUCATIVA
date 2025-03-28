package milenyumsoft.plataformaeducativa.service;

import milenyumsoft.plataformaeducativa.dto.EstudianteDTO;
import milenyumsoft.plataformaeducativa.modelo.Curso;
import milenyumsoft.plataformaeducativa.modelo.Estudiante;
import milenyumsoft.plataformaeducativa.modelo.Role;
import milenyumsoft.plataformaeducativa.repository.IEstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
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
    private ICursoService cursoService;

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
    public Estudiante createEstudiante(EstudianteDTO estudianteDTO) {

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

         Set<Role> listRole = estudianteDTO.getRolesId()
                 .stream()
                 .map(id -> roleService.findById(id))
                 .filter(Optional::isPresent)
                 .map(Optional::get)
                 .collect(Collectors.toSet());

         Set<Curso> listCurso = estudianteDTO.getCursosId()
                 .stream()
                 .map(cursoId -> cursoService.findByIdCusro(cursoId))
                 .filter(Optional::isPresent)
                 .map(Optional::get)
                 .collect(Collectors.toSet());

         estudianteNuevo.setRoleList(listRole);
         estudianteNuevo.setCursosList(listCurso);


        return estudianteRepository.save(estudianteNuevo);
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
