package milenyumsoft.plataformaeducativa.dto;

import lombok.*;

import java.util.List;
import java.util.Set;

@Setter @Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EstudianteDTO {

    private String username;
    private String password;
    private boolean enabled;
    private boolean accountNotExpired;
    private boolean accountNotLocked;
    private boolean credentialNotExpired;
    private Set<Long> rolesId;
    private String matricula;
    private String nombre;
    private String apellido;
    private Set<Long> cursosId;


}
