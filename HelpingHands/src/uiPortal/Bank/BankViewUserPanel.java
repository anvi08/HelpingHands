/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package uiPortal.Bank;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import profile.bank.BankPerson;
import profile.bank.BankPersonDirectory;
import utilities.DbConnection;
import java.sql.PreparedStatement;
import static utilities.DbConnection.query;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import utilities.Constants;

/**
 *
 * @author Anvi Jain
 */
public class BankViewUserPanel extends javax.swing.JPanel {

    /**
     * Creates new form BankViewUserPanel
     */

    BankPersonDirectory bankPersonDirectory;
    ArrayList<BankPerson> bankPersonList;
    ArrayList<BankPerson> activeBankPersonList;
    BankPerson bankPerson;
    String loggedInUserCountry = "";

    public BankViewUserPanel() throws SQLException {
        initComponents();
        
        this.bankPersonDirectory = new BankPersonDirectory(bankPerson);
        bankPersonList = new ArrayList<BankPerson>();  
        activeBankPersonList = new ArrayList<BankPerson>();
        populateBankTable();
        setCountriesDropdown();
        setEmpTypeDropdown();
       
    }
    public BankViewUserPanel(BankPerson bp) throws SQLException {
        initComponents();
        
        this.bankPersonDirectory = new BankPersonDirectory(bankPerson);
        bankPersonList = new ArrayList<BankPerson>();  
        activeBankPersonList = new ArrayList<BankPerson>();
        setPanelForUser(bp);
        loggedInUserCountry=bp.getCountry();
        populateBankTable();
    }
    public void setPanelForUser(BankPerson bp){
        String userCountry = bp.getCountry();
        String userType = bp.getEmpType();
        ArrayList<String> countriesList = new ArrayList<String>();
        countriesList.addAll(Constants.donorCountries);
        countriesList.addAll(Constants.receivingCountries);
        
        /*
        for each country in the dropdown/constants 
        get a list of all countries
        match user to the country
        if country matches
        set the dropdown to that country
        */
        for (String country : countriesList )
        {
            if(userCountry.equals(country))
            {
                dropDownCountry.removeAllItems();
                dropDownCountry.addItem(userCountry);
                dropDownCountry.setEnabled(false);
            }
        }
        
        dropDownType.removeAllItems();
        dropDownType.addItem("EMPLOYEE");
        dropDownType.setEnabled(false);
        
    }
    public void setCountriesDropdown() {
        ArrayList<String> countriesList = new ArrayList<String>();
        countriesList.addAll(Constants.donorCountries);
        Vector<String> contryVector = new Vector<>(countriesList);
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(contryVector);
        dropDownCountry.setModel(model);
    }
    
    public void setEmpTypeDropdown() {
        Vector<String> empTypeVector = new Vector<String>(Arrays.asList(Constants.empType));
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(empTypeVector);
        dropDownType.setModel(model);
        
    } 
    
    
    @SuppressWarnings("unchecked")
    
    
    private void populateBankTable() throws SQLException{
        DefaultTableModel model = (DefaultTableModel)tblBankEmployee.getModel();
        model.setRowCount(0);
        
        if(loggedInUserCountry.equals("")){
            bankPersonList = bankPersonDirectory.populateActiveBp();
            System.out.println();
        }
        else{
            bankPersonList = bankPersonDirectory.populateCountryActiveBp(loggedInUserCountry);
            System.out.println("");
        }
        
        
        System.out.println("In the panel:"+bankPersonList.get(0).getFirstName());
        for(BankPerson bankPerson: bankPersonList){
            Object[] row = new Object[6];
            //row[0] = bankPerson;
            row[0] = bankPerson.getFirstName();
            row[1] = bankPerson.getLastName();
            row[2] = bankPerson.getEmail();
            row[3] = bankPerson.getEmpType();
            row[4] = bankPerson.getBankName();
            row[5] = bankPerson.getCountry();
            model.addRow(row);
            System.out.print("BANK PERSON"+bankPerson.getFirstName());
        }
        System.out.print(bankPerson);
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblBankEmployee = new javax.swing.JTable();
        btnViewBankPerson = new javax.swing.JButton();
        btnViewActive = new javax.swing.JButton();
        btnViewInactive = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        viewUpdatePanel = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        dropDownCountry = new javax.swing.JComboBox<>();
        txtFirstName = new javax.swing.JTextField();
        txtLastName = new javax.swing.JTextField();
        txtEmailId = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        dropDownType = new javax.swing.JComboBox<>();
        btnUpdateBankEmp = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        tblBankEmployee.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "First Name ", "Last Name", "Email Id", "Type", "Bank Name", "Country"
            }
        ));
        jScrollPane1.setViewportView(tblBankEmployee);

        btnViewBankPerson.setText("View");
        btnViewBankPerson.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewBankPersonActionPerformed(evt);
            }
        });

        btnViewActive.setText("View Active");
        btnViewActive.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewActiveActionPerformed(evt);
            }
        });

        btnViewInactive.setText("View Inactive");
        btnViewInactive.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewInactiveActionPerformed(evt);
            }
        });

        btnDelete.setBackground(new java.awt.Color(255, 0, 0));
        btnDelete.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        viewUpdatePanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setText("Email Id");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel13.setText("Country");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel14.setText("Type");

        btnUpdateBankEmp.setBackground(new java.awt.Color(0, 102, 255));
        btnUpdateBankEmp.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnUpdateBankEmp.setText("Update");
        btnUpdateBankEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateBankEmpActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel15.setText("First Name");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel16.setText("Last name");

        javax.swing.GroupLayout viewUpdatePanelLayout = new javax.swing.GroupLayout(viewUpdatePanel);
        viewUpdatePanel.setLayout(viewUpdatePanelLayout);
        viewUpdatePanelLayout.setHorizontalGroup(
            viewUpdatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewUpdatePanelLayout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(viewUpdatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(viewUpdatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(dropDownCountry, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtFirstName)
                    .addComponent(txtEmailId)
                    .addComponent(txtLastName)
                    .addComponent(dropDownType, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(viewUpdatePanelLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(btnUpdateBankEmp, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        viewUpdatePanelLayout.setVerticalGroup(
            viewUpdatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewUpdatePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(viewUpdatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(dropDownCountry, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(viewUpdatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txtFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(viewUpdatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(txtLastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(viewUpdatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(txtEmailId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(viewUpdatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(dropDownType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(54, 54, 54)
                .addComponent(btnUpdateBankEmp)
                .addContainerGap(130, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(viewUpdatePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(btnViewBankPerson, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnViewActive)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnViewInactive, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDelete)
                .addContainerGap(42, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(94, 94, 94)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnViewBankPerson)
                            .addComponent(btnViewActive)
                            .addComponent(btnViewInactive)
                            .addComponent(btnDelete))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(viewUpdatePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnViewInactiveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewInactiveActionPerformed
        // TODO add your handling code here:
        viewUpdatePanel.setVisible(false);
        DefaultTableModel model = (DefaultTableModel)tblBankEmployee.getModel();
        model.setRowCount(0);
        try {
            
            if(loggedInUserCountry.equals("")){
            bankPersonList = bankPersonDirectory.populateInactiveBp();
            System.out.println();
        }
        else{
            bankPersonList = bankPersonDirectory.populateCountryInactiveBp(loggedInUserCountry);
            System.out.println("");
        }
            
            
            
            
            //bankPersonList = bankPersonDirectory.populateInactiveBp();
        } catch (SQLException ex) {
            Logger.getLogger(BankViewUserPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        //System.out.println("In the panel:"+bankPersonList.get(0).getFirstName());
        for(BankPerson bankPerson: bankPersonList){
            Object[] row = new Object[6];
            //row[0] = bankPerson;
            row[0] = bankPerson.getFirstName();
            row[1] = bankPerson.getLastName();
            row[2] = bankPerson.getEmail();
            row[3] = bankPerson.getEmpType();
            row[4] = bankPerson.getBankName();
            row[5] = bankPerson.getCountry();
            model.addRow(row);
            //System.out.print("BANK PERSON"+bankPerson.getFirstName());
        }
        //System.out.print(bankPerson);
        
    }//GEN-LAST:event_btnViewInactiveActionPerformed

    private void btnViewBankPersonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewBankPersonActionPerformed
        // TODO add your handling code here:
        viewUpdatePanel.setVisible(true);
        txtEmailId.setEditable(false);
        int selectedRow = tblBankEmployee.getSelectedRow();
        BankPerson bp= bankPersonList.get(selectedRow);
        //System.out.println(bp.getBankPersonId());
        txtFirstName.setText(bp.getFirstName());
        txtLastName.setText(bp.getLastName());
        txtEmailId.setText(bp.getEmail());
        dropDownCountry.setSelectedItem(bp.getCountry());
        dropDownType.setSelectedItem(bp.getEmpType());

    }//GEN-LAST:event_btnViewBankPersonActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        int selectedRow = tblBankEmployee.getSelectedRow();
        if(selectedRow<0){
            JOptionPane.showMessageDialog(this, "Please Select a row");
        }else{
            BankPerson bp= bankPersonList.get(selectedRow);
            System.out.println(bp.getBankPersonId());
            
            String email = bp.getEmail();
            txtFirstName.setText(bp.getFirstName());
            txtLastName.setText(bp.getLastName());
            txtEmailId.setText(bp.getEmail());
            dropDownCountry.setSelectedItem(bp.getCountry());
            dropDownType.setSelectedItem(bp.getEmpType());
            bankPersonDirectory.deleteBankPerson(email);
            JOptionPane.showMessageDialog(this,"Selected Row has been deleted");
            try {
                populateBankTable();
            } catch (SQLException ex) {
                Logger.getLogger(BankViewUserPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(this, "The Bank User Has been Deleted");
        }
        
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnUpdateBankEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateBankEmpActionPerformed
        // TODO add your handling code here:
        //BankPerson bp= bankPersonList.get(selectedRow);
        int selectedRow = tblBankEmployee.getSelectedRow();
        try{
            String firstName = txtFirstName.getText();
            String lastName = txtLastName.getText();
            String email = txtEmailId.getText();
        String country = dropDownCountry.getSelectedItem().toString();
        String type = dropDownType.getSelectedItem().toString();
        System.out.print("Is bank person empty? "+bankPersonList.isEmpty());
        BankPerson bp = bankPersonList.get(selectedRow);
        bp.setFirstName(firstName);
        bp.setLastName(lastName);
        bp.setCountry(country);
        bp.setEmpType(type);
        //System.out.println("In update panellllllllllll:"+bp);
        bankPersonDirectory.updateBankPerson(selectedRow, bp);
        try {
            populateBankTable();
        } catch (SQLException ex) {
            Logger.getLogger(BankViewUserPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(this, "The Bank User Has been Updated");
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Please select a row to update");
        }
        
    }//GEN-LAST:event_btnUpdateBankEmpActionPerformed

    private void btnViewActiveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewActiveActionPerformed
        // TODO add your handling code here:
        viewUpdatePanel.setVisible(false);
        DefaultTableModel model = (DefaultTableModel)tblBankEmployee.getModel();
        model.setRowCount(0);
        try {
            if(loggedInUserCountry.equals("")){
            bankPersonList = bankPersonDirectory.populateActiveBp();
            System.out.println();
        }
        else{
            bankPersonList = bankPersonDirectory.populateCountryActiveBp(loggedInUserCountry);
            System.out.println("");
        }
        } catch (SQLException ex) {
            Logger.getLogger(BankViewUserPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("In the panel:"+bankPersonList.get(0).getFirstName());
        for(BankPerson bankPerson: bankPersonList){
            Object[] row = new Object[6];
            //row[0] = bankPerson;
            row[0] = bankPerson.getFirstName();
            row[1] = bankPerson.getLastName();
            row[2] = bankPerson.getEmail();
            row[3] = bankPerson.getEmpType();
            row[4] = bankPerson.getBankName();
            row[5] = bankPerson.getCountry();
            model.addRow(row);
            System.out.print("BANK PERSON"+bankPerson.getFirstName());
        }
        System.out.print(bankPerson);
    }//GEN-LAST:event_btnViewActiveActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnUpdateBankEmp;
    private javax.swing.JButton btnViewActive;
    private javax.swing.JButton btnViewBankPerson;
    private javax.swing.JButton btnViewInactive;
    private javax.swing.JComboBox<String> dropDownCountry;
    private javax.swing.JComboBox<String> dropDownType;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblBankEmployee;
    private javax.swing.JTextField txtEmailId;
    private javax.swing.JTextField txtFirstName;
    private javax.swing.JTextField txtLastName;
    private javax.swing.JPanel viewUpdatePanel;
    // End of variables declaration//GEN-END:variables

}
