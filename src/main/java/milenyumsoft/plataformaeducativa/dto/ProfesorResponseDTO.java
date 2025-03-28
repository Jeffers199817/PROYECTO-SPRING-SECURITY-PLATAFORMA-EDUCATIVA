package milenyumsoft.plataformaeducativa.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProfesorResponseDTO {
    private Long id;
    private String nombre;
    private String apellido;
    private String codigoProfesor;
}
