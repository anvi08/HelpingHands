/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.causeBankTrack;

import java.util.Date;

/**
 *
 * @author Anvi Jain
 */
public class BankAssignTicket {
    private String causeName;
    private Date createDate;

    public BankAssignTicket(String causeName, Date createDate) {
        this.causeName = causeName;
        this.createDate = createDate;
    }

    public BankAssignTicket() {
    }

    public String getCauseName() {
        return causeName;
    }

    public void setCauseName(String causeName) {
        this.causeName = causeName;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

        
    
    
}
