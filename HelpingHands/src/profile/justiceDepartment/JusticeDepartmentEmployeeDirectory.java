/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package profile.justiceDepartment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import utilities.DbConnection;

/**
 *
 * @author Shreya Sharma
 */
public class JusticeDepartmentEmployeeDirectory {
    
    private JusticeDepartmentEmployee justiceDepartmentEmployee;
    
    public JusticeDepartmentEmployeeDirectory(JusticeDepartmentEmployee justiceDepartmentEmployee) {
        this.justiceDepartmentEmployee = justiceDepartmentEmployee;
    }
    
    public void addEmployeeToDb() throws SQLException {
        int status = 1;
        String sql = "INSERT INTO `justicedepartmentemployee`(`First_Name`, `Last_Name`,`Email_id`,`Password`,`Type`,`Country`,`Status`) "
                + "VALUES ('" + justiceDepartmentEmployee.getFirstName()+ "','" + justiceDepartmentEmployee.getLastName()+ "','" + justiceDepartmentEmployee.getEmail()+ "','" + justiceDepartmentEmployee.getPassword()+ "','" + justiceDepartmentEmployee.getEmpType()+ "','" + justiceDepartmentEmployee.getCountry()+ "','" + status + "')";
        boolean rs = DbConnection.query(sql);
        System.out.println("rs " + rs );
    }
    
    public ArrayList<JusticeDepartmentEmployee> fetchJusticeDeptEmpData(int status, String country, String userId) throws SQLException {
        ArrayList<JusticeDepartmentEmployee> justiceDeptEmpList = new ArrayList<JusticeDepartmentEmployee>();
        String query = "select * from justicedepartmentemployee where status ='" + status + "'";
        if (!userId.equals("SYS-ADMIN")) {
        }
        System.out.println("query " + query);
        ResultSet resultSet = DbConnection.selectQuery(query);
        while (resultSet.next()) {
            String id = resultSet.getString("j_emp_id");
            String firstName = resultSet.getString("First_Name");
            String lastName = resultSet.getString("Last_Name");
            String emailId = resultSet.getString("Email_id");
            String empType = resultSet.getString("Type");
            String password = resultSet.getString("Password");
            String dbCountry = resultSet.getString("Country");
            String dbStatus = resultSet.getString("Status");
            JusticeDepartmentEmployee justiceDepartmentEmployee = new JusticeDepartmentEmployee(firstName, lastName, emailId, password, empType, dbCountry);            
            boolean empStatus = dbStatus.equals("0") ? false : true;
            justiceDepartmentEmployee.setStatus(empStatus);
//            justiceDepartmentEmployee.setId(status);
           justiceDeptEmpList.add(justiceDepartmentEmployee);
            
        }
        return justiceDeptEmpList;
    }
    
}
