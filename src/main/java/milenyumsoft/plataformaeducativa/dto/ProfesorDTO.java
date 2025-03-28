package milenyumsoft.plataformaeducativa.dto;

import lombok.*;

import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProfesorDTO {
    private String username;
    private String password;
    private boolean enabled;
    private boolean accountNotExpired;
    private boolean accountNotLocked;
    private boolean credentialNotExpired;
    private Set<Long> roleIds; // Lista de IDs de roles
    private String codigoProfesor;
    private String nombre;
    private String apellido;
}
