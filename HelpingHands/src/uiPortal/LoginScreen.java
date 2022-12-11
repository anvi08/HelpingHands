/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package uiPortal;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import model.causes.Cause;
import profile.Receiver.Receiver;
import profile.Receiver.ReceiverDirectory;
import utilities.DbConnection;
import utilities.Constants;
import twofa.twoFactorAuth;
import profile.donor.Donor;
import profile.donor.DonorDirectory;
import static utilities.DbConnection.query;
import utilities.ValidateUserLogin;
import utilities.Validators;
import javax.swing.JFrame;
import model.causes.CauseDirectory;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.awt.Image;
//import java.io.IOException;
//import java.net.URL;
//import javax.imageio.ImageIO;
//import javax.swing.ImageIcon;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import profile.justiceDepartment.JusticeDepartmentEmployee;
/**
 *
 * @author abhis
 */
public class LoginScreen extends javax.swing.JFrame {
    int inValidForm = 0;
//    private BufferedImage img;    
    /**
     * Creates new form LoginScreen
     */
    CauseDirectory causeDirectory;
    Cause cause;
    ReceiverDirectory receiverDirectory;
    DonorDirectory donorDirectory;
    Receiver receiver;
    Donor donor;
    public LoginScreen() throws SQLException {

        initComponents();
        getContentPane().setBackground(Color.cyan);
        for (String item :Constants.userType){
            combobxUserType.addItem(item);
        }  
        for (String item :Constants.enterpriseList) {
            dropdownRole1.addItem(item);
        }
        this.receiverDirectory = new ReceiverDirectory(receiver);
        this.donorDirectory = new DonorDirectory(donor);
        this.causeDirectory = new CauseDirectory(cause);
        combobxUserType.setSelectedIndex(-1);  
        dropdownRole1.setSelectedIndex(-1);
        //System.out.println();
        txtTwofa.setVisible(false);
        btnTwoFA.setVisible(false);   
  
        dropdownRole1.addActionListener(new ActionListener(){
                // this is anonymous class
        @Override
        public void actionPerformed(ActionEvent evt){
             //then you know that is attached to this button
        final boolean enabled = dropdownRole1.getSelectedIndex() == 3;
        if(enabled){
            btnTwoFA.setVisible(true);
            txtTwofa.setVisible(true);
            lblTwoFa.setText("<html>Enable 2FA for OTP</html>");
            //txtTwofa.setText("OTP field");
        }else{        
            btnTwoFA.setVisible(false);        
            txtTwofa.setVisible(false);
            lblTwoFa.setText("");            
            }

        }
        }); 
        userDropDowns();   
//        generateCharts();    
//        generateDonorCharts();
        popUsers();
        ImageIcon imageIcon = new ImageIcon("C:\\Users\\abhis\\Desktop\\NEU\\INFO5100 AED\\Pictures\\Helpinghands.png"); // load the image to a imageIcon
        Image image = imageIcon.getImage(); // transform it 
        Image newimg = image.getScaledInstance(200, 200,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
        imageIcon = new ImageIcon(newimg);  // transform it back   
        jLabel1.setIcon(imageIcon);        
    }

//    public void generateCharts() throws SQLException{
//        DefaultPieDataset pie = new DefaultPieDataset();
//        
//        ResultSet resultSet1 = causeDirectory.getCauseOrgs();
//        
//        while(resultSet1.next()){
//            pie.setValue(resultSet1.getString("NGO_Org"), Integer.valueOf(resultSet1.getString("Count")));          
//        }
//        JFreeChart chart = ChartFactory.createPieChart("Organisations on Platform", pie); 
//        chart.setBackgroundPaint(Color.ORANGE);
//        PieSectionLabelGenerator gen = new StandardPieSectionLabelGenerator(
//                   "{0}: {1} ({2})", new DecimalFormat("0"), new DecimalFormat("0%"));        
//        PiePlot piePlot1 =  (PiePlot) chart.getPlot();
//        piePlot1.setLabelGenerator(gen);
//        ChartFrame frame = new ChartFrame("Pie Chart",chart);
//        ChartPanel CP = new ChartPanel(chart);
//        chartPanel2.add(CP);
//        chartPanel2.updateUI();
//        chartPanel2.setPreferredSize(new Dimension(400, 200));     
//
//
//    }
//    
//    public void generateDonorCharts() throws SQLException{
//        DefaultPieDataset pie = new DefaultPieDataset();
//        ResultSet resultSet = donorDirectory.getDonorDemographics();
//        while(resultSet.next()){
//            pie.setValue(resultSet.getString("Country"), Integer.valueOf(resultSet.getString("Value")));          
//        }
//        PieSectionLabelGenerator gen = new StandardPieSectionLabelGenerator(
//                   "{0}: {1} ({2})", new DecimalFormat("0"), new DecimalFormat("0%"));
//
//        JFreeChart chart = ChartFactory.createPieChart("Donor Demographics", pie);
//        chart.setBackgroundPaint(Color.RED );
//        PiePlot piePlot1 =  (PiePlot) chart.getPlot();
//        piePlot1.setLabelGenerator(gen);
//        ChartFrame frame = new ChartFrame("Pie Chart",chart);
//        ChartPanel CP = new ChartPanel(chart);
//        System.out.println("CHARTTTTTT");
//        jPanel1.add(CP);
//        jPanel1.updateUI();
//        jPanel1.setPreferredSize(new Dimension(400, 200));   
//        jPanel1.setVisible(true);
//    }    
    public void popUsers() throws SQLException{
        ResultSet resultSet1 = donorDirectory.getDonorCount();
        ResultSet resultSet2 = receiverDirectory.getReceiverCount();
        while(resultSet1.next()&&resultSet2.next()){
            int donorsCount = Integer.valueOf(resultSet1.getString("Count"));            
            int receiversCount = Integer.valueOf(resultSet2.getString("Count"));
            String total = String.valueOf(donorsCount+receiversCount);
            txtUsers.setText(total);
            txtUsers.setVisible(true);
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

        btnLoginTab = new javax.swing.JButton();
        btnRegisterTab = new javax.swing.JButton();
        cardLayoutRegisterLoginPanel = new javax.swing.JPanel();
        panelLogin = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        dropdownRole1 = new javax.swing.JComboBox<>();
        btnLogin = new javax.swing.JButton();
        lblErrMsg = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        txtTwofa = new javax.swing.JPasswordField();
        btnTwoFA = new javax.swing.JButton();
        lblTwoFa = new javax.swing.JLabel();
        panelRegister = new javax.swing.JPanel();
        txtLastName = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtEmailReg = new javax.swing.JTextField();
        combobxType = new javax.swing.JComboBox<>();
        combobxCountry = new javax.swing.JComboBox<>();
        btnRegister = new javax.swing.JButton();
        txtPasswordReg = new javax.swing.JPasswordField();
        jLabel13 = new javax.swing.JLabel();
        combobxUserType = new javax.swing.JComboBox<>();
        txtFirstName = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtConfirmPassword = new javax.swing.JPasswordField();
        lblErrFirstName = new javax.swing.JLabel();
        lblErrEmailId = new javax.swing.JLabel();
        lblErrLastName = new javax.swing.JLabel();
        lblErrConfirmPassword = new javax.swing.JLabel();
        lblErrPassword = new javax.swing.JLabel();
        lblErrContact = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtUsers = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(51, 255, 255));

        btnLoginTab.setText("LOGIN");
        btnLoginTab.setBorder(null);
        btnLoginTab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginTabActionPerformed(evt);
            }
        });

        btnRegisterTab.setText("REGISTER");
        btnRegisterTab.setBorder(null);
        btnRegisterTab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegisterTabActionPerformed(evt);
            }
        });

        cardLayoutRegisterLoginPanel.setBackground(new java.awt.Color(255, 255, 255));
        cardLayoutRegisterLoginPanel.setLayout(new java.awt.CardLayout());

        panelLogin.setBackground(new java.awt.Color(153, 255, 153));

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Role :");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Email Id:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Password: ");

        txtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailActionPerformed(evt);
            }
        });

        dropdownRole1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                dropdownRole1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                dropdownRole1FocusLost(evt);
            }
        });
        dropdownRole1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dropdownRole1ActionPerformed(evt);
            }
        });

        btnLogin.setBackground(new java.awt.Color(0, 153, 0));
        btnLogin.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLogin.setForeground(new java.awt.Color(255, 255, 255));
        btnLogin.setText("LOGIN");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        lblErrMsg.setForeground(new java.awt.Color(153, 0, 0));

        btnTwoFA.setText("2FA Verification");
        btnTwoFA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTwoFAActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelLoginLayout = new javax.swing.GroupLayout(panelLogin);
        panelLogin.setLayout(panelLoginLayout);
        panelLoginLayout.setHorizontalGroup(
            panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLoginLayout.createSequentialGroup()
                .addGroup(panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLoginLayout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelLoginLayout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(dropdownRole1, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelLoginLayout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(lblTwoFa, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelLoginLayout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)
                                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtTwofa, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblErrMsg, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelLoginLayout.createSequentialGroup()
                                .addGap(92, 92, 92)
                                .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(panelLoginLayout.createSequentialGroup()
                        .addGap(155, 155, 155)
                        .addComponent(btnTwoFA)))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        panelLoginLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel4, jLabel5, jLabel6});

        panelLoginLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {dropdownRole1, txtEmail, txtPassword});

        panelLoginLayout.setVerticalGroup(
            panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLoginLayout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelLoginLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(dropdownRole1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTwoFa, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTwofa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(lblErrMsg)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnTwoFA)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLogin)
                .addContainerGap(134, Short.MAX_VALUE))
        );

        panelLoginLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel4, jLabel5, jLabel6});

        panelLoginLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {dropdownRole1, txtEmail});

        cardLayoutRegisterLoginPanel.add(panelLogin, "card2");

        panelRegister.setBackground(new java.awt.Color(255, 255, 204));

        txtLastName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtLastNameFocusLost(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Last Name:");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Email:");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("Password:");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("Type");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setText("Country");

        txtEmailReg.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtEmailRegFocusLost(evt);
            }
        });

        combobxCountry.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combobxCountryActionPerformed(evt);
            }
        });

        btnRegister.setBackground(new java.awt.Color(0, 153, 0));
        btnRegister.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnRegister.setForeground(new java.awt.Color(255, 255, 255));
        btnRegister.setText("Register");
        btnRegister.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                btnRegisterFocusLost(evt);
            }
        });
        btnRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegisterActionPerformed(evt);
            }
        });

        txtPasswordReg.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtPasswordRegFocusLost(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel13.setText("User Type");

        combobxUserType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combobxUserTypeActionPerformed(evt);
            }
        });

        txtFirstName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtFirstNameFocusLost(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel14.setText("First Name:");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel15.setText("Confirm Password");

        txtConfirmPassword.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtConfirmPasswordFocusLost(evt);
            }
        });

        lblErrFirstName.setForeground(new java.awt.Color(153, 0, 0));

        lblErrEmailId.setForeground(new java.awt.Color(153, 0, 0));

        lblErrLastName.setForeground(new java.awt.Color(153, 0, 0));
        lblErrLastName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                lblErrLastNameFocusLost(evt);
            }
        });

        lblErrConfirmPassword.setForeground(new java.awt.Color(153, 0, 0));

        lblErrPassword.setForeground(new java.awt.Color(153, 0, 0));

        lblErrContact.setForeground(new java.awt.Color(153, 0, 0));

        javax.swing.GroupLayout panelRegisterLayout = new javax.swing.GroupLayout(panelRegister);
        panelRegister.setLayout(panelRegisterLayout);
        panelRegisterLayout.setHorizontalGroup(
            panelRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRegisterLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(panelRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRegisterLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(btnRegister, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelRegisterLayout.createSequentialGroup()
                        .addGroup(panelRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(txtLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(panelRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(panelRegisterLayout.createSequentialGroup()
                                        .addGroup(panelRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel8)
                                            .addComponent(jLabel15)
                                            .addComponent(jLabel9))
                                        .addGap(39, 39, 39)
                                        .addGroup(panelRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtEmailReg, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
                                            .addComponent(txtConfirmPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(panelRegisterLayout.createSequentialGroup()
                                        .addComponent(jLabel14)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panelRegisterLayout.createSequentialGroup()
                                        .addComponent(jLabel12)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(combobxCountry, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(panelRegisterLayout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(combobxType, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(panelRegisterLayout.createSequentialGroup()
                                        .addComponent(jLabel13)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(combobxUserType, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(txtPasswordReg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblErrFirstName, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
                            .addComponent(lblErrLastName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblErrEmailId, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblErrPassword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblErrContact, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
                            .addComponent(lblErrConfirmPassword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(27, 27, 27))
        );

        panelRegisterLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {combobxCountry, combobxType, combobxUserType, txtConfirmPassword, txtEmailReg, txtLastName, txtPasswordReg});

        panelRegisterLayout.setVerticalGroup(
            panelRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRegisterLayout.createSequentialGroup()
                .addGroup(panelRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRegisterLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(panelRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(panelRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel14))
                            .addComponent(lblErrFirstName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblErrLastName, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                            .addGroup(panelRegisterLayout.createSequentialGroup()
                                .addGroup(panelRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(txtLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 17, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(panelRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtEmailReg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel8))
                            .addComponent(lblErrEmailId, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                        .addGroup(panelRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblErrPassword, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel9)
                                .addComponent(txtPasswordReg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                        .addGroup(panelRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel15)
                                .addComponent(txtConfirmPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblErrConfirmPassword, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelRegisterLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblErrContact, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12)
                .addGroup(panelRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(combobxUserType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(combobxCountry, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(combobxType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRegister)
                .addContainerGap())
        );

        panelRegisterLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {txtEmailReg, txtLastName, txtPasswordReg});

        panelRegisterLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {combobxCountry, combobxUserType});

        cardLayoutRegisterLoginPanel.add(panelRegister, "card3");

        jLabel2.setFont(new java.awt.Font("Yu Gothic UI Semibold", 2, 48)); // NOI18N
        jLabel2.setText("      HelpingHands");

        jLabel3.setBackground(new java.awt.Color(153, 255, 51));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 255));
        jLabel3.setText("Total Users");

        txtUsers.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(410, 410, 410)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnLoginTab, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRegisterTab)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtUsers, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cardLayoutRegisterLoginPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(214, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 443, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(209, 209, 209))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnLoginTab, btnRegisterTab});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLoginTab, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRegisterTab)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUsers, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cardLayoutRegisterLoginPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(123, 123, 123))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnLoginTab, btnRegisterTab});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_txtEmailActionPerformed

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        // TODO add your handling code here: 
        String emailId = txtEmail.getText();
        String password =String.valueOf(txtPassword.getPassword());
        String role = (String)dropdownRole1.getSelectedItem();
        if (emailId == null || emailId.trim().equals("") || password == null || password.equals("")) {
            lblErrMsg.setText("User Name or Password cannot be empty.");
        } else {
            System.out.println(role);
            try {
              validateRole(role, emailId, password);
//            String passcode = twoFactorAuth.randomPasswordGenerator();
//            System.out.println("passsword is "+passcemailode);
//            twoFactorAuth.Send2FA(passcode, emailId);
            } catch (SQLException ex) {
                Logger.getLogger(LoginScreen.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
      
    }//GEN-LAST:event_btnLoginActionPerformed

    
    
    private void btnLoginTabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginTabActionPerformed
        // TODO add your handling code here:
        cardLayoutRegisterLoginPanel.removeAll();
        cardLayoutRegisterLoginPanel.add(panelLogin);
        cardLayoutRegisterLoginPanel.repaint();
        cardLayoutRegisterLoginPanel.revalidate();
        try {
            popUsers();

        } catch (SQLException ex) {
            Logger.getLogger(LoginScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnLoginTabActionPerformed

    private void btnRegisterTabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegisterTabActionPerformed
        // TODO add your handling code here:
        cardLayoutRegisterLoginPanel.removeAll();
        cardLayoutRegisterLoginPanel.add(panelRegister);
        cardLayoutRegisterLoginPanel.repaint();
        cardLayoutRegisterLoginPanel.revalidate();
    }//GEN-LAST:event_btnRegisterTabActionPerformed

    private void btnRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegisterActionPerformed
        // TODO add your handling code here:
        
        String lastName = txtLastName.getText();
        String firstName = txtFirstName.getText();
        String email = txtEmailReg.getText();
        String pass = String.valueOf(txtPasswordReg.getPassword());       
        String confirmPass = String.valueOf(txtConfirmPassword.getPassword());

                

        
        Validators validator = new Validators();
        if (inValidForm != 0) {
            JOptionPane.showMessageDialog(this, "Employee cannot be added as the form is Invalid");
            return;
        }         
        
        if (validator.isEmpty(firstName) || validator.isEmpty(lastName) || validator.isEmpty(email) || 
                        validator.isEmpty(pass) || validator.isEmpty(confirmPass)
                         || combobxType.getSelectedIndex() == -1 || combobxCountry.getSelectedIndex() == -1 || combobxUserType.getSelectedIndex() == -1 ) {
                     JOptionPane.showMessageDialog(this, "All the fields in this form are mandatory. Make sure all the fields are filled");
                    return;
        }
        else{
        String type = combobxType.getSelectedItem().toString();        
        String country = combobxCountry.getSelectedItem().toString();      
        String userType = combobxUserType.getSelectedItem().toString();                     
            
            if(userType.equals("Donor")){       
                Donor donor = new Donor(firstName,lastName,email,pass,userType,country,type);                
                DonorDirectory donorDirectory = new DonorDirectory(donor);
                donorDirectory.addDonors();
                JOptionPane.showMessageDialog(this, "New User Has Been Created");                  
            }else{
                Receiver receiver = new Receiver(firstName,lastName,email,pass,userType,country,type);                                
                ReceiverDirectory receiverDirectory = new ReceiverDirectory(receiver);
                receiverDirectory.addReceiver();   
                JOptionPane.showMessageDialog(this, "New User Has Been Created");                    
            }
            
          
        }

        txtFirstName.setText("");
        txtLastName.setText("");
        txtEmailReg.setText("");
        txtPasswordReg.setText("");
        txtConfirmPassword.setText("");
        combobxType.setSelectedIndex(-1);  
        combobxCountry.setSelectedIndex(-1);             
        combobxUserType.setSelectedIndex(-1);

        
        
    }//GEN-LAST:event_btnRegisterActionPerformed

    private void dropdownRole1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dropdownRole1ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_dropdownRole1ActionPerformed
    private void combobxCountryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combobxCountryActionPerformed
        // TODO add your handling code here:   
    }//GEN-LAST:event_combobxCountryActionPerformed

    private void combobxUserTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combobxUserTypeActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_combobxUserTypeActionPerformed

    private void btnRegisterFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_btnRegisterFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_btnRegisterFocusLost

    private void txtFirstNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtFirstNameFocusLost
        // TODO add your handling code here:
        String firstName = txtFirstName.getText();

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
    }//GEN-LAST:event_txtFirstNameFocusLost

    private void lblErrLastNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_lblErrLastNameFocusLost
        // TODO add your handling code here:       
    }//GEN-LAST:event_lblErrLastNameFocusLost

    private void txtEmailRegFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtEmailRegFocusLost
        // TODO add your handling code here:
        String emailId = txtEmailReg.getText();
        Validators validator = new Validators();
        String errMsg = validator.validateEmail(emailId);

        if (errMsg != null && !errMsg.trim().equals("")) {
            lblErrEmailId.setText(errMsg);
            inValidForm += 1;
        } else {
            if (inValidForm > 0) {
                lblErrEmailId.setText("");
                inValidForm -= 1;
            }
        }

        if (inValidForm == 0) {
            lblErrEmailId.setText("");
        }        
    }//GEN-LAST:event_txtEmailRegFocusLost

    private void txtLastNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtLastNameFocusLost
        // TODO add your handling code here:
        String lastName = txtLastName.getText();

        Validators validator = new Validators();
        String errorMsg = validator.validateName(lastName);

        if (errorMsg != null && !errorMsg.trim().equals("")) {
            lblErrLastName.setText(errorMsg);
            inValidForm += 1;
        } else {
            if (inValidForm > 0) {
                lblErrLastName.setText("");
                inValidForm -= 1;
            }
        }

        if (inValidForm == 0) {
            lblErrLastName.setText("");
        }         
    }//GEN-LAST:event_txtLastNameFocusLost

    private void txtPasswordRegFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPasswordRegFocusLost
        // TODO add your handling code here:
        String password = txtPasswordReg.getText();
        Validators validator = new Validators();
        String errMsg = validator.validatePassword(password);

        if (errMsg != null && !errMsg.trim().equals("")) {
            lblErrPassword.setText(errMsg);
            inValidForm += 1;
        } else {
            if (inValidForm > 0) {
                lblErrPassword.setText("");
                inValidForm -= 1;
            }
        }

        if (inValidForm == 0) {
            lblErrPassword.setText("");
        }        
    }//GEN-LAST:event_txtPasswordRegFocusLost

    private void txtConfirmPasswordFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtConfirmPasswordFocusLost
        // TODO add your handling code here:
        String password = txtConfirmPassword.getText();
        String confirmPassword = txtConfirmPassword.getText();

        Validators validator = new Validators();
        String errorMsg = validator.validateConfirmPassword(password, confirmPassword);

        if (errorMsg != null && !errorMsg.trim().equals("")) {
            lblErrConfirmPassword.setText(errorMsg);
            inValidForm += 1;
        } else {
            if (inValidForm > 0) {
                lblErrConfirmPassword.setText("");
                inValidForm -= 1;
            }
        }

        if (inValidForm == 0) {
            lblErrConfirmPassword.setText("");
        }        
    }//GEN-LAST:event_txtConfirmPasswordFocusLost

    private void dropdownRole1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_dropdownRole1FocusLost
        // TODO add your handling code here:

        
    }//GEN-LAST:event_dropdownRole1FocusLost

    private void dropdownRole1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_dropdownRole1FocusGained
        // TODO add your handling code here:


    }//GEN-LAST:event_dropdownRole1FocusGained

    private void btnTwoFAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTwoFAActionPerformed
        // TODO add your handling code here:
        if(txtEmail.getText().equals("")==false){
            String passcode = twoFactorAuth.randomPasswordGenerator();
            System.out.println("passsword is "+passcode);      
            twoFactorAuth.Send2FA(passcode, txtEmail.getText());
            donorDirectory.add2FA(passcode, txtEmail.getText(), dropdownRole1.getSelectedItem().toString());                    
        }else{
            JOptionPane.showMessageDialog(this,"Please Input Email");
        }

    }//GEN-LAST:event_btnTwoFAActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LoginScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                LoginScreen loginScreen;
                try {
                    loginScreen = new LoginScreen();
                    loginScreen.setVisible(true);
                    loginScreen.setExtendedState(JFrame.MAXIMIZED_BOTH);                    
                } catch (SQLException ex) {
                    Logger.getLogger(LoginScreen.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton btnLoginTab;
    private javax.swing.JButton btnRegister;
    private javax.swing.JButton btnRegisterTab;
    private javax.swing.JButton btnTwoFA;
    private javax.swing.JPanel cardLayoutRegisterLoginPanel;
    private javax.swing.JComboBox<String> combobxCountry;
    private javax.swing.JComboBox<String> combobxType;
    private javax.swing.JComboBox<String> combobxUserType;
    private javax.swing.JComboBox<String> dropdownRole1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel lblErrConfirmPassword;
    private javax.swing.JLabel lblErrContact;
    private javax.swing.JLabel lblErrEmailId;
    private javax.swing.JLabel lblErrFirstName;
    private javax.swing.JLabel lblErrLastName;
    private javax.swing.JLabel lblErrMsg;
    private javax.swing.JLabel lblErrPassword;
    private javax.swing.JLabel lblTwoFa;
    private javax.swing.JPanel panelLogin;
    private javax.swing.JPanel panelRegister;
    private javax.swing.JPasswordField txtConfirmPassword;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtEmailReg;
    private javax.swing.JTextField txtFirstName;
    private javax.swing.JTextField txtLastName;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JPasswordField txtPasswordReg;
    private javax.swing.JPasswordField txtTwofa;
    private javax.swing.JTextField txtUsers;
    // End of variables declaration//GEN-END:variables
    public void validateRole(String role, String email, String password) throws SQLException{
        if(role==null){
            String role1 = "";
            navigateToLandingPage();           
        }
        else{
            ValidateUserLogin validateUserLogin = new ValidateUserLogin(email, password);
            switch(role){
           
            case "":
                navigateToLandingPage();
                break;
                
            case "BANK":
                
                break;
                
                
            case "NGO":
                for (Map.Entry<String, String> entry : Constants.ngoOrgAdminList.entrySet()) {
                    String key = entry.getKey();
                    Object value = entry.getValue();
                    if(key.equals(email.trim()) && value.equals(password.trim())) {
                        navigateToNGOLandingPage(email);
                        return;
                    }
                }
                lblErrMsg.setText("Username not available in DB for this role");
                break;
            case "Receiver":
                if(receiverDirectory.validateReceiver(email,password)){
                    System.out.println("FOUND OUR WAY HERE");
                    navigateToReceiverLandingPage(email);

                    return;                        
                    
                }
                lblErrMsg.setText("Username not available in DB for this role");                
                break;
            case "Donor":
                if(donorDirectory.validateDonor(email,password)){
//                    String passcode = twoFactorAuth.randomPasswordGenerator();
//                    System.out.println("passsword is "+passcode);
//                    twoFactorAuth.Send2FA(passcode, txtEmail.getText());
//                    donorDirectory.add2FA(passcode, txtEmail.getText(), role);
                    boolean twoFa = true;//donorDirectory.validateDonor2FA(txtTwofa.getText().trim());
                    if(twoFa){
                        System.out.println("FOUND OUR WAY HERE");
                        navigateToDonorLandingPage(email);
                        return;                         
                    }else{
                        JOptionPane.showMessageDialog(this, "The input TwoFA Password was incorrect");
                    }
                       

                } else {
                   lblErrMsg.setText("Username not available in DB for this role"); 
                }
                break;
            case "Service Provider":
                if(receiverDirectory.validateReceiver(email,password)){
                    System.out.println("FOUND OUR WAY HERE");
                    navigateToServiceLandingPage(email);

                    return;                        
                    
                }
                lblErrMsg.setText("Username not available in DB for this role");                
                break;                
            case "Justice Dept.":
                try {
                    JusticeDepartmentEmployee justiceDepartmentEmployee = validateUserLogin.validateJusticeLogin();
                    if (justiceDepartmentEmployee != null) {
                        String dbPassword = justiceDepartmentEmployee.getPassword();
                        if (password.trim().equals(dbPassword.trim())) {
                            if (justiceDepartmentEmployee.isStatus()) {
                                LandingPageFrame landingPage =  new LandingPageFrame(justiceDepartmentEmployee);
                                switchLandingPage(landingPage);   
                            } else {
                                lblErrMsg.setText("User is Inactive"); 
                                return;
                            }
                        } else {
                           lblErrMsg.setText("Username or password is wrong"); 
                           return;
                        }
                    } else {
                      lblErrMsg.setText("Username not available in DB for this role");  
                      break;
                    }
                } catch (Exception e) {
                    System.out.println("exception"+ e);
                }

                break;
            default:
                lblErrMsg.setText("Username not available in DB for this role");
                break;
            }            
        }

    }
    
    public void navigateToLandingPage(){
        dispose();
        String loggedInUser = null;
        LandingPageFrame landingPage =  new LandingPageFrame(loggedInUser);
        landingPage.setTitle("Dashboard");
        landingPage.setVisible(true);
        Component[] componentList = landingPage.getComponents();

        //Loop through the components
        for(Component c : componentList){

            System.out.println(c);
        }

    }    
        
    public void navigateToNGOLandingPage(String role){
        String loggedInUser = null;        
        if(role.trim().equals("health")){
            loggedInUser = "Healthcare";
        }
        else if(role.trim().equals("disaster")){
            loggedInUser = "Natural Disasters";
        }
        else{
            loggedInUser = "Education";
        }
        dispose();
        LandingPageFrame landingPage =  new LandingPageFrame(loggedInUser);
        landingPage.setTitle("Dashboard");
        landingPage.setVisible(true);
        landingPage.remove(this);
        
    } 
    
    public void navigateToServiceLandingPage(String role){
        String loggedInUser = null;        
        if(role.trim().equals("shelter")){
            loggedInUser = "shelter"+" - Natural Disasters - Service";
        }
        else if(role.trim().equals("meds")){
            loggedInUser = "meds"+" - Healthcare - Service";
        }
        else{
            loggedInUser = "book"+" - Education - Service";
        }
        dispose();
        LandingPageFrame landingPage =  new LandingPageFrame(loggedInUser);
        landingPage.setTitle("Dashboard");
        landingPage.setVisible(true);
        landingPage.remove(this);
        
    }     
    
    private void switchLandingPage(LandingPageFrame landingPageFrame) {
        dispose();
        landingPageFrame.setTitle("Dashboard");
        landingPageFrame.setVisible(true);
    }

    public void navigateToReceiverLandingPage(String role){
        dispose();
        String loggedInUser = role+ " - Receiver";
        LandingPageFrame landingPage =  new LandingPageFrame(loggedInUser);
        landingPage.setTitle("Dashboard");
        landingPage.setVisible(true);
        
        Component[] componentList = landingPage.getComponents();

        //Loop through the components
        for(Component c : componentList){

            System.out.println(c);
        }
        
    }

    private void navigateToDonorLandingPage(String role){
        dispose();
        String loggedInUser = role+ " - Donor";
        LandingPageFrame landingPage =  new LandingPageFrame(loggedInUser);
        landingPage.setTitle("Dashboard");
        landingPage.setVisible(true);
        Component[] componentList = landingPage.getComponents();

        //Loop through the components
        for(Component c : componentList){

            System.out.println(c);
        }
        
    }       
    

   
    
    public void userDropDowns(){
        combobxUserType.addActionListener (new ActionListener () {
                public void actionPerformed(ActionEvent e) {
                combobxCountry.removeAllItems();  
                combobxType.removeAllItems();
                if(combobxUserType.getSelectedIndex()==0){
                    for (String item :Constants.donorCountries) {
                        combobxCountry.addItem(item);
                    }                     
                    for (String item :Constants.donorType){
                        combobxType.addItem(item);
                    }         
                    combobxCountry.setSelectedIndex(-1);  
                    combobxType.setSelectedIndex(-1);                    
                }
                    else{
                        for (String item :Constants.receivingCountries) {
                            combobxCountry.addItem(item);
                        }                     
                        for (String item :Constants.receivingType){
                            combobxType.addItem(item);
                        }  
                        combobxCountry.setSelectedIndex(-1);  
                        combobxType.setSelectedIndex(-1);                                  
                    }
            }
            });    
    }
    


    
}
