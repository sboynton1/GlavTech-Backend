package tech.GlavTech.SD2022.register;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class RegistrationRequest {
    //This request serves as the body of what needs to be sent over for when the user registers.
    // TODO add the parameters we want a user object to start with when they register
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private String address;
    public  String phone;
    private String city;
    private String state;
    private String zip;
}
