/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.causeBankTrack;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author HP
 */
public class BankAssignTicketDirectory {
    private BankAssignTicket bankAssignTicket;
    public ArrayList<BankAssignTicket> allBankAssignTicket;
    
    

    public BankAssignTicketDirectory(BankAssignTicket bankAssignTicket) {
        this.bankAssignTicket = bankAssignTicket;
        this.allBankAssignTicket = new ArrayList<BankAssignTicket>();
    }
    
    public void addBankAssignTicket(String causeName, Date createDate){
        bankAssignTicket = new BankAssignTicket(causeName, createDate);
        allBankAssignTicket.add(bankAssignTicket);
    }

    public ArrayList<BankAssignTicket> getAllBankAssignTicket() {
        return allBankAssignTicket;
    }
    
    
    
}
