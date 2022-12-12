/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.causeBankTrack;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import utilities.DbConnection;

/**
 *
 * @author HP
 */
public class BankAssignTicketDirectory {
    private BankAssignTicket bankAssignTicket;
    public ArrayList<BankAssignTicket> allBankAssignTicket = new ArrayList<BankAssignTicket>();
    
    

    public BankAssignTicketDirectory(BankAssignTicket bankAssignTicket) {
        this.bankAssignTicket = bankAssignTicket;
        allBankAssignTicket.add(bankAssignTicket);
        //this.allBankAssignTicket = new ArrayList<BankAssignTicket>();
    }
    
    public void addBankAssignTicket(String causeName, Date createDate){
        bankAssignTicket = new BankAssignTicket(causeName, createDate);
        allBankAssignTicket.add(bankAssignTicket);
    }

    public ArrayList<BankAssignTicket> getAllBankAssignTicket() {
        return allBankAssignTicket;
    }
    
    /*public ArrayList<BankAssignTicket> fetchAllBankAssignTicket(){
        String query = "Select * from bankempticket;";
        
        int causeId = bankEmployeeTicket.getCauseTicket().getCauseId();
        String causeName = null;
        String query1 = "Select * from cause where Cause_Id = '"+causeId+"';";
        ResultSet resultset = DbConnection.selectQuery(query);
        
        return allBankAssignTicket;
    }*/
    
    
}
