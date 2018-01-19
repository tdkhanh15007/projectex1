/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Code;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author OS10
 */
public class ChildAdmin extends javax.swing.JPanel {

    /**
     * Creates new form ChildAdmin
     */
    FrameMain fMain;

    public ChildAdmin(FrameMain fM) {
        initComponents();
        fMain = fM;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbChild = new javax.swing.JTable();
        bntSelect = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        bntInsertChild = new javax.swing.JButton();
        txtSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbCus = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tbNanny = new javax.swing.JTable();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tbEmployee = new javax.swing.JTable();
        bntInsert = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();

        tbChild.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Full Name", "Birth Day", "Current Medications", "Pass Illess", "Doctor", "Emai Parents ", "Gender"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(tbChild);
        if (tbChild.getColumnModel().getColumnCount() > 0) {
            tbChild.getColumnModel().getColumn(2).setMinWidth(120);
        }

        bntSelect.setText("Select");
        bntSelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntSelectActionPerformed(evt);
            }
        });

        jButton2.setText("Update");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Delete");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        bntInsertChild.setText("Insert");
        bntInsertChild.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntInsertChildActionPerformed(evt);
            }
        });

        btnSearch.setText("jButton1");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 569, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtSearch)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSearch)
                .addGap(18, 18, 18)
                .addComponent(bntSelect, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(bntInsertChild, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addGap(15, 15, 15)
                .addComponent(jButton3)
                .addGap(34, 34, 34))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bntSelect)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(bntInsertChild)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch))
                .addGap(0, 23, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Children", jPanel1);

        tbCus.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Email", "Full Name", "Address", "Phone", "Contact 2nd", "Contact 3rd", "Work Phone", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane5.setViewportView(tbCus);

        jButton4.setText("Insert");

        jButton5.setText("Update");

        jButton6.setText("Delete");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 569, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(149, 149, 149)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jButton5)
                .addGap(27, 27, 27)
                .addComponent(jButton6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(jButton5)
                    .addComponent(jButton6))
                .addGap(0, 23, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Customer", jPanel2);

        tbNanny.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Address", "Phone", "Activity", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane6.setViewportView(tbNanny);

        jButton7.setText("Insert");

        jButton8.setText("Update");

        jButton9.setText("Delete");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 569, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(149, 149, 149)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jButton8)
                .addGap(27, 27, 27)
                .addComponent(jButton9)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton7)
                    .addComponent(jButton8)
                    .addComponent(jButton9))
                .addGap(0, 23, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Nanny", jPanel3);

        tbEmployee.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Username", "Password", "Phone", "Role", "Name"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane7.setViewportView(tbEmployee);

        bntInsert.setText("Insert");
        bntInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntInsertActionPerformed(evt);
            }
        });

        jButton11.setText("Update");

        jButton12.setText("Delete");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 569, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(149, 149, 149)
                .addComponent(bntInsert, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jButton11)
                .addGap(27, 27, 27)
                .addComponent(jButton12)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bntInsert)
                    .addComponent(jButton11)
                    .addComponent(jButton12))
                .addGap(0, 23, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Employee", jPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void bntInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntInsertActionPerformed

    }//GEN-LAST:event_bntInsertActionPerformed

    private void bntSelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntSelectActionPerformed
        
        DefaultTableModel tbModel = (DefaultTableModel) tbChild.getModel();
        tbModel.setRowCount(0);

        RegionBeanChild rbean = new RegionBeanChild();
        ArrayList<RegionBeanChild> listRegion = rbean.showAllData();
        Vector v;

        for (RegionBeanChild rb : listRegion) {
            v = new Vector();
            v.add(rb.getFullName());
            v.add(rb.getBirth());
            v.add(rb.getCurrent_medications());
            v.add(rb.getPass_illess());
            v.add(rb.getDoctor());
            v.add(rb.getCus_email());
            v.add(rb.isGender());
            tbModel.addRow(v);
        }
    }//GEN-LAST:event_bntSelectActionPerformed

    private void bntInsertChildActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntInsertChildActionPerformed
        JFrame fInsertChild = new JFrame();
        fInsertChild.add(new InsertChild());
        
        fInsertChild.pack();
        fInsertChild.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        fInsertChild.setVisible(true);
    }//GEN-LAST:event_bntInsertChildActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // Lay dong hien tai dang chon trong bang
        DefaultTableModel tbModel = (DefaultTableModel) tbChild.getModel();
        int currentRow = tbChild.getSelectedRow();

        // Lay du lieu trong dong
        String fName = tbModel.getValueAt(currentRow, 0).toString();
        String lName = tbModel.getValueAt(currentRow, 1).toString();
        String mName = tbModel.getValueAt(currentRow, 2).toString();
        Date bDay = null;
        String strDate = tbModel.getValueAt(currentRow, 3).toString();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            bDay = sdf.parse(strDate);
        } catch (ParseException ex) {
            Logger.getLogger(ChildAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String cMedi = tbModel.getValueAt(currentRow, 4).toString();
        String pIless = tbModel.getValueAt(currentRow, 5).toString();
        String dTor = tbModel.getValueAt(currentRow, 6).toString();
        String eMail = tbModel.getValueAt(currentRow, 7).toString();
        String gDer = tbModel.getValueAt(currentRow, 8).toString();

        // Tao form insert moi
        // Truyen tham so qua form moi
        RegionBeanChild rbc = new RegionBeanChild(fName, new java.sql.Date(bDay.getTime()), cMedi, pIless, dTor, eMail, true);
        UpdateChild ic = new UpdateChild(rbc);
        ic.setVisible(true);

        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        
        // Lay dong hien tai dang chon
        int selectedRow = tbChild.getSelectedRow();
        DefaultTableModel tbModel = (DefaultTableModel) tbChild.getModel();
        
        // Lay email
        String cusMail = tbModel.getValueAt(selectedRow, 7).toString();
        
        String sqlQuery = "update Chirldren set status = 0 where cus_email = ?";
        
        
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        
// Lay thogn tin người dung nhap vao
        String keyword = txtSearch.getText();
        
        // VIet SQL truy van vao CSDL
        String sqlQuery = "select *\n" +
                        "from Chirldren\n" +
                        "where fullname like ?\n" +
                        "or cus_email like ?";
        PreparedStatement ps;
        ResultSet rs;
        
        try {
            ps = ConnectionData.getConnection().prepareStatement(sqlQuery);
            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");
            rs = ps.executeQuery();
            
            // Lay ket qua tu sql
            Vector vRow;
            ArrayList<Vector> arylstRow = new ArrayList<>();
            
            DefaultTableModel tbModel = (DefaultTableModel) tbChild.getModel();
            tbModel.setRowCount(0);
            
            while (rs.next()) {
                vRow = new Vector();
                vRow.add(rs.getString("fullname"));
                vRow.add(rs.getString("birth"));
                vRow.add(rs.getString("current_medications"));
                vRow.add(rs.getString("pass_illess"));
                vRow.add(rs.getString("doctor"));
                vRow.add(rs.getString("cus_email"));
                vRow.add(rs.getString("gender"));
                
                // Dua dong nay vao bang
                tbModel.addRow(vRow);
            }

        } catch (Exception e) {
            // THong bao loi
           JOptionPane.showMessageDialog(null, "Khong tim thay");
            return;
        }
        
        
    }//GEN-LAST:event_btnSearchActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bntInsert;
    private javax.swing.JButton bntInsertChild;
    private javax.swing.JButton bntSelect;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tbChild;
    private javax.swing.JTable tbCus;
    private javax.swing.JTable tbEmployee;
    private javax.swing.JTable tbNanny;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
