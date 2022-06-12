package tech.GlavTech.SD2022.Pods.login;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tech.GlavTech.SD2022.Pods.register.PasswordHandler;
import tech.GlavTech.SD2022.service.RequestOperations;
import tech.GlavTech.SD2022.exception.UserNotFoundException;
import tech.GlavTech.SD2022.model.User;
import tech.GlavTech.SD2022.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

@Service
@AllArgsConstructor
public class LoginService {

    @Autowired
    private final PasswordHandler passwordHandler;
    private final UserService userService;

    private final RequestOperations requestOperations;

    private final JSONObject jsonObject = new JSONObject();
    public ResponseEntity<Object> login(LoginRequest userLogin, HttpServletRequest request) {
        try {
            User possibleUser = userService.findUserByUsername(userLogin.getUsername());
            String gotPass = passwordHandler.getSHA(userLogin.getPassword());
            if (!gotPass.equals(possibleUser.getPassword())) {
                System.out.println("Incorr Pass");
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Incorrect Password");
            }
            //save session if session is not present
//            request.getSession().setAttribute("userInSession", possibleUser);
//            System.out.println(request.getSession().getAttribute("userInSession"));
            //user in json
            jsonObject.put("user", requestOperations.ObjectToJSON(possibleUser));
            //access token in json
            jsonObject.put("accessToken", requestOperations.generateNewToken());
            System.out.println(jsonObject);
            return ResponseEntity.status(HttpStatus.OK).body(jsonObject.toString());
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username not found");
        } catch (NoSuchAlgorithmException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Problem during password encryption");
        } catch (JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Problem Parsing user object to json");
        }
    }

    }
