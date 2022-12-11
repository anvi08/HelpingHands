/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.causes;

//import com.mysql.cj.xdevapi.Statement;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.ArrayList;
import utilities.DbConnection;
import java.sql.ResultSet;
import java.sql.SQLException;

import utilities.DbConnection;

/**
 *
 * @author abhis
 */
public class CauseDirectory {
    private Cause cause;
    public ArrayList<Cause> allCauses;   
    private String query1;
    
    public CauseDirectory(Cause cause){
        
        this.cause = cause;
        // this.allCauses = allCauses;
        
    }

    
    public  ArrayList<Cause> popCauseTable(String loggedInUser) throws SQLException{
        ArrayList<Cause> allCauses = new ArrayList();

        if(loggedInUser==null){
            query1 = "Select * from cause;";            
        }else{
            query1 = "Select * from cause where NGO_Org = '"+loggedInUser+"';";
        }

        ResultSet resultSet = DbConnection.selectQuery(query1);    
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
            allCauses.add(cause);    
        }
        return allCauses;
    }

    public  ArrayList<Cause> popActiveCauseTable(String loggedInUser) throws SQLException{
        ArrayList<Cause> allCauses = new ArrayList();

        if(loggedInUser==null){
             query1 = "select * from financialaiddb.cause where `Status` = 1;";            
        }else{
             query1 = "select * from financialaiddb.cause where `Status` = 1 and `NGO_Org` = '"+ loggedInUser + "';";
        }

        ResultSet resultSet = DbConnection.selectQuery(query1);    
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
            allCauses.add(cause);    
        }
        return allCauses;
    }    

    public  ArrayList<Cause> popInactiveCauseTable(String loggedInUser) throws SQLException{
        ArrayList<Cause> allCauses = new ArrayList();

        if(loggedInUser==null){
             query1 = "select * from financialaiddb.cause where `Status` != 1;";            
        }else{
             query1 = "select * from financialaiddb.cause where `Status` != 1 and `NGO_Org` = '"+ loggedInUser + "';";
        }

        ResultSet resultSet = DbConnection.selectQuery(query1);    
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
            allCauses.add(cause);    
        }
        return allCauses;
    }    
    
    public  ArrayList<Cause> popCauseTableNGo(String loggedInUser) throws SQLException{
        ArrayList<Cause> allCauses = new ArrayList();
        String query = "Select * from cause where NGO_Org = '"+loggedInUser+"';" ;
        ResultSet resultSet = DbConnection.selectQuery(query);    
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
            allCauses.add(cause);    
        }
        return allCauses;
    }
    
    public void addCause(){
        
        int status = cause.isStatus() == false ? 0 : 1;
        
        String sql = "INSERT INTO `cause`(`NGO_Org`, `Cause_Name`,`Cause_Desc`,`R_Category`,`Country`,`Status`)"
                + "VALUES ('" + cause.getNgoOrg() + "','" + cause.getCauseName() + "','" + cause.getCauseDesc() + "','" + cause.getRecCategory() + "','" + cause.getCountry() + "','" + status + "')";//        fetch();
        DbConnection.query(sql);
    }    

    public ResultSet getCauseOrgs(){
        String query = "Select count(Cause_Name) as Count,NGO_Org from  financialaiddb.cause group by NGO_Org;";
        ResultSet resultSet = DbConnection.selectQuery(query); 
        return resultSet;
    }    
    
    
    public void updateCause(Cause cause, int causeId){
        
        int status = cause.isStatus() == false ? 0 : 1;
        
        String updateQuery = "Update financialaiddb.cause Set NGO_Org = '"+ cause.getNgoOrg() + "', Cause_Name = '"+ cause.getCauseName() + "',Cause_Desc = '" + cause.getCauseDesc() + "'," + 
                 "R_Category = '" + cause.getRecCategory() + "' ," + " Country = '" + cause.getCountry() + 
                   "' where Cause_Id = " + causeId +";"; 
        DbConnection.query(updateQuery);
    }        
    
    public void deletCause(String causeName){
            String deleteQuery = "Delete from financialaiddb.cause where Cause_Name = '" + causeName + "';";        
            DbConnection.query(deleteQuery);        
    }
    
    public Cause fetchCauseById(int causeId) throws SQLException {
        Cause cause = null;
        String query = "select * from cause where Cause_Id = " + causeId + ";";
        ResultSet resultSet = DbConnection.selectQuery(query);
        while(resultSet.next()){

            String organisation = resultSet.getString("NGO_Org");
            String country = resultSet.getString("Country");
            String name = resultSet.getString("Cause_Name");
            String description = resultSet.getString("Cause_Desc");
            String category = resultSet.getString("R_Category");
            // String status = resultSet.getString("Status");
            String id = resultSet.getString("Cause_Id");
            //System.out.println(causeId);
            boolean status = Integer.parseInt(resultSet.getString("Status")) == 0 ? false : true;
            
            cause = new Cause(name,description,organisation,category,country,true);
            cause.setCauseId(Integer.valueOf(id));  
        }
        return cause;
    }
    
}
