package tech.GlavTech.SD2022.pods.post;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tech.GlavTech.SD2022.model.post.Post;
import tech.GlavTech.SD2022.model.post.Thread;
import tech.GlavTech.SD2022.model.User;
import tech.GlavTech.SD2022.repo.PostRepo;
import tech.GlavTech.SD2022.repo.UserRepo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PostService {

    @Autowired
    private final PostRepo postRepo;

    @Autowired
    private final UserRepo userRepo;

    public List<Post> postsFromUser(long userID) {
        List<Post> posts = new ArrayList<>();

        try {
            posts = postRepo.findPostsByUserID(userID);
        } catch (Exception e) {
            posts = new ArrayList<>();
        }
        return posts;
    }


    public ResponseEntity<Object> postThread(ThreadRequest tr) {
        User sender;

        //Verifies Sender is a real user
        try {
            sender = userRepo.findUserByUsername(tr.getSenderUsername()).orElseThrow(Exception::new);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User Not Found!");
        }

        Thread newPost = new Thread();
        newPost.setUserID(sender.getId());
        newPost.setPostText(tr.getPostText());
        newPost.setPostTitle(tr.getPostTitle());
        newPost.setSentAtTime(LocalDate.now());
        newPost.setImageUrl(tr.getImageUrl());
        newPost.setPostType((Post.PostType.THREAD));
        postRepo.save(newPost);

        return new ResponseEntity<>(newPost, HttpStatus.OK);
    }
}
