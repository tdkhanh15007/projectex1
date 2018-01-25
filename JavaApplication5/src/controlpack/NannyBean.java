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
import java.sql.Statement;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Khanh
 */
public class NannyBean {

    MyDatabase dt = new MyDatabase();
    Connection conn = dt.getConnection();
    public String nanny_name;
    public int nanny_id;
    public String address;
    public String phone;
    public String act_name;
    public int act_id;
    public boolean status;
    public float paid;
    public int order_id;

    public Vector<NannyBean> loadAll() {
        Vector<NannyBean> v = new Vector<NannyBean>();
        try {
            PreparedStatement ps = conn.prepareStatement("select nanny_id,name,phone,activity_id,status from Nannies order by activity_id");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NannyBean nb = new NannyBean();
                nb.nanny_id = rs.getInt("nanny_id");
                nb.nanny_name = rs.getString("name");
                nb.phone = rs.getString("phone");
                nb.status = rs.getBoolean("status");
                PreparedStatement ps2 = conn.prepareStatement("select activity from Activity where activity_id=?");
                ps2.setInt(1, rs.getInt("activity_id"));
                ResultSet rs2 = ps2.executeQuery();
                while (rs2.next()) {
                    nb.act_name = rs2.getString("activity");
                }
                v.add(nb);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NannyBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return v;
    }

    public Vector<NannyBean> searchNanny(String character) {
        Vector<NannyBean> v = new Vector<NannyBean>();
        try {
            PreparedStatement ps = conn.prepareStatement("select nanny_id,name,phone,activity_id,status from Nannies where name like '%" + character + "%' order by activity_id");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NannyBean nb = new NannyBean();
                nb.nanny_id = rs.getInt("nanny_id");
                nb.nanny_name = rs.getString("name");
                nb.phone = rs.getString("phone");
                nb.status = rs.getBoolean("status");
                PreparedStatement ps2 = conn.prepareStatement("select activity from Activity where activity_id=?");
                ps2.setInt(1, rs.getInt("activity_id"));
                ResultSet rs2 = ps2.executeQuery();
                while (rs2.next()) {
                    nb.act_name = rs2.getString("activity");
                }
                v.add(nb);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NannyBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return v;
    }

    public boolean checkExist(String phone) {
        try {
            PreparedStatement ps = conn.prepareStatement("select nanny_id from Nannies where phone=?");
            ps.setString(1, phone);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException ex) {
            Logger.getLogger(NannyBean.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean checkforUpdate(int id, String phone) {
        try {
            PreparedStatement ps = conn.prepareStatement("select name from Nannies where phone=? and nanny_id=?");
            ps.setString(1, phone);
            ps.setInt(2, id);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException ex) {
            Logger.getLogger(NannyBean.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public int phonetoID(String phone) {
        int id = 0;
        try {
            PreparedStatement ps = conn.prepareStatement("select nanny_id from Nannies where phone=?");
            ps.setString(1, phone);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getInt("nanny_id");
            }
        } catch (SQLException ex) {
            Logger.getLogger(NannyBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    public int nametoID(String phone) {
        int id = 0;
        try {
            PreparedStatement ps = conn.prepareStatement("select nanny_id from Nannies where name=?");
            ps.setString(1, phone);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getInt("nanny_id");
            }
        } catch (SQLException ex) {
            Logger.getLogger(NannyBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    public boolean createNanny(String name, String addr, String phone, int act_id) {
        if (!checkExist(phone)) {
            try {
                PreparedStatement ps = conn.prepareStatement("insert into Nannies(name,address,phone,activity_id,status) values(?,?,?,?,?)");
                ps.setString(1, name);
                ps.setString(2, addr);
                ps.setString(3, phone);
                ps.setInt(4, act_id);
                ps.setBoolean(5, true);
                ps.executeUpdate();
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(NannyBean.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        }
        return false;
    }

    public boolean updateNanny(String name, String addr, String phone, int act_id, int nannyid) {
        if (checkforUpdate(nannyid, phone)) {
            try {
                PreparedStatement ps = conn.prepareStatement("update Nannies set name=?,address=?,phone=?,activity_id=? where nanny_id=?");
                ps.setString(1, name);
                ps.setString(2, addr);
                ps.setString(3, phone);
                ps.setInt(4, act_id);
                ps.setInt(5, nannyid);
                ps.executeUpdate();
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(NannyBean.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        } else {
            if (!checkExist(phone)) {
                try {
                    PreparedStatement ps = conn.prepareStatement("update Nannies set name=?,address=?,phone=?,activity_id=? where nanny_id=?");
                    ps.setString(1, name);
                    ps.setString(2, addr);
                    ps.setString(3, phone);
                    ps.setInt(4, act_id);
                    ps.setInt(5, nannyid);
                    ps.executeUpdate();
                    return true;
                } catch (SQLException ex) {
                    Logger.getLogger(NannyBean.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                }
            }
        }
        return false;
    }

    public void deleteNanny(String phone, boolean status) {
        try {
            PreparedStatement ps = conn.prepareStatement("update Nannies set status = ? where phone=?");
            ps.setString(2, phone);
            ps.setBoolean(1, status);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(NannyBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Vector<NannyBean> searchtoPaid(String phone) {
        Vector<NannyBean> v = new Vector<NannyBean>();
        try {
            int id = phonetoID(phone);
            PreparedStatement ps1 = conn.prepareStatement("select order_id from OrdersDetails where nanny_id=?");
            ps1.setInt(1, id);
            ResultSet rs1 = ps1.executeQuery();
            while (rs1.next()) {
                NannyBean nb = new NannyBean();

                nb.order_id = rs1.getInt("order_id");
                PreparedStatement ps2 = conn.prepareStatement("select payment from Orders where order_id=?");
                ps2.setInt(1, rs1.getInt("order_id"));
                ResultSet rs2 = ps2.executeQuery();
                while (rs2.next()) {
                    nb.paid = rs2.getFloat("payment") / 2;

                }
                v.add(nb);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NannyBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return v;
    }

    public boolean paidorhold(int orderid) {
        try {
            PreparedStatement ps = conn.prepareStatement("select payment_id from Payments where order_id=?");
            ps.setInt(1, orderid);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException ex) {
            return false;
        }
    }

    public void paidforNanny(int order_id) {
        try {
            float paid = 0;
            int nanny_id = 0;
            PreparedStatement ps1 = conn.prepareStatement("select payment from Orders where order_id=?");
            ps1.setInt(1, order_id);
            ResultSet rs1 = ps1.executeQuery();
            while (rs1.next()) {
                paid = rs1.getFloat("payment") / 2;
            }
            PreparedStatement ps2 = conn.prepareStatement("select nanny_id from OrdersDetails where order_id=?");
            ps2.setInt(1, order_id);
            ResultSet rs2 = ps2.executeQuery();
            while (rs2.next()) {
                nanny_id = rs2.getInt("nanny_id");
            }
            PreparedStatement ps3 = conn.prepareStatement("insert into Payments(nanny_id,order_id,amount) values(?,?,?)");
            ps3.setInt(1, nanny_id);
            ps3.setInt(2, order_id);
            ps3.setFloat(3, paid);
            ps3.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(NannyBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void changePay(int order_id, boolean status) {
        try {
            if (status) {
                paidforNanny(order_id);
            } else {
                PreparedStatement ps = conn.prepareStatement("delete from Payments where order_id=?");
                ps.setInt(1, order_id);
                ps.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(NannyBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public String getAddr(String phone) {
        String addr = "";
        try {
            PreparedStatement ps = conn.prepareStatement("select address from Nannies where phone =?");
            ps.setString(1, phone);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                addr = rs.getString("address");
            }
        } catch (SQLException ex) {
            Logger.getLogger(NannyBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return addr;
    }

    public String phonetoName(String phone) {
        String name = "";
        try {
            PreparedStatement ps = conn.prepareStatement("select name from Nannies where phone=?");
            ps.setString(1, phone);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                name = rs.getString("name");
            }
        } catch (SQLException ex) {
            Logger.getLogger(NannyBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return name;
    }

    public Vector<NannyBean> tbReport(Date date1, Date date2, String phone) {
        Vector<NannyBean> v = new Vector<NannyBean>();
        try {
            int nannyid = phonetoID(phone);
            PreparedStatement ps = conn.prepareStatement("select order_id from OrdersDetails where nanny_id=?");
            ps.setInt(1, nannyid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                PreparedStatement ps1 = conn.prepareStatement("select startdate,enddate from Orders where order_id=?");
                ps1.setInt(1, rs.getInt("order_id"));
                ResultSet rs1 = ps1.executeQuery();
                while (rs1.next()) {
                    Date enddate = new Date(rs1.getDate("enddate").getTime());
                    Date sdate = new Date(rs1.getDate("startdate").getTime());
                    if (date1.compareTo(sdate) < 0 && date2.compareTo(enddate) > 0) {
                        NannyBean nb = new NannyBean();
                        nb.order_id = rs.getInt("order_id");
                        PreparedStatement ps2 = conn.prepareStatement("select payment_id,amount from Payments where order_id=?");
                        ps2.setInt(1, rs.getInt("order_id"));
                        ResultSet rs2 = ps2.executeQuery();
                        while(rs2.next()){
                            nb.paid = rs2.getFloat("amount");
                            nb.nanny_id = rs2.getInt("payment_id");
                        }
                        v.add(nb);
                    }
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(NannyBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return v;
    }
    public String getCmt(int id){
        String cmt = "";
        try {
            PreparedStatement ps = conn.prepareStatement("select comments from Orders where order_id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                cmt = rs.getString("comments");
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cmt;
    }
    
    public float getPay(int id){
        float cmt = 0;
        try {
            PreparedStatement ps = conn.prepareStatement("select payment from Orders where order_id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                cmt = rs.getFloat("payment");
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cmt;
    }
}
