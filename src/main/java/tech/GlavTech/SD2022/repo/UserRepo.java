package tech.GlavTech.SD2022.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.GlavTech.SD2022.model.User;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
    void deleteUserById(Long id);

    Optional<User> findUserById(Long id);
}
