package tech.GlavTech.SD2022.model.post;

import tech.GlavTech.SD2022.model.User;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // IDENTITY VS AUTO
    @Column(nullable = false, updatable = false)
    private long postID;

    private long userID;

    private String postText;
    private String postTitle;

    private LocalDate sentAtTime;

    public enum PostType {
        THREAD(0), RECIPE(1), RESTAURANT_REVIEW(2);
        private int value;
        private PostType(int value) {
            this.value = value;
        }
    }

    private PostType postType;


    public Post() {}

    public Post(long postID, long userId, String postText, String postTitle, LocalDate sentAtTime, PostType postType) {
        this.postID = postID;
        this.userID = userID;
        this.postText = postText;
        this.postTitle = postTitle;
        this.sentAtTime = sentAtTime;
        this.postType = postType;

    }

    public long getPostID() {
        return postID;
    }

    public void setPostID(long postID) {
        this.postID = postID;
    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public String getPostText() {
        return postText;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public LocalDate getSentAtTime() {
        return sentAtTime;
    }

    public void setSentAtTime(LocalDate sentAtTime) {
        this.sentAtTime = sentAtTime;
    }

    public PostType getPostType() {
        return postType;
    }

    public void setPostType(PostType postType) {
        this.postType = postType;
    }
}
