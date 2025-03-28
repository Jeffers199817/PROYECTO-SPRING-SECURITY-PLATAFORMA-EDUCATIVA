package milenyumsoft.plataformaeducativa.service;

import milenyumsoft.plataformaeducativa.dto.RoleDTO;
import milenyumsoft.plataformaeducativa.modelo.Permission;
import milenyumsoft.plataformaeducativa.modelo.Role;
import milenyumsoft.plataformaeducativa.repository.IPermissionRepository;
import milenyumsoft.plataformaeducativa.repository.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class RoleService implements IRoleService{

    @Autowired
    private IRoleRepository roleRepository;

    @Autowired
    private IPermissionRepository permissionRepository;

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Optional<Role> findById(Long id) {
        return roleRepository.findById(id);
    }

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public String deleteById(Long id) {
        Optional<Role> rolAEliminar = this.findById(id);
            if(!rolAEliminar.isEmpty()){
            roleRepository.deleteById(id);
            return "Rol eliminado exitosamente.";}

            return "Rol no existe.";
    }

    @Override
    public String update(RoleDTO roleDTO) {

        Optional<Role> role1 = this.findById(roleDTO.getId());

        Set<Permission> permissionsList = new HashSet<>();
        Optional<Permission> permi;


        if(!role1.isPresent()){
            return "Role con id : " + roleDTO.getId() + " No existe.";
        }

        Role roleExistente = role1.get();
        roleExistente.setRole(roleDTO.getRole());
            for( Long id: roleDTO.getPermissionsIds()){

                permi = permissionRepository.findById(id);

                if(permi.isPresent()){
                    Permission permiExistente= permi.get();
                    permissionsList.add(permiExistente);
                }else {
                    return "No existe permisos con ese id: " + id + " Ingrese un permiso con id valido." + "  No se pudo actualizar rol vuelva a intentarlo";
                }
            }



        roleExistente.setPermissionList(permissionsList);

        roleRepository.save(roleExistente);


        return "Rol actualizado Correctamente: " + roleExistente.getRole() +" Permisos: " +  roleExistente.getPermissionList();
    }
}
