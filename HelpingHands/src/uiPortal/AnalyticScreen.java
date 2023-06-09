/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package uiPortal;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import javax.swing.ImageIcon;
import model.causes.Cause;
import model.causes.CauseDirectory;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import profile.Receiver.Receiver;
import profile.Receiver.ReceiverDirectory;
import profile.donor.Donor;
import profile.donor.DonorDirectory;

/**
 *
 * @author abhis
 */
public class AnalyticScreen extends javax.swing.JPanel {

    /**
     * Creates new form AnalyticScreen
     */
    CauseDirectory causeDirectory;
    Cause cause;
    ReceiverDirectory receiverDirectory;
    DonorDirectory donorDirectory;
    Receiver receiver;
    Donor donor;    
    public AnalyticScreen() throws SQLException {
        initComponents();
        jPanel1.removeAll();   
        this.receiverDirectory = new ReceiverDirectory(receiver);
        this.donorDirectory = new DonorDirectory(donor);
        this.causeDirectory = new CauseDirectory(cause);      
        generateReceiverCharts();
        generateCharts();    
        generateDonorCharts();        
    }

        public void generateDonorCharts() throws SQLException{
            DefaultPieDataset pie = new DefaultPieDataset();
            ResultSet resultSet = donorDirectory.getDonorDemographics();
            while(resultSet.next()){
                pie.setValue(resultSet.getString("Country"), Integer.valueOf(resultSet.getString("Value")));          
            }
            PieSectionLabelGenerator gen = new StandardPieSectionLabelGenerator(
                       "{0}: {1} ({2})", new DecimalFormat("0"), new DecimalFormat("0%"));

            JFreeChart chart = ChartFactory.createPieChart("Donor Demographics", pie);
            chart.setBackgroundPaint(Color.RED );
            PiePlot piePlot1 =  (PiePlot) chart.getPlot();
            piePlot1.setLabelGenerator(gen);
            ChartFrame frame = new ChartFrame("Pie Chart",chart);
            ChartPanel CP = new ChartPanel(chart);
            System.out.println("CHARTTTTTT");
            jPanel1.add(CP);
            jPanel1.updateUI();
            jPanel1.setPreferredSize(new Dimension(400, 200));   
            jPanel1.setVisible(true);
        }    
	
	public void generateCharts() throws SQLException{
            DefaultPieDataset pie = new DefaultPieDataset();

            ResultSet resultSet1 = causeDirectory.getCauseOrgs();

            while(resultSet1.next()){
                pie.setValue(resultSet1.getString("NGO_Org"), Integer.valueOf(resultSet1.getString("Count")));          
            }
            JFreeChart chart = ChartFactory.createPieChart("Organisations on Platform", pie); 
            chart.setBackgroundPaint(Color.ORANGE);
            PieSectionLabelGenerator gen = new StandardPieSectionLabelGenerator(
                       "{0}: {1} ({2})", new DecimalFormat("0"), new DecimalFormat("0%"));        
            PiePlot piePlot1 =  (PiePlot) chart.getPlot();
            piePlot1.setLabelGenerator(gen);
            ChartFrame frame = new ChartFrame("Pie Chart",chart);
            ChartPanel CP = new ChartPanel(chart);
            chartPanel2.add(CP);
            chartPanel2.updateUI();
            chartPanel2.setPreferredSize(new Dimension(400, 200));     


        }    
    
    public void generateReceiverCharts() throws SQLException{
        DefaultPieDataset pie = new DefaultPieDataset();
        ResultSet resultSet = causeDirectory.userGraph();
        while(resultSet.next()){
            pie.setValue(resultSet.getString("Country"), Integer.valueOf(resultSet.getString("Unit")));          
        }
        PieSectionLabelGenerator gen = new StandardPieSectionLabelGenerator(
                   "{0}: {1} ({2})", new DecimalFormat("0"), new DecimalFormat("0%"));

        JFreeChart chart = ChartFactory.createPieChart("Receiver Demographics", pie);
        chart.setBackgroundPaint(Color.white );
        PiePlot piePlot1 =  (PiePlot) chart.getPlot();
        piePlot1.setLabelGenerator(gen);
        ChartFrame frame = new ChartFrame("Pie Chart",chart);
        ChartPanel CP = new ChartPanel(chart);
        System.out.println("CHARTTTTTT");
        chartPanel3.add(CP);
        chartPanel3.updateUI();
        chartPanel3.setPreferredSize(new Dimension(400, 200));   
        chartPanel3.setVisible(true);
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
        chartPanel2 = new javax.swing.JPanel();
        chartPanel3 = new javax.swing.JPanel();

        setBackground(new java.awt.Color(255, 255, 255));
        setForeground(new java.awt.Color(255, 255, 255));

        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.LINE_AXIS));

        chartPanel2.setLayout(new javax.swing.BoxLayout(chartPanel2, javax.swing.BoxLayout.LINE_AXIS));

        chartPanel3.setLayout(new javax.swing.BoxLayout(chartPanel3, javax.swing.BoxLayout.LINE_AXIS));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(chartPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56))
            .addGroup(layout.createSequentialGroup()
                .addGap(190, 190, 190)
                .addComponent(chartPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(227, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addComponent(chartPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 111, Short.MAX_VALUE)
                .addComponent(chartPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel chartPanel2;
    private javax.swing.JPanel chartPanel3;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
