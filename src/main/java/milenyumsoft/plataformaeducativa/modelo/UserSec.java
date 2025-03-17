package milenyumsoft.plataformaeducativa.modelo;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter @Getter
@AllArgsConstructor @NoArgsConstructor @ToString
@Table(name="users")
public class UserSec {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String username;



}
