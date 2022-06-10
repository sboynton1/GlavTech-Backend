package tech.GlavTech.SD2022.register;

import org.springframework.stereotype.Service;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class PasswordHandler {
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

    public String getSHA(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        // digest() method called
        // to calculate message digest of an input
        // and return array of byte
        return toHexString(input.getBytes(StandardCharsets.UTF_8));
    }

    private static String toHexString(byte[] hash)
    {
        // Convert byte array into signum representation
        BigInteger number = new BigInteger(1, hash);

        // Convert message digest into hex value
        StringBuilder hexString = new StringBuilder(number.toString(16));

        // Pad with leading zeros
        while (hexString.length() < 64)
        {
            hexString.insert(0, '0');
        }

        return hexString.toString();
    }
}
