/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.causeticket;

import java.util.Date;

/**
 *
 * @author abhis
 */
public class CauseTicket {
    private int donorId;
    private int receiverId;
    private int causeId;
    private int tktId;
    private Date createdDate;
    private Date moneyDonorCountry;
    private Date moneyReceiverCountry;
    private Date moneyReceived;
    private String donorCountry;
    private String receivingCountry;
    private int amount;    
    
    public CauseTicket(int donorId, int receiverId, int causeId, Date createdDate,Date moneyDonorCountry, Date moneyReceiverCountry, Date moneyReceived, String donorCountry, String receivingCountry,int amount){
        this.donorId = donorId;
        this.receiverId = receiverId;
        this.causeId = causeId;
        this.createdDate = createdDate;
        this.moneyDonorCountry = moneyDonorCountry;
        this.moneyReceived = moneyReceived;
        this.moneyReceiverCountry = moneyReceiverCountry;
        this.donorCountry = donorCountry;
        this.receivingCountry = receivingCountry;
        this.amount = amount;
    }

    public int getDonorId() {
        return donorId;
    }

    public void setDonorId(int donorId) {
        this.donorId = donorId;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    public int getCauseId() {
        return causeId;
    }

    public void setCauseId(int causeId) {
        this.causeId = causeId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getMoneyDonorCountry() {
        return moneyDonorCountry;
    }

    public void setMoneyDonorCountry(Date moneyDonorCountry) {
        this.moneyDonorCountry = moneyDonorCountry;
    }

    public Date getMoneyReceiverCountry() {
        return moneyReceiverCountry;
    }

    public void setMoneyReceiverCountry(Date moneyReceiverCountry) {
        this.moneyReceiverCountry = moneyReceiverCountry;
    }

    public Date getMoneyReceived() {
        return moneyReceived;
    }

    public void setMoneyReceived(Date moneyReceived) {
        this.moneyReceived = moneyReceived;
    }

    public String getDonorCountry() {
        return donorCountry;
    }

    public void setDonorCountry(String donorCountry) {
        this.donorCountry = donorCountry;
    }

    public String getReceivingCountry() {
        return receivingCountry;
    }

    public void setReceivingCountry(String receivingCountry) {
        this.receivingCountry = receivingCountry;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getTktId() {
        return tktId;
    }

    public void setTktId(int tktId) {
        this.tktId = tktId;
    }
    
    
    @Override  
    public String toString(){
        return Integer.toString(donorId);
    }   
    
}
