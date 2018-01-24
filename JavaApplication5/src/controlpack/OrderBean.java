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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author K
 */
public class OrderBean {

    MyDatabase dt = new MyDatabase();
    Connection conn = dt.getConnection();
    public int order_id;
    public String child_name;
    public String cus_name;
    public String nanny_name;
    public String act_name;
    public String time_slot;
    public Date startDate;
    public Date endDate;
    public float payments;
    public String cmts;

    public Vector<OrderBean> loadAll(String cusname) {
        Vector<OrderBean> v = new Vector<OrderBean>();
        try {
            PreparedStatement ps4 = conn.prepareStatement("select enddate,order_id from Orders");
            ResultSet rs4 = ps4.executeQuery();
            Date now = new Date();
            while (rs4.next()) {
                Date enddate = new Date(rs4.getDate("enddate").getTime());
                if (now.compareTo(enddate) > 0) {
                    PreparedStatement ps5 = conn.prepareStatement("update Orders set status = 'false' where order_id=?");
                    ps5.setInt(1, rs4.getInt("order_id"));
                    ps5.executeUpdate();
                }
            }
            PreparedStatement ps = conn.prepareStatement("select cus_email from Customer where cus_name=?");
            ps.setString(1, cusname);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String cus_email = rs.getString("cus_email");
                PreparedStatement ps1 = conn.prepareStatement("select child_id,fullname from Chirldren where cus_email=?");
                ps1.setString(1, cus_email);
                ResultSet rs1 = ps1.executeQuery();
                while (rs1.next()) {
                    OrderBean ob = new OrderBean();
                    String childname = rs1.getString("fullname");
                    int childID = rs1.getInt("child_id");
                    PreparedStatement ps2 = conn.prepareStatement("select order_id from Orders where child_id=? and status='true'");
                    ps2.setInt(1, childID);
                    ResultSet rs2 = ps2.executeQuery();
                    while (rs2.next()) {
                        int orID = rs2.getInt("order_id");
                        ob.order_id = orID;
                        PreparedStatement ps3 = conn.prepareStatement("select nanny_id,hour_id from OrdersDetails where order_id=?");
                        ps3.setInt(1, orID);
                        ResultSet rs3 = ps3.executeQuery();
                        while (rs3.next()) {
                            ob.child_name = childname;
                            ob.cus_name = cusname;
                            ob.nanny_name = getNannyname(rs3.getInt("nanny_id"));
                            ob.act_name = getAct(rs3.getInt("nanny_id"));
                            ob.time_slot = getHour(rs3.getInt("hour_id"));
                        }
                    }
                    v.add(ob);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return v;
    }

    public boolean checkbeforeSearch(String cusname) {
        try {
            PreparedStatement ps = conn.prepareStatement("select cus_email from Customer where cus_name=?");
            ps.setString(1, cusname);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String cus_email = rs.getString("cus_email");
                PreparedStatement ps1 = conn.prepareStatement("select child_id,fullname from Chirldren where cus_email=?");
                ps1.setString(1, cus_email);
                ResultSet rs1 = ps1.executeQuery();
                while(rs1.next()){
                    int childID = rs1.getInt("child_id");
                    PreparedStatement ps2 = conn.prepareStatement("select order_id,status from Orders where child_id=?");
                    ps2.setInt(1, childID);
                    ResultSet rs2 = ps2.executeQuery();
                    while(rs2.next()){
                        return rs2.getBoolean("status");
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public String getNannyname(int id) {
        String name = "";
        try {
            PreparedStatement ps = conn.prepareStatement("select name from Nannies where nanny_id =?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                name = rs.getString("name");
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return name;
    }

    public String getAct(int id) {
        String name = "";
        try {
            PreparedStatement ps = conn.prepareStatement("select activity_id from Nannies where nanny_id =?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int act_id = rs.getInt("activity_id");
                PreparedStatement ps1 = conn.prepareStatement("select activity from Activity where activity_id=?");
                ps1.setInt(1, act_id);
                ResultSet rs1 = ps1.executeQuery();
                while (rs1.next()) {
                    name = rs1.getString("activity");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return name;
    }

    public String getHour(int id) {
        String name = "";
        try {
            PreparedStatement ps = conn.prepareStatement("select timer from Hours where hour_id =?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                name = rs.getString("timer");
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return name;
    }
    
    public void setStatus(int orID){
        try {
            PreparedStatement ps = conn.prepareStatement("update Orders set status = 'false' where order_id=?");
            ps.setInt(1, orID);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(OrderBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Vector<OrderBean> onclickTable(int id){
        Vector<OrderBean> v = new Vector<OrderBean>();
        try {
            PreparedStatement ps = conn.prepareStatement("select startdate,enddate,payment,comments from Orders where order_id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                OrderBean ob = new OrderBean();
                Date startdate = new Date(rs.getDate("startdate").getTime());
                Date enddate = new Date(rs.getDate("enddate").getTime());
                float payment = rs.getFloat("payment");
                String cmt = rs.getString("comments");
                ob.startDate= startdate;
                ob.endDate = enddate;
                ob.payments = payment;
                ob.cmts = cmt;
                v.add(ob);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return v;
    }
}
