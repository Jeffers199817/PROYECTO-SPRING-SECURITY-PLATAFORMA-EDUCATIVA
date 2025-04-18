package milenyumsoft.plataformaeducativa.dto;


import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CursoResponseDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private String nombreProfesor;

}
