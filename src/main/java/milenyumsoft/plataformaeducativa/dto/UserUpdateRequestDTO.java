package milenyumsoft.plataformaeducativa.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import milenyumsoft.plataformaeducativa.modelo.Role;

import java.util.Set;

public class UserUpdateRequestDTO {

    @NotBlank String username;
    @NotBlank String oldPassword;
    String newPassword;
    Set<Role> roleList;
}
