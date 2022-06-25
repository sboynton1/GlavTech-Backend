package tech.GlavTech.SD2022.pods.following;


import lombok.AllArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.GlavTech.SD2022.exception.UserNotFoundException;
import tech.GlavTech.SD2022.model.User;

import javax.persistence.CascadeType;
import javax.persistence.OneToOne;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/followhandler")
public class FollowController {


    private FollowService followService;




    @PostMapping(path = "/followUser")
    @OneToOne(cascade = {CascadeType.ALL})
    public ResponseEntity<Object> followUser(@RequestBody FollowRequest fr){
        return followService.followUser(fr);
    }




    @PostMapping(path = "/unfollowUser")
    @OneToOne(cascade = {CascadeType.ALL})
    public ResponseEntity<Object> unfollowUser (@RequestBody FollowRequest fr) {
        return followService.unfollowUser(fr);

    }
    @PostMapping(path="/isFollowing")
    public  ResponseEntity<Boolean> isFollowing(@RequestBody FollowRequest fr) {
        return followService.isFollowing(fr);
    }


    @GetMapping(path = "/followers/{username}")
    public ResponseEntity<Object> getFollowers(@PathVariable("username") String username) {
        List<User> followerList;
        try {
           followerList = followService.getFollowers(username);
            System.out.println(username + " followers are: " + followerList);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(followerList, HttpStatus.OK);

    }

    @GetMapping(path = "/following/{username}")
    public ResponseEntity<Object> getFollowing(@PathVariable("username") String username) {
        List<User> followingList;
        try {
            followingList = followService.getFollowing(username);
            System.out.println("here");
            System.out.println(username + "follows these users: " + followingList);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(followingList, HttpStatus.OK);

    }
//    @PostMapping(path = "/isFollowed")
//    public ResponseEntity<Boolean> isFollowed() {
//        try {
//
//        }
//    }

}



