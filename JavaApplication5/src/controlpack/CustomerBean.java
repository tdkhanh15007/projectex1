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
public class CustomerBean {
    MyDatabase dt = new MyDatabase();
    Connection conn = dt.getConnection();
    public String cus_email;
    public String cus_name;
    public String address;
    public String phone_number;
    public String work_phone;
    public boolean status;

    public CustomerBean(String cus_email, String cus_name, String phone_number, String work_phone, boolean status) {
        this.cus_email = cus_email;
        this.cus_name = cus_name;
        this.phone_number = phone_number;
        this.work_phone = work_phone;
        this.status = status;
    }

    public CustomerBean(String cus_email, String cus_name, String address, String phone_number, String work_phone, boolean status) {
        this.cus_email = cus_email;
        this.cus_name = cus_name;
        this.address = address;
        this.phone_number = phone_number;
        this.work_phone = work_phone;
        this.status = status;
    }
    
    

    public CustomerBean() {
    }
    public Vector<CustomerBean> displayAll(){
        Vector<CustomerBean> v = new Vector<CustomerBean>();
        try {
            PreparedStatement ps1 = conn.prepareStatement("select cus_email,cus_name,phone_number,work_phone,status from Customer");
            ResultSet rs1 = ps1.executeQuery();
            while(rs1.next()){
                CustomerBean cb = new CustomerBean();
                cb.cus_email = rs1.getString("cus_email");
                cb.cus_name = rs1.getString("cus_name");
                cb.phone_number = rs1.getString("phone_number");
                cb.work_phone = rs1.getString("work_phone");
                cb.status = rs1.getBoolean("status");
                v.add(cb);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return v;
    }
}
