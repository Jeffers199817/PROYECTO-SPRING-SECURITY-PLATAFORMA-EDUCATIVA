package milenyumsoft.plataformaeducativa.dto;

import lombok.*;
import milenyumsoft.plataformaeducativa.modelo.Estudiante;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CursoDTO {

    private String nombre;
    private String descripcion;
    private Long profesorId;
    private Set<Long> estuantesId;
}
