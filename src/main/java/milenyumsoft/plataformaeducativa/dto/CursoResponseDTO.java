package milenyumsoft.plataformaeducativa.dto;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CursoResponseDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private ProfesorDTO profesor;
}
