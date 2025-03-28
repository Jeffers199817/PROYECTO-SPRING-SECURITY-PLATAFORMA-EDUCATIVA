package milenyumsoft.plataformaeducativa.service;

import milenyumsoft.plataformaeducativa.modelo.UserSec;
import org.apache.catalina.User;

import java.util.List;
import java.util.Optional;

public interface IUserSecService {

    List<UserSec> findAll();
    Optional<UserSec> findById(Long id);
    UserSec save(UserSec userSec);
    String deleteById(Long id);
    UserSec update(UserSec userSec);
    String encriptPassword(String password);

}
