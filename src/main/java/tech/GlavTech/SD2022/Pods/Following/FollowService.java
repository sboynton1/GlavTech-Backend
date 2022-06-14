package tech.GlavTech.SD2022.Pods.Following;

import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.GlavTech.SD2022.model.User;
import tech.GlavTech.SD2022.repo.UserRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FollowService {

    @Autowired
    private final UserRepo userRepo;

    public List<User> getUsersYouAdmire(String username){
        //List<Follower> admiredUsers;
        List<User> admiredUsers = new ArrayList<>();
        User current;

        List<User> certifiedAdmiredUsers = new ArrayList<>();

        try {
            admiredUsers = userRepo.findUsersByAdmiredUsers(username);
        } catch (Exception e){
            admiredUsers = new ArrayList<>();
        }

        if(admiredUsers.size() > 0) {
            for(User admiredUser: admiredUsers) {
                try {
                    Optional<User> possibleUser = userRepo.findUserById(admiredUser.getId());
                    if(possibleUser.isPresent()) {
                        certifiedAdmiredUsers.add(possibleUser.get());
                    }
                } catch (Exception e) {
                    System.out.println("Caught exception 9876");
                }
            }
        }
        return certifiedAdmiredUsers;
    }

    public List<User> getUsersThatFollowYou(String username) {
        List<User>  worshippingUsers = new ArrayList<>();
        User current;

        List<User> certifiedWorshippers = new ArrayList<>();

        try{
            worshippingUsers = userRepo.findUsersByWorshippingUsers(username);
        } catch (Exception e) {
            worshippingUsers = new ArrayList<>();
        }

        if(worshippingUsers.size() > 0) {
            for(User worshipper: worshippingUsers) {
                try {
                    Optional<User> possibleUser = userRepo.findUserById(worshipper.getId());
                    if(possibleUser.isPresent()) {
                        certifiedWorshippers.add(possibleUser.get());
                    }
                } catch (Exception e) {
                    System.out.println("Caught exception 1234");
                }
            }
        }
        return certifiedWorshippers;
    }


}
