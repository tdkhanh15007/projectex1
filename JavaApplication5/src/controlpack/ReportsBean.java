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
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author K
 */
public class ReportsBean {

    MyDatabase dt = new MyDatabase();
    Connection conn = dt.getConnection();
    public int order_id;
    public int nanny_id;
    public String nanny_name;
    public Date orderDate;
    public String orderBy;
    public int price;
    public int payment;

    public ReportsBean(int order_id, String nanny_name, Date orderDate, String orderBy, int price, int payment) {
        this.order_id = order_id;
        this.nanny_name = nanny_name;
        this.orderDate = orderDate;
        this.orderBy = orderBy;
        this.price = price;
        this.payment = payment;
    }



    public ReportsBean() {
    }

    public Vector<ReportsBean> displayAll() {
        Vector<ReportsBean> v = new Vector<ReportsBean>();
        Instant now = Instant.now(); //current date
        Instant before = now.minus(Duration.ofDays(30));
        Date dateBefore = Date.from(before);
        try {
            PreparedStatement ps1 = conn.prepareStatement("select startdate from Orders");
            ResultSet rs1 = ps1.executeQuery();
            while (rs1.next()) {
                Date orderDate = new Date(rs1.getDate("startdate").getTime());
                if (orderDate.compareTo(dateBefore) > 0) {
                    ReportsBean rb = new ReportsBean();
                    PreparedStatement ps2 = conn.prepareStatement("select order_id,payment,user_email from Orders where startdate = ?");
                    java.sql.Date sqlDate = new java.sql.Date(orderDate.getTime());
                    rb.orderDate = orderDate;
                    ps2.setDate(1, sqlDate);
                    ResultSet rs2 = ps2.executeQuery();
                    while (rs2.next()) {
                        rb.order_id = rs2.getInt("order_id");
                        rb.price = rs2.getInt("payment");
                        rb.orderBy= rs2.getString("user_email");
                        PreparedStatement ps3 = conn.prepareStatement("select nanny_id,amount from Payments where order_id=?");
                        ps3.setInt(1, rb.order_id);
                        ResultSet rs3 = ps3.executeQuery();
                        while (rs3.next()) {
                            String nannyname = nannyidtoString(rs3.getInt("nanny_id"));
                            rb.nanny_name = nannyname;
                            rb.payment = rs3.getInt("amount");
                        }
                    }
                    v.add(rb);
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(ReportsBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        return v;
    }

    public Vector<ReportsBean> displayCondition(Date fromDate, Date toDate) {
        Vector<ReportsBean> v = new Vector<ReportsBean>();
        try {
            PreparedStatement ps1 = conn.prepareStatement("select startdate from Orders");
            ResultSet rs1 = ps1.executeQuery();
            while (rs1.next()) {
                Date orderDate = new Date(rs1.getDate("startdate").getTime());
                if(orderDate.compareTo(fromDate)>0&&orderDate.compareTo(toDate)<0){
                    ReportsBean rb = new ReportsBean();
                    PreparedStatement ps2 = conn.prepareStatement("select order_id,payment,user_email from Orders where startdate = ?");
                    java.sql.Date sqlDate = new java.sql.Date(orderDate.getTime());
                    rb.orderDate = orderDate;
                    ps2.setDate(1, sqlDate);
                    ResultSet rs2 = ps2.executeQuery();
                    while (rs2.next()) {
                        rb.order_id = rs2.getInt("order_id");
                        rb.price = rs2.getInt("payment");
                        rb.orderBy= rs2.getString("user_email");
                        PreparedStatement ps3 = conn.prepareStatement("select nanny_id,amount from Payments where order_id=?");
                        ps3.setInt(1, rb.order_id);
                        ResultSet rs3 = ps3.executeQuery();
                        while (rs3.next()) {
                            String nannyname = nannyidtoString(rs3.getInt("nanny_id"));
                            rb.nanny_name = nannyname;
                            rb.payment = rs3.getInt("amount");
                        }
                    }
                    v.add(rb);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReportsBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return v;
    }

//    public void setOrderID(Date utilDate) {
//        try {
//            PreparedStatement ps = conn.prepareStatement("select order_id,payment from Orders where startdate = ?");
//            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
//            ps.setDate(1, sqlDate);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                setOrder_id(rs.getInt("order_id"));
//                System.out.println(getOrder_id());
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(ReportsBean.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    public String nannyidtoString(int id) {
        String nanny_name = null;
        try {
            PreparedStatement ps = conn.prepareStatement("Select name from Nannies where nanny_id= ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                nanny_name = rs.getString("name");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReportsBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nanny_name;
    }
}
