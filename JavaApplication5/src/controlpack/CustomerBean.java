/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controlpack;

import MyDatabase.MyDatabase;
import java.sql.Connection;
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
public class CustomerBean {
    MyDatabase dt = new MyDatabase();
    Connection conn = dt.getConnection();
    public String cus_email;
    public String cus_name;
    public String address;
    public String phone_number;
    public String work_phone;
    public boolean status;

    public CustomerBean(String cus_email, String cus_name, String phone_number, String work_phone, boolean status) {
        this.cus_email = cus_email;
        this.cus_name = cus_name;
        this.phone_number = phone_number;
        this.work_phone = work_phone;
        this.status = status;
    }

    public CustomerBean(String cus_email, String cus_name, String address, String phone_number, String work_phone, boolean status) {
        this.cus_email = cus_email;
        this.cus_name = cus_name;
        this.address = address;
        this.phone_number = phone_number;
        this.work_phone = work_phone;
        this.status = status;
    }
    
    

    public CustomerBean() {
    }
    public Vector<CustomerBean> displayAll(boolean check){
        Vector<CustomerBean> v = new Vector<CustomerBean>();
        try {
            String sql = "";
            if(check){
                sql = "select cus_email,cus_name,phone_number,work_phone,status from Customer";
            }else{
                sql = "select cus_email,cus_name,phone_number,work_phone,status from Customer where status='true'";
            }
            PreparedStatement ps1 = conn.prepareStatement(sql);
            ResultSet rs1 = ps1.executeQuery();
            while(rs1.next()){
                CustomerBean cb = new CustomerBean();
                cb.cus_email = rs1.getString("cus_email");
                cb.cus_name = rs1.getString("cus_name");
                cb.phone_number = rs1.getString("phone_number");
                cb.work_phone = rs1.getString("work_phone");
                cb.status = rs1.getBoolean("status");
                v.add(cb);
            }
            ps1.close();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return v;
    }
//    public Vector<CustomerBean> displayHide(){
//        Vector<CustomerBean> v = new Vector<CustomerBean>();
//        try {
//            PreparedStatement ps1 = conn.prepareStatement("select cus_email,cus_name,phone_number,work_phone,status from Customer where status=?");
//            ps1.setBoolean(1, false);
//            ResultSet rs1 = ps1.executeQuery();
//            while(rs1.next()){
//                CustomerBean cb = new CustomerBean();
//                cb.cus_email = rs1.getString("cus_email");
//                cb.cus_name = rs1.getString("cus_name");
//                cb.phone_number = rs1.getString("phone_number");
//                cb.work_phone = rs1.getString("work_phone");
//                cb.status = rs1.getBoolean("status");
//                v.add(cb);
//            }
//            ps1.close();
//        } catch (SQLException ex) {
//            Logger.getLogger(CustomerBean.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return v;
//    }
    
    public void addCus(String cus_email, String cus_name, String address, String phone_number, String work_phone){
        try {
            PreparedStatement ps = conn.prepareStatement("insert into Customer(cus_email,cus_name,address,phone_number,work_phone,status) values(?,?,?,?,?,?)");
            ps.setString(1, cus_email);
            ps.setString(2, cus_name);
            ps.setString(3, address);
            ps.setString(4, phone_number);
            ps.setString(5, work_phone);
            ps.setBoolean(6, true);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateCus(String cus_email, String cus_name, String address, String phone_number, String work_phone){
        try {
            PreparedStatement ps = conn.prepareStatement("update Customer set cus_name=?,address=?,phone_number=?,work_phone=? where cus_email=?");
            ps.setString(1, cus_name);
            ps.setString(2, address);
            ps.setString(3, phone_number);
            ps.setString(4, work_phone);
            ps.setString(5, cus_email);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public boolean isExist(String cus_mail){
        try {
            PreparedStatement ps = conn.prepareStatement("Select cus_name from Customer where cus_email = ?");
            ps.setString(1, cus_mail);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerBean.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public void deleteCus(String cus_mail){
        try {
            PreparedStatement ps = conn.prepareStatement("update Customer set status=? where cus_email=?");
            ps.setBoolean(1, false);
            ps.setString(2, cus_mail);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void activeCus(String cus_mail){
        try {
            PreparedStatement ps = conn.prepareStatement("update Customer set status=? where cus_email=?");
            ps.setBoolean(1, true);
            ps.setString(2, cus_mail);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Vector<CustomerBean> searchCus(String character,boolean checked){
        Vector<CustomerBean> v = new Vector<CustomerBean>();
        try {
            String sql = "";
            if(character.contains("@")){
                if(checked){
                  sql = "select cus_email,cus_name,phone_number,work_phone,status from Customer where cus_email like '%" + character + "%'";  
                }
                else{
                    sql = "select cus_email,cus_name,phone_number,work_phone,status from Customer where status='true' and cus_email like '%" + character + "%'";  
                }
            }
            else{
                if(checked){
                    sql = "select cus_email,cus_name,phone_number,work_phone,status from Customer where cus_name like '%" + character + "%'";
                }
                else{
                    sql = "select cus_email,cus_name,phone_number,work_phone,status from Customer where status='true' and cus_name like '%" + character + "%'";
                }                
            }
            PreparedStatement ps1 = conn.prepareStatement(sql);
            ResultSet rs1 = ps1.executeQuery();
            while(rs1.next()){
                CustomerBean cb = new CustomerBean();
                cb.cus_email = rs1.getString("cus_email");
                cb.cus_name = rs1.getString("cus_name");
                cb.phone_number = rs1.getString("phone_number");
                cb.work_phone = rs1.getString("work_phone");
                cb.status = rs1.getBoolean("status");
                v.add(cb);
            }
            ps1.close();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return v;
    }
    public String getCusAdd(String cusMail){
        String cusAdd = "";
        try {
            PreparedStatement ps = conn.prepareStatement("select address from Customer where cus_email =?");
            ps.setString(1, cusMail);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                cusAdd = rs.getString("address");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cusAdd;
    }
}
