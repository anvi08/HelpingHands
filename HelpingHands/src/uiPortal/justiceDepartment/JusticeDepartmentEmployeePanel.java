/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package uiPortal.justiceDepartment;

import java.awt.Color;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.causes.Cause;
import model.causes.CauseDirectory;
import model.causeticket.CauseTicket;
import model.causeticket.CauseTicketDirectory;
import model.justiceTicket.JusticeTicket;
import model.justiceTicket.JusticeTicketDirectory;
import model.justiceTicket.JusticeTicketTrack;
import profile.Receiver.Receiver;
import profile.Receiver.ReceiverDirectory;
import profile.donor.Donor;
import profile.donor.DonorDirectory;
import profile.justiceDepartment.JusticeDepartmentEmployee;

/**
 *
 * @author Khalesi
 */
public class JusticeDepartmentEmployeePanel extends javax.swing.JPanel {
    JusticeDepartmentEmployee justiceDepartmentEmployee;
    ArrayList<JusticeTicketTrack> justiceTicketTrackList;
    JusticeTicketTrack selectedJusticeTicketTrack;
    /**
     * Creates new form JusticeDepartmentEmployeePanel
     */
    public JusticeDepartmentEmployeePanel(JusticeDepartmentEmployee justiceDepartmentEmployee) {
        initComponents();
        this.justiceDepartmentEmployee = justiceDepartmentEmployee;
        setDefaultData();
    }
    
        
    private void setDefaultData() {
        this.justiceTicketTrackList = new ArrayList<JusticeTicketTrack>();
        updateDetailsPanel.setVisible(false);
        fetchTktData();
    }
    
     private void fetchTktData() {
        JusticeTicketDirectory jTicketDirectory = new JusticeTicketDirectory(null);
        String country = null; 
        int empId = 0;
        String empProfileRole = null;
        if (justiceDepartmentEmployee != null) {
            country = justiceDepartmentEmployee.getCountry();
            empId = justiceDepartmentEmployee.getId();
            empProfileRole = justiceDepartmentEmployee.getEmpType();
        }
        try {
            ArrayList<JusticeTicket>jTicketList = new ArrayList<JusticeTicket>();
            if (justiceDepartmentEmployee != null && justiceDepartmentEmployee.getId() > 0) {
                 jTicketList = jTicketDirectory.fetchAssignedTicketsByJEmpID(justiceDepartmentEmployee.getId());
            }
            if (jTicketList != null && !jTicketList.isEmpty()) {
                    justiceTicketTrackList = new ArrayList<JusticeTicketTrack>();
            for (JusticeTicket jTkt: jTicketList) { 
               JusticeTicketTrack justiceTicketTrack  = new JusticeTicketTrack(jTkt);
                CauseTicket causeTicket = fetchCauseTicketData(jTkt.getCauseTktId());
                if (causeTicket != null) {
                    justiceTicketTrack.setCauseTicket(causeTicket);
                    Cause cause = fetchCauseData(causeTicket.getCauseId());
                    if (cause != null) {
                        justiceTicketTrack.setCause(cause);
                        Donor donor = fetchDonorData(causeTicket.getDonorId());
                        if (donor != null) {
                            justiceTicketTrack.setDonor(donor);
                        }
                        Receiver receiver = fetchReceiverData(causeTicket.getReceiverId());
                        if (receiver != null) {
                            justiceTicketTrack.setReceiver(receiver);
                        }
                    }
                }
             justiceTicketTrackList.add(justiceTicketTrack);
        }
            
         if (justiceTicketTrackList != null && !justiceTicketTrackList.isEmpty()) {
             this.populateAssignTicketTable();
         } else {
               DefaultTableModel model = (DefaultTableModel)tblJusticeTickets.getModel();
          model.setRowCount(0);
         }
            }
       
        
    }  catch(Exception e) {
            System.out.println(e);
        }
        
    }
    
    private Donor fetchDonorData(int donorId) {
        Donor donor = null;
        DonorDirectory donorDirectory = new DonorDirectory(null);
        try {
            donor = donorDirectory.fetchDonorById(donorId);
        } catch (Exception e) {
            System.out.println(e);
        }
        return donor;
    }
    
    private Receiver fetchReceiverData(int reciverId) {
        Receiver receiver = null;
        ReceiverDirectory receiverDirectory = new ReceiverDirectory(null);
        try {
            receiver = receiverDirectory.fetchReceiverById(reciverId);
        } catch (Exception e) {
            System.out.println(e);
        }
        return receiver;
    }
        
    private CauseTicket fetchCauseTicketData(int causeTicketId) {
        CauseTicket causeTicket= null;
        try {
            CauseTicketDirectory causeTicketDirectory = new CauseTicketDirectory(null);
            causeTicket = causeTicketDirectory.fetchCauseTicketData(causeTicketId);
        } catch (Exception e) {
            System.out.println(e);
        }
        return causeTicket;
    }
    
    private Cause fetchCauseData(int causeId) {
        Cause cause = null;
        try {
           CauseDirectory causeDirectory =  new CauseDirectory(null); 
           cause = causeDirectory.fetchCauseById(causeId);
        } catch (Exception e) {
            System.out.println(e);
        }
        return cause;
    }
    
    private void populateAssignTicketTable() {
          DefaultTableModel model = (DefaultTableModel)tblJusticeTickets.getModel();
          model.setRowCount(0);
          if (justiceTicketTrackList != null && !justiceTicketTrackList.isEmpty()) {
              for (JusticeTicketTrack jTktTrackList: justiceTicketTrackList) {
                 Object[] row = new Object[5];
                 row[0] = jTktTrackList;
                 row[1] = jTktTrackList.getCause().getNgoOrg();
                 if (jTktTrackList.getjTkt().getCreatedDate() != null) {
                        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); 
                        row[2] = formatter.format(jTktTrackList.getjTkt().getCreatedDate());
                 } else {
                     row[2] = "NA";
                 }
                 if (jTktTrackList.getjTkt().getUpdatedDate() != null) {
                        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); 
                        row[3] = formatter.format(jTktTrackList.getjTkt().getUpdatedDate());
                 } else {
                     row[3] = "NA";
                 } 
                 row[4] = jTktTrackList.getjTkt().getjTktStatus();
                 model.addRow(row);
              }
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

        lblAssignUnassignTkt = new javax.swing.JLabel();
        updateDetailsPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtDonorName = new javax.swing.JTextField();
        txtReceiverName = new javax.swing.JTextField();
        txtNgoOrg = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtCauseName = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtReceiverEmail = new javax.swing.JTextField();
        txtDonorEmail = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtDonorCountry = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtReceiverCountry = new javax.swing.JTextField();
        panelCardLayout = new javax.swing.JPanel();
        btnTrackCause = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        btnToggleAcceptResolve = new javax.swing.JButton();
        panelTrackDetails = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        lblJusticeUpdatedOn = new javax.swing.JLabel();
        lblJusticeCreatedon = new javax.swing.JLabel();
        lblJusticeStatus = new javax.swing.JLabel();
        lblCmnt = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtComment = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblJusticeTickets = new javax.swing.JTable();
        btnView = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        lblAssignUnassignTkt.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblAssignUnassignTkt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAssignUnassignTkt.setText("Assigned Tickets");

        updateDetailsPanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("View Details");

        jLabel2.setText("Cause:");

        jLabel3.setText("Receiver Name: ");

        jLabel4.setText("Donor Email :");

        jLabel5.setText("NGO ORG: ");

        jLabel6.setText("Donor Name:");

        jLabel7.setText("Receiver Email: ");

        jLabel8.setText("Receiver Country:");

        jLabel9.setText("Donor Country:");

        panelCardLayout.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout panelCardLayoutLayout = new javax.swing.GroupLayout(panelCardLayout);
        panelCardLayout.setLayout(panelCardLayoutLayout);
        panelCardLayoutLayout.setHorizontalGroup(
            panelCardLayoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 673, Short.MAX_VALUE)
        );
        panelCardLayoutLayout.setVerticalGroup(
            panelCardLayoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 154, Short.MAX_VALUE)
        );

        btnTrackCause.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnTrackCause.setText("TRACK CAUSE");
        btnTrackCause.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTrackCauseActionPerformed(evt);
            }
        });

        btnCancel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnCancel.setText("CANCEL");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        btnToggleAcceptResolve.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnToggleAcceptResolve.setText("ACCEPT");
        btnToggleAcceptResolve.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnToggleAcceptResolveActionPerformed(evt);
            }
        });

        panelTrackDetails.setBackground(new java.awt.Color(255, 255, 255));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setText("STATUS:");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Justice Ticket Details");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setText("This Ticket was Created  on ");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel13.setText("This Ticket was Updated  on ");

        lblJusticeStatus.setBackground(new java.awt.Color(255, 255, 255));
        lblJusticeStatus.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblJusticeStatus.setForeground(new java.awt.Color(51, 153, 0));
        lblJusticeStatus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblJusticeStatus.setText("abcd");

        lblCmnt.setText("Comments :");

        txtComment.setColumns(20);
        txtComment.setRows(5);
        jScrollPane1.setViewportView(txtComment);

        javax.swing.GroupLayout panelTrackDetailsLayout = new javax.swing.GroupLayout(panelTrackDetails);
        panelTrackDetails.setLayout(panelTrackDetailsLayout);
        panelTrackDetailsLayout.setHorizontalGroup(
            panelTrackDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelTrackDetailsLayout.createSequentialGroup()
                .addGroup(panelTrackDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTrackDetailsLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblJusticeStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelTrackDetailsLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblJusticeCreatedon, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelTrackDetailsLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblJusticeUpdatedOn, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(76, 76, 76)
                        .addComponent(lblCmnt, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(71, Short.MAX_VALUE))
        );
        panelTrackDetailsLayout.setVerticalGroup(
            panelTrackDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTrackDetailsLayout.createSequentialGroup()
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(panelTrackDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelTrackDetailsLayout.createSequentialGroup()
                        .addGroup(panelTrackDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelTrackDetailsLayout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelTrackDetailsLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblJusticeStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(panelTrackDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblCmnt, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblJusticeUpdatedOn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelTrackDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblJusticeCreatedon, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout updateDetailsPanelLayout = new javax.swing.GroupLayout(updateDetailsPanel);
        updateDetailsPanel.setLayout(updateDetailsPanelLayout);
        updateDetailsPanelLayout.setHorizontalGroup(
            updateDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(updateDetailsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(updateDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(updateDetailsPanelLayout.createSequentialGroup()
                        .addComponent(panelTrackDetails, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(panelCardLayout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(updateDetailsPanelLayout.createSequentialGroup()
                        .addGroup(updateDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(updateDetailsPanelLayout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtDonorName, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(updateDetailsPanelLayout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCauseName, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, updateDetailsPanelLayout.createSequentialGroup()
                                .addGroup(updateDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(updateDetailsPanelLayout.createSequentialGroup()
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtReceiverName, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(updateDetailsPanelLayout.createSequentialGroup()
                                        .addComponent(jLabel9)
                                        .addGap(34, 34, 34)
                                        .addComponent(txtDonorCountry, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(updateDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, updateDetailsPanelLayout.createSequentialGroup()
                                .addComponent(btnToggleAcceptResolve)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnTrackCause, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(updateDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNgoOrg, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtReceiverEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDonorEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtReceiverCountry, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(updateDetailsPanelLayout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 779, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        updateDetailsPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnCancel, btnToggleAcceptResolve, btnTrackCause});

        updateDetailsPanelLayout.setVerticalGroup(
            updateDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(updateDetailsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(updateDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCauseName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(updateDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtNgoOrg, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(updateDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDonorName, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDonorEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(updateDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtReceiverName, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtReceiverEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(updateDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(updateDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtDonorCountry, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9))
                    .addGroup(updateDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(txtReceiverCountry, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(updateDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(updateDetailsPanelLayout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(panelCardLayout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(updateDetailsPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(panelTrackDetails, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(updateDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTrackCause, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnToggleAcceptResolve))
                .addContainerGap(45, Short.MAX_VALUE))
        );

        updateDetailsPanelLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnToggleAcceptResolve, btnTrackCause});

        tblJusticeTickets.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Cause", "NGO Org", "Created Date", "Updated date", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblJusticeTickets);

        btnView.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnView.setText("VIEW");
        btnView.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnView, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addComponent(updateDetailsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 784, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 695, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane2)
                        .addComponent(lblAssignUnassignTkt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(203, 203, 203)
                .addComponent(btnView, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(updateDetailsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(475, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lblAssignUnassignTkt, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(987, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        emptyInputFields();
        emptyLabels();
        updateDetailsPanel.setVisible(false);
        selectedJusticeTicketTrack = null;
    

    }//GEN-LAST:event_btnCancelActionPerformed
    
    private void emptyLabels() {
        lblJusticeCreatedon.setText("");
        lblJusticeStatus.setText("");
        lblJusticeUpdatedOn.setText("");
        txtComment.setText("");
    }
    
    private void emptyInputFields() {
        txtCauseName.setText("");
        txtDonorCountry.setText("");
        txtDonorEmail.setText("");
        txtDonorName.setText("");
        txtNgoOrg.setText("");
        txtReceiverCountry.setText("");
        txtReceiverName.setText("");
        txtReceiverEmail.setText("");
        
    }
    private void btnViewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewActionPerformed
        // TODO add your handling code here:
              if (justiceTicketTrackList != null && !justiceTicketTrackList.isEmpty()) {
            int selectedRow = tblJusticeTickets.getSelectedRow();
            
            if (selectedRow < 0) {
                JOptionPane.showMessageDialog(this, "Select one row to view details");
                return;
            } 
            updateDetailsPanel.setVisible(true);

            selectedJusticeTicketTrack = justiceTicketTrackList.get(selectedRow);
                populateTrackDetails();
             txtCauseName.setText(selectedJusticeTicketTrack.getCause().getCauseName());
             txtNgoOrg.setText(selectedJusticeTicketTrack.getCause().getNgoOrg());
             if (selectedJusticeTicketTrack.getDonor().getFirstName() != null && !selectedJusticeTicketTrack.getDonor().getFirstName().trim().equals("")) {
                 if (selectedJusticeTicketTrack.getDonor().getLastName()!= null && !selectedJusticeTicketTrack.getDonor().getLastName().trim().equals("")) {
                     String fullName = selectedJusticeTicketTrack.getDonor().getFirstName() + " " + selectedJusticeTicketTrack.getDonor().getLastName();
                txtDonorName.setText(fullName);
             } 
                txtDonorName.setText(selectedJusticeTicketTrack.getDonor().getFirstName());
             } 
             if (selectedJusticeTicketTrack.getReceiver().getFirstName() != null && !selectedJusticeTicketTrack.getReceiver().getFirstName().trim().equals("")) {
                 if (selectedJusticeTicketTrack.getReceiver().getLastName()!= null && !selectedJusticeTicketTrack.getReceiver().getLastName().trim().equals("")) {
                 String fullName = selectedJusticeTicketTrack.getReceiver().getFirstName() + " " + selectedJusticeTicketTrack.getReceiver().getLastName();
                 txtReceiverName.setText(fullName);
             } 
                txtReceiverName.setText(selectedJusticeTicketTrack.getReceiver().getFirstName());
             } 
             
             txtDonorEmail.setText(selectedJusticeTicketTrack.getDonor().getEmail());
             txtReceiverEmail.setText(selectedJusticeTicketTrack.getReceiver().getEmail());
             txtReceiverCountry.setText(selectedJusticeTicketTrack.getCauseTicket().getReceivingCountry());
             txtDonorCountry.setText(selectedJusticeTicketTrack.getCauseTicket().getDonorCountry());
             
             setTxtFieldsDisable();
        }
    }//GEN-LAST:event_btnViewActionPerformed

        private void populateTrackDetails() {
        if (selectedJusticeTicketTrack != null) {
            setBtnToggleText();
            lblJusticeStatus.setText(selectedJusticeTicketTrack.getjTkt().getjTktStatus());
            setFontColorForJusticeStatus();
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); 
            if (selectedJusticeTicketTrack.getjTkt().getCreatedDate() != null) {
                lblJusticeCreatedon.setText(formatter.format(selectedJusticeTicketTrack.getjTkt().getCreatedDate()));
                 } else {
                     lblJusticeCreatedon.setText("NA");
                 }
                 if (selectedJusticeTicketTrack.getjTkt().getUpdatedDate() != null) {
                        lblJusticeUpdatedOn.setText(formatter.format(selectedJusticeTicketTrack.getjTkt().getUpdatedDate()));
                 } else {
                     lblJusticeUpdatedOn.setText("NA");
                 } 
        }
    }
        
    private void setTxtFieldsDisable() {
        txtCauseName.setEnabled(false);
        txtDonorCountry.setEnabled(false);
        txtDonorEmail.setEnabled(false);
        txtDonorName.setEnabled(false);
        txtNgoOrg.setEnabled(false);
        txtReceiverCountry.setEnabled(false);
        txtReceiverName.setEnabled(false);
        txtReceiverEmail.setEnabled(false);
    }
    
    private void setBtnToggleText() {
        if (selectedJusticeTicketTrack != null) {
            if (selectedJusticeTicketTrack.getjTkt().getjTktStatus().equals("ASSIGNED")) {
                btnToggleAcceptResolve.setText("ACCEPT");
                txtComment.setEnabled(false);
            } else if (selectedJusticeTicketTrack.getjTkt().getjTktStatus().equals("WIP")) {
                btnToggleAcceptResolve.setText("RESOLVE");
                txtComment.setEnabled(true);
            } else {
                btnToggleAcceptResolve.setVisible(false);
                txtComment.setEnabled(false);
            }
        }
    }
        
        
           private void setFontColorForJusticeStatus() {
        if (selectedJusticeTicketTrack != null) {
            String status = selectedJusticeTicketTrack.getjTkt().getjTktStatus();
            switch (status) {
                case "NEW":
                    Color newBlue = new Color(102, 255,255);
                    lblJusticeStatus.setForeground(newBlue);
                    break;
                case "ASSIGNED":
                    Color newOrange = new Color(255, 153,51);
                    lblJusticeStatus.setForeground(newOrange);
                    break;
                case "WIP":
                    Color  newLeafColor= new Color(255, 153,0);
                    lblJusticeStatus.setForeground(newLeafColor);
                    break;
                case "RESOLVED":
                    Color  success= new Color(51, 153,0);
                    lblJusticeStatus.setForeground(success);
                    break;
                default:
                    throw new AssertionError();
            }
        }
    }
        
    private void btnTrackCauseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTrackCauseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTrackCauseActionPerformed

    private void btnToggleAcceptResolveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnToggleAcceptResolveActionPerformed
        // TODO add your handling code here:
        if (selectedJusticeTicketTrack != null) {
          switch (selectedJusticeTicketTrack.getjTkt().getjTktStatus()) {
              case "ASSIGNED":
                  acceptTicket();
                  break;
              case "WIP":
                  if (selectedJusticeTicketTrack != null) {
                      String justiceCountry = selectedJusticeTicketTrack.getjTkt().getjCountry();
                      if (justiceCountry.trim().equals("USA") || justiceCountry.trim().equals("Canada")) {
                          if(selectedJusticeTicketTrack.getCauseTicket().getMoneyDonorCountry() == null) {
                            JOptionPane.showMessageDialog(this, "Cannot resolve ticket as Cause is not updated");
                            return;
                          } else {
                              resolveJTkt();
                          }
                      } else if (justiceCountry.trim().equals("India") || justiceCountry.trim().equals("Uganda") || 
                             justiceCountry.trim().equals("Kenya") ) {
                           if(selectedJusticeTicketTrack.getCauseTicket().getMoneyReceiverCountry()== null) {
                            JOptionPane.showMessageDialog(this, "Cannot resolve ticket as Cause is not updated");
                            return;
                          } else {
                              resolveJTkt();
                          }
                          
                      } else {
                          JOptionPane.showMessageDialog(this, "Cannot Resolve ticket");
                          return;
                      }
                  }
                  break;
          }
        }
    }//GEN-LAST:event_btnToggleAcceptResolveActionPerformed
    
    private void acceptTicket() {
        if (selectedJusticeTicketTrack != null) {
            try {
                JusticeTicketDirectory jTktDirectory = new JusticeTicketDirectory(null);
                jTktDirectory.acceptTicket(selectedJusticeTicketTrack.getjTkt().getjTktId());
                fetchTktData();
                JOptionPane.showMessageDialog(this, "Ticket is now WIP");
                updateDetailsPanel.setVisible(false);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
    
    private void resolveJTkt() {
        if (selectedJusticeTicketTrack != null) {
            String txtCmnt = txtComment.getText();
            if (txtCmnt != null && !txtCmnt.trim().equals("")) {
                try {
                     JusticeTicketDirectory jTktDirectory = new JusticeTicketDirectory(null);
                    jTktDirectory.resolveTicket(selectedJusticeTicketTrack.getjTkt().getjTktId(), txtCmnt.trim());
                    fetchTktData(); 
                    JOptionPane.showMessageDialog(this, "Ticket is Marked Resolved");
                    updateDetailsPanel.setVisible(false);
                } catch (Exception e) {
                    System.out.println(e);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Add Comment before resolving");
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnToggleAcceptResolve;
    private javax.swing.JButton btnTrackCause;
    private javax.swing.JButton btnView;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblAssignUnassignTkt;
    private javax.swing.JLabel lblCmnt;
    private javax.swing.JLabel lblJusticeCreatedon;
    private javax.swing.JLabel lblJusticeStatus;
    private javax.swing.JLabel lblJusticeUpdatedOn;
    private javax.swing.JPanel panelCardLayout;
    private javax.swing.JPanel panelTrackDetails;
    private javax.swing.JTable tblJusticeTickets;
    private javax.swing.JTextField txtCauseName;
    private javax.swing.JTextArea txtComment;
    private javax.swing.JTextField txtDonorCountry;
    private javax.swing.JTextField txtDonorEmail;
    private javax.swing.JTextField txtDonorName;
    private javax.swing.JTextField txtNgoOrg;
    private javax.swing.JTextField txtReceiverCountry;
    private javax.swing.JTextField txtReceiverEmail;
    private javax.swing.JTextField txtReceiverName;
    private javax.swing.JPanel updateDetailsPanel;
    // End of variables declaration//GEN-END:variables
}
