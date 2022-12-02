/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.causes;

//import com.mysql.cj.xdevapi.Statement;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import utilities.DbConnection;

/**
 *
 * @author abhis
 */
public class CauseDirectory {
    private Cause cause;

    
    public CauseDirectory(Cause cause){
        
        this.cause = cause;
        
    }

<<<<<<< HEAD
    public void addCause(){
        String sql = "INSERT INTO `cause`(`NGO_Org`, `Cause_Name`,`Cause_Desc`,`R_Category`,`Country`,`Status`) "
                + "VALUES ('" + cause.getNgoOrg() + "','" + cause.getCauseName() + "','" + cause.getCauseDesc() + "','" + cause.getRecCategory() + "','" + cause.getCountry() + "','" + '1' + "')";//        fetch();
        DbConnection.query(sql);
=======
    public void addCause() throws SQLException{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");  
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/financialaiddb","root","root");  
            String sql = "INSERT INTO `cause`(`NGO_Org`, `Cause_Name`,`Cause_Desc`,`R_Category`,`Country`,`Status`) "
                    + "VALUES ('" + cause.getNgoOrg() + "','" + cause.getCauseName() + "','" + cause.getCauseDesc() + "','" + cause.getRecCategory() + "','" + cause.getCountry() + "','" + '1' + "')";
            st = con.createStatement();
            st.execute(sql);
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
        }
//        fetch();
>>>>>>> AnviJain/NUID-002657150
    }    
    
}
