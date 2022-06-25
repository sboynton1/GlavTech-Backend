package tech.GlavTech.SD2022.pods.post;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import tech.GlavTech.SD2022.model.post.Instruction;

import java.util.List;

@Getter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class RecipeRequest {
    private String senderUsername;
    private String postTitle;
    private String postText;
    private String imageUrl;
    private List<Instruction> instructions;

}
