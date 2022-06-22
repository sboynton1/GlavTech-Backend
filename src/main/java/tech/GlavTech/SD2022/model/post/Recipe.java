package tech.GlavTech.SD2022.model.post;

import tech.GlavTech.SD2022.model.User;

import javax.persistence.Entity;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Recipe extends Post{

    private String imageUrl;

    //Tentative
    //Sends instructions in triplets. 1st space = direction 2nd space = number  3rd space = hour/min/day
//    private List<String> instructions;

    public Recipe () {}

    public Recipe(long postID, long userId, String postText, String postTitle, LocalDate sentAtTime, PostType postType, String imageUrl) {
        super(postID, userId, postText, postTitle, sentAtTime, postType);
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

//    public List<String> getInstructions() {
//        return instructions;
//    }
//
//    public void setInstructions(List<String> instructions) {
//        this.instructions = instructions;
//    }
}
