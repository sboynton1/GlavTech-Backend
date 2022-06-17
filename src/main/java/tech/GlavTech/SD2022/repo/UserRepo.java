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

    /**
     * Matches users that are [@param] are following
     * @param username username to match in query
     * @return List of Users that match
     */
    List<User> findUsersByFollowers(String username);



    /**
     * Finds users who follow [@param]
     * @param username username to match in query
     * @return List of Users that match
     */
    List<User> findUsersByFollowing(String username);

//    @Query(
//            value = "SELECT admired_users FROM databasemanager.user_admired_users a WHERE a.user_id = ?1",
//            nativeQuery = true
//    )
//    List<String> get

}
