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
    ActBean acb = new ActBean();
    GroupsBean gb = new GroupsBean();
    public int order_id;
    public String child_name;
    public String cus_name;
    public String useremail;
    public String nanny_name;
    public String act_name;
    public String time_slot;
    public Date startDate;
    public Date endDate;
    public float payments;
    public String cmts;
    public String hours;

    public Vector<OrderBean> loadAll(String cusname) {
        Vector<OrderBean> v = new Vector<OrderBean>();
        try {
            //xoa hd cu
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
                    String childname = rs1.getString("fullname");
                    int childID = rs1.getInt("child_id");
                    PreparedStatement ps2 = conn.prepareStatement("select order_id from Orders where child_id=? and status='true'");
                    ps2.setInt(1, childID);
                    ResultSet rs2 = ps2.executeQuery();
                    while (rs2.next()) {
                        OrderBean ob = new OrderBean();
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
                        v.add(ob);
                    }
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
                while (rs1.next()) {
                    int childID = rs1.getInt("child_id");
                    PreparedStatement ps2 = conn.prepareStatement("select order_id,status from Orders where child_id=?");
                    ps2.setInt(1, childID);
                    ResultSet rs2 = ps2.executeQuery();
                    while (rs2.next()) {
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

    public void setStatus(int orID) {
        try {
            PreparedStatement ps = conn.prepareStatement("update Orders set status = 'false' where order_id=?");
            ps.setInt(1, orID);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(OrderBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Vector<OrderBean> onclickTable(int id) {
        Vector<OrderBean> v = new Vector<OrderBean>();
        try {
            PreparedStatement ps = conn.prepareStatement("select startdate,enddate,payment,comments from Orders where order_id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                OrderBean ob = new OrderBean();
                Date startdate = new Date(rs.getDate("startdate").getTime());
                Date enddate = new Date(rs.getDate("enddate").getTime());
                float payment = rs.getFloat("payment");
                String cmt = rs.getString("comments");
                ob.startDate = startdate;
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

    public boolean updateOr(int id, Date sDate, Date eDate, String cmt) {
        int day = daysBetween(sDate, eDate);
        float priceper = getPrice(id);
        float newpay = day * priceper;
        try {
            PreparedStatement ps = conn.prepareStatement("update Orders set enddate=?,payment=?,comments=? where order_id=?");
            ps.setDate(1, new java.sql.Date(eDate.getTime()));
            ps.setFloat(2, newpay);
            ps.setString(3, cmt);
            ps.setInt(4, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(OrderBean.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public int daysBetween(Date d1, Date d2) {
        return (int) ((d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
    }

    public float getPrice(int id) {
        float price = 0;
        float price1 = 0;
        try {
            PreparedStatement ps = conn.prepareStatement("select payment from Orders where order_id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                price = rs.getFloat("payment");
            }
            PreparedStatement ps1 = conn.prepareStatement("select startdate,enddate from Orders where order_id=?");
            ps1.setInt(1, id);
            ResultSet rs1 = ps1.executeQuery();
            while (rs1.next()) {
                Date startdate = new Date(rs1.getDate("startdate").getTime());
                Date enddate = new Date(rs1.getDate("enddate").getTime());
                price1 = price / daysBetween(startdate, enddate);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return price1;
    }

    public Vector<OrderBean> displayTime() {
        Vector<OrderBean> v = new Vector<OrderBean>();
        try {
            PreparedStatement ps = conn.prepareStatement("select timer from Hours");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                OrderBean ob = new OrderBean();
                ob.time_slot = rs.getString("timer");
                v.add(ob);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return v;
    }

    public Vector<OrderBean> searchFreeNanny(String actName, int timeID, Date fromDay) {
        Vector<OrderBean> v = new Vector<OrderBean>();
        int actID = acb.nametoID(actName);
        try {
            PreparedStatement ps = conn.prepareStatement("select nanny_id from Nannies where activity_id=?");
            ps.setInt(1, actID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int nannyid = rs.getInt("nanny_id");
                if (!notFreeTime(nannyid, timeID) || !notFreeDay(nannyid, fromDay)) {
                    OrderBean ob = new OrderBean();
                    ob.nanny_name = getNannyname(nannyid);
                    v.add(ob);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return v;
    }

    public boolean notFreeTime(int nannyID, int timeID) {
        try {
            PreparedStatement ps = conn.prepareStatement("select order_id from OrdersDetails where hour_id=? and nanny_id=?");
            ps.setInt(1, timeID);
            ps.setInt(2, nannyID);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException ex) {
            Logger.getLogger(OrderBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean notFreeDay(int nannyID, Date requestDate) {
        try {
            PreparedStatement ps = conn.prepareStatement("select order_id from OrdersDetails where nanny_id=?");
            ps.setInt(1, nannyID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int orID = rs.getInt("order_id");
                PreparedStatement ps1 = conn.prepareStatement("select startdate,enddate from Orders where order_id=?");
                ps1.setInt(1, orID);
                ResultSet rs1 = ps1.executeQuery();
                while (rs1.next()) {
                    Date startdate = new Date(rs1.getDate("startdate").getTime());
                    Date enddate = new Date(rs1.getDate("enddate").getTime());
                    if (requestDate.compareTo(startdate) > 0 && requestDate.compareTo(enddate) < 0) {
                        return true;
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public float getActFee(String activityname) {
        float price = 0;
        try {
            PreparedStatement ps = conn.prepareStatement("select activity_charges from Activity where activity=?");
            ps.setString(1, activityname);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                price = rs.getFloat("activity_charges");
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return price;
    }

    public float getGroupFee(String birth) {
        float price = 0;
        int year = gb.calYear(birth);
        try {
            PreparedStatement ps = conn.prepareStatement("select group_charges from Groups where group_ages=?");
            ps.setInt(1, year);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                price = rs.getFloat("group_charges");
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return price;
    }

    public int getGroupID(String birth) {
        int price = 0;
        int year = gb.calYear(birth);
        try {
            PreparedStatement ps = conn.prepareStatement("select group_id from Groups where group_ages=?");
            ps.setInt(1, year);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                price = rs.getInt("group_id");
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return price;
    }

    public boolean inserOrder(float price, Date startdate, String user, int childID, Date endDate, int nanny_id, int group_id, int hour_id) {
        try {
            if (checkAlready(childID, startdate, hour_id)) {
                return false;
            } else {
                PreparedStatement ps = conn.prepareStatement("insert into Orders(payment,startdate,user_email,child_id,enddate,status) values(?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
                ps.setFloat(1, price);
                ps.setDate(2, new java.sql.Date(startdate.getTime()));
                ps.setString(3, user);
                ps.setInt(4, childID);
                ps.setDate(5, new java.sql.Date(endDate.getTime()));
                ps.setBoolean(6, true);
                ps.executeUpdate();
                ResultSet rs = ps.getGeneratedKeys();
                while (rs.next()) {
                    int id = rs.getInt(1);
                    PreparedStatement ps1 = conn.prepareStatement("insert into OrdersDetails(order_id,nanny_id,group_id,hour_id) values(?,?,?,?)");
                    ps1.setInt(1, id);
                    ps1.setInt(2, nanny_id);
                    ps1.setInt(3, group_id);
                    ps1.setInt(4, hour_id);
                    ps1.executeUpdate();
                    return true;
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(OrderBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean checkAlready(int child_id, Date orderDate, int time_id) {
        try {
            PreparedStatement ps = conn.prepareStatement("select order_id,startdate,enddate from Orders where child_id=?");
            ps.setInt(1, child_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Date startdate = new Date(rs.getDate("startdate").getTime());
                Date enddate = new Date(rs.getDate("enddate").getTime());
                if (orderDate.after(startdate) && orderDate.before(enddate)) {
                    PreparedStatement ps1 = conn.prepareStatement("select hour_id from OrdersDetails where order_id=?");
                    ps1.setInt(1, rs.getInt("order_id"));
                    ResultSet rs1 = ps1.executeQuery();
                    while (rs1.next()) {
                        if (time_id == rs1.getInt("hour_id")) {
                            return true;
                        }
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public Vector<OrderBean> getReportlist(Date date1, Date date2, boolean status) {
        Vector<OrderBean> v = new Vector<OrderBean>();
        try {
            PreparedStatement ps = conn.prepareStatement("select startdate,enddate,order_id from Orders where status = ?");
            ps.setBoolean(1, status);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Date enddate = new Date(rs.getDate("enddate").getTime());
                Date sdate = new Date(rs.getDate("startdate").getTime());
                int id = rs.getInt("order_id");
                if (date1.compareTo(sdate) < 0 && date2.compareTo(enddate) > 0) {
                    OrderBean ob = new OrderBean();
                    ob.order_id = id;
                    PreparedStatement ps1 = conn.prepareStatement("select user_email,child_id,comments from Orders where order_id=?");
                    ps1.setInt(1, id);
                    ResultSet rs1 = ps1.executeQuery();
                    while (rs1.next()) {
                        ob.useremail = rs1.getString("user_email");
                        ob.cus_name = getCus(rs1.getInt("child_id"));
                        ob.cmts = rs1.getString("comments");
                    }
                    v.add(ob);
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        return v;
    }

    public String getCus(int child_id) {
        String name = "";
        try {
            PreparedStatement ps = conn.prepareStatement("select cus_email from Chirldren where child_id=?");
            ps.setInt(1, child_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String email = rs.getString("cus_email");
                PreparedStatement ps1 = conn.prepareStatement("select cus_name from Customer where cus_email=?");
                ps1.setString(1, email);
                ResultSet rs1 = ps1.executeQuery();
                while (rs1.next()) {
                    name = rs1.getString("cus_name");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return name;
    }

}
