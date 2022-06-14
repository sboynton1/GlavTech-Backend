package tech.GlavTech.SD2022.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.GlavTech.SD2022.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    void deleteUserById(Long id);
    Optional<User> findUserById(Long id);
    Optional<User> findUserByEmail(String email);
    Optional<User> findUserByUsername(String username);
    boolean existsByUsername(String username);

    List<User> findUsersByAdmiredUsers(String username);
    List<User> findUsersByWorshippingUsers(String username);

}
