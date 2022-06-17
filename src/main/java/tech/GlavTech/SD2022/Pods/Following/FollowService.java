package tech.GlavTech.SD2022.Pods.Following;

import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tech.GlavTech.SD2022.exception.UserNotFoundException;
import tech.GlavTech.SD2022.model.User;
import tech.GlavTech.SD2022.repo.UserRepo;
import tech.GlavTech.SD2022.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FollowService {

    @Autowired
    private final UserRepo userRepo;
    private final UserService userService;

    public List<User> getUsersYouAdmire(String username){
        //List<Follower> admiredUsers;
        List<User> admiredUsers = new ArrayList<>();
        User current;

        List<User> certifiedAdmiredUsers = new ArrayList<>();

        try {
            admiredUsers = userRepo.findUsersByFollowers(username);
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
            worshippingUsers = userRepo.findUsersByFollowing(username);
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

    public ResponseEntity<Object> followUser(FollowRequest fr) {
        User sourceUser;
        try {
            sourceUser = userService.findUserByUsername(fr.getCurrentUsername());
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Something went wrong!");
        }
        User targetUser;
        try {
            targetUser = userService.findUserByUsername(fr.getTargetUsername());
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Target User not found");
        }

        //System.out.println(sourceUser.getUsername() + " Following " + targetUser.getUsername());

        //update source following ensuring no duplicates
        List<String> followingList = sourceUser.getFollowing();
        if (!followingList.contains(targetUser.getUsername())) {
            followingList.add(targetUser.getUsername());
            sourceUser.setFollowing(followingList);
        }

        //update target follower list ensuring no duplicates
        List<String> followersList = targetUser.getFollowers();
        if (!followersList.contains(sourceUser.getUsername())) {
            followersList.add(sourceUser.getUsername());
            targetUser.setFollowers(followersList);
        }

        //save changes in db
        userService.updateUser(sourceUser);
        userService.updateUser(targetUser);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<Object> unfollowUser(FollowRequest fr) {
        User sourceUser;
        try {
            sourceUser = userService.findUserByUsername(fr.getCurrentUsername());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Something went wrong!");
        }
        User targetUser;
        try {
            targetUser = userService.findUserByUsername(fr.getTargetUsername());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User not found");
        }

        System.out.println(sourceUser.getUsername() + " unfollowing " + targetUser.getUsername());
        //update source following list
        sourceUser.getFollowing().remove(targetUser.getUsername());
        //update target follower list
        targetUser.getFollowers().remove(sourceUser.getUsername());

        //save changes
        userService.updateUser(sourceUser);
        userService.updateUser(targetUser);

        return new ResponseEntity<>(HttpStatus.OK);
    }




}
