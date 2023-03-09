package com.example.chatAPI.Util;

import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class CommonUtils {
    public boolean isValidPassword(String password)
    {

        // Regex to check valid password.
        String regex = "^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[@#$%^&+=])"
                + "(?=\\S+$).{8,20}$";

        // Compile the ReGex
        Pattern p = Pattern.compile(regex);

        // If the password is empty
        // return false
        if (password == null) {
            return false;
        }

        // Pattern class contains matcher() method
        // to find matching between given password
        // and regular expression.
        Matcher m = p.matcher(password);

        // Return if the password
        // matched the ReGex
        return m.matches();
    }
    public boolean isValidEmail(String email){
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";
        Pattern p = Pattern.compile(regex);
        if(email==null)return false;
        Matcher m = p.matcher(email);
        return m.matches();
    }
    public boolean isValidPhoneNumber(String phoneNumber){
        String regex = "(0|91)?[6-9][0-9]{9}";
        Pattern p = Pattern.compile(regex);
        if(phoneNumber==null)return false;
        Matcher m = p.matcher(phoneNumber);
        return m.matches();
    }
}
