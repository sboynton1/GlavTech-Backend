package tech.GlavTech.SD2022.Pods.Following;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.GlavTech.SD2022.model.User;
import tech.GlavTech.SD2022.repo.UserRepo;
import tech.GlavTech.SD2022.service.UserService;

import javax.persistence.CascadeType;
import javax.persistence.OneToOne;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/followhandler", consumes = MediaType.APPLICATION_JSON_VALUE)
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

//    @PostMapping(path = "/isFollowed")
//    public ResponseEntity<Boolean> isFollowed() {
//        try {
//
//        }
//    }

}



