package milenyumsoft.plataformaeducativa.modelo;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.security.core.userdetails.User;

import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name="estudiante")
@DiscriminatorValue("ESTUDIANTE")
public class Estudiante extends UserSec {

    private String matricula;
    private String nombre;
    private String apellido;


    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(name="curso_estudiante",
    joinColumns = @JoinColumn(name="estudiante_id"),
    inverseJoinColumns = @JoinColumn(name="curso_id"))
    private Set<Curso> cursosList = new HashSet<>() ;



}
