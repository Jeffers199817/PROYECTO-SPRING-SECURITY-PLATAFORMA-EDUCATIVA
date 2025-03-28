package milenyumsoft.plataformaeducativa.service;

import milenyumsoft.plataformaeducativa.modelo.Estudiante;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstudianteService implements IEstudianteService {
    @Override
    public Optional<Estudiante> findByIdEstudiante(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Estudiante> findAllEstudiante() {
        return List.of();
    }

    @Override
    public Estudiante createEstudiante(Estudiante estudiante) {
        return null;
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
