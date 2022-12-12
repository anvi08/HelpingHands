/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.causeticket;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import model.causes.Cause;
import utilities.DbConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Calendar;
import model.causeBankTrack.BankEmployeeTicket;
import static utilities.DbConnection.query;

/**
 *
 * @author abhis
 */
public class CauseTicketDirectory {
    private CauseTicket causeTicket;
    public ArrayList<CauseTicket> allCauseTicket;       
    private String moneyDonorCountry1;
    private String moneyReceived1;
    private String moneyReceiverCountry1;
    private BankEmployeeTicket bankEmployeeTicket; 
    
    public CauseTicketDirectory(CauseTicket causeTicket){
        
        this.causeTicket = causeTicket;
        // this.allCauses = allCauses;
        
    }  

    public CauseTicketDirectory() {
    }
    

    public void addTicket() throws SQLException{
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); 
        String createdDate = formatter.format(causeTicket.getCreatedDate());

        if(causeTicket.getMoneyDonorCountry()==null){
            String moneyDonorCountry1 = "NULL";
            System.out.println("DATE IS NULLLLLLLLLL "+ moneyDonorCountry1);

        }
        if(causeTicket.getMoneyReceiverCountry()==null){
            String moneyReceiverCountry1 = "NULL";
           
        }
        if(causeTicket.getMoneyReceived()==null){
            String moneyReceived1 = "NULL";
        
        }
        
//        String sql = "INSERT INTO `causeticket`(`Donor_Id`, `Receiver_Id`,`Cause_Id`,`Created_Date`,`Money_Donor_Country`,`Money_Receiving_Country`,`Money_Received`,`Donor_Country`,`Receiving_Country`,`Amount`)"
//                + "VALUES ('" + causeTicket.getDonorId() + "','" + causeTicket.getReceiverId() + "','" + causeTicket.getCauseId() + "','" + createdDate  + "','" + createdDate + "','" + createdDate + "','" + createdDate + "','" + causeTicket.getDonorCountry() + "','" + causeTicket.getReceivingCountry() + "','" + causeTicket.getAmount() + "')";//        fetch();

        String sql = "INSERT INTO `causeticket`(`Donor_Id`, `Receiver_Id`,`Cause_Id`,`Created_Date`,`Donor_Country`,`Receiving_Country`,`Amount`)"
                + "VALUES ('" + causeTicket.getDonorId() + "','" + causeTicket.getReceiverId() + "','" + causeTicket.getCauseId() + "','" + createdDate  + "','" + causeTicket.getDonorCountry() + "','" + causeTicket.getReceivingCountry() + "','" + causeTicket.getAmount() + "')";//        fetch();
        
        
        System.out.println(sql);
        DbConnection.query(sql);
        
//        PreparedStatement pstmt = DbConnection.getPreStatement(sql);
//        pstmt.setNull(6, java.sql.Types.DATE);
    }    
    
    public  ArrayList<CauseTicket> trackCause(int donorID) throws SQLException{
        ArrayList<CauseTicket> tracker = new ArrayList();
        String query = "Select * from causeticket where Donor_Id = " + donorID + "";
        ResultSet resultSet = DbConnection.selectQuery(query);    
        while(resultSet.next()){
            
            int causeTickeId = Integer.parseInt(resultSet.getString("SNo"));
            int donorId = Integer.valueOf(resultSet.getString("Donor_Id"));
            int receiverId = Integer.valueOf(resultSet.getString("Receiver_Id"));
            int causeId = Integer.valueOf(resultSet.getString("Cause_Id"));
            Date createdDate = resultSet.getDate("Created_Date");
            Date moneyDonorCountry = resultSet.getDate("Money_Donor_Country");
            Date moneyReceivingCountry = resultSet.getDate("Money_Receiving_Country");
            Date moneyReceived = resultSet.getDate("Money_Received");
            String dCountry = resultSet.getString("Donor_Country");
            String rCountry = resultSet.getString("Receiving_Country");
            int amount = Integer.valueOf(resultSet.getString("Amount"));

            
            CauseTicket causeticket = new CauseTicket(donorId,receiverId,causeId,createdDate,moneyDonorCountry,moneyReceivingCountry,moneyReceived,dCountry,rCountry,amount);
            causeticket.setTktId(causeTickeId);
            tracker.add(causeticket);    
        }
        
        return tracker;
    }

    public  ArrayList<CauseTicket> trackCauseReceiver(int causeID) throws SQLException{
        ArrayList<CauseTicket> tracker = new ArrayList();
        String query = "Select * from causeticket where Cause_Id = " + causeID + ";";
        System.out.println(query);
        ResultSet resultSet = DbConnection.selectQuery(query);    
        if (!resultSet.isBeforeFirst() ) {    
            System.out.println("No data"); 
            return tracker;
        } 
        while(resultSet.next()){

            int donorId = Integer.valueOf(resultSet.getString("Donor_Id"));
            int receiverId = Integer.valueOf(resultSet.getString("Receiver_Id"));
            int causeId = Integer.valueOf(resultSet.getString("Cause_Id"));
            Date createdDate = resultSet.getDate("Created_Date");
            Date moneyDonorCountry = resultSet.getDate("Money_Donor_Country");
            Date moneyReceivingCountry = resultSet.getDate("Money_Receiving_Country");
            Date moneyReceived = resultSet.getDate("Money_Received");
            String dCountry = resultSet.getString("Donor_Country");
            String rCountry = resultSet.getString("Receiving_Country");
            int amount = Integer.valueOf(resultSet.getString("Amount"));
            int causeTicketId = Integer.valueOf(resultSet.getString("SNo"));

            
            CauseTicket causeticket = new CauseTicket(donorId,receiverId,causeId,createdDate,moneyDonorCountry,moneyReceivingCountry,moneyReceived,dCountry,rCountry,amount);
            causeticket.setTktId(causeTicketId);
            tracker.add(causeticket);    
        }
        
        return tracker;
    }    

    public void moneyReceived(int cause_Id,int receiver_Id) throws ParseException{
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        String cd = dateFormat.format(cal.getTime());
//        Date createdDate = dateFormat.parse(cd);
//        System.out.println(createdDate);        
        String query1 = "Update financialaiddb.causeticket set Money_Received = '"+cd
                +"' where Cause_Id = "+cause_Id+" and Receiver_Id = "+receiver_Id+" ;";
        System.out.println(query1);
        DbConnection.query(query1);
    }
    
    
    public void moneyDonorCountry(int cause_tk_id) throws ParseException{
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        String cd = dateFormat.format(cal.getTime());
//        Date createdDate = dateFormat.parse(cd);
//        System.out.println(createdDate);        
        String query1 = "Update financialaiddb.causeticket set Money_Donor_Country = '"+cd
                +"' where SNo = "+cause_tk_id+";";
        System.out.println(query1);
        DbConnection.query(query1);
    }
    
    public void moneyReceiverCountry(int cause_tk_id) throws ParseException{
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        String cd = dateFormat.format(cal.getTime());
//        Date createdDate = dateFormat.parse(cd);
//        System.out.println(createdDate);        
        String query1 = "Update financialaiddb.causeticket set Money_Receiving_Country = '"+cd
                +"' where SNo = "+cause_tk_id+";";
        System.out.println(query1);
        DbConnection.query(query1);
    }

//    public void moneyReceivedCommunity(int cause_Id,int receiver_Id) throws ParseException{
//        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        Calendar cal = Calendar.getInstance();
//        String cd = dateFormat.format(cal.getTime());
////        Date createdDate = dateFormat.parse(cd);
////        System.out.println(createdDate);        
//        String query1 = "Update financialaiddb.causeticket set Money_Received = '"+cd
//                +"' where Cause_Id = "+cause_Id+" and Receiver_Id = "+receiver_Id+" and Amount;";
//        System.out.println(query1);
//        DbConnection.query(query1);
//    }    
    public void moneyDonorCountry(){
    }

    public CauseTicket fetchCauseTicketData(int causeTktId) throws SQLException {
        CauseTicket causeTicket = null;
        String query = "select * from causeticket where SNo = " + causeTktId + ";";
        ResultSet resultSet = DbConnection.selectQuery(query); 
        while (resultSet.next()) {
            int donorId = Integer.valueOf(resultSet.getString("Donor_Id"));
            int receiverId = Integer.valueOf(resultSet.getString("Receiver_Id"));
            int causeId = Integer.valueOf(resultSet.getString("Cause_Id"));
            Date createdDate = resultSet.getDate("Created_Date");
            Date moneyDonorCountry = resultSet.getDate("Money_Donor_Country"); 
            Date moneyReceiverCountry = resultSet.getDate("Money_Received"); 
            Date moneyReceived = resultSet.getDate("Money_Received");
            String donorCountry = resultSet.getString("Donor_Country"); 
            String receivingCountry = resultSet.getString("Receiving_Country");
            int amount = Integer.valueOf(resultSet.getString("Amount"));
            
            causeTicket = new CauseTicket(donorId,receiverId,causeId,createdDate,moneyDonorCountry,moneyReceiverCountry,moneyReceived,donorCountry,receivingCountry,amount);
        }
        return causeTicket;
    }
    
    public CauseTicket fetchPrimaryKey (int donroId, int causeID, int receiverId) throws SQLException {
        CauseTicket causeTkt = null;
        String query = "select * from causeticket where Donor_Id ="+ donroId + " AND Cause_Id = "+ causeID + " AND Receiver_Id = " + receiverId + ";";
        ResultSet resultSet = DbConnection.selectQuery(query); 
        while (resultSet.next()) {
            int donorId = Integer.valueOf(resultSet.getString("Donor_Id"));
            int rId = Integer.valueOf(resultSet.getString("Receiver_Id"));
            int causeId = Integer.valueOf(resultSet.getString("Cause_Id"));
            int pk = Integer.valueOf(resultSet.getString("SNo"));
            Date createdDate = resultSet.getDate("Created_Date");
            Date moneyDonorCountry = resultSet.getDate("Money_Donor_Country"); 
            Date moneyReceiverCountry = resultSet.getDate("Money_Received"); 
            Date moneyReceived = resultSet.getDate("Money_Received");
            String donorCountry = resultSet.getString("Donor_Country"); 
            String receivingCountry = resultSet.getString("Receiving_Country");
            int amount = Integer.valueOf(resultSet.getString("Amount"));
              causeTicket = new CauseTicket(donorId,receiverId,causeId,createdDate,moneyDonorCountry,moneyReceiverCountry,moneyReceived,donorCountry,receivingCountry,amount);
              causeTicket.setTktId(pk);
        }
        return causeTicket;
    }
    
 

}
