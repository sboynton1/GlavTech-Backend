package tech.GlavTech.SD2022.register;

import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class EmailTester {
    public Boolean validateEmail(String email){
        //Using the OWASP email validation regex
        return Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$")
                .matcher(email).matches();
    }

}
