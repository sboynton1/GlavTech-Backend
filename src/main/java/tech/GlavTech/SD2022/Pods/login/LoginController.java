package tech.GlavTech.SD2022.Pods.login;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.GlavTech.SD2022.model.User;
import tech.GlavTech.SD2022.repo.UserRepo;
import tech.GlavTech.SD2022.service.UserService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(path = "auth/login")
@AllArgsConstructor
public class LoginController {

    private LoginService loginService;

    @PostMapping
    public ResponseEntity<Object> login(@RequestBody LoginRequest LR, HttpServletRequest request){
        System.out.println("Received Login Request");
        return loginService.login(LR, request);
    }
}