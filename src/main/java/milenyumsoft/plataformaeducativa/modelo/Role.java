package milenyumsoft.plataformaeducativa.modelo;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor @ToString
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String role;

    //Usamos la lista Set porque no permite repetidos

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name="roles_permissions", joinColumns = @JoinColumn(name= "role_id")
            ,inverseJoinColumns = @JoinColumn(name="permission_id"))
    @ToString.Exclude
    private Set<Permission> permissionList = new HashSet<>();
}
