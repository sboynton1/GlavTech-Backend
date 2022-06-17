package tech.GlavTech.SD2022.repo;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tech.GlavTech.SD2022.exception.UserNotFoundException;
import tech.GlavTech.SD2022.model.User;

import java.util.List;

@SpringBootTest
public class UserRepoTest {

    @Autowired
    private UserRepo userRepo;

    @Test
    public void findUsersByFollowersTest() {
        List<User> results = userRepo.findUsersByFollowers("miles2");
        System.out.println("results = " + results);
        
    }

    @Test
    public void findUsersByFollowingTest() {
        List<User> results = userRepo.findUsersByFollowing("miles2");
        System.out.println("results = " + results);
    }
}
