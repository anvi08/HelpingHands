/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package profile.donor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.causes.Cause;
import utilities.DbConnection;

/**
 *
 * @author abhis
 */
public class DonorDirectory {
    private Donor donor;
    
    public DonorDirectory(Donor donor){
        this.donor = donor;
    }
    
    public void addDonors(){
        
        String firstName = donor.getFirstName();
        String lastName = donor.getLastName();
        String pass = donor.getPassword();
        String type = donor.getType();

        String country = donor.getCountry();
        String email = donor.getEmail();    
        String sql = "INSERT INTO `donortable`(`First_Name`,`Last_Name`, `Email`,`Password`,`Type`,`Country`) "
        + "VALUES ('" + firstName + "','" + lastName + "','" + email + "','" + pass + "','" + type + "','" + country + "')";//        fetch();
        System.out.print(sql);
        DbConnection.query(sql);    
    }
    
    public boolean validateDonor(String inputEmail,String inputPassword) throws SQLException{
        ArrayList<String> credentials = new ArrayList();
        String query = "Select Email,Password from donortable";
        ResultSet resultSet = DbConnection.selectQuery(query);    
        while(resultSet.next()){

            String email = resultSet.getString("Email");
            String pass = resultSet.getString("Password");
            if(inputEmail.trim().equals(email) && inputPassword.trim().equals(pass)){
                credentials.add(email);                   
                credentials.add(pass);
            }
 
        }
        if(credentials.isEmpty()){
            return false;            
        }else{
            return true;
        }

    }

    public  ArrayList<Cause> popDonorTable(String cause1) throws SQLException{
        ArrayList<Cause> allDonors = new ArrayList();

        String tableQuery = "Select * from cause where NGO_Org = '"+ cause1 + "' and R_Id is not null;";
        System.out.println(tableQuery); 
        ResultSet resultSet = DbConnection.selectQuery(tableQuery); 
//        ResultSet resultSet = DbConnection.selectQuery(query1);    
        while(resultSet.next()){

            String organisation = resultSet.getString("NGO_Org");
            String country1 = resultSet.getString("Country");
            String name = resultSet.getString("Cause_Name");
            String description = resultSet.getString("Cause_Desc");
            String category = resultSet.getString("R_Category");
            // String status = resultSet.getString("Status");
            String causeId = resultSet.getString("Cause_Id");
            //System.out.println(causeId);
            boolean status = Integer.parseInt(resultSet.getString("Status")) == 0 ? false : true;
            
            Cause cause = new Cause(name,description,organisation,category,country1,true);
            cause.setCauseId(Integer.valueOf(causeId));
            cause.setRecId(Integer.valueOf(resultSet.getString("R_Id")));
            allDonors.add(cause);
        }
        return allDonors;   

    }

    public  ArrayList<Cause> popDonorTrackingTable(int donorId) throws SQLException{
        ArrayList<Cause> allDonors = new ArrayList();

        String tableQuery = "select * from financialaiddb.cause where Cause_Id in  (Select Cause_Id from financialaiddb.causeticket where Donor_Id = "+donorId+");";
        System.out.println(tableQuery); 
        ResultSet resultSet = DbConnection.selectQuery(tableQuery); 
//        ResultSet resultSet = DbConnection.selectQuery(query1);    
        while(resultSet.next()){

            String organisation = resultSet.getString("NGO_Org");
            String country1 = resultSet.getString("Country");
            String name = resultSet.getString("Cause_Name");
            String description = resultSet.getString("Cause_Desc");
            String category = resultSet.getString("R_Category");
            // String status = resultSet.getString("Status");
            String causeId = resultSet.getString("Cause_Id");
            //System.out.println(causeId);
            boolean status = Integer.parseInt(resultSet.getString("Status")) == 0 ? false : true;
            
            Cause cause = new Cause(name,description,organisation,category,country1,true);
            cause.setCauseId(Integer.valueOf(causeId));
            cause.setRecId(Integer.valueOf(resultSet.getString("R_Id")));
            allDonors.add(cause);
        }
        return allDonors;   

    }
    
    public ResultSet getdonor(String user){
        String query = "Select * from donortable where Email = '"+ user.split("-")[0].trim() + "';";
        ResultSet resultSet = DbConnection.selectQuery(query); 
        return resultSet;
    }

    public void add2FA(String tempPass,String email,String user){
        

//        String pass = donor.getTempPass();
//        String email = donor.getEmail();   
//        String user = "Donor";
        String sql = "INSERT INTO `users`(`Email`,`Temp_Password`, `User_Type`) "
        + "VALUES ('" + email + "','" + tempPass + "','" + email + "')";//        fetch();
        System.out.print(sql);
        DbConnection.query(sql);    
    }

    public boolean validateDonor2FA(String inputPassword) throws SQLException{
        ArrayList<String> credentials = new ArrayList();
        String query = "Select * from users";
        ResultSet resultSet = DbConnection.selectQuery(query);    
        while(resultSet.next()){

            String email = resultSet.getString("Email");
            String pass = resultSet.getString("Temp_Password");
            if(inputPassword.trim().equals(pass)){
                credentials.add(email);                   
                credentials.add(pass);
            }
 
        }
        if(credentials.isEmpty()){
            return false;            
        }else{
            return true;
        }

    }  
    
    public Donor fetchDonorById(int donorId) throws  SQLException{
        Donor donor = null;
        String query = "select * from donortable where ID = " + donorId + ";";
        ResultSet resultSet = DbConnection.selectQuery(query);
        while (resultSet.next()) {
            String firstName = resultSet.getString("First_Name");
            String lastName = resultSet.getString("Last_Name");
            String emailId = resultSet.getString("Email");
            String password = resultSet.getString("Password");
            String type = resultSet.getString("Type");
            String country = resultSet.getString("Country");
            
            donor = new Donor(firstName, lastName, emailId, password, null, country, type);
        }
        return donor;
    }

}
