/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package profile.justiceDepartment;

import utilities.DbConnection;

/**
 *
 * @author Khalesi
 */
public class JusticeDepartmentEmployeeDirectory {
    
    private JusticeDepartmentEmployee justiceDepartmentEmployee;
    
    public JusticeDepartmentEmployeeDirectory(JusticeDepartmentEmployee justiceDepartmentEmployee) {
        this.justiceDepartmentEmployee = justiceDepartmentEmployee;
    }
    
    public void addEmployeeToDb() {
        int status = 1;
        String sql = "INSERT INTO `justicedepartmentemployee`(`First_Name`, `Last_Name`,`Email_id`,`Password`,`Type`,`Country`,`Status`) "
                + "VALUES ('" + justiceDepartmentEmployee.getFirstName()+ "','" + justiceDepartmentEmployee.getLastName()+ "','" + justiceDepartmentEmployee.getEmail()+ "','" + justiceDepartmentEmployee.getPassword()+ "','" + justiceDepartmentEmployee.getEmpType()+ "','" + justiceDepartmentEmployee.getCountry()+ "','" + status + "')";
        DbConnection.query(sql);
    }
    
}
