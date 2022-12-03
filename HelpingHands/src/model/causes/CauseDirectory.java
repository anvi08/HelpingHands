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
    
    public CauseDirectory(Cause cause){
        
        this.cause = cause;
        this.allCauses = allCauses;
        
    }

    
    public  ArrayList<Cause> popCauseTable(String query) throws SQLException{
        ArrayList<Cause> allCauses = new ArrayList();
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
        
        int status = cause.isStatus() == false ? '0' : '1';
        
        String sql = "INSERT INTO `cause`(`NGO_Org`, `Cause_Name`,`Cause_Desc`,`R_Category`,`Country`,`Status`) "
                + "VALUES ('" + cause.getNgoOrg() + "','" + cause.getCauseName() + "','" + cause.getCauseDesc() + "','" + cause.getRecCategory() + "','" + cause.getCountry() + "','" + status + "')";//        fetch();
        DbConnection.query(sql);
    }    

    
}
