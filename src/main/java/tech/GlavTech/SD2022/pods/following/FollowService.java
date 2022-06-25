package tech.GlavTech.SD2022.pods.following;

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
public class FollowService {

    private final UserService userService;

    public FollowService(UserService userService) {
        this.userService = userService;
    }

    public List<User> getFollowers(String username) {
        //Throws UserNotFound Exception if fails
        User sourceUser = userService.findUserByUsername(username);
        List<User> followList = new ArrayList<>();

        //grab every user in follow list
        for (String followedName : sourceUser.getFollowers()) {
            User followedUser;
            try {
                //look up followed user and add to followList
                followedUser = userService.findUserByUsername(followedName);
                followList.add(followedUser);
            } catch (UserNotFoundException e) {
                //Changes message for exception
                throw new UserNotFoundException("Followed User with name [" + followedName + "] was not found");
            }
        }
        return followList;
    }

    public List<User> getFollowing(String username) {
        //Throws UserNotFound Exception if fails
        User sourceUser = userService.findUserByUsername(username);
        List<User> followList = new ArrayList<>();

        //grab every user in follow list
        for (String followingName : sourceUser.getFollowing()) {
            User followingUser;
            try {
                //look up followed user and add to followList
                followingUser = userService.findUserByUsername(followingName);
                followList.add(followingUser);
            } catch (UserNotFoundException e) {
                //Changes message for exception
                throw new UserNotFoundException("Followed User with name [" + followingName + "] was not found");
            }
        }
        return followList;
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

    public ResponseEntity<Boolean> isFollowing(FollowRequest fr) {
        User sourceUser;
        try {
            sourceUser = userService.findUserByUsername(fr.getCurrentUsername());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(false);
        }

        //update source following list
        List<String> followList = sourceUser.getFollowing();
        boolean result = followList.contains(fr.getTargetUsername());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
