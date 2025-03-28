package milenyumsoft.plataformaeducativa.repository;

import milenyumsoft.plataformaeducativa.modelo.UserSec;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserSecRepository extends JpaRepository<UserSec, Long> {


    Optional<UserSec> findUserEntityByUsername(String name);

    @Modifying
    @Query(value = "DELETE FROM user_roles WHERE user_id = :user_id", nativeQuery=true)
    public void deleteUserSecByIdRoles(@Param("user_id") Long user_id);
}
