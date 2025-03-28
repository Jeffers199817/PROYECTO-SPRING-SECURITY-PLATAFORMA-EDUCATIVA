package milenyumsoft.plataformaeducativa.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name="profesor")
@DiscriminatorValue("PROFESOR")
public class Profesor extends UserSec {

    private String codigoProfesor;
    private String nombre;
    private String apellido;
    @JsonIgnore
    @OneToMany(mappedBy = "profesor", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    List<Curso> listaCursos = new ArrayList<>();


}
