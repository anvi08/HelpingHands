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
import profile.donor.Donor;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.table.DefaultTableModel;
import model.causes.Cause;
import model.causes.CauseDirectory;
import model.causeticket.CauseTicket;
import model.causeticket.CauseTicketDirectory;
import model.justiceTicket.JusticeTicket;
import model.justiceTicket.JusticeTicketDirectory;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import profile.Receiver.Receiver;
import profile.Receiver.ReceiverDirectory;
import profile.donor.DonorDirectory;
import profile.justiceDepartment.JusticeDepartmentEmployee;
import profile.justiceDepartment.JusticeDepartmentEmployeeDirectory;
import twofa.EmailNotification;
import uiDonor.DonorTrackCause;
import utilities.Constants;

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
    CauseTicket justiceCauseTicket;
    CauseTicketDirectory causeTicketDirectory;
    CauseTicket causeTicket;
    int receiverCheck = 0;
    public ReceiverTrackCause(int receiverID) throws SQLException {
        initComponents();
        this.receiverID = receiverID;
        this.causeDirectory = new CauseDirectory(cause);
        this.receiverDirectory = new ReceiverDirectory(receiver);
        this.causeTicketDirectory = new CauseTicketDirectory(causeTicket);
        popReceiverTable(receiverDirectory.trackCause(receiverID));
        jProgressBar1.setVisible(false);
        panelJudiciary1.setVisible(false);
        btnTrackJusticeTkt.setVisible(false);
        btnRedFlag.setVisible(false);
        btnOrangeFlag.setVisible(false);        
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
        lblStatus = new javax.swing.JLabel();
        lblCreateDate = new javax.swing.JLabel();
        lblUpdateDate = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        btnRedFlag = new javax.swing.JButton();
        txtnew = new javax.swing.JLabel();
        btnReceived = new javax.swing.JButton();
        btnOrangeFlag = new javax.swing.JButton();
        btnTrackJusticeTkt = new javax.swing.JButton();

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

        jLabel5.setText("Status");

        jLabel6.setText("Created On: ");

        jLabel7.setText("Updated On:");

        lblStatus.setText("jLabel1");

        lblCreateDate.setText("jLabel2");

        lblUpdateDate.setText("jLabel3");

        jLabel4.setText("Email:");

        lblEmail.setText("jLabel8");

        javax.swing.GroupLayout panelJudiciary1Layout = new javax.swing.GroupLayout(panelJudiciary1);
        panelJudiciary1.setLayout(panelJudiciary1Layout);
        panelJudiciary1Layout.setHorizontalGroup(
            panelJudiciary1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelJudiciary1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(panelJudiciary1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelJudiciary1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCreateDate, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                    .addComponent(lblUpdateDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        panelJudiciary1Layout.setVerticalGroup(
            panelJudiciary1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelJudiciary1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(panelJudiciary1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(lblStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelJudiciary1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(lblCreateDate))
                .addGap(18, 18, 18)
                .addGroup(panelJudiciary1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(lblUpdateDate))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelJudiciary1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(lblEmail))
                .addContainerGap(25, Short.MAX_VALUE))
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

        btnRedFlag.setText("Raise Red Flag");
        btnRedFlag.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRedFlagActionPerformed(evt);
            }
        });

        btnReceived.setText("Funds Received");
        btnReceived.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReceivedActionPerformed(evt);
            }
        });

        btnOrangeFlag.setText("Raise Orange Flag");
        btnOrangeFlag.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOrangeFlagActionPerformed(evt);
            }
        });

        btnTrackJusticeTkt.setText("Track Justice Ticket");
        btnTrackJusticeTkt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTrackJusticeTktActionPerformed(evt);
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
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnRedFlag)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnOrangeFlag, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnTrackJusticeTkt, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)))))
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
                            .addComponent(btnRedFlag)
                            .addComponent(btnReceived)
                            .addComponent(btnOrangeFlag)
                            .addComponent(btnTrackJusticeTkt)))
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
        panelJudiciary1.setVisible(false);
        if(SelectedRow<0){
            JOptionPane.showMessageDialog(this, "Please Select a row");
        }else{
            DefaultTableModel m2 = (DefaultTableModel)tblCause.getModel();
            Cause SelectedRecords = (Cause) m2.getValueAt(SelectedRow, 0);
            System.out.println("CAUSE IDDDDD "+SelectedRecords.getCauseId());
            JOptionPane.showMessageDialog(this,"The Exact location of your funds is given below");
            try {
                ArrayList<CauseTicket> trackCauses = causeTicketDirectory.trackCauseReceiver(SelectedRecords.getCauseId());
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
                    if(causetix.getCauseId()== SelectedRecords.getCauseId()){
                        Date createdDate = causetix.getCreatedDate();
                        System.out.println(createdDate+"YOOOOOOOOOOOOOOOO");
                        checkForJusticeDeptTickets(causetix);
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
//                            btnReceived.setVisible((true));
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
                        if((moneyReceived==null) && moneyReceiverCountry!=null){
                            btnReceived.setVisible((true));
                        }else{
                            btnReceived.setVisible((false));
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

    
    
        
    private void checkForJusticeDeptTickets( CauseTicket causetix) {
        if (causetix != null) {
            JusticeTicket  jtkt = checkIfJusticeTicketExist(causetix);
            if (jtkt != null) {
                 btnTrackJusticeTkt.setVisible(true);
                 btnRedFlag.setVisible(false);
                 btnOrangeFlag.setVisible(false);
                  justiceCauseTicket = causetix;
            } else {
              if (causetix.getMoneyDonorCountry()== null) { 
              Date today = new Date();
             Date createdDate = causetix.getCreatedDate();
             long timeDiff = Math.abs(today.getTime() - createdDate.getTime());
             long daysDiff = TimeUnit.DAYS.convert(timeDiff, TimeUnit.MILLISECONDS);
             if (daysDiff > 10 || daysDiff == 10) {
                 btnTrackJusticeTkt.setVisible(false);
                 btnRedFlag.setVisible(false);
                 btnOrangeFlag.setVisible(true);
                 justiceCauseTicket = causetix;
             }
              } else if (causetix.getMoneyReceiverCountry()== null) {
              Date today = new Date();
             Date createdDate = causetix.getCreatedDate();
             long timeDiff = Math.abs(today.getTime() - createdDate.getTime());
             long daysDiff = TimeUnit.DAYS.convert(timeDiff, TimeUnit.MILLISECONDS);
             if (daysDiff > 10 || daysDiff == 10) {
                 btnTrackJusticeTkt.setVisible(false);
                 btnRedFlag.setVisible(true);
                 btnOrangeFlag.setVisible(false);
                 justiceCauseTicket = causetix;
             }
              } else {
                 btnTrackJusticeTkt.setVisible(false);
                 btnRedFlag.setVisible(false);
                 btnOrangeFlag.setVisible(false);
              }


                }
        }
    }
    
        
    private JusticeTicket checkIfJusticeTicketExist(CauseTicket causetix) {
        JusticeTicket  jtkt = null;
        JusticeTicketDirectory jTktDirectory = new JusticeTicketDirectory(null);
        try {
             jtkt = jTktDirectory.fetchJusticeTicketByCauseId(causetix.getTktId());
        } catch (Exception e) {
            System.out.println(e);
        }
        return jtkt;
                
    }
    
    
    
    
    private void btnRedFlagActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRedFlagActionPerformed
        // TODO add your handling code here:
                if (justiceCauseTicket != null) {
            int causeTktId = justiceCauseTicket.getTktId();
            String country = justiceCauseTicket.getReceivingCountry();
         if ((causeTktId == 0 || causeTktId > 0) && country != null && !country.trim().equals("")) {
             Date date = new Date();
             JusticeTicket jTicket = new JusticeTicket(causeTktId, date, Constants.justiceTicketStatus.get("new"), country, date);
             JusticeTicketDirectory jtktDirectory = new JusticeTicketDirectory(jTicket); 
             try {
                jtktDirectory.createJusticeTicket();
                JOptionPane.showMessageDialog(this, "Justice ticket has been created");
                setJusticeTicketData();
                btnOrangeFlag.setVisible(false);
                btnRedFlag.setVisible(false);
                btnTrackJusticeTkt.setVisible(true);
             } catch (Exception e) {
                 System.out.println(e);
             }
             
         }
        }
    }//GEN-LAST:event_btnRedFlagActionPerformed

    private void btnReceivedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReceivedActionPerformed
        try {
            // TODO add your handling code here:
            int SelectedRow = tblCause.getSelectedRow();            
            DefaultTableModel m2 = (DefaultTableModel)tblCause.getModel();
            Cause SelectedRecords = (Cause) m2.getValueAt(SelectedRow, 0);            
            ArrayList<CauseTicket> trackCauses = causeTicketDirectory.trackCauseReceiver(SelectedRecords.getCauseId());
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

    private void btnTrackJusticeTktActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTrackJusticeTktActionPerformed
        // TODO add your handling code here:
        setJusticeTicketData();
    }//GEN-LAST:event_btnTrackJusticeTktActionPerformed

    private void btnOrangeFlagActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrangeFlagActionPerformed
        // TODO add your handling code here:
        if (justiceCauseTicket != null) {
            Donor donor = fetchDonorData();
            if (donor != null) {
                EmailNotification.SendEmail("abcd", donor.getEmail());
            }
        }
    }//GEN-LAST:event_btnOrangeFlagActionPerformed
        private void setJusticeTicketData() {
        if (justiceCauseTicket != null) {
            JusticeTicket jtkt  = checkIfJusticeTicketExist(justiceCauseTicket);
            lblStatus.setText(jtkt.getjTktStatus());
             DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); 
            if (jtkt.getCreatedDate() != null) {
                lblCreateDate.setText(formatter.format(jtkt.getCreatedDate()));
                 } else {
                     lblCreateDate.setText("NA");
                 }
                 if (jtkt.getUpdatedDate() != null) {
                        lblUpdateDate.setText(formatter.format(jtkt.getUpdatedDate()));
                 } else {
                     lblUpdateDate.setText("NA");
                 } 
            setFontColorForJusticeStatus(jtkt.getjTktStatus());
            if (jtkt != null && !jtkt.getjTktStatus().trim().equals("NEW")) {
                JusticeDepartmentEmployeeDirectory jdEmpDirectory = new JusticeDepartmentEmployeeDirectory(null);
                JusticeDepartmentEmployee jDemp = null;
                try {
                    int jEmpId = jtkt.getjEmpId();
                    if (jEmpId > 0) {
                        jDemp = jdEmpDirectory.fetchEmployeeById(jEmpId); 
                        if (jDemp != null) {
                            lblEmail.setText(jDemp.getEmail());
                            lblEmail.setVisible(true);
                        }
                    }
                   
                } catch (Exception e) {
                    System.out.println(e);
                }
            } else {
                 lblEmail.setText("NA");
            }
            panelJudiciary1.setVisible(true);
        }
    }
        
        private Donor fetchDonorData() {
            Donor donor = null;
            if (justiceCauseTicket != null && justiceCauseTicket.getDonorId() > 0) {
                try {
                    DonorDirectory donorDirectory = new DonorDirectory(null); 
                    donor = donorDirectory.fetchDonorById(justiceCauseTicket.getDonorId());
                } catch (Exception e) {
                    System.out.println(e);
                }
                
            }
            return donor;
        }
    
            
           private void setFontColorForJusticeStatus(String status) {
            switch (status) {
                case "NEW":
                    Color newBlue = new Color(102, 255,255);
                    lblStatus.setForeground(newBlue);
                    break;
                case "ASSIGNED":
                    Color newOrange = new Color(255, 153,51);
                    lblStatus.setForeground(newOrange);
                    break;
                case "WIP":
                    Color  newLeafColor= new Color(255, 153,0);
                    lblStatus.setForeground(newLeafColor);
                    break;
                case "RESOLVED":
                    Color  success= new Color(51, 153,0);
                    lblStatus.setForeground(success);
                    break;
                default:
                    throw new AssertionError();
            
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnOrangeFlag;
    private javax.swing.JButton btnReceived;
    private javax.swing.JButton btnRedFlag;
    private javax.swing.JButton btnTrack;
    private javax.swing.JButton btnTrackJusticeTkt;
    private javax.swing.JPanel chartPanel;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCreateDate;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JLabel lblUpdateDate;
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
