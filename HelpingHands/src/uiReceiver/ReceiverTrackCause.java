/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package uiReceiver;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.table.DefaultTableModel;
import model.causes.Cause;
import model.causes.CauseDirectory;
import model.causeticket.CauseTicket;
import model.causeticket.CauseTicketDirectory;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import profile.Receiver.Receiver;
import profile.Receiver.ReceiverDirectory;
import uiDonor.DonorTrackCause;

/**
 *
 * @author abhis
 */
public class ReceiverTrackCause extends javax.swing.JPanel {

    /**
     * Creates new form ReceiverTrackCause
     */  
    CauseDirectory causeDirectory;
    Cause cause;    
    private String loggedInUser;    
    ReceiverDirectory receiverDirectory;
    Receiver receiver;    
    private int receiverID;
    CauseTicketDirectory causeTicketDirectory;
    CauseTicket causeTicket;     
    public ReceiverTrackCause(int receiverID) throws SQLException {
        initComponents();
        this.receiverID = receiverID;
        this.causeDirectory = new CauseDirectory(cause);
        this.receiverDirectory = new ReceiverDirectory(receiver);
        this.causeTicketDirectory = new CauseTicketDirectory(causeTicket);
        popReceiverTable(receiverDirectory.trackCause(receiverID));
        jProgressBar1.setVisible(false);
        panelJudiciary1.setVisible(false);        
        btnReceived.setVisible(false);        
    }
    private void popReceiverTable(ArrayList<Cause> receiverTable) throws SQLException {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        DefaultTableModel model = (DefaultTableModel)tblCause.getModel();
        model.setRowCount(0);
        for(Cause cause: receiverTable){
            Object[] row = new Object[6];
            row[0] = cause;
            row[1] = cause.getCauseName();
            row[2] = cause.getCauseDesc();
            row[3] = cause.getRecCategory();
            row[4] = cause.getCountry();                                     
            model.addRow(row);
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblCause = new javax.swing.JTable();
        btnTrack = new javax.swing.JButton();
        panelTracker = new javax.swing.JPanel();
        chartPanel = new javax.swing.JPanel();
        txtCreated = new javax.swing.JLabel();
        txtCreated1 = new javax.swing.JLabel();
        txtCreated2 = new javax.swing.JLabel();
        txtCreated3 = new javax.swing.JLabel();
        txtDate1 = new javax.swing.JLabel();
        txtDate2 = new javax.swing.JLabel();
        txtDate3 = new javax.swing.JLabel();
        txtDate4 = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();
        panelJudiciary1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btnHelp2 = new javax.swing.JButton();
        txtnew = new javax.swing.JLabel();
        btnReceived = new javax.swing.JButton();

        tblCause.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "NGO Organisation", "Cause Name", "Cause Description", "Receiving Category", "Country"
            }
        ));
        jScrollPane1.setViewportView(tblCause);

        btnTrack.setText("Track Funds");
        btnTrack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTrackActionPerformed(evt);
            }
        });

        chartPanel.setLayout(new javax.swing.BoxLayout(chartPanel, javax.swing.BoxLayout.LINE_AXIS));

        txtCreated.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        txtCreated1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        txtCreated2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        txtCreated3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        panelJudiciary1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setText("jLabel1");

        jLabel6.setText("jLabel2");

        jLabel7.setText("jLabel3");

        javax.swing.GroupLayout panelJudiciary1Layout = new javax.swing.GroupLayout(panelJudiciary1);
        panelJudiciary1.setLayout(panelJudiciary1Layout);
        panelJudiciary1Layout.setHorizontalGroup(
            panelJudiciary1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelJudiciary1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(panelJudiciary1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5))
                .addContainerGap(125, Short.MAX_VALUE))
        );
        panelJudiciary1Layout.setVerticalGroup(
            panelJudiciary1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelJudiciary1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addContainerGap(47, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelTrackerLayout = new javax.swing.GroupLayout(panelTracker);
        panelTracker.setLayout(panelTrackerLayout);
        panelTrackerLayout.setHorizontalGroup(
            panelTrackerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTrackerLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelTrackerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCreated1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCreated, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCreated2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCreated3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(panelTrackerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDate1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDate2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDate4, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDate3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(59, 59, 59)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(panelTrackerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTrackerLayout.createSequentialGroup()
                        .addGap(430, 430, 430)
                        .addComponent(chartPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelTrackerLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(panelJudiciary1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(1815, 1815, 1815))
        );

        panelTrackerLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txtCreated, txtCreated1, txtCreated2, txtCreated3});

        panelTrackerLayout.setVerticalGroup(
            panelTrackerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTrackerLayout.createSequentialGroup()
                .addComponent(chartPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelJudiciary1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(153, 153, 153))
            .addGroup(panelTrackerLayout.createSequentialGroup()
                .addGroup(panelTrackerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelTrackerLayout.createSequentialGroup()
                        .addGroup(panelTrackerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCreated, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDate1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelTrackerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDate2, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCreated1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelTrackerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCreated2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDate3, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelTrackerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCreated3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDate4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelTrackerLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {txtCreated, txtCreated1, txtCreated2, txtCreated3});

        btnHelp2.setText("Help");
        btnHelp2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHelp2ActionPerformed(evt);
            }
        });

        btnReceived.setText("Funds Received");
        btnReceived.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReceivedActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelTracker, javax.swing.GroupLayout.PREFERRED_SIZE, 985, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 938, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnTrack)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(451, 451, 451)
                                .addComponent(txtnew, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(btnReceived)
                                .addGap(25, 25, 25)
                                .addComponent(btnHelp2)))))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(83, 83, 83)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnTrack)
                            .addComponent(btnHelp2)
                            .addComponent(btnReceived)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                        .addComponent(txtnew)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelTracker, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnTrackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTrackActionPerformed
        // TODO add your handling code here:
        int SelectedRow = tblCause.getSelectedRow();
        if(SelectedRow<0){
            JOptionPane.showMessageDialog(this, "Please Select a row");
        }else{
            DefaultTableModel m2 = (DefaultTableModel)tblCause.getModel();
            Cause SelectedRecords = (Cause) m2.getValueAt(SelectedRow, 0);
            System.out.println("CAUSE IDDDDD "+SelectedRecords.getCauseId());
            JOptionPane.showMessageDialog(this,"The Exact location of your funds is given below");
            try {
                ArrayList<CauseTicket> trackCauses = causeTicketDirectory.trackCauseReceiver(receiverID);
                panelTracker.setVisible(true);
                jProgressBar1.setVisible(true);
                int MY_MINIMUM = 0;
                int MY_MAXIMUM = 100;                
                jProgressBar1.setMinimum(MY_MINIMUM);
                jProgressBar1.setMaximum(MY_MAXIMUM);
                jProgressBar1.setValue(100);
                jProgressBar1.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
                jProgressBar1.setOrientation(JProgressBar.VERTICAL);               
                if(trackCauses.isEmpty()){
                    System.out.println("YEESSSSSSSSSS");
//                    JOptionPane.showMessageDialog(this, "<html>Your Cause is awaiting a new Donor Tracking will be updated soon</html>");
                    panelTracker.setVisible(true);
                    jProgressBar1.setVisible(true);
                    jProgressBar1.setMinimum(MY_MINIMUM);
                    jProgressBar1.setMaximum(MY_MAXIMUM);
                    jProgressBar1.setValue(5);
                    jProgressBar1.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
                    jProgressBar1.setOrientation(JProgressBar.VERTICAL);
                    jProgressBar1.setString("Your funds are almost there");
                    txtCreated3.setText("Your Cause is awaiting new Donor");
                    return;
                }                
                for(CauseTicket causetix : trackCauses){
                    if(causetix.getReceiverId()==receiverID){
                        Date createdDate = causetix.getCreatedDate();
                        System.out.println(createdDate+"YOOOOOOOOOOOOOOOO");
                   
                        Date moneyDonorCountry = causetix.getMoneyDonorCountry();
                        Date moneyReceiverCountry = causetix.getMoneyReceiverCountry();
                        Date moneyReceived = causetix.getMoneyReceived();
                        System.out.println(moneyDonorCountry+"YOOOOOOOOOOOOOOOO");                             

                        int check = 0;
//                        jPanel1.setVisible(true);
                        chartPanel.setVisible(true);
                        jProgressBar1.setVisible(true);
                        jProgressBar1.setMinimum(MY_MINIMUM);
                        jProgressBar1.setMaximum(MY_MAXIMUM);
                        jProgressBar1.setValue(100);
                        jProgressBar1.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
                        jProgressBar1.setOrientation(JProgressBar.VERTICAL);
                        
                        if(moneyReceived!=null){
                            check+=1;
//                            jProgressBar1.setString("Your funds are almost there");
                            txtCreated.setText("<html>Your Funds have reached the right people on \n Thank you for using HelpingHands</html>");
                            txtDate1.setText(moneyReceived.toString());          
                            
                        }                               
                        if(moneyReceiverCountry!=null){
                            btnReceived.setVisible((true));
                            check+=1;
//                            jProgressBar1.setString("Your funds are almost there");
                            txtCreated1.setText("<html>Your Funds were processed by the bank in \n "+ causetix.getReceivingCountry() +" and will be in the hands of the benificiary soon</html>");
                            txtDate2.setText(moneyReceiverCountry.toString());                            
                       
                        }                                
                        if(moneyDonorCountry!=null){
                            check+=1;
//                            jProgressBar1.setString("Your funds are almost there");
                            txtCreated2.setText("<html>Your Funds were Processed in \n "+causetix.getDonorCountry()+" and will be tracked by the Receiving Country Bank authorities soon</html>");
                            txtDate3.setText(moneyDonorCountry.toString());
                            
                        }    
                        if(createdDate!=null){
                            check+=1;                            
//                            jProgressBar1.setString("funds almost there");
                            txtCreated3.setText("<html> Thank you for using HelpingHands, Your Funds were Initiated on</html>");
                            txtDate4.setText( createdDate.toString());
                            final String html = "<html><body style='width: %1spx'>%1s";
               
                        }
                    
                        if(check==1){
                            jProgressBar1.setValue(25);
                            return;
                        }
                        if(check==2){
                            jProgressBar1.setValue(50);                            
                            return;
                        }
                        if(check==3){
                            jProgressBar1.setValue(75);                            
                            return;
                        }
                        if(check==4){
                            jProgressBar1.setValue(100);                            
                            return;
                        }

                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(DonorTrackCause.class.getName()).log(Level.SEVERE, null, ex);
            }
        }               
        
    }//GEN-LAST:event_btnTrackActionPerformed

    private void btnHelp2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHelp2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnHelp2ActionPerformed

    private void btnReceivedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReceivedActionPerformed
        try {
            // TODO add your handling code here:
            int SelectedRow = tblCause.getSelectedRow();            
            DefaultTableModel m2 = (DefaultTableModel)tblCause.getModel();
            Cause SelectedRecords = (Cause) m2.getValueAt(SelectedRow, 0);            
            ArrayList<CauseTicket> trackCauses = causeTicketDirectory.trackCauseReceiver(receiverID);
            for(CauseTicket causetix : trackCauses){
                if(causetix.getMoneyReceiverCountry()!=null && Integer.valueOf(causetix.getCauseId())==Integer.valueOf(SelectedRecords.getCauseId())){
                    int dId = Integer.valueOf(causetix.getCauseId());
                    int rId = Integer.valueOf(causetix.getReceiverId());
                    causeTicketDirectory.moneyReceived(dId,rId);
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Calendar cal = Calendar.getInstance();
                    String cd = dateFormat.format(cal.getTime());                    
                    JOptionPane.showMessageDialog(this,"Thank you for using Helping hands");
//                            jProgressBar1.setString("Your funds are almost there");
                    txtCreated.setText("<html>Your Funds have reached the right people on \n Thank you for using HelpingHands</html>");
                    txtDate1.setText(cd.toString()); 
                    jProgressBar1.setValue(100); 
                    jProgressBar1.setForeground(Color.GREEN);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReceiverTrackCause.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ReceiverTrackCause.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnReceivedActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHelp2;
    private javax.swing.JButton btnReceived;
    private javax.swing.JButton btnTrack;
    private javax.swing.JPanel chartPanel;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelJudiciary1;
    private javax.swing.JPanel panelTracker;
    private javax.swing.JTable tblCause;
    private javax.swing.JLabel txtCreated;
    private javax.swing.JLabel txtCreated1;
    private javax.swing.JLabel txtCreated2;
    private javax.swing.JLabel txtCreated3;
    private javax.swing.JLabel txtDate1;
    private javax.swing.JLabel txtDate2;
    private javax.swing.JLabel txtDate3;
    private javax.swing.JLabel txtDate4;
    private javax.swing.JLabel txtnew;
    // End of variables declaration//GEN-END:variables
}
