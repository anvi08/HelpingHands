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
    public ArrayList<BankPerson> allBankPersons;

    public BankPersonDirectory(BankPerson bankPerson) {
        this.bankPerson = bankPerson;
        this.allBankPersons = new ArrayList<BankPerson>();
    }
    
    
    public ArrayList<BankPerson> populateBankPerson()throws SQLException{
        
            ArrayList<BankPerson> allBankPersons = new ArrayList();
            
            String query = "Select * from bankemployee;";
            ResultSet resultset = DbConnection.selectQuery(query);
            System.out.println("resultSet " +resultset );
            //System.out.println("profile.bank.BankPersonDirectory.populateBankPerson()");
            while(resultset.next()){
                int bankPersonId = resultset.getInt("Bank_Id");
                System.out.println(bankPersonId);
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
                allBankPersons.add(newbankPerson);
            }
            
        return allBankPersons;
    }
    
    public ArrayList<BankPerson> populateActiveBp() throws SQLException{
        
        
        ArrayList<BankPerson> activeBankPersons = new ArrayList();
        String query = "Select * from bankemployee where Status = 1;";
        ResultSet resultset = DbConnection.selectQuery(query);
        while(resultset.next()){
                int bankPersonId = resultset.getInt("Bank_Id");
                System.out.println(bankPersonId);
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
                activeBankPersons.add(newbankPerson);
            }
        return activeBankPersons;
    }
    
        
    public ArrayList<BankPerson> populateActiveBp(String country) throws SQLException{
        
        
        ArrayList<BankPerson> activeBankPersons = new ArrayList();
        String query = "Select * from bankemployee where Status = 1 AND Country = '" +country+"';" ;
        ResultSet resultset = DbConnection.selectQuery(query);
        while(resultset.next()){
                int bankPersonId = resultset.getInt("Bank_Id");
                System.out.println(bankPersonId);
                String firstName =resultset.getString("First_Name");
                String lastName = resultset.getString("Last_Name");
                String email = resultset.getString("Email_Id");
                String password = resultset.getString("Password");
                String empType = resultset.getString("Type");
                String bankName = resultset.getString("Bank_Name");
                boolean status = resultset.getBoolean("Status");
                String selectedCountry = resultset.getString("Country");
                
                BankPerson newbankPerson = new BankPerson(firstName, lastName, email, password, empType, bankName, status, selectedCountry);
                newbankPerson.setBankPersonId(bankPersonId);
                activeBankPersons.add(newbankPerson);
            }
        return activeBankPersons;
    }
    
    public ArrayList<BankPerson> populateCountryActiveBp(String userCountry) throws SQLException{
        
        
        ArrayList<BankPerson> activeBankPersons = new ArrayList();
        String userType ="EMPLOYEE";
        String query = "Select * from bankemployee where Status = 1 and Country = '"+userCountry+"' and Type = '"+userType+"';";
        ResultSet resultset = DbConnection.selectQuery(query);
        while(resultset.next()){
                int bankPersonId = resultset.getInt("Bank_Id");
                System.out.println(bankPersonId);
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
                activeBankPersons.add(newbankPerson);
            }
        return activeBankPersons;
    }
    
    public ArrayList<BankPerson> populateCountryInactiveBp(String userCountry) throws SQLException{
        ArrayList<BankPerson> inactiveBankPersons = new ArrayList();
        String userType ="EMPLOYEE";
        String query = "Select * from bankemployee where Status = 0 and Country = '"+userCountry+"' and Type = '"+userType+"';";
        ResultSet resultset = DbConnection.selectQuery(query);
        while(resultset.next()){
                int bankPersonId = resultset.getInt("Bank_Id");
                System.out.println(bankPersonId);
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
                inactiveBankPersons.add(newbankPerson);
            }
        return inactiveBankPersons;
    }
    
    public ArrayList<BankPerson> populateInactiveBp() throws SQLException{
        ArrayList<BankPerson> inactiveBankPersons = new ArrayList();
        //String userType ="EMPLOYEE";
        String query = "Select * from bankemployee where Status = 0";
        ResultSet resultset = DbConnection.selectQuery(query);
        while(resultset.next()){
                int bankPersonId = resultset.getInt("Bank_Id");
                System.out.println(bankPersonId);
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
                inactiveBankPersons.add(newbankPerson);
            }
        return inactiveBankPersons;
    }
    
    public void addBankPerson(){
        int status = 1;
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
    
    /*
    public void deletCause(String causeName){
            String deleteQuery = "Delete from financialaiddb.cause where Cause_Name = '" + causeName + "';";        
            DbConnection.query(deleteQuery);        
    }
    */
    
    public void deleteBankPerson(String email){
       // String deleteQuery = "Delete from financialaiddb.bankemployee where Email_id = '" + email + "';";  
        String deleteQuery = "Update financialaiddb.bankemployee set Status = 0 where Email_id = '" + email + "';";
        DbConnection.query(deleteQuery);        
    }
    
    public void updateBankPerson(int bankPersonId, BankPerson bp){
        
        String updateQuery = "Update financialaiddb.bankemployee set First_Name ='"+ bp.getFirstName() +"' ,Last_Name ='"+ bp.getLastName()+"',Country = '"+bp.getCountry()+"', Type = '"+bp.getEmpType() +"' where Email_id = '"+bp.getEmail()+"'; ";
        DbConnection.query(updateQuery);
    }
    
    
    
}
