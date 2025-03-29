package milenyumsoft.plataformaeducativa.controller;

import milenyumsoft.plataformaeducativa.dto.EstudianteDTO;
import milenyumsoft.plataformaeducativa.modelo.Estudiante;
import milenyumsoft.plataformaeducativa.service.IEstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@PreAuthorize("hasRole('ADMIN') or hasrole('PROFESOR')")
@RequestMapping("/api/estudiante")
public class EstudianteController {
    @Autowired
    private IEstudianteService estudianteService;

    @GetMapping("/traer/todo")
    public ResponseEntity<List<Estudiante>> findAllEstudiante(){

        List<Estudiante> listEstudiante = estudianteService.findAllEstudiante();
        return ResponseEntity.ok(listEstudiante);
    }


    @PostMapping("/crear")
    public ResponseEntity<Estudiante> crearEstudiante(@RequestBody EstudianteDTO estudianteDTO){

      Estudiante estudiante=  estudianteService.createEstudiante(estudianteDTO);

        return ResponseEntity.ok(estudiante);
    }
}
