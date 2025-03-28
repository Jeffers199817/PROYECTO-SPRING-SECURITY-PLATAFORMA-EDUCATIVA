package milenyumsoft.plataformaeducativa.service;

import milenyumsoft.plataformaeducativa.dto.ProfesorDTO;
import milenyumsoft.plataformaeducativa.modelo.Profesor;
import milenyumsoft.plataformaeducativa.modelo.Role;
import milenyumsoft.plataformaeducativa.repository.IProfesorRepository;
import milenyumsoft.plataformaeducativa.repository.IRoleRepository;
import milenyumsoft.plataformaeducativa.repository.IUserSecRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProfesorService implements IProfersorService {

    @Autowired
    private IProfesorRepository profesorRepository;

    @Autowired
    private IUserSecService iUserSecService;
    @Autowired
    private UserSecService userSecService;

    @Autowired
    private IRoleRepository roleRepository;


    @Override
    public List<Profesor> findAllProfersor() {
        return profesorRepository.findAll();
    }

    @Override
    public Optional<Profesor> findByIdProfesor(Long id) {

        Optional<Profesor> profesor = profesorRepository.findById(id);
        System.out.println("Legue aqui");
        System.out.println("esto es ; " + profesor);

        if(!profesor.isEmpty()){

           return profesor;
        }else {
            return null;
        }

    }

    @Override
    public Profesor createProfesor(ProfesorDTO profesorDTO) {

       Profesor profesorExistente= profesorRepository.traerProfesorCodigo(profesorDTO.getCodigoProfesor());
       if(profesorExistente == null){

            Profesor newProfesor = new Profesor();
            newProfesor.setUsername(profesorDTO.getUsername());
            newProfesor.setPassword(userSecService.encriptPassword(profesorDTO.getPassword()));
            newProfesor.setEnabled(profesorDTO.isEnabled());
            newProfesor.setAccountNotExpired(profesorDTO.isAccountNotExpired());
            newProfesor.setAccountNotLocked(profesorDTO.isAccountNotLocked());
            newProfesor.setCredentialNotExpired(profesorDTO.isCredentialNotExpired());
            newProfesor.setCodigoProfesor(profesorDTO.getCodigoProfesor());
            newProfesor.setNombre(profesorDTO.getNombre());
            newProfesor.setApellido(profesorDTO.getApellido());

            //Cargar los roles des de la base de datos basado en los id

           Set<Role> listRole = profesorDTO.getRoleIds().stream()
                   .map(roleId-> roleRepository.findById(roleId).get())
                   .collect(Collectors.toSet());

            newProfesor.setRoleList(listRole);


         return    profesorRepository.save(newProfesor);
       }else{
           throw new RuntimeException("Profesor con ese código ya existe, no se puede crear.");
       }

    }

    @Override
    public String deleteProfesor(Long id) {
        return "";
    }

    @Override
    public Profesor updateProfesor(Long id, Profesor profesor) {
        return null;
    }
}
