package tech.GlavTech.SD2022.register;

import org.springframework.stereotype.Service;

@Service
public class PasswordTester {
    //Passwords must contain a special char, longer than 6 char, a digit char, and
    public String passwordStrengthTest (String password) {
        String strong = "Passed";

        if(password.length() < 6) {return "Password must be longer than six characters!";}

        boolean hasNoSpecialCharacters = true;
        boolean hasNoDigits = true;
        boolean allLowerCase = true;
        for(int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            if(!Character.isAlphabetic(c) && !Character.isDigit(c)) {hasNoSpecialCharacters = false;}
            if(Character.isDigit(c)) {hasNoDigits = false;}
            if(Character.isUpperCase(c)) {allLowerCase = false;}
        }
        if(hasNoSpecialCharacters) {return "Password must contain special characters!";}
        if(hasNoDigits) {return "Password must contain a digit character!";}
        if(allLowerCase) {return "Password must contain an uppercase letter!";}

        return strong;



    }
}
