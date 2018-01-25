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
    public String cusname;
    public Date orderDate;
    public String orderBy;
    public int price;
    public int payment;
    OrderBean ob = new OrderBean();

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
                        rb.orderBy = rs2.getString("user_email");
                        PreparedStatement ps4 = conn.prepareStatement("select nanny_id from OrdersDetails where order_id=?");
                        ps4.setInt(1, rs2.getInt("order_id"));
                        ResultSet rs4 = ps4.executeQuery();
                        while (rs4.next()) {
                            String nannyname = nannyidtoString(rs4.getInt("nanny_id"));
                            System.out.println(nannyname);
                            rb.nanny_name = nannyname;
                        }
                        PreparedStatement ps3 = conn.prepareStatement("select amount from Payments where order_id=?");
                        ps3.setInt(1, rb.order_id);
                        ResultSet rs3 = ps3.executeQuery();
                        while (rs3.next()) {
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
                if (orderDate.compareTo(fromDate) > 0 && orderDate.compareTo(toDate) < 0) {
                    ReportsBean rb = new ReportsBean();
                    PreparedStatement ps2 = conn.prepareStatement("select order_id,payment,user_email from Orders where startdate = ?");
                    java.sql.Date sqlDate = new java.sql.Date(orderDate.getTime());
                    rb.orderDate = orderDate;
                    ps2.setDate(1, sqlDate);
                    ResultSet rs2 = ps2.executeQuery();
                    while (rs2.next()) {
                        rb.order_id = rs2.getInt("order_id");
                        rb.price = rs2.getInt("payment");
                        rb.orderBy = rs2.getString("user_email");

                        PreparedStatement ps4 = conn.prepareStatement("select nanny_id from OrdersDetails where order_id=?");
                        ps4.setInt(1, rs2.getInt("order_id"));
                        ResultSet rs4 = ps4.executeQuery();
                        while (rs4.next()) {
                            String nannyname = nannyidtoString(rs4.getInt("nanny_id"));
                            rb.nanny_name = nannyname;
                        }
                        PreparedStatement ps3 = conn.prepareStatement("select amount from Payments where order_id=?");
                        ps3.setInt(1, rb.order_id);
                        ResultSet rs3 = ps3.executeQuery();
                        while (rs3.next()) {
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

    public Vector<ReportsBean> tbOrders(Date date1, Date date2) {
        Vector<ReportsBean> v = new Vector<ReportsBean>();
        try {
            PreparedStatement ps = conn.prepareStatement("select startdate,enddate,order_id from Orders");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Date enddate = new Date(rs.getDate("enddate").getTime());
                Date sdate = new Date(rs.getDate("startdate").getTime());
                if (date1.compareTo(sdate) < 0 && date2.compareTo(enddate) > 0) {
                    int orid = rs.getInt("order_id");
                    ReportsBean rb = new ReportsBean();
                    rb.order_id = orid;
                    PreparedStatement ps1 = conn.prepareStatement("select child_id,payment from Orders where order_id=?");
                    ps1.setInt(1, orid);
                    ResultSet rs1 = ps1.executeQuery();
                    while (rs1.next()) {
                        rb.payment = rs1.getInt("payment");
                        rb.cusname = ob.getCus(rs1.getInt("child_id"));
                    }
                    v.add(rb);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReportsBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return v;
    }

    public Vector<ReportsBean> tbPaids(Date date1, Date date2) {
        Vector<ReportsBean> v = new Vector<ReportsBean>();
        try {
            PreparedStatement ps = conn.prepareStatement("select startdate,enddate,order_id from Orders");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Date enddate = new Date(rs.getDate("enddate").getTime());
                Date sdate = new Date(rs.getDate("startdate").getTime());
                if (date1.compareTo(sdate) < 0 && date2.compareTo(enddate) > 0) {
                    int orid = rs.getInt("order_id");
                    PreparedStatement ps1 = conn.prepareStatement("select nanny_id,payment_id,amount from Payments where order_id=?");
                    ps1.setInt(1, orid);
                    ResultSet rs1 = ps1.executeQuery();
                    while (rs1.next()) {
                        ReportsBean rb = new ReportsBean();
                        rb.order_id = rs1.getInt("payment_id");
                        rb.payment = rs1.getInt("amount");
                        rb.nanny_name = nannyidtoString(rs1.getInt("nanny_id"));
                        v.add(rb);
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReportsBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return v;
    }

}
