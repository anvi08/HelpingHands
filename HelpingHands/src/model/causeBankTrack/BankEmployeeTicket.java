/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.causeBankTrack;

import java.util.ArrayList;
import model.causes.Cause;
import model.causeticket.CauseTicket;
import profile.bank.BankPerson;
import profile.bank.BankPersonDirectory;

/**
 *
 * @author Anvi Jain
 */
public class BankEmployeeTicket {
    private Cause cause;
    private CauseTicket causeTicket;
    private BankTicket bankTicket;
    private int bk_tk_no;
    
    //public ArrayList<BankEmployeeTicket> allBankEmployeeTicket;
    
    public BankEmployeeTicket(BankTicket bankTcket) {
        this.bankTicket = bankTcket;
        this.cause = null;
        this.causeTicket = null;
        
    }
    
        public BankEmployeeTicket(CauseTicket causeTicket) {
        this.causeTicket = causeTicket;
        
    }
    public BankTicket getBankTicket() {
        return bankTicket;
    }

    public void setBankTicket(BankTicket bankTicket) {
        this.bankTicket = bankTicket;
    }
    
    
    public Cause getCause() {
        return cause;
    }

    public void setCause(Cause cause) {
        this.cause = cause;
    }


    public int getBk_tk_no() {
        return bk_tk_no;
    }

    public void setBk_tk_no(int bk_tk_no) {
        this.bk_tk_no = bk_tk_no;
    }

    public CauseTicket getCauseTicket() {
        return causeTicket;
    }

    public void setCauseTicket(CauseTicket causeTicket) {
        this.causeTicket = causeTicket;
    }

    @Override
    public String toString() {
        return getCause().getCauseName();
    }
    
    
    
}
