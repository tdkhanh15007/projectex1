/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlpack;

import MyDatabase.MyDatabase;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author K
 */
public class ChildrenBean {

    MyDatabase dt = new MyDatabase();
    Connection conn = dt.getConnection();
    public int child_id;
    public String fullName;
    public Date birth;
    public String current_medications;
    public String pass_illess;
    public String doctor;
    public String cus_email;
    public boolean gender;

    public ChildrenBean(int child_id, String fullName, Date birth, String cus_email, boolean gender) {
        this.child_id = child_id;
        this.fullName = fullName;
        this.birth = birth;
        this.cus_email = cus_email;
        this.gender = gender;
    }

    public ChildrenBean(int child_id, String fullName, Date birth, String current_medications, String pass_illess, String doctor, String cus_email, boolean gender) {
        this.child_id = child_id;
        this.fullName = fullName;
        this.birth = birth;
        this.current_medications = current_medications;
        this.pass_illess = pass_illess;
        this.doctor = doctor;
        this.cus_email = cus_email;
        this.gender = gender;
    }

    public ChildrenBean() {
    }

    public Vector<ChildrenBean> displayAll() {
        Vector<ChildrenBean> v = new Vector<ChildrenBean>();
        try {
            PreparedStatement ps2 = conn.prepareStatement("select cus_email from Customer where status = 'true'");
            ResultSet rs2 = ps2.executeQuery();
            while (rs2.next()) {
                PreparedStatement ps1 = conn.prepareStatement("select child_id,fullname,birth,gender from Chirldren where cus_email = ? and status='true'");
                ps1.setString(1, rs2.getString("cus_email"));
                ResultSet rs1 = ps1.executeQuery();
                while (rs1.next()) {
                    ChildrenBean cb = new ChildrenBean();
                    cb.child_id = rs1.getInt("child_id");
                    cb.fullName = rs1.getString("fullname");
                    cb.birth = new java.util.Date(rs1.getDate("birth").getTime());
                    cb.cus_email = rs2.getString("cus_email");
                    cb.gender = rs1.getBoolean("gender");
                    v.add(cb);
                }
                ps1.close();
            }
            ps2.close();
        } catch (SQLException ex) {
            Logger.getLogger(ChildrenBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return v;
    }

    public Vector<ChildrenBean> searchChild(String character) {
        Vector<ChildrenBean> v = new Vector<ChildrenBean>();
        if (checkName(character)) {
            try {
                PreparedStatement ps2 = conn.prepareStatement("select cus_email from Customer where status = 'true'");
                ResultSet rs2 = ps2.executeQuery();
                while (rs2.next()) {
                    PreparedStatement ps1 = conn.prepareStatement("select child_id,fullname,birth,gender from Chirldren where cus_email = ? and fullname like '%" + character + "%' and status = 'true'");
                    ps1.setString(1, rs2.getString("cus_email"));
                    ResultSet rs1 = ps1.executeQuery();
                    while (rs1.next()) {
                        ChildrenBean cb = new ChildrenBean();
                        cb.child_id = rs1.getInt("child_id");
                        cb.fullName = rs1.getString("fullname");
                        cb.birth = new java.util.Date(rs1.getDate("birth").getTime());
                        cb.cus_email = rs2.getString("cus_email");
                        cb.gender = rs1.getBoolean("gender");
                        v.add(cb);
                    }
                }
                ps2.close();
            } catch (SQLException ex) {
                Logger.getLogger(ChildrenBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                PreparedStatement ps3 = conn.prepareStatement("select cus_email from Customer where cus_name like '%" + character + "%' and status = 'true'");
                ResultSet rs3 = ps3.executeQuery();
                while (rs3.next()) {
                    String cusmail = rs3.getString("cus_email");
                    PreparedStatement ps4 = conn.prepareStatement("select child_id,fullname,birth,gender from Chirldren where cus_email = ?");
                    ps4.setString(1, cusmail);
                    ResultSet rs4 = ps4.executeQuery();
                    while (rs4.next()) {
                        ChildrenBean cb = new ChildrenBean();
                        cb.child_id = rs4.getInt("child_id");
                        cb.fullName = rs4.getString("fullname");
                        cb.birth = new java.util.Date(rs4.getDate("birth").getTime());
                        cb.cus_email = cusmail;
                        cb.gender = rs4.getBoolean("gender");
                        v.add(cb);
                    }
                }
                ps3.close();
            } catch (SQLException ex) {
                Logger.getLogger(ChildrenBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return v;
    }

    public boolean checkName(String character) {
        try {
            PreparedStatement ps1 = conn.prepareStatement("select child_id from Chirldren where fullname like '%" + character + "%'");
            ResultSet rs = ps1.executeQuery();
            return rs.next();
        } catch (SQLException ex) {
            Logger.getLogger(ChildrenBean.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public String getcurrent_medications(int id) {
        String current_medications = "";
        try {
            PreparedStatement ps = conn.prepareStatement("select current_medications from Chirldren where child_id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                current_medications = rs.getString("current_medications");
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(ChildrenBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return current_medications;
    }

    public String getpass_illess(int id) {
        String pass_illess = "";
        try {
            PreparedStatement ps = conn.prepareStatement("select pass_illess from Chirldren where child_id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                pass_illess = rs.getString("pass_illess");
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(ChildrenBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pass_illess;
    }

    public String getdoctor(int id) {
        String doctor = "";
        try {
            PreparedStatement ps = conn.prepareStatement("select doctor from Chirldren where child_id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                doctor = rs.getString("doctor");
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(ChildrenBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return doctor;
    }

    public boolean updateChild(int child_id, String fullName, Date birth, String current_medications, String pass_illess, String doctor, boolean gender) {
        try {
            PreparedStatement ps = conn.prepareStatement("update Chirldren set fullname=?,birth=?,current_medications=?,pass_illess=?,doctor=?,gender=? where child_id=?");
            ps.setString(1, fullName);
            ps.setDate(2, new java.sql.Date(birth.getTime()));
            ps.setString(3, current_medications);
            ps.setString(4, pass_illess);
            ps.setString(5, doctor);
            ps.setBoolean(6, gender);
            ps.setInt(7, child_id);
            ps.executeUpdate();
            ps.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ChildrenBean.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public void deleteChild(int child_id) {
        try {
            PreparedStatement ps = conn.prepareStatement("update Chirldren set status = 'false' where child_id = ?");
            ps.setInt(1, child_id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ChildrenBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addChild(String fullName, Date birth, String current_medications, String pass_illess, String doctor, boolean gender, String cusmail) {
        try {
            PreparedStatement ps = conn.prepareStatement("insert into Chirldren(fullname,birth,current_medications,pass_illess,doctor,cus_email,gender,status) values(?,?,?,?,?,?,?,?)");
            ps.setString(1, fullName);
            ps.setDate(2, new java.sql.Date(birth.getTime()));
            ps.setString(3, current_medications);
            ps.setString(4, pass_illess);
            ps.setString(5, doctor);
            ps.setString(6, cusmail);
            ps.setBoolean(7, gender);
            ps.setBoolean(8, true);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(ChildrenBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean isExist(String cusmail, String childname) {
        try {
            PreparedStatement ps = conn.prepareStatement("select child_id from Chirldren where cus_email=? and fullName=? and status = 'true'");
            ps.setString(1, cusmail);
            ps.setString(2, childname);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException ex) {
            Logger.getLogger(ChildrenBean.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public String strofBirth(int id){
        String str = "";
        try {
            PreparedStatement ps = conn.prepareStatement("select birth from Chirldren where child_id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Date birth = new java.util.Date(rs.getDate("birth").getTime());
                SimpleDateFormat form = new SimpleDateFormat("dd/mm/yyyy");
                str = form.format(birth);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ChildrenBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return str;
    }

}
