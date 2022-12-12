/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.causeBankTrack;

import java.util.ArrayList;
import utilities.DbConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import profile.bank.BankPerson;
/**
 *
 * @author HP
 */
public class BankTicketDirectory {
    
    private BankTicket bankTicket;
    public ArrayList<BankTicket> bankTicketList;

    public BankTicketDirectory(BankTicket bankTicket) {
        this.bankTicket = bankTicket;
        this.bankTicketList = new ArrayList<BankTicket>();
    }

    public ArrayList<BankTicket> fetchBankTicket() throws SQLException{
        ArrayList<BankTicket> allBankTickets = new ArrayList();
        String query = "Select * from bankempticket;";
        ResultSet resultset = DbConnection.selectQuery(query);
        while(resultset.next()){
                int bankTkId = resultset.getInt("Bank_Tk_Id");
                int causeTkId = resultset.getInt("Cause_Tk_Id");
                int bankEmpId = resultset.getInt("Employee_Id");
                Date assignedDate = resultset.getDate("Assigned_Date");
                
                BankTicket newBankTicket = new BankTicket(bankTkId, causeTkId, bankEmpId, assignedDate);
                allBankTickets.add(newBankTicket);
            }
        
        
        return allBankTickets;
    }
   
    public ArrayList<BankTicket> fetchEmployeeBankTicket(int bk_person_id) throws SQLException{
        ArrayList<BankTicket> empBankTickets = new ArrayList();
        String query = "Select * from bankempticket where Employee_Id="+bk_person_id+";";
        
        ResultSet resultset = DbConnection.selectQuery(query);
        while(resultset.next()){
                int bankTkId = resultset.getInt("Bank_Tk_Id");
                int causeTkId = resultset.getInt("Cause_Tk_Id");
                int bankEmpId = resultset.getInt("Employee_Id");
                Date assignedDate = resultset.getDate("Assigned_Date");
                
                BankTicket newBankTicket = new BankTicket(bankTkId, causeTkId, bankEmpId, assignedDate);
                empBankTickets.add(newBankTicket);
            }
        
        
        return empBankTickets;
    }
    
    public void updateBankEmptktTable(BankPerson bp, int bank_tk_num){
        //String updateQuery = "Update financialaiddb.bankemployee set First_Name ='"+ bp.getFirstName() +"' ,Last_Name ='"+ bp.getLastName()+"',Country = '"+bp.getCountry()+"', Type = '"+bp.getEmpType() +"' where Email_id = '"+bp.getEmail()+"'; ";
        String updateQuery = "Update financialaiddb.bankempticket set Employee_Id = '"+bp.getBankPersonId()+"' where Bank_Tk_Id='"+bank_tk_num+"';";
        
        DbConnection.query(updateQuery);
    }
    
    
    
}
