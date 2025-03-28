package milenyumsoft.plataformaeducativa.service;

import milenyumsoft.plataformaeducativa.modelo.Profesor;

import java.util.List;
import java.util.Optional;

public interface IProfersorService {

    List<Profesor> findAllProfersor();
    Optional<Profesor> findByIdProfesor(Long id);
    Profesor createProfesor(Profesor profesor);
    String deleteProfesor(Long id);
    Profesor updateProfesor(Long id, Profesor profesor);
}
