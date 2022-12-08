/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package profile.bank;

import java.util.ArrayList;
import utilities.DbConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Anvi Jain
 */
public class BankPersonDirectory {
    
    private BankPerson bankPerson;
    //public ArrayList<Cause> allCauses; 
    public ArrayList<BankPerson> allBankPersons;

    public BankPersonDirectory(BankPerson bankPerson) {
        this.bankPerson = bankPerson;
        this.allBankPersons = new ArrayList<BankPerson>();
    }
    
    
    public ArrayList<BankPerson> populateBankPerson(String query)throws SQLException{
        
            //ArrayList<BankPerson> allBankPersons = new ArrayList();
            ResultSet resultset = DbConnection.selectQuery(query);
            System.out.println("profile.bank.BankPersonDirectory.populateBankPerson()");
            while(resultset.next()){
                int bankPersonId = resultset.getInt("Bank_Id");
                String firstName =resultset.getString("First_Name");
                String lastName = resultset.getString("Last_Name");
                String email = resultset.getString("Email_Id");
                String password = resultset.getString("Password");
                String empType = resultset.getString("Type");
                String bankName = resultset.getString("Bank_Name");
                boolean status = resultset.getBoolean("Status");
                String country = resultset.getString("Country");
                
                BankPerson newbankPerson = new BankPerson(firstName, lastName, email, password, empType, bankName, status, country);
                newbankPerson.setBankPersonId(bankPersonId);
                this.allBankPersons.add(newbankPerson);
            }
            System.out.println(this.allBankPersons.isEmpty());
            
            
        
        return this.allBankPersons;
    }
    
    public void addBankPerson(){
        int status = bankPerson.isStatus() == false ? 0 : 1;
        //boolean status = false;
        System.out.println("Status "+bankPerson.isStatus());
        //System.out.println(status);
        //System.out.print("Emp type "+bankPerson.getEmpType());
        String sql;
        sql = "INSERT INTO `bankemployee` (`First_Name`, `Last_Name`, `Email_Id`, `Password`, `Type`, `Bank_Name`, `Status`, `Country`)"
                + "VALUES ('" + bankPerson.getFirstName() + "','" + bankPerson.getLastName() + "','" + bankPerson.getEmail() + "','" + bankPerson.getPassword() + "','" + bankPerson.getEmpType()+ "','" + bankPerson.getBankName() + "','" + status + "','" + bankPerson.getCountry()+"')";
        System.out.print(sql);
        DbConnection.query(sql);
    }
    
}
