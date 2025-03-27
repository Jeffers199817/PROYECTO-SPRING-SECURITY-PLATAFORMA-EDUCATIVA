package milenyumsoft.plataformaeducativa.controller;

import milenyumsoft.plataformaeducativa.modelo.Role;
import milenyumsoft.plataformaeducativa.modelo.UserSec;
import milenyumsoft.plataformaeducativa.service.IRoleService;
import milenyumsoft.plataformaeducativa.service.IUserSecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.springframework.security.authorization.AuthorityReactiveAuthorizationManager.hasRole;

@RestController
@RequestMapping( "/api/users")
@PreAuthorize("hasRole('ADMIN')")
public class UserSecController {

    @Autowired
    private IUserSecService userSecService;
    @Autowired
    private IRoleService roleService;



    @GetMapping("/todo")
   // @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<List<UserSec>> getAllUsers(){

        List<UserSec> users = userSecService.findAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
  //  @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<UserSec> userSecFindId(@PathVariable Long id){
        Optional<UserSec> user= userSecService.findById(id);
        return user.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
    }

    @PostMapping("/crear")
   // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserSec> createUser(@RequestBody UserSec userSec) {

        //1. Crear un lista de roles y un obtejo Role
        Set<Role> roleList = new HashSet<>();
        Role readRole;

        //2. encriptar la contrase ña que se optiene del usuario
        System.out.println("1");
        userSec.setPassword(userSecService.encriptPassword(userSec.getPassword()));
        System.out.println( "sigo en el 1231bucle ");
        // 3. Recuperamos la permission por su ID

        for (Role role : userSec.getRoleList()){
            readRole = roleService.findById(role.getId()).orElse(null);
            if (readRole != null) {
                //si encuentro, guardo en la lista
                roleList.add(readRole);
            }
        }
        System.out.println("2");
        if (!roleList.isEmpty()) {

            userSec.setRoleList(roleList);
            System.out.println("estoy aqui je");
            UserSec newUser = userSecService.save(userSec);

            return ResponseEntity.ok(newUser);

        }
        System.out.println("3");
        return null;

    }

    public ResponseEntity<UserSec> updateUserSec(@PathVariable Long id, @RequestBody UserSec){


    }



}
