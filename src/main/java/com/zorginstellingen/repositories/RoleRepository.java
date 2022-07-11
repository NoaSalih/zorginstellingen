package com.zorginstellingen.repositories;

import com.zorginstellingen.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository  extends JpaRepository<Role, Long> {
   @Query("select role from Role role where role.roleName=?1")
   Role findByRoleName(String roleName);

}
