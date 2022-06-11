package tech.GlavTech.SD2022.Pods.login;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tech.GlavTech.SD2022.Pods.register.PasswordHandler;
import tech.GlavTech.SD2022.service.JsonOperations;
import tech.GlavTech.SD2022.exception.UserNotFoundException;
import tech.GlavTech.SD2022.model.User;
import tech.GlavTech.SD2022.service.UserService;

import java.security.NoSuchAlgorithmException;

@Service
@AllArgsConstructor
public class LoginService {

    @Autowired
    private final PasswordHandler passwordHandler;
    private final UserService userService;

    private final JsonOperations jsonOperations;

    public ResponseEntity<Object> login(LoginRequest userLogin) {
        try {
            User possibleUser = userService.findUserByUsername(userLogin.getUsername());
            String gotPass = passwordHandler.getSHA(userLogin.getPassword());
            if (!gotPass.equals(possibleUser.getPassword())) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Incorrect Password");
            }
            return ResponseEntity.status(HttpStatus.OK).body(jsonOperations.toJSONString("accessToken", "hardcodedvalue"));
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username not found");
        } catch (NoSuchAlgorithmException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Problem during password encryption");
        }


    }

    }
