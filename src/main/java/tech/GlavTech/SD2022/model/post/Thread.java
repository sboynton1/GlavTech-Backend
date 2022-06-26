package tech.GlavTech.SD2022.model.post;

import tech.GlavTech.SD2022.model.User;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
public class Thread extends Post{
    private String imageUrl;

    public Thread() {}

    public Thread(long postID, long userId, String username, String postText, String postTitle, LocalDate sentAtTime, PostType postType, String imageUrl) {
        super(postID, userId, username, postText, postTitle, sentAtTime, postType);
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


}
