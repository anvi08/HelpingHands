/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilities;
import java.sql.ResultSet;
import java.sql.SQLException;
import profile.bank.BankPerson;
import utilities.DbConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import profile.justiceDepartment.JusticeDepartmentEmployee;
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
    public BankPerson bankEmployeeLogin() throws SQLException{
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
        
        BankPerson bp = null;
        String query = "Select * from bankemployee where Email_Id = '"+email+"' ";
        ResultSet resultSet = DbConnection.selectQuery(query);
        while(resultSet.next()){
            int bank_id =resultSet.getInt("Bank_id");
            String firstName=resultSet.getString("First_Name");
            String lastName = resultSet.getString("Last_Name");
            String email = resultSet.getString("Email_id");
            String password = resultSet.getString("Password");
            String type = resultSet.getString("Type");
            String bank_name = resultSet.getString("Bank_Name");
            int status = resultSet.getInt("Status");
            boolean empStatus;
            if(status==0){
                empStatus=false;
            }
            else{
                empStatus=true;
            }
            //var empStatus = status.equals("0") ? false : true;
            String country = resultSet.getString("Country");
            
            bp = new BankPerson(firstName, lastName, email, password, type, bank_name, empStatus, country);
            bp.setStatus(empStatus);
            bp.setEmpId(bank_id);
        }
        return bp;
    }   
    
    public JusticeDepartmentEmployee validateJusticeLogin() throws SQLException {
        JusticeDepartmentEmployee justiceDepartmentEmployee = null;
        String query = "select * from justicedepartmentemployee where Email_id ='" + email + "'";
        ResultSet resultSet = DbConnection.selectQuery(query);
        while(resultSet.next()){
            int id = Integer.parseInt(resultSet.getString("j_emp_id"));
            String firstName = resultSet.getString("First_Name");
            String lastName = resultSet.getString("Last_Name");
            String emailId = resultSet.getString("Email_id");
            String empType = resultSet.getString("Type");
            String password = resultSet.getString("Password");
            String dbCountry = resultSet.getString("Country");
            String dbStatus = resultSet.getString("Status");
            boolean empStatus = dbStatus.equals("0") ? false : true;
            justiceDepartmentEmployee = new JusticeDepartmentEmployee(firstName, lastName, emailId, password, empType, dbCountry);
            justiceDepartmentEmployee.setStatus(empStatus);
            justiceDepartmentEmployee.setId(id);
        }
        return justiceDepartmentEmployee;
    }
}
