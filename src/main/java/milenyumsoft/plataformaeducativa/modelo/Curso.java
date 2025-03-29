package milenyumsoft.plataformaeducativa.modelo;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name="curso")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String descripcion;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="profesor_id")
    private Profesor profesor;

    @ManyToMany(mappedBy = "cursosList", fetch = FetchType.EAGER)
    @ToString.Exclude
    private Set<Estudiante> estudianteList = new HashSet<>();



}
