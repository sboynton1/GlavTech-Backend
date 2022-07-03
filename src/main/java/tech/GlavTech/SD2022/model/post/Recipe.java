package tech.GlavTech.SD2022.model.post;

import tech.GlavTech.SD2022.model.User;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Recipe extends Post{

    private String imageUrl;

    @ElementCollection
    private List<String> instructions;

    public Recipe () {}

    public Recipe(long postID, long userId, String username, String postText, String postTitle, LocalDateTime sentAtTime, PostType postType, String imageUrl, List<String> instructions) {
        super(postID, userId, username, postText, postTitle, sentAtTime, postType);
        this.imageUrl = imageUrl;
        this.instructions = instructions;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<String> getInstructions() {
        return instructions;
    }

    public void setInstructions(List<String> instructions) {
        this.instructions = instructions;
    }
}
