package milenyumsoft.plataformaeducativa.controller;

import milenyumsoft.plataformaeducativa.dto.ProfesorDTO;
import milenyumsoft.plataformaeducativa.modelo.Profesor;
import milenyumsoft.plataformaeducativa.service.IProfersorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/profesor")
//@PreAuthorize("hasRole('ADMIN')")
@PreAuthorize("hasRole('ADMIN') or hasRole('PROFESOR')")
public class ProfesorController {

    @Autowired
    private IProfersorService profersorService;

    @PostMapping("/crear")
    public ResponseEntity<Profesor> createProfesor(@RequestBody ProfesorDTO profesorDTO){

        Profesor newProfesor = profersorService.createProfesor(profesorDTO);

        return  ResponseEntity.ok(newProfesor);
    }


    @GetMapping("/traer/{id}")
    public ResponseEntity<Profesor> findByIdProfesor(@PathVariable Long id){
        System.out.println("El id es: " + id);

        Optional<Profesor> profesor = profersorService.findByIdProfesor(id);
       Profesor profeActual= profesor.get();

       return ResponseEntity.ok(profeActual);

    }


}
