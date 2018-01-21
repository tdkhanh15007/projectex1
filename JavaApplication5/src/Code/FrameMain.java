/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Code;

import controlpack.CustomerBean;
import controlpack.UserBean;
import java.awt.GraphicsEnvironment;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author OS10
 */
public class FrameMain extends javax.swing.JFrame {
    public static String name,role;

    public FrameMain() {
        initComponents();
        ChildAdmin ca = new ChildAdmin(this);
        if(role=="false"){
            bntCash.setVisible(false);
            bntReport.setVisible(false);
            bntHumans.setVisible(false);
        }
        labelImage.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        labelImage.setVerticalAlignment((int) BOTTOM_ALIGNMENT);
        //ngày hiện tại
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date today = Calendar.getInstance().getTime();
        String reportDate = df.format(today);
        txtTimenow.setText(reportDate);
        //fullscreen
        this.setResizable(false);
        this.setSize(getMaxWidth(), getMaxHeight());
        pnMain.setSize(getMaxWidth(), getMaxHeight());
        pnMain.add(ca);
        pnMain.updateUI();
        setLocationRelativeTo(null);
//        LoginPanel lg =  new LoginPanel();
//        JPanel jPanel1 = new JPanel();
//        jPanel1.add(lg);
    }
    public int getMaxWidth() {
          return GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width;
     }
     public int getMaxHeight() {
         return GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height;
     }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnMain = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        labelImage = new javax.swing.JLabel();
        bntHome = new javax.swing.JButton();
        bntLogin = new javax.swing.JButton();
        bntInfo = new javax.swing.JButton();
        txtOrder = new javax.swing.JButton();
        bntReport = new javax.swing.JButton();
        bntHumans = new javax.swing.JButton();
        bntCash = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtTimenow = new javax.swing.JLabel();
        txtUser = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Trebuchet MS", 2, 14)); // NOI18N
        jTextArea1.setForeground(new java.awt.Color(0, 0, 255));
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setText("The Product was developed by Group 3 of Aptech CUSC\nCopyright 2018\nContact: group3@gmail.com\nPhone: 01234567890");
        jScrollPane2.setViewportView(jTextArea1);

        labelImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/MyImages/logo.jpg"))); // NOI18N

        javax.swing.GroupLayout pnMainLayout = new javax.swing.GroupLayout(pnMain);
        pnMain.setLayout(pnMainLayout);
        pnMainLayout.setHorizontalGroup(
            pnMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnMainLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
            .addGroup(pnMainLayout.createSequentialGroup()
                .addContainerGap(123, Short.MAX_VALUE)
                .addComponent(labelImage)
                .addContainerGap(123, Short.MAX_VALUE))
        );
        pnMainLayout.setVerticalGroup(
            pnMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnMainLayout.createSequentialGroup()
                .addGap(97, 97, 97)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(labelImage)
                .addContainerGap(485, Short.MAX_VALUE))
        );

        bntHome.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        bntHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icon-home-red-30.png"))); // NOI18N
        bntHome.setText("Home");
        bntHome.setMinimumSize(new java.awt.Dimension(150, 39));
        bntHome.setPreferredSize(new java.awt.Dimension(120, 39));
        bntHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntHomeActionPerformed(evt);
            }
        });

        bntLogin.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        bntLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8-exit-30.png"))); // NOI18N
        bntLogin.setText("Logout");
        bntLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntLoginActionPerformed(evt);
            }
        });

        bntInfo.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        bntInfo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/MyImages/icons8-about-30.png"))); // NOI18N
        bntInfo.setText("About");
        bntInfo.setMinimumSize(new java.awt.Dimension(150, 39));
        bntInfo.setPreferredSize(new java.awt.Dimension(120, 39));
        bntInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntInfoActionPerformed(evt);
            }
        });

        txtOrder.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        txtOrder.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8-shopping-cart-50_1.png"))); // NOI18N
        txtOrder.setText("Order");
        txtOrder.setMinimumSize(new java.awt.Dimension(150, 39));
        txtOrder.setPreferredSize(new java.awt.Dimension(120, 39));
        txtOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtOrderActionPerformed(evt);
            }
        });

        bntReport.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        bntReport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8-bar-chart-30.png"))); // NOI18N
        bntReport.setText("Report");
        bntReport.setMinimumSize(new java.awt.Dimension(150, 39));
        bntReport.setPreferredSize(new java.awt.Dimension(120, 39));
        bntReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntReportActionPerformed(evt);
            }
        });

        bntHumans.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        bntHumans.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8-profile-30.png"))); // NOI18N
        bntHumans.setText("Humans");
        bntHumans.setMinimumSize(new java.awt.Dimension(150, 39));
        bntHumans.setPreferredSize(new java.awt.Dimension(120, 39));
        bntHumans.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntHumansActionPerformed(evt);
            }
        });

        bntCash.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        bntCash.setIcon(new javax.swing.ImageIcon(getClass().getResource("/MyImages/icons8-get-cash-30.png"))); // NOI18N
        bntCash.setText("Cash");
        bntCash.setMinimumSize(new java.awt.Dimension(150, 39));
        bntCash.setPreferredSize(new java.awt.Dimension(120, 39));
        bntCash.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntCashActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabel2.setText("Client Time:");

        txtTimenow.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        txtTimenow.setText("jLabel3");

        txtUser.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        txtUser.setText(""+name);

        jLabel6.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabel6.setText("User:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bntHome, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bntReport, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bntHumans, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bntCash, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bntInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bntLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pnMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTimenow)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtUser)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bntHome, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bntReport, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bntInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bntLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bntHumans, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bntCash, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtTimenow)
                    .addComponent(txtUser)
                    .addComponent(jLabel6))
                .addGap(4, 4, 4)
                .addComponent(pnMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bntLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntLoginActionPerformed
        // Tat form hien tai
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        dispose();

        // Hien form login
        FormLogin fLogin = new FormLogin();
        fLogin.setVisible(true);
    }//GEN-LAST:event_bntLoginActionPerformed

    private void bntHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntHomeActionPerformed
        pnMain.removeAll();
        ChildAdmin ca = new ChildAdmin(this);
        ca.setSize(pnMain.getWidth(), pnMain.getHeight());
        pnMain.add(ca);
        pnMain.updateUI();
    }//GEN-LAST:event_bntHomeActionPerformed

    private void bntInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntInfoActionPerformed
        pnMain.removeAll();
        InfoPanel rp = new InfoPanel();
        rp.setSize(pnMain.getWidth(), pnMain.getHeight());
        pnMain.add(rp);
        pnMain.updateUI();
    }//GEN-LAST:event_bntInfoActionPerformed

    private void txtOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtOrderActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtOrderActionPerformed

    private void bntReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntReportActionPerformed
        pnMain.removeAll();
        ReportsPn rp = new ReportsPn();
        rp.setSize(pnMain.getWidth(), pnMain.getHeight());
        pnMain.add(rp);
        pnMain.updateUI();
    }//GEN-LAST:event_bntReportActionPerformed

    private void bntHumansActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntHumansActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bntHumansActionPerformed

    private void bntCashActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntCashActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bntCashActionPerformed

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrameMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //lấy username,role
        name = args[0];
        role = args[1];        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrameMain().setVisible(true);
            }
        });
    }

    public void setPanel(JPanel panel) {
        panel.setSize(pnMain.getWidth(), pnMain.getHeight());
        // panel.setVisible(true);
        pnMain.removeAll();
        pnMain.add(panel);
        pnMain.updateUI();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bntCash;
    private javax.swing.JButton bntHome;
    private javax.swing.JButton bntHumans;
    private javax.swing.JButton bntInfo;
    private javax.swing.JButton bntLogin;
    private javax.swing.JButton bntReport;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel labelImage;
    private javax.swing.JPanel pnMain;
    private javax.swing.JButton txtOrder;
    private javax.swing.JLabel txtTimenow;
    private javax.swing.JLabel txtUser;
    // End of variables declaration//GEN-END:variables
}
