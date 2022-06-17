package tech.GlavTech.SD2022.Pods.Following;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class FollowRequest {
    private String currentUsername;
    private String targetUsername;
}