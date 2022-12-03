/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package profile;

/**
 *
 * @author Shreya Sharma
 */
public abstract class Person {
    
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String empType;
    
    public Person(String firstName, String lastName, String email, String password, String empType) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.empType = empType;
        
    }
    abstract public void setCountry(String country);
    abstract public void setProfileRole(String profileRole);
    abstract public void setStatus(boolean role);
    abstract public void setEmpId(int empId);
    
    abstract public String getCountry();
    abstract public String getProfileRole();
    abstract public boolean isStatus();
    abstract public int getEmpId();
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmpType(String type) {
        this.empType = type;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getEmpType() {
        return empType;
    }
}
