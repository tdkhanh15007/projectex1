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
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
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
    public int id, fromOld;
    public float charges;

    public Vector<GroupsBean> loadAll() {
        Vector<GroupsBean> v = new Vector<GroupsBean>();
        try {
            PreparedStatement ps = conn.prepareStatement("select group_id,group_ages,group_charges from Groups");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                GroupsBean gb = new GroupsBean();
                gb.id = rs.getInt("group_id");
                gb.fromOld = rs.getInt("group_ages");
                gb.charges = rs.getFloat("group_charges");
                v.add(gb);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GroupsBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        return v;
    }

    public boolean updateGroup(int id, float charges) {
        try {
            PreparedStatement ps = conn.prepareStatement("update Groups set group_charges=? where group_id = ?");
            ps.setFloat(1, charges);
            ps.setInt(2, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(GroupsBean.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public int calYear(String birth) {
        int years = 0;
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int month = localDate.getMonthValue();
        int year = cal.get(Calendar.YEAR);
        int day = cal.get(Calendar.DATE);
        String str[] = birth.split("/");
        int dayb = Integer.parseInt(str[0]);
        int monthb = Integer.parseInt(str[1]);
        int yearb = Integer.parseInt(str[2]);
        if(monthb==0){
            monthb = 1;
        }
        LocalDate start = LocalDate.of(yearb, monthb, dayb);
        LocalDate end = LocalDate.of(year,month,day);
        long yearsold = ChronoUnit.YEARS.between(start, end);
        years = (int) yearsold;
        return years;
    }

}
