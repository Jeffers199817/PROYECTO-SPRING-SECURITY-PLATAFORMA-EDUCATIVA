package milenyumsoft.plataformaeducativa.service;

import milenyumsoft.plataformaeducativa.dto.AuthLoginRequestDTO;
import milenyumsoft.plataformaeducativa.dto.AuthResponseDTO;
import milenyumsoft.plataformaeducativa.modelo.UserSec;
import milenyumsoft.plataformaeducativa.repository.IUserSecRepository;
import milenyumsoft.plataformaeducativa.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImp implements UserDetailsService {
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private IUserSecRepository userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

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


    public AuthResponseDTO loginUser(AuthLoginRequestDTO authLoginRequestDTO){

        //recuperamos nombre de usuario y contraseña
        String username = authLoginRequestDTO.username();
        String password = authLoginRequestDTO.password();

            Authentication authentication = this.authenticate (username, password);
            //si todo sale bien
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String accessToken = jwtUtils.createToken(authentication);
            AuthResponseDTO authResponseDTO = new AuthResponseDTO(username, "login ok", accessToken, true);
            return authResponseDTO;

        }



    public Authentication authenticate (String username, String password) {
        //con esto debo buscar el usuario
        UserDetails userDetails = this.loadUserByUsername(username);

        if (userDetails == null) {
            throw new BadCredentialsException("Invalid username or password");
        }
        // si no es igual
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }
        return new UsernamePasswordAuthenticationToken(username, userDetails.getPassword(), userDetails.getAuthorities());
    }

}
