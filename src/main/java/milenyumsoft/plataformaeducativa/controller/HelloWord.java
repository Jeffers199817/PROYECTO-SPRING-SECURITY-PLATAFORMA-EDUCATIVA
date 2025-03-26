package milenyumsoft.plataformaeducativa.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWord {


    @GetMapping("/holaseg")
    public String seguridadSec(){

        return "Con seguridad";
    }

    @GetMapping("holanoseg")
    public String sinSeguridad(){
        return "Sin seguridad";
    }

}
