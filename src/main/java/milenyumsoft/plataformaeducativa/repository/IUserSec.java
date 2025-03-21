package milenyumsoft.plataformaeducativa.repository;

import jdk.jfr.Registered;
import milenyumsoft.plataformaeducativa.modelo.UserSec;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserSec extends JpaRepository<UserSec, Long> {
}
