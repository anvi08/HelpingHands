/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package profile.Receiver;

import profile.Receiver.Receiver;
import utilities.DbConnection;

/**
 *
 * @author abhis
 */
public class ReceiverDirectory {
    private Receiver receiver;
    
    public ReceiverDirectory(Receiver receiver){
        this.receiver = receiver;
    }
    
    public void addReceiver(){
        
        String firstName = receiver.getFirstName();
        String lastName = receiver.getLastName();
        String pass = receiver.getLastName();
        String type = receiver.getType();
        Long contact = receiver.getContact();
        String country = receiver.getCountry();
        String email = receiver.getEmail();
        
            
        String sql = "INSERT INTO `receivertable`(`First_Name`,`Last_Name`, `Email`,`Password`,`Type`,`Contact`,`Country`) "
        + "VALUES ('" + firstName + "','" + lastName + "','" + email + "','" + pass + "','" + type + "','" + contact + "','" + country + "')";//        fetch();
        DbConnection.query(sql);    
    }    
}
