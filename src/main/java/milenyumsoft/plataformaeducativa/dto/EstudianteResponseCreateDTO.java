package milenyumsoft.plataformaeducativa.dto;

import lombok.*;
import milenyumsoft.plataformaeducativa.modelo.Curso;
import milenyumsoft.plataformaeducativa.modelo.Profesor;
import milenyumsoft.plataformaeducativa.modelo.Role;

import java.util.HashSet;
import java.util.Set;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EstudianteResponseCreateDTO {

    private String username;
    private String password;
    private boolean enabled;
    private boolean accountNotExpired;
    private boolean accountNotLocked;
    private boolean credentialNotExpired;
    private Set<String> listProfesor;
    private String matricula;
    private String nombre;
    private String apellido;
    private Set<String> listCursos;
}
