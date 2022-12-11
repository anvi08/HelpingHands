/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.justiceTicket;
import model.causes.Cause;
import model.causeticket.CauseTicket;
import profile.Receiver.Receiver;
import profile.donor.Donor;

/**
 *
 * @author Khalesi
 */
public class JusticeTicketTrack {
    private JusticeTicket jTkt;
    private CauseTicket causeTicket;
    private Cause cause;
    private Donor donor;
    private Receiver receiver;
    
    public JusticeTicketTrack(JusticeTicket jTkt) {
        this.jTkt = jTkt;
        this.cause = null;
        this.causeTicket = null;
        this.donor = null;
        this.receiver = null;
    }

    public JusticeTicket getjTkt() {
        return jTkt;
    }

    public void setjTkt(JusticeTicket jTkt) {
        this.jTkt = jTkt;
    }

    public CauseTicket getCauseTicket() {
        return causeTicket;
    }

    public void setCauseTicket(CauseTicket causeTicket) {
        this.causeTicket = causeTicket;
    }

    public Cause getCause() {
        return cause;
    }

    public void setCause(Cause cause) {
        this.cause = cause;
    }

    public Donor getDonor() {
        return donor;
    }

    public void setDonor(Donor donor) {
        this.donor = donor;
    }

    public Receiver getReceiver() {
        return receiver;
    }

    public void setReceiver(Receiver receiver) {
        this.receiver = receiver;
    }
    
    

    @Override
    public String toString() {
        return getCause().getCauseName();
    }
    
    
}
