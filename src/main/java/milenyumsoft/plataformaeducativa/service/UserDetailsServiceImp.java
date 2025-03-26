package milenyumsoft.plataformaeducativa.service;

import milenyumsoft.plataformaeducativa.modelo.UserSec;
import milenyumsoft.plataformaeducativa.repository.IUserSecRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

    @Autowired
    private IUserSecRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        //1.- Traemos el usuario sec y devolvemos en userdetails

        UserSec userSec = userRepo.findUserEntityByUsername(username)
                .orElseThrow(()->new UsernameNotFoundException("El usuario " + username + " no fue encontrado"));

        //2.- Creamos un instancia de grantedAuthority

        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();

        //3.- Programación funcional tomamos los roles y los converitmos en simplegrantedauthority para poder agregarlos a la authoritylist.

        userSec.getRoleList()
                .forEach(role->authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(role.getRole()))));

        //4. Pasar a grantedauthority a los permissions

        userSec.getRoleList().stream()
                .flatMap(role->role.getPermissionList().stream())
                .forEach(permission ->authorityList.add(new SimpleGrantedAuthority(permission.getPermissionName())) );

        //5. retornamos el usaurio en formato spring security con loedd datos de nuestro usersec


        return new User(
                userSec.getUsername(),
                userSec.getPassword(),
                userSec.isEnabled(),
                userSec.isAccountNotExpired(),
                userSec.isCredentialNotExpired(),
                userSec.isAccountNotLocked(),
                authorityList);
    }
}
