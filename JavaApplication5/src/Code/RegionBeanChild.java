/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Code;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author student
 */
public class RegionBeanChild {

    private String fullName;
    private Date birth;
    private String current_medications;
    private String pass_illess;
    private String doctor;
    private String cus_email;
    private Boolean gender = true;

    public RegionBeanChild() {
    }

    public ArrayList<RegionBeanChild> showAllData() {
        ArrayList<RegionBeanChild> listRegion = new ArrayList<RegionBeanChild>();

        ConnectionData connData = new ConnectionData();
        Connection conn = connData.getConnection();
        String sql = "select * from Chirldren";

        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
//                
                RegionBeanChild rb = new RegionBeanChild(rs.getString("fullname"), rs.getDate("birth"), rs.getString("current_medications"), rs.getString("pass_illess"), rs.getString("doctor"), rs.getString("cus_email"), rs.getBoolean("gender"));
                listRegion.add(rb);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RegionBeanChild.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listRegion;
    }

    public void updateRegion(String fullName, Date birth, String current_medications, String pass_illess, String doctor, String cus_email, Boolean gender) throws SQLException {
        ConnectionData connData = new ConnectionData();
        Connection conn = connData.getConnection();

        String sql = "update Children set fullname = ?, birth = ?, current_medications = ?, pass_illess = ?, doctor = ?,"
                + "gender = ? where cus_email=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        
        ps.setString(1,fullName);
        ps.setDate(2, birth);
        ps.setString(3, current_medications);
        ps.setString(4, pass_illess);
        ps.setString(5, doctor);
        ps.setBoolean(6, gender);
        ps.setString(7, cus_email);
        ps.executeUpdate();

    }

    public void insertRegion(String fullName, Date birth, String current_medications, String pass_illess, String doctor, String cus_email, Boolean gender) throws SQLException {
        ConnectionData connData = new ConnectionData();
        Connection conn = connData.getConnection();

        String sql = "insert into Chirldren (fullname, birth, current_medications, pass_illess, doctor, cus_email, gender) values (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        
        ps.setString(1,fullName);
        ps.setDate(2, birth);
        ps.setString(3, current_medications);
        ps.setString(4, pass_illess);
        ps.setString(5, doctor);
        ps.setString(6, cus_email);
        ps.setBoolean(7, gender);
        ps.executeUpdate();

    }

    public RegionBeanChild(String fullName, Date birth, String current_medications, String pass_illess, String doctor, String cus_email, Boolean gender) {
        this.fullName = fullName;
        this.birth = birth;
        this.current_medications = current_medications;
        this.pass_illess = pass_illess;
        this.doctor = doctor;
        this.cus_email = cus_email;
        this.gender = gender;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String firstname) {
        this.fullName = firstname;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getCurrent_medications() {
        return current_medications;
    }

    public void setCurrent_medications(String current_medications) {
        this.current_medications = current_medications;
    }

    public String getPass_illess() {
        return pass_illess;
    }

    public void setPass_illess(String pass_illess) {
        this.pass_illess = pass_illess;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getCus_email() {
        return cus_email;
    }

    public void setCus_email(String cus_email) {
        this.cus_email = cus_email;
    }

    public Boolean isGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }
}
