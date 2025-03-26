package milenyumsoft.plataformaeducativa.service;

import milenyumsoft.plataformaeducativa.modelo.Permission;
import milenyumsoft.plataformaeducativa.repository.IPermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PermissionService implements IPermissionService{

    @Autowired
    private IPermissionRepository permissionRepository;

    @Override
    public List<Permission> findAll() {
        return permissionRepository.findAll() ;
    }

    @Override
    public Optional<Permission> findById(Long id) {
        return permissionRepository.findById(id);
    }

    @Override
    public Permission save(Permission permission) {
        return permissionRepository.save(permission);
    }

    @Override
    public void deleteById(Long id) {
        permissionRepository.deleteById(id);
    }

    @Override
    public Permission update(Permission permission) {

          Optional<Permission> permission1=  this.findById(permission.getId());

          if(permission1.isPresent()) {
             Permission permi= permission1.get();
             permi.setPermissionName(permission.getPermissionName());


              return permissionRepository.save(permi);

          }else {
              throw new RuntimeException("Permission with ID" + permission.getId() + "no existe");
          }


    }
}
