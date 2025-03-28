package milenyumsoft.plataformaeducativa.service;

import milenyumsoft.plataformaeducativa.modelo.Estudiante;

import java.util.List;
import java.util.Optional;

public interface IEstudianteService {


    Optional<Estudiante> findByIdEstudiante(Long id);
    List<Estudiante> findAllEstudiante();
    Estudiante createEstudiante(Estudiante estudiante);
    Estudiante updateEstudiante(Long id, Estudiante estudiante);
    String eliminarEstudiante(Long id);
}
