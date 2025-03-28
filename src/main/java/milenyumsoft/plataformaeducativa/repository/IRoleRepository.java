package milenyumsoft.plataformaeducativa.repository;

import milenyumsoft.plataformaeducativa.modelo.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Long> {

    @Modifying
    @Query(value = "DELETE FROM user_roles WHERE role_id = :roleId", nativeQuery = true)
    public void deleteUserRoles(@Param("roleId")Long roleId);

    @Modifying
    @Query( value="DELETE FROM roles_permissions WHERE role_id = :roleId", nativeQuery = true)
    public void deleteRolePermissions(@Param("roleId") Long roleId);
}
