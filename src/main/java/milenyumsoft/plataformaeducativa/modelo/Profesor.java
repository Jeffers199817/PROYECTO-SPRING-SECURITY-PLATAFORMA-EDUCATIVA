package milenyumsoft.plataformaeducativa.modelo;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name="profesores")
@DiscriminatorValue("PROFESOR")
public class Profesor extends UserSec {

    private String codigoProfesor;
    private String nombre;
    private String apellido;

    @OneToMany()
    List<Curso> listaCursos = new ArrayList<>();


}
