/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Code;

import MyDatabase.MyDatabase;
import controlpack.ActBean;
import controlpack.MainMethod;
import controlpack.NannyBean;
import controlpack.UserBean;
import java.awt.Font;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Khanh
 */
public class HumansPn extends javax.swing.JPanel {

    MainMethod Mmt = new MainMethod();
    UserBean us = new UserBean();
    NannyBean nb = new NannyBean();
    ActBean acb = new ActBean();
    MyDatabase myData = new MyDatabase();
    FrameMain fm;

    /**
     * Creates new form HumansPn
     */
    public HumansPn(FrameMain fM) {
        fm = fM;
        initComponents();
        hiddenAll();
        hiddenNUp();
        loadUser();
        loadNanny();
        loadAct();
        tbUser.getTableHeader().setFont(new Font("Trebuchet MS", Font.BOLD, 14));
        tbNanny.getTableHeader().setFont(new Font("Trebuchet MS", Font.BOLD, 14));
    }

    public void loadUser() {
        DefaultTableModel model = (DefaultTableModel) tbUser.getModel();
        model.setRowCount(0);
        Vector<UserBean> item = us.displayAll();
        Vector v;
        for (int i = 0; i < item.size(); i++) {
            v = new Vector();
            UserBean temp = item.get(i);
            String email = temp.user;
            String name = temp.ussname;
            String phone = temp.phone;
            boolean role = temp.role;
            String strRole = "";
            if (role) {
                strRole = "Admin";
            } else {
                strRole = "Employee";
            }
            v.add(email);
            v.add(name);
            v.add(phone);
            v.add(strRole);
            model.addRow(v);
        }
    }

    public void loadNanny() {
        DefaultTableModel model = (DefaultTableModel) tbNanny.getModel();
        model.setRowCount(0);
        Vector<NannyBean> item = nb.loadAll();
        Vector v;
        int idFake = 0;
        for (int i = 0; i < item.size(); i++) {
            idFake++;
            v = new Vector();
            NannyBean temp = item.get(i);
            int id = temp.nanny_id;
            String name = temp.nanny_name;
            String phone = temp.phone;
            String act = temp.act_name;
            String strStatus = "Working";
            if (!temp.status) {
                strStatus = "Quit";
            }
            v.add(idFake);
            v.add(name);
            v.add(phone);
            v.add(act);
            v.add(strStatus);
            model.addRow(v);
        }
        jScrollPane2.setVisible(true);
    }

    public void seachNanny(String character) {
        DefaultTableModel model = (DefaultTableModel) tbNanny.getModel();
        model.setRowCount(0);
        Vector<NannyBean> item = nb.searchNanny(character);
        Vector v;
        int idFake = 0;
        for (int i = 0; i < item.size(); i++) {
            idFake++;
            v = new Vector();
            NannyBean temp = item.get(i);
            int id = temp.nanny_id;
            String name = temp.nanny_name;
            String phone = temp.phone;
            String act = temp.act_name;
            String strStatus = "Working";
            if (!temp.status) {
                strStatus = "Quit";
            }
            v.add(idFake);
            v.add(name);
            v.add(phone);
            v.add(act);
            v.add(strStatus);
            model.addRow(v);
        }
    }

    public void loadAct() {
        Vector<ActBean> item = acb.displayAll();
        for (int i = 0; i < item.size(); i++) {
            ActBean temp = item.get(i);
            String name = temp.act_type;
            jComboBox2.addItem(name);
        }
    }

    public void hiddenNUp() {
        jScrollPane2.setVisible(false);
        jScrollPane3.setVisible(false);
        jLabel7.setVisible(false);
        jTextField5.setVisible(false);
        jLabel9.setVisible(false);
        jTextField6.setVisible(false);
        jLabel8.setVisible(false);
        jTextArea1.setVisible(false);
        jLabel10.setVisible(false);
        jComboBox2.setVisible(false);
        jButton6.setVisible(false);
        jButton7.setVisible(false);
        jButton8.setVisible(false);

    }

    public void displayNUp() {
        jLabel7.setVisible(true);
        jTextField5.setVisible(true);
        jLabel9.setVisible(true);
        jTextField6.setVisible(true);
        jLabel8.setVisible(true);
        jTextArea1.setVisible(true);
        jLabel10.setVisible(true);
        jComboBox2.setVisible(true);
        jButton6.setVisible(true);
        jButton7.setVisible(true);
        jButton8.setVisible(false);
    }

    public void displayNCr() {
        jLabel7.setVisible(true);
        jTextField5.setVisible(true);
        jLabel9.setVisible(true);
        jTextField6.setVisible(true);
        jLabel8.setVisible(true);
        jTextArea1.setVisible(true);
        jLabel10.setVisible(true);
        jComboBox2.setVisible(true);
        jButton6.setVisible(true);
        jButton7.setVisible(false);
        jButton8.setVisible(true);
    }

    public void hiddenAll() {
        jLabel1.setVisible(false);
        jTextField1.setVisible(false);
        jLabel3.setVisible(false);
        jTextField3.setVisible(false);
        jLabel2.setVisible(false);
        jTextField2.setVisible(false);
        jLabel4.setVisible(false);
        jComboBox1.setVisible(false);
        jLabel5.setVisible(false);
        jPasswordField1.setVisible(false);
        jLabel6.setVisible(false);
        jPasswordField2.setVisible(false);
        jButton1.setVisible(false);
        jButton2.setVisible(false);
        jButton3.setVisible(false);
    }

    public void displaytoUp() {
        jLabel1.setVisible(false);
        jTextField1.setVisible(false);
        jLabel3.setVisible(true);
        jTextField3.setVisible(true);
        jLabel2.setVisible(true);
        jTextField2.setVisible(true);
        jLabel4.setVisible(true);
        jComboBox1.setVisible(true);
        jLabel5.setVisible(true);
        jPasswordField1.setVisible(true);
        jLabel6.setVisible(true);
        jPasswordField2.setVisible(true);
        jButton1.setVisible(true);
        jButton2.setVisible(true);
        jButton3.setVisible(true);
    }

    public void displaytoNew() {
        jLabel1.setVisible(true);
        jTextField1.setVisible(true);
        jLabel3.setVisible(true);
        jTextField3.setVisible(true);
        jLabel2.setVisible(true);
        jTextField2.setVisible(true);
        jLabel4.setVisible(true);
        jComboBox1.setVisible(true);
        jLabel5.setVisible(true);
        jPasswordField1.setVisible(true);
        jLabel6.setVisible(true);
        jPasswordField2.setVisible(true);
        jButton1.setVisible(true);
        jButton2.setVisible(false);
        jButton3.setVisible(true);
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
        panel1 = new java.awt.Panel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbUser = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        jLabel6 = new javax.swing.JLabel();
        jPasswordField2 = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        panel2 = new java.awt.Panel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbNanny = new javax.swing.JTable();
        txtSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jComboBox2 = new javax.swing.JComboBox();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        jTabbedPane1.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N

        panel1.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        panel1.setForeground(new java.awt.Color(102, 0, 102));

        tbUser.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        tbUser.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "User", "Name", "Phone", "Role"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbUserMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbUser);
        if (tbUser.getColumnModel().getColumnCount() > 0) {
            tbUser.getColumnModel().getColumn(0).setResizable(false);
            tbUser.getColumnModel().getColumn(1).setResizable(false);
            tbUser.getColumnModel().getColumn(2).setResizable(false);
            tbUser.getColumnModel().getColumn(3).setResizable(false);
        }

        jLabel1.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 0, 102));
        jLabel1.setText("User:");

        jLabel2.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 0, 102));
        jLabel2.setText("Name:");

        jTextField1.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jTextField1.setForeground(new java.awt.Color(102, 0, 102));

        jTextField2.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jTextField2.setForeground(new java.awt.Color(102, 0, 102));

        jLabel3.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 0, 102));
        jLabel3.setText("Phone:");

        jTextField3.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jTextField3.setForeground(new java.awt.Color(102, 0, 102));

        jLabel4.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 0, 102));
        jLabel4.setText("Role:");

        jComboBox1.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jComboBox1.setForeground(new java.awt.Color(102, 0, 102));
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Employee", "Admin" }));

        jLabel5.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 0, 102));
        jLabel5.setText("New Password:");

        jPasswordField1.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jPasswordField1.setForeground(new java.awt.Color(102, 0, 102));

        jLabel6.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 0, 102));
        jLabel6.setText("Comfirm Password:");

        jPasswordField2.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jPasswordField2.setForeground(new java.awt.Color(102, 0, 102));

        jButton1.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(102, 0, 102));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/MyImages/icons8-save-30.png"))); // NOI18N
        jButton1.setText("Save");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(102, 0, 102));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/MyImages/icons8-theatre-mask-30.png"))); // NOI18N
        jButton2.setText("Set Role");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jButton3.setForeground(new java.awt.Color(102, 0, 102));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/MyImages/icons8-reset-30 -p.png"))); // NOI18N
        jButton3.setText("Clear");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jButton4.setForeground(new java.awt.Color(102, 0, 102));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/MyImages/icons8-add-new-30-pi.png"))); // NOI18N
        jButton4.setText("Add Employee");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1))
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGap(204, 204, 204)
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField1)
                            .addComponent(jTextField2)
                            .addComponent(jPasswordField1, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE))
                        .addGap(41, 41, 41)
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPasswordField2, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 246, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(panel1Layout.createSequentialGroup()
                .addGap(331, 331, 331)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addGap(18, 18, 18)
                .addComponent(jButton3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4))
                .addGap(18, 18, 18)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6)
                        .addComponent(jPasswordField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(59, 59, 59)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addContainerGap(220, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Employees", panel1);

        tbNanny.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        tbNanny.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Nanny ID", "Name", "Phone", "Activity", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbNanny.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbNannyMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbNanny);

        txtSearch.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        txtSearch.setForeground(new java.awt.Color(204, 102, 0));

        btnSearch.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        btnSearch.setForeground(new java.awt.Color(204, 102, 0));
        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/MyImages/icons8-search-15-or.png"))); // NOI18N
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(204, 102, 0));
        jLabel7.setText("Name:");

        jLabel8.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(204, 102, 0));
        jLabel8.setText("Address:");

        jLabel9.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(204, 102, 0));
        jLabel9.setText("Phone:");

        jLabel10.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(204, 102, 0));
        jLabel10.setText("Activity:");

        jTextField5.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jTextField5.setForeground(new java.awt.Color(204, 102, 0));

        jTextField6.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jTextField6.setForeground(new java.awt.Color(204, 102, 0));

        jComboBox2.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jComboBox2.setForeground(new java.awt.Color(204, 102, 0));

        jButton6.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jButton6.setForeground(new java.awt.Color(204, 102, 0));
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/MyImages/icons8-save-30.png"))); // NOI18N
        jButton6.setText("Save");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jButton7.setForeground(new java.awt.Color(204, 102, 0));
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/MyImages/icons8-transfer-30.png"))); // NOI18N
        jButton7.setText("Set Status");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jButton8.setForeground(new java.awt.Color(204, 102, 0));
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/MyImages/icons8-reset-30 -or.png"))); // NOI18N
        jButton8.setText("Clear");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jButton9.setForeground(new java.awt.Color(204, 102, 0));
        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/MyImages/icons8-add-new-30-or.png"))); // NOI18N
        jButton9.setText("Add Nanny");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane3.setViewportView(jTextArea1);

        javax.swing.GroupLayout panel2Layout = new javax.swing.GroupLayout(panel2);
        panel2.setLayout(panel2Layout);
        panel2Layout.setHorizontalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 778, Short.MAX_VALUE)
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel2Layout.createSequentialGroup()
                                .addComponent(jButton6)
                                .addGap(18, 18, 18)
                                .addComponent(jButton7)
                                .addGap(18, 18, 18)
                                .addComponent(jButton8))
                            .addGroup(panel2Layout.createSequentialGroup()
                                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField5, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                    .addComponent(jScrollPane3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField6, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                                    .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panel2Layout.setVerticalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtSearch)
                            .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jButton9))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7)
                    .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9)
                        .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(jLabel10)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton6)
                    .addComponent(jButton7)
                    .addComponent(jButton8))
                .addGap(27, 27, 27))
        );

        jTabbedPane1.addTab("Nannies", panel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        jTextField1.setText("");
        jPasswordField1.setText("");
        jPasswordField2.setText("");
        jTextField3.setText("");
        jTextField2.setText("");
        displaytoNew();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        jTextField5.setText("");
        jTextField6.setText("");
        jTextArea1.setText("");
        jComboBox2.setSelectedIndex(0);
        jScrollPane3.setVisible(true);
        displayNCr();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void tbUserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbUserMouseClicked
        DefaultTableModel tbModel = (DefaultTableModel) tbUser.getModel();
        int currentRow = tbUser.getSelectedRow();
        String phone = tbModel.getValueAt(currentRow, 2).toString();
        String name = tbModel.getValueAt(currentRow, 1).toString();
        String strrole = tbModel.getValueAt(currentRow, 3).toString();
        if (strrole.equals("Admin")) {
            jComboBox1.setSelectedIndex(1);
        } else {
            jComboBox1.setSelectedIndex(0);
        }
        jPasswordField1.setText("");
        jPasswordField2.setText("");
        jTextField3.setText(phone);
        jTextField2.setText(name);
        displaytoUp();
        if (tbModel.getValueAt(currentRow, 0).toString().equals(fm.name())) {
            jComboBox1.setVisible(false);
            jLabel4.setVisible(false);
        }
        jButton3.setVisible(false);
    }//GEN-LAST:event_tbUserMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        DefaultTableModel tbModel = (DefaultTableModel) tbUser.getModel();
        int currentRow = tbUser.getSelectedRow();
        String email = tbModel.getValueAt(currentRow, 0).toString();
        String strRole = tbModel.getValueAt(currentRow, 3).toString();
        boolean newRole = true;
        if (strRole.equals("Admin")) {
            newRole = false;
        }
        us.quickSet(email, newRole);
        hiddenAll();
        loadUser();
    }//GEN-LAST:event_jButton2ActionPerformed
public boolean checkFone(String phone) {
        if (phone.startsWith("0") && phone.length() > 9 && phone.length() < 12) {
            return true;
        }
        return false;
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (myData.checkPat(jTextField3.getText())) {
            JOptionPane.showMessageDialog(this, "Phone invalid!");
        } else if(!checkFone(jTextField3.getText())){
            JOptionPane.showMessageDialog(this, "Phone invalid!");
        }
        
        
        else if (jTextField2.getText().equals("") || jTextField2.getText().equals(null)) {
            JOptionPane.showMessageDialog(this, "Name can not be blank!");
        } else {
            if (jTextField1.isVisible()) {
                if (jTextField1.getText().equals("") || jTextField1.getText().equals(null)) {
                    JOptionPane.showMessageDialog(this, "User can not be blank!");
                } else {
                    String pass1 = jPasswordField1.getText();
                    String pass2 = jPasswordField2.getText();
                    if (us.repeatPass(pass1, pass2)) {
                        String pass = us.covertoMD5(pass1);
                        boolean role = true;
                        if (jComboBox1.getSelectedIndex() == 0) {
                            role = false;
                        }
                        if (!us.createUser(jTextField1.getText(), jTextField2.getText(), jTextField3.getText(), pass, role)) {
                            JOptionPane.showMessageDialog(this, "User is exist!", "Warning", JOptionPane.WARNING_MESSAGE);
                        } else {
                            loadUser();
                            hiddenAll();
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Password and Comfirm Password does not match!!", "Warning", JOptionPane.WARNING_MESSAGE);
                    }
                }

            } else {
                DefaultTableModel tbModel = (DefaultTableModel) tbUser.getModel();
                int currentRow = tbUser.getSelectedRow();
                String email = tbModel.getValueAt(currentRow, 0).toString();
                String phone = jTextField3.getText();
                String name = jTextField2.getText();
                boolean role = true;
                if (jComboBox1.getSelectedIndex() == 0) {
                    role = false;
                }
                if (jPasswordField1.getText().equals("") && jPasswordField2.getText().equals("")) {
                    String pass = us.md5Pass(tbModel.getValueAt(currentRow, 0).toString());
                    us.updateUser(name, phone, role, pass, email);
                    hiddenAll();
                    loadUser();
                } else if (jTextField1.getText().equals("") || jTextField1.getText().equals(null)) {
                    JOptionPane.showMessageDialog(this, "User can not be blank!");
                } else {
                    if (us.repeatPass(jPasswordField1.getText(), jPasswordField2.getText())) {
                        String pass = us.covertoMD5(jPasswordField1.getText());
                        us.updateUser(name, phone, role, pass, email);
                        hiddenAll();
                        loadUser();
                    } else {
                        JOptionPane.showMessageDialog(this, "Password and Comfirm Password does not match!!", "Warning", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        jTextField1.setText("");
        jTextField3.setText("");
        jTextField2.setText("");
        jComboBox1.setSelectedIndex(0);
        jPasswordField1.setText("");
        jPasswordField2.setText("");
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        jScrollPane2.setVisible(true);
        if (!txtSearch.getText().equals("") || !txtSearch.getText().equals(null)) {
            seachNanny(txtSearch.getText());
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    private void tbNannyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbNannyMouseClicked
        jScrollPane3.setVisible(true);
        DefaultTableModel tbModel = (DefaultTableModel) tbNanny.getModel();
        int currentRow = tbNanny.getSelectedRow();
        jTextField5.setText(tbModel.getValueAt(currentRow, 1).toString());
        jTextField6.setText(tbModel.getValueAt(currentRow, 2).toString());
        jTextArea1.setText(nb.getAddr(tbModel.getValueAt(currentRow, 2).toString()));
        jComboBox2.setSelectedItem(tbModel.getValueAt(currentRow, 3).toString());
        displayNUp();
    }//GEN-LAST:event_tbNannyMouseClicked

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        DefaultTableModel tbModel = (DefaultTableModel) tbNanny.getModel();
        int currentRow = tbNanny.getSelectedRow();
        String strstatus = tbModel.getValueAt(currentRow, 4).toString();
        boolean status = true;
        if (strstatus.equals("Working")) {
            status = false;
        }
        String phone = tbModel.getValueAt(currentRow, 2).toString();
        nb.deleteNanny(phone, status);
        loadNanny();
        hiddenNUp();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        jTextField5.setText("");
        jTextField6.setText("");
        jTextArea1.setText("");
        jComboBox2.setSelectedIndex(0);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        if (jButton8.isVisible()) {
            int actID = acb.nametoID(jComboBox2.getSelectedItem().toString());
            if (nb.createNanny(jTextField5.getText(), jTextArea1.getText(), jTextField6.getText(), actID)) {
                hiddenNUp();
                loadNanny();
            } else {
                JOptionPane.showMessageDialog(this, "Nanny is exist!", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            DefaultTableModel tbModel = (DefaultTableModel) tbNanny.getModel();
            int currentRow = tbNanny.getSelectedRow();
            int actID = acb.nametoID(tbModel.getValueAt(currentRow, 3).toString());
            int nnID = nb.phonetoID(tbModel.getValueAt(currentRow, 2).toString());
            if (nb.updateNanny(jTextField5.getText(), jTextArea1.getText(), jTextField6.getText(), actID, nnID)) {
                hiddenNUp();
                loadNanny();
            } else {
                JOptionPane.showMessageDialog(this, "Phonenumber in used!", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButton6ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JPasswordField jPasswordField2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private java.awt.Panel panel1;
    private java.awt.Panel panel2;
    private javax.swing.JTable tbNanny;
    private javax.swing.JTable tbUser;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
