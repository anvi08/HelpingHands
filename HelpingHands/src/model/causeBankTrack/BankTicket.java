/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.causeBankTrack;

import java.util.Date;

/**
 *
 * @author HP
 */
public class BankTicket {
    
    private int bankTkId;
    private int causeTkId;
    private int bankEmpId;
    private Date assignedDate;

    public BankTicket(int bankTkId, int causeTkId, int bankEmpId, Date assignedDate) {
        this.bankTkId = bankTkId;
        this.causeTkId = causeTkId;
        this.bankEmpId = bankEmpId;
        this.assignedDate = assignedDate;
    }
    
    

    public int getBankTkId() {
        return bankTkId;
    }

    public void setBankTkId(int bankTkId) {
        this.bankTkId = bankTkId;
    }

    public int getCauseTkId() {
        return causeTkId;
    }

    public void setCauseTkId(int causeTkId) {
        this.causeTkId = causeTkId;
    }

    public int getBankEmpId() {
        return bankEmpId;
    }

    public void setBankEmpId(int bankEmpId) {
        this.bankEmpId = bankEmpId;
    }

    public Date getAssignedDate() {
        return assignedDate;
    }

    public void setAssignedDate(Date assignedDate) {
        this.assignedDate = assignedDate;
    }
    
    
    
}
