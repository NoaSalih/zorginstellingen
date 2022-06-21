package zorginstellingen.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import zorginstellingen.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select user from User user where user.email=?1 and user.active=true")
    User findByEmail(String email);

    @Query("select user from User user where user.username=?1 and user.active=true")
    User findByUsername(String email);

    @Query("select user from User user where user.username=?1 and user.active=true")
    User checkUsernameExists(String username);

}
