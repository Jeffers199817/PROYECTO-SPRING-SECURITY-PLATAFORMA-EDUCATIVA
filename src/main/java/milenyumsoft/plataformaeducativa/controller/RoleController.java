package milenyumsoft.plataformaeducativa.controller;

import milenyumsoft.plataformaeducativa.modelo.Permission;
import milenyumsoft.plataformaeducativa.modelo.Role;
import milenyumsoft.plataformaeducativa.service.IPermissionService;
import milenyumsoft.plataformaeducativa.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping( "/api/rol")
@PreAuthorize("denyAll()")
public class RoleController {

    @Autowired
    private IPermissionService permissionService;
    @Autowired
    private IRoleService roleService;


    @GetMapping("/todo")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<List<Role>> findRoleAll(){
        List<Role> listaRoles = roleService.findAll();
        return ResponseEntity.ok(listaRoles);

    }


    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<Role> findRolId(@PathVariable Long id){

       Optional<Role> role = roleService.findById(id);

        return  role.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
    }

    @GetMapping("/crear")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<Role> createRole(@RequestBody Role role) {
/*
        Set<Permission> listaPermisos = new HashSet<>();
        Permission permission;


     /*
        for (Permission permiss : role.getPermissionsList()) {

            permission = permissionService.findById(permiss.getId()).orElse(null);

            if (permission != null) {
                listaPermisos.add(permission);
            }
        }
*/

        //VAMOS UTILIZAR FUNCIONES LAMDAS

        Set<Permission> listaPermisos = role.getPermissionsList().stream()
                .map(permission -> permissionService.findById(permission.getId()).orElse(null))
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        if (listaPermisos != null){
            role.setPermissionsList(listaPermisos);
        Role newRole = roleService.save(role);
        return ResponseEntity.ok(newRole);

    }
    return null;


    }





}
