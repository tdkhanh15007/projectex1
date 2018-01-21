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
    public int price;
    public int payment;

    public ReportsBean(int order_id, int nanny_id, int price, int payment) {
        this.order_id = order_id;
        this.nanny_id = nanny_id;
        this.price = price;
        this.payment = payment;
    }
    public Vector<ReportsBean> displayAll(){
        Vector<ReportsBean> v = new Vector<ReportsBean>();
        Instant now = Instant.now(); //current date
        Instant before = now.minus(Duration.ofDays(30));
        Date dateBefore = Date.from(before);        
        try {
            PreparedStatement ps1 = conn.prepareStatement("select startdate from Orders");
            ResultSet rs1 = ps1.executeQuery();
            while(rs1.next()){
                Date orderDate = new Date(rs1.getDate("startdate").getTime());
                if(orderDate.compareTo(dateBefore)>0){
                    PreparedStatement ps2 = conn.prepareStatement("select order_id,payment from Orders where startdate = ?");
                    java.sql.Date sqlDate = new java.sql.Date(orderDate.getTime());
                    ps2.setDate(1, sqlDate);
                    ResultSet rs2 = ps2.executeQuery();
                    while(rs2.next()){
                        order_id = rs2.getInt("")
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReportsBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return v;
    }
}
