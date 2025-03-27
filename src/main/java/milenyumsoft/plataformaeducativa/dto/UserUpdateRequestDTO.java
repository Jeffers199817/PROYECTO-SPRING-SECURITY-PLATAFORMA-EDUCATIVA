package milenyumsoft.plataformaeducativa.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import milenyumsoft.plataformaeducativa.modelo.Role;

import java.util.Set;
@Setter
@Getter
@AllArgsConstructor @NoArgsConstructor
@ToString
public class UserUpdateRequestDTO {

    @NotBlank String username;
    @NotBlank String oldPassword;
    String newPassword;
    Set<Role> roleList;
}
