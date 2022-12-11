/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package uiPortal.justiceDepartment;

import java.awt.Color;
import java.awt.HeadlessException;
import java.sql.SQLException;
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
            if (viewUnAssignedTickets) {
                 jTicketList = jTicketDirectory.fetchTicketDataForJsticeAdmin(country);
            } else {
                if (empProfileRole != null && empProfileRole.equals("EMPLOYEE")) {
                    jTicketList = jTicketDirectory.fetchAssignedTickets(country, empId);
                } else {
                    jTicketList = jTicketDirectory.fetchAssignedTickets(country, 0);
                }
                 
            }
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
        panelAssignTickets = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblEmpDetails = new javax.swing.JTable();
        btnAssignTickets = new javax.swing.JButton();
        panelTrackDetails = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        lblJusticeUpdatedOn = new javax.swing.JLabel();
        lblJusticeCreatedon = new javax.swing.JLabel();
        lblJusticeStatus = new javax.swing.JLabel();
        assignedTicketEmpPanel = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtComment = new javax.swing.JTextArea();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtAssignedLastName = new javax.swing.JTextField();
        txtAssignedFirstName = new javax.swing.JTextField();
        txtAssignedEmail = new javax.swing.JTextField();
        btnTrackCause = new javax.swing.JButton();
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
        btnViewUnassignedTkts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewUnassignedTktsActionPerformed(evt);
            }
        });

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

        panelCardLayout.setBackground(new java.awt.Color(255, 255, 255));
        panelCardLayout.setLayout(new java.awt.CardLayout());

        panelAssignTickets.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

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
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 963, Short.MAX_VALUE)
            .addGroup(panelAssignTicketsLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnAssignTickets, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panelAssignTicketsLayout.setVerticalGroup(
            panelAssignTicketsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAssignTicketsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAssignTickets, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelCardLayout.add(panelAssignTickets, "card3");

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

        assignedTicketEmpPanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("ASSIGNED EMP DETAILS");

        txtComment.setColumns(20);
        txtComment.setRows(5);
        jScrollPane3.setViewportView(txtComment);

        jLabel14.setText("Comment by Justice Department: ");

        jLabel16.setText("First Name:");

        jLabel17.setText("Last Name: ");

        jLabel18.setText("Email ID:");

        txtAssignedLastName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAssignedLastNameActionPerformed(evt);
            }
        });

        txtAssignedFirstName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAssignedFirstNameActionPerformed(evt);
            }
        });

        txtAssignedEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAssignedEmailActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout assignedTicketEmpPanelLayout = new javax.swing.GroupLayout(assignedTicketEmpPanel);
        assignedTicketEmpPanel.setLayout(assignedTicketEmpPanelLayout);
        assignedTicketEmpPanelLayout.setHorizontalGroup(
            assignedTicketEmpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(assignedTicketEmpPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(assignedTicketEmpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(assignedTicketEmpPanelLayout.createSequentialGroup()
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtAssignedFirstName))
                    .addGroup(assignedTicketEmpPanelLayout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtAssignedEmail))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, assignedTicketEmpPanelLayout.createSequentialGroup()
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtAssignedLastName)))
                .addGap(18, 18, 18))
            .addGroup(assignedTicketEmpPanelLayout.createSequentialGroup()
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 19, Short.MAX_VALUE))
        );

        assignedTicketEmpPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel14, jLabel16, jLabel17, jLabel18});

        assignedTicketEmpPanelLayout.setVerticalGroup(
            assignedTicketEmpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(assignedTicketEmpPanelLayout.createSequentialGroup()
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(assignedTicketEmpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAssignedFirstName))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(assignedTicketEmpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAssignedLastName))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(assignedTicketEmpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAssignedEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(assignedTicketEmpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );

        btnTrackCause.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnTrackCause.setText("TRACK CAUSE");

        javax.swing.GroupLayout panelTrackDetailsLayout = new javax.swing.GroupLayout(panelTrackDetails);
        panelTrackDetails.setLayout(panelTrackDetailsLayout);
        panelTrackDetailsLayout.setHorizontalGroup(
            panelTrackDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelTrackDetailsLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(panelTrackDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTrackDetailsLayout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblJusticeCreatedon, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelTrackDetailsLayout.createSequentialGroup()
                        .addGroup(panelTrackDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelTrackDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblJusticeUpdatedOn, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblJusticeStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnTrackCause, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(assignedTicketEmpPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(151, Short.MAX_VALUE))
        );
        panelTrackDetailsLayout.setVerticalGroup(
            panelTrackDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTrackDetailsLayout.createSequentialGroup()
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(panelTrackDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTrackDetailsLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(assignedTicketEmpPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelTrackDetailsLayout.createSequentialGroup()
                        .addGroup(panelTrackDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panelTrackDetailsLayout.createSequentialGroup()
                                .addGroup(panelTrackDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelTrackDetailsLayout.createSequentialGroup()
                                        .addGap(12, 12, 12)
                                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panelTrackDetailsLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblJusticeStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelTrackDetailsLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblJusticeUpdatedOn)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelTrackDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblJusticeCreatedon, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnTrackCause, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56))))
        );

        panelTrackDetailsLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {lblJusticeCreatedon, lblJusticeStatus, lblJusticeUpdatedOn});

        panelCardLayout.add(panelTrackDetails, "card3");

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
                .addGroup(updateDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                            .addComponent(txtNgoOrg, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtReceiverEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDonorEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtReceiverCountry, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(updateDetailsPanelLayout.createSequentialGroup()
                        .addGap(633, 633, 633)
                        .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        updateDetailsPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel3, jLabel8});

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(panelCardLayout, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        updateDetailsPanelLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel3, jLabel8});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
            .addComponent(lblAssignUnassignTkt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnViewDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnViewUnassignedTkts)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnViewAssignedTkts))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(updateDetailsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 483, Short.MAX_VALUE))))
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

            selectedJusticeTicketTrack = justiceTicketTrackList.get(selectedRow);
             setCardLayoutPanel();
             if (viewUnAssignedTickets && justiceDepartmentEmployee != null) {
                populateEmpTable();
            } 
            if (!viewUnAssignedTickets){
                populateTrackDetails();
            }
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
        if (viewUnAssignedTickets && justiceDepartmentEmployee != null) {
            panelCardLayout.removeAll();
            panelCardLayout.add(panelAssignTickets);
            panelCardLayout.repaint();
            panelCardLayout.revalidate();
        } 
        if (!viewUnAssignedTickets) {
            panelCardLayout.removeAll();
            panelCardLayout.add(panelTrackDetails);
            panelCardLayout.repaint();
            panelCardLayout.revalidate(); 
        }
        
        
    }
    
    private void populateTrackDetails() {
        if (selectedJusticeTicketTrack != null) {
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
        if (selectedJusticeTicketTrack.getjTkt().getjTktStatus() != null && !selectedJusticeTicketTrack.getjTkt().getjTktStatus().equals("NEW")) {
            setAssignedEmpDetails();
        }
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
    
    private void setAssignedEmpDetails() {
        if (selectedJusticeTicketTrack != null) {
            try {
                JusticeDepartmentEmployeeDirectory jdEmpDirectory = new JusticeDepartmentEmployeeDirectory(null);
                JusticeDepartmentEmployee assignedEmployee =  jdEmpDirectory.fetchEmployeeById(selectedJusticeTicketTrack.getjTkt().getjEmpId());
                if (assignedEmployee != null) {
                    txtAssignedFirstName.setText(assignedEmployee.getFirstName());
                    txtAssignedLastName.setText(assignedEmployee.getLastName());
                    txtAssignedEmail.setText(assignedEmployee.getEmail());
                    
                    txtAssignedEmail.setEnabled(false);
                    txtAssignedLastName.setEnabled(false);
                    txtAssignedFirstName.setEnabled(false);
                    assignedTicketEmpPanel.setVisible(true);
                }
            } catch (Exception e) {
                System.out.println(e);
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
    
    
    private void btnViewAssignedTktsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewAssignedTktsActionPerformed
        // TODO add your handling code here:
        viewUnAssignedTickets = false;
        setLableForTable(viewUnAssignedTickets);
        emptyInputFields();
        emptyEmpTable();
        updateDetailsPanel.setVisible(false);
        emptyASsignedTicketFields();
        assignedTicketEmpPanel.setVisible(false);
        fetchTktData();
    }//GEN-LAST:event_btnViewAssignedTktsActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        // TODO add your handling code here:
        emptyInputFields();
        emptyLabels();
        emptyEmpTable();
        emptyASsignedTicketFields();
        updateDetailsPanel.setVisible(false);
        assignedTicketEmpPanel.setVisible(false);
        selectedJusticeTicketTrack = null;
    
    }//GEN-LAST:event_btnCancelActionPerformed
    
    private void emptyLabels() {
        lblJusticeCreatedon.setText("");
        lblJusticeStatus.setText("");
        lblJusticeUpdatedOn.setText("");
        txtComment.setText("");
    }
    
    private void emptyASsignedTicketFields() {
        txtAssignedEmail.setText("");
        txtAssignedFirstName.setText("");
        txtAssignedLastName.setText("");
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
    
    private void emptyEmpTable() {
        DefaultTableModel model = (DefaultTableModel)tblEmpDetails.getModel();
        model.setRowCount(0);
    }
    
    
    private void btnAssignTicketsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAssignTicketsActionPerformed
        // TODO add your handling code here:
        if (justiceDepartmentEmployeeList != null && !justiceDepartmentEmployeeList.isEmpty()) {
            int selectedRow = tblEmpDetails.getSelectedRow();
            if (selectedRow < 0) {
                JOptionPane.showMessageDialog(this, "Select one row to Assign Tickets");
                return;
            }
            JusticeDepartmentEmployee selectedJusticeDeptEmployee = justiceDepartmentEmployeeList.get(selectedRow);
            JusticeTicketDirectory justiceTicketDirectory = new JusticeTicketDirectory(null);
            try {
                int jTktid = selectedJusticeTicketTrack.getjTkt().getjTktId();
                int empId = selectedJusticeDeptEmployee.getId();
                justiceTicketDirectory.assignJusticeEmployeeToTicket(jTktid,empId);
                JOptionPane.showMessageDialog(this, "Ticket assigned");
                fetchTktData();
            } catch (HeadlessException | SQLException e){
                System.out.println(e);
            }
        } else {
            JOptionPane.showMessageDialog(this, "No employee present to assign tickets.");
            return;
        }
        
        
        
    }//GEN-LAST:event_btnAssignTicketsActionPerformed

    private void btnViewUnassignedTktsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewUnassignedTktsActionPerformed
        // TODO add your handling code here:
        viewUnAssignedTickets = true;
        setLableForTable(viewUnAssignedTickets);
        emptyInputFields();
        emptyEmpTable();
        emptyASsignedTicketFields();
        assignedTicketEmpPanel.setVisible(false);
        updateDetailsPanel.setVisible(false);
        fetchTktData();
        
    }//GEN-LAST:event_btnViewUnassignedTktsActionPerformed

    private void txtAssignedLastNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAssignedLastNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAssignedLastNameActionPerformed

    private void txtAssignedFirstNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAssignedFirstNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAssignedFirstNameActionPerformed

    private void txtAssignedEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAssignedEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAssignedEmailActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel assignedTicketEmpPanel;
    private javax.swing.JButton btnAssignTickets;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnTrackCause;
    private javax.swing.JButton btnViewAssignedTkts;
    private javax.swing.JButton btnViewDetails;
    private javax.swing.JButton btnViewUnassignedTkts;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
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
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblAssignUnassignTkt;
    private javax.swing.JLabel lblJusticeCreatedon;
    private javax.swing.JLabel lblJusticeStatus;
    private javax.swing.JLabel lblJusticeUpdatedOn;
    private javax.swing.JPanel panelAssignTickets;
    private javax.swing.JPanel panelCardLayout;
    private javax.swing.JPanel panelTrackDetails;
    private javax.swing.JTable tblEmpDetails;
    private javax.swing.JTable tblJusticeTickets;
    private javax.swing.JTextField txtAssignedEmail;
    private javax.swing.JTextField txtAssignedFirstName;
    private javax.swing.JTextField txtAssignedLastName;
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
