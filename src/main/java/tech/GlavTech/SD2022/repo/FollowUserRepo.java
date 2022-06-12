package tech.GlavTech.SD2022.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.GlavTech.SD2022.model.Follower;

import java.util.List;

@Repository
public interface FollowUserRepo extends JpaRepository<Follower, Integer> {
    //Returns list of users the current user is following
    List<Follower> findUserByAdmiredID(Integer admiredID);

    //Returns list of users that are following current user
    List<Follower> findUserByWorshiperID(Integer worshiperID);
}
