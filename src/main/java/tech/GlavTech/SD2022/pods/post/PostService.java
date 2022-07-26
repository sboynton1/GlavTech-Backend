package tech.GlavTech.SD2022.pods.post;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tech.GlavTech.SD2022.exception.UserNotFoundException;
import tech.GlavTech.SD2022.model.post.Post;
import tech.GlavTech.SD2022.model.post.Recipe;
import tech.GlavTech.SD2022.model.post.Thread;
import tech.GlavTech.SD2022.model.User;
import tech.GlavTech.SD2022.pods.following.FollowService;
import tech.GlavTech.SD2022.repo.PostRepo;
import tech.GlavTech.SD2022.repo.UserRepo;
import tech.GlavTech.SD2022.service.UserService;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

    @Autowired
    private final UserService userService;

    @Autowired
    private final FollowService followService;

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
        newPost.setUsername(sender.getUsername());
        newPost.setPostText(tr.getPostText());
        newPost.setPostTitle(tr.getPostTitle());
        newPost.setSentAtTime(LocalDateTime.now());
        newPost.setImageUrl(tr.getImageUrl());
        newPost.setPostType((Post.PostType.THREAD));
        postRepo.save(newPost);

        return new ResponseEntity<>(newPost, HttpStatus.OK);
    }

    public ResponseEntity<Object> postRecipe(RecipeRequest rr) {
        User sender;

        //Verifies Sender is a real user
        try {
            sender = userRepo.findUserByUsername(rr.getSenderUsername()).orElseThrow(Exception::new);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User Not Found!");
        }
        Recipe newPost = new Recipe();
        newPost.setUserID(sender.getId());
        newPost.setUsername(sender.getUsername());
        newPost.setPostText(rr.getPostText());
        newPost.setPostTitle(rr.getPostTitle());
        newPost.setSentAtTime(LocalDateTime.now());
        newPost.setImageUrl(rr.getImageUrl());
        newPost.setInstructions(rr.getInstructions());
        newPost.setPostType((Post.PostType.RECIPE));
        postRepo.save(newPost);

        return new ResponseEntity<>(newPost, HttpStatus.OK);
    }

    /**
     * Gets feed for user specified
     *
     * @param username
     * @return lists of posts to display on feed
     */
    public List<Post> getFeed(String username) throws Exception {
        User sourceUser;
        List<Post> postList;
        try {
            sourceUser = userService.findUserByUsername(username);
            //add source user posts to post list
            postList = postRepo.findPostsByUserID(sourceUser.getId());
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException("Source user not found");
        } catch (Exception e) {
            throw new Exception("Could not collect users posts");
        }


        List<User> followingList;
        try {
            followingList = followService.getFollowing(sourceUser.getUsername());
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException(e.getMessage());
        }

        for (User followedUser : followingList) {
            List<Post> followedUserPosts;
            try {
                //add source user posts to post list
                followedUserPosts = postRepo.findPostsByUserID(followedUser.getId());
                postList.addAll(followedUserPosts);
            } catch (Exception e) {
                throw new Exception("Could not collect followed user [" + followedUser.getUsername() + "]'s posts");
            }
        }
        return postList;
    }

    @Transactional
    public void deletePost(long postID) throws Exception {
        try {
            postRepo.deletePostByPostID(postID);
        } catch (Exception e) {
            throw new Exception("Can't Delete Post");
        }
        System.out.println("Deleting Post.....");
    }
}
