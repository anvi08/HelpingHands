/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package profile.serviceprovider;

/**
 *
 * @author abhis
 */
public class ServiceProvider extends profile.Person{


    private String profileRole;
    private String country;
    private String type;
    private int id;
    private boolean status;
    
    public ServiceProvider(String firstName, String lastName, String email, String password, String user, String country, String type){
         super(firstName, lastName, email, password);       
         this.profileRole = profileRole;
         this.country = country;
         this.type = type;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getProfileRole() {
        return profileRole;
    }

    public void setProfileRole(String profileRole) {
        this.profileRole = profileRole;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
