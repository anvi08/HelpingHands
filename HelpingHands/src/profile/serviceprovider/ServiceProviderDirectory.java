/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package profile.serviceprovider;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.causes.Cause;
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
        ArrayList<Cause> allServices = new ArrayList();

        String tableQuery = "Select * from cause where NGO_Org = '"+ cause1 + "' and R_Id is null and R_Category = 'Community';";
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
    public ResultSet getService(String user){
        String query = "Select * from receivertable where Email = '"+ user.split("-")[0].trim() + "';";
        System.out.println("get Service "+ query);
        ResultSet resultSet = DbConnection.selectQuery(query); 
        return resultSet;
    }

    public ArrayList<Cause> trackCause(String cause1,int receiverID) throws SQLException{
        ArrayList<Cause> allReceiver = new ArrayList();
        String trackQuery = "Select * from cause where NGO_Org = '"+ cause1 + "' and R_Id = '"+ receiverID +"';";
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
