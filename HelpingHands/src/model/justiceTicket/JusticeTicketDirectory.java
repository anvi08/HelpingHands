/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.justiceTicket;

import java.sql.SQLException;

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
        String query = "INSERT INTO `justiceticket`(`cause_tkt_id`, `created_date`,`Status`,`updated_date`,`Country`) "
                + "VALUES ('" + jTicket.getCauseTktId()+ "','" + jTicket.getCreatedDate()+ "','" + jTicket.getjTktStatus()+ "','" + jTicket.getUpdatedDate()+ "','" + jTicket.getjCountry()+ "');";
    }
    
}
