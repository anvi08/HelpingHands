/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package twofa;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

/**
 *
 * @author Khalesi
 */
public class EmailNotification {
    
    
        public static void SendEmail(String password,String emailID) {    
                    final String helpingHandsEmail = "helpinhandstest@gmail.com";
                    final String helpingHandsPassword = "arvxiihmmgpmemin";//"bkzneapqigmuzfck";//";lkjhj_11";    
                    
            Properties configuration = new Properties();
            configuration.put("mail.smtp.auth", "true");
            configuration.put("mail.smtp.starttls.enable", "true");
            String host = "smtp.gmail.com";
            configuration.put("mail.smtp.host",host);
            configuration.put("mail.smtp.port", "587");
            configuration.put("mail.smtp.ssl.trust", host);
            configuration.put("mail.smtp.ssl.protocols", "TLSv1.2");
            // Setting up the Email Server

            configuration.put("mail.smtp.starttls.enable", "true");
            Session session = Session.getDefaultInstance(configuration, new javax.mail.Authenticator(){  
            protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(helpingHandsEmail, helpingHandsPassword);
                }
            });

            try {
                Message message = new MimeMessage(session);
                        message.setFrom(new InternetAddress(helpingHandsEmail));
                        message.setRecipients(
                                Message.RecipientType.TO,
                                InternetAddress.parse(emailID)
                        );
                        message.setSubject("Orange Flag Raised");
                        message.setText("Dear " + emailID+" ,"
                                + "\n\n " +" Please check the status of the funds in your track fund section as an Orange Flag has been raised");
                Transport sessionBuild = session.getTransport("smtp");
                sessionBuild.connect(host, helpingHandsEmail, helpingHandsPassword);
                sessionBuild.sendMessage(message, message.getAllRecipients());
                sessionBuild.close();
                JOptionPane.showMessageDialog(null,"Email notification is sent.");
                System.out.println("Sent message successfully....");
            } catch (MessagingException ex) {
                ex.printStackTrace();
            }
    } 
}
