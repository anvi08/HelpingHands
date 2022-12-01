/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.causes;

/**
 *
 * @author abhis
 */
public class Cause {
    private int causeId;
    private String causeName;
    private String causeDesc;
    private String ngoOrg;
    private String recCategory;
    private String country;
    private int recId;
    private int req;
    private boolean status;
    
    public Cause(String causeName, String causeDesc, String ngoOrg, String recCategory, String country, boolean status) {

        this.causeName = causeName;
        this.causeDesc = causeDesc;
        this.ngoOrg = ngoOrg; 
        this.recCategory = recCategory;
        this.country = country;
        this.status = status;
    }

    public int getCauseId() {
        return causeId;
    }

    public String getCauseName() {
        return causeName;
    }

    public String getCauseDesc() {
        return causeDesc;
    }

    public String getNgoOrg() {
        return ngoOrg;
    }

    public String getRecCategory() {
        return recCategory;
    }

    public String getCountry() {
        return country;
    }

    public int getRecId() {
        return recId;
    }

    public int getReq() {
        return req;
    }

    public boolean isStatus() {
        return status;
    }

    public void setCauseId(int causeId) {
        this.causeId = causeId;
    }

    public void setCauseName(String causeName) {
        this.causeName = causeName;
    }

    public void setCauseDesc(String causeDesc) {
        this.causeDesc = causeDesc;
    }

    public void setNgoOrg(String ngoOrg) {
        this.ngoOrg = ngoOrg;
    }

    public void setRecCategory(String recCategory) {
        this.recCategory = recCategory;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setRecId(int recId) {
        this.recId = recId;
    }

    public void setReq(int req) {
        this.req = req;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    


    
}
