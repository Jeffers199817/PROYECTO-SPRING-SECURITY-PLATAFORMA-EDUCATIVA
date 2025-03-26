package milenyumsoft.plataformaeducativa.controller;

import milenyumsoft.plataformaeducativa.modelo.Permission;
import milenyumsoft.plataformaeducativa.service.IPermissionService;
import milenyumsoft.plataformaeducativa.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
//@PreAuthorize("denyAll()")
@RequestMapping ( "/api/permission")
public class PermissionController {

    @Autowired
    private IPermissionService permissionService;


    @GetMapping("/todo")
   // @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<List<Permission>> findPermissionAll(){

       List<Permission> listPermission= permissionService.findAll();

       return ResponseEntity.ok(listPermission);
    }

    @GetMapping("/{id}")
   // @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<Permission> findPermissionById(@PathVariable Long id){

       Optional<Permission>  permission = permissionService.findById(id);

       return permission.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
    }

    @PostMapping("/crear")
   // @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<Permission> createPermission(@RequestBody Permission permission){

       Permission newPermission=  permissionService.save(permission);
       return ResponseEntity.ok(newPermission);
    }

    @PutMapping("/update")
    public ResponseEntity<Permission> updatePermission(@RequestBody Permission permission)
    {
      Permission permissionUpdate=  permissionService.update(permission);

    return ResponseEntity.ok(permissionUpdate);
    }
}
