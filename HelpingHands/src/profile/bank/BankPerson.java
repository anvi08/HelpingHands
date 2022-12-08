/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package profile.bank;

import profile.Person;
import utilities.Constants;

/**
 *
 * @author Anvi Jain
 */
public class BankPerson extends Person{
    
    //private String firstName;
    private int bankPersonId;
    private String profileRole;
    private String country;
    private boolean status;
    private int empId;
    private String bankName;
    private String empType;

    public BankPerson(String firstName, String lastName, String email, String password, String empType, String bankName, boolean status,  String country) {
        super(firstName, lastName, email, password);
        //this.firstName=firstName;
        this.country = country;
        this.profileRole = Constants.profileRoleBank;
        this.bankName = bankName;
        this.empType = empType;
    }

    public String getProfileRole() {
        return profileRole;
    }

    public void setProfileRole(String profileRole) {
        this.profileRole = profileRole;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getEmpType() {
        return empType;
    }

    public void setEmpType(String empType) {
        this.empType = empType;
    }

    public int getBankPersonId() {
        return bankPersonId;
    }

    public void setBankPersonId(int bankPersonId) {
        this.bankPersonId = bankPersonId;
    }
    
    
    
    @Override
    public String toString(){
        return getFirstName();
    }
    
    
    
}
