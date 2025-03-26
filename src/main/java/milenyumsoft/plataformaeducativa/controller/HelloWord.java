package milenyumsoft.plataformaeducativa.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PreAuthorize("denyAll()")
@RequestMapping("/interes")
public class HelloWord {

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/holaseg")
    public String seguridadSec(){

        return "Con seguridad, inicio de sesión exitoso";
    }
    @PreAuthorize("permitAll()")
    @GetMapping("holanoseg")
    public String sinSeguridad(){
        return "Sin seguridad.";
    }

}
