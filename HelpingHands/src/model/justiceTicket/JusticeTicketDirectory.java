/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.justiceTicket;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import profile.justiceDepartment.JusticeDepartmentEmployee;
import utilities.Constants;
import utilities.DbConnection;

/**
 *
 * @author Khalesi
 */
public class JusticeTicketDirectory {
    private JusticeTicket jTicket;
    
    public JusticeTicketDirectory(JusticeTicket jTicket) {
        this.jTicket = jTicket;
    }
    
    public void createJusticeTicket() throws SQLException  {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); 
        String date = formatter.format(jTicket.getCreatedDate());
        
        
        String query = "INSERT INTO `justiceticket`(`cause_tkt_id`, `created_date`,`Status`,`updated_date`,`Country`) "
                + "VALUES ('" + jTicket.getCauseTktId()+ "','" + date + "','" + jTicket.getjTktStatus()+ "','" + date+ "','" + jTicket.getjCountry()+ "');";
        
        DbConnection.query(query);
    }
    
    public ArrayList<JusticeTicket> fetchTicketDataForJsticeAdmin(String country) throws SQLException {
        ArrayList<JusticeTicket> justiceTicketList = new ArrayList<JusticeTicket>();
        String query;
        if (country != null) {
            query = "select * from justiceticket where j_emp_id is NULL AND Status = '" + Constants.justiceTicketStatus.get("new") 
                + "' AND country = '" + country + "';";
        } else {
            query = "select * from justiceticket where j_emp_id is NULL AND Status = '" + Constants.justiceTicketStatus.get("new") 
                + "';";
        }
         ResultSet resultSet = DbConnection.selectQuery(query);
         if (resultSet != null) {
             DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); 
             Date tktCreatedDate = null;
             Date tktUdatedDate = null;
             while (resultSet.next()) {
                 int jEmpId = 0;
                 String jEmpCMnt = null;
                 int j_tkt_id = Integer.parseInt(resultSet.getString("j_tkt_id"));
                 int cause_tkt_id = Integer.parseInt(resultSet.getString("cause_tkt_id"));
                 if (resultSet.getString("j_emp_id") != null) {
                    jEmpId = Integer.parseInt(resultSet.getString("j_emp_id"));
                 }
                 if (resultSet.getString("j_emp_cmnt") != null) {
                     jEmpCMnt = resultSet.getString("j_emp_cmnt");
                 }
                 String createdDate = resultSet.getString("created_date");
                 String updatedDate = resultSet.getString("updated_date");
                 String status = resultSet.getString("Status");
                 // String jEmpCmnt = resultSet.getString("j_emp_cmnt");
                 String jCountry = resultSet.getString("country");
                 try {
                    tktCreatedDate = formatter.parse(createdDate); 
                    tktUdatedDate = formatter.parse(updatedDate);
                 } catch (Exception e) {
                     System.out.println(e);
                 }
                 
                 JusticeTicket jTicket = new JusticeTicket(cause_tkt_id, tktCreatedDate, status, jCountry, tktUdatedDate);
                 jTicket.setjTktId(j_tkt_id);
                 jTicket.setjCmnt(jEmpCMnt);
                 jTicket.setjEmpId(jEmpId);
                         
                 justiceTicketList.add(jTicket);
             }
         }
        return  justiceTicketList;
    }
    
     public ArrayList<JusticeTicket> fetchAssignedTickets(String country, int empId) throws SQLException {
         ArrayList<JusticeTicket> justiceTicketList = new ArrayList<JusticeTicket>();
         String query = "select * from justiceticket where j_emp_id IS NOT NULL;"; 
         if (empId > 0 && country != null) {
             query = query + " AND country = '" + country + "' AND j_tkt_id = " + empId + ";";
         }
         ResultSet resultSet = DbConnection.selectQuery(query);
             if (resultSet != null) {
             DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); 
             Date tktCreatedDate = null;
             Date tktUdatedDate = null;
              int jEmpId = 0;
              String jEmpCMnt = null;
             while (resultSet.next()) {
                 int j_tkt_id = Integer.parseInt(resultSet.getString("j_tkt_id"));
                 int cause_tkt_id = Integer.parseInt(resultSet.getString("cause_tkt_id"));
                 // int j_emp_id = Integer.parseInt(resultSet.getString("j_emp_id"));
                 String createdDate = resultSet.getString("created_date");
                 String updatedDate = resultSet.getString("updated_date");
                 String status = resultSet.getString("Status");
                 // String jEmpCmnt = resultSet.getString("j_emp_cmnt");
                 String jCountry = resultSet.getString("country");
                 if (resultSet.getString("j_emp_id") != null) {
                    jEmpId = Integer.parseInt(resultSet.getString("j_emp_id"));
                 }
                 if (resultSet.getString("j_emp_cmnt") != null) {
                     jEmpCMnt = resultSet.getString("j_emp_cmnt");
                 }
                 try {
                    tktCreatedDate = formatter.parse(createdDate); 
                    tktUdatedDate = formatter.parse(updatedDate);
                 } catch (Exception e) {
                     System.out.println(e);
                 }
                 
                 JusticeTicket jTicket = new JusticeTicket(cause_tkt_id, tktCreatedDate, status, jCountry, tktUdatedDate);
                 jTicket.setjTktId(j_tkt_id);
                 jTicket.setjCmnt(jEmpCMnt);
                 jTicket.setjEmpId(jEmpId);
                         
                 justiceTicketList.add(jTicket);
             }
         }
             
        return  justiceTicketList;
    }
     
     public void assignJusticeEmployeeToTicket(int jTktId, int emp_id) throws SQLException {
         Date date = new Date();
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); 
        String updatedDate = formatter.format(date);
        String query = "Update `justiceticket` Set Status = 'ASSIGNED',  updated_date = '" + updatedDate +  "' ,j_emp_id = " + emp_id + " where j_tkt_id = " + jTktId + ";";     
         System.out.println ("query " + query);
         DbConnection.query(query);
     }
     
    public ArrayList<JusticeTicket> fetchAssignedTicketsByJEmpID(int justEmpId)throws SQLException {
         ArrayList<JusticeTicket> justiceTicketList = new ArrayList<JusticeTicket>();
         String query = "select * from justiceticket where j_emp_id = " + justEmpId + ";"; 
         ResultSet resultSet = DbConnection.selectQuery(query);
             if (resultSet != null) {
             DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); 
             Date tktCreatedDate = null;
             Date tktUdatedDate = null;
              int jEmpId = 0;
              String jEmpCMnt = null;
             while (resultSet.next()) {
                 int j_tkt_id = Integer.parseInt(resultSet.getString("j_tkt_id"));
                 int cause_tkt_id = Integer.parseInt(resultSet.getString("cause_tkt_id"));
                 // int j_emp_id = Integer.parseInt(resultSet.getString("j_emp_id"));
                 String createdDate = resultSet.getString("created_date");
                 String updatedDate = resultSet.getString("updated_date");
                 String status = resultSet.getString("Status");
                 // String jEmpCmnt = resultSet.getString("j_emp_cmnt");
                 String jCountry = resultSet.getString("country");
                 if (resultSet.getString("j_emp_id") != null) {
                    jEmpId = Integer.parseInt(resultSet.getString("j_emp_id"));
                 }
                 if (resultSet.getString("j_emp_cmnt") != null) {
                     jEmpCMnt = resultSet.getString("j_emp_cmnt");
                 }
                 try {
                    tktCreatedDate = formatter.parse(createdDate); 
                    tktUdatedDate = formatter.parse(updatedDate);
                 } catch (Exception e) {
                     System.out.println(e);
                 }
                 
                 JusticeTicket jTicket = new JusticeTicket(cause_tkt_id, tktCreatedDate, status, jCountry, tktUdatedDate);
                 jTicket.setjTktId(j_tkt_id);
                 jTicket.setjCmnt(jEmpCMnt);
                 jTicket.setjEmpId(jEmpId);
                         
                 justiceTicketList.add(jTicket);
             }
         }
             
        return  justiceTicketList;
    }
    
    public void acceptTicket(int jTktId) throws SQLException {
         Date date = new Date();
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); 
        String updatedDate = formatter.format(date);
         String query = "Update `justiceticket` Set Status = 'WIP',  updated_date = '" + updatedDate +  "' where j_tkt_id = " + jTktId + ";";     
         System.out.println ("query " + query);
         DbConnection.query(query);
    }
    
    public void resolveTicket(int jTktId, String comment) throws SQLException {
         Date date = new Date();
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); 
        String updatedDate = formatter.format(date);
         String query = "Update `justiceticket` Set Status = 'RESOLVED',  updated_date = '" + updatedDate + "' , j_emp_cmnt = '" + comment +"' where j_tkt_id = " + jTktId + ";";     
         System.out.println ("query " + query);
         DbConnection.query(query);
    }
    
    public JusticeTicket fetchJusticeTicketByCauseId(int causeId)throws SQLException {
        JusticeTicket jTkt = null;
        if ( causeId > 0) {
            String query = "select * from justiceticket where cause_tkt_id = " + causeId + ";";
            ResultSet resultSet = DbConnection.selectQuery(query);
            
            if (resultSet != null) {
             DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); 
             Date tktCreatedDate = null;
             Date tktUdatedDate = null;
             while (resultSet.next()) {
                 int jEmpId = 0;
                 String jEmpCMnt = null;
                 int j_tkt_id = Integer.parseInt(resultSet.getString("j_tkt_id"));
                 int cause_tkt_id = Integer.parseInt(resultSet.getString("cause_tkt_id"));
                 if (resultSet.getString("j_emp_id") != null) {
                    jEmpId = Integer.parseInt(resultSet.getString("j_emp_id"));
                 }
                 if (resultSet.getString("j_emp_cmnt") != null) {
                     jEmpCMnt = resultSet.getString("j_emp_cmnt");
                 }
                 String createdDate = resultSet.getString("created_date");
                 String updatedDate = resultSet.getString("updated_date");
                 String status = resultSet.getString("Status");
                 // String jEmpCmnt = resultSet.getString("j_emp_cmnt");
                 String jCountry = resultSet.getString("country");
                 try {
                    tktCreatedDate = formatter.parse(createdDate); 
                    tktUdatedDate = formatter.parse(updatedDate);
                 } catch (Exception e) {
                     System.out.println(e);
                 }
                 
                  jTkt = new JusticeTicket(cause_tkt_id, tktCreatedDate, status, jCountry, tktUdatedDate);
                 jTkt.setjTktId(j_tkt_id);
                 jTkt.setjCmnt(jEmpCMnt);
                 jTkt.setjEmpId(jEmpId);
             }   
            }
        }
        return jTkt;
    } 
    
}
