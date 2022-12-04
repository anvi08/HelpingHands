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
        if (confirmPassword == null || confirmPassword.trim().equals("")) {
            msg = "Confirm Password cannot be empty.";
        } else if (!password.trim().equals(confirmPassword.trim())) {
            msg = "Confirm Password and Password doesnot match";
        }
        return msg;
    }
    
    /*
        Stop user from adding numbers in input fields
    */
    public String validateName(String name) {
        String msg = null;
        final Pattern pattern = Pattern.compile("^[A-Za-z-]++$");
        if (name == null || name.trim().equals("")) {
            msg = "Name field cannot be empty";
        } else if (!pattern.matcher(name).matches()) {
            msg = "Name field can contain only letters";
        }
        return msg;
    }
    
//    public String validateEmail(String email) {
//        String msg = null;
//        final 
//    }
}
