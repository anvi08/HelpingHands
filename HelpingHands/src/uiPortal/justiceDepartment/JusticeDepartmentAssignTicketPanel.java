/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package uiPortal.justiceDepartment;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.causes.Cause;
import model.causes.CauseDirectory;
import model.causeticket.CauseTicket;
import javax.swing.table.DefaultTableModel;
import model.causeticket.CauseTicketDirectory;
import model.justiceTicket.JusticeTicket;
import model.justiceTicket.JusticeTicketDirectory;
import model.justiceTicket.JusticeTicketTrack;
import profile.Receiver.Receiver;
import profile.Receiver.ReceiverDirectory;
import profile.donor.Donor;
import profile.donor.DonorDirectory;
import profile.justiceDepartment.JusticeDepartmentEmployee;
import profile.justiceDepartment.JusticeDepartmentEmployeeDirectory;

/**
 *
 * @author Khalesi
 */
public class JusticeDepartmentAssignTicketPanel extends javax.swing.JPanel {
    JusticeDepartmentEmployee justiceDepartmentEmployee = null;
    ArrayList<JusticeTicketTrack> justiceTicketTrackList;
    JusticeTicketTrack selectedJusticeTicketTrack;
    ArrayList<JusticeDepartmentEmployee> justiceDepartmentEmployeeList; 
    boolean viewUnAssignedTickets = true;
    
    /**
     * Creates new form JusticeDepartmentAssignTicketPanel
     */
    public JusticeDepartmentAssignTicketPanel() {
        initComponents();
        setDefaultData();
    }
    
    public JusticeDepartmentAssignTicketPanel(JusticeDepartmentEmployee justiceDepartmentEmployee) {
        initComponents();
        this.justiceDepartmentEmployee = justiceDepartmentEmployee;
        setDefaultData();
  
        
    }
    
    private void setDefaultData() {
        this.justiceTicketTrackList = new ArrayList<JusticeTicketTrack>();
        setLableForTable(true);
        updateDetailsPanel.setVisible(false);
        viewUnAssignedTickets = true;
        fetchTktData();
        fetchEmployeeDetails();
    }
    
    private void setLableForTable(boolean setUnAssignedLabel) {
        if (setUnAssignedLabel) {
            lblAssignUnassignTkt.setText("View Unassigned Tickets");
        } else {
            lblAssignUnassignTkt.setText("View Assigned Tickets");
        }
    }
    
    private void fetchEmployeeDetails() {
        justiceDepartmentEmployeeList = new ArrayList<JusticeDepartmentEmployee>();
        if (justiceDepartmentEmployee != null) {
            JusticeDepartmentEmployeeDirectory justiceDepartmentEmployeeDirectory = new JusticeDepartmentEmployeeDirectory(justiceDepartmentEmployee);
            try {
               justiceDepartmentEmployeeList = justiceDepartmentEmployeeDirectory.fetchJusticeDeptEmpData(1, justiceDepartmentEmployee.getCountry(), "EMPLOYEE");
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
    
    private void fetchTktData() {
        JusticeTicketDirectory jTicketDirectory = new JusticeTicketDirectory(null);
        
        try {
            ArrayList<JusticeTicket>jTicketList = jTicketDirectory.fetchTicketDataForJsticeAdmin(justiceDepartmentEmployee.getCountry());
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

        jScrollPane2 = new javax.swing.JScrollPane();
        tblJusticeTickets = new javax.swing.JTable();
        lblAssignUnassignTkt = new javax.swing.JLabel();
        btnViewAssignedTkts = new javax.swing.JButton();
        btnViewUnassignedTkts = new javax.swing.JButton();
        btnViewDetails = new javax.swing.JButton();
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
        panelTrackDetails = new javax.swing.JPanel();
        panelAssignTickets = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblEmpDetails = new javax.swing.JTable();
        btnAssignTickets = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

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

        lblAssignUnassignTkt.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblAssignUnassignTkt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAssignUnassignTkt.setText("View Un Assigned Ticket");

        btnViewAssignedTkts.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnViewAssignedTkts.setText("VIEW ASSIGNED TICKETS");
        btnViewAssignedTkts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewAssignedTktsActionPerformed(evt);
            }
        });

        btnViewUnassignedTkts.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnViewUnassignedTkts.setText("VIEW UNASSSIGNED TICKETS");

        btnViewDetails.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnViewDetails.setText("VIEW");
        btnViewDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewDetailsActionPerformed(evt);
            }
        });

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

        panelCardLayout.setLayout(new java.awt.CardLayout());

        javax.swing.GroupLayout panelTrackDetailsLayout = new javax.swing.GroupLayout(panelTrackDetails);
        panelTrackDetails.setLayout(panelTrackDetailsLayout);
        panelTrackDetailsLayout.setHorizontalGroup(
            panelTrackDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 846, Short.MAX_VALUE)
        );
        panelTrackDetailsLayout.setVerticalGroup(
            panelTrackDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 271, Short.MAX_VALUE)
        );

        panelCardLayout.add(panelTrackDetails, "card3");

        panelAssignTickets.setBackground(new java.awt.Color(255, 255, 255));

        tblEmpDetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "First Name", "Last Name", "Email ID"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblEmpDetails);

        btnAssignTickets.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnAssignTickets.setText("ASSIGN TICKETS");
        btnAssignTickets.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAssignTicketsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelAssignTicketsLayout = new javax.swing.GroupLayout(panelAssignTickets);
        panelAssignTickets.setLayout(panelAssignTicketsLayout);
        panelAssignTicketsLayout.setHorizontalGroup(
            panelAssignTicketsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 846, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAssignTicketsLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnAssignTickets, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panelAssignTicketsLayout.setVerticalGroup(
            panelAssignTicketsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAssignTicketsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAssignTickets, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(64, Short.MAX_VALUE))
        );

        panelCardLayout.add(panelAssignTickets, "card3");

        btnCancel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnCancel.setText("CANCEL");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout updateDetailsPanelLayout = new javax.swing.GroupLayout(updateDetailsPanel);
        updateDetailsPanel.setLayout(updateDetailsPanelLayout);
        updateDetailsPanelLayout.setHorizontalGroup(
            updateDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelCardLayout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(updateDetailsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(updateDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(updateDetailsPanelLayout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(34, 34, 34)
                        .addComponent(txtDonorCountry, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel8))
                    .addGroup(updateDetailsPanelLayout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtReceiverName, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                        .addGap(35, 35, 35)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(updateDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(updateDetailsPanelLayout.createSequentialGroup()
                        .addGroup(updateDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNgoOrg, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtReceiverEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDonorEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                    .addGroup(updateDetailsPanelLayout.createSequentialGroup()
                        .addComponent(txtReceiverCountry, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        updateDetailsPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel3, jLabel8});

        updateDetailsPanelLayout.setVerticalGroup(
            updateDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(updateDetailsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(updateDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNgoOrg, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCauseName, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                        .addComponent(txtReceiverCountry, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(panelCardLayout, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(131, Short.MAX_VALUE))
        );

        updateDetailsPanelLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel3, jLabel8});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 939, Short.MAX_VALUE)
            .addComponent(lblAssignUnassignTkt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 436, Short.MAX_VALUE)
                        .addComponent(btnViewDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnViewUnassignedTkts)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnViewAssignedTkts))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(updateDetailsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnViewAssignedTkts, btnViewUnassignedTkts});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblAssignUnassignTkt, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnViewAssignedTkts)
                    .addComponent(btnViewUnassignedTkts, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnViewDetails))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(updateDetailsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnViewAssignedTkts, btnViewDetails, btnViewUnassignedTkts});

    }// </editor-fold>//GEN-END:initComponents

    private void btnViewDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewDetailsActionPerformed
        // TODO add your handling code here:
        if (justiceTicketTrackList != null && !justiceTicketTrackList.isEmpty()) {
            int selectedRow = tblJusticeTickets.getSelectedRow();
            
            if (selectedRow < 0) {
                JOptionPane.showMessageDialog(this, "Select one row to view details");
                return;
            } 
            updateDetailsPanel.setVisible(true);
            if (viewUnAssignedTickets) {
                populateEmpTable();
            } else {
                populateTrackDetails();
            }
            selectedJusticeTicketTrack = justiceTicketTrackList.get(selectedRow);
             setCardLayoutPanel();
             txtCauseName.setText(selectedJusticeTicketTrack.getCause().getCauseName());
             txtNgoOrg.setText(selectedJusticeTicketTrack.getCause().getNgoOrg());
             if (selectedJusticeTicketTrack.getDonor().getFirstName() != null && !selectedJusticeTicketTrack.getDonor().getFirstName().trim().equals("")) {
                 if (selectedJusticeTicketTrack.getDonor().getLastName()!= null && !selectedJusticeTicketTrack.getDonor().getLastName().trim().equals("")) {
                txtDonorName.setText(selectedJusticeTicketTrack.getDonor().getFirstName() + " " + selectedJusticeTicketTrack.getDonor().getLastName());
             } 
                txtDonorName.setText(selectedJusticeTicketTrack.getDonor().getFirstName());
             } 
             if (selectedJusticeTicketTrack.getReceiver().getFirstName() != null && !selectedJusticeTicketTrack.getReceiver().getFirstName().trim().equals("")) {
                 if (selectedJusticeTicketTrack.getReceiver().getLastName()!= null && !selectedJusticeTicketTrack.getReceiver().getLastName().trim().equals("")) {
                txtReceiverName.setText(selectedJusticeTicketTrack.getReceiver().getFirstName() + " " + selectedJusticeTicketTrack.getReceiver().getLastName());
             } 
                txtReceiverName.setText(selectedJusticeTicketTrack.getReceiver().getFirstName());
             } 
             
             txtDonorEmail.setText(selectedJusticeTicketTrack.getDonor().getEmail());
             txtReceiverEmail.setText(selectedJusticeTicketTrack.getReceiver().getEmail());
             txtReceiverCountry.setText(selectedJusticeTicketTrack.getCauseTicket().getReceivingCountry());
             txtDonorCountry.setText(selectedJusticeTicketTrack.getCauseTicket().getDonorCountry());
             
             setTxtFieldsDisable();
        }
    }//GEN-LAST:event_btnViewDetailsActionPerformed
    
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
    
    
    private void setCardLayoutPanel() {
        if (viewUnAssignedTickets) {
            panelCardLayout.removeAll();
            panelCardLayout.add(panelAssignTickets);
            panelCardLayout.repaint();
            panelCardLayout.revalidate();
        }
        
        
    }
    
    private void populateTrackDetails() {
        
    }
    
    private void populateEmpTable() {
        DefaultTableModel model = (DefaultTableModel)tblEmpDetails.getModel();
        model.setRowCount(0);
        if (justiceDepartmentEmployeeList != null && !justiceDepartmentEmployeeList.isEmpty()) {
              for (JusticeDepartmentEmployee justiceDptEmp: justiceDepartmentEmployeeList) {
                 Object[] row = new Object[3];
                 row[0] = justiceDptEmp;
                 row[1] = justiceDptEmp.getLastName();
                 row[2] = justiceDptEmp.getEmail();
                 model.addRow(row);
              }
    }
    }
    
    
    private void btnViewAssignedTktsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewAssignedTktsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnViewAssignedTktsActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnAssignTicketsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAssignTicketsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAssignTicketsActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAssignTickets;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnViewAssignedTkts;
    private javax.swing.JButton btnViewDetails;
    private javax.swing.JButton btnViewUnassignedTkts;
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JPanel panelAssignTickets;
    private javax.swing.JPanel panelCardLayout;
    private javax.swing.JPanel panelTrackDetails;
    private javax.swing.JTable tblEmpDetails;
    private javax.swing.JTable tblJusticeTickets;
    private javax.swing.JTextField txtCauseName;
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
