package tech.GlavTech.SD2022.Pods.Following;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.GlavTech.SD2022.model.User;
import tech.GlavTech.SD2022.repo.FollowUserRepo;
import tech.GlavTech.SD2022.repo.UserRepo;
import tech.GlavTech.SD2022.model.Follower;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import javax.persistence.CascadeType;
import javax.persistence.OneToOne;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/followhandler")
public class FollowController {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private FollowUserRepo followUserRepo;

    @PostMapping(path = "/followUser")
    @OneToOne(cascade = {CascadeType.ALL})
    public ResponseEntity<String> admireUser(@AuthenticationPrincipal User current, @RequestBody String admiredName){



        long admireID = current.getId();
        User admiredUser;
        System.out.println("THIS IS WHO I WANT OT FOLLOW: " + admiredName);
        System.out.println("THIS IS WHO I WANT OT FOLLOW: " + admiredName);
        System.out.println("THIS IS WHO I WANT OT FOLLOW: " + admiredName);
        System.out.println("THIS IS WHO I WANT OT FOLLOW: " + admiredName);
        System.out.println("THIS IS WHO I WANT OT FOLLOW: " + admiredName);
        System.out.println("THIS IS WHO I WANT OT FOLLOW: " + admiredName);
        System.out.println("THIS IS WHO I WANT OT FOLLOW: " + admiredName);
        System.out.println("THIS IS WHO I WANT OT FOLLOW: " + admiredName);
        System.out.println("THIS IS WHO I WANT OT FOLLOW: " + admiredName);

        try {
            admiredUser = userRepo.findUserByUsername(admiredName).orElseThrow(Exception::new);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User not found");
        }

        //If the user is already followed
//        if(followUserRepo.existsByFollowIDs(current.getId(), admireID)) {
//            return ResponseEntity.status(HttpStatus.CONFLICT).body("User already followed!");
//        }

        long worshippedID = admiredUser.getId();

        //Saving Follower ~ Calls constructor in follower
        Follower newAdmirer = new Follower(current, admiredUser);
        followUserRepo.save(newAdmirer);
        System.out.println("Followed" + admiredName + " Successfully!");
        return new ResponseEntity<>(HttpStatus.OK);

    }


}
