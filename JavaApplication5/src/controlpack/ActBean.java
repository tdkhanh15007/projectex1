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
public class ActBean {

    MyDatabase dt = new MyDatabase();
    Connection conn = dt.getConnection();
    public String act_type;
    public float charges;
    public int act_id;    
    
    public ActBean() {
    }

    public Vector<ActBean> displayAll() {
        Vector<ActBean> v = new Vector<ActBean>();
        try {
            PreparedStatement ps = conn.prepareStatement("select activity_id,activity,activity_charges from Activity");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ActBean gb = new ActBean();
                gb.act_id = rs.getInt("activity_id");
                gb.act_type = rs.getString("activity");
                gb.charges = rs.getFloat("activity_charges");
                v.add(gb);
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(ActBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return v;
    }
    
    public boolean createGroup(String grouptype, float groupcharges) {
        if (!isExist(grouptype)) {
            try {
                PreparedStatement ps = conn.prepareStatement("insert into Activity(activity,activity_charges) values(?,?)");
                ps.setString(1, grouptype);
                ps.setFloat(2, groupcharges);
                ps.executeUpdate();
                ps.close();
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(ActBean.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        } else {
            return false;
        }
    }
    
    public boolean updateGroup(int groupid, String grouptype, float groupcharges) {
        try {
            PreparedStatement ps = conn.prepareStatement("update Activity set activity=?,activity_charges=? where activity_id=?");
            ps.setString(1, grouptype);
            ps.setFloat(2, groupcharges);
            ps.setInt(3, groupid);
            ps.executeUpdate();
            ps.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ActBean.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean isExist(String activity) {
        try {
            PreparedStatement ps = conn.prepareStatement("select activity_id from Activity where activity=?");
            ps.setString(1, activity);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException ex) {
            Logger.getLogger(ActBean.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public int nametoID(String name) {
        int id = 0;
        try {
            PreparedStatement ps = conn.prepareStatement("select activity_id from Activity where activity=?");
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getInt("activity_id");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ActBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }
}
