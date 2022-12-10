/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilities;

import java.util.regex.Pattern;

/**
 *
 * @author Shreya Sharma
 */
public class Validators {
    
    public String validateConfirmPassword(String password, String confirmPassword) {
        String msg = null;
        if (isEmpty(confirmPassword)) {
            msg = "Confirm Password cannot be empty.";
        } else if (!password.trim().equals(confirmPassword.trim())) {
            msg = "Confirm Password and Password doesnot match";
        }
        return msg;
    }
    
    /*
     *   Stop user from adding numbers in input fields
    */
    public String validateName(String name) {
        String msg = null;
        final Pattern pattern = Pattern.compile("^[A-Za-z-]++$");
        if (isEmpty(name)) {
            msg = "Name field cannot be empty";
        } else if (!pattern.matcher(name).matches()) {
            msg = "Name field can contain only letters";
        }
        return msg;
    }
    
    public String validateEmail(String email) {
        final Pattern pattern = Pattern.compile("^(.+)@(.+)$");
        String msg = null;
        if (isEmpty(email)) {
            msg = "Email cannot be empty";
        } else if( !pattern.matcher(email).matches()) {
            msg = "Invalid Email Id entered";
        }
        
        return msg;
    }
    
    public String validatePassword(String password) {
        String msg = null;
        if (isEmpty(password)) {
            msg = "Password cannot be empty";
        } else if (password.length() < 4 ) {
            msg = "password should have atleat 4 characters";
        }
        return msg;
    }
    
    /*
    * returns true if the parameter is empty
    */
    public boolean isEmpty(String inputField) {
        boolean isEmpty = false;
        
        if (inputField == null || inputField.trim().equals("")) {
            isEmpty = true;
        }
        return isEmpty;
    }
    
    
}
