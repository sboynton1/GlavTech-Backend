package tech.GlavTech.SD2022.model.post;

import tech.GlavTech.SD2022.model.User;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Recipe extends Post{

    private String imageUrl;

    @OneToMany(cascade = {CascadeType.ALL})
    private List<Instruction> instructions;

    public Recipe () {}

    public Recipe(long postID, long userId, String postText, String postTitle, LocalDate sentAtTime, PostType postType, String imageUrl, List<Instruction> instructions) {
        super(postID, userId, postText, postTitle, sentAtTime, postType);
        this.imageUrl = imageUrl;
        this.instructions = instructions;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<Instruction> getInstructions() {
        return instructions;
    }

    public void setInstructions(List<Instruction> instructions) {
        this.instructions = instructions;
    }
}
