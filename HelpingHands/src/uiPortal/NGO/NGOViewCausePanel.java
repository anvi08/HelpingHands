/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package uiPortal.NGO;

import utilities.DbConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.causes.Cause;
import model.causes.CauseDirectory;
import utilities.Constants;
import utilities.Validators;

/**
 *
 * @author abhis
 */
public class NGOViewCausePanel extends javax.swing.JPanel {
    private String loggedInUser;
    int inValidForm = 0;
    /**
     * Creates new form NGOViewCausePanel
     */
    CauseDirectory causeDirectory;
    Cause cause;
    public NGOViewCausePanel(String loggedInUser) throws SQLException {
        initComponents();
        this.causeDirectory = new CauseDirectory(cause);
        this.loggedInUser = loggedInUser;
        for (String item :Constants.receivingCountries) {
            combobxCountry.addItem(item);
        }
        
        for(String item : Constants.ngoOrganisations){
            combobxOrganisation.addItem(item);
        }
        
        for(String item : Constants.receivingType){
            combobxCategory.addItem(item);
        }
        
        if(loggedInUser != null){
            validateRole(loggedInUser);
            combobxCountry.setSelectedIndex(-1);      
            combobxCategory.setSelectedIndex(-1);                
            popCauseTable();            
        }else{   
            combobxCountry.setSelectedIndex(-1);      
            combobxCategory.setSelectedIndex(-1);        
            combobxOrganisation.setSelectedIndex(-1);       
            popCauseTable();            
        }           

    }   

    private void popCauseTable() throws SQLException {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        DefaultTableModel model = (DefaultTableModel)tblCause.getModel();
        model.setRowCount(0);
        for(Cause cause: causeDirectory.popCauseTable(loggedInUser)){
            Object[] row = new Object[6];
            row[0] = cause;
            row[1] = cause.getCauseName();
            row[2] = cause.getCauseDesc();
            row[3] = cause.getRecCategory();
            row[4] = cause.getCountry();                                     
            model.addRow(row);
        }
    }

    private void popActiveCauseTable() throws SQLException {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        DefaultTableModel model = (DefaultTableModel)tblCause.getModel();
        model.setRowCount(0);
        for(Cause cause: causeDirectory.popActiveCauseTable(loggedInUser)){
            Object[] row = new Object[6];
            row[0] = cause;
            row[1] = cause.getCauseName();
            row[2] = cause.getCauseDesc();
            row[3] = cause.getRecCategory();
            row[4] = cause.getCountry();                                     
            model.addRow(row);
        }
    }    

    private void popInactiveCauseTable() throws SQLException {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        DefaultTableModel model = (DefaultTableModel)tblCause.getModel();
        model.setRowCount(0);
        for(Cause cause: causeDirectory.popInactiveCauseTable(loggedInUser)){
            Object[] row = new Object[6];
            row[0] = cause;
            row[1] = cause.getCauseName();
            row[2] = cause.getCauseDesc();
            row[3] = cause.getRecCategory();
            row[4] = cause.getCountry();                                     
            model.addRow(row);
        }
    }     
    private void popUpdatedCause(Cause updatedCause) throws SQLException {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        DefaultTableModel model = (DefaultTableModel)tblCause.getModel();
        model.setRowCount(0);
        Object[] row = new Object[6];
        row[0] = updatedCause;
        row[1] = updatedCause.getCauseName();
        row[2] = updatedCause.getCauseDesc();
        row[3] = updatedCause.getRecCategory();
        row[4] = updatedCause.getCountry();                                     
        model.addRow(row);
    }
//    public void fillTable() throws SQLException{
      
//    DefaultTableModel tblModel =  (DefaultTableModel)tblCause.getModel();
//    tblModel.setRowCount(0);
//    while(resultSet.next()){
//
//        String organisation = resultSet.getString("NGO_Org");
//        String country = resultSet.getString("Country");
//        String name = resultSet.getString("Cause_Name");
//        String description = resultSet.getString("Cause_Desc");
//        String category = resultSet.getString("R_Category");
//        // String status = resultSet.getString("Status");
//        boolean status = Integer.parseInt(resultSet.getString("Status")) == 0 ? false : true;
//        
//
//        
//        Cause cause = new Cause(name,description,organisation,category,country,true);
//        //String tblData[] = {cause,cause.getCauseName(),cause.getCauseDesc(),cause.getRecCategory(),cause.getCountry(),"Check"};       
//        Object[] row = new Object[6];
//        row[0] = cause;
//        row[1] = cause.getCauseName();
//        row[2] = cause.getCauseDesc();
//        row[3] = cause.getRecCategory();        
//        row[4] = cause.getCountry();        
//        row[5] = cause.isStatus();        
//        tblModel.addRow(row);
//
//
//    }
//       
//        
//    }
         

    
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
        txtName = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDescription = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        combobxCountry = new javax.swing.JComboBox<>();
        combobxCategory = new javax.swing.JComboBox<>();
        combobxOrganisation = new javax.swing.JComboBox<>();
        btnView = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnActiveCauses = new javax.swing.JButton();
        btnInactiveCauses = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        lblErrFirstName = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

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

        txtName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNameFocusLost(evt);
            }
        });

        txtDescription.setColumns(20);
        txtDescription.setRows(5);
        jScrollPane2.setViewportView(txtDescription);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Organisation:");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Description:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Name:");
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Category:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Country:");

        btnView.setBackground(new java.awt.Color(255, 255, 0));
        btnView.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnView.setText("View");
        btnView.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewActionPerformed(evt);
            }
        });

        btnUpdate.setBackground(new java.awt.Color(0, 0, 255));
        btnUpdate.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnUpdate.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnActiveCauses.setBackground(new java.awt.Color(255, 255, 0));
        btnActiveCauses.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnActiveCauses.setText("Active Causes");
        btnActiveCauses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActiveCausesActionPerformed(evt);
            }
        });

        btnInactiveCauses.setBackground(new java.awt.Color(255, 255, 0));
        btnInactiveCauses.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnInactiveCauses.setText("Inactive Causes");
        btnInactiveCauses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInactiveCausesActionPerformed(evt);
            }
        });

        btnDelete.setBackground(new java.awt.Color(255, 51, 51));
        btnDelete.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        lblErrFirstName.setForeground(new java.awt.Color(153, 0, 0));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(combobxOrganisation, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtName)
                            .addComponent(jScrollPane2)
                            .addComponent(combobxCountry, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(combobxCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnView)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnActiveCauses)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnInactiveCauses)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnDelete))
                            .addComponent(lblErrFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(148, 148, 148)
                        .addComponent(btnUpdate)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(combobxOrganisation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnView)
                            .addComponent(btnActiveCauses)
                            .addComponent(btnInactiveCauses)
                            .addComponent(btnDelete))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lblErrFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(combobxCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(combobxCountry, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnUpdate)
                .addContainerGap(28, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnViewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewActionPerformed
        // TODO add your handling code here:
        int SelectedRow = tblCause.getSelectedRow();
        if(SelectedRow<0){
            JOptionPane.showMessageDialog(this, "Please Select a row");
        }else{

            DefaultTableModel m2 = (DefaultTableModel)tblCause.getModel();
            Cause SelectedRecords = (Cause) m2.getValueAt(SelectedRow, 0);
            txtName.setText(SelectedRecords.getCauseName());
            txtDescription.setText(SelectedRecords.getCauseDesc());
//            txtStatus.setText("TRUE");
            combobxOrganisation.setSelectedItem(SelectedRecords.getNgoOrg());
            combobxCountry.setSelectedItem(SelectedRecords.getCountry());            
            combobxCategory.setSelectedItem(SelectedRecords.getRecCategory());            
        }        
        
    }//GEN-LAST:event_btnViewActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        int SelectedRow = tblCause.getSelectedRow();
        try{
            DefaultTableModel m2 = (DefaultTableModel)tblCause.getModel();
            Cause SelectedRecords = (Cause) m2.getValueAt(SelectedRow, 0);         
            if(SelectedRecords.isStatus()==true){
            String organisation = combobxOrganisation.getSelectedItem().toString();
            String country = combobxCountry.getSelectedItem().toString();
            String name = txtName.getText();
            String description = txtDescription.getText();
            String category = combobxCategory.getSelectedItem().toString();

            combobxOrganisation.setSelectedIndex(-1);        
            combobxCountry.setSelectedIndex(-1);      
            combobxCategory.setSelectedIndex(-1);      
            txtName.setText("");
            txtDescription.setText("");

            Cause cause = new Cause(name,description,organisation,category,country,false);
            JOptionPane.showMessageDialog(this, "Data has been Updated");
            causeDirectory.updateCause(cause, SelectedRecords.getCauseId());
            popUpdatedCause(cause);       
            
        }else{
                JOptionPane.showMessageDialog(this,"Inactive Causes Cannot be Updated");
            }
 
        //System.out.println(updateQuery);
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Please Select a row to Update");
        }
        
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnActiveCausesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActiveCausesActionPerformed
        try {
            // TODO add your handling code here:
            jScrollPane2.setVisible(true);
            btnView.setVisible(true);
            jLabel1.setVisible(true);
            jLabel2.setVisible(true);            
            jLabel3.setVisible(true);            
            jLabel4.setVisible(true);
            jLabel5.setVisible(true);          
            combobxCountry.setVisible(true);
            combobxOrganisation.setVisible(true);
            combobxCategory.setVisible(true);   
            txtName.setVisible(true);
            txtDescription.setVisible(true);  
            btnUpdate.setVisible(false);            
            
            if(loggedInUser != null){
                popActiveCauseTable();                
            }
            else{
                popActiveCauseTable();
            }
        } catch (SQLException ex) {
            Logger.getLogger(NGOViewCausePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnActiveCausesActionPerformed

    private void btnInactiveCausesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInactiveCausesActionPerformed
        // TODO add your handling code here:
        try {
            jScrollPane2.setVisible(false);
            // TODO add your handling code here:
            btnView.setVisible(false);
            jLabel1.setVisible(false);
            jLabel2.setVisible(false);            
            jLabel3.setVisible(false);            
            jLabel4.setVisible(false);
            jLabel5.setVisible(false);          
            combobxCountry.setVisible(false);
            combobxOrganisation.setVisible(false);
            combobxCategory.setVisible(false);   
            txtName.setVisible(false);
            txtDescription.setVisible(false);
            btnUpdate.setVisible(false);
            if(loggedInUser != null){
                popInactiveCauseTable();                
            }
            else{
                popInactiveCauseTable();
            }

        } catch (SQLException ex) {
            Logger.getLogger(NGOViewCausePanel.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }//GEN-LAST:event_btnInactiveCausesActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        int SelectedRow = tblCause.getSelectedRow();
        if(SelectedRow<0){
            JOptionPane.showMessageDialog(this, "Please Select a row");
        }else{        for (String item :Constants.receivingCountries) {
            combobxCountry.addItem(item);
        }
            DefaultTableModel m2 = (DefaultTableModel)tblCause.getModel();
            Cause SelectedRecords = (Cause) m2.getValueAt(SelectedRow, 0);
            String organisation = SelectedRecords.getNgoOrg();
            String country = SelectedRecords.getCountry();
            String name = SelectedRecords.getCauseName();
            String description = SelectedRecords.getCauseDesc();
            String category = SelectedRecords.getRecCategory(); 
            causeDirectory.deletCause(name);
            JOptionPane.showMessageDialog(this,"Selected Row has been deleted");
//            popCauseTable();
        }         
        

                
        
        
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void txtNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNameFocusLost
        // TODO add your handling code here:
        String firstName = txtName.getText();

        Validators validator = new Validators();
        String errorMsg = validator.validateName(firstName);

        if (errorMsg != null && !errorMsg.trim().equals("")) {
            lblErrFirstName.setText(errorMsg);
            inValidForm += 1;
        } else {
            if (inValidForm > 0) {
                inValidForm -= 1;
            }
        }

        if (inValidForm == 0) {
            lblErrFirstName.setText("");
        }                                  
            
    }//GEN-LAST:event_txtNameFocusLost

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActiveCauses;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnInactiveCauses;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton btnView;
    private javax.swing.JComboBox<String> combobxCategory;
    private javax.swing.JComboBox<String> combobxCountry;
    private javax.swing.JComboBox<String> combobxOrganisation;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblErrFirstName;
    private javax.swing.JTable tblCause;
    private javax.swing.JTextArea txtDescription;
    private javax.swing.JTextField txtName;
    // End of variables declaration//GEN-END:variables

    public void validateRole(String loggedInUser){

        if(loggedInUser != null){
            
            System.out.println(loggedInUser);
            combobxOrganisation.removeAllItems();
            combobxOrganisation.addItem(loggedInUser); 
            combobxOrganisation.setEnabled(false);
        }
        
        
    } 
}
