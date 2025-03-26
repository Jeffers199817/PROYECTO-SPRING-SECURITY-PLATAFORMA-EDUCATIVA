package milenyumsoft.plataformaeducativa.dto;
import lombok.*;

import java.util.List;

@Getter @Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RoleDTO {

    private Long id;
    private String role;
    private List<Long> permissionsIds;


}
