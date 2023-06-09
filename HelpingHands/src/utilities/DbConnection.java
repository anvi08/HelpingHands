/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilities;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author abhis
 */
public class DbConnection {
    
    private static final String URL = "jdbc:mysql://localhost:3306/financialaiddb?zeroDateBehavior=convertToNull";
    private static final String USER_NAME = "root";
    private static final String ABHI_PASSWORD = "@Fd2556b9dd1997";
    private static final String ANVI_PASSWORD = "root";
    private static final String SHREYA_PASSWORD = "Friends_1306";
    
    private static Connection connection = null;
    private static Statement statement = null;
    
    
    /* Creating Connection*/
    public static void connection(){
       try{
            connection = DriverManager.getConnection(URL, USER_NAME, ANVI_PASSWORD);

            statement = connection.createStatement();
            System.out.println("Connection Opened");
        }catch(SQLException e){            
            JOptionPane.showMessageDialog(null, "Connection is not Opened ! " + e.getMessage());      
        }
    }
    /*  Selecting Query */
    public static ResultSet selectQuery(String query) {
        try{
            connection();            
            return statement.executeQuery(query);
        }catch(SQLException e){
            e.getMessage();
            return null;
            
        }
    }

    /*  Executing Query */
    public static boolean query(String query) {
        try{
            connection();
            return statement.execute(query);
        }catch(SQLException e){
            e.getMessage();
            throw new IllegalArgumentException();
        }
    }
    /* Prepares the data first then execute it */
     public static PreparedStatement getPreStatement(String query)
     {
       try {
           return statement.getConnection().prepareStatement(query);
         } catch (SQLException e) {}
       return null;
     }
    
    
    
}
