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
            PreparedStatement ps1 = conn.prepareStatement("select child_id,fullname,birth,cus_email,gender from Chirldren");
            ResultSet rs1 = ps1.executeQuery();
            while(rs1.next()){
                ChildrenBean cb = new ChildrenBean();
                cb.child_id = rs1.getInt("child_id");
                cb.fullName = rs1.getString("fullname");
                cb.birth = new java.util.Date(rs1.getDate("birth").getTime());
                cb.cus_email = rs1.getString("cus_email");
                cb.gender = rs1.getBoolean("gender");
                v.add(cb);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ChildrenBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return v;
    }
    
    public String getcurrent_medications(int id){
        String current_medications = "";
        try {
            PreparedStatement ps = conn.prepareStatement("select current_medications from Chirldren where child_id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                current_medications = rs.getString("current_medications");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ChildrenBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return current_medications;
    }
    
    public String getpass_illess(int id){
        String pass_illess = "";
        try {
            PreparedStatement ps = conn.prepareStatement("select pass_illess from Chirldren where child_id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                pass_illess = rs.getString("pass_illess");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ChildrenBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pass_illess;
    }
    
    public String getdoctor(int id){
        String doctor = "";
        try {
            PreparedStatement ps = conn.prepareStatement("select doctor from Chirldren where child_id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                doctor = rs.getString("doctor");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ChildrenBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return doctor;
    }

}
