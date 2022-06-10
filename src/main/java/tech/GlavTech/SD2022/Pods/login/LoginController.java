package tech.GlavTech.SD2022.Pods.login;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "auth/login")
@AllArgsConstructor
public class LoginController {

    private LoginService loginService;

    @PostMapping
    public ResponseEntity<Object> login(@RequestBody LoginRequest LR){
        System.out.println("Received Login Request");
        return loginService.login(LR);
    }
}