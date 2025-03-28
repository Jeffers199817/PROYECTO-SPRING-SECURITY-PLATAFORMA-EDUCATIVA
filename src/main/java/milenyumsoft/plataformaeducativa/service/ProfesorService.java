package milenyumsoft.plataformaeducativa.service;

import milenyumsoft.plataformaeducativa.modelo.Profesor;
import milenyumsoft.plataformaeducativa.repository.IProfesorRepository;
import milenyumsoft.plataformaeducativa.repository.IUserSecRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfesorService implements IProfersorService {

    @Autowired
    private IProfesorRepository profesorRepository;

    @Autowired
    private IUserSecRepository userSecRepository;



    @Override
    public List<Profesor> findAllProfersor() {
        return profesorRepository.findAll();
    }

    @Override
    public Optional<Profesor> findByIdProfesor(Long id) {

        Optional<Profesor> profesor = profesorRepository.findById(id);

        if(profesor.isPresent()){

           return profesor;
        }else {
            return null;
        }

    }

    @Override
    public Profesor createProfesor(Profesor profesor) {

       Profesor profesorExistente= profesorRepository.traerProfesorCodigo(profesor.getCodigoProfesor());
       if(profesorExistente == null){
           System.out.println("Llegue aquí.");
         return    profesorRepository.save(profesor);
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
