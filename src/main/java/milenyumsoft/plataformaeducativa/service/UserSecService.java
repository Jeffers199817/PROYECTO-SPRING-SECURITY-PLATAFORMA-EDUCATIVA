package milenyumsoft.plataformaeducativa.service;

import milenyumsoft.plataformaeducativa.modelo.UserSec;
import milenyumsoft.plataformaeducativa.repository.IUserSecRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserSecService implements IUserSecService{


    @Autowired
    private IUserSecRepository userSecRepository;


    @Override
    public List<UserSec> findAll() {
        return userSecRepository.findAll();
    }

    @Override
    public Optional<UserSec> findById(Long id) {
        return userSecRepository.findById(id);
    }

    @Override
    public UserSec save(UserSec userSec) {
        System.out.println("llleg aqui" + userSec);
        return userSecRepository.save(userSec);
    }

    @Override
    @Transactional
    public String deleteById(Long id) {
       Optional<UserSec> userExistente=  this.findById(id);
        if(userExistente.isPresent()) {
            UserSec usuarioExistenteExtraido = userExistente.get();
            userSecRepository.deleteUserSecByIdRoles(usuarioExistenteExtraido.getId());
            userSecRepository.deleteById(usuarioExistenteExtraido.getId());

            return "Usuario eliminado " + usuarioExistenteExtraido.getUsername() + " correctamente.";
        }



        return "Usuario no existe, ingrese un usuario válido.";
    }

    @Override
    public UserSec update(UserSec userSec) {
        return  userSecRepository.save(userSec);
    }

    @Override
    public String encriptPassword(String password) {
        return  new BCryptPasswordEncoder().encode(password);
    }
}
