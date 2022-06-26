package tech.GlavTech.SD2022.pods.post;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.GlavTech.SD2022.exception.UserNotFoundException;
import tech.GlavTech.SD2022.model.User;
import tech.GlavTech.SD2022.model.post.Post;
import tech.GlavTech.SD2022.repo.PostRepo;
import tech.GlavTech.SD2022.repo.UserRepo;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path="/api/post")
public class PostController {

    private PostService postService;
    private UserRepo userRepo;
    private PostRepo postRepo;

    @PostMapping(path = "/thread")
    public ResponseEntity<Object> postThread(@RequestBody ThreadRequest tr) {
        return postService.postThread(tr);
    }

    @GetMapping(path = "/user/{username}")
    public List<Post> getPostsFromUser(@PathVariable String username) {
        User sender;

        try {
            sender = userRepo.findUserByUsername(username).orElseThrow(Exception::new);
            List<Post> posts = postRepo.findPostsByUserID(sender.getId());
            return posts;
        } catch (Exception e) {
            return new ArrayList<Post>();
        }
    }

    @PostMapping(path = "/recipe")
    public ResponseEntity<Object> postRecipe(@RequestBody RecipeRequest rr) {return postService.postRecipe(rr);}

    @GetMapping(path = "/feed/{username}")
    public ResponseEntity<Object> getFeed(@PathVariable String username) {
        List<Post> feed;
        try {
            feed = postService.getFeed(username);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(feed, HttpStatus.OK);
    }

}
