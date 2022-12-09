/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilities;
import profile.bank.BankPerson;
/**
 *
 * @author Anvi Jain
 */
public class ValidateUserLogin {
    BankPerson bankPerson;
    String loggedInUser;
    String email;
    String password;

    public ValidateUserLogin(String email, String password) {
        this.email = email;
        this.password = password;
    }
    
    
    /*
    get logged in user from loginn, and use validate function
    */
    public BankPerson bankEmployeeLogin(){
        /*
        if "BANK" dropdown selected while login check:
        if admin or employee:
        if admin:
        check which country admin:
          based on this show the list of employees
        
        
        if employee:
        separate jpanel
        
        */
        //query1 = "select * from financialaiddb.cause where `Status` = 1 and `NGO_Org` = '"+ loggedInUser + "';";
        String query = "Select * from bankemployee where Email_Id = '"+loggedInUser+"' ";
        return bankPerson;
    }
}
