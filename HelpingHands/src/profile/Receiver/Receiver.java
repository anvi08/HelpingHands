/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package profile.Receiver;

/**
 *
 * @author abhis
 */
public class Receiver extends profile.Person{

    private Long contact;
    private String profileRole;
    private String country;
    private String type;
    private int id;
    private boolean status;
    
    public Receiver(String firstName, String lastName, String email, String password, Long contact, String user, String country, String type){
         super(firstName, lastName, email, password);       
         this.contact = contact;
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

    public Long getContact() {
        return contact;
    }

    public void setContact(Long contact) {
        this.contact = contact;
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
