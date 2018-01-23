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
public class GroupsBean {

    MyDatabase dt = new MyDatabase();
    Connection conn = dt.getConnection();
    public String group_type;
    public float charges;
    public int group_id;

    public GroupsBean() {
    }

    public Vector<GroupsBean> displayAll() {
        Vector<GroupsBean> v = new Vector<GroupsBean>();
        try {
            PreparedStatement ps = conn.prepareStatement("select group_id,group_type,group_charges from Groups");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                GroupsBean gb = new GroupsBean();
                gb.group_id = rs.getInt("group_id");
                gb.group_type = rs.getString("group_type");
                gb.charges = rs.getFloat("group_charges");
                v.add(gb);
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(GroupsBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return v;
    }

    public boolean createGroup(String grouptype, float groupcharges) {
        if (!isExist(grouptype)) {
            try {
                PreparedStatement ps = conn.prepareStatement("insert into Groups(group_type,group_charges) values(?,?)");
                ps.setString(1, grouptype);
                ps.setFloat(2, groupcharges);
                ps.executeUpdate();
                ps.close();
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(GroupsBean.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean updateGroup(int groupid, String grouptype, float groupcharges) {
        try {
            PreparedStatement ps = conn.prepareStatement("update Groups set group_type=?,group_charges=? where group_id=?");
            ps.setString(1, grouptype);
            ps.setFloat(2, groupcharges);
            ps.setInt(3, groupid);
            ps.executeUpdate();
            ps.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(GroupsBean.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean isExist(String grouptype) {
        try {
            PreparedStatement ps = conn.prepareStatement("select group_id from Groups where group_type=?");
            ps.setString(1, grouptype);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException ex) {
            Logger.getLogger(GroupsBean.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
