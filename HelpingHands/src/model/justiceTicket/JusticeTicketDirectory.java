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
        String query = "select * from justiceticket where j_emp_id is NULL AND Status = '" + Constants.justiceTicketStatus.get("new") 
                + "' AND country = '" + country + "';";
         ResultSet resultSet = DbConnection.selectQuery(query);
         if (resultSet != null) {
             DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); 
             Date tktCreatedDate = null;
             Date tktUdatedDate = null;
             while (resultSet.next()) {
                 int j_tkt_id = Integer.parseInt(resultSet.getString("j_tkt_id"));
                 int cause_tkt_id = Integer.parseInt(resultSet.getString("cause_tkt_id"));
                 // int j_emp_id = Integer.parseInt(resultSet.getString("j_emp_id"));
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
                // jTicket.setjCmnt(jEmpCmnt);
              //   jTicket.setjEmpId(j_emp_id);
                         
                 justiceTicketList.add(jTicket);
             }
         }
        return  justiceTicketList;
    }
    
}
