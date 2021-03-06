package tech.GlavTech.SD2022.pods.register;

import tech.GlavTech.SD2022.model.User;
import tech.GlavTech.SD2022.repo.UserRepo;
import tech.GlavTech.SD2022.exception.UserNotFoundException;
import tech.GlavTech.SD2022.service.UserService;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RegistrationService {

    @Autowired
    private final UserRepo userRepository;
    private final PasswordHandler passwordHandler;
    private final EmailTester eTester;
    private final UserService userService;

    //The register method. This will place a new user in the database when called.
    public ResponseEntity<String> register(RegistrationRequest userReg) {
        try {
            Optional<User> possibleDupes;
            possibleDupes = userRepository.findUserByEmail(userReg.getEmail());
            if(possibleDupes.isPresent()) return ResponseEntity.status(HttpStatus.CONFLICT).body("Duplicate Email");
            String checker = passwordHandler.passwordStrengthTest(userReg.getPassword());
            if (!checker.equals("Passed")) return ResponseEntity.status(HttpStatus.CONFLICT).body(checker);
            System.out.println(userReg);
            if(!userRepository.findUserByUsername(userReg.getUsername()).isPresent()) {
                throw new UserNotFoundException(String.format(""));
            }
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Duplicate Username");
        } catch(UserNotFoundException e) {
            if (!eTester.validateEmail(userReg.getEmail())) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Email Not Valid");
            }
            User newUser = new User();
            newUser.setFirstName(userReg.getFirstName());
            newUser.setLastName(userReg.getLastName());
            newUser.setAddress(userReg.getAddress());
            newUser.setCity(userReg.getCity());
            newUser.setState(userReg.getState());
            newUser.setZip(userReg.getZip());
            newUser.setPhone(userReg.getPhone());
            newUser.setUsername(userReg.getUsername());
            newUser.setAdmin(false);

            //Password Encryption
            String pass = "";
            try {
                pass = passwordHandler.getSHA(userReg.getPassword());
            } catch (NoSuchAlgorithmException a) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Error During Encryption");
            }
            newUser.setPassword(pass);
            newUser.setEmail(userReg.getEmail());
            userService.addUser(newUser);
            return new ResponseEntity("User registered successfully", HttpStatus.OK);
        }

    }
}