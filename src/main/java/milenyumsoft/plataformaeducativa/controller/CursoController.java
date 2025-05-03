package milenyumsoft.plataformaeducativa.controller;

import jakarta.servlet.http.HttpServletResponse;
import milenyumsoft.plataformaeducativa.dto.CursoDTO;
import milenyumsoft.plataformaeducativa.dto.CursoResponseDTO;
import milenyumsoft.plataformaeducativa.modelo.Curso;
import milenyumsoft.plataformaeducativa.repository.ICursoRepository;
import milenyumsoft.plataformaeducativa.service.ICursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping( "/api/curso")
@PreAuthorize("hasRole('ADMIN') or hasRole('PROFESOR')")
public class CursoController {

    @Autowired
    private ICursoService cursoService;

    @GetMapping("/traer/{id}")
    public ResponseEntity<Curso> findByIdCurso(@PathVariable Long id){

        Optional<Curso> cursoExistente=  cursoService.findByIdCusro(id);
        if(!cursoExistente.isEmpty()){
          Curso cursoExtraido=  cursoExistente.get();
          return ResponseEntity.ok(cursoExtraido);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }



    @PostMapping("/crear")
    public ResponseEntity<CursoResponseDTO> crearCurso(@RequestBody CursoDTO cursoDTO){
System.out.println("this is methos created a new student");
     return ResponseEntity.ok(cursoService.createCurso(cursoDTO));
    }
}
