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

    private UserRepo userRepo;
    private UserService userService;




    @PostMapping(path = "/followUser")
    @OneToOne(cascade = {CascadeType.ALL})
    public ResponseEntity<String> admireUser(@RequestBody FollowRequest fr){
        User current;
        System.out.println("current user is : " + fr.getCurrentUsername());
        System.out.println("User trying to follow is : " + fr.getAdmiredUsername());
        try {
            current = userRepo.findUserByUsername(fr.getCurrentUsername()).orElseThrow(Exception::new);
            System.out.println("Current user's ID: " + current.getId());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Something went wrong!");
        }

        User admiredUser;

        try {
            admiredUser = userRepo.findUserByUsername(fr.getAdmiredUsername()).orElseThrow(Exception::new);
            System.out.println("Admired user's id is : " + admiredUser.getId());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User not found");
        }

        List<String> followedList = current.getAdmiredUsers();
        followedList.add(admiredUser.getUsername());
        current.setAdmiredUsers(followedList);
        userService.updateUser(current);

        for(int i  = 0; i < followedList.size(); i++) {
            System.out.println(followedList.get(i));
        }

        System.out.println("got here");

        return new ResponseEntity<>(HttpStatus.OK);

    }

}
