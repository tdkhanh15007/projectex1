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
}
