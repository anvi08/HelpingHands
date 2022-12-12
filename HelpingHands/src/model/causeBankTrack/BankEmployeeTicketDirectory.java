/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.causeBankTrack;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import utilities.DbConnection;

/**
 *
 * @author Anvi Jain
 */
public class BankEmployeeTicketDirectory {
    
    private BankEmployeeTicket bankEmployeeTicket;
    public ArrayList<BankEmployeeTicket> allBankEmployeeTicket;

    public BankEmployeeTicketDirectory(BankEmployeeTicket bankEmployeeTicket) {
        this.bankEmployeeTicket = bankEmployeeTicket;
        this.allBankEmployeeTicket = allBankEmployeeTicket;
    }

    
    
    public void addBankEmployeeTicket(BankEmployeeTicket bankEmployeeTicket) throws SQLException{
        
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); 
        Date createDate= bankEmployeeTicket.getCauseTicket().getCreatedDate();
        String createdDate = formatter.format(bankEmployeeTicket.getCauseTicket().getCreatedDate());
        String sql;
        sql = "Insert into `bankempticket` (`Cause_Tk_Id`, `Assigned_Date`)" + 
                "VALUES ('"+bankEmployeeTicket.getCauseTicket().getCauseId()+"','"+ createdDate+"')";
        System.out.println(sql);
        DbConnection.query(sql);
        
        int causeId = bankEmployeeTicket.getCauseTicket().getCauseId();
        String causeName = getCauseNameById(causeId);
        BankAssignTicket bankAssignTicket;
        bankAssignTicket = new BankAssignTicket(causeName, createDate);
        BankAssignTicketDirectory bankAssignTicketDirectory = new BankAssignTicketDirectory(bankAssignTicket);
    }
    
    public String getCauseNameById(int causeId) throws SQLException{
        String causeName = null;
        String query = "Select * from cause where Cause_Id = '"+causeId+"';";
        ResultSet resultset = DbConnection.selectQuery(query);
        
        while(resultset.next()){
            causeName = resultset.getString("Cause_Name");
        }
        return causeName;
    }
    
    
    
}
