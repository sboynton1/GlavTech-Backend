package tech.GlavTech.SD2022.Pods.login;

import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.AllArgsConstructor;
import org.json.*;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tech.GlavTech.SD2022.Pods.register.EmailTester;
import tech.GlavTech.SD2022.Pods.register.PasswordHandler;
import tech.GlavTech.SD2022.Pods.register.RegistrationRequest;
import tech.GlavTech.SD2022.exception.UserNotFoundException;
import tech.GlavTech.SD2022.model.User;
import tech.GlavTech.SD2022.repo.UserRepo;
import tech.GlavTech.SD2022.service.UserService;

import java.util.Optional;

@Service
@AllArgsConstructor
public class LoginService {

    @Autowired
    private final PasswordHandler passwordHandler;
    private final UserService userService;

    public ResponseEntity<Object> login(LoginRequest userLogin) {
        try {
            User possibleUser = userService.findUserByUsername(userLogin.getUsername());
            String gotPass = userLogin.getPassword();
            if (!gotPass.equals(possibleUser.getPassword())) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Incorrect Password");
            }
            return ResponseEntity.status(HttpStatus.OK).body((new JSONObject().put("accessToken", "1").toString()));
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username not found");
        }


    }

    }
