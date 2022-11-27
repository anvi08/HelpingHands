/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author abhis
 */
import java.sql.*;
public class DatabaseConn {

    public static void main(String args[]){  
    try{  
        Class.forName("com.mysql.cj.jdbc.Driver");  
        Connection con = DriverManager.getConnection(  
        "jdbc:mysql://localhost:3306/financialaiddb","root","@Fd2556b9dd1997");  
        //here sonoo is database name, root is username and password  
        Statement stmt=con.createStatement();  
        ResultSet rs=stmt.executeQuery("Select * from donortable;");  
        while(rs.next())  
        System.out.println(rs.getString(1)+"  "+rs.getString(2)+"  "+rs.getString(3));  
        con.close();  
    }
        catch(Exception e){ System.out.println(e);}  
    }  

    
}
