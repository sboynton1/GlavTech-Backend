package tech.GlavTech.SD2022.pods.post;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import tech.GlavTech.SD2022.model.User;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class ThreadRequest {
    private String senderUsername;
    private String postTitle;
    private String postText;
    private String imageUrl;
}
