package tech.GlavTech.SD2022.Pods.login;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class LoginRequest {
    //This request serves as the body of what needs to be sent over for when the user registers.
    // TODO add the parameters we want a user object to start with when they register
    private String username;
    private String password;
}
