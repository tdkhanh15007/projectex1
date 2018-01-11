/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Code;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author OS10
 */
public class LoginPanel extends javax.swing.JPanel {

    /**
     * Creates new form LoginPanel
     */
    FrameMain fMain;
    JFrame jFrame;
    public LoginPanel(FrameMain fM, JFrame jF) {
        initComponents();
        fMain = fM;
        jFrame = jF;
    }



    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbLogin = new javax.swing.JLabel();
        lbUser = new javax.swing.JLabel();
        lbPass = new javax.swing.JLabel();
        txtUser = new javax.swing.JTextField();
        txtPass = new javax.swing.JPasswordField();
        bntOk = new javax.swing.JButton();
        bntExit = new javax.swing.JButton();
        bntReset = new javax.swing.JButton();

        lbLogin.setFont(new java.awt.Font("Times New Roman", 0, 30)); // NOI18N
        lbLogin.setText("Login");

        lbUser.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        lbUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/iconuser20.png"))); // NOI18N
        lbUser.setText("Username:");

        lbPass.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        lbPass.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icon-password-20.png"))); // NOI18N
        lbPass.setText("Password:");

        txtPass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPassActionPerformed(evt);
            }
        });

        bntOk.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        bntOk.setText("Ok");
        bntOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntOkActionPerformed(evt);
            }
        });

        bntExit.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        bntExit.setText("Exit");
        bntExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntExitActionPerformed(evt);
            }
        });

        bntReset.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        bntReset.setText("Reset");
        bntReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntResetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(lbUser)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(bntOk, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbPass)))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGap(81, 81, 81)
                            .addComponent(bntReset, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(bntExit, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lbLogin)
                        .addGap(75, 75, 75)))
                .addContainerGap(101, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(lbLogin)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbUser)
                    .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbPass)
                    .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bntOk)
                    .addComponent(bntReset)
                    .addComponent(bntExit))
                .addContainerGap(48, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtPassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPassActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPassActionPerformed

    private void bntOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntOkActionPerformed
        
        LoginService lg = new LoginService();
        String username = txtUser.getText();
        String password = txtPass.getText();
        
        if (lg.loginCheck(username, password)) {
            ChildAdmin ca = new ChildAdmin(fMain);
            fMain.setPanel(ca);
            jFrame.dispose();
        }
        
    }//GEN-LAST:event_bntOkActionPerformed
    public class LoginService {

        public boolean loginCheck(String username, String password) {

            String query;
            String dbUsername, dbPassword;
            boolean login = false;

            try {
                Connection conn = ConnectionData.getConnection();

                query = "SELECT * FROM [User] where user_email = ? and password = ?";
                PreparedStatement pstm = conn.prepareStatement(query);
                pstm.setString(1, username);
                pstm.setString(2, password);
                
                ResultSet rs = pstm.executeQuery();

                if (rs.next()) {
                    JOptionPane.showMessageDialog(null, "Login success");
                    login = true;
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid username or password");
                }
            } catch (SQLException ex) {
                Logger.getLogger(LoginPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            return login;
        }
    }


    private void bntExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_bntExitActionPerformed

    private void bntResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntResetActionPerformed
        txtUser.setText(null);
        txtPass.setText(null);
    }//GEN-LAST:event_bntResetActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bntExit;
    private javax.swing.JButton bntOk;
    private javax.swing.JButton bntReset;
    private javax.swing.JLabel lbLogin;
    private javax.swing.JLabel lbPass;
    private javax.swing.JLabel lbUser;
    private javax.swing.JPasswordField txtPass;
    private javax.swing.JTextField txtUser;
    // End of variables declaration//GEN-END:variables
}
