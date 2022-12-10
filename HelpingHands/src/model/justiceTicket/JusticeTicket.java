/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.justiceTicket;

import java.util.Date;

/**
 *
 * @author Khalesi
 */
public class JusticeTicket {
    private int jTktId;
    private int causeTktId;
    private int jEmpId;
    private Date createdDate;
    private String jTktStatus;
    private Date updatedDate;
    private String jCmnt;
    private String jCountry;
    
    public JusticeTicket(int causeTktId, Date createdDate, String jTktStatus, String country, Date updatedDate) {
        this.causeTktId = causeTktId;
        this.createdDate = createdDate;
        this.jCountry = country;
        this.jTktStatus = jTktStatus;
        this.updatedDate = updatedDate;
        
}

    public int getjTktId() {
        return jTktId;
    }

    public void setjTktId(int jTktId) {
        this.jTktId = jTktId;
    }

    public int getCauseTktId() {
        return causeTktId;
    }

    public void setCauseTktId(int causeTktId) {
        this.causeTktId = causeTktId;
    }

    public int getjEmpId() {
        return jEmpId;
    }

    public void setjEmpId(int jEmpId) {
        this.jEmpId = jEmpId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getjTktStatus() {
        return jTktStatus;
    }

    public void setjTktStatus(String jTktStatus) {
        this.jTktStatus = jTktStatus;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getjCmnt() {
        return jCmnt;
    }

    public void setjCmnt(String jCmnt) {
        this.jCmnt = jCmnt;
    }

    public String getjCountry() {
        return jCountry;
    }

    public void setjCountry(String jCOuntry) {
        this.jCountry = jCOuntry;
    }
    
    
}
