/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package profile.justiceDepartment;
import utilities.Constants;

/**
 *
 * @author Khalesi
 */
public class JusticeDepartment extends profile.Person { 
    
    private String profileRole;
    private String country;
    private boolean status;
    private int empId;
   // private profile.Person profile;
    
    public JusticeDepartment(String firstName, String lastName, String email, String password, String empType, String country) {
        super(firstName, lastName, email, password, empType);
        this.country = country;
        this.profileRole = Constants.profileRoleJustice;
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
    
    
    
}
