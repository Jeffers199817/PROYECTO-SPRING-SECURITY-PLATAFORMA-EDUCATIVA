package milenyumsoft.plataformaeducativa.service;

import milenyumsoft.plataformaeducativa.dto.RoleDTO;
import milenyumsoft.plataformaeducativa.modelo.Role;

import java.util.List;
import java.util.Optional;

public interface IRoleService {


    List<Role> findAll();
    Optional<Role> findById(Long id);
    Role save(Role role);
    String deleteById(Long id);
    String update(RoleDTO roleDTO);
}
