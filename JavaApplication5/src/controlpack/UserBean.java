/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlpack;

import MyDatabase.MyDatabase;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Khanh
 */
public class UserBean {

    MyDatabase dt = new MyDatabase();
    Connection conn = dt.getConnection();
    public String user;
    public boolean role;
    public String pass;
    public String ussname;
    public String phone;

    public boolean getUserRole(String user_email){
        boolean isAdmin = false;
        try {
            PreparedStatement ps = conn.prepareStatement("Select role from Users where user_email = ?");
            ps.setString(1, user_email);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                isAdmin = rs.getBoolean("role");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE, null, ex);
        }
         return isAdmin;
    }
    
    public Vector<UserBean> displayAll(){
        Vector<UserBean> v = new Vector<UserBean>();
        try {
            PreparedStatement ps = conn.prepareStatement("select user_email,phone,role,name from Users");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                UserBean us = new UserBean();
                us.ussname = rs.getString("name");
                us.user = rs.getString("user_email");
                us.phone = rs.getString("phone");
                us.role = rs.getBoolean("role");
                v.add(us);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return v;
    }
    
    public String md5Pass(String useremail){
        String md5pas ="";
        try {
            PreparedStatement ps = conn.prepareStatement("select password from Users where user_email=?");
            ps.setString(1, useremail);
            ResultSet rs= ps.executeQuery();
            while(rs.next()){
                md5pas = rs.getString("password");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return md5pas;
    }
    
    public void quickSet(String useremail,boolean role){
        try {
            PreparedStatement ps = conn.prepareStatement("update User set role = ? where user_email=?");
            ps.setBoolean(1, role);
            ps.setString(2, useremail);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean updateUser(String name,String phone,boolean role,String md5pass,String usermail){
        try {
            PreparedStatement ps = conn.prepareStatement("update Users set name=?,phone=?,role=?,password=? where user_email=?");
            ps.setString(1, name);
            ps.setString(2, phone);
            ps.setBoolean(3, role);
            ps.setString(4, md5pass);
            ps.setString(5, usermail);
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {            
            Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public String covertoMD5(String plainpass){
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(plainpass.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
    
    public boolean checkSyb(String s){
        boolean check = false;
        char syb[] = {'-','!','@','#','$','%','^','&','*','(',')','='};
        char a[] = s.toCharArray();
        for(int i=0; i<a.length; i++){
            for(int j=0; j<syb.length; j++){
                if(a[i]==syb[j]){
                    check = true;
                }
            }
        }
        return check;
    }
}
