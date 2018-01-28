/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Code;

import MyDatabase.MyDatabase;
import controlpack.ActBean;
import controlpack.ChildrenBean;
import controlpack.CustomerBean;
import controlpack.GroupsBean;
import controlpack.MainMethod;
import controlpack.NannyBean;
import controlpack.OrderBean;
import java.awt.Color;
import java.awt.Font;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author OS10
 */
public class ChildAdmin extends javax.swing.JPanel {

    DefaultTableModel dm;
    int child_id = 0;
    MyDatabase a = new MyDatabase();
    /**
     * Creates new form ChildAdmin
     */
    FrameMain fMain;
    CustomerBean cb = new CustomerBean();
    ChildrenBean chBean = new ChildrenBean();
    MainMethod MMt = new MainMethod();
    GroupsBean gb = new GroupsBean();
    ActBean acb = new ActBean();
    OrderBean ob = new OrderBean();

    public ChildAdmin(FrameMain fM) {
        initComponents();
        fMain = fM;
        btnActive.setVisible(false);
        hiddenBtn();
        loadCus(jCheckBox1.isSelected());
        loadChild();
        tbCus.getTableHeader().setFont(new Font("Trebuchet MS", Font.BOLD, 14));
        tbChild.getTableHeader().setFont(new Font("Trebuchet MS", Font.BOLD, 14));
        loadAct();
        loadTime();
    }

    public void hiddenBtn() {
        btnUpdateChild.setVisible(false);
        btnCreateOrder.setVisible(false);
        btnStatusChild.setVisible(false);
        btnUpdateCus.setVisible(false);
        btnNewChild.setVisible(false);
        jCheckBox1.setVisible(false);
        btnStatusCus.setVisible(false);
        lbNN.setVisible(false);
        cbAddOrderNN.setVisible(false);
    }

    public void displayBtn() {
        btnUpdateCus.setVisible(true);
        btnNewChild.setVisible(true);
        jCheckBox1.setVisible(true);
        btnStatusCus.setVisible(true);
    }

    public void loadCus(boolean check) {
        DefaultTableModel model = (DefaultTableModel) tbCus.getModel();
        model.setRowCount(0);
        Vector<CustomerBean> item = cb.displayAll(check);
        Vector v;
        for (int i = 0; i < item.size(); i++) {
            v = new Vector();
            CustomerBean temp = item.get(i);
            String cus_email = temp.cus_email;
            String cus_name = temp.cus_name;
            String phone = temp.phone_number;
            String workphone = temp.work_phone;
            boolean status = temp.status;
            String statusText = "";
            if (status) {
                statusText = "Active";
            } else {
                statusText = "InActivce";
            }
            v.add(cus_email);
            v.add(cus_name);
            v.add(phone);
            v.add(workphone);
            v.add(statusText);
            model.addRow(v);
        }
    }

    public void searchCus(String character, boolean checked) {
        DefaultTableModel model = (DefaultTableModel) tbCus.getModel();
        model.setRowCount(0);
        Vector<CustomerBean> item = cb.searchCus(character, checked);
        Vector v;
        for (int i = 0; i < item.size(); i++) {
            v = new Vector();
            CustomerBean temp = item.get(i);
            String cus_email = temp.cus_email;
            String cus_name = temp.cus_name;
            String phone = temp.phone_number;
            String workphone = temp.work_phone;
            boolean status = temp.status;
            String statusText = "";
            if (status) {
                statusText = "Active";
            } else {
                statusText = "InActivce";
            }
            v.add(cus_email);
            v.add(cus_name);
            v.add(phone);
            v.add(workphone);
            v.add(statusText);
            model.addRow(v);
        }
    }

    public void loadChild() {
        DefaultTableModel model = (DefaultTableModel) tbChild.getModel();
        model.setRowCount(0);
        Vector<ChildrenBean> item = chBean.displayAll();
        Vector v;
        for (int i = 0; i < item.size(); i++) {
            v = new Vector();
            ChildrenBean temp = item.get(i);
            int child_id = temp.child_id;
            String fullname = temp.fullName;
            String cusemail = temp.cus_email;
            Date birth = temp.birth;
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            String strDate = formatter.format(birth);
            boolean gender = temp.gender;
            String strGen = "";
            if (gender) {
                strGen = "Male";
            } else {
                strGen = "Female";
            }
            v.add(child_id);
            v.add(fullname);
            v.add(strDate);
            v.add(strGen);
            v.add(cusemail);
            model.addRow(v);
        }
    }

    public void searchChild(String character) {
        DefaultTableModel model = (DefaultTableModel) tbChild.getModel();
        model.setRowCount(0);
        Vector<ChildrenBean> item = chBean.searchChild(character);
        Vector v;
        for (int i = 0; i < item.size(); i++) {
            v = new Vector();
            ChildrenBean temp = item.get(i);
            int child_id = temp.child_id;
            String fullname = temp.fullName;
            String cusemail = temp.cus_email;
            Date birth = temp.birth;
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            String strDate = formatter.format(birth);
            boolean gender = temp.gender;
            String strGen = "";
            if (gender) {
                strGen = "Male";
            } else {
                strGen = "Female";
            }
            v.add(child_id);
            v.add(fullname);
            v.add(strDate);
            v.add(strGen);
            v.add(cusemail);
            model.addRow(v);
        }
    }

    public void loadAct() {
        Vector<ActBean> item = acb.displayAll();
        for (int i = 0; i < item.size(); i++) {
            ActBean temp = item.get(i);
            String name = temp.act_type;
            cbAddOrderAct.addItem(name);
        }
    }

    public void loadTime() {
        Vector<OrderBean> item = ob.displayTime();
        for (int i = 0; i < item.size(); i++) {
            OrderBean temp = item.get(i);
            String name = temp.time_slot;
            cbAddOrderTime.addItem(name);
        }
    }

    public void loadNanny(String actName, int timeID, Date fromDay) {
        Vector<OrderBean> item = ob.searchFreeNanny(actName, timeID, fromDay);
        for (int i = 0; i < item.size(); i++) {
            OrderBean temp = item.get(i);
            String name = temp.nanny_name;
            cbAddOrderNN.addItem(name);
            cbAddOrderNN.setVisible(true);
            lbNN.setVisible(true);
        }
    }

    public void clearData() {
        txtAddOrChildID.setText("");
        txtAddOrChildname.setText("");
        Instant now = Instant.now(); //current date
        Instant before = now.plus(Duration.ofDays(1));
        Date dateAfter = Date.from(before);
        dateChooseStart.setDate(new Date());
        datChooseEnd.setDate(dateAfter);
    }

    public void closeDL() {
        dlUpdateChild.dispose();
        dlAddOrder.dispose();
        dlAddCus.dispose();
        dlUpdateCus.dispose();
        dlAddChild.dispose();
        dlComfirmOrder.dispose();
        dlCfCus.dispose();
        dlCfChild.dispose();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dlUpdateChild = new javax.swing.JDialog();
        jLabel8 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtfullname = new javax.swing.JTextField();
        txtPass = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        txtDoctor = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        bntOk = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel7 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        txtCurent = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        dlAddOrder = new javax.swing.JDialog();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtAddOrChildID = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        lbNN = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        txtAddOrChildname = new javax.swing.JTextField();
        txtAddOrderGroup = new javax.swing.JLabel();
        cbAddOrderAct = new javax.swing.JComboBox();
        cbAddOrderTime = new javax.swing.JComboBox();
        dateChooseStart = new com.toedter.calendar.JDateChooser();
        datChooseEnd = new com.toedter.calendar.JDateChooser();
        cbAddOrderNN = new javax.swing.JComboBox();
        jButton9 = new javax.swing.JButton();
        dlAddCus = new javax.swing.JDialog();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        txtNewCusEmail = new javax.swing.JTextField();
        txtNewCusName = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtNewCusAddr = new javax.swing.JTextArea();
        jLabel40 = new javax.swing.JLabel();
        txtNewCusFone = new javax.swing.JTextField();
        txtNewCusWorkFone = new javax.swing.JTextField();
        btnAddCus = new javax.swing.JButton();
        btnClearAddCuss = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        dlUpdateCus = new javax.swing.JDialog();
        jLabel38 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        txtUpdateCus = new javax.swing.JTextField();
        txtUpdateCusName = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtUpdateCusAddress = new javax.swing.JTextArea();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        txtUpdateCusmobile = new javax.swing.JTextField();
        txtUpdateCusWorkP = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        jButton16 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        dlAddChild = new javax.swing.JDialog();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        txtNewChild = new javax.swing.JTextField();
        txtNewChildBirth = new com.toedter.calendar.JDateChooser();
        jLabel48 = new javax.swing.JLabel();
        txtNewChildGender = new javax.swing.JComboBox();
        txtNewChildMedication = new javax.swing.JTextField();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        txtNewChilIlle = new javax.swing.JTextField();
        txtNewChildDoctor = new javax.swing.JTextField();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        txtNewEmailParents = new javax.swing.JTextField();
        bntOk1 = new javax.swing.JButton();
        bntReset1 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        dlComfirmOrder = new javax.swing.JDialog();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        txtChildName = new javax.swing.JLabel();
        txtAct = new javax.swing.JLabel();
        txtTime = new javax.swing.JLabel();
        txtStart = new javax.swing.JLabel();
        txtEnd = new javax.swing.JLabel();
        txtNanny = new javax.swing.JLabel();
        txtPrice = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        txtOrderBy = new javax.swing.JLabel();
        dlCfCus = new javax.swing.JDialog();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        btnCfDelete = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        dlCfChild = new javax.swing.JDialog();
        jLabel55 = new javax.swing.JLabel();
        btnCfDelete1 = new javax.swing.JButton();
        jLabel56 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbChild = new javax.swing.JTable();
        btnUpdateChild = new javax.swing.JButton();
        btnStatusChild = new javax.swing.JButton();
        btnCreateOrder = new javax.swing.JButton();
        txtSearch = new javax.swing.JTextField();
        btnSearchChild = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbCus = new javax.swing.JTable();
        btnNewCus = new javax.swing.JButton();
        btnUpdateCus = new javax.swing.JButton();
        btnStatusCus = new javax.swing.JButton();
        txtSearchCus = new javax.swing.JTextField();
        btnSearchCus = new javax.swing.JButton();
        btnNewChild = new javax.swing.JButton();
        jCheckBox1 = new javax.swing.JCheckBox();
        btnActive = new javax.swing.JButton();

        jLabel8.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabel8.setText("Gender:");

        jLabel6.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel6.setText("Doctor:");

        jLabel9.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel9.setText("Pass Iless:");

        txtfullname.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N

        txtPass.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel10.setText("Email  Parent:");

        txtEmail.setEditable(false);
        txtEmail.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N

        jButton1.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/MyImages/icons8-exit-sign-filled-30.png"))); // NOI18N
        jButton1.setText("Exit");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        txtDoctor.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        jLabel1.setText("Update Children");

        bntOk.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        bntOk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/MyImages/icons8-save-30.png"))); // NOI18N
        bntOk.setText("Update");
        bntOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntOkActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel2.setText("Fullname:");

        jLabel7.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel7.setText("Curent Medication:");

        jComboBox1.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Male", "Female" }));

        txtCurent.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel5.setText("Birth Day:");

        javax.swing.GroupLayout dlUpdateChildLayout = new javax.swing.GroupLayout(dlUpdateChild.getContentPane());
        dlUpdateChild.getContentPane().setLayout(dlUpdateChildLayout);
        dlUpdateChildLayout.setHorizontalGroup(
            dlUpdateChildLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dlUpdateChildLayout.createSequentialGroup()
                .addGroup(dlUpdateChildLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dlUpdateChildLayout.createSequentialGroup()
                        .addGap(175, 175, 175)
                        .addComponent(jLabel1))
                    .addGroup(dlUpdateChildLayout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(dlUpdateChildLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel2)
                            .addComponent(jLabel7)
                            .addComponent(jLabel9)
                            .addComponent(jLabel6)
                            .addComponent(jLabel10))
                        .addGroup(dlUpdateChildLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(dlUpdateChildLayout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(dlUpdateChildLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtCurent, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtfullname, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(dlUpdateChildLayout.createSequentialGroup()
                                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel8)
                                        .addGap(18, 18, 18)
                                        .addComponent(jComboBox1, 0, 139, Short.MAX_VALUE))))
                            .addGroup(dlUpdateChildLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(dlUpdateChildLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(dlUpdateChildLayout.createSequentialGroup()
                                        .addComponent(bntOk)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(txtPass)
                                    .addComponent(txtDoctor)
                                    .addComponent(txtEmail))))))
                .addGap(44, 44, 44))
        );
        dlUpdateChildLayout.setVerticalGroup(
            dlUpdateChildLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dlUpdateChildLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dlUpdateChildLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(dlUpdateChildLayout.createSequentialGroup()
                        .addGroup(dlUpdateChildLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(dlUpdateChildLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(36, 36, 36)
                                .addGroup(dlUpdateChildLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(txtfullname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(dlUpdateChildLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dlUpdateChildLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel8)
                                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(dlUpdateChildLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7)
                            .addComponent(txtCurent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(dlUpdateChildLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel9)
                            .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6))
                    .addComponent(txtDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(dlUpdateChildLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(dlUpdateChildLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bntOk)
                    .addComponent(jButton1))
                .addGap(72, 72, 72))
        );

        jLabel3.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        jLabel3.setText("Looking for Nanny");

        jLabel4.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel4.setText("Child ID:");

        jLabel11.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel11.setText("Child Name:");

        jLabel12.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel12.setText("Group:");

        jLabel13.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel13.setText("Activity:");

        jLabel14.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel14.setText("Time:");

        jLabel15.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel15.setText("From:");

        txtAddOrChildID.setEditable(false);
        txtAddOrChildID.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N

        jLabel16.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel16.setText("To:");

        lbNN.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        lbNN.setText("Nanny:");

        jButton7.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/MyImages/icons8-money-30.png"))); // NOI18N
        jButton7.setText("Cashout");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        txtAddOrChildname.setEditable(false);
        txtAddOrChildname.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N

        txtAddOrderGroup.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        txtAddOrderGroup.setText("jLabel18");

        cbAddOrderAct.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        cbAddOrderAct.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbAddOrderActItemStateChanged(evt);
            }
        });

        cbAddOrderTime.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        cbAddOrderTime.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbAddOrderTimeItemStateChanged(evt);
            }
        });

        dateChooseStart.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dateChooseStartPropertyChange(evt);
            }
        });

        datChooseEnd.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                datChooseEndPropertyChange(evt);
            }
        });

        cbAddOrderNN.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N

        jButton9.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/MyImages/icons8-exit-sign-filled-30.png"))); // NOI18N
        jButton9.setText("Back");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout dlAddOrderLayout = new javax.swing.GroupLayout(dlAddOrder.getContentPane());
        dlAddOrder.getContentPane().setLayout(dlAddOrderLayout);
        dlAddOrderLayout.setHorizontalGroup(
            dlAddOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dlAddOrderLayout.createSequentialGroup()
                .addGroup(dlAddOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dlAddOrderLayout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(jButton7)
                        .addGap(18, 18, 18)
                        .addComponent(jButton9))
                    .addGroup(dlAddOrderLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(dlAddOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel4)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13)
                            .addComponent(jLabel14)
                            .addComponent(jLabel15)
                            .addComponent(jLabel16)
                            .addComponent(lbNN))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(dlAddOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtAddOrChildID)
                            .addComponent(txtAddOrChildname)
                            .addComponent(txtAddOrderGroup)
                            .addComponent(cbAddOrderAct, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbAddOrderTime, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dateChooseStart, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                            .addComponent(datChooseEnd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbAddOrderNN, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(dlAddOrderLayout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(jLabel3)))
                .addContainerGap(63, Short.MAX_VALUE))
        );
        dlAddOrderLayout.setVerticalGroup(
            dlAddOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dlAddOrderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addGroup(dlAddOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(dlAddOrderLayout.createSequentialGroup()
                        .addGroup(dlAddOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(dlAddOrderLayout.createSequentialGroup()
                                .addGroup(dlAddOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel4)
                                    .addComponent(txtAddOrChildID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(dlAddOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel11)
                                    .addComponent(txtAddOrChildname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(dlAddOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel12)
                                    .addComponent(txtAddOrderGroup))
                                .addGap(18, 18, 18)
                                .addGroup(dlAddOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel13)
                                    .addComponent(cbAddOrderAct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(dlAddOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel14)
                                    .addComponent(cbAddOrderTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel15))
                            .addComponent(dateChooseStart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel16))
                    .addComponent(datChooseEnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(dlAddOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbNN)
                    .addComponent(cbAddOrderNN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(dlAddOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton7)
                    .addComponent(jButton9))
                .addGap(40, 40, 40))
        );

        jLabel34.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        jLabel34.setText("New Customer");

        jLabel35.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel35.setText("Customer Email:");

        jLabel36.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel36.setText("Name:");

        jLabel37.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel37.setText("Address:");

        jLabel39.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel39.setText("Work phone:");

        txtNewCusEmail.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N

        txtNewCusName.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N

        txtNewCusAddr.setColumns(20);
        txtNewCusAddr.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        txtNewCusAddr.setRows(5);
        jScrollPane1.setViewportView(txtNewCusAddr);

        jLabel40.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel40.setText("Mobile phone:");

        txtNewCusFone.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N

        txtNewCusWorkFone.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N

        btnAddCus.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        btnAddCus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/MyImages/icons8-add-new-30-gr.png"))); // NOI18N
        btnAddCus.setText("Add");
        btnAddCus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddCusActionPerformed(evt);
            }
        });

        btnClearAddCuss.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        btnClearAddCuss.setIcon(new javax.swing.ImageIcon(getClass().getResource("/MyImages/icons8-eraser-30.png"))); // NOI18N
        btnClearAddCuss.setText("Clear");
        btnClearAddCuss.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearAddCussActionPerformed(evt);
            }
        });

        jButton15.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jButton15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/MyImages/icons8-undo-30.png"))); // NOI18N
        jButton15.setText("Back");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout dlAddCusLayout = new javax.swing.GroupLayout(dlAddCus.getContentPane());
        dlAddCus.getContentPane().setLayout(dlAddCusLayout);
        dlAddCusLayout.setHorizontalGroup(
            dlAddCusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dlAddCusLayout.createSequentialGroup()
                .addGroup(dlAddCusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dlAddCusLayout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(btnAddCus)
                        .addGap(18, 18, 18)
                        .addComponent(btnClearAddCuss)
                        .addGap(18, 18, 18)
                        .addComponent(jButton15))
                    .addGroup(dlAddCusLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(dlAddCusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel36)
                            .addComponent(jLabel35)
                            .addComponent(jLabel37)
                            .addComponent(jLabel39)
                            .addComponent(jLabel40))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(dlAddCusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel34)
                            .addGroup(dlAddCusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jScrollPane1)
                                .addComponent(txtNewCusEmail)
                                .addComponent(txtNewCusName)
                                .addComponent(txtNewCusFone)
                                .addComponent(txtNewCusWorkFone)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        dlAddCusLayout.setVerticalGroup(
            dlAddCusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dlAddCusLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel34)
                .addGap(10, 10, 10)
                .addGroup(dlAddCusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(dlAddCusLayout.createSequentialGroup()
                        .addGroup(dlAddCusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel35)
                            .addComponent(txtNewCusEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(dlAddCusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel36)
                            .addComponent(txtNewCusName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(dlAddCusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel37)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel40))
                    .addComponent(txtNewCusFone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(dlAddCusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39)
                    .addComponent(txtNewCusWorkFone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(dlAddCusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddCus)
                    .addComponent(btnClearAddCuss)
                    .addComponent(jButton15))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel38.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        jLabel38.setText("Update Customer");

        jLabel41.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel41.setText("Customer Email:");

        txtUpdateCus.setEditable(false);
        txtUpdateCus.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        txtUpdateCus.setText("jTextField3");

        txtUpdateCusName.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        txtUpdateCusName.setText("jTextField4");

        jLabel42.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel42.setText("Name:");

        txtUpdateCusAddress.setColumns(20);
        txtUpdateCusAddress.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        txtUpdateCusAddress.setRows(5);
        jScrollPane2.setViewportView(txtUpdateCusAddress);

        jLabel43.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel43.setText("Address:");

        jLabel44.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel44.setText("Mobile phone:");

        txtUpdateCusmobile.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        txtUpdateCusmobile.setText("jTextField5");

        txtUpdateCusWorkP.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        txtUpdateCusWorkP.setText("jTextField6");

        jLabel45.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel45.setText("Work phone:");

        jButton16.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jButton16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/MyImages/icons8-save-30.png"))); // NOI18N
        jButton16.setText("Save");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        jButton17.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jButton17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/MyImages/icons8-undo-30.png"))); // NOI18N
        jButton17.setText("Back");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout dlUpdateCusLayout = new javax.swing.GroupLayout(dlUpdateCus.getContentPane());
        dlUpdateCus.getContentPane().setLayout(dlUpdateCusLayout);
        dlUpdateCusLayout.setHorizontalGroup(
            dlUpdateCusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dlUpdateCusLayout.createSequentialGroup()
                .addGap(121, 121, 121)
                .addComponent(jButton16)
                .addGap(18, 18, 18)
                .addComponent(jButton17)
                .addGap(114, 114, 114))
            .addGroup(dlUpdateCusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(dlUpdateCusLayout.createSequentialGroup()
                    .addGap(58, 58, 58)
                    .addGroup(dlUpdateCusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel42)
                        .addComponent(jLabel41)
                        .addComponent(jLabel43)
                        .addComponent(jLabel45)
                        .addComponent(jLabel44))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(dlUpdateCusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel38)
                        .addGroup(dlUpdateCusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane2)
                            .addComponent(txtUpdateCus)
                            .addComponent(txtUpdateCusName)
                            .addComponent(txtUpdateCusmobile)
                            .addComponent(txtUpdateCusWorkP)))
                    .addContainerGap(85, Short.MAX_VALUE)))
        );
        dlUpdateCusLayout.setVerticalGroup(
            dlUpdateCusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dlUpdateCusLayout.createSequentialGroup()
                .addGap(309, 309, 309)
                .addGroup(dlUpdateCusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton17)
                    .addComponent(jButton16))
                .addGap(25, 25, 25))
            .addGroup(dlUpdateCusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(dlUpdateCusLayout.createSequentialGroup()
                    .addGap(15, 15, 15)
                    .addComponent(jLabel38)
                    .addGap(10, 10, 10)
                    .addGroup(dlUpdateCusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(dlUpdateCusLayout.createSequentialGroup()
                            .addGroup(dlUpdateCusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel41)
                                .addComponent(txtUpdateCus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(dlUpdateCusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel42)
                                .addComponent(txtUpdateCusName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(dlUpdateCusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel43)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addComponent(jLabel44))
                        .addComponent(txtUpdateCusmobile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(dlUpdateCusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel45)
                        .addComponent(txtUpdateCusWorkP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(88, Short.MAX_VALUE)))
        );

        jLabel46.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        jLabel46.setText("New Child");

        jLabel47.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel47.setText("Fullname:");

        txtNewChild.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N

        jLabel48.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel48.setText("Birth Day:");

        txtNewChildGender.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        txtNewChildGender.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Male", "Female" }));

        txtNewChildMedication.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N

        jLabel49.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel49.setText("Curent Medication:");

        jLabel50.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel50.setText("Pass Iless:");

        txtNewChilIlle.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N

        txtNewChildDoctor.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N

        jLabel51.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel51.setText("Doctor:");

        jLabel52.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel52.setText("Email  Parent:");

        txtNewEmailParents.setEditable(false);
        txtNewEmailParents.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N

        bntOk1.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        bntOk1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/MyImages/icons8-add-new-30.png"))); // NOI18N
        bntOk1.setText("Add");
        bntOk1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntOk1ActionPerformed(evt);
            }
        });

        bntReset1.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        bntReset1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/MyImages/icons8-eraser-30.png"))); // NOI18N
        bntReset1.setText("Reset");
        bntReset1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntReset1ActionPerformed(evt);
            }
        });

        jButton18.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jButton18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/MyImages/icons8-undo-30.png"))); // NOI18N
        jButton18.setText("Back");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout dlAddChildLayout = new javax.swing.GroupLayout(dlAddChild.getContentPane());
        dlAddChild.getContentPane().setLayout(dlAddChildLayout);
        dlAddChildLayout.setHorizontalGroup(
            dlAddChildLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 519, Short.MAX_VALUE)
            .addGroup(dlAddChildLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(dlAddChildLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(dlAddChildLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(dlAddChildLayout.createSequentialGroup()
                            .addGap(55, 55, 55)
                            .addComponent(bntOk1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(bntReset1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jButton18)
                            .addGap(129, 129, 129))
                        .addGroup(dlAddChildLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(dlAddChildLayout.createSequentialGroup()
                                .addGap(125, 125, 125)
                                .addComponent(jLabel46))
                            .addGroup(dlAddChildLayout.createSequentialGroup()
                                .addGroup(dlAddChildLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel48)
                                    .addComponent(jLabel47)
                                    .addComponent(jLabel49)
                                    .addComponent(jLabel50)
                                    .addComponent(jLabel51)
                                    .addComponent(jLabel52))
                                .addGroup(dlAddChildLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(dlAddChildLayout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addGroup(dlAddChildLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtNewChilIlle)
                                            .addComponent(txtNewChildDoctor)
                                            .addComponent(txtNewEmailParents)))
                                    .addGroup(dlAddChildLayout.createSequentialGroup()
                                        .addGap(20, 20, 20)
                                        .addGroup(dlAddChildLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtNewChildMedication, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(txtNewChild, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(dlAddChildLayout.createSequentialGroup()
                                                .addComponent(txtNewChildBirth, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(81, 81, 81)
                                                .addComponent(txtNewChildGender, 0, 139, Short.MAX_VALUE))))))))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        dlAddChildLayout.setVerticalGroup(
            dlAddChildLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 406, Short.MAX_VALUE)
            .addGroup(dlAddChildLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(dlAddChildLayout.createSequentialGroup()
                    .addGap(41, 41, 41)
                    .addGroup(dlAddChildLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(dlAddChildLayout.createSequentialGroup()
                            .addGroup(dlAddChildLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(dlAddChildLayout.createSequentialGroup()
                                    .addComponent(jLabel46)
                                    .addGap(36, 36, 36)
                                    .addGroup(dlAddChildLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel47)
                                        .addComponent(txtNewChild, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18)
                                    .addGroup(dlAddChildLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel48)
                                        .addComponent(txtNewChildGender, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addComponent(txtNewChildBirth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(dlAddChildLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel49)
                                .addComponent(txtNewChildMedication, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(dlAddChildLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel50)
                                .addComponent(txtNewChilIlle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addComponent(jLabel51))
                        .addComponent(txtNewChildDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(21, 21, 21)
                    .addGroup(dlAddChildLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel52)
                        .addComponent(txtNewEmailParents, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(dlAddChildLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(bntOk1)
                        .addComponent(bntReset1)
                        .addComponent(jButton18))
                    .addGap(42, 42, 42)))
        );

        jLabel19.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        jLabel19.setText("Comfirm Order");

        jLabel20.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel20.setText("Name:");

        jLabel21.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel21.setText("Activity:");

        jLabel22.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel22.setText("Time");

        jLabel23.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel23.setText("Start:");

        jLabel24.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel24.setText("End:");

        jLabel25.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel25.setText("Nanny:");

        jLabel26.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel26.setText("Price:");

        jButton11.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/MyImages/icons8-approval-30.png"))); // NOI18N
        jButton11.setText("Comfirm");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton12.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/MyImages/icons8-undo-30.png"))); // NOI18N
        jButton12.setText("Back");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        txtChildName.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        txtChildName.setText("jLabel27");

        txtAct.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        txtAct.setText("jLabel28");

        txtTime.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        txtTime.setText("jLabel29");

        txtStart.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        txtStart.setText("jLabel30");

        txtEnd.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        txtEnd.setText("jLabel31");

        txtNanny.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        txtNanny.setText("jLabel32");

        txtPrice.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        txtPrice.setText("jLabel33");

        jLabel57.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel57.setText("Orderby:");

        txtOrderBy.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        txtOrderBy.setText("jLabel33");

        javax.swing.GroupLayout dlComfirmOrderLayout = new javax.swing.GroupLayout(dlComfirmOrder.getContentPane());
        dlComfirmOrder.getContentPane().setLayout(dlComfirmOrderLayout);
        dlComfirmOrderLayout.setHorizontalGroup(
            dlComfirmOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dlComfirmOrderLayout.createSequentialGroup()
                .addGroup(dlComfirmOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dlComfirmOrderLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(dlComfirmOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(dlComfirmOrderLayout.createSequentialGroup()
                                .addGroup(dlComfirmOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel20)
                                    .addComponent(jLabel21)
                                    .addComponent(jLabel22)
                                    .addComponent(jLabel23)
                                    .addComponent(jLabel24)
                                    .addComponent(jLabel25)
                                    .addComponent(jLabel26)
                                    .addComponent(jLabel57))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(dlComfirmOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtChildName)
                                    .addComponent(txtAct)
                                    .addComponent(txtTime)
                                    .addComponent(txtStart)
                                    .addComponent(txtEnd)
                                    .addComponent(txtNanny)
                                    .addComponent(txtOrderBy)
                                    .addComponent(txtPrice)))
                            .addGroup(dlComfirmOrderLayout.createSequentialGroup()
                                .addComponent(jButton11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton12))))
                    .addGroup(dlComfirmOrderLayout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(jLabel19)))
                .addContainerGap(47, Short.MAX_VALUE))
        );
        dlComfirmOrderLayout.setVerticalGroup(
            dlComfirmOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dlComfirmOrderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19)
                .addGap(34, 34, 34)
                .addGroup(dlComfirmOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(txtChildName))
                .addGap(18, 18, 18)
                .addGroup(dlComfirmOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(txtAct))
                .addGap(18, 18, 18)
                .addGroup(dlComfirmOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(txtTime))
                .addGap(18, 18, 18)
                .addGroup(dlComfirmOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(txtStart))
                .addGap(18, 18, 18)
                .addGroup(dlComfirmOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(txtEnd))
                .addGap(18, 18, 18)
                .addGroup(dlComfirmOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(txtNanny))
                .addGap(18, 18, 18)
                .addGroup(dlComfirmOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(txtPrice))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(dlComfirmOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtOrderBy)
                    .addComponent(jLabel57))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addGroup(dlComfirmOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton11)
                    .addComponent(jButton12))
                .addGap(29, 29, 29))
        );

        jLabel53.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel53.setText("Do you realy want to delete ");

        jLabel54.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel54.setText("jLabel54");

        btnCfDelete.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        btnCfDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/MyImages/icons8-approval-30.png"))); // NOI18N
        btnCfDelete.setText("Comfirm");
        btnCfDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCfDeleteActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/MyImages/icons8-undo-30.png"))); // NOI18N
        jButton3.setText("Back");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout dlCfCusLayout = new javax.swing.GroupLayout(dlCfCus.getContentPane());
        dlCfCus.getContentPane().setLayout(dlCfCusLayout);
        dlCfCusLayout.setHorizontalGroup(
            dlCfCusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dlCfCusLayout.createSequentialGroup()
                .addGroup(dlCfCusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dlCfCusLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel53)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel54))
                    .addGroup(dlCfCusLayout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(btnCfDelete)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        dlCfCusLayout.setVerticalGroup(
            dlCfCusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dlCfCusLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(dlCfCusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel53)
                    .addComponent(jLabel54))
                .addGap(18, 18, 18)
                .addGroup(dlCfCusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCfDelete)
                    .addComponent(jButton3))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jLabel55.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel55.setText("Do you realy want to delete ");

        btnCfDelete1.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        btnCfDelete1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/MyImages/icons8-approval-30.png"))); // NOI18N
        btnCfDelete1.setText("Comfirm");
        btnCfDelete1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCfDelete1ActionPerformed(evt);
            }
        });

        jLabel56.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel56.setText("jLabel54");

        jButton4.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/MyImages/icons8-undo-30.png"))); // NOI18N
        jButton4.setText("Back");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout dlCfChildLayout = new javax.swing.GroupLayout(dlCfChild.getContentPane());
        dlCfChild.getContentPane().setLayout(dlCfChildLayout);
        dlCfChildLayout.setHorizontalGroup(
            dlCfChildLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dlCfChildLayout.createSequentialGroup()
                .addGroup(dlCfChildLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dlCfChildLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel55)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel56))
                    .addGroup(dlCfChildLayout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(btnCfDelete1)
                        .addGap(18, 18, 18)
                        .addComponent(jButton4)))
                .addGap(18, 18, 18))
        );
        dlCfChildLayout.setVerticalGroup(
            dlCfChildLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dlCfChildLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(dlCfChildLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel55)
                    .addComponent(jLabel56))
                .addGap(18, 18, 18)
                .addGroup(dlCfChildLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCfDelete1)
                    .addComponent(jButton4))
                .addGap(25, 25, 25))
        );

        tbChild.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        tbChild.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Child ID", "Fullname", "Birthday", "Gender", "Email Parents "
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbChild.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbChildMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tbChild);

        btnUpdateChild.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        btnUpdateChild.setForeground(new java.awt.Color(255, 0, 204));
        btnUpdateChild.setIcon(new javax.swing.ImageIcon(getClass().getResource("/MyImages/icons8-edit-30.png"))); // NOI18N
        btnUpdateChild.setText("Update");
        btnUpdateChild.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateChildActionPerformed(evt);
            }
        });

        btnStatusChild.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        btnStatusChild.setForeground(new java.awt.Color(255, 0, 204));
        btnStatusChild.setIcon(new javax.swing.ImageIcon(getClass().getResource("/MyImages/icons8-delete-bin-30.png"))); // NOI18N
        btnStatusChild.setText("Delete");
        btnStatusChild.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStatusChildActionPerformed(evt);
            }
        });

        btnCreateOrder.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        btnCreateOrder.setForeground(new java.awt.Color(255, 0, 204));
        btnCreateOrder.setIcon(new javax.swing.ImageIcon(getClass().getResource("/MyImages/icons8-pacifier-30.png"))); // NOI18N
        btnCreateOrder.setText("Looking for Nanny");
        btnCreateOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateOrderActionPerformed(evt);
            }
        });

        btnSearchChild.setIcon(new javax.swing.ImageIcon(getClass().getResource("/MyImages/icons8-search-15.png"))); // NOI18N
        btnSearchChild.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchChildActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSearchChild, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnCreateOrder, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnUpdateChild, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnStatusChild, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 459, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtSearch)
                            .addComponent(btnSearchChild, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(btnUpdateChild, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCreateOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnStatusChild, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 701, Short.MAX_VALUE)
                        .addContainerGap())))
        );

        jTabbedPane1.addTab("Children", jPanel1);

        tbCus.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        tbCus.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Email", "Full Name", "Phone", "Work Phone", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbCus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbCusMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tbCus);

        btnNewCus.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        btnNewCus.setForeground(new java.awt.Color(0, 153, 51));
        btnNewCus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/MyImages/icons8-add-new-30-gr.png"))); // NOI18N
        btnNewCus.setText("Add Customer");
        btnNewCus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewCusActionPerformed(evt);
            }
        });

        btnUpdateCus.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        btnUpdateCus.setForeground(new java.awt.Color(0, 153, 51));
        btnUpdateCus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/MyImages/icons8-edit-30-gr.png"))); // NOI18N
        btnUpdateCus.setText("Update");
        btnUpdateCus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateCusActionPerformed(evt);
            }
        });

        btnStatusCus.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        btnStatusCus.setForeground(new java.awt.Color(0, 153, 51));
        btnStatusCus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/MyImages/icons8-delete-bin-30-gr.png"))); // NOI18N
        btnStatusCus.setText("Delete");
        btnStatusCus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStatusCusActionPerformed(evt);
            }
        });

        txtSearchCus.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N

        btnSearchCus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/MyImages/icons8-search-15-gr.png"))); // NOI18N
        btnSearchCus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchCusActionPerformed(evt);
            }
        });

        btnNewChild.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        btnNewChild.setForeground(new java.awt.Color(0, 153, 51));
        btnNewChild.setIcon(new javax.swing.ImageIcon(getClass().getResource("/MyImages/icons8-baby-30.png"))); // NOI18N
        btnNewChild.setText("Add Baby");
        btnNewChild.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewChildActionPerformed(evt);
            }
        });

        jCheckBox1.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jCheckBox1.setForeground(new java.awt.Color(0, 153, 51));
        jCheckBox1.setText("Include Hidden Customer");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        btnActive.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        btnActive.setForeground(new java.awt.Color(0, 153, 51));
        btnActive.setIcon(new javax.swing.ImageIcon(getClass().getResource("/MyImages/icons8-checked-user-male-30.png"))); // NOI18N
        btnActive.setText("Active Again");
        btnActive.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActiveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnUpdateCus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnNewCus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(txtSearchCus, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSearchCus, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnStatusCus, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnNewChild, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jCheckBox1)
                    .addComponent(btnActive, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 459, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtSearchCus)
                            .addComponent(btnSearchCus, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(btnNewCus, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnUpdateCus, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnNewChild, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnStatusCus, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jCheckBox1)
                        .addGap(18, 18, 18)
                        .addComponent(btnActive)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        jTabbedPane1.addTab("Customer", jPanel2);

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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        dlUpdateChild.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void bntOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntOkActionPerformed
        Date now = new Date();
        if (!jDateChooser1.getDate().after(now)) {
            try {
                if (txtfullname.getText().equals(null) || txtfullname.getText().equals("")) {
                    JOptionPane.showMessageDialog(this, "Update failed!!");
                } else if (a.checkHaveNum(txtfullname.getText())) {
                    JOptionPane.showMessageDialog(this, "Name can not contains number!!");
                } else {
                    DefaultTableModel tbModel = (DefaultTableModel) tbChild.getModel();
                    int currentRow = tbChild.getSelectedRow();
                    int child_id = (int) tbModel.getValueAt(currentRow, 0);
                    boolean gender = false;
                    if (jComboBox1.getSelectedIndex() == 0) {
                        gender = true;
                    }
                    if (chBean.updateChild(child_id, txtfullname.getText(), jDateChooser1.getDate(), txtCurent.getText(), txtPass.getText(), txtDoctor.getText(), gender)) {
                        JOptionPane.showMessageDialog(this, "Update successful!!");
                    }
                    dlUpdateChild.dispose();
                    loadChild();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Update failed!!");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Update failed!!");
        }


    }//GEN-LAST:event_bntOkActionPerformed

    private void bntOk1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntOk1ActionPerformed
        boolean gender = false;
        Date now = new Date();
        if (!txtNewChildBirth.getDate().after(now)) {
            if (txtNewChildGender.getSelectedIndex() == 0) {
                gender = true;
            }
            if (chBean.isExist(txtNewEmailParents.getText(), txtNewChild.getText())) {
                JOptionPane.showMessageDialog(this, "Children already created!", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (checkNull(txtNewChild.getText())) {
                JOptionPane.showMessageDialog(this, "Fill name of child!!");
            } else if (a.checkHaveNum(txtNewChild.getText())) {
                JOptionPane.showMessageDialog(this, "Name can not contains number!!");
            } else {
                chBean.addChild(txtNewChild.getText(), txtNewChildBirth.getDate(), txtNewChildMedication.getText(), txtNewChilIlle.getText(), txtNewChildDoctor.getText(), gender, txtNewEmailParents.getText());
                loadChild();
                dlAddChild.dispose();
                JOptionPane.showMessageDialog(this, "Child was added!!");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Can not born in the future!!");
        }

    }//GEN-LAST:event_bntOk1ActionPerformed
    public boolean checkNull(String name) {
        if (name.isEmpty() || name.equals("")) {
            return true;
        }
        return false;
    }
    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        dlAddChild.dispose();
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        dlAddCus.dispose();
    }//GEN-LAST:event_jButton15ActionPerformed

    private void btnAddCusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddCusActionPerformed
        if (txtNewCusEmail.getText().equals("") || txtNewCusEmail.getText().equals(null) || txtNewCusName.getText().equals("") || txtNewCusName.getText().equals(null) || txtNewCusFone.getText().equals("") || txtNewCusFone.getText().equals(null)) {
            JOptionPane.showMessageDialog(this, "Can not Create Customer!!");
        } else if (a.checkStart(txtNewCusEmail.getText())) {
            JOptionPane.showMessageDialog(this, "Email can not start with symbol!!");
        } else if (a.checkSyb(txtNewCusName.getText())) {
            JOptionPane.showMessageDialog(this, "Name can not have symbol!!");
        } else if (a.checkPat(txtNewCusFone.getText())) {
            JOptionPane.showMessageDialog(this, "Phone invalid !!");
        } else if (a.checkPat(txtNewCusWorkFone.getText())) {
            JOptionPane.showMessageDialog(this, "Phone invalid !!");
        } else if (!a.checkEmail(txtNewCusEmail.getText())) {
            JOptionPane.showMessageDialog(this, "Email invalid!!");
        } else if (!a.checkFone(txtNewCusFone.getText())) {
            JOptionPane.showMessageDialog(this, "Phone invalid!!");
        } else if (!a.checkFone(txtNewCusWorkFone.getText())) {
            JOptionPane.showMessageDialog(this, "Phone invalid!!");
        } else if (a.checkHaveNum(txtNewCusName.getText())) {
            JOptionPane.showMessageDialog(this, "Name can not contain number!!");
        } else {
            try {
                if (!cb.isExist(txtNewCusEmail.getText())) {
                    cb.addCus(txtNewCusEmail.getText(), txtNewCusName.getText(), txtNewCusAddr.getText(), txtNewCusFone.getText(), txtNewCusWorkFone.getText());
                    dlAddCus.dispose();
                    JOptionPane.showMessageDialog(this, "Customer was added!!");
                    loadCus(jCheckBox1.isSelected());
                } else {
                    JOptionPane.showMessageDialog(this, "Customer Email is Exist!", "Warning", JOptionPane.WARNING_MESSAGE);
                }
            } catch (Exception e) {

            }
        }
//        if (checkEmail(txtNewCusEmail.getText()) && checkFone(txtNewCusFone.getText()) && checkFone(txtNewCusWorkFone.getText())) {

//        } else if (a.checkStart(txtNewCusEmail.getText())) {
//            JOptionPane.showMessageDialog(this, "Can not Create Customer!!");
//        } else if (a.checkSyb(txtNewCusName.getText())) {
//            JOptionPane.showMessageDialog(this, "Can not Create Customer!!");
//        } else if (a.checkPat(txtNewCusFone.getText())) {
//            JOptionPane.showMessageDialog(this, "Phone must be number!!");
//        }

    }//GEN-LAST:event_btnAddCusActionPerformed


    private void btnClearAddCussActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearAddCussActionPerformed
        txtNewCusAddr.setText("");
        txtNewCusEmail.setText("");
        txtNewCusFone.setText("");
        txtNewCusName.setText("");
        txtNewCusWorkFone.setText("");
    }//GEN-LAST:event_btnClearAddCussActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        dlCfCus.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btnCfDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCfDeleteActionPerformed
        DefaultTableModel tbModel = (DefaultTableModel) tbCus.getModel();
        int currentRow = tbCus.getSelectedRow();
        String cusmail = tbModel.getValueAt(currentRow, 0).toString();
        cb.deleteCus(cusmail);
        dlCfCus.dispose();
        JOptionPane.showMessageDialog(this, "Customer was hided click hidden items for display again!!");
        loadCus(jCheckBox1.isSelected());
        hiddenBtn();
    }//GEN-LAST:event_btnCfDeleteActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        dlUpdateCus.dispose();
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        if (txtUpdateCusName.getText().equals("") || txtUpdateCusName.getText().equals(null)) {
            JOptionPane.showMessageDialog(this, "Update failed!!");
        } else if (!a.checkFone(txtUpdateCusmobile.getText())) {
            JOptionPane.showMessageDialog(this, "Update failed!!");
        } else if (!a.checkFone(txtUpdateCusWorkP.getText())) {
            JOptionPane.showMessageDialog(this, "Update failed!!");
        } else if (a.checkPat(txtUpdateCusmobile.getText())) {
            JOptionPane.showMessageDialog(this, "Update failed!!");
        } else if (a.checkPat(txtUpdateCusWorkP.getText())) {
            JOptionPane.showMessageDialog(this, "Update failed!!");
        } else if (a.checkSyb(txtUpdateCusName.getText())) {
            JOptionPane.showMessageDialog(this, "Update failed!!");
        } else if (a.checkHaveNum(txtUpdateCusName.getText())) {
            JOptionPane.showMessageDialog(this, "Update failed!!");
        } else {
            cb.updateCus(txtUpdateCus.getText(), txtUpdateCusName.getText(), txtUpdateCusAddress.getText(), txtUpdateCusmobile.getText(), txtUpdateCusWorkP.getText());
            dlUpdateCus.dispose();
            JOptionPane.showMessageDialog(this, "Update successful!!");
            loadCus(jCheckBox1.isSelected());
        }
    }//GEN-LAST:event_jButton16ActionPerformed

    private void btnCfDelete1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCfDelete1ActionPerformed
        int selectedRow = tbChild.getSelectedRow();
        DefaultTableModel tbModel = (DefaultTableModel) tbChild.getModel();

        // Lay email
        int childid = (int) tbModel.getValueAt(selectedRow, 0);
        chBean.deleteChild(childid);
        JOptionPane.showMessageDialog(this, "Child deleted!");
        dlCfChild.dispose();
        loadChild();
    }//GEN-LAST:event_btnCfDelete1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        dlCfChild.dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void bntReset1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntReset1ActionPerformed
        txtNewChild.setText("");
        txtNewChildMedication.setText("");
        txtNewChilIlle.setText("");
        txtNewChildDoctor.setText("");
    }//GEN-LAST:event_bntReset1ActionPerformed

    private void tbChildMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbChildMouseClicked
        btnUpdateChild.setVisible(true);
        btnCreateOrder.setVisible(true);
        btnStatusChild.setVisible(true);
    }//GEN-LAST:event_tbChildMouseClicked

    private void btnUpdateChildActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateChildActionPerformed
        closeDL();
        // Lay dong hien tai dang chon trong bang
        DefaultTableModel tbModel = (DefaultTableModel) tbChild.getModel();
        int currentRow = tbChild.getSelectedRow();

        // Lay du lieu trong dong
        int child_id = (int) tbModel.getValueAt(currentRow, 0);
        MMt.displaydialog(dlUpdateChild);
        txtfullname.setText(tbModel.getValueAt(currentRow, 1).toString());
        String strBirth = tbModel.getValueAt(currentRow, 2).toString();
        String strGen = tbModel.getValueAt(currentRow, 3).toString();
        if (strGen == "Male") {
            jComboBox1.setSelectedIndex(0);
        } else {
            jComboBox1.setSelectedIndex(1);
        }
        txtCurent.setText(chBean.getcurrent_medications(child_id));
        txtPass.setText(chBean.getpass_illess(child_id));
        txtDoctor.setText(chBean.getdoctor(child_id));
        txtEmail.setText(tbModel.getValueAt(currentRow, 4).toString());
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date birthdate = df.parse(strBirth);
            jDateChooser1.setDate(birthdate);
        } catch (ParseException ex) {
            Logger.getLogger(ChildAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnUpdateChildActionPerformed

    private void btnStatusChildActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStatusChildActionPerformed
        closeDL();
        int selectedRow = tbChild.getSelectedRow();
        DefaultTableModel tbModel = (DefaultTableModel) tbChild.getModel();

        // Lay email
        String childname = tbModel.getValueAt(selectedRow, 1).toString();
        jLabel56.setText(childname);
        MMt.displaydialog(dlCfChild);
    }//GEN-LAST:event_btnStatusChildActionPerformed

    private void btnCreateOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateOrderActionPerformed
        closeDL();
        DefaultTableModel tbModel = (DefaultTableModel) tbChild.getModel();
        int currentRow = tbChild.getSelectedRow();
        child_id = (int) tbModel.getValueAt(currentRow, 0);
        txtAddOrChildID.setText(tbModel.getValueAt(currentRow, 0).toString());
        txtAddOrChildname.setText(tbModel.getValueAt(currentRow, 1).toString());
        //lấy ngày hiện tại +1        
        cbAddOrderNN.setVisible(false);
        lbNN.setVisible(false);
        //tính tuổi
        String strBirth = tbModel.getValueAt(currentRow, 2).toString();
        int ages = gb.calYear(strBirth);
        cAges = gb.calYear(strBirth);
        String strgroup = "New born";
        if (ages > 0) {
            strgroup = ages + " years old.";
        }
        txtAddOrderGroup.setText(strgroup);
        MMt.displaydialog(dlAddOrder);
    }//GEN-LAST:event_btnCreateOrderActionPerformed
    int cAges = 0;
    private void btnSearchChildActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchChildActionPerformed
        if (!txtSearch.getText().equals("") || !txtSearch.getText().equals(null)) {
            searchChild(txtSearch.getText());
        }
    }//GEN-LAST:event_btnSearchChildActionPerformed

    private void tbCusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbCusMouseClicked
        DefaultTableModel tbModel = (DefaultTableModel) tbCus.getModel();
        int currentRow = tbCus.getSelectedRow();
        String status = tbModel.getValueAt(currentRow, 4).toString();
        displayBtn();
        if (status.equals("InActivce")) {
            btnStatusCus.setVisible(false);
            btnActive.setVisible(true);
            btnNewChild.setEnabled(false);
        } else {
            btnNewChild.setEnabled(true);
            btnActive.setVisible(false);
        }
    }//GEN-LAST:event_tbCusMouseClicked

    private void btnNewCusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewCusActionPerformed
        closeDL();
        MMt.displaydialog(dlAddCus);
        loadCus(jCheckBox1.isSelected());
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNewCusActionPerformed

    private void btnUpdateCusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateCusActionPerformed
        closeDL();
        DefaultTableModel tbModel = (DefaultTableModel) tbCus.getModel();
        int currentRow = tbCus.getSelectedRow();
        txtUpdateCus.setText(tbModel.getValueAt(currentRow, 0).toString());
        txtUpdateCusName.setText(tbModel.getValueAt(currentRow, 1).toString());
        txtUpdateCusAddress.setText(cb.getCusAdd(tbModel.getValueAt(currentRow, 0).toString()));
        txtUpdateCusmobile.setText(tbModel.getValueAt(currentRow, 2).toString());
        txtUpdateCusWorkP.setText(tbModel.getValueAt(currentRow, 3).toString());
        MMt.displaydialog(dlUpdateCus);
    }//GEN-LAST:event_btnUpdateCusActionPerformed

    private void btnStatusCusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStatusCusActionPerformed
        closeDL();
        MMt.displaydialog(dlCfCus);
        DefaultTableModel tbModel = (DefaultTableModel) tbCus.getModel();
        int currentRow = tbCus.getSelectedRow();
        jLabel54.setText(tbModel.getValueAt(currentRow, 1).toString());
        loadChild();
    }//GEN-LAST:event_btnStatusCusActionPerformed

    private void btnSearchCusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchCusActionPerformed
        if (!txtSearchCus.getText().equals("") || !txtSearchCus.getText().equals(null)) {
            searchCus(txtSearchCus.getText(), jCheckBox1.isSelected());
        }
    }//GEN-LAST:event_btnSearchCusActionPerformed

    private void btnNewChildActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewChildActionPerformed
        closeDL();
        DefaultTableModel tbModel = (DefaultTableModel) tbCus.getModel();
        int currentRow = tbCus.getSelectedRow();
        String parentsEmail = tbModel.getValueAt(currentRow, 0).toString();
        txtNewChildBirth.setDate(new Date());
        txtNewEmailParents.setText(parentsEmail);
        txtNewChild.setText("");
        txtNewChildMedication.setText("");
        txtNewChilIlle.setText("");
        txtNewChildDoctor.setText("");
        MMt.displaydialog(dlAddChild);
    }//GEN-LAST:event_btnNewChildActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        loadCus(jCheckBox1.isSelected());
        hiddenBtn();
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void btnActiveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActiveActionPerformed
        DefaultTableModel tbModel = (DefaultTableModel) tbCus.getModel();
        int currentRow = tbCus.getSelectedRow();
        String cusmail = tbModel.getValueAt(currentRow, 0).toString();
        cb.activeCus(cusmail);
        loadCus(jCheckBox1.isSelected());
        btnActive.setVisible(false);
        JOptionPane.showMessageDialog(this, "Customer actived!!");
        hiddenBtn();
        loadChild();
    }//GEN-LAST:event_btnActiveActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        try {
            if (lbNN.isVisible()) {
//            DefaultTableModel tbModel = (DefaultTableModel) tbChild.getModel();
//            int currentRow = tbCus.getSelectedRow();
//            String birth =  tbModel.getValueAt(currentRow, 2).toString();
                System.out.println(dateChooseStart.getDate());
                System.out.println(datChooseEnd.getDate());
                int childId = Integer.valueOf(txtAddOrChildID.getText());
                String strbirth = chBean.strofBirth(childId);
                txtChildName.setText(txtAddOrChildname.getText());
                txtAct.setText(cbAddOrderAct.getSelectedItem().toString());
                String actname = txtAct.getText();
                txtTime.setText(cbAddOrderTime.getSelectedItem().toString());
                Date startD = dateChooseStart.getDate();
                Date endD = datChooseEnd.getDate();
                if (!startD.after(endD)) {
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                    String strstartD = formatter.format(startD);
                    String strendD = formatter.format(endD);
                    txtStart.setText(strstartD);
                    txtEnd.setText(strendD);
                    txtNanny.setText(cbAddOrderNN.getSelectedItem().toString());
                    txtOrderBy.setText(fMain.name());
                    float payment = ob.getGroupFee(strbirth) + ob.getActFee(actname) * ob.daysBetween(startD, endD);
                    txtPrice.setText(String.valueOf(payment));
                    dlAddOrder.dispose();
                    MMt.displaydialog(dlComfirmOrder);
                } else {
                    JOptionPane.showMessageDialog(this, "Start date must before End date");
                }

            } else {
                System.out.println(dateChooseStart.getDate());
                System.out.println(datChooseEnd.getDate());
                String actName = cbAddOrderAct.getSelectedItem().toString();
                int timeID = cbAddOrderTime.getSelectedIndex() + 1;
                Date fromDate = dateChooseStart.getDate();
                cbAddOrderNN.removeAllItems();
                loadNanny(actName, timeID, fromDate);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Please fill all fields");
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        dlAddOrder.dispose();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        try {
            //thêm vào bảng order
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            String sDate = txtStart.getText();
            String eDate = txtEnd.getText();
            Date date1 = formatter.parse(sDate);
            Date date2 = formatter.parse(eDate);
            System.out.println(date1);
            System.out.println(date2);
            String birth = chBean.strofBirth(child_id);
            if (ob.inserOrder(
                    Float.valueOf(txtPrice.getText()),
                    date1, fMain.name(),
                    child_id,
                    date2,
                    nb.nametoID(txtNanny.getText()),
                    ob.getGroupID(birth),
                    cbAddOrderTime.getSelectedIndex() + 1)) {
                JOptionPane.showMessageDialog(this, "Saved!");
                closeDL();
                hiddenBtn();
            } else {
                JOptionPane.showMessageDialog(this, "Child was already register at selected times!");
            }
        } catch (ParseException ex) {
            Logger.getLogger(ChildAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        closeDL();
        MMt.displaydialog(dlAddOrder);
    }//GEN-LAST:event_jButton12ActionPerformed

    private void dateChooseStartPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dateChooseStartPropertyChange
        lbNN.setVisible(false);
        cbAddOrderNN.setVisible(false);
    }//GEN-LAST:event_dateChooseStartPropertyChange

    private void datChooseEndPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_datChooseEndPropertyChange
        lbNN.setVisible(false);
        cbAddOrderNN.setVisible(false);
    }//GEN-LAST:event_datChooseEndPropertyChange

    private void cbAddOrderActItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbAddOrderActItemStateChanged
        lbNN.setVisible(false);
        cbAddOrderNN.setVisible(false);
    }//GEN-LAST:event_cbAddOrderActItemStateChanged

    private void cbAddOrderTimeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbAddOrderTimeItemStateChanged
        lbNN.setVisible(false);
        cbAddOrderNN.setVisible(false);
    }//GEN-LAST:event_cbAddOrderTimeItemStateChanged

    NannyBean nb = new NannyBean();
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bntOk;
    private javax.swing.JButton bntOk1;
    private javax.swing.JButton bntReset1;
    private javax.swing.JButton btnActive;
    private javax.swing.JButton btnAddCus;
    private javax.swing.JButton btnCfDelete;
    private javax.swing.JButton btnCfDelete1;
    private javax.swing.JButton btnClearAddCuss;
    private javax.swing.JButton btnCreateOrder;
    private javax.swing.JButton btnNewChild;
    private javax.swing.JButton btnNewCus;
    private javax.swing.JButton btnSearchChild;
    private javax.swing.JButton btnSearchCus;
    private javax.swing.JButton btnStatusChild;
    private javax.swing.JButton btnStatusCus;
    private javax.swing.JButton btnUpdateChild;
    private javax.swing.JButton btnUpdateCus;
    private javax.swing.JComboBox cbAddOrderAct;
    private javax.swing.JComboBox cbAddOrderNN;
    private javax.swing.JComboBox cbAddOrderTime;
    private com.toedter.calendar.JDateChooser datChooseEnd;
    private com.toedter.calendar.JDateChooser dateChooseStart;
    private javax.swing.JDialog dlAddChild;
    private javax.swing.JDialog dlAddCus;
    private javax.swing.JDialog dlAddOrder;
    private javax.swing.JDialog dlCfChild;
    private javax.swing.JDialog dlCfCus;
    private javax.swing.JDialog dlComfirmOrder;
    private javax.swing.JDialog dlUpdateChild;
    private javax.swing.JDialog dlUpdateCus;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton9;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox jComboBox1;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lbNN;
    private javax.swing.JTable tbChild;
    private javax.swing.JTable tbCus;
    private javax.swing.JLabel txtAct;
    private javax.swing.JTextField txtAddOrChildID;
    private javax.swing.JTextField txtAddOrChildname;
    private javax.swing.JLabel txtAddOrderGroup;
    private javax.swing.JLabel txtChildName;
    private javax.swing.JTextField txtCurent;
    private javax.swing.JTextField txtDoctor;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JLabel txtEnd;
    private javax.swing.JLabel txtNanny;
    private javax.swing.JTextField txtNewChilIlle;
    private javax.swing.JTextField txtNewChild;
    private com.toedter.calendar.JDateChooser txtNewChildBirth;
    private javax.swing.JTextField txtNewChildDoctor;
    private javax.swing.JComboBox txtNewChildGender;
    private javax.swing.JTextField txtNewChildMedication;
    private javax.swing.JTextArea txtNewCusAddr;
    private javax.swing.JTextField txtNewCusEmail;
    private javax.swing.JTextField txtNewCusFone;
    private javax.swing.JTextField txtNewCusName;
    private javax.swing.JTextField txtNewCusWorkFone;
    private javax.swing.JTextField txtNewEmailParents;
    private javax.swing.JLabel txtOrderBy;
    private javax.swing.JTextField txtPass;
    private javax.swing.JLabel txtPrice;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtSearchCus;
    private javax.swing.JLabel txtStart;
    private javax.swing.JLabel txtTime;
    private javax.swing.JTextField txtUpdateCus;
    private javax.swing.JTextArea txtUpdateCusAddress;
    private javax.swing.JTextField txtUpdateCusName;
    private javax.swing.JTextField txtUpdateCusWorkP;
    private javax.swing.JTextField txtUpdateCusmobile;
    private javax.swing.JTextField txtfullname;
    // End of variables declaration//GEN-END:variables
}
