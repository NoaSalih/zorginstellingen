package zorginstellingen.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import zorginstellingen.model.Role;

@Repository
public interface RoleRepository  extends JpaRepository<Role, Long> {
   @Query("select role from Role role where role.roleName=?1")
   Role findByRoleName(String roleName);

}
