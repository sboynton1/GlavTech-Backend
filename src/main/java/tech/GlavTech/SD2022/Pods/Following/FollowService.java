package tech.GlavTech.SD2022.Pods.Following;

import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.GlavTech.SD2022.model.Follower;
import tech.GlavTech.SD2022.model.User;
import tech.GlavTech.SD2022.repo.FollowUserRepo;
import tech.GlavTech.SD2022.repo.UserRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FollowService {

    @Autowired
    private final FollowUserRepo followRepo;

    @Autowired
    private final UserRepo userRepo;

    public List<User> getAdmiredUsers(Integer userID){
        List<Follower> followingObject;
        List<User> users = new ArrayList<>();

        try {
            followingObject = followRepo.findUserByAdmiredID(userID);
        } catch (Exception e){
            followingObject = new ArrayList<>();
        }

        if(followingObject.size() > 0) {
            for(Follower admiredUser : followingObject) {
                try {
                    Optional<User> possibleUser = userRepo.findUserById((long)admiredUser.getWorshiperID());
                    if(possibleUser.isPresent()) {
                        users.add(possibleUser.get());
                    }
                } catch (Exception e) {
                    System.out.println("Caught exception 9876");
                }
            }
        }
        return users;
    }

    public List<User> getWorshippingUsers(Integer userID) {
        List<Follower>  followingObject;
        List<User> users = new ArrayList<>();

        try{
            followingObject = followRepo.findUserByWorshiperID(userID);
        } catch (Exception e) {
            followingObject = new ArrayList<>();
        }

        if(followingObject.size() > 0) {
            for(Follower worshippingUser : followingObject) {
                try {
                    Optional<User> possibleUser = userRepo.findUserById((long)worshippingUser.getAdmiredID());
                    if(possibleUser.isPresent()) {
                        users.add(possibleUser.get());
                    }
                } catch (Exception e) {
                    System.out.println("Caught exception 1234");
                }
            }
        }
        return users;
    }


}
