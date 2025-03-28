package milenyumsoft.plataformaeducativa.controller;

import milenyumsoft.plataformaeducativa.dto.ProfesorDTO;
import milenyumsoft.plataformaeducativa.modelo.Profesor;
import milenyumsoft.plataformaeducativa.service.IProfersorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/profesor")
@PreAuthorize("hasRole('ADMIN')")
public class ProfesorController {

    @Autowired
    private IProfersorService profersorService;

    @PostMapping("/crear")
    public ResponseEntity<Profesor> createProfesor(@RequestBody ProfesorDTO profesorDTO){

        Profesor newProfesor = profersorService.createProfesor(profesorDTO);

        return  ResponseEntity.ok(newProfesor);
    }


}
