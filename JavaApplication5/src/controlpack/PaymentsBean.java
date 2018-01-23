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
 * @author Khanh
 */
public class PaymentsBean {

    MyDatabase dt = new MyDatabase();
    Connection conn = dt.getConnection();
    public int nanny_id;
    public float paid;
    public int order_id;
    public String phone;

    public PaymentsBean() {
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

    public Vector<PaymentsBean> searchtoPaid(String phone) {
        Vector<PaymentsBean> v = new Vector<PaymentsBean>();
        try {
            int id = phonetoID(phone);
            PreparedStatement ps1 = conn.prepareStatement("select order_id from OrdersDetails where nanny_id=?");
            ps1.setInt(1, id);
            ResultSet rs1 = ps1.executeQuery();
            while (rs1.next()) {
                PaymentsBean nb = new PaymentsBean();

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

    public void changePay(int order_id) {
        try {
            PreparedStatement ps = conn.prepareStatement("delete from Payments where order_id=?");
            ps.setInt(1, order_id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(NannyBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
