package tech.GlavTech.SD2022.register;

import tech.GlavTech.SD2022.model.User;
import tech.GlavTech.SD2022.repo.UserRepo;
import tech.GlavTech.SD2022.register.PasswordTester;
import tech.GlavTech.SD2022.exception.UserNotFoundException;
import tech.GlavTech.SD2022.service.UserService;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class RegistrationService {

    @Autowired
    private final UserRepo userRepository;
    private final PasswordTester pTester;
    private final EmailTester eTester;
    private final UserService userService;


    //The register method. This will place a new user in the database when called.
    public ResponseEntity<String> register(RegistrationRequest userReg) {
        try {
            Optional<User> possibleDupes;
            possibleDupes = userRepository.findUserByEmail(userReg.getEmail());
            if(possibleDupes.isPresent()) return ResponseEntity.status(HttpStatus.CONFLICT).body("Duplicate Email");
            String checker = pTester.passwordStrengthTest(userReg.getPassword());
            if (!checker.equals("Passed")) return ResponseEntity.status(HttpStatus.CONFLICT).body(checker);
            userRepository.findUserByUsername(userReg.getUsername()).orElseThrow(() -> new UserNotFoundException(String.format("")));
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Duplicate Username");
        } catch(UserNotFoundException e) {
            if (eTester.validateEmail(userReg.getEmail()) == false) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Email Not Valid");
            }
            User newUser = new User();
            newUser.setName(userReg.getName());
            newUser.setUsername(userReg.getUsername());
            newUser.setPassword((userReg.getPassword()));
            newUser.setEmail(userReg.getEmail());
            userService.addUser(newUser);
            return new ResponseEntity("User registered successfully", HttpStatus.OK);
        }

    }
}
