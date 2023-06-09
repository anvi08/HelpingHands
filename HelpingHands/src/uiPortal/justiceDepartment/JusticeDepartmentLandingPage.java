/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package uiPortal.justiceDepartment;

import profile.justiceDepartment.JusticeDepartmentEmployee;
import utilities.Constants;

/**
 *
 * @author Shreya Sharma
 */
public class JusticeDepartmentLandingPage extends javax.swing.JPanel {

    /**
     * Creates new form JusticeDepartmentLandingPage
     */
    JusticeDepartmentEmployee justiceDepartmentEmployee = null;
    public JusticeDepartmentLandingPage() {
        initComponents();
        setDefaultLandingPage();    
    }
    
    public JusticeDepartmentLandingPage(JusticeDepartmentEmployee justiceDepartmentEmployee) {
        initComponents();
        this.justiceDepartmentEmployee = justiceDepartmentEmployee;
        setDefaultLandingPage();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnAssignTicketsLink = new javax.swing.JButton();
        btnViewEmployeeLink = new javax.swing.JButton();
        btnAddEmployeeLink = new javax.swing.JButton();
        cardLayoutPanel = new javax.swing.JPanel();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        btnAssignTicketsLink.setBackground(new java.awt.Color(153, 153, 0));
        btnAssignTicketsLink.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnAssignTicketsLink.setText("Assign Tickets");
        btnAssignTicketsLink.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAssignTicketsLinkActionPerformed(evt);
            }
        });

        btnViewEmployeeLink.setBackground(new java.awt.Color(153, 153, 0));
        btnViewEmployeeLink.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnViewEmployeeLink.setText("View Employee");
        btnViewEmployeeLink.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewEmployeeLinkActionPerformed(evt);
            }
        });

        btnAddEmployeeLink.setBackground(new java.awt.Color(153, 153, 0));
        btnAddEmployeeLink.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnAddEmployeeLink.setText("Add Employee");
        btnAddEmployeeLink.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddEmployeeLinkActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(508, Short.MAX_VALUE)
                .addComponent(btnAddEmployeeLink)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnViewEmployeeLink)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAssignTicketsLink)
                .addGap(14, 14, 14))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAssignTicketsLink)
                    .addComponent(btnViewEmployeeLink)
                    .addComponent(btnAddEmployeeLink))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        cardLayoutPanel.setBackground(new java.awt.Color(255, 255, 255));
        cardLayoutPanel.setLayout(new java.awt.CardLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(cardLayoutPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cardLayoutPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddEmployeeLinkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddEmployeeLinkActionPerformed
        // TODO add your handling code here:
        JusticeDepartmentAddEmployee justiceDepartmentAddEmployee;
        if (justiceDepartmentEmployee != null) {
           justiceDepartmentAddEmployee = new JusticeDepartmentAddEmployee(justiceDepartmentEmployee);
        } else {
           justiceDepartmentAddEmployee = new JusticeDepartmentAddEmployee(); 
        }
        cardLayoutPanel.removeAll();
        cardLayoutPanel.add(justiceDepartmentAddEmployee);
        cardLayoutPanel.repaint();
        cardLayoutPanel.revalidate();
    }//GEN-LAST:event_btnAddEmployeeLinkActionPerformed

    private void btnAssignTicketsLinkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAssignTicketsLinkActionPerformed
        // TODO add your handling code here:
        JusticeDepartmentAssignTicketPanel justiceDepartmentAssignTicketPanel;
        if (justiceDepartmentEmployee != null) {
           justiceDepartmentAssignTicketPanel = new JusticeDepartmentAssignTicketPanel(justiceDepartmentEmployee);
        } else {
           justiceDepartmentAssignTicketPanel = new JusticeDepartmentAssignTicketPanel(); 
        }
        cardLayoutPanel.removeAll();
        cardLayoutPanel.add(justiceDepartmentAssignTicketPanel);
        cardLayoutPanel.repaint();
        cardLayoutPanel.revalidate();
    }//GEN-LAST:event_btnAssignTicketsLinkActionPerformed

    private void btnViewEmployeeLinkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewEmployeeLinkActionPerformed
        // TODO add your handling code here:
        setDefaultLandingPage();
       
    }//GEN-LAST:event_btnViewEmployeeLinkActionPerformed
    
    private void setDefaultLandingPage() {
        JusticeDepartmentViewEmployee justiceDepartmentViewEmployee;
        if (justiceDepartmentEmployee != null) {
           justiceDepartmentViewEmployee = new JusticeDepartmentViewEmployee(justiceDepartmentEmployee);
        } else {
            justiceDepartmentViewEmployee = new JusticeDepartmentViewEmployee();
        }
         
        cardLayoutPanel.removeAll();
        cardLayoutPanel.add(justiceDepartmentViewEmployee);
        cardLayoutPanel.repaint();
        cardLayoutPanel.revalidate();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddEmployeeLink;
    private javax.swing.JButton btnAssignTicketsLink;
    private javax.swing.JButton btnViewEmployeeLink;
    private javax.swing.JPanel cardLayoutPanel;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
