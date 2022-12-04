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
public class JusticeDepartmentEmployee extends profile.Person { 
    
    private String profileRole;
    private String country;
    private boolean status;
    private int id;
    private String empType;
   // private profile.Person profile;
    
    public JusticeDepartmentEmployee(String firstName, String lastName, String email, String password, String empType, String country) {
        super(firstName, lastName, email, password);
        this.empType = empType;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public void setEmpType(String type) {
        this.empType = type;
    }
    
    
    public String getEmpType() {
        return empType;
    }
    
    
}
