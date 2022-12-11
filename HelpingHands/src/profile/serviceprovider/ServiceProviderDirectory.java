/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package profile.serviceprovider;

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
public class ServiceProviderDirectory {
    private ServiceProvider serviceProvider;
    public ArrayList<ServiceProvider> allServiceProvider;    
    
    public ServiceProviderDirectory(ServiceProvider serviceProvider){
        this.serviceProvider = serviceProvider;
    }

    public  ArrayList<Cause> popServiceTable(String cause1) throws SQLException{
        String tableQuery = null;
        ArrayList<Cause> allServices = new ArrayList();
        if(cause1==null){
             tableQuery = "Select * from cause where R_Id is null and R_Category = 'Community';";            
            
        }else{
             tableQuery = "Select * from cause where NGO_Org = '"+ cause1 + "' and R_Id is null and R_Category = 'Community';";            
        }
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
            allServices.add(cause);
        }
        return allServices;   
    }
    public void updateCause(int rId,int causeId,int requirements){
        String sql = "UPDATE cause SET R_Id = "+ rId+" , Requirement = "+requirements+" where Cause_Id = " + causeId  + ";";
        System.out.println(sql);
        DbConnection.query(sql);                  
    }    
    public ArrayList<Receiver> getService(String user) throws SQLException{
        ArrayList<Receiver> allReceiver = new ArrayList();        
        String query = "Select * from receivertable where Email = '"+ user.split("-")[0].trim() + "';";
        System.out.println("get Service "+ query);
        ResultSet resultSet = DbConnection.selectQuery(query); 
        while(resultSet.next()){

            String firstName = resultSet.getString("First_Name");
            String lastName = resultSet.getString("Last_Name");
            String email = resultSet.getString("Email");
            String password = resultSet.getString("password");
            String type = resultSet.getString("Type");
            String user1 = "";
            String Id1 = resultSet.getString("ID");
            // String status = resultSet.getString("Status");
            String country = resultSet.getString("Country");
            //System.out.println(causeId);
            
            Receiver receiver = new Receiver(firstName,lastName,email,password,user1,country,type);
            receiver.setId(Integer.valueOf(Id1));
            allReceiver.add(receiver);
        }
        return allReceiver;         
    }

    public ArrayList<Receiver> getService2(String user) throws SQLException{
        ArrayList<Receiver> allReceiver = new ArrayList();        
        String query = "Select * from receivertable where Last_Name = '"+ user + "';";
        System.out.println("get Service "+ query);
        ResultSet resultSet = DbConnection.selectQuery(query); 
        while(resultSet.next()){

            String firstName = resultSet.getString("First_Name");
            String lastName = resultSet.getString("Last_Name");
            String email = resultSet.getString("Email");
            String password = resultSet.getString("password");
            String type = resultSet.getString("Type");
            String user1 = "";
            String Id1 = resultSet.getString("ID");
            // String status = resultSet.getString("Status");
            String country = resultSet.getString("Cause_Id");
            //System.out.println(causeId);
            
            Receiver receiver = new Receiver(firstName,lastName,email,password,user1,country,type);
            receiver.setId(Integer.valueOf(Id1));
            allReceiver.add(receiver);
        }
        return allReceiver;          
    }    

    public ArrayList<Receiver> getUser(String user) throws SQLException{
        ArrayList<Receiver> allReceiver = new ArrayList();
        String query = "Select * from receivertable where Last_Name = '"+ user + "';";
        ResultSet resultSet = DbConnection.selectQuery(query); 
        while(resultSet.next()){

            String firstName = resultSet.getString("First_Name");
            String lastName = resultSet.getString("Last_Name");
            String email = resultSet.getString("Email");
            String password = resultSet.getString("password");
            String type = resultSet.getString("Type");
            String user1 = "";
            String Id1 = resultSet.getString("ID");
            // String status = resultSet.getString("Status");
            String country = resultSet.getString("Country");
            //System.out.println(causeId);
            
            Receiver receiver = new Receiver(firstName,lastName,email,password,user1,country,type);
            receiver.setId(Integer.valueOf(Id1));
            allReceiver.add(receiver);
        }
        return allReceiver;           
        
    }
    
    public ArrayList<Cause> trackCause(String cause1,int receiverID) throws SQLException{
        String trackQuery = null;
        ArrayList<Cause> allReceiver = new ArrayList();
        if(receiverID==0&&cause1==null){
            trackQuery = "Select * from cause where R_Id is not null";             
        }else{
            trackQuery = "Select * from cause where NGO_Org = '"+ cause1 + "' and R_Id = '"+ receiverID +"';"; 
        }
        System.out.println("QUERYYYYYYYYYY"+trackQuery);
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

}
