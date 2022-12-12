/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.causeBankTrack;

import java.util.ArrayList;
import utilities.DbConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
/**
 *
 * @author HP
 */
public class BankTicketDirectory {
    
    private BankTicket bankTicket;
    public ArrayList<BankTicket> bankTicketList;

    public BankTicketDirectory(BankTicket bankTicket) {
        this.bankTicket = bankTicket;
        this.bankTicketList = new ArrayList<BankTicket>();
    }

    public ArrayList<BankTicket> fetchBankTicket() throws SQLException{
        ArrayList<BankTicket> allBankTickets = new ArrayList();
        String query = "Select * from bankempticket;";
        
        ResultSet resultset = DbConnection.selectQuery(query);
        while(resultset.next()){
                int bankTkId = resultset.getInt("Bank_Tk_Id");
                int causeTkId = resultset.getInt("Cause_Tk_Id");
                int bankEmpId = resultset.getInt("Employee_Id");
                Date assignedDate = resultset.getDate("Assigned_Date");
                
                BankTicket newBankTicket = new BankTicket(bankTkId, causeTkId, bankEmpId, assignedDate);
                allBankTickets.add(newBankTicket);
            }
        
        
        return allBankTickets;
    }

    
    
    
}
