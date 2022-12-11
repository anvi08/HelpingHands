/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package profile.Receiver;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.causes.Cause;
import profile.Receiver.Receiver;
import utilities.DbConnection;

/**
 *
 * @author abhis
 */
public class ReceiverDirectory {
    private Receiver receiver;
    public ArrayList<Receiver> allReceivers;    
    
    public ReceiverDirectory(Receiver receiver){
        this.receiver = receiver;
    }

    public  ArrayList<Cause> popReceiverTable(String country, String r_Categeory, String cause1) throws SQLException{
        ArrayList<Cause> allReceiver = new ArrayList();

        String tableQuery = "Select * from cause where country = '"+ country +"' and `R_Category` = '"+r_Categeory+
                    "' and NGO_Org = '"+ cause1 + "' and R_Id is null;";
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
            allReceiver.add(cause);
        }
        return allReceiver;   
//        while(resultSet.next()){
//
//            String fname = resultSet.getString("First_Name");
//            String country1 = resultSet.getString("Country");
//            String lname = resultSet.getString("Last_Name");
//            String email = resultSet.getString("E_Mail");
//            String pass = resultSet.getString("Password");
//            // String status = resultSet.getString("Status");
//            String user = resultSet.getString("User");
//            String type = resultSet.getString("Type");
//            //System.out.println(causeId);
//            Receiver receiver = new Receiver(fname,lname,email,pass,user,country,type);
//            allReceiver.add(receiver);    
//        }
//        return allReceiver;
    }
    
    public void addReceiver(){
        
        String firstName = receiver.getFirstName();
        String lastName = receiver.getLastName();
        String pass = receiver.getPassword();
        String type = receiver.getType();
        String country = receiver.getCountry();
        String email = receiver.getEmail();
        
            
        String sql = "INSERT INTO `receivertable`(`First_Name`,`Last_Name`, `Email`,`Password`,`Type`,`Country`) "
        + "VALUES ('" + firstName + "','" + lastName + "','" + email + "','" + pass + "','" + type + "','" + country + "')";//        fetch();
        DbConnection.query(sql);    
    }    

    public boolean validateReceiver(String inputEmail,String inputPassword) throws SQLException{
        ArrayList<String> credentials = new ArrayList();
        String query = "Select Email,Password from receivertable";
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
    
    public ResultSet getReceiver(String user){
        String query = "Select * from receivertable where Email = '"+ user.split("-")[0].trim() + "';";
        ResultSet resultSet = DbConnection.selectQuery(query); 
        return resultSet;
    }
    public ResultSet getReceiverCount(){
        String query = "Select count(*) as Count from receivertable;";
        ResultSet resultSet = DbConnection.selectQuery(query); 
        return resultSet;
    }
    
    public void updateCause(int rId,int causeId){
        String sql = "UPDATE cause SET R_Id = "+ rId+" where Cause_Id = " + causeId  + ";";
        DbConnection.query(sql);                  
    }

    public ArrayList<Cause> trackCause(int receiverID) throws SQLException{
        ArrayList<Cause> allReceiver = new ArrayList();
        String trackQuery = "Select * from cause where R_Id = '"+ receiverID +"';";
        ResultSet resultSet = DbConnection.selectQuery(trackQuery); 
//        ResultSet resultSet = DbConnection.selectQuery(query1);    
        while(resultSet.next()){

            String organisation = resultSet.getString("NGO_Org");
            String country = resultSet.getString("Country");
            String name = resultSet.getString("Cause_Name");
            String description = resultSet.getString("Cause_Desc");
            String category = resultSet.getString("R_Category");
            // String status = resultSet.getString("Status");
            String causeId = resultSet.getString("Cause_Id");
            //System.out.println(causeId);
            boolean status = Integer.parseInt(resultSet.getString("Status")) == 0 ? false : true;
            
            Cause cause = new Cause(name,description,organisation,category,country,true);
            cause.setCauseId(Integer.valueOf(causeId));
            cause.setRecId(receiverID);
            allReceiver.add(cause);
        }
        return allReceiver;           
    }
    
    public ResultSet getCauseBackground(int causeId){
        String query = "Select * from financialaiddb.cause a inner Join financialaiddb.receivertable b on a.R_Id = b.ID where Cause_Id = "+causeId+";";
        ResultSet resultSet = DbConnection.selectQuery(query); 
        return resultSet;
    }
    
}
